package yc.Http;

import java.nio.charset.StandardCharsets;

public class RequestParser {
    private Request request;
    private boolean isDone;
    private boolean isHeaderDone;
    private byte[] last_buffer;
    private int body_length;

    public RequestParser(){
        reset();
    }
    public void reset(){
        request = new Request();
        isDone = false;
        isHeaderDone = false;
        body_length=0;
        last_buffer=new byte[0];
    }
    public int put(byte[] buffer,int length){
        if(!isHeaderDone){
            return handle_header(buffer,length);
        }else if(!isDone){
            return handle_body(buffer,length);
        }
        return 0;
    }
    private int handle_header(byte[] buffer,int length){
        byte[] new_buffer=new byte[last_buffer.length+length];
        System.arraycopy(last_buffer,0,new_buffer,0,last_buffer.length);
        System.arraycopy(buffer,0,new_buffer,last_buffer.length,length);
        String bufferString =new String(new_buffer,0,new_buffer.length, StandardCharsets.UTF_8);
        if(bufferString.contains("\r\n\r\n")){
            var fields=bufferString.split("\r\n");
            String head=fields[0];
            var heads=head.split(" ");
            request.setMethod(heads[0]);
            request.setTarget(heads[1]);
            request.setVersion(heads[2]);
            for(int i=1;i<fields.length-1;++i){
                var key_value=fields[i].split(": ");
                request.put(key_value[0],key_value[1]);
            }
            int n=bufferString.indexOf("\r\n\r\n");
            n=n+4-last_buffer.length;
            last_buffer=new byte[0];
            isHeaderDone=true;
            String ll=request.get(RequestField.Content_Length);
            if(ll==null){
                isDone=true;
            }else{
                body_length=Integer.parseInt(ll);
            }
            return n;
        }else{
            last_buffer=new_buffer;
            return length;
        }
    }
    private int handle_body(byte[] buffer,int length){
        int diff_length=body_length-request.getBodyLength();
        if(length<diff_length){
            request.appendBody(new String(buffer,0,length,StandardCharsets.UTF_8));
            return length;
        }else{
            request.appendBody(new String(buffer,0,diff_length,StandardCharsets.UTF_8));
            isDone=true;
            return diff_length;
        }
    }
    public Request getRequest(){
        return request;
    }

    public boolean isDone(){
        return isDone;
    }

}

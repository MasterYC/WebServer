package yc.Http;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestParser {
    private Request request;
    private boolean isDone = false;
    private boolean isHeaderDone = false;
    private boolean isFieldDone = false;
    String last_buffer= "";
    int body_length=0;

    public RequestParser(){
        reset();
    }
    public void reset(){
        request = new Request();
        isDone = false;
        isHeaderDone = false;
        isFieldDone = false;
        body_length=0;
        last_buffer="";
    }
    public int put(byte[] buffer,int length){
        if(!isHeaderDone){
            return handle_header(buffer,length);
        } else if (!isFieldDone) {
            return handle_field(buffer,length);
        }else if(!isDone){

        }
        return 0;
    }
    private int handle_header(byte[] buffer,int length){
        String bufferString =last_buffer+ new String(buffer,0,length, StandardCharsets.UTF_8);
        if(bufferString.contains("\r\n")){
            var head=bufferString.split("\r\n");
            var heads=head[0].split(" ");
            request.setMethod(heads[0]);
            request.setTarget(heads[1]);
            request.setVersion(heads[2]);
            int n=bufferString.indexOf("\r\n");
            n=n+1-last_buffer.length();
            isHeaderDone=true;
            last_buffer="";
            return n;
        }else{
            last_buffer=bufferString;
            return length;
        }
    }
    private int handle_field(byte[] buffer,int length){
        String bufferString =last_buffer+ new String(buffer,0,length, StandardCharsets.UTF_8);
        if(bufferString.contains("\r\n\r\n")){
            var fields=bufferString.split("\r\n");
            for (String field : fields) {
                var key_value = field.split(": ");
                request.put(key_value[0], key_value[1]);
            }
            int n=bufferString.indexOf("\r\n\r\n");
            n=n+3-last_buffer.length();
            isFieldDone=true;
            last_buffer="";
            return n;
        }else{
            last_buffer=bufferString;
            return length;
        }
    }
}

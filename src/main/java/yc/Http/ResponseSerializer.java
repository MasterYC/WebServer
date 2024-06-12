package yc.Http;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResponseSerializer {
    private Response response;
    private boolean isDone;
    private byte[] buffer;
    private int cursor;
    public ResponseSerializer(Response response){
        this.response=response;
        this.isDone=false;
        this.cursor=0;
        this.buffer=null;
    }
    public byte[] consumeAll(){
        this.isDone=false;
        this.cursor=0;
        return response.toString().getBytes(StandardCharsets.UTF_8);
    }
    public byte[] consume(int n){
        if(buffer==null)buffer=consumeAll();
        cursor=cursor+n;
        byte[] temp=null;
        if(cursor<buffer.length){
            temp=Arrays.copyOfRange(buffer,cursor-n,cursor);
        }else{
            temp=Arrays.copyOfRange(buffer,cursor-n,buffer.length-1);
            buffer=null;
            isDone=true;
        }
        return temp;

    }
    public boolean isDone(){
        return isDone;
    }
}

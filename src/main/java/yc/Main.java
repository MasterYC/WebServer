package yc;

//import java.util.logging.Logger;
import yc.Http.RequestField;
import yc.Http.RequestParser;
import yc.Http.Response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(8080);
        var socket=serverSocket.accept();
        InputStream inputstream=new BufferedInputStream(socket.getInputStream());
        RequestParser requestParser=new RequestParser();
        byte[] bytes=new byte[1024];
        while(!requestParser.isDone()){
            int n=inputstream.available();
            inputstream.mark(1024);
            if(n==0){
                n=inputstream.read(bytes,0,1);
            }else if(n<=1024){
                n=inputstream.read(bytes,0,n);
            }else{
                n=inputstream.read(bytes,0,1024);
            }
            int m=requestParser.put(bytes,n);
            if(m<n){
                inputstream.reset();
                inputstream.skipNBytes(m);
            }
        }
        OutputStream outputStream=new BufferedOutputStream(socket.getOutputStream());
        Response response=new Response();
        response.setBody("hello world");
        outputStream.write(response.toString().getBytes(StandardCharsets.UTF_8));
        socket.close();
        System.out.println(requestParser.getRequest().toString());
        System.out.println(response.toString());




    }
}
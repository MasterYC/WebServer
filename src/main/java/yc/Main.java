package yc;

//import java.util.logging.Logger;
import yc.Http.RequestField;
import yc.Http.RequestParser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(80);
        var socket=serverSocket.accept();
        InputStream inputstream=socket.getInputStream();
        inputstream.mark(2000);
        RequestParser requestParser=new RequestParser();
        byte[] bytes=new byte[1024];
//        while(!requestParser.isDone()){
//            int n=inputstream.available();
//            inputstream.mark(1024);
//            if(n==0){
//                n=inputstream.read(bytes,0,1);
//            }else if(n<=1024){
//                n=inputstream.read(bytes,0,n);
//            }else{
//                n=inputstream.read(bytes,0,1024);
//            }
//            int m=requestParser.put(bytes,n);
//            if(m<n){
//                inputstream.reset();
//                inputstream.skipNBytes(m);
//            }
//        }
        inputstream.mark(1024);
        inputstream.skip(5);
        int n=inputstream.read(bytes,0,10);


        System.out.println(new String(bytes,0,n, StandardCharsets.UTF_8));
        inputstream.reset();
        inputstream.skip(5);
        n=inputstream.read(bytes,0,10);

        System.out.println(new String(bytes,0,n, StandardCharsets.UTF_8));
        System.out.println(inputstream.markSupported());


    }
}
package yc;

//import java.util.logging.Logger;
import yc.Http.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.run();
//        Response response=new Response();
//        response.setBody("niasdsa");
//        ResponseSerializer responseSerializer=new ResponseSerializer(response);
//        while(!responseSerializer.isDone()){
//            System.out.print(new String(responseSerializer.consume(50)));
//        }



    }
}
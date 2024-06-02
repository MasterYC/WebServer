package yc;

//import java.util.logging.Logger;
import yc.Http.RequestField;

import java.nio.charset.StandardCharsets;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws Exception {
        String str="Get / HTTP/1.1\r\nCon";
        System.out.println(str.indexOf("\r\n"));


    }
}
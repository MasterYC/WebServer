package yc;

//import java.util.logging.Logger;
import yc.Http.RequestField;
import yc.Http.RequestParser;
import yc.Http.Response;
import yc.Http.Server;

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
        Server server = new Server(8080);
        server.run();




    }
}
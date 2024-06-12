package yc.Http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Session {
    private final Socket socket;
    public Session(Socket socket)  {
        this.socket=socket;
        try {
            socket.setKeepAlive(true);
            socket.setSoTimeout(10000);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void run(){
        try {
            byte[] bytes=new byte[1024];
            var reader=new BufferedInputStream(socket.getInputStream());
            var writer=socket.getOutputStream();
            while(true){
                RequestParser requestParser=new RequestParser();
                while(!requestParser.isDone()){
                    int n=reader.available();
                    reader.mark(1024);
                    if(n==0){
                        n=reader.read(bytes,0,1);
                    }else if(n<=1024){
                        n=reader.read(bytes,0,n);
                    }else{
                        n=reader.read(bytes,0,1024);
                    }
                    int m=requestParser.put(bytes,n);
                    if(m<n){
                        reader.reset();
                        reader.skipNBytes(m);
                    }
                }
                Response response=new Response();
                response.put(ResponseField.Connection,"keep-alive");
                response.setBody("hello world");
                writer.write(response.toString().getBytes());
                writer.flush();
//                System.out.println(requestParser.getRequest());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}

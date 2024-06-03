package yc.Http;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private int port;
    ExecutorService executorService;
    public Server(int port) throws IOException {
        this.port=port;
        serverSocket=new ServerSocket(port);
        executorService= Executors.newVirtualThreadPerTaskExecutor();
    }
    public void run(){
        while(true){
            try {
                var socket=serverSocket.accept();
                executorService.execute(new Session(socket)::run);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

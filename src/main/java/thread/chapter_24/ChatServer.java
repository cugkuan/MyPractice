package thread.chapter_24;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private int port;


    private ExecutorService threadPool;

    private ServerSocket serverSocket;

    public ChatServer(int port){
        this.port = port;
    }


    public ChatServer(){
        this(13312);
    }

    public void startServer() throws IOException{
        this.threadPool = Executors.newFixedThreadPool(3);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);

        listen();

    }

    private void listen() throws IOException {

        for (;;){
            Socket client = serverSocket.accept();
            threadPool.execute(new ClientHandler(client));
        }
    }

    public static void main(String[] arg) throws IOException {
        new ChatServer().startServer();
    }



}

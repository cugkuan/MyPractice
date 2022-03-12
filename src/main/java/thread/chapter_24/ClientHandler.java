package thread.chapter_24;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {


    private final Socket socket;

    private final String clientIdentify;

    public ClientHandler(Socket socket){
        this.socket = socket;
        this.clientIdentify = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
    }

    @Override
    public void run() {

        try {
            chat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chat() throws IOException{

        BufferedReader bufferedReader =  warp2Reader(socket.getInputStream());
        PrintStream printStream = wrap2Print(socket.getOutputStream());
        String received ;
        while ((received = bufferedReader.readLine()) != null){
            System.out.printf("client:%s-message:%s\n",clientIdentify,received);
            if (received.equals("quit")){
                write2Client(printStream,"client will close");
                socket.close();
                break;
            }
            // 向客户端发送消息
            write2Client(printStream,"server:"+received);
        }

    }

    private BufferedReader warp2Reader(InputStream inputStream){

        return new BufferedReader( new InputStreamReader(inputStream));

    }

    private PrintStream wrap2Print(OutputStream outputStream){

        return  new PrintStream(outputStream);
    }

    private void write2Client(PrintStream print,String msg){

        print.println(msg);
        print.flush();
    }
}

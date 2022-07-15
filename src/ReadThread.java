import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReadThread extends Thread{
    private BufferedReader reader;
    private Socket socket;
    private ChatClient chatClient;

    /**
     * parameterized Constructor
     */

    public ReadThread(Socket socket, ChatClient chatClient){
        this.socket = socket;
        this.chatClient = chatClient;

        try{
            InputStream input = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

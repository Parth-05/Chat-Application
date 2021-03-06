import java.net.Socket;

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void execute(){
        try{
            Socket socket = new Socket(hostname,port);
            System.out.println("Connected to chat Server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        }catch(Exception e){
            System.out.println("Server not Found: " + e.getMessage());
        }
    }

    void SetUserName(String userName){
        this.userName = userName;
    }
     String getUserName(){
        return this.userName = userName;
     }

    public static void main(String[] args){
        System.out.println("Chat App");

        if (args.length <2)
            return;

        String hostname = args[0];
        Integer port = Integer.parseInt(args[1]);

        ChatClient cl = new ChatClient(hostname, port);
        cl.execute();
    }
}
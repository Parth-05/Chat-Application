import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private Integer port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(Integer port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat Server is listening at the port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            System.out.println("Error in the server : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 8989;

        ChatServer server = new ChatServer(port);
        server.execute();
    }

    void broadcast(String message, UserThread excludeUser) {
        for(UserThread aUser : userThreads) {
            if(aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

    void addUserName(String userName) {
        userNames.add(userName);
    }

    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if(removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + "quit");
        }
    }
    Set<String> getUserNames() {
        return this.userNames;
    }
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}

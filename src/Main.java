public class Main {
    public static void main(String[] args){
        System.out.println("Chat App");

        if (args.length <2)
            return;

        String hostname = args[0];
        Integer port = Integer.parseInt(args[1]);

        Client cl = new Client(hostname, port);
        cl.execute();
    }
}

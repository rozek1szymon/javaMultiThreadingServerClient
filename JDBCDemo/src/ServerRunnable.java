import java.io.*;
import java.net.Socket;

public class ServerRunnable implements Runnable {
    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            String command = reader.readLine();
            if(command.equals("1"))
            {
                writer.println("polaczyles sie");
                System.out.println("mam info");
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception" + ex.getMessage());
        }
    }
}
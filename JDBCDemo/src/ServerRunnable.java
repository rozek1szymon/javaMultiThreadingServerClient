import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            DatabaseConnection databaseConnection = new DatabaseConnection();
            if(command.equals("1"))
            {

                ResultSet myRs = databaseConnection.ReturnHotels();
                writer.println(myRs);
                String newdata = "";

                while (myRs.next())
                {
                   newdata += myRs.getString("Name" ) + "," + myRs.getString("Localization") + "," + myRs.getString("Price_per_person") + ":";

                }
                writer.println(newdata);
                System.out.println(newdata);

            }
            else if(command.substring(0,1).equals("2"))
            {
                String nameOfHotel = command.replace("2","");
                databaseConnection.UpdateHotels(nameOfHotel);
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception" + ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
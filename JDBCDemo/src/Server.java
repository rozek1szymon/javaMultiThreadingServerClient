import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.*;


public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port)
    {
        try
        {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=HotelsManager;integratedSecurity=true;";

            ResultSet myRs = null;
            ResultSet myRs2 = null;

            System.out.println("works");

            server = new ServerSocket(port);

            System.out.println("Server started");

            System.out.println("Waiting for client ...");

            socket = server.accept();

            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line="";
            Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();
            Statement myStmt;

            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    if(line.equals("1"))
                    {
                        myStmt = con.createStatement();
                        myRs = myStmt.executeQuery("select * from Hotels");
                        while (myRs.next())
                        {
                            System.out.println(myRs.getString("Name"));
                        }
                    }
                    else if(line.equals("2"))
                    {
                        myStmt = con.createStatement();
                        myRs = myStmt.executeQuery("select * from Hotels order by Price_per_person desc");
                        while (myRs.next())
                        {
                            System.out.println(myRs.getString("Name"));
                        }
                    }
                    else
                    {
                        System.out.println(line.length());
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

            System.out.println("Closing connection");

            socket.close();
            in.close();
        }
        catch
        (IOException e)
        {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        // Create a variable for the connection string.
        Server server = new Server(5000);

    }
}

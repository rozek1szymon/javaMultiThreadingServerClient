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

public class DatabaseConnection
{
    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=HotelsManager;integratedSecurity=true;";
    ResultSet myRs = null;
    ResultSet myRs2 = null;
    Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();
    Statement myStmt;

    public DatabaseConnection() throws SQLException
    {
        myStmt = con.createStatement();
    }

    public ResultSet ReturnHotels() throws SQLException
    {
        myRs = myStmt.executeQuery("select * from Hotels where IsBooked=0 order by Price_per_person desc");

        return  myRs;

    }
    public void UpdateHotels(String hotel) throws SQLException {
        myStmt.executeUpdate(String.format("Update Hotels Set IsBooked = 1 where Name = '%s'", hotel));
    }
}

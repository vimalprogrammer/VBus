import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection getConnection() {

        Connection con = null;

        try
        {
            Class.forName("org.postgresql.Driver");
            System.out.println("Opened database successfully - 1");
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found "+e);
            System.out.println("Error in loading driver");
        }
        
        try 
        {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
            con.setAutoCommit(false);
            System.out.println("**ConnectionDB called Successfully**");
        }
        catch (SQLException ex) 
        {
                System.out.println("SQL exception occured" + ex);
        }
        return con;
    }

}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.net.InetAddress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class Edit_bus extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {  
                       
        HttpSession session = request.getSession();

        String bus_no = request.getParameter("bus_number");
        String bus_name = request.getParameter("bus_name");
        String seats = request.getParameter("seats");
        String sleeper = request.getParameter("sleeper");
        String dep = request.getParameter("dep");
        String des = request.getParameter("des");
        String price = request.getParameter("price");

        // JSONArray groups = new JSONArray();
        JSONObject singlegroup = new JSONObject();
        singlegroup.put("id",1);
        singlegroup.put("bus_no", bus_no);
        singlegroup.put("bus_name", bus_name);
        singlegroup.put("seats", seats);
        singlegroup.put("sleeper", sleeper);
        singlegroup.put("dep", dep);
        singlegroup.put("des", des);
        singlegroup.put("price", price);
        // groups.add(singlegroup);        

        PrintWriter out = response.getWriter();
        int jsonid=0;

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Opened database successfully - 1");
        }catch(ClassNotFoundException e){
            System.out.println("Class not found "+e);
            System.out.println("Error in loading driver");
        }
        try
        {
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
            c.setAutoCommit(false);
           
           
           Statement stmt = c.createStatement();
           
            ResultSet rs = stmt.executeQuery( "SELECT * FROM bus_details where bus_id='"+bus_no+"'");
           if(rs.next())
           {
           
             String sql="delete from bus_details where bus_id = " + bus_no;  
             stmt.executeUpdate(sql);
           
             //out.println("Bus deleted successfully");
           
             String sql1 = "INSERT INTO Bus_Details (BUS_ID,BUS_NAME,TOTAL_SEATS,AC_or_NON_AC,DEPARTURE,DESTINATION,PRICE)"
               + "VALUES ('"+bus_no+"','"+bus_name+"','"+seats+"','"+sleeper+"','"+dep+"','"+des+"','"+price+"');";
           
             stmt.executeUpdate(sql1);
            
            //Bus Seats 
            String s="b"+bus_no;
            String sql2="drop table "+s+" ";
            stmt.executeUpdate(sql2);
            
            String sql3="create table "+s+" (id serial primary key,"+" seat_no text not null)";
            stmt.executeUpdate(sql3);
            
            String sql4="insert into "+s+" (seat_no) select i from generate_series(1,'"+seats+"') i";
            stmt.executeUpdate(sql4);
           
             stmt.close();
             c.commit();
             c.close();
           }
        }
        catch(SQLException e)
        {
            out.println("Error Caused\n "+e);        
            e.printStackTrace();
        }   
        out.println(singlegroup);
    }     
}

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


public class delete_bus extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {  
                       
        HttpSession session = request.getSession();

        String bus_no = request.getParameter("bus_no");

        JSONObject singlegroup = new JSONObject();
        singlegroup.put("id",1);
        singlegroup.put("bus_no", bus_no);

        PrintWriter out = response.getWriter();
        int jsonid=0;

        try{
            Connection c=ConnectionDB.getConnection();         
            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM bus_details where bus_name='"+bus_no+"'");

            if(rs.next())
            {
                String sql="delete from bus_details where bus_name = '"+bus_no+"'";  
                stmt.executeUpdate(sql);
                
                String message = "Bus Deleted Successfully!";
                System.out.println(message);
            }

             stmt.close();
             c.commit();
             c.close();
        }
        catch(SQLException e)
        {
            out.println("Error Caused\n "+e);        
            e.printStackTrace();
        }   
        out.println(singlegroup);
    }     
}

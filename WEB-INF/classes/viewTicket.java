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


public class viewTicket extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {  
                       
                HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");
        System.out.println(username+" "+email);
        JSONArray groups = new JSONArray();
        PrintWriter out = response.getWriter();
        int jsonid=0;

        try{
            Connection c=ConnectionDB.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ticket_bookings where mail='"+email+"'");
            System.out.println("ViewTickets Called");
            while(rs.next())
            {
                JSONObject singlegroup = new JSONObject();
                singlegroup.put("id", jsonid);
                singlegroup.put("user_id", rs.getString(1));
                singlegroup.put("username", rs.getString(2));
                singlegroup.put("email", rs.getString(3));
                singlegroup.put("ticket_id", rs.getString(4));
                singlegroup.put("bus_no", rs.getString(5));
                singlegroup.put("price", rs.getString(6));
                groups.add(singlegroup);
                jsonid=jsonid+1;
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e)
        {
            out.println("Error Caused\n "+e);        
            e.printStackTrace();
        }   
        out.println(groups);
    }     
}

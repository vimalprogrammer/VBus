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


public class availableTicket extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);

                String departure=String.valueOf(session.getAttribute("departure"));
                String destination=String.valueOf(session.getAttribute("destination"));

                JSONArray groups = new JSONArray();
                int jsonid=0;

        try{
            Connection c=ConnectionDB.getConnection();            

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM bus_details where departure='"+departure+"' and destination='"+destination+"'");
            
            if(rs.next())
            {
             do
             {
                 String bus_no = rs.getString("bus_id");
                 String bus_name = rs.getString("bus_name");
                 String seats = rs.getString("total_seats");
                 String sleeper = rs.getString("ac_or_non_ac");
                 String dep = rs.getString("departure");
                 String des = rs.getString("destination");
                 String price = rs.getString("price");
                 String ac = rs.getString("ac");

                 JSONObject singlegroup = new JSONObject();
                 singlegroup.put("id", jsonid);
                 singlegroup.put("bus_no", rs.getString("bus_id"));
                 singlegroup.put("bus_name", rs.getString("bus_name"));
                 singlegroup.put("seats", rs.getString("total_seats"));
                 singlegroup.put("sleeper", rs.getString("ac_or_non_ac"));
                 singlegroup.put("dep", rs.getString("departure"));
                 singlegroup.put("des", rs.getString("destination"));
                 singlegroup.put("price", rs.getString("price"));
                 singlegroup.put("ac", rs.getString("ac"));
                 groups.add(singlegroup);
                 jsonid=jsonid+1;
            
             } while(rs.next());
            
            stmt.close();
            c.close();
            }
            }            
            catch(SQLException e)
            {
                out.println("Error Caused\n "+e);        
                e.printStackTrace();
            }
            out.println(groups);
        }
}
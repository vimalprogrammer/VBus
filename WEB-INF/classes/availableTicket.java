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
                //inialize session
                HttpSession session = request.getSession(true);

                String departure=String.valueOf(session.getAttribute("departure"));
                String destination=String.valueOf(session.getAttribute("destination"));

                JSONArray groups = new JSONArray();
                int jsonid=0;

                try{
                    Class.forName("org.postgresql.Driver");
                    System.out.println("Opened database successfully - 1");
                }catch(ClassNotFoundException e){
                    System.out.println("Class not found "+e);
                    System.out.println("Error in loading driver");
                }
        try{
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
            //c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM bus_details where departure='"+departure+"' and destination='"+destination+"'");

            // out.println("<form action='book_tkt.jsp' >");

            // out.println("<br><br><br>");
            // out.println("<h3>Click bus Id to View Tickets</h3>");
            
            if(rs.next())
            {
             do
             {
                //out.println("AvailTKt in");
                //debugging purpose
                 String bus_no = rs.getString("bus_id");
                 String bus_name = rs.getString("bus_name");
                 String seats = rs.getString("total_seats");
                 String sleeper = rs.getString("ac_or_non_ac");
                 String dep = rs.getString("departure");
                 String des = rs.getString("destination");
                 String price = rs.getString("price");
                 String ac = rs.getString("ac");

                 JSONObject singlegroup = new JSONObject();
                 // out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2)+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" +rs.getString(5) +"</td><td>"+rs.getString(6)+"</td></tr>");
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
                        
                // out.println("<tr><td><input type=\"submit\" name=\"bus_no\" value="+bus_no+"><td>" + bus_name + "</td><td>" + seats + "</td><td>" + sleeper + "</td><td>" + dep + "</td><td>" + des+"</td><td>"+price+"</td><td>"+ac+"</td></tr>");

                // System.out.println("---------------------------------------------------");
                // System.out.println("Bus No: "+bus_no);
                // System.out.println("Bus Name: "+bus_name);
                // System.out.println("Seats: "+seats);
                // System.out.println("Sleeper: "+sleeper);
                // System.out.println("Departure: "+dep);
                // System.out.println("Destination: "+des);
                // System.out.println("Price: "+price);
                // System.out.println("AC: "+ac);
                // System.out.println("---------------------------------------------------");
            
             } while(rs.next());
            
            //  out.println("</table></form>");
            
             //stmt.executeUpdate(sql);
            //stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            }
            // else
            // {
            //        out.println("<script type=\"text/javascript\">");
            //        out.println("alert('No bus available with these spots!');");
            //        out.println("location='book_ticket1.jsp';");
            //        out.println("</script>");
            // }
            }            
            catch(SQLException e)
            {
                out.println("Error Caused\n "+e);        
                e.printStackTrace();
            }
            out.println(groups);
        }
}
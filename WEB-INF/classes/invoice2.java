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


public class invoice2 extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {    
        System.out.println("invoice 2 Called");
        HttpSession session = request.getSession();


        String seat_booked = session.getAttribute("seat_booked").toString();
        System.out.println("seat_booked " + seat_booked);
        session.setAttribute("seat_booked", seat_booked);
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String bus_no= (String) session.getAttribute("bus_no");
        String email= (String) session.getAttribute("email");
        try{
            Connection c=ConnectionDB.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_details where email='"+email+"'");
            String  first_name="";
            String  last_name="";
            email="";

            while ( rs.next() ) {
                first_name = rs.getString("first_name");
                email = rs.getString("email");
                session.setAttribute("first_name", first_name);
                session.setAttribute("email", email);
            }

                ResultSet rs1 = stmt.executeQuery( "SELECT * FROM bus_details where bus_name='"+bus_no+"'");
                rs1.next();
                String price=rs1.getString("price");
                session.setAttribute("price",price);
                String booked_seat = "B"+seat_booked;
                String sql1 = "Update "+bus_no+" set seat_no='"+booked_seat+"' where id='"+seat_booked+"'; ";
                stmt.executeUpdate(sql1);
                
                String sql = "INSERT INTO ticket_bookings (name,mail,ticket_no,bus_no,price) "
                    + "VALUES ('"+first_name+"','"+email+"','"+seat_booked+"','"+bus_no+"','"+price+"');";
                
                stmt.executeUpdate(sql);
                rs.close();
                rs1.close();
                stmt.close();
                c.close();

            }catch(SQLException e){
                System.out.println("Error in connection "+e);
            }

        String google_sign_in = String.valueOf(session.getAttribute("google_sign_in"));
        
        String google_access_token = String.valueOf(session.getAttribute("google_access_token"));
        PrintWriter out = response.getWriter();
        JSONObject seat = new JSONObject();
        seat.put("id", 1);
        seat.put("seat_booked", seat_booked);
        seat.put("name", username);
        seat.put("email", session.getAttribute("email"));
        seat.put("price", session.getAttribute("price"));
        seat.put("bus_no", bus_no);
        if(google_sign_in.equals("true")){
            seat.put("google_sign_in", true);
            seat.put("google_access_token", google_access_token);
            System.out.println("------GOOGLE SIGN------");
            System.out.println("google_access_token: " + google_access_token );
            System.out.println("------GOOGLE SIGN------");
        }else{
            seat.put("google_sign_in", false);
        }
        out.println(seat);
    }
}

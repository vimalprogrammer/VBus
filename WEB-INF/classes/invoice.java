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


public class invoice extends HttpServlet {

    public static String Booking_poss=null;

    // public void setinfo(String Booking_poss){
    //     this.Booking_poss=Booking_poss;
    //     System.out.println("Booking possible set function ::"+Booking_poss);
    // }


    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {    

        System.out.println("Invoice Called.....");
        HttpSession session = request.getSession();

        // String seat_booked = request.getParameter("bus_booked");
        // String bus_no_blocked= (String) session.getAttribute("bus_no");
        // session.setAttribute("seat_booked", seat_booked);
        // System.out.println("seat_booked is: " + seat_booked);

        // String seat_block = (String) session.getAttribute("seat_booked");
        // System.out.println("seat_block is: " + seat_block);
        // String seat_block_flag="0";
        // int bus_exist_db=0;
        // String bus_seat_check="";

        // try{
        //     Class.forName("org.postgresql.Driver");
        // }catch(ClassNotFoundException e){
        //     System.out.println("Class not found "+e);
        // }

        // try{
        //     bus_seat_check=bus_no_blocked+"check";
        //     Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
               //c.setAutoCommit(false);
            // Statement stmt = c.createStatement();

            // String exist_check = "SELECT EXISTS (SELECT table_name FROM information_schema.tables WHERE table_name = '"+bus_seat_check+"');";
            // ResultSet rs = stmt.executeQuery(exist_check);
            // String flag="false";
            // while(rs.next()){
            //     String x=rs.getString(1);
            //     System.out.println("c is: "+x);
            //     if(x.equals("t")){
            //         flag="true";
            //         bus_exist_db=1;
            //     }
            //     else{
            //         flag="false";
            //         bus_exist_db=0;
            //     }
            // }
            //String  table_exist="";
            // if(flag.equals("true")){
            //     flag="false";
            //     String sql = "select * from "+bus_seat_check+" where bus_no = '" + bus_no_blocked + "' and seat = '"+ seat_block+"';";
            //     System.out.println("--Bus exist--");
            //     ResultSet rs2 = stmt.executeQuery(sql);
            //     if(rs2.next()){
            //         System.out.println("Seat is already booked");   
            //         seat_block_flag="1";
            //     }
            //     else{
            //         System.out.println("Seat Booked first time");
            //         String sql2 = "INSERT INTO "+bus_seat_check+" (bus_no,seat) VALUES ('"+bus_no_blocked+"','"+seat_block+"');";
            //         stmt.executeUpdate(sql2);
            //     }
            // }
        //     else{
        //         System.out.println("table does not exist");
        //         System.out.println("----------------executed create statement----------------");
        //         System.out.println("bus_seat_check is: "+bus_seat_check);
        //         String sql3 = "CREATE TABLE "+bus_seat_check+" (bus_no text, seat text);";
        //         stmt.executeUpdate(sql3);
        //         System.out.println("----------------executed create statement----------------");
        //         String sql4 = "INSERT INTO "+bus_seat_check+" (bus_no,seat) VALUES ('"+bus_no_blocked+"','"+seat_block+"');";
        //         stmt.executeUpdate(sql4);
        //         // String sql3 = "CREATE TABLE '"+bus_seat_check+"' (bus_no text, seat text);";
        //         // stmt.executeUpdate(sql3);
        //         // String sql4 = "insert into '"+bus_seat_check+"' values ('" + bus_no_blocked + "', '" + seat_block + "');";
        //         // stmt.executeUpdate(sql4);
        //     }
        //     stmt.close();
        //     c.close();
        // }
        // catch(SQLException e){
        //     System.out.println("Error in connection!");
        //     System.out.println(e);
        // }
        
        // session.setAttribute("seat_block_flag", seat_block_flag);

        // String password = (String) session.getAttribute("password");
        // String Booking_possible=(String)session.getAttribute("Booking_possible"); 
        // String Booking_possible=Booking_poss;

        // System.out.println("Booking_possible is inside do get: "+Booking_possible);

        String Booking_possible_status=(String)session.getAttribute("Booking_possible_status");
        System.out.println("Booking_possible_status is inside do get: "+Booking_possible_status);


        // System.out.println("Booking_possible is: "+Booking_possible);
        // session.setAttribute("Booking_possible_flag", Booking_possible).toString();
        // String Booking_possible_flag=(String)session.getAttribute("Booking_possible_flag");

        PrintWriter out = response.getWriter();
        // JSONArray groups = new JSONArray();
        JSONObject seat = new JSONObject();
        seat.put("id", 1);
        String seat_allot="";
        // seat.put("seat_booked", seat_booked);
        if(Booking_possible_status.equals("true")){
            seat.put("seat_block", false);
            seat_allot="true";
        }
        else{
            seat.put("seat_block", true);
            seat_allot="false";
        }
        // singlegroup.put("password",password);
        // groups.add(singlegroup);
        // singlegroup = new JSONObject();
        // singlegroup.put("demo","varun");
        // groups.add(singlegroup);
        out.println(seat);
        
}
}

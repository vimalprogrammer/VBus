
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


public class Test_Ticket extends HttpServlet {


    public static TicketCounter tl=null;
    public static String str=null; 
    public static void setM(TicketCounter tc){
        str=(String)tc.Booking_possible;
        System.out.println("Test_Ticket :"+str); 
    }


    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException  {    
        HttpSession session = request.getSession();

        String seat_booked = request.getParameter("bus_booked");
        session.setAttribute("seat_booked", seat_booked);
        
        String name=session.getAttribute("username").toString();
        String bus_no= (String) session.getAttribute("bus_no");

        TicketCounter ticketCounter = new TicketCounter();
        TicketBookingThread t1 = new TicketBookingThread(ticketCounter,name,seat_booked,bus_no);

        t1.start();
        try{
            t1.join();
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println("----Below code called----");

        PrintWriter out = response.getWriter();
        JSONObject seat = new JSONObject();
        seat.put("id", 1);
        seat.put("res", str);
        out.println(seat);
    }
}
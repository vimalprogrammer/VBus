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
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {    

        System.out.println("Invoice Called.....");
        HttpSession session = request.getSession();
        String Booking_possible_status=(String)session.getAttribute("Booking_possible_status");
        System.out.println("Booking_possible_status is inside do get: "+Booking_possible_status);
        PrintWriter out = response.getWriter();
        JSONObject seat = new JSONObject();
        seat.put("id", 1);
        String seat_allot="";
        if(Booking_possible_status.equals("true")){
            seat.put("seat_block", false);
            seat_allot="true";
        }
        else{
            seat.put("seat_block", true);
            seat_allot="false";
        }
        out.println(seat);
    }
}

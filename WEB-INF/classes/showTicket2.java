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


public class showTicket2 extends HttpServlet {

    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {    
        System.out.println("showTicket2 called fine");
      
        HttpSession session = request.getSession();
        String bus_no = (String) session.getAttribute("bus_no");
        if(bus_no.contains("b"))
        {
            bus_no=bus_no;
        }
        else
        {
            bus_no="b"+bus_no;
        }
        session.setAttribute("bus_no", bus_no);
        PrintWriter out = response.getWriter();
        JSONArray groups = new JSONArray();
        int jsonid=0;

        try
        {
            Class.forName("org.postgresql.Driver");
            System.out.println("Opened database successfully - 1");
        }
        
        catch(ClassNotFoundException e){
            System.out.println("Class not found "+e);
            System.out.println("Error in loading driver");
        }

        try{
            Connection c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM "+bus_no+" order by id desc limit 1");
            while ( rs.next() ) {
                String seat_cnt=rs.getString("seat_no");
            }
            rs.close();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM "+bus_no+" order by id asc;");
            while(rs1.next())
            {
                String seats = rs1.getString("seat_no");
                int seat_no_layout = rs1.getInt("id");
                JSONObject group = new JSONObject();
                group.put("id", jsonid);
                group.put("seat_cnt", seats);
                String seat_no_layout_booked=seat_no_layout+"";
                if(seats.contains("B"))
                {
                    group.put("available", false);
                } 
                else
                {
                    group.put("available", true);
                }
                if(seat_no_layout%10==0)
                {
                    group.put("seat_no_layout", true);
                }
                else
                {
                    group.put("seat_no_layout", false);
                }

                if(seat_no_layout==20)
                {
                    group.put("bus_walk_place", true);
                }
                else
                {
                    group.put("bus_walk_place", false);
                }

                if(seat_no_layout>50)
                {
                    group.put("slepper50", true);
                    if((seat_no_layout==50 || seat_no_layout_booked.equals("B50")) || (seat_no_layout==53 || seat_no_layout_booked.equals("B53")))
                    {
                        group.put("sleeper_row", true);
                    }
                }
                else
                {
                    group.put("slepper50", false);
                }

                if(seat_no_layout>40 && seat_no_layout<50)
                {
                    group.put("sleeper40", true);
                    group.put("sleeper_exist", true);
                    if((seat_no_layout==41 || seat_no_layout_booked.equals("B41")) || (seat_no_layout==44 || seat_no_layout_booked.equals("B44")) ||
                    (seat_no_layout==47 || seat_no_layout_booked.equals("B47")) || (seat_no_layout==51 || seat_no_layout_booked.equals("B51")))
                    {
                        group.put("sleeper_row", true);
                        System.out.println("SEAT LAYOUT - "+seat_no_layout + "TRUE");
                    }
                    else
                    {
                        group.put("sleeper_row", false);
                    }
                }
                else
                {
                    group.put("sleeper40", false);
                }

                groups.add(group);
                jsonid++;
            }
            rs1.close();
            stmt.close();
            c.close();
        }catch(SQLException e){
            System.out.println("SQL exception occured" + e);
        }
    out.println(groups);
}
}

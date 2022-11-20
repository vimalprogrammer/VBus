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


public class add_bus extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);

                String bus_number=String.valueOf(request.getParameter("bus_number"));
                String bus_name=String.valueOf(request.getParameter("bus_name"));
                String ac=String.valueOf(request.getParameter("ac"));
                String sleeper=String.valueOf(request.getParameter("sleeper"));
                String dep=String.valueOf(request.getParameter("dep"));
                String des=String.valueOf(request.getParameter("des"));
                String price=String.valueOf(request.getParameter("price"));
                String layout=String.valueOf(request.getParameter("layout"));
                String last_row=String.valueOf(request.getParameter("last_row"));
                String lower_berth=String.valueOf(request.getParameter("lower_berth"));
                String upper_berth=String.valueOf(request.getParameter("upper_berth"));

                System.out.println(bus_number);
                System.out.println(bus_name);
                System.out.println(ac);
                System.out.println(sleeper);
                System.out.println(dep);
                System.out.println(des);
                System.out.println(price);
                System.out.println(layout);
                System.out.println(lower_berth);
                System.out.println(upper_berth);

                String seats="";

                if(layout.equals("2X2") && last_row.equals("last_row_enable") &&upper_berth.equals("upper_berth_enable"))
                    seats="51";

                else if(layout.equals("2X2") && last_row.equals("last_row_enable"))
                    seats="45";

                else if(layout.equals("2X2") && upper_berth.equals("upper_berth_enable"))
                    seats="46";
            
                else if(layout.equals("2X2"))
                    seats="40";
                
                if(layout.equals("2X3") && last_row.equals("last_row_enable") &&upper_berth.equals("upper_berth_enable"))
                    seats="61";

                else if(layout.equals("2X3") && last_row.equals("last_row_enable"))
                    seats="55";

                else if(layout.equals("2X3") && upper_berth.equals("upper_berth_enable"))
                    seats="56";
            
                else if(layout.equals("2X3"))
                    seats="50";

                try{
                    Connection c=ConnectionDB.getConnection();
                    Statement stmt = c.createStatement();

                    String sql = "INSERT INTO Bus_Details (BUS_ID,BUS_NAME,TOTAL_SEATS,AC_or_NON_AC,DEPARTURE,DESTINATION, PRICE, AC) "
                    + "VALUES ('"+bus_number+"','"+bus_name+"','"+seats+"','"+sleeper+"','"+dep+"','"+des+"','"+price+"','"+ac+"');";
                
                  stmt.executeUpdate(sql);

                  String s="b"+bus_number;

                  String sql2="create table "+s+" (id serial primary key,"+" seat_no text not null)";
                  stmt.executeUpdate(sql2);

                  String sql3="insert into "+s+" (seat_no) select i from generate_series(1,'"+seats+"') i";
                  stmt.executeUpdate(sql3);
                  stmt.close();
                  c.commit();
                  c.close();
                }
                catch (SQLException e) {
                    System.out.println("SQL exception occured" + e);
                }

                JSONObject add_bus = new JSONObject();
                add_bus.put("id", 1);
                add_bus.put("bus_number", bus_number);
                add_bus.put("bus_name", bus_name);
                add_bus.put("ac", ac);
                add_bus.put("sleeper", sleeper);
                add_bus.put("dep", dep);
                add_bus.put("des", des);
                add_bus.put("price", price);
                add_bus.put("layout", layout);
                add_bus.put("lower_berth", lower_berth);
                add_bus.put("upper_berth", upper_berth);
                out.println(add_bus);
                    
            }
}


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
        //System.out.println(bus_no);
        session.setAttribute("bus_no", bus_no);
        // String password = (String) session.getAttribute("password");
        PrintWriter out = response.getWriter();
        // JSONArray groups = new JSONArray();
        // JSONArray groups = new JSONArray();

        JSONArray groups = new JSONArray();
        int jsonid=0;

        // group.put("id", 1);
        // group.put("seat_cnt", bus_no);


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
            ResultSet rs = stmt.executeQuery( "SELECT * FROM "+bus_no+" order by id desc limit 1");
            while ( rs.next() ) {
                // int id = rs.getInt("id");
                String seat_cnt=rs.getString("seat_no");
                //System.out.println(seat_cnt);
                // JSONObject group = new JSONObject();
                // group.put("id", 1);
                // group.put("seat_cnt", seat_cnt);
                // groups.add(group);
            }
            rs.close();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM "+bus_no+" order by id asc;");
            while(rs1.next())
            {
                String seats = rs1.getString("seat_no");
                int seat_no_layout = rs1.getInt("id");
                //System.out.println(seats);
                JSONObject group = new JSONObject();
                group.put("id", jsonid);
                group.put("seat_cnt", seats);
                if(seats.contains("B"))
                {
                    group.put("available", false);
                } 
                else
                {
                    group.put("available", true);
                }
                //System.out.println("Seat no layout : "+seat_no_layout);

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
                    //System.out.println("slepper50 In");
                    String seat_no_layout_booked=seat_no_layout+"";
                    if((seat_no_layout==51 || seat_no_layout_booked.equals("B51")) || (seat_no_layout==54 || seat_no_layout_booked.equals("B54")))
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
                    //System.out.println("Sleeper 40 In");
                    String seat_no_layout_booked=seat_no_layout+"";
                    if((seat_no_layout==41 || seat_no_layout_booked.equals("B41")) || (seat_no_layout==44 || seat_no_layout_booked.equals("B44")) ||
                    (seat_no_layout==47 || seat_no_layout_booked.equals("B47")) || (seat_no_layout==51 || seat_no_layout_booked.equals("B51")))
                    {
                        group.put("sleeper_row", true);
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

// try{
//     Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
//     //c.setAutoCommit(false);
//     Statement stmt = c.createStatement();
//     ResultSet rs = stmt.executeQuery( "SELECT * FROM "+bus_no+" order by id asc;" );
//     String seat_cnt="select id from "+bus_no+" order by id desc limit 1;";

//     ResultSet rs2=stmt.executeQuery(seat_cnt);
//     rs2.next();
//     // System.out.println("seat_cnt: "+seat_cnt);
//     String seat_cnt1=rs2.getString("id");

//     System.out.println("seat_cnt: "+seat_cnt1);

    // group.put("id", 1);
    // group.put("seat_cnt", seat_cnt1);


    // group.put("id", 1);
    // group.put("seat_cnt", seat_cnt1);
    // groups.add(group);

    // out.println("<form action='book_tkt.jsp' >");

    // out.println("<br><br><br>");
    // out.println("<h3>Click bus Id to View Tickets</h3>");
    
    // if(rs.next())
    // {
    //  do
    //  {
    //     //out.println("AvailTKt in");
    //     //debugging purpose
    //      String bus_no = rs.getString("bus_id");
    //      String bus_name = rs.getString("bus_name");
    //      String seats = rs.getString("total_seats");
    //      String sleeper = rs.getString("ac_or_non_ac");
    //      String dep = rs.getString("departure");
    //      String des = rs.getString("destination");
    //      String price = rs.getString("price");
    //      String ac = rs.getString("ac");

    //      JSONObject singlegroup = new JSONObject();
    //      // out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2)+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" +rs.getString(5) +"</td><td>"+rs.getString(6)+"</td></tr>");
    //      singlegroup.put("id", jsonid);
    //      singlegroup.put("bus_no", rs.getString("bus_id"));
    //      singlegroup.put("bus_name", rs.getString("bus_name"));
    //      singlegroup.put("seats", rs.getString("total_seats"));
    //      singlegroup.put("sleeper", rs.getString("ac_or_non_ac"));
    //      singlegroup.put("dep", rs.getString("departure"));
    //      singlegroup.put("des", rs.getString("destination"));
    //      singlegroup.put("price", rs.getString("price"));
    //      singlegroup.put("ac", rs.getString("ac"));
    //      groups.add(singlegroup);
    //      jsonid=jsonid+1;
                
    //     // out.println("<tr><td><input type=\"submit\" name=\"bus_no\" value="+bus_no+"><td>" + bus_name + "</td><td>" + seats + "</td><td>" + sleeper + "</td><td>" + dep + "</td><td>" + des+"</td><td>"+price+"</td><td>"+ac+"</td></tr>");

    //     // System.out.println("---------------------------------------------------");
    //     // System.out.println("Bus No: "+bus_no);
    //     // System.out.println("Bus Name: "+bus_name);
    //     // System.out.println("Seats: "+seats);
    //     // System.out.println("Sleeper: "+sleeper);
    //     // System.out.println("Departure: "+dep);
    //     // System.out.println("Destination: "+des);
    //     // System.out.println("Price: "+price);
    //     // System.out.println("AC: "+ac);
    //     // System.out.println("---------------------------------------------------");
    
    //  } while(rs.next());
    
    // //  out.println("</table></form>");
    
    //  //stmt.executeUpdate(sql);
    // //stmt.executeUpdate(sql);
    // stmt.close();
    // c.close();
    // // else
    // // {
    // //        out.println("<script type=\"text/javascript\">");
    // //        out.println("alert('No bus available with these spots!');");
    // //        out.println("location='book_ticket1.jsp';");
    // //        out.println("</script>");
    // // }
    // }            
    // catch(SQLException e)
    // {
    //     out.println("Error Caused\n "+e);        
    //     e.printStackTrace();
    // }
    // out.println(groups);
// }
// catch(SQLException e)
// {
//     out.println("Error Caused\n "+e);        
//     e.printStackTrace();
// }

    out.println(groups);
}
}

import java.sql.SQLException;
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


public class TicketCounter extends HttpServlet {

    public String Booking_possible=null;

	public synchronized void bookTicket(String pname, String seat_booked, String bus_no) {

		try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("Class not found "+e);
        }

		try{
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
			//c.setAutoCommit(false);
			Statement stmt = c.createStatement();
		//---------------------------------------------------------------------------------

			String sql="select * from "+bus_no+" where id='"+seat_booked+"'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String seat_checking = rs.getString("seat_no");
			if(seat_checking.contains("B")){
				Booking_possible = "false";
			}
			else{
				Booking_possible = "true";
				String booked_seat = "B"+seat_booked;
				String sql1 = "Update "+bus_no+" set seat_no='"+booked_seat+"' where id='"+seat_booked+"'; ";
				stmt.executeUpdate(sql1);	
				// seat_block_flag="1";
			}

		//-----------------------------------------------------------------------------------

			stmt.close();
			c.close();
		
		}catch(SQLException e){
			System.out.println("Error in connection "+e);
		}

		// System.out.println("---Ticket Counter Called----");

		System.out.println("Passenger Name: " + pname);
		System.out.println("Seat Booked: " + seat_booked);
		System.out.println("Bus No: " + bus_no);

		// JSONObject seat = new JSONObject();
		// if (Booking_possible.equals("false")) {
		// 	seat.put("id", 1);
		// 	seat.put("seat_block_flag", true);
		// } else
		// 	seat.put("seat_block_flag", false);

		//Accessing in the next page

		// System.out.println(seat);
		// invoice test=new invoice();
		// test.setinfo(Booking_possible);

		
	}

	//------------------------------------------------

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {    
	System.out.println("Ticket Counter Called");
	HttpSession session = request.getSession();
	PrintWriter out = response.getWriter();
	// JSONArray groups = new JSONArray();

	String Booking_possible_status= Booking_possible;
	System.out.println("Booking_possible_status : "+Booking_possible_status);

	session.setAttribute("Booking_possible_status", Booking_possible_status);
	out.println("Booking Possiblity: "+ Booking_possible_status);

	// JSONObject seat = new JSONObject();
	// if (Booking_possible.equals("true")) {
	// 	seat.put("id", 1);
	// 	seat.put("seat_block_flag", true);
	// 	seat.put("Available","yes" );

	// } else
	// {
	// 	seat.put("seat_block_flag", false);
	// 	seat.put("Not Available","yes" );
	// }

	// System.out.println(Booking_possible);
	// System.out.println(seat);
	// out.println(seat);

	}
}
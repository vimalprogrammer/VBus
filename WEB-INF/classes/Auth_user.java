import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import com.google.zxing.WriterException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Auth_user extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{


		HttpSession session = req.getSession();

		String user=String.valueOf(session.getAttribute("username"));
		String pass=String.valueOf(session.getAttribute("password"));

		System.out.println("Auth_user: "+user);
		System.out.println("Auth_pass: "+pass);

		String qr="";
        Statement stmt = null;
		try{
			
		  Connection c=ConnectionDB.getConnection();
	      stmt = c.createStatement();
		  ResultSet rs = stmt.executeQuery( "SELECT * FROM user_details where first_name='"+user+"'");
	 
		  rs.next();
		  String sec_key=rs.getString("secret_key");

		 if(sec_key==null)
		 {
			String key = Code.generateSecretKey();

		     String sql = "UPDATE user_details SET secret_key = '"+key+"' where first_name='"+user+"';";
		     stmt.executeUpdate(sql);
		     stmt.close();
		     c.close();

	         String name=String.valueOf(session.getAttribute("username"));
	         String email=String.valueOf(session.getAttribute("password"));
	         session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);
			
			qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			session.setAttribute("qr",qr);
			Code.createQRCode(barCodeUrl, "qr", 400, 400);
			String code = Code.getTOTPCode(key);

			session.setAttribute("code", code);	
		}
		else
		{
		    String key = rs.getString("secret_key");
		    stmt.close();
		    c.close();
	        String name=String.valueOf(session.getAttribute("username"));
	        String email=String.valueOf(session.getAttribute("password"));

	        session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);			
			qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			session.setAttribute("qr",qr);
			Code.createQRCode(barCodeUrl, "qr", 400, 400);
			String code = Code.getTOTPCode(key);
			session.setAttribute("code", code);			
		}
	}
catch(Exception e){
	System.out.println("Exception is : "+ e);
}

	String qr1=String.valueOf(session.getAttribute("qr"));
	String code1=String.valueOf(session.getAttribute("code"));
	PrintWriter out = res.getWriter();
	JSONArray groups = new JSONArray();
	JSONObject qrcode = new JSONObject();
	qrcode.put("id", 1);
	qrcode.put("qr", qr1);
	qrcode.put("code", code1);
	groups.add(qrcode);
	out.println(groups); 
}
}
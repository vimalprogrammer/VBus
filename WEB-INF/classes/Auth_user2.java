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

public class Auth_user2 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{


		HttpSession session = req.getSession();

		String ad_user=String.valueOf(session.getAttribute("ad_username"));
		String ad_pass=String.valueOf(session.getAttribute("ad_password"));

		System.out.println("Auth_user: "+ad_user);
		System.out.println("Auth_pass: "+ad_pass);

		String ad_qr="";
        Statement stmt = null;
	try{
		 Connection c=ConnectionDB.getConnection();
	     stmt = c.createStatement();
		 ResultSet rs = stmt.executeQuery( "SELECT * FROM admin_details where admin_name='"+ad_user+"'");
	 
		 rs.next();
		  String sec_key=rs.getString("secret_key");

		 if(sec_key==null)
		 {
			String key = Code.generateSecretKey();

		     String sql = "UPDATE admin_details SET secret_key = '"+key+"' where first_name='"+ad_user+"';";
		     stmt.executeUpdate(sql);
		     stmt.close();
		     c.close();

	         String name=String.valueOf(session.getAttribute("ad_username"));
	         String email=String.valueOf(session.getAttribute("ad_password"));
	         session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);
			ad_qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			session.setAttribute("ad_qr",ad_qr);
			Code.createQRCode(barCodeUrl, "ad_qr", 400, 400);
			String ad_code = Code.getTOTPCode(key);

			session.setAttribute("ad_code", ad_code);
			
		}
		else
		{
		    String key = rs.getString("secret_key");
		    stmt.close();
		    c.close();

	        String name=String.valueOf(session.getAttribute("ad_username"));
	        String email=String.valueOf(session.getAttribute("ad_password"));

	        session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);

			ad_qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			session.setAttribute("ad_qr",ad_qr);
			Code.createQRCode(barCodeUrl, "ad_qr", 400, 400);
			String ad_code = Code.getTOTPCode(key);

			session.setAttribute("ad_code", ad_code);			
		}
	}
catch(Exception e){
	System.out.println("Exception is : "+ e);
}

	String qr2=String.valueOf(session.getAttribute("ad_qr"));
	String code2=String.valueOf(session.getAttribute("ad_code"));

	PrintWriter out = res.getWriter();
	JSONArray groups = new JSONArray();
	JSONObject qrcode = new JSONObject();
	qrcode.put("id", 1);
	qrcode.put("ad_qr", qr2);
	qrcode.put("ad_code", code2);
	groups.add(qrcode);
	out.println(groups); 
	}
}
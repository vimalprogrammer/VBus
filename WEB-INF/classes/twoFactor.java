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

public class twoFactor extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{


		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		String username = (String) session.getAttribute("username");

		String qr="";
        Statement stmt = null;

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

	      stmt = c.createStatement();

			String key = Code.generateSecretKey();

			System.out.println("Key is "+key);

			String sql2 = "UPDATE user_details SET secret_key = '"+key+"' where email='"+email+"';";
			stmt.executeUpdate(sql2);
			stmt.close();
			c.close();

	        session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, username);

            username=username+"@vbus.com";

			System.out.println("Bar code url is "+barCodeUrl);
			System.out.println("username is "+username);
			
			qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+username+"?secret="+key+"&chs=160x160&chld=L|0";
			session.setAttribute("qr",qr);
			Code.createQRCode(barCodeUrl, "qr", 400, 400);
			String code = Code.getTOTPCode(key);

			session.setAttribute("code", code);	
			System.out.println("code is "+code);
		}
catch(Exception e){
	System.out.println("Exception(ERROR) is : "+ e);
}

	String qr1=String.valueOf(session.getAttribute("qr"));
	String code1=String.valueOf(session.getAttribute("code"));
	PrintWriter out = res.getWriter();
	JSONArray groups = new JSONArray();
	JSONObject qrcode = new JSONObject();
	qrcode.put("id", 1);
	qrcode.put("qr", qr1);
	qrcode.put("code", code1);
	qrcode.put("email", email);
	qrcode.put("username", username);
	groups.add(qrcode);
	out.println(groups); 
}
}
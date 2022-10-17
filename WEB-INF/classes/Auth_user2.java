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
        Connection c = null;
        Statement stmt = null;
		try
		{
	     Class.forName("org.postgresql.Driver");
	     c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
	     //c.setAutoCommit(false);
	     stmt = c.createStatement();
		 ResultSet rs = stmt.executeQuery( "SELECT * FROM admin_details where admin_name='"+ad_user+"'");
	 
		 // String sec_key="";
		 // if(rs.next())
		 // {
		 rs.next();
		  String sec_key=rs.getString("secret_key");
		 //}

		 if(sec_key==null)
		 {
			String key = Code.generateSecretKey();

		     String sql = "UPDATE admin_details SET secret_key = '"+key+"' where first_name='"+ad_user+"';";
		     stmt.executeUpdate(sql);
		     //out.println("<br><br><br><center>User Registered Successfully!!!</center><br><br>");
		     stmt.close();
		     c.close();

			//String key="CUPPS2FZTRSDZBOCXT4MA7PRT6HXEL65";

	         String name=String.valueOf(session.getAttribute("ad_username"));
	         String email=String.valueOf(session.getAttribute("ad_password"));
	         session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);

			//Code.createQRCode(barCodeUrl, "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Demo1-Debug-v3\\QRCode.png", 400, 400);
			//otpauth://totp/tcs%3Avars?secret=QDWSM3OYIPGTEVSPB5FKVDM3CSNCWHOP&issuer=tcs
			//https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example&chs=160x160&chld=L|0
			
			//https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/vimal?secret=JBSWY3DPEHPK3PXP&chs=160x160&chld=L|0
			ad_qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			//String qr="https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl="key"&choe=UTF-8";
			session.setAttribute("ad_qr",ad_qr);
			Code.createQRCode(barCodeUrl, "ad_qr", 400, 400);
			//catch(Exception e){p.println("Exception : "+e);}
			String ad_code = Code.getTOTPCode(key);

			session.setAttribute("ad_code", ad_code);
			
			//req.getRequestDispatcher("code.jsp").forward(req, res);
		}
		else
		{
		    String key = rs.getString("secret_key");
		    //out.println("<br><br><br><center>User Registered Successfully!!!</center><br><br>");
		    stmt.close();
		    c.close();

			//String key="CUPPS2FZTRSDZBOCXT4MA7PRT6HXEL65";

	        String name=String.valueOf(session.getAttribute("ad_username"));
	        String email=String.valueOf(session.getAttribute("ad_password"));

	        session.setAttribute("sec_key",key);
			
			String barCodeUrl = Code.getGoogleAuthenticatorBarCode(key, email, name);

			//Code.createQRCode(barCodeUrl, "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Demo1-Debug-v3\\QRCode.png", 400, 400);
			//otpauth://totp/tcs%3Avars?secret=QDWSM3OYIPGTEVSPB5FKVDM3CSNCWHOP&issuer=tcs
			//https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example&chs=160x160&chld=L|0
			
			//https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/vimal?secret=JBSWY3DPEHPK3PXP&chs=160x160&chld=L|0
			ad_qr="https://chart.googleapis.com/chart?cht=qr&chl=otpauth://totp/"+name+"?secret="+key+"&chs=160x160&chld=L|0";
			//String qr="https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl="key"&choe=UTF-8";
			session.setAttribute("ad_qr",ad_qr);
			Code.createQRCode(barCodeUrl, "ad_qr", 400, 400);
			//catch(Exception e){p.println("Exception : "+e);}
			String ad_code = Code.getTOTPCode(key);

			session.setAttribute("ad_code", ad_code);
			
			//req.getRequestDispatcher("code.jsp").forward(req, res);
			
		}
	}
catch(Exception e){
	System.out.println("Exception is : "+ e);
}

String qr2=String.valueOf(session.getAttribute("ad_qr"));
String code2=String.valueOf(session.getAttribute("ad_code"));

// System.out.println(qr);
// String password = (String) session.getAttribute("password");
PrintWriter out = res.getWriter();
JSONArray groups = new JSONArray();
JSONObject qrcode = new JSONObject();
qrcode.put("id", 1);
qrcode.put("ad_qr", qr2);
qrcode.put("ad_code", code2);
// singlegroup.put("password",password);
// groups.add(singlegroup);
// singlegroup = new JSONObject();
// singlegroup.put("demo","varun");
groups.add(qrcode);
out.println(groups); 
}
}
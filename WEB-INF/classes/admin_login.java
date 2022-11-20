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


public class admin_login extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);

                String res="";
                String ad_username = request.getParameter("ad_name");
                String ad_password = request.getParameter("ad_pass");
                System.out.println(ad_username);
                System.out.println(ad_password);

            try{
                Connection c=ConnectionDB.getConnection();

                Statement stmt = c.createStatement();
                System.out.println("Opened database successfully - 2");
                ResultSet rs = stmt.executeQuery("SELECT * FROM admin_details where admin_name='"+ad_username+"' and admin_password='"+ad_password+"';");
                if(rs.next())
                {
                    session.setAttribute("ad_username",ad_username);
                    session.setAttribute("ad_password",ad_password);
                    System.out.println("Login Success");
                    res="1";
                }
                else
                {
                    System.out.println("Login Failed");
                    res="0";
                }
                rs.close();
                stmt.close();
                c.close();
            }catch(SQLException e){
                System.out.println("Opened database successfully-catch");
                System.out.println(e);
            }
            out.println(res);

        }
}

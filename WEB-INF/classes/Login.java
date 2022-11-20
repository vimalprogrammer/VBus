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


public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);
                String res="";
                String username = request.getParameter("name");
                String password = request.getParameter("password");
                System.out.println(username);
                System.out.println(password);
            try{
                Connection c=ConnectionDB.getConnection();
                Statement stmt = c.createStatement();
                System.out.println("Opened database successfully - 2");
                ResultSet rs = stmt.executeQuery("SELECT * FROM user_details where first_name='"+username+"' and password='"+password+"';");
                if(rs.next())
                {
                    session.setAttribute("username",username);
                    session.setAttribute("password",password);
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

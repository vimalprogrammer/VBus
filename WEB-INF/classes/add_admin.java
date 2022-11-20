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


public class add_admin extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession(true);

                String new_admin=request.getParameter("new_admin");
                String new_pass=request.getParameter("new_pass");
                
                System.out.println(new_admin);
                System.out.println(new_pass);
                
                try{
                    Connection c=ConnectionDB.getConnection();
                    Statement stmt = c.createStatement();

                    String sql = "INSERT INTO Admin_Details (ADMIN_NAME, ADMIN_PASSWORD) "
                                     + "VALUES ('"+new_admin+"','"+new_pass+"');";

                    stmt.executeUpdate(sql);

                    stmt.close();
                    c.close();
                    }catch(SQLException e){
                        System.out.println("Opened database successfully-catch");
                        System.out.println(e);
                    }

                    JSONObject user = new JSONObject();
                    user.put("id", 1);
                    user.put("new_admin", new_admin);
                    user.put("new_pass", new_pass);
                    out.println(user);        
            }
}


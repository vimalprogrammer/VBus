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
                //inialize session
                HttpSession session = request.getSession(true);

                String new_admin=request.getParameter("new_admin");
                String new_pass=request.getParameter("new_pass");
                
                System.out.println(new_admin);
                System.out.println(new_pass);
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

                    //ResultSet rs;
                    String sql = "INSERT INTO Admin_Details (ADMIN_NAME, ADMIN_PASSWORD) "
                                     + "VALUES ('"+new_admin+"','"+new_pass+"');";

                    stmt.executeUpdate(sql);

                    stmt.close();
                    c.close();
                    }catch(SQLException e){
                        // System.out.println("Error in connection");
                        System.out.println("Opened database successfully-catch");
                        System.out.println(e);
                    }

                    // JSONArray groups = new JSONArray();
                    JSONObject user = new JSONObject();
                    user.put("id", 1);
                    user.put("new_admin", new_admin);
                    user.put("new_pass", new_pass);
                    // singlegroup.put("password",password);
                    // groups.add(singlegroup);
                    // singlegroup = new JSONObject();
                    // singlegroup.put("demo","varun");
                    // groups.add(singlegroup);
                    out.println(user);        
            }
}


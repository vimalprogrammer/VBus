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


public class user_register extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                //inialize session
                HttpSession session = request.getSession(true);

                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String email=request.getParameter("email");
                String ph=request.getParameter("ph");
                String gender=request.getParameter("gender");
                String password=request.getParameter("password");
                String confirm_password=request.getParameter("confirm_password");

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

                    System.out.println("Insert user details");
                    String sql = "INSERT INTO User_Details (FIRST_NAME,LAST_NAME,EMAIL,PHONE,GENDER,PASSWORD) "
                    + "VALUES ('"+fname+"','"+lname+"','"+email+"','"+ph+"','"+gender+"','"+password+"');";
   
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
                    user.put("fname", fname);
                    user.put("lname", lname);
                    user.put("email", email);
                    user.put("ph", ph);
                    user.put("gender", gender);
                    user.put("password", password);
                    user.put("confirm_password", confirm_password);
                    // singlegroup.put("password",password);
                    // groups.add(singlegroup);
                    // singlegroup = new JSONObject();
                    // singlegroup.put("demo","varun");
                    // groups.add(singlegroup);
                    out.println(user);        
            }
}


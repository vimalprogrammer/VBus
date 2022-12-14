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
                HttpSession session = request.getSession(true);

                String fname=request.getParameter("fname");
                String lname=request.getParameter("lname");
                String email=request.getParameter("email");
                String ph=request.getParameter("ph");
                String gender=request.getParameter("gender");
                String password=request.getParameter("password");
                String confirm_password=request.getParameter("confirm_password");

                System.out.println("|||||||||||||||||||||||||||||||||||||||");
                System.out.println(fname);
                System.out.println(lname);
                System.out.println(email);
                System.out.println(ph);
                System.out.println(gender);
                System.out.println(password);
                System.out.println("|||||||||||||||||||||||||||||||||||||||");

                session.setAttribute("username",fname);
                session.setAttribute("email",email);

                try
                {
                    Class.forName("org.postgresql.Driver");
                    System.out.println("Opened database successfully - 1");
                }
                
                catch(ClassNotFoundException e){
                    System.out.println("Class not found "+e);
                    System.out.println("Error in loading driver");
                }

                Connection con = null;

                try{
                    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");

                    Statement stmt = con.createStatement();

                    System.out.println("Insert user details");
                    String sql = "INSERT INTO User_Details (FIRST_NAME,LAST_NAME,EMAIL,PHONE,GENDER,PASSWORD) "
                    + "VALUES ('"+fname+"','"+lname+"','"+email+"','"+ph+"','"+gender+"','"+password+"');";
   
                    stmt.executeUpdate(sql);

                    stmt.close();
                    con.close();
                    }catch(SQLException e){
                        System.out.println("Opened database successfully-catch");
                        System.out.println(e);
                    }

                    JSONObject user = new JSONObject();
                    user.put("id", 1);
                    user.put("fname", fname);
                    user.put("lname", lname);
                    user.put("email", email);
                    user.put("ph", ph);
                    user.put("gender", gender);
                    user.put("password", password);
                    user.put("confirm_password", confirm_password);
                    out.println(user);        
            }
}


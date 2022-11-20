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


public class GoogleSignin extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {  

        System.out.println("google sign in Called");
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String email = request.getParameter("email");

        session.setAttribute("username", username);
        session.setAttribute("email", email);

        try{
            Connection c=ConnectionDB.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rsG = stmt.executeQuery("SELECT * FROM user_details where email='"+email+"';");
            int accountExist=0;
            while(rsG.next()){
                accountExist=1;
            }
            if(accountExist==0){
                String sql = "INSERT INTO user_details (first_name,email) "+ "VALUES ('"+username+"','"+email+"');";
                stmt.executeUpdate(sql);
            }
            String google_sign_in = "true";
            session.setAttribute("google_sign_in",google_sign_in); 
            rsG.close();
            stmt.close();
            c.close();
                //}
            }
            catch(SQLException e){
                    System.out.println("Error in connection V4 "+e);
            }       
}
}
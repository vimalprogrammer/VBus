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


public class getUser extends HttpServlet {
    public static String google_email=null;
    public static String google_name=null;
    public static String google_access_token=null;

    public void setemail(String email){
        this.google_email=email;
        System.out.println("email set to "+google_email);
    }
    public void setname(String name){
        this.google_name=name;
        System.out.println("email set to "+google_name);
    }

    public void setaccess_token(String access_token){
        this.google_access_token=access_token;
        System.out.println("access_token set to "+access_token);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {    
        System.out.println("get user Called");
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String email="";
        String access_token="";

        if(username==null || password==null)
        {
            try{
                Class.forName("org.postgresql.Driver");
                System.out.println("Opened database successfully - username null check");
            }catch(ClassNotFoundException e){
                System.out.println("Class not found "+e);
                System.out.println("Error in loading driver");
            }
            try{
                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
                //c.setAutoCommit(false);
                Statement stmt = c.createStatement();
                
                    // ResultSet rsG2 = stmt.executeQuery("SELECT * FROM signInhandle;");
    
    
                    // while ( rsG2.next() ) {
                    //     username = rsG2.getString("name");
                    //     email = rsG2.getString("email");
                    //     access_token = rsG2.getString("access_token");
                    //     System.out.println("google : " +username);
                    //     System.out.println("email : " +email);
                    //     System.out.println("access_token : " +access_token);
                    //     // String  phone = rs.getString("phone");
                    //     session.setAttribute("username", username);
                    //     session.setAttribute("email", email);
                    //     session.setAttribute("access_token", access_token);
                    // }
                    
                    // rsG2.close();

                    // stmt.executeUpdate("TRUNCATE TABLE signInhandle;");

                    
                    // String google_email=session.getAttribute("google_email").toString();
                    // String google_name=session.getAttribute("google_name").toString();

                    // LoginModuleVbus loginModuleVbus2 = new LoginModuleVbus();
                    // LoginModuleVbus loginModuleVbus= loginModuleVbus2.getLoginModuleVbus();
                    // google_email=loginModuleVbus.email;
                    // google_name=loginModuleVbus.gname;
                    System.out.println("google_email : " +google_email);
                    System.out.println("google_name : " +google_name);

                    String google_sign_in = "true";
                    session.setAttribute("google_sign_in",google_sign_in); 

                    session.setAttribute("username", google_name);
                    session.setAttribute("email", google_email);

                    username = google_name;
                    email = google_email;
                    access_token = google_access_token;
                    session.setAttribute("google_access_token", access_token);

                    ResultSet rsG = stmt.executeQuery("SELECT * FROM user_details where email='"+google_email+"';");
                    
                    int accountExist=0;
                    while(rsG.next())
                    {
                        accountExist=1;
                    }

                    if(accountExist==0)
                    {
                        String sql = "INSERT INTO user_details (first_name,email) "
                        + "VALUES ('"+username+"','"+email+"');";
                    
                        stmt.executeUpdate(sql);
                    }


                    rsG.close();
                    stmt.close();
                    c.close();
                //}
            }
            catch(SQLException e){
                    System.out.println("Error in connection V4 "+e);
            }
        }

    else
    {
        session.setAttribute("qrUser", username);
        session.setAttribute("qrPass", password);
        // String googleSignIn=(String) session.getAttribute("googleSignIn");
        // String googleSignInEmail=(String) session.getAttribute("googleSignInEmail");
        // String googleUsername = (String) session.getAttribute("googleUsername");

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
            
                ResultSet rs = stmt.executeQuery("SELECT * FROM user_details where first_name='"+username+"' and password='"+password+"';");


                while ( rs.next() ) {
                    String  first_name = rs.getString("first_name");
                    String  last_name = rs.getString("last_name");
                    email = rs.getString("email");
                    // String  phone = rs.getString("phone");
                    session.setAttribute("first_name", first_name);
                    session.setAttribute("last_name", last_name);
                    session.setAttribute("email", email);
                }
                rs.close();
                stmt.close();
                c.close();
                // request.getRequestDispatcher("/servlet_user").forward(request, response);

        }
        catch(SQLException e){
                System.out.println("Error in connection "+e);
        }
    }



        System.out.println(username);
        // String password = (String) session.getAttribute("password");
        PrintWriter out = response.getWriter();
        // JSONArray groups = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("id", 1);
        user.put("username", username);
        // singlegroup.put("password",password);
        // groups.add(singlegroup);
        // singlegroup = new JSONObject();
        // singlegroup.put("demo","varun");
        // groups.add(singlegroup);
        out.println(user);        
}
}
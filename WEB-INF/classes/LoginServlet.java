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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/callback")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("entering doGet");
        try {
            // get code
            String code=String.valueOf(request.getParameter("code"));
            response.setContentType("text/html");
            PrintWriter  out=response.getWriter();
            HttpSession session = request.getSession();
           //  out.println(request.getParameter("code"));
            out.println("Code is "+code+"<br>");
            System.out.println("Auth Code : "+code);

            // format parameters to post
            String urlParameters = "code="
                    + code
                    + "&client_id=816881978221-bjj2gg9r7gj8r4ddks56h6nionsqf70g.apps.googleusercontent.com"
                    + "&client_secret=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"
                    + "&redirect_uri=http://localhost:8080/V4/LoginServlet"
                    + "&grant_type=authorization_code";
            
            
            //post parameters
            URL url = new URL("https://accounts.google.com/o/oauth2/token");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();
            
            //get output in outputString 
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
            
            //get Access Token 
            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
            String access_token = json.get("access_token").getAsString();
            System.out.println("Access Token : "+access_token);
            session.setAttribute("access_token", access_token);
            //out.println("Access Token "+access_token);

            //get User Info 
            url = new URL(
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                            + access_token);
            urlConn = url.openConnection();
            outputString = "";
            reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
            //out.println(outputString);
            
            // Convert JSON response into Pojo class
            GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
            System.out.println(data);
            //out.println(data);
            String email = data.getEmail();
            String name = data.getName();
            session.setAttribute("google_email", email);
            session.setAttribute("google_name", name);
            out.println(name);
            out.println(email);

            InetAddress myIP=InetAddress.getLocalHost();
            out.println(myIP.getHostAddress());
            String ip=myIP.getHostAddress();

            //take values in state parameter in google oauth2 server response

            String state=String.valueOf(request.getParameter("state"));
            out.println("State is "+state+"<br>");
            System.out.println("State is "+state);
            
            
            // String newUrl="http://"+state+":8080/V2/success_user.jsp?name="+name+"&email="+email;

            //-------------------------------------------------------------------------
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
                String sql = "INSERT INTO signInhandle (name,email,access_token) VALUES ('"+name+"','"+email+"','"+access_token+"');";
                System.out.println("Values inserted succesfully");
                System.out.println(name);
                System.out.println(email);

                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            }catch(SQLException e){
                System.out.println("Error in connection V7 "+e);
            }
            //--------------------------------------------------------------------------------------    
            // String newUrl="http://"+state+":4200/user/Welcome";
        
            // response.sendRedirect(newUrl);

            //out.println(newUrl);
            //request.getRequestDispatcher("http://192.168.43.146:8080/V2/login2.jsp").forward(request, response);

            //response.sendRedirect(newUrl);
            writer.close();
            reader.close();            
        } catch (MalformedURLException e) {
            System.out.println( e);
        } catch (ProtocolException e) {
            System.out.println( e);
        } catch (IOException e) {
            System.out.println( e);
        }
        System.out.println("leaving doGet");
    }

}

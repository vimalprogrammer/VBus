import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class IsLoggedIn extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("Logged In");
        // request.getSession().setAttribute("loggedIN", "yes");
        out.println("1");
    }
}
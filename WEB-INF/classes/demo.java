import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.net.InetAddress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


public class demo extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("Servlet Called");
        PrintWriter out = response.getWriter();
        JSONArray groups = new JSONArray();
        JSONObject singlegroup = new JSONObject();
        singlegroup.put("id", 1);
        singlegroup.put("demo","vimal");
        groups.add(singlegroup);
        singlegroup = new JSONObject();
        singlegroup.put("id", 2);
        singlegroup.put("demo","vishnu");
        groups.add(singlegroup);
        out.println(groups);
}
}

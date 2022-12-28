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


public class qrcheck extends HttpServlet {

    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {    
        System.out.println("qrcheck Called");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String res="0";
        String qrcode = (String) session.getAttribute("code");
        System.out.println("QRCODE : "+qrcode);
        String otpcode = request.getParameter("otpcode");
        System.out.println("OTPCODE : "+otpcode);
        if(otpcode.equals(qrcode)){
            System.out.println("OTP and QR code matched");
            res="1";
        }
        out.println(res);
}
}
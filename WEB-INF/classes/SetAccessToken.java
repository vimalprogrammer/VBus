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


public class SetAccessToken extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String access_token = request.getParameter("access_token");
        request.getSession().setAttribute("access_token", access_token);
        System.out.println("Set Access token---" + access_token);
    }
}
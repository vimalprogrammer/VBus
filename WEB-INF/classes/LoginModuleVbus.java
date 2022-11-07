import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.security.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import java.net.*;
//import HttpClientHelper;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class LoginModuleVbus implements LoginModule {

	private CallbackHandler handler;
	private Subject subject;

	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;

	private String login;
	// private List<String> userGroups;
	// private String userrole = null;

	String email;
	String gname;
	String gaccess_token;

	// LoginModuleVbus loginModuleVbus=null;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		System.out.println("Login Module intiated! --- V-bus");

		handler = callbackHandler;
		this.subject = subject;

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Opened database successfully - 1");
        }catch(ClassNotFoundException e){
            System.out.println("Class not found "+e);
            System.out.println("Error in loading driver");
        }
	}

	@Override
	public boolean login() throws LoginException {

		System.out.println("Login Module login started! --- Vbus");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		Boolean flag = false;

		try {

			handler.handle(callbacks);
			String name = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1])
					.getPassword());

			System.out.println(name + "--- Vbus");
			System.out.println(password + "--- Vbus");

			if(password.length()>10)
			{
				String access_token=password;
				URL url = new URL(
						"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
								+ access_token);
				URLConnection urlConn = url.openConnection();
				urlConn = url.openConnection();
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(
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
				email = data.getEmail();
				gname = data.getName();
				gaccess_token = access_token;
				System.out.println(gname);
				System.out.println(email);
				// loginModuleVbus = new LoginModuleVbus();
				getUser get=new getUser();
				get.setemail(email);
				get.setname(gname);
				get.setaccess_token(gaccess_token);

				if(email.equals(name))
				{
					flag = true;
					login = name;
					return flag;
				}

			}
			else{
				try{
					Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
					//c.setAutoCommit(false);
					   Statement stmt = c.createStatement();
						System.out.println("Opened database successfully - 2");
						ResultSet rs = stmt.executeQuery("SELECT * FROM user_details where first_name='"+name+"' and password='"+password+"';");
						if(rs.next())
						{
							System.out.println("Login Success");
							flag = true;///g
							login = name;//g
							System.out.println("Login Module loginin success! --- Vbus");
						}
						rs.close();
						stmt.close();
						c.close();
						return flag;//g
					}catch(SQLException e){
						System.out.println("Opened database successfully-catch");
						System.out.println(e);
					}
			}

			throw new LoginException("Authentication failed");

		} catch (IOException e) {
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}
	}

	public String getId() {
        return email;
    }

	public String getName() {
		return gname;
	}

	@Override
	public boolean commit() throws LoginException {

		System.out.println("Login Module commit started! --- Vbus");

		userPrincipal = new UserPrincipal(login);
		userPrincipal.setName(login);
		subject.getPrincipals().add(userPrincipal);

		System.out.println("Login Module commit ended --- Vbus");
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		System.out.println("login module abort! --- Vbus");
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		// subject.getPrincipals().remove(rolePrincipal);
		System.out.println("login module logout! --- Vbus");
		return true;
	}
}
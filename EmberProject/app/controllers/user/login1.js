import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserLogin1Controller extends Controller {
  @service router;
  constructor() {
    super(...arguments);
    var now = this;
    $.ajax({
      url: "/V4/IsLoggedIn",
      method: "GET",
      data: {},
      success: function (response) {
        console.log(response);
        if (response == 1) {
          console.log("You can Access! Logged In!");
        } else if (response == 0) {
          now.router.transitionTo("user.login1");
        }
      },
    });
  }
  @action
  Login() {
    var username = document.getElementById("usr").value;
    var now = this;
    var password = document.getElementById("pwd").value;
    console.log(username);
    console.log(password);

    $.ajax({
      url: "/V4/j_security_check",
      method: "POST",
      data: { j_username: username, j_password: password },
      success: function (response) {
        console.log("J security SENTT");
        console.log(response);
        if (response == 2) {
          console.log("error page");
          // document.getElementById('worng-otp').innerHTML =
          //   'Wrong OTP,Try again!';
        } else {
          $.ajax({
            url: "/V4/IsLoggedIn",
          });

          $.ajax({
            url: "/V4/Login",
            method: "GET",
            data: { name: username, password: password },
            success: function (response) {
              console.log("ajax sent");
              console.log(response);
              var result = response;
            },
          });

          now.router.transitionTo("user.totp");
        }
      },
      error(xhr, status, error) {
        console.log("error");
      },
    });

    // $.ajax({
    //   url: "/V4/Login",
    //   method: "GET",
    //   data: { name: name, password: password },
    //   success: function (response) {
    //     console.log("ajax sent");
    //     console.log(response);
    //     var result = response;
    //     if (result == 1) {
    //       console.log("success");
    //       // t.router.transitionTo("user.Welcome");
    //       t.router.transitionTo("user.totp");
    //     }
    //   },
    // });
  }

  @action
  GetAccessToken() {
    console.log("Getting access token...");

    var client;
    var access_token;
    var now = this;

    client = google.accounts.oauth2.initTokenClient({
      client_id:
        "816881978221-bjj2gg9r7gj8r4ddks56h6nionsqf70g.apps.googleusercontent.com",
      scope:
        "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/calendar.readonly",
      client_secret: "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",

      callback: (tokenResponse) => {
        access_token = tokenResponse.access_token;
        console.log("Access Token - " + access_token);

        $.ajax({
          url: "/V4/SetAccessToken",
          method: "GET",
          data: { access_token: access_token },
          success: function (response) {
            console.log(response);
          },
        });

        $.ajax({
          url: "https://www.googleapis.com/oauth2/v1/userinfo",
          method: "GET",
          data: { access_token: access_token },
          success: function (response) {
            console.log("User Details-");
            console.log(response);
            console.log(response.name);
            console.log(response.email);
            var username = response.name;
            var email = response.email;

            $.ajax({
              url: "/V4/GoogleSignin",
              method: "GET",
              data: {
                username: username,
                email: email,
              },
              success: function (response) {
                $.ajax({
                  url: "/V4/j_security_check",
                  method: "POST",
                  data: { j_username: email, j_password: access_token },
                  success: function (response) {
                    console.log("J security SENTT");
                    console.log(response);
                    if (response == 2) {
                      console.log("error page");
                    } else {
                      now.router.transitionTo("user.Welcome");
                    }
                  },
                  error(xhr, status, error) {
                    console.log("error");
                  },
                });

                // window.location = 'http://localhost:4200/user/Welcome';
              },
            });
          },
        });
      },
    });
    client.requestAccessToken();
  }

  @action
  Gsign_in() {
    $.ajax({
      url: "/V4/LoginServlet",
      method: "GET",
      success: function (response) {
        console.log("google sign in - sent from show-ticket controller");
        console.log(response);
        console.log(response);
        var result = response;
        // now.router.transitionTo("user.Welcome");
        //   window.location.reload(true);
      },
    });
  }
}

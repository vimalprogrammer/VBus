import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserTicketCheckingController extends Controller {
  @service router;
  @action
  user_register() {
    var fname = document.getElementById("fname").value;
    var lname = document.getElementById("lname").value;
    var email = document.getElementById("email").value;
    var ph = document.getElementById("ph").value;
    var gender = document.querySelector('input[name = "gender"]:checked').value;
    var password = document.getElementById("password").value;
    var confirm_password = document.getElementById("confirm_password").value;

    var t = this;
    console.log(fname);
    console.log(lname);
    console.log(email);
    console.log(ph);
    console.log(gender);
    console.log(password);
    console.log(confirm_password);

    var t=this;
    $.ajax({
      url: "/V4/user_register",
      method: "GET",
      data: {
        fname: fname,
        lname: lname,
        email: email,
        ph: ph,
        gender: gender,
        password: password,
        confirm_password: confirm_password,
      },
      success: function (response) {
        console.log("user register ajax sent");
        console.log(response);
        t.router.transitionTo("user.login1");
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Registration Successful',
          showConfirmButton: false,
          timer: 1500
        }) 
      },
    });
  }
}

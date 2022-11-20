import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserQrcodeController extends Controller {
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
  qrcheck() {
    var otpcode = document.getElementById("otpcode").value;
    var t = this;
    console.log(otpcode);
    $.ajax({
      url: "/V4/qrcheck",
      method: "GET",
      data: { otpcode: otpcode },
      success: function (response) {
        console.log("ajax sent");
        console.log(response);
        var res = response;
        if (res == 1) {
          t.router.transitionTo("user.Welcome");
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Login Successful',
            showConfirmButton: false,
            timer: 1500
          }) 
        }
        else
        {
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Invalid Credentials!',
          })
        }
      },
    });
  }
}

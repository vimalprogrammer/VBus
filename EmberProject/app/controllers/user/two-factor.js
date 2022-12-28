import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserTwoFactorController extends Controller {
  @service router;
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
        console.log("Response : "+response);
        console.log("OTP :"+otpcode);
        var res = response;
        if (res == 1) {
          t.router.transitionTo("user.login1");
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Login Successful",
            showConfirmButton: false,
            timer: 1500,
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Invalid Credentials!",
          });
        }
      },
    });
  }
}

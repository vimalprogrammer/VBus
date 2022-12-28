import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminLogin2Controller extends Controller {
  @service router;
  @action
  admin_login() {
    var ad_name = document.getElementById("ad_name").value;
    var t = this;
    var ad_pass = document.getElementById("ad_pwd").value;
    console.log(ad_name);
    console.log(ad_pass);
    $.ajax({
      url: "/V4/admin_login",
      method: "GET",
      data: { ad_name: ad_name, ad_pass: ad_pass },
      success: function (response) {
        console.log("ajax sent");
        console.log(response);
        var result = response;
        if (result == 1) {
          console.log("success");
          // t.router.transitionTo("user.Welcome");
          t.router.transitionTo("admin.totp2");
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

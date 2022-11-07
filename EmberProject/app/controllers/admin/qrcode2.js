import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminQrcode2Controller extends Controller {
  @service router;
  @action
  qrcheck2() {
    var ad_otpcode = document.getElementById("ad_otpcode").value;
    var t = this;
    console.log(ad_otpcode);
    $.ajax({
      url: "/V4/qrcheck2",
      method: "GET",
      data: { ad_otpcode: ad_otpcode },
      success: function (response) {
        console.log("ajax sent for admin qrcode");
        console.log(response);
        var res = response;
        if (res == 1) {
          // t.router.transitionTo("user.Welcome");
          t.router.transitionTo("admin.ad_panel");
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

import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserTotpController extends Controller {
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
  qrcode() {
    var name = document.getElementById("usr").value;
    var t = this;
    var password = document.getElementById("pwd").value;
    console.log(name);
    console.log(password);
    $.ajax({
      url: "/V4/Auth_user",
      method: "GET",
      data: { name: name, password: password },
      success: function (response) {
        console.log("ajax sent");
        console.log(response);
        var result = response;
        // t.router.transitionTo("user.Welcome");
        t.router.transitionTo("user.qrcode");
        //   window.location.reload(true);
      },
    });
  }
}

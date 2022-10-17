import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserBookTicket1Controller extends Controller {
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
  TicketChecking() {
    var dep = document.getElementById("myInput").value;
    var t = this;
    var des = document.getElementById("myInput1").value;
    console.log(dep);
    console.log(des);
    $.ajax({
      url: "/V4/TicketChecking",
      method: "GET",
      data: { dep: dep, des: des },
      success: function (response) {
        console.log("ajax sent for ticket checking");
        console.log(response);
        var result = response;
        t.router.transitionTo("user.TicketChecking");
        //   window.location.reload(true);
      },
    });
  }
}

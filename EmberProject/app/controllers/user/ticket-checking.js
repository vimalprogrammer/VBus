import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserTicketCheckingController extends Controller {
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
  ticketButton(id) {
    var bus_no = document.getElementById(id).value;
    var t = this;
    console.log(bus_no);
    $.ajax({
      url: "/V4/ticketButton",
      method: "GET",
      data: { bus_no: bus_no },
      success: function (response) {
        console.log("ajax sent");
        console.log(response);
        var result = response;
        t.router.transitionTo("user.showTicket");
        //   window.location.reload(true);
      },
    });
  }
}

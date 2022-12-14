import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserShowTicketController extends Controller {
  @service router;
  constructor() {
    console.log("chchc");
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
  invoice(seat_id) {
    var bus_booked = document.getElementById(seat_id).value;
    var t = this;
    console.log(bus_booked);
    $.ajax({
      url: "/V4/Test_Ticket",
      method: "GET",
      data: { bus_booked: bus_booked },
      success: function (response) {
        console.log("ajax sent from show-ticket controller");
        var result = response;
        const obj = JSON.parse(response);
        console.log(obj);
        console.log(obj.res);
        var res = obj.res;
        if (res == "false") {
          Swal.fire("Sorry the seat is already booked by someone else");
        } else {
          t.router.transitionTo("user.invoice");
        }
      },
    });
  }
}

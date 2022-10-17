import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserWelcomeController extends Controller {
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
  logout() {
    var t = this;
    console.log("logout checking js");
    $.ajax({
      url: "/V4/logout",
      method: "GET",
      success: function (response) {
        console.log("ajax sent");
        console.log(response);
        // t.router.transitionTo("user.Welcome");
        t.router.transitionTo("login");
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Logged out Successful',
          showConfirmButton: false,
          timer: 1500
        }) 
        //   window.location.reload(true);
      },
    });
  }
}

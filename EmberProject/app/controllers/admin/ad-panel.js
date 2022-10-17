import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminAdPanelController extends Controller {
  @service router;
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
          title: 'Logged out Successfully',
          showConfirmButton: false,
          timer: 1500
        }) 
        //   window.location.reload(true);
      },
    });
  }
}

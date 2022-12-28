import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminAddAdminController extends Controller {
  @service router;
  @action
  add_admin() {
    var new_admin = document.getElementById("new_admin").value;
    var new_pass = document.getElementById("new_pass").value;
    var t = this;
    console.log(new_admin);
    console.log(new_pass);
    $.ajax({
      url: "/V4/add_admin",
      method: "GET",
      data: { new_admin: new_admin, new_pass: new_pass },
      success: function (response) {
        console.log("add admin ajax sent");
        console.log(response);
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Admin Added Successfully",
          showConfirmButton: false,
          timer: 1500,
        });
      },
    });
  }
}

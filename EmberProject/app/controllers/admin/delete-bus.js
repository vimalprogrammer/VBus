import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminDeleteBusController extends Controller {
  @service router;
  @action
  delete_bus() {
    var bus_no = document.getElementById("bus_no").value;
    var t = this;
    console.log(bus_no);
    $.ajax({
      url: "/V4/delete_bus",
      method: "GET",
      data: { bus_no: bus_no },
      success: function (response) {
        console.log("delete ajax sent");
        console.log(response);
        t.router.transitionTo("admin.ad_panel");
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Bus Deleted Successfully",
          showConfirmButton: false,
          timer: 1500,
        });
      },
    });
  }
}

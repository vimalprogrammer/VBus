import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminEditBusController extends Controller {
  @service router;
  @action
  edit_bus() {
    var bus_number = document.getElementById("bus_number").value;
    var bus_name = document.getElementById("bus_name").value;
    var seats = document.getElementById("seats").value;
    var sleeper = document.getElementById("sleeper").value;
    var dep = document.getElementById("dep").value;
    var des = document.getElementById("des").value;
    var price = document.getElementById("price").value;

    var t = this;
    console.log(bus_number);
    console.log(bus_name);
    console.log(seats);
    console.log(sleeper);
    console.log(dep);
    console.log(des);
    console.log(price);
    $.ajax({
      url: "/V4/Edit_bus",
      method: "GET",
      data: {
        bus_number: bus_number,
        bus_name: bus_name,
        seats: seats,
        sleeper: sleeper,
        dep: dep,
        des: des,
        price: price,
      },
      success: function (response) {
        console.log("edit_bus ajax sent");
        console.log(response);
        var result = response;
        t.router.transitionTo("admin.ad_panel");
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Bus Edited Successfully',
          showConfirmButton: false,
          timer: 1500
        }) 
      },
    });
  }
}

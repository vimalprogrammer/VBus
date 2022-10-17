import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class AdminAddBusController extends Controller {
  @service router;
  @action
  bus_layout() {
    var bus_number = document.getElementById("bus_number").value;
    var bus_name = document.getElementById("bus_name").value;
    var ac = document.querySelector('input[name = "ac"]:checked').value;
    var sleeper = document.querySelector(
      'input[name = "sleeper"]:checked'
    ).value;
    var dep = document.getElementById("dep").value;
    var des = document.getElementById("des").value;
    var price = document.getElementById("price").value;
    var layout = document.querySelector('input[name = "layout"]:checked').value;
    var last_row = document.querySelector(
      'input[name = "last_row"]:checked'
    ).value;
    var lower_berth = document.querySelector(
      'input[name = "lower_berth"]:checked'
    ).value;
    var upper_berth = document.querySelector(
      'input[name = "upper_berth"]:checked'
    ).value;

    var t = this;
    console.log(bus_number);
    console.log(bus_name);
    console.log(ac);
    console.log(sleeper);
    console.log(dep);
    console.log(des);
    console.log(price);
    console.log(layout);
    console.log(last_row);
    console.log(lower_berth);
    console.log(upper_berth);

    $.ajax({
      url: "/V4/add_bus",
      method: "GET",
      data: {
        bus_number: bus_number,
        bus_name: bus_name,
        ac: ac,
        sleeper: sleeper,
        dep: dep,
        des: des,
        price: price,
        layout: layout,
        last_row: last_row,
        lower_berth: lower_berth,
        upper_berth: upper_berth,
      },
      success: function (response) {
        console.log("add bus ajax sent");
        console.log(response);
      },
    });
  }
}

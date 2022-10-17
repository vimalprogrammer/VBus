import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";

export default class DemoController extends Controller {
  // @action
  // check() {
  //   console.log(" Controller ");
  // }
  model() {
    var d = $.getJSON("https://reqres.in/api/products/5");
    console.log(d);
    return "arun";
  }
}

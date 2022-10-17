import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserInvoiceRoute extends Route {
  @service store;
  model() {
    console.log("invoice checking js");
    return this.store.findAll("invoice2");
  }
}

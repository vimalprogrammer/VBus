import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserShowTicketRoute extends Route {
  @service store;
  model() {
    console.log("ticket checking js");
    return this.store.findAll("showTicket2");
  }
}

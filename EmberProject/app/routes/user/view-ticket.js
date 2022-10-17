import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserViewTicketRoute extends Route {
  @service store;
  model() {
    console.log("welcome route js");
    return this.store.findAll("viewTicket");
  }
}

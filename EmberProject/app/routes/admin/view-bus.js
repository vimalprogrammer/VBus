import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class AdminViewBusRoute extends Route {
  @service store;
  model() {
    console.log("view buses js");
    return this.store.findAll("viewBus");
  }
}

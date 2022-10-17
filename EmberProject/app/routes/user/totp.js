import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserTotpRoute extends Route {
  @service store;
  model() {
    console.log("ticket checking js");
    return this.store.findAll("Auth_user");
  }
}

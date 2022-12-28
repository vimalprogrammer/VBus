import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserTwoFactorRoute extends Route {
  @service store;
  model() {
    console.log("two factor js");
    return this.store.findAll("twoFactor");
  }
}

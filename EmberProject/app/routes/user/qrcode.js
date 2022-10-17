import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserQrcodeRoute extends Route {
  @service store;
  model() {
    console.log("qrcode checking js");
    return this.store.findAll("Auth_user");
  }
}

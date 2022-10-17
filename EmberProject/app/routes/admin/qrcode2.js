import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class AdminQrcode2Route extends Route {
  @service store;
  model() {
    console.log("qrcode 2 checking js");
    return this.store.findAll("Auth_user2");
  }
}

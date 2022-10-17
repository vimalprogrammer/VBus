import RESTAdapter from "@ember-data/adapter/rest";

export default class AuthUser2Adapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("auth user 2 * adapter");
    return "Auth_user2";
  }
}

import RESTAdapter from "@ember-data/adapter/rest";

export default class AuthUserAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("auth user * adapter");
    return "Auth_user";
  }
}

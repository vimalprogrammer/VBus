import RESTAdapter from "@ember-data/adapter/rest";

export default class GetUserAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("get user * adapter");
    return "getUser";
  }
}

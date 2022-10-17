import RESTAdapter from "@ember-data/adapter/rest";

export default class Invoice2Adapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("invoice 2 * adapter");
    return "invoice2";
  }
}

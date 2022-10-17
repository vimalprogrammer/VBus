import RESTAdapter from "@ember-data/adapter/rest";

export default class ShowTicket2Adapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("viewshow Tickets * adapter");
    return "showTicket2";
  }
}

import RESTAdapter from "@ember-data/adapter/rest";

export default class ViewTicketsAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("view Tickets * adapter");
    return "viewTicket";
  }
}

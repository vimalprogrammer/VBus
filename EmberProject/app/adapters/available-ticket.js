import RESTAdapter from "@ember-data/adapter/rest";

export default class AvailableTicketAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("available Ticket * adapter");
    return "availableTicket";
  }
}

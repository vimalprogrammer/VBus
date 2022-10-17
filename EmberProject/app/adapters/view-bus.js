import RESTAdapter from "@ember-data/adapter/rest";

export default class ViewBusAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("view Buses * adapter");
    return "viewBus";
  }
}

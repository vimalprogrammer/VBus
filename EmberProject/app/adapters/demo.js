import RESTAdapter from "@ember-data/adapter/rest";

export default class DemoAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("admin--roleslist--addnewrole * adapter");
    return "demo";
  }
}

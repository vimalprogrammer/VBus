import RESTAdapter from "@ember-data/adapter/rest";

export default class QrcodeAdapter extends RESTAdapter {
  host = "/V4";
  pathForType() {
    console.log("twoFactor * adapter");
    return "twoFactor";
  }
}

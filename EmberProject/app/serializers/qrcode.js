import RESTSerializer from "@ember-data/serializer/rest";

export default class QrcodeSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("qrCode * serialzer");
    payload = { qrcode: payload };
    console.log(payload);
    return super.normalizeResponse(
      store,
      primaryModelClass,
      payload,
      id,
      requestType
    );
  }
}

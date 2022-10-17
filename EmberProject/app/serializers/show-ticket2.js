import RESTSerializer from "@ember-data/serializer/rest";

export default class ShowTicket2Serializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("show tickets * serialzer");
    payload = { showTicket2: payload };
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

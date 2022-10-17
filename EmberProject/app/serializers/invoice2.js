import RESTSerializer from "@ember-data/serializer/rest";

export default class Invoice2Serializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("invoice * serialzer");
    payload = { invoice2: payload };
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

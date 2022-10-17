import RESTSerializer from "@ember-data/serializer/rest";

export default class DemoSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("admin--groupslist * serialzer");
    payload = { demo: payload };
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

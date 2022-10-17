import RESTSerializer from "@ember-data/serializer/rest";

export default class GetUserSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("get user * serialzer");
    payload = { getUser: payload };
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

import RESTSerializer from "@ember-data/serializer/rest";

export default class AuthUser2Serializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("auth user 2 * serialzer");
    payload = { Auth_user2: payload };
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

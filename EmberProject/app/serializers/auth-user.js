import RESTSerializer from "@ember-data/serializer/rest";

export default class AuthUserSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("auth user * serialzer");
    payload = { Auth_user: payload };
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

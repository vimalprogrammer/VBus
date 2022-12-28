import RESTSerializer from "@ember-data/serializer/rest";

export default class TwoFactorSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("two factor * serialzer");
    payload = { twoFactor: payload };
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

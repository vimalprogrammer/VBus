import RESTSerializer from "@ember-data/serializer/rest";

export default class ViewTicketsSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("get tickets * serialzer");
    payload = { viewTicket: payload };
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

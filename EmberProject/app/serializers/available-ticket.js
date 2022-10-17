import RESTSerializer from "@ember-data/serializer/rest";

export default class AvailableTicketSerializer extends RESTSerializer {
  normalizeResponse(store, primaryModelClass, payload, id, requestType) {
    console.log("available tciket * serialzer");
    payload = { availableTicket: payload };
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

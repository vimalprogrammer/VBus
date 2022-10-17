import Model, { attr } from "@ember-data/model";

export default class AvailableTicketModel extends Model {
  @attr bus_no;
  @attr bus_name;
  @attr seats;
  @attr sleeper;
  @attr dep;
  @attr des;
  @attr price;
  @attr ac;
}

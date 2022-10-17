import Model, { attr } from "@ember-data/model";

export default class ViewTicketModel extends Model {
  @attr user_id;
  @attr username;
  @attr email;
  @attr ticket_id;
  @attr bus_no;
  @attr price;
}

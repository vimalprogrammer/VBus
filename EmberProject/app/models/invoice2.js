import Model, { attr } from "@ember-data/model";

export default class Invoice2Model extends Model {
  @attr seat_booked;
  @attr name;
  @attr email;
  @attr price;
  @attr bus_no;
  @attr google_sign_in;
  @attr google_access_token;
  @attr seat_block_flag;
}

import Model, { attr } from "@ember-data/model";

export default class ShowTicket2Model extends Model {
  @attr seat_cnt;
  @attr available;
  @attr seat_no_layout;
  @attr bus_walk_place;
  @attr slepper40;
  @attr slepper50;
}

import Model, { attr } from "@ember-data/model";

export default class AuthUser2Model extends Model {
  @attr ad_qr;
  @attr ad_code;
}

import Model, { attr } from "@ember-data/model";

export default class TwoFactorModel extends Model {
  @attr qr;
  @attr code;
}

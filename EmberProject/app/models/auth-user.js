import Model, { attr } from "@ember-data/model";

export default class AuthUserModel extends Model {
  @attr qr;
  @attr code;
}

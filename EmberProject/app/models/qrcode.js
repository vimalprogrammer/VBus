import Model, { attr } from "@ember-data/model";

export default class QrcodeModel extends Model {
  @attr qr;
  @attr code;
}

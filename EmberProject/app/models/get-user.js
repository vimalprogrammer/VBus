import Model, { attr } from "@ember-data/model";

export default class GetUserModel extends Model {
  @attr username;
}

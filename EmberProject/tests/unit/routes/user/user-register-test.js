import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Route | user/user_register", function (hooks) {
  setupTest(hooks);

  test("it exists", function (assert) {
    let route = this.owner.lookup("route:user/user-register");
    assert.ok(route);
  });
});

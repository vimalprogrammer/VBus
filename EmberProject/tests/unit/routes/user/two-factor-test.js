import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Route | user/twoFactor", function (hooks) {
  setupTest(hooks);

  test("it exists", function (assert) {
    let route = this.owner.lookup("route:user/two-factor");
    assert.ok(route);
  });
});

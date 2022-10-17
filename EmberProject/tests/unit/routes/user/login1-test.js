import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Route | user/login1", function (hooks) {
  setupTest(hooks);

  test("it exists", function (assert) {
    let route = this.owner.lookup("route:user/login1");
    assert.ok(route);
  });
});

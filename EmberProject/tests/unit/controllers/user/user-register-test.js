import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Controller | user/user-register", function (hooks) {
  setupTest(hooks);

  // TODO: Replace this with your real tests.
  test("it exists", function (assert) {
    let controller = this.owner.lookup("controller:user/user-register");
    assert.ok(controller);
  });
});

import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Controller | admin/edit-bus", function (hooks) {
  setupTest(hooks);

  // TODO: Replace this with your real tests.
  test("it exists", function (assert) {
    let controller = this.owner.lookup("controller:admin/edit-bus");
    assert.ok(controller);
  });
});

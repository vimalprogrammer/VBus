import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Adapter | get user", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let adapter = this.owner.lookup("adapter:get-user");
    assert.ok(adapter);
  });
});

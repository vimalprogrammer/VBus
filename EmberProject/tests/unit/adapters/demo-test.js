import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Adapter | demo", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let adapter = this.owner.lookup("adapter:demo");
    assert.ok(adapter);
  });
});

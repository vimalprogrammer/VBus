import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Adapter | two factor", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let adapter = this.owner.lookup("adapter:two-factor");
    assert.ok(adapter);
  });
});

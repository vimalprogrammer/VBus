import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Adapter | view ticket", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let adapter = this.owner.lookup("adapter:view-ticket");
    assert.ok(adapter);
  });
});

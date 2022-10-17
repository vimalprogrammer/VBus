import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Adapter | invoice2", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let adapter = this.owner.lookup("adapter:invoice2");
    assert.ok(adapter);
  });
});

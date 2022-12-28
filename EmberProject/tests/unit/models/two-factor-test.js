import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Model | two factor", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let store = this.owner.lookup("service:store");
    let model = store.createRecord("two-factor", {});
    assert.ok(model);
  });
});

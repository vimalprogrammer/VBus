import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Model | view ticket", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let store = this.owner.lookup("service:store");
    let model = store.createRecord("view-ticket", {});
    assert.ok(model);
  });
});

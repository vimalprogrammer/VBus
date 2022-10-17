import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Model | show ticket2", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let store = this.owner.lookup("service:store");
    let model = store.createRecord("show-ticket2", {});
    assert.ok(model);
  });
});

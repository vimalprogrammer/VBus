import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Route | admin/delete_bus", function (hooks) {
  setupTest(hooks);

  test("it exists", function (assert) {
    let route = this.owner.lookup("route:admin/delete-bus");
    assert.ok(route);
  });
});

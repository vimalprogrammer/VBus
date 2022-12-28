import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Serializer | two factor", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let store = this.owner.lookup("service:store");
    let serializer = store.serializerFor("two-factor");

    assert.ok(serializer);
  });

  test("it serializes records", function (assert) {
    let store = this.owner.lookup("service:store");
    let record = store.createRecord("two-factor", {});

    let serializedRecord = record.serialize();

    assert.ok(serializedRecord);
  });
});

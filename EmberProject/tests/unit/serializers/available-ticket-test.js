import { module, test } from "qunit";
import { setupTest } from "ember-project/tests/helpers";

module("Unit | Serializer | available ticket", function (hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test("it exists", function (assert) {
    let store = this.owner.lookup("service:store");
    let serializer = store.serializerFor("available-ticket");

    assert.ok(serializer);
  });

  test("it serializes records", function (assert) {
    let store = this.owner.lookup("service:store");
    let record = store.createRecord("available-ticket", {});

    let serializedRecord = record.serialize();

    assert.ok(serializedRecord);
  });
});

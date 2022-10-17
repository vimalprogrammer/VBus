import EmberRouter from "@ember/routing/router";
import config from "ember-project/config/environment";

export default class Router extends EmberRouter {
  location = config.locationType;
  rootURL = config.rootURL;
}

Router.map(function () {
  this.route("login");

  this.route("user", function () {
    this.route("login1");
    this.route("Welcome");
    this.route("view_ticket");
    this.route("book_ticket1");
    this.route("TicketChecking");
    this.route("showTicket");
    this.route("invoice");
    this.route("addCal");
    this.route("totp");
    this.route("qrcode");
    this.route("user_register");
  });
  this.route("not-found", { path: "/*path" });
  this.route("item", { path: "/item/:item_id" });

  this.route("admin", function () {
    this.route("login2");
    this.route("totp2");
    this.route("qrcode2");
    this.route("ad_panel");
    this.route("add_bus");
    this.route("view_bus");
    this.route("edit_bus");
    this.route("delete_bus");
    this.route("add_admin");
  });
});

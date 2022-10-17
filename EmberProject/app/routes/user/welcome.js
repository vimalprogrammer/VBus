import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default class UserWelcomeRoute extends Route {
  @service store;
  model() {
    console.log("welcome route js");
    return this.store.findAll("getUser");
  }
}

// import Route from "@ember/routing/route";
// import { inject as service } from "@ember/service";
// import RSVP from 'rsvp';

// export default class UserWelcomeRoute extends Route {
//   @service store;
//   model(params) {
//     const { username } = params;
//     console.log("welcome route js");

//     return RSVP.hash({
//       username:username,

//     });
//     // return this.store.findAll("getUser");

//   }
// }

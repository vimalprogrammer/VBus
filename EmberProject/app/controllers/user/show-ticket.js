import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserShowTicketController extends Controller {
  @service router;
  constructor() {
    console.log("chchc");
    super(...arguments);
    var now = this;
    $.ajax({
      url: "/V4/IsLoggedIn",
      method: "GET",
      data: {},
      success: function (response) {
        console.log(response);
        if (response == 1) {
          console.log("You can Access! Logged In!");
        } else if (response == 0) {
          now.router.transitionTo("user.login1");
        }
      },
    });
  }
  @action
  invoice(seat_id) {
    var bus_booked = document.getElementById(seat_id).value;
    var t = this;
    console.log(bus_booked);
    $.ajax({
      url: "/V4/Test_Ticket",
      method: "GET",
      data: { bus_booked: bus_booked },
      success: function (response) {
        console.log("ajax sent from show-ticket controller");
        // console.log(response);
        // console.log(response);
        var result = response;
        // t.router.transitionTo("user.invoice");
        const obj = JSON.parse(response);
        console.log(obj);
        console.log(obj.res);
        var res = obj.res;
        if(res=="false"){
            Swal.fire('Sorry the seat is already booked by someone else');
        }

        else
        {
            t.router.transitionTo("user.invoice");
        }


        // $.ajax({
        //   url: "/V4/invoice",
        //   method: "GET",
        //   success: function (response) {
        //     console.log("ajax sent from invoice controller");
        //     // console.log(response);
        //     // console.log(response);
        //     var result2 = response;
        //     // t.router.transitionTo("user.invoice");
        //     const obj2 = JSON.parse(response);
        //     console.log(obj2);

        //     var res = obj2.seat_block;
        //     console.log(res);

        //     if(res == false){
        //       console.log("Seat is available");
        //     }
        //     else{
        //       console.log("Seat is already booked");
        //       Swal.fire('Sorry the seat is already booked by someone else');
        //     }

            // if(obj2.seat_block==true)
            // {
            //   console.log("Seat is already booked");
            //   Swal.fire('Sorry the seat is already booked by someone else');
            // }
            // else{
            //   console.log("Seat is available");
            // }
            // else
            // {
            //   Swal.fire('Success! Successfully booked the seat');
            //   //t.router.transitionTo("user.invoice");
            // }
          // window.location.reload(true);
    //   },
    // });
  },
});
  }
}

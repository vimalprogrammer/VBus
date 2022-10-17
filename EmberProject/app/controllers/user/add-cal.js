import Controller from "@ember/controller";
import { action } from "@ember/object";
import { service } from "@ember/service";
import $ from "jquery";

export default class UserAddCalController extends Controller {
  @service router;
  constructor() {
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
  AddToCal() {
    $.ajax({
      url: '/V4/',
      method: 'GET',
      data: {},
      success: function (response) {
        console.log(response);
        var accessToken = response;
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token=' + accessToken);
        xhr.setRequestHeader('Authorization', 'Bearer ' + accessToken);
        xhr.responseType = 'json';
        xhr.onload = () => {
        console.log(xhr.response.id);
        };
      }
      });
  }

  @action
  AddToGoogleCal(google_access_token) {
    var access_token = document.getElementById(google_access_token).value;
    console.log(access_token);

    // gapi.client.getToken({access_token: access_token});
    // console.log(gapi.client.getToken());

    gapi.auth.setToken({
      access_token: access_token,
  });

    var title = "Vimal Bus Reservation";
    var description = "Bus Booked Successfully";
    var location = "Chennai";

    var startdate = new Date();
    var enddate = new Date();
    var DD = String(startdate.getDate()).padStart(2, '0');
    var MM = String(startdate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var YYYY = startdate.getFullYear();
    startdate = YYYY +"-"+ MM +"-"+ DD+"T10:00:00.000-07:00";
    enddate= YYYY +"-"+ MM +"-"+ DD+"T10:25:00.000-07:00";
    console.log('Date-Time: ', startdate);
    console.log('Date-Time: ', enddate);

    var event = {
      summary: title,
      location: location,
      description: description,
      start: {
        dateTime: startdate,
        timeZone: "Asia/Kolkata",
      },
      end: {
        dateTime: enddate,
        timeZone: "Asia/Kolkata",
      },
      // attendees: [
      //   { email: "attendeeEmail" },
      // ],
      reminders: {
        useDefault: false,
        overrides: [
          { method: "email", minutes: 24 * 60 },
          { method: "popup", minutes: 10 },
        ],
      },
    };

    var request = gapi.client.calendar.events.insert({
      calendarId: "primary",
      resource: event,
    });

    request.execute(function (event) {
      console.log(event);
      console.log("Event created: " + event.htmlLink);
    });
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Successfully added to Google Calendar',
      showConfirmButton: false,
      timer: 1500
    })  
  }
}
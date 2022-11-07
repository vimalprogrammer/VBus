public class TicketBookingThread extends Thread {

	private TicketCounter ticketCounter;
	private String passengerName;
	private String seat_booked;
	private String bus_no;

	public TicketBookingThread(TicketCounter ticketCounter,String passengerName, String seat_booked, String bus_no) 
	{
		this.ticketCounter = ticketCounter;
		this.passengerName = passengerName;
		this.seat_booked = seat_booked;
		this.bus_no = bus_no;
	}
	
	public void run() {
		ticketCounter.bookTicket(passengerName, seat_booked, bus_no);
		// System.out.println("DEBUG "+ticketCounter.Booking_possible);
		Test_Ticket.setM(ticketCounter);
	}
}
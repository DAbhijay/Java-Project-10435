public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("Grand Java Hotel", 3, 3);

        hotel.displayAllRooms();

        System.out.println("\n--- Booking Rooms ---");
        hotel.bookRoom(0, 0);
        hotel.bookRoom(1, 2);

        System.out.println("\n--- Trying to Book an Occupied Room ---");
        hotel.bookRoom(0, 0);

        System.out.println("\n--- Cancelling a Booking ---");
        hotel.cancelBooking(0, 0);

        System.out.println("\n--- Calculating Bill ---");
        Room bookedRoom = hotel.getRoom(1, 2);
        hotel.calculateBill(3, bookedRoom.getPricePerDay());

        hotel.displayAllRooms();
    }
}
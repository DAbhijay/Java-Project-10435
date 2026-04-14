public class Main {
    public static void main(String[] args) {
        Room r = new Room(101, "Single", 1000.0);
        System.out.println("Room Number : " + r.getRoomNumber());
        System.out.println("Room Type   : " + r.getRoomType());
        System.out.println("Price/Day   : " + r.getPricePerDay());
        System.out.println("Available   : " + r.isAvailable());

        r.displayStatus();
        r.setAvailable(false);
        r.displayStatus();
    }
}
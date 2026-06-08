public class DeluxeRoom extends Room {

    public DeluxeRoom(int roomNumber) {
        super(roomNumber, "Deluxe", 3000.0);
    }

    @Override
    public String getRoomCategory() {
        return "Deluxe Room: King Bed, Wi-Fi, AC, 4K TV, Jacuzzi, Mini-Bar";
    }
}
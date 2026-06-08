public class DoubleRoom extends Room {

    public DoubleRoom(int roomNumber) {
        super(roomNumber, "Double", 1800.0);
    }

    @Override
    public String getRoomCategory() {
        return "Double Room: 2 Beds, Wi-Fi, AC, TV, Mini-Fridge";
    }
}
public class SingleRoom extends Room {

    public SingleRoom(int roomNumber) {
        super(roomNumber, "Single", 1000.0);
    }

    @Override
    public String getRoomCategory() {
        return "Single Room: 1 Bed, Wi-Fi, AC, TV";
    }
}
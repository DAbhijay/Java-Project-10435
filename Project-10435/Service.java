public interface Service {

    double TAX_RATE    = 0.10;   // 10% tax
    double FOOD_CHARGE = 150.0;  // Per day food charge
    double SERVICE_FEE = 100.0;  // Flat service fee

    double calculateBill(int days, double rate);
    double calculateFullBill(int days, double rate, boolean includeFood);
    void displayBill(String guestName, int days, double rate, boolean includeFood);
}
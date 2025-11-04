package InterfaceClasses;

public interface Billable {
    Double calculateCharges();
    void generateBill();
    void processPayment(Double amount);
}

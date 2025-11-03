package EntityClasses;

import InterfaceClasses.Billable;
import InterfaceClasses.Displayable;
import Utils.HelperUtils;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class InPatient extends Patient implements Billable, Displayable {
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String roomNumber;
    private String bedNumber;
    private String admittingDoctorId;
    private Double dailyCharges;
    private Double totalCharges;
    private Boolean paymentProcessed;

    public InPatient() {
        super();
    }

    public InPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords, List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber, String admittingDoctorId, Double dailyCharges, Double totalCharges, Boolean paymentProcessed) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId, medicalRecords, appointments);
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
        this.admittingDoctorId = admittingDoctorId;
        this.dailyCharges = dailyCharges;
        this.totalCharges = totalCharges;
        this.paymentProcessed = paymentProcessed;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        if (!HelperUtils.isValidDate(admissionDate)) {
            throw new IllegalArgumentException("Admission Date can't be null");
        }
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        if (!HelperUtils.isValidDate(dischargeDate)) {
            throw new IllegalArgumentException("Discharge Date can't be null");
        }
        if (HelperUtils.isValidDate(this.admissionDate) && dischargeDate.isBefore(this.admissionDate)) {
            throw new IllegalArgumentException("Discharge Date can't be before Admission Date");
        }
        this.dischargeDate = dischargeDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (!HelperUtils.isValidString(roomNumber)) {
            throw new IllegalArgumentException("Room Number can't be null or empty");
        }
        this.roomNumber = roomNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        if (!HelperUtils.isValidString(bedNumber)) {
            throw new IllegalArgumentException("Bed Number can't be null or empty");
        }
        this.bedNumber = bedNumber;
    }

    public String getAdmittingDoctorId() {
        return admittingDoctorId;
    }

    public void setAdmittingDoctorId(String admittingDoctorId) {
        if (!HelperUtils.isValidString(admittingDoctorId)) {
            throw new IllegalArgumentException("Admitting Doctor ID can't be null or empty");
        }
        this.admittingDoctorId = admittingDoctorId;
    }

    public Double getDailyCharges() {
        return dailyCharges;
    }

    public void setDailyCharges(Double dailyCharges) {
        if (dailyCharges == null) {
            throw new IllegalArgumentException("Daily Charges can't be null");
        }
        if (dailyCharges < 0) {
            throw new IllegalArgumentException("Daily Charges must be a non-negative number");
        }
        this.dailyCharges = dailyCharges;
    }

    public Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Double totalCharges) {
        if (totalCharges == null) {
            throw new IllegalArgumentException("Total Charges can't be null");
        }
        if (totalCharges < 0) {
            throw new IllegalArgumentException("Total Charges must be a non-negative number");
        }
        this.totalCharges = totalCharges;
    }

    public boolean isPaymentProcessed() {
        return paymentProcessed != null && paymentProcessed;
    }

    public void setPaymentProcessed(boolean paymentProcessed) {
        this.paymentProcessed = paymentProcessed;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Discharge Date: " + dischargeDate);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Bed Number: " + bedNumber);
        System.out.println("Admitting Doctor ID: " + admittingDoctorId);
        System.out.println("Daily Charges: " + dailyCharges);
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    @Override
    public Double calculateCharges() {
        // require both dates and dailyCharges to be valid
        if (!HelperUtils.isValidDate(admissionDate) || !HelperUtils.isValidDate(dischargeDate) || dailyCharges == null) {
            System.out.println("Admission date, discharge date or daily charges are not set");
            return 0.0;
        }
        long days = DAYS.between(admissionDate, dischargeDate);
        if (days < 0) {
            System.out.println("Discharge date is before admission date");
            return 0.0;
        }
        return days * dailyCharges;
    }

    @Override
    public void generateBill() {
        System.out.println("Patient Bill");
        System.out.println(" -----------------------");
        System.out.println("Patient Name: " + getFirstName() + " " + getLastName());
        System.out.println("Admission Date: " + admissionDate+"Discharge Date: " + dischargeDate);
        System.out.println("Room Number: " + roomNumber+"Bed Number: " + bedNumber);
        System.out.println("Daily Charges: " + dailyCharges);
        System.out.println("Total Charges: " + calculateCharges());
        System.out.println("Payment Processed: " + (paymentProcessed ? "Yes" : "No"));
        System.out.println(" -----------------------");
    }

    @Override
    public void processPayment(Double amount) {
        double totalCharges = calculateCharges();
        if (amount > totalCharges) {
            paymentProcessed = true;
            System.out.println("Payment of " + amount + " processed successfully. Remaining Balance: " + (amount - totalCharges));
        } else if (amount == totalCharges) {
            paymentProcessed=true;
            System.out.println("Payment of " + amount + " processed successfully. No balance Remaining");
        } else {
            paymentProcessed=false;
            System.out.println("Insufficient payment. Remaining due is: " + (totalCharges-amount));
        }
    }
}

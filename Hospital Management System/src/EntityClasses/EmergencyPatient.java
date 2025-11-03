package EntityClasses;

import InterfaceClasses.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;

public class EmergencyPatient extends InPatient implements Displayable {
    private String emergencyType;
    private ArrivalMode arrivalMode; // (String - Ambulance/Walk-in)
    private Integer triageLevel; //(int - 1 to 5)
    private Boolean admittedViaER;

    public EmergencyPatient() {
        super();
    }

    public EmergencyPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords, List<Appointment> appointments, LocalDate admissionDate, LocalDate dischargeDate, String roomNumber, String bedNumber, String admittingDoctorId, Double dailyCharges, Double totalCharges, Boolean paymentProcessed, String emergencyType, ArrivalMode arrivalMode, Integer triageLevel, Boolean admittedViaER) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId, medicalRecords, appointments, admissionDate, dischargeDate, roomNumber, bedNumber, admittingDoctorId, dailyCharges, totalCharges, paymentProcessed);
        this.emergencyType = emergencyType;
        this.arrivalMode = arrivalMode;
        this.triageLevel = triageLevel;
        this.admittedViaER = admittedViaER;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        if (!HelperUtils.isValidString(emergencyType)) {
            throw new IllegalArgumentException("Emergency Type can't be null or empty");
        }
        this.emergencyType = emergencyType;
    }

    public ArrivalMode getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(ArrivalMode arrivalMode) {
        if (HelperUtils.isNull(arrivalMode)) {
            throw new IllegalArgumentException("Arrival Mode can't be null");
        }
        this.arrivalMode = arrivalMode;
    }

    public Integer getTriageLevel() {
        return triageLevel;
    }

    public void setTriageLevel(Integer triageLevel) {
        if (HelperUtils.isNull(triageLevel) || !HelperUtils.isValidNumber(triageLevel, 1, 5)) {
            throw new IllegalArgumentException("Triage level must be between 1 and 5");
        }
        this.triageLevel = triageLevel;
    }

    public Boolean getAdmittedViaER() {
        return admittedViaER;
    }

    public void setAdmittedViaER(Boolean admittedViaER) {
        if (HelperUtils.isNull(admittedViaER)) {
            throw new IllegalArgumentException("Admitted Via ER flag can't be null");
        }
        this.admittedViaER = admittedViaER;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Arrival Mode: " + arrivalMode);
        System.out.println("Triage Level (1=Critical → 5=Minor): " + triageLevel);
        System.out.println("Admitted Via ER: " + admittedViaER);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    @Override
    public Double calculateCharges() {
        Double charges = super.calculateCharges();

        double emergencyCharges = switch (triageLevel) {
            case 1-> 1000.0;
            case 2 -> 750.0;
            case 3-> 500.0;
            case 4-> 250.0;
            case 5-> 100.0;
            default-> 0;

        };
        Double eRCharge = (arrivalMode == ArrivalMode.AMBULANCE) ? 100.0 : 0.0;
        return charges+emergencyCharges+eRCharge;
    }

    @Override
    public void generateBill() {
        System.out.println("-----------------------------------------------------");
        System.out.println("               Emergency Patient Bill                ");
        System.out.println("-----------------------------------------------------");
        super.generateBill();
        System.out.println("Emergency Type: " + emergencyType);
        System.out.println("Arrival Mode: " + arrivalMode);
        System.out.println("Triage Level (1=Critical → 5=Minor): " + triageLevel);
        System.out.println("Admitted Via ER: " + admittedViaER);
        System.out.println("Emergency Charges: " + switch (triageLevel) {
            case 1-> 1000.0;
            case 2 -> 750.0;
            case 3-> 500.0;
            case 4-> 250.0;
            case 5-> 100.0;
            default-> 0.0;
        });
        System.out.println("ER Arrival Charge: " + ((arrivalMode == ArrivalMode.AMBULANCE) ? 100.0 : 0.0));
        System.out.println("total charges(including emergency): " + calculateCharges());
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void processPayment(Double amount) {
        if(triageLevel<=2 && amount<calculateCharges()){
            System.out.println("High-priority emergency case: Payment plan offered. Processing partial payment");
        }
        super.processPayment(amount);
    }

    public void performTriage(){
        System.out.println("Performing triage for Emergency Patient: "+"Patient Name "+getFirstName()+" "+getLastName());
        System.out.println("Patient ID: "+getPatientId()+", Triage Level: "+triageLevel);
        switch (triageLevel){
            case 1-> System.out.println("Immediate life-saving intervention required.");
            case 2,3-> System.out.println("Urgent care needed");
            case 4,5-> System.out.println("Non-Urgent care");
    }}
}

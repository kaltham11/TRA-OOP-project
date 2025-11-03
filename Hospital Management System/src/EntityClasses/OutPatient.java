package EntityClasses;

import InterfaceClasses.Displayable;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;

public class OutPatient extends Patient implements Displayable {
    private Integer visitCount;
    private LocalDate lastVisitDate;
    private String preferredDoctorId;
    private LocalDate nextVisitDate;

    public OutPatient() {
        super();
    }

    public OutPatient(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords, List<Appointment> appointments, Integer visitCount, LocalDate lastVisitDate, String preferredDoctorId, LocalDate nextVisitDate) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, patientId, bloodGroup, allergies, emergencyContact, registrationDate, insuranceId, medicalRecords, appointments);
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
        this.preferredDoctorId = preferredDoctorId;
        this.nextVisitDate = nextVisitDate;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        if(!HelperUtils.isPositive(visitCount) || visitCount==null){
            throw new IllegalArgumentException("visitCount must be a positive integer");
        }
        this.visitCount = visitCount;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        if(!HelperUtils.isValidDate(lastVisitDate)){
            throw new IllegalArgumentException("Last Visit Date can't be null or empty");
        }
        this.lastVisitDate = lastVisitDate;
    }

    public String getPreferredDoctorId() {
        return preferredDoctorId;
    }

    public void setPreferredDoctorId(String preferredDoctorId) {
        if(!HelperUtils.isValidString(preferredDoctorId)){
            throw new IllegalArgumentException("Preferred Doctor ID can't be null or empty");
        }
        this.preferredDoctorId = preferredDoctorId;
    }

    public LocalDate getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDate nextVisitDate) {
        if(!HelperUtils.isValidDate(nextVisitDate)){
            throw new IllegalArgumentException("Next Visit Date can't be null or empty");
        }
        this.nextVisitDate = nextVisitDate;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Visit Count: " + visitCount);
        System.out.println("Last Visit Date: " + lastVisitDate);
        System.out.println("Preferred Doctor ID: " + preferredDoctorId);
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    public  void scheduleFollowUp() {

        nextVisitDate= LocalDate.now().plusDays(15);
        System.out.println("Next follow-up visit scheduled on: " + nextVisitDate);
    }
    public void updateVisitCount(){
        if(visitCount==null){
            visitCount=1;
        }else {
            visitCount++;
        }
        lastVisitDate=LocalDate.now();
    }

}

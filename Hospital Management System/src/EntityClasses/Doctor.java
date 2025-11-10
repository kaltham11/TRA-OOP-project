package EntityClasses;

import InterfaceClasses.Displayable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Utils.HelperUtils;

public class Doctor extends Person implements Displayable {
    private String doctorId;
    private String specialization;
    private String qualification;
    private Integer experienceYears;
    private String departmentId;
    private Double consultationFee;
    private List<String>availableSlots=new ArrayList<>();
    private List<String>assignedPatients=new ArrayList<>();

    public Doctor() {
        super();
    }

    public Doctor(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, Integer experienceYears, String departmentId, Double consultationFee, List<String> availableSlots, List<String> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experienceYears = experienceYears;
        this.departmentId = departmentId;
        this.consultationFee = consultationFee;
        this.availableSlots = availableSlots;
        this.assignedPatients = assignedPatients;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        if (!HelperUtils.isValidString(doctorId)) {
            throw new IllegalArgumentException("Doctor ID can't be null or empty");
        }
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (!HelperUtils.isValidString(specialization)) {
            throw new IllegalArgumentException("Specialization can't be null or empty");
        }
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        if (!HelperUtils.isValidString(qualification)) {
            throw new IllegalArgumentException("Qualification can't be null or empty");
        }
        this.qualification = qualification;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if (HelperUtils.isNegative(experienceYears)) {
            throw new IllegalArgumentException("Experience Years must be a non-negative number");
        }
        this.experienceYears = experienceYears;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        if (!HelperUtils.isValidString(departmentId)) {
            throw new IllegalArgumentException("Department ID can't be null or empty");
        }
        this.departmentId = departmentId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        if (HelperUtils.isNegative(consultationFee)) {
            throw new IllegalArgumentException("Consultation Fee must be a non-negative number");
        }
        this.consultationFee = consultationFee;
    }

    public List<String> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<String> getAssignedPatients() {
        return assignedPatients;
    }

    public void setAssignedPatients(List<String> assignedPatients) {
        if (!HelperUtils.isNotNull(assignedPatients)) {
            throw new IllegalArgumentException("Assigned Patients list can't be null");
        }
        this.assignedPatients = assignedPatients;
    }

    @Override
    public void displayInfo() {
        System.out.println("---------------------------------------------------");
        super.displayInfo();
        System.out.println("Doctor Id: " + doctorId);
        System.out.println("Doctor Specialization: " + specialization);
        System.out.println("Doctor qualification: " + qualification);
        System.out.println("Doctor Experience Years: " + experienceYears);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Consultation Fee: " + consultationFee);
        if (availableSlots.isEmpty()) {
            System.out.println("The Doctor hasn't any Available Slots");
        }
        for (String ava : availableSlots) {
            System.out.println("The Doctor Available Slots is: " + "-" + ava);
        }

        if (assignedPatients.isEmpty()) {
            System.out.println("The Doctor hasn't any Assigned Patients");
        }
        for (String asa : assignedPatients) {
            System.out.println("The Doctor Assigned Patients is: " + "-" + asa);
        }
        System.out.println("---------------------------------------------------");

    }

    @Override
    public void displaySummary() {
        System.out.println("Doctor ID: " + doctorId + ", Name: " + getFirstName() + " " + getLastName() + ", Specialization: " + specialization + ", Department ID: " + departmentId);
    }

    public void assignPatient(String assignedPatient){
     assignedPatients.add(assignedPatient);
        System.out.println("Assigned Patients added successfully");
    }
    public void removePatient(String patientId){
       boolean removed=assignedPatients.removeIf(p->p.equals(patientId));
       if(removed){
           System.out.println("Patient removed successfully");
       }else{
           System.out.println("Patient not found");
       }
    }

    public void  updateAvailability(String slots){
      availableSlots.add(slots);
        System.out.println("Availability updated successfully");
    }

    public  void updateFee(double fee){
        if (HelperUtils.isNegative(fee)) {
            throw new IllegalArgumentException("Consultation Fee must be a non-negative number");
        }
        setConsultationFee(fee);
        System.out.println("Consultation fee updated successfully");
    }
    public  void updateFee(double fee, String reason){
        if (HelperUtils.isNegative(fee)) {
            throw new IllegalArgumentException("Consultation Fee must be a non-negative number");
        }
        setConsultationFee(fee);
        System.out.println("Consultation fee updated successfully. Reason: " + reason);
    }
    public  void addAvailability(String slot){
        availableSlots.add(slot);
        System.out.println("Availability slot added successfully");
    }
    public  void addAvailability(List<String> slots){
        availableSlots.addAll(slots);
        System.out.println("Availability slots added successfully");
    }


}

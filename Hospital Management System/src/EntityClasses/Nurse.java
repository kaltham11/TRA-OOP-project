package EntityClasses;
import InterfaceClasses.Displayable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Utils.HelperUtils;

public class Nurse extends Person implements Displayable {
    private String nurseId;
    private String departmentId;
    private Shift shift;
    private String qualification;
    private List<String> assignedPatients=new ArrayList<>();

    public Nurse() {
        super();
    }

    public Nurse(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String nurseId, String departmentId, Shift shift, String qualification, List<String> assignedPatients) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.nurseId = nurseId;
        this.departmentId = departmentId;
        this.shift = shift;
        this.qualification = qualification;
        this.assignedPatients = assignedPatients;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        if (!HelperUtils.isValidString(nurseId)) {
            throw new IllegalArgumentException("Nurse ID can't be null or empty");
        }
        this.nurseId = nurseId;
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        if (!HelperUtils.isValidString(shift)) {
            throw new IllegalArgumentException("Shift can't be null");
        }
        this.shift = shift;
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
        System.out.println("-----------------------------------------------------");
        super.displayInfo();
        System.out.println("Nurse ID: " + nurseId);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Shift: " + shift);
        System.out.println("Nurse Qualification: " + qualification);
        if (assignedPatients.isEmpty()) {
            System.out.println("The Nurse hasn't any Assigned Patients");
        }
        for (String asa : assignedPatients) {
            System.out.println("The Nurse Assigned Patients is: " + "-" + asa);
        }
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println("Nurse ID: " + nurseId + ", Name: " + getFirstName() + " " + getLastName() + ", Department ID: " + departmentId + ", Shift: " + shift);
    }

    public void assignPatient(String assignedPatient){
        assignedPatients.add(assignedPatient);
        System.out.println("Assigned Patients added successfully");
    }
}

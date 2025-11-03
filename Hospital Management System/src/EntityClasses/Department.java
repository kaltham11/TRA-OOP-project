package EntityClasses;

import InterfaceClasses.Displayable;

import java.util.List;

public class Department implements Displayable {
    private String departmentId;
    private String departmentName;
    private String headDoctorId;
    private List<String> doctors;
    private List<String> nurses;
    private Integer bedCapacity;
    private Integer availableBeds;

    public Department() {
    }

    public Department(String departmentId, String departmentName, String headDoctorId, List<String> doctors, List<String> nurses, Integer bedCapacity, Integer availableBeds) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.headDoctorId = headDoctorId;
        this.doctors = doctors;
        this.nurses = nurses;
        this.bedCapacity = bedCapacity;
        this.availableBeds = availableBeds;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public void setHeadDoctorId(String headDoctorId) {
        this.headDoctorId = headDoctorId;
    }

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        this.doctors = doctors;
    }

    public List<String> getNurses() {
        return nurses;
    }

    public void setNurses(List<String> nurses) {
        this.nurses = nurses;
    }

    public Integer getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(Integer bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        this.availableBeds = availableBeds;
    }

    @Override
    public void displayInfo() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Department ID: " + departmentId);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Head Doctor ID: " + headDoctorId);
        if (doctors.isEmpty()) {
            System.out.println("There are no Doctors");
        }
        for (String doctor : doctors) {
            System.out.println("The Doctor is: " + "-" + doctor);
        }
        if (nurses.isEmpty()) {
            System.out.println("There are no Nurses");
        }
        for (String nurse : nurses) {
            System.out.println("The Nurse is: " + "-" + nurse);
        }
        System.out.println("The Bed Capacity: " + bedCapacity);
        System.out.println("The Available Beds: " + availableBeds);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println("Department ID: " + departmentId + ", Department Name: " + departmentName + ", Head Doctor ID: " + headDoctorId + ", Available Beds: " + availableBeds + "/" + bedCapacity);
    }

    public void assignDoctor(String newDoctor){
        doctors.add(newDoctor);
        System.out.println("Assigned Doctor updated successfully");
    }

    public void assignNurse(String newNurse){
        nurses.add(newNurse);
        System.out.println("Assigned Nurse updated successfully");
    }

    public void updateBedAvailability(Integer newBedAvailability){
        this.availableBeds=newBedAvailability;
        System.out.println("Bed Availability updated successfully");
    }

}

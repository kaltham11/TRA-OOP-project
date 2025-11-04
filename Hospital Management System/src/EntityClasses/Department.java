package EntityClasses;

import InterfaceClasses.Displayable;
import Utils.HelperUtils;

import java.util.ArrayList;
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
        if (!HelperUtils.isValidString(departmentId)) {
            throw new IllegalArgumentException("Department ID can't be null or empty");
        }
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        if (!HelperUtils.isValidString(departmentName)) {
            throw new IllegalArgumentException("Department Name can't be null or empty");
        }
        this.departmentName = departmentName;
    }

    public String getHeadDoctorId() {
        return headDoctorId;
    }

    public void setHeadDoctorId(String headDoctorId) {
        if (!HelperUtils.isValidString(headDoctorId)) {
            throw new IllegalArgumentException("Head Doctor ID can't be null or empty");
        }
        this.headDoctorId = headDoctorId;
    }

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        if (HelperUtils.isNull(doctors)) {
            this.doctors = new ArrayList<>();
            return;
        }
        this.doctors = doctors;
    }

    public List<String> getNurses() {
        return nurses;
    }

    public void setNurses(List<String> nurses) {
        if (HelperUtils.isNull(nurses)) {
            this.nurses = new ArrayList<>();
            return;
        }
        this.nurses = nurses;
    }

    public Integer getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(Integer bedCapacity) {
        if (HelperUtils.isNull(bedCapacity)) {
            throw new IllegalArgumentException("Bed Capacity can't be null");
        }
        if (HelperUtils.isNegative(bedCapacity)) {
            throw new IllegalArgumentException("Bed Capacity must be a non-negative integer");
        }
        this.bedCapacity = bedCapacity;
    }

    public Integer getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(Integer availableBeds) {
        if (HelperUtils.isNull(availableBeds)) {
            throw new IllegalArgumentException("Available Beds can't be null");
        }
        if (HelperUtils.isNegative(availableBeds)) {
            throw new IllegalArgumentException("Available Beds must be a non-negative integer");
        }
        if (HelperUtils.isNotNull(bedCapacity) && availableBeds > bedCapacity) {
            throw new IllegalArgumentException("Available Beds can't exceed Bed Capacity");
        }
        this.availableBeds = availableBeds;
    }

    @Override
    public void displayInfo() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Department ID: " + departmentId);
        System.out.println("Department Name: " + departmentName);
        System.out.println("Head Doctor ID: " + headDoctorId);
        System.out.println("The Bed Capacity: " + bedCapacity);
        System.out.println("The Available Beds: " + availableBeds);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println("Department ID: " + departmentId + ", Department Name: " + departmentName + ", Head Doctor ID: " + headDoctorId + ", Available Beds: " + availableBeds + "/" + bedCapacity);
    }

    public void assignDoctor(String newDoctor){
        if (HelperUtils.isNull(doctors)) {
            doctors = new ArrayList<>();
        }
        doctors.add(newDoctor);
        System.out.println("Assigned Doctor updated successfully");
    }

    public void assignNurse(String newNurse){
        if (HelperUtils.isNull(nurses)) {
            nurses = new ArrayList<>();
        }
        nurses.add(newNurse);
        System.out.println("Assigned Nurse updated successfully");
    }

    public void updateBedAvailability(Integer newBedAvailability){
        setAvailableBeds(newBedAvailability);
        System.out.println("Bed Availability updated successfully");
    }

}

package ServiceClasses;

import EntityClasses.Consultant;
import EntityClasses.Department;
import EntityClasses.Doctor;
import EntityClasses.Nurse;
import InterfaceClasses.Editable;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DepartmentService implements Manageable<Department>, Searchable, Editable<Department> {
    public static  List<Department> departmentList = new ArrayList<>();

    @Override
    public Department add() {
        Department dept = new Department();
        dept.setDepartmentId(HelperUtils.generateId("DEP", 6));
        dept.setDepartmentName(InputHandler.getStringInput("Enter Department Name"));

        String headDoctorId = InputHandler.getStringInput("Enter Head Doctor ID for the Department");
        while (!DoctorService.checkIfIdDoctorExist(headDoctorId)) {
            headDoctorId=InputHandler.getStringInput("Doctor ID does not exist. Please enter a valid Head Doctor ID");
        }
        dept.setHeadDoctorId(headDoctorId);

        int bedCapacity = InputHandler.getIntInput("Enter Bed Capacity for the Department",0, 1000);
        int availableBeds = InputHandler.getIntInput("Enter Available Beds for the Department",0, bedCapacity);
        while (availableBeds > bedCapacity) {
             availableBeds = InputHandler.getIntInput("Available Beds can't be grater than Bed capacity," +
                     "Enter Available Beds for the Department",0, bedCapacity);
        }
        dept.setBedCapacity(bedCapacity);
        dept.setAvailableBeds(availableBeds);

        validate(dept);
        return dept;
    }

    public void save(Department department) {
        if (HelperUtils.isNotNull(department)) {
            departmentList.add(department);
            System.out.println("The department added successfully");
        } else {
            throw new IllegalArgumentException("Department object can't be null");
        }
    }

    @Override
    public Department edit() {
        if (departmentList.isEmpty()) {
            System.out.println("There are no Departments");
            return null;
        }
        String id = InputHandler.getStringInput("Please, Enter Department ID to edit");
        Department selected = null;
        for (Department d : departmentList) {
            if (d.getDepartmentId().equals(id)) {
                selected = d;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Department not found");
            return null;
        }

        boolean editingFlag = true;
        while (editingFlag) {
            System.out.println("""
                    ==============================================
                    Enter the option number to edit it
                    1- Department Name
                    2- Head Doctor ID
                    3- Doctors
                    4- Nurses
                    5- Bed Capacity
                    6- Available Beds
                    7- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    selected.setDepartmentName(InputHandler.getStringInput("Please, Enter a new Department Name"));
                    System.out.println("Department Name updated successfully");
                }
                case 2 -> {
                    String headDoctorId = InputHandler.getStringInput("Please, Enter a new Head Doctor ID");
                    if (DoctorService.checkIfIdDoctorExist(headDoctorId)) {
                        selected.setHeadDoctorId(headDoctorId);
                        System.out.println("Head Doctor updated successfully");
                    } else {
                        System.out.println("Doctor ID does not exist");
                    }
                }
                case 3 -> {
                    boolean loop = true;
                    List<String> updateDoctors = new ArrayList<>();
                    System.out.println("Enter Doctors IDs for this department (type 'q' to finish)");
                    while (loop) {
                        String docId = InputHandler.getStringInput("Doctor ID: ");
                        if (docId.equalsIgnoreCase("q")) {
                            loop = false;
                        } else {
                            if (DoctorService.checkIfIdDoctorExist(docId)) {
                                updateDoctors.add(docId);
                            } else {
                                System.out.println("Doctor ID does not exist. Please enter a valid Doctor ID");
                            }
                        }
                    }
                    selected.setDoctors(updateDoctors);
                    System.out.println("Doctors updated successfully");
                }
                case 4 -> {
                    boolean loop = true;
                    List<String> updateNurses = new ArrayList<>();
                    System.out.println("Enter Nurses IDs for this department (type 'q' to finish)");
                    while (loop) {
                        String nurseId = InputHandler.getStringInput("Nurse ID: ");
                        if (nurseId.equalsIgnoreCase("q")) {
                            loop = false;
                        } else {
                            if (NurseService.checkIfIdNurseExist(nurseId)) {
                                updateNurses.add(nurseId);
                            } else {
                                System.out.println("Nurse ID does not exist. Please enter a valid Nurse ID");
                            }
                        }
                    }
                    selected.setNurses(updateNurses);
                    System.out.println("Nurses updated successfully");
                }
                case 5 -> {
                    int newCapacity = InputHandler.getIntInput("Please, Enter new Bed Capacity");
                    selected.setBedCapacity(newCapacity);
                    System.out.println("Bed Capacity updated successfully");
                }
                case 6 -> {
                    int newAvailable = InputHandler.getIntInput("Please, Enter new Available Beds");
                    if (selected.getBedCapacity() != null && newAvailable > selected.getBedCapacity()) {
                        System.out.println("Available beds can't be greater than bed capacity. Update aborted.");
                    } else {
                        selected.setAvailableBeds(newAvailable);
                        System.out.println("Available Beds updated successfully");
                    }
                }
                case 7 -> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selected);
        return selected;
    }

    public void update(Department updatedDepartment) {
        if (updatedDepartment == null) {
            System.out.println("No updated to save");
            return;
        }
        for (int i = 0; i < departmentList.size(); i++) {
            if (departmentList.get(i).getDepartmentId().equalsIgnoreCase(updatedDepartment.getDepartmentId())) {
                departmentList.set(i, updatedDepartment);
                System.out.println("Department information updated successfully");
                return;
            }
        }
        System.out.println("Updated Department not found in list");
    }

    @Override
    public void validate(Department entity) {
        if (HelperUtils.isNull(entity)) {
            throw new IllegalArgumentException("Department object can't be null");
        }
        if (!HelperUtils.isValidString(entity.getDepartmentName())) {
            throw new IllegalArgumentException("Department Name can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getHeadDoctorId())) {
            throw new IllegalArgumentException("Head Doctor ID can't be empty");
        }
        if (!HelperUtils.isValidNumber(entity.getBedCapacity(), 0, 1000)) {
            throw new IllegalArgumentException("Bed Capacity must be a non-negative number");
        }
        if (HelperUtils.isNegative(entity.getAvailableBeds())) {
            throw new IllegalArgumentException("Available Beds must be a non-negative number");
        }
        if (entity.getAvailableBeds() > entity.getBedCapacity()) {
            throw new IllegalArgumentException("Available Beds can't exceed Bed Capacity");
        }
    }

    public String getDepartmentToRemove() {
        if (departmentList.isEmpty()) {
            System.out.println("There are no Departments");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Department ID to remove Department");
    }

    @Override
    public void remove(String id) {
        if (!HelperUtils.isValidString(id)) {
            System.out.println("No Department removed, Invalid Input");
            return;
        }
        if (checkIfIdDepartmentExist(id)) {
            departmentList.removeIf(d -> d.getDepartmentId().equals(id));
            System.out.println("Department removed successfully.");
        } else {
            System.out.println("Department not found");
        }
    }
    @Override
    public List<Department> getAll() {
        return new ArrayList<>(departmentList);
    }

    public void displayAllDepartment() {
        if (departmentList.isEmpty()) {
            System.out.println("There are no Departments Available");
            return;
        }
        System.out.println("The List of The Departments");
        for (Department d : departmentList) {
            d.displayInfo();
        }
    }

    @Override
    public void search() {
        if (departmentList.isEmpty()) {
            System.out.println("There are no Departments Available");
            return;
        }
        String name = InputHandler.getStringInput("Enter Department Name to search");
        boolean found = false;
        for (Department d : departmentList) {
            if (d.getDepartmentName().equalsIgnoreCase(name)) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Department found with this name");
        }
    }

    @Override
    public void searchById() {
        if (departmentList.isEmpty()) {
            System.out.println("There are no Departments Available");
            return;
        }
        String departmentId = InputHandler.getStringInput("Enter Department Id to search");
        boolean found = false;
        for (Department d : departmentList) {
            if (d.getDepartmentId().equalsIgnoreCase(departmentId)) {
                d.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Department found with this id");
        }
    }

    public void assignDoctorToDepartment(){
        if(departmentList.isEmpty()){
            System.out.println("There are no Departments Available");
            return;
        }
        String departmentId= InputHandler.getStringInput("Enter Department Id to assign Doctor");
        Department selectedDepartment=null;
        for(Department d:departmentList){
            if(d.getDepartmentId().equalsIgnoreCase(departmentId)){
                selectedDepartment=d;
                break;
            }
        }
        if(selectedDepartment==null){
            System.out.println("Department not found");
            return;
        }
        String doctorId= InputHandler.getStringInput("Enter Doctor Id to assign to Department");
        if(!DoctorService.checkIfIdDoctorExist(doctorId)){
            System.out.println("Doctor ID does not exist");
            return;
        }
        List<String> doctors = selectedDepartment.getDoctors();
        if (doctors == null) {
            doctors = new ArrayList<>();
        }

        if (doctors.contains(doctorId)) {
            System.out.println("Doctor is already assigned to this Department");
            return;
        }

        doctors.add(doctorId);
        selectedDepartment.setDoctors(doctors);
        Doctor doctor = DoctorService.getDoctorById(doctorId);
        if (doctor != null) {
            doctor.setDepartmentId(departmentId);
        }

        System.out.println("Doctor assigned to Department successfully");
    }

    public void assignNurseToDepartment(){
        if(departmentList.isEmpty()){
            System.out.println("There are no Departments Available");
            return;
        }
        String departmentId=InputHandler.getStringInput("Enter Department Id to assign Nurse");
        Department selectedDepartment=null;
        for(Department d:departmentList){
            if(d.getDepartmentId().equals(departmentId)){
                selectedDepartment=d;
                break;
            }
        }
        if(selectedDepartment==null){
            System.out.println("Department not found");
            return;
        }
        String nurseId=InputHandler.getStringInput("Enter Nurse Id to assign to Department");
        if(!NurseService.checkIfIdNurseExist(nurseId)){
            System.out.println("Nurse ID does not exist");
            return;
        }
        List<String> nurseList = selectedDepartment.getNurses();
        if (nurseList == null) {
            nurseList = new ArrayList<>();
        }

        if (nurseList.contains(nurseId)) {
            System.out.println("Nurse is already assigned to this Department");
            return;
        }

        nurseList.add(nurseId);
        selectedDepartment.setNurses(nurseList);
        System.out.println("Nurse assigned to Department successfully");

        Nurse nurse = NurseService.getNurseById(nurseId);
        if (nurse != null) {
            nurse.setDepartmentId(departmentId);
        }
    }

    public void viewDepartmentDetails(){
        String departmentId= InputHandler.getStringInput("Enter Department Id to view details");
        Department department=departmentList.stream().filter(d->d.getDepartmentId().equals(departmentId)).findFirst().orElse(null);
        if(department==null){
            System.out.println("Department not found");
            return;
        }
        System.out.println("Department Details:");
        System.out.println("Department ID: " + department.getDepartmentId());
        System.out.println("Department Name: " + department.getDepartmentName());
        System.out.println("Head Doctor ID: " +  department.getHeadDoctorId());
        System.out.println("Number of Doctors: " + department.getDoctors().size());
        System.out.println("Number of Nurses: " + department.getNurses().size());
    }

    public void  viewDepartmentStatistics(){
        System.out.println("Total Number of Departments: " + departmentList.size());
        Department maxDoctors=departmentList.stream().max(Comparator.comparingInt(d->d.getDoctors().size())).orElse(null);
        if(maxDoctors!=null){
            System.out.println("Department with Maximum Doctors: " + maxDoctors.getDepartmentName() + " (Numbers of Doctors: " + maxDoctors.getDoctors().size() + ")");
        }
        Department maxNurses=departmentList.stream().max(Comparator.comparingInt(n->n.getNurses().size())).orElse(null);
        if(maxNurses!=null){
            System.out.println("Department with Maximum Nurses: " + maxNurses.getDepartmentName() + " (Numbers of Nurses: " + maxNurses.getNurses().size() + ")");
            }
    }

    public static Boolean checkIfIdDepartmentExist(String idDepartment) {
        for (Department d : departmentList) {
            if (d.getDepartmentId().equals(idDepartment)) {
                return true;
            }
        }
        return false;
    }

    public static void addSampleDepartments() {
        DoctorService doctorService=new DoctorService();
        List<Doctor> doctors= doctorService.getAll();

        for (int i = 1; i <= 5; i++) {
            Department dept = new Department();
            dept.setDepartmentId(HelperUtils.generateId("DEP", 6));
            dept.setDepartmentName("Department " + i);

            if (!doctors.isEmpty()) {
                int randomIndex = new Random().nextInt(doctors.size());
                dept.setHeadDoctorId(doctors.get(randomIndex).getDoctorId());
            } else {
                dept.setHeadDoctorId("NONE");
            }

            int bedCapacity = 50 + i * 10;
            dept.setBedCapacity(bedCapacity);

            int availableBeds = bedCapacity - (i * 5);
            dept.setAvailableBeds(Math.max(0, availableBeds));
            departmentList.add(dept);

        }
    }

}

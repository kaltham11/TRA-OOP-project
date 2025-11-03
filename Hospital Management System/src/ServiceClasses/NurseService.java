package ServiceClasses;

import EntityClasses.MedicalRecord;
import EntityClasses.Nurse;
import EntityClasses.Gender;
import EntityClasses.Shift;
import InterfaceClasses.Editable;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NurseService implements Manageable<Nurse>, Searchable, Editable<Nurse> {
    private static final List<Nurse> nurseList = new ArrayList<>();

    @Override
    public Nurse add() {
        Nurse nurse = new Nurse();
        nurse.setId(HelperUtils.generateId("PER", 8));
        nurse.setNurseId(HelperUtils.generateId("NUR", 8));
        nurse.setFirstName(InputHandler.getStringInput("Enter a First Name for the Nurse"));
        nurse.setLastName(InputHandler.getStringInput("Enter a Last Name for the Nurse"));
        nurse.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Nurse in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        nurse.setGender(selectedGender);

        nurse.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Nurse"));
        nurse.setEmail(InputHandler.getStringInput("Enter the email for the Nurse"));
        nurse.setAddress(InputHandler.getStringInput("Enter the address for the Nurse"));

        System.out.println("Select Shift");
        for (Shift s : Shift.values()) {
            System.out.println((s.ordinal() + 1) + "." + s);
        }
        int shiftOption = InputHandler.getIntInput("Enter option(1-" + Shift.values().length + "): ",
                1, Shift.values().length);
        Shift selectedShift = Shift.values()[shiftOption - 1];
        nurse.setShift(selectedShift);

        nurse.setQualification(InputHandler.getStringInput("Enter Qualification for Nurse"));
        validate(nurse);
        return nurse;
    }

    public void save(Nurse nurse) {
        if (HelperUtils.isNotNull(nurse)) {
            nurseList.add(nurse);
            System.out.println("The nurse information added successfully");
        } else {
            throw new IllegalArgumentException("Nurse object can't be null");
        }
    }

    @Override
    public Nurse edit() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses");
            return null;
        }
        String id = InputHandler.getStringInput("Please, Enter Nurse ID to edit");
        Nurse selectedNurse = null;
        for (Nurse n : nurseList) {
            if (n.getNurseId().equals(id)) {
                selectedNurse = n;
                break;
            }
        }
        if (selectedNurse == null) {
            System.out.println("Nurse not found");
            return null;
        }

        boolean editingFlag = true;
        while (editingFlag) {
            System.out.println("""
                    ==============================================
                    Enter the option number to edit it
                    1- First Name
                    2- Last Name
                    3- Date of Birth
                    4- Gender
                    5- Phone Number
                    6- Email
                    7- Address
                    8- Shift
                    9- Qualification
                    10- Assigned Patients
                    11- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    selectedNurse.setFirstName(InputHandler.getStringInput("Please, Enter a new First Name"));
                    System.out.println("First Name updated successfully");
                }
                case 2 -> {
                    selectedNurse.setLastName(InputHandler.getStringInput("Please, Enter a new Last Name"));
                    System.out.println("Last Name updated successfully");
                }
                case 3 -> {
                    LocalDate dob = InputHandler.getDateInput("Enter Date of Birth in format (YYYY-MM-DD)");
                    if (HelperUtils.isPastDate(dob) || HelperUtils.isToday(dob)) {
                        selectedNurse.setDateOfBirth(dob);
                        System.out.println("Date of Birth updated successfully");
                    } else {
                        System.out.println("Invalid date. Please ensure the date is in the past and in the correct format.");
                    }
                }
                case 4 -> {
                    System.out.println("Select Gender");
                    for (Gender g : Gender.values()) {
                        System.out.println((g.ordinal() + 1) + "." + g);
                    }
                    int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                            1, Gender.values().length);
                    Gender slectedGender = Gender.values()[genderOption - 1];
                    selectedNurse.setGender(slectedGender);
                    System.out.println("Gender updated successfully");
                }
                case 5 -> {
                    selectedNurse.setPhoneNumber(InputHandler.getStringInput("Please, Enter a new Phone Number"));
                    System.out.println("Phone Number updated successfully");
                }
                case 6 -> {
                    selectedNurse.setEmail(InputHandler.getStringInput("Please, Enter a new Email"));
                    System.out.println("Email updated successfully");
                }
                case 7 -> {
                    selectedNurse.setAddress(InputHandler.getStringInput("Please, Enter a new Address"));
                    System.out.println("Address updated successfully");
                }
                case 8 -> {
                    System.out.println("Select Shift");
                    for (Shift s : Shift.values()) {
                        System.out.println((s.ordinal() + 1) + "." + s);
                    }
                    int shiftOption = InputHandler.getIntInput("Enter option(1-" + Shift.values().length + "): ",
                            1, Shift.values().length);
                    Shift selectedShift = Shift.values()[shiftOption - 1];
                    selectedNurse.setShift(selectedShift);
                    System.out.println("Shift updated successfully");
                }
                case 9 -> {
                    selectedNurse.setQualification(InputHandler.getStringInput("Please, Enter a new Qualification"));
                    System.out.println("Qualification updated successfully");
                }
                case 10 -> {
                    boolean flag2 = true;
                    System.out.println("Please, Enter assigned Patients (type 'q' when finished)");
                    List<String> updateAssigned = new ArrayList<>();
                    while (flag2) {
                        String patientId = InputHandler.getStringInput("Assigned Patient ID: ");
                        if (patientId.equalsIgnoreCase("q")) {
                            flag2 = false;
                        } else {
                            if (PatientService.checkIfIdPatientExit(patientId)) {
                                updateAssigned.add(patientId);
                            } else {
                                System.out.println("Patient ID does not exist. Please enter a valid Patient ID");
                            }
                        }
                    }
                    selectedNurse.setAssignedPatients(updateAssigned);
                    System.out.println("Assigned Patients updated successfully");
                }
                case 11-> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selectedNurse);
        return selectedNurse;
    }

    @Override
    public void validate(Nurse entity) {
        if (HelperUtils.isNull(entity)) {
            throw new IllegalArgumentException("Nurse object can't be null");
        }
        if (!HelperUtils.isValidString(entity.getFirstName())) {
            throw new IllegalArgumentException("First Name can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getLastName())) {
            throw new IllegalArgumentException("Last Name can't be empty");
        }
        if (!HelperUtils.isValidDate(entity.getDateOfBirth())) {
            throw new IllegalArgumentException("Date Birth can't be empty");
        }
        if (entity.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of Birth can't be in the future");
        }
        if (!HelperUtils.isValidString(entity.getGender())) {
            throw new IllegalArgumentException("Gender can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone Number can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getQualification())) {
            throw new IllegalArgumentException("Qualification can't be empty");
        }
    }

    public void update(Nurse updatedNurse) {
        if (updatedNurse == null) {
            System.out.println("No updated to save");
            return;
        }
        for (int i = 0; i < nurseList.size(); i++) {
            if (nurseList.get(i).getNurseId().equalsIgnoreCase(updatedNurse.getNurseId())) {
                nurseList.set(i, updatedNurse);
                System.out.println("Nurse information updated successfully");
                return;
            }
        }
        System.out.println("Updated Nurse not found in list");
    }

    public String getNurseToRemove() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Nurse ID to remove Nurse");
    }

    @Override
    public void remove(String removeNurseById) {
        if (!HelperUtils.isValidString(removeNurseById)) {
            System.out.println("No Nurse removed, Invalid Input");
            return;
        }
        if (checkIfIdNurseExist(removeNurseById)) {
            nurseList.removeIf(n -> n.getNurseId().equals(removeNurseById));
            System.out.println("Nurse removed successfully.");
        } else {
            System.out.println("Nurse not found");
        }

    }


    @Override
    public List<Nurse> getAll() {
        return new ArrayList<>(nurseList);
    }

    public void displayAllNurse() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses Available");
            return;
        }
        System.out.println("The List of The Nurses");
        for (Nurse n : nurseList) {
            n.displayInfo();
        }
    }


    public void assignNurseToPatient(){
        if(nurseList.isEmpty()){
            System.out.println("There are no Nurses Available");
            return;
        }
        String nurseId=InputHandler.getStringInput("Enter Nurse ID to assign Patient");
        Nurse selectedNurse=null;
        for(Nurse n:nurseList) {
            if (n.getNurseId().equalsIgnoreCase(nurseId)) {
                selectedNurse = n;
                break;
            }
        }
            if(selectedNurse==null){
                System.out.println("Nurse not found");
                return;
            }
            boolean flag=true;
            List<String> assignedPatients=selectedNurse.getAssignedPatients();
            while (flag){
                String patientId=InputHandler.getStringInput("Enter Patient ID to assign (type 'q' to finish)");
                if(patientId.equalsIgnoreCase("q")){
                    flag=false;
                }else{
                    if(PatientService.checkIfIdPatientExit(patientId)){
                        assignedPatients.add(patientId);
                        System.out.println("Patient assigned successfully");
                    }else{
                        System.out.println("Patient ID does not exist. Please enter a valid Patient ID");
                    }
                }
            }
            selectedNurse.setAssignedPatients(assignedPatients);
        }

    @Override
    public void search() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses Available");
            return;
        }

        String departmentId = InputHandler.getStringInput("Enter department ID to search");

        boolean found = false;
        for (Nurse n : nurseList) {
            if (n.getDepartmentId().equalsIgnoreCase(departmentId)) {
                n.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Nurse found with this qualification");
        }
    }


    @Override
    public void searchById() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses Available");
            return;
        }
        String nurseId = InputHandler.getStringInput("Enter Nurse Id to search");

        boolean found = false;
        for (Nurse n : nurseList) {
            if (n.getNurseId().equalsIgnoreCase(nurseId)) {
                n.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Nurse found with this id");
        }
    }

    public void searchByShift() {
        if (nurseList.isEmpty()) {
            System.out.println("There are no Nurses Available");
            return;
        }

        System.out.println("Select Shift to search");
        for(Shift s:Shift.values()){
            System.out.println((s.ordinal()+1)+"."+s);
        }
        int shiftOption = InputHandler.getIntInput("Enter option(1-" + Shift.values().length + "): ",
                1, Shift.values().length);
        Shift selectedShift = Shift.values()[shiftOption - 1];


        boolean found = false;
        for (Nurse n : nurseList) {
            if (n.getShift().equals(selectedShift)) {
                n.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Nurse found with this qualification");
        }
    }

    public static Boolean checkIfIdNurseExist(String idNurse) {
        for (Nurse n : nurseList) {
            if (n.getNurseId().equals(idNurse)) {
                return true;
            }
        }
        return false;
    }

}


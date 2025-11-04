package ServiceClasses;

import EntityClasses.Doctor;
import EntityClasses.Gender;
import EntityClasses.MedicalRecord;
import InterfaceClasses.Editable;
import Utils.HelperUtils;
import Utils.InputHandler;
import EntityClasses.Patient;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientService implements Manageable<Patient>, Searchable, Editable<Patient> {
    private static final List<Patient> patientList =new ArrayList<>();


    @Override
    public Patient add() {
        boolean flag=true;
        Patient patient=new Patient();
        patient.setId(HelperUtils.generateId("PER", 8));
        patient.setPatientId(HelperUtils.generateId("PAT",8));
        patient.setFirstName(InputHandler.getStringInput("Enter a First Name for the Patient"));
        patient.setLastName(InputHandler.getStringInput("Enter a Last Name for the Patient"));
        patient.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Patient in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for(Gender g:Gender.values()){
            System.out.println((g.ordinal()+1)+"."+g);
        }
        int genderOption=InputHandler.getIntInput("Enter option(1-"+Gender.values().length+"): ",
                1,Gender.values().length);
        Gender slectedGender=Gender.values()[genderOption -1];
        patient.setGender(slectedGender);

        patient.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Patient"));
        patient.setEmail(InputHandler.getStringInput("Enter the email for the Patient"));
        patient.setAddress(InputHandler.getStringInput("Enter the address for the Patient"));
        patient.setBloodGroup(InputHandler.getStringInput("Enter Blood Group (A+, A-, B+, B-, AB+, AB-, O+, O-)"));
        List<String> allergies=new ArrayList<>();
        System.out.println("Enter allergies of the Patient (type 'q' to finish)");
        while (flag) {
            String allergy = InputHandler.scanner.nextLine();
            if (allergy.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                allergies.add(allergy);
                System.out.println("Please enter (q+Enter) if you done otherwise press Enter to add more");
            }
        }
            patient.setAllergies(allergies);

        patient.setEmergencyContact(InputHandler.getStringInput("Enter emergency Contact"));
        patient.setInsuranceId(InputHandler.getStringInput("Enter Insurance ID"));
        patient.setRegistrationDate(LocalDate.now());
        validate(patient);
       return patient;
    }
    public void save(Patient patient){
        if(HelperUtils.isNotNull(patient)) {
            patientList.add(patient);
            System.out.println("The Patient information added successfully");
        }else {
            throw new IllegalArgumentException("Patient object can't be null");
        }
    }

    @Override
    public Patient edit() {
        if(patientList.isEmpty()){
            System.out.println("There are no Patient");
            return null;
        }
        String id=InputHandler.getStringInput("Please, Enter Patient ID to edit");
        Patient selectedPatient=null;
        for(Patient editPatient: patientList){
            if(editPatient.getPatientId().equals(id)){
                selectedPatient=editPatient;
                break;
            }
        }
        if(HelperUtils.isNull(selectedPatient)){
            System.out.println("Patient not found");
            return null;
        }
        boolean editingFlag=true;
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
                    8-Blood Group
                    9-Allergies
                    10-Emergency Contact
                    11-Insurance ID
                    12- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    selectedPatient.setFirstName(InputHandler.getStringInput("Please, Enter a new First Name"));
                    System.out.println("First Name updated successfully");
                }
                case 2 -> {
                    selectedPatient.setLastName(InputHandler.getStringInput("Please, Enter a new Last Name"));
                    System.out.println("Last Name updated successfully");
                }
                case 3 -> {

                        LocalDate dob = InputHandler.getDateInput("Enter Date of Birth in format (YYYY-MM-DD)");
                        if (HelperUtils.isPastDate(dob)||HelperUtils.isToday(dob)) {
                                selectedPatient.setDateOfBirth(dob);
                                System.out.println("Date of Birth updated successfully");
                        } else {
                            System.out.println("Invalid date. Please ensure the date is in the past and in the correct format.");
                        }
                }
                case 4 -> {
                    System.out.println("Select Gender");
                    for(Gender g: Gender.values()){
                        System.out.println((g.ordinal()+1)+"."+g);
                    }
                    int genderOption=InputHandler.getIntInput("Enter option(1-"+Gender.values().length+"): ",
                            1,Gender.values().length);
                    Gender slectedGender=Gender.values()[genderOption -1];
                    selectedPatient.setGender(slectedGender);
                    System.out.println("Gender updated successfully");
                }
                case 5 -> {
                    selectedPatient.setPhoneNumber(InputHandler.getStringInput("Please, Enter a new Phone Number"));
                    System.out.println("Phone Number updated successfully");
                }
                case 6 -> {
                    selectedPatient.setEmail(InputHandler.getStringInput("Please, Enter a new Email"));
                    System.out.println("Email updated successfully");
                }
                case 7 -> {
                    selectedPatient.setAddress(InputHandler.getStringInput("Please, Enter a new Address"));
                    System.out.println("Address updated successfully");
                }
                case 8 -> {
                    selectedPatient.setBloodGroup(InputHandler.getStringInput("Please, Enter a new Blood Group"));
                    System.out.println("Blood Group updated successfully");
                }
                case 9 -> {
                    boolean flag=true;
                    System.out.println("Enter allergies of the Patient (type 'q' to finish)");
                    List<String> updateAllergies = new ArrayList<>();
                    while (flag) {
                        String allergy = InputHandler.getStringInput("Allergy: ");

                        if (allergy.equalsIgnoreCase("q")) {
                            flag = false;

                        } else {
                            updateAllergies.add(allergy);
                            System.out.println("Please enter (q+Enter) if you done otherwise to continue press (Enter)");
                        }
                    }
                    selectedPatient.setAllergies(updateAllergies);
                    System.out.println("Allergies updated successfully");
                }
                case 10 -> {
                    selectedPatient.setEmergencyContact(InputHandler.getStringInput("Please, Enter a new Emergency Contact"));
                    System.out.println("Emergency Contact updated successfully");
                }
                case 11 -> {
                    selectedPatient.setInsuranceId(InputHandler.getStringInput("Please, Enter a new Insurance ID"));
                    System.out.println("Insurance ID updated successfully");
                }
                case 12 -> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selectedPatient);
        return selectedPatient;
    }

    @Override
    public void validate(Patient entity) {
        if(HelperUtils.isNull(entity)){
            throw new IllegalArgumentException("Patient object can't be null");
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
        if(entity.getDateOfBirth().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date of Birth can't be in the future");
        }
        if (!HelperUtils.isValidString(entity.getGender())) {
            throw new IllegalArgumentException("Gender can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getPhoneNumber())){
            throw new IllegalArgumentException("Phone Number can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getBloodGroup())) {
            throw new IllegalArgumentException("Blood Group can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getEmergencyContact())) {
            throw new IllegalArgumentException("Emergency Contact can't be empty");
        }
    }

    public  void update(Patient updatedPatient){
        if(updatedPatient==null){
            System.out.println("No updated to save");
            return;
        }
        for(int i=0;i<patientList.size();i++){
            if(patientList.get(i).getPatientId().equalsIgnoreCase(updatedPatient.getPatientId())){
             patientList.set(i,updatedPatient);
                System.out.println("Patient information updated successfully");
                return;
            }
        }
        System.out.println("Updated Patient not found in list");
    }

   public String getPatientToRemove(){
        if(patientList.isEmpty()){
            System.out.println("There are no Patient");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Patient ID to remove Patient");
   }



    @Override
    public void remove(String removePatientById){
        if(!HelperUtils.isValidString(removePatientById)) {
            System.out.println("No Patient removed, Invalid Input");
            return;
        }
       if (checkIfIdPatientExit(removePatientById)) {
           patientList.removeIf(p -> p.getPatientId().equals(removePatientById));
           System.out.println("Patient removed successfully.");
       } else {
           System.out.println("Patient not found");
       }

   }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(patientList);
    }
    public void displayAllPatient() {
        if (patientList.isEmpty()) {
            System.out.println("There are no Patient Available");
            return;
        }
        System.out.println("The List of The Patient");
        for (Patient p : patientList) {
            p.displayInfo();
        }
    }


    @Override
    public void search(){
        if (patientList.isEmpty()) {
            System.out.println("There are no Patient Available");
            return;
        }

        String patientName=InputHandler.getStringInput("Enter Patient First Name to search");



        boolean found=false;
        for (Patient p : patientList) {
            if(p.getFirstName().equalsIgnoreCase(patientName)){
                p.displayInfo();
                found=true;
            }
        }
        if(!found){
            System.out.println("No Patient found with this name");
        }
    }

    @Override
    public void searchById() {
        if (patientList.isEmpty()) {
            System.out.println("There are no Patient Available");
            return;
        }
        String patientId=InputHandler.getStringInput("Enter Patient Id to search");



        boolean found=false;
        for (Patient p : patientList) {
            if(p.getPatientId().equalsIgnoreCase(patientId)){
                p.displayInfo();
                found=true;
            }
        }
        if(!found){
            System.out.println("No Patient found with this name");
        }
    }


    public static Boolean checkIfIdPatientExit(String idPatient) {
        for (Patient p : patientList) {
            if (p.getPatientId().equals(idPatient)) {
                return true;
            }
        }
        return false;
    }
    public static Patient getPatientById(String id) {
        for (Patient p : patientList) {
            if (p.getPatientId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public Patient addPatient(String firstName, String lastName, String phone){
        Patient patient=new Patient();
        patient.setId(HelperUtils.generateId("PER", 8));
        patient.setPatientId(HelperUtils.generateId("PAT",8));
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setRegistrationDate(LocalDate.now());
        validate(patient);
        return patient;
    }
 public Patient addPatient(String firstName, String lastName, String phone, String bloodGroup, String email){
        Patient patient=new Patient();
        patient.setId(HelperUtils.generateId("PER", 8));
        patient.setPatientId(HelperUtils.generateId("PAT",8));
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhoneNumber(phone);
        patient.setBloodGroup(bloodGroup);
        patient.setEmail(email);
        patient.setRegistrationDate(LocalDate.now());
        validate(patient);
        return patient;
    }

 public Patient addPatient(Patient patient) {
     validate(patient);
     return patient;
 }

public void searchPatients(String keyword) {
    if (patientList.isEmpty()) {
        System.out.println("There are no Patient Available");
        return;
    }

    if (HelperUtils.isNull(keyword) ||!HelperUtils.isValidString(keyword)) {
        System.out.println("Keyword is empty. Displaying all patients:");
    }

    String lowerKeyword = keyword.toLowerCase();
    boolean found = false;

    for (Patient p : patientList) {
        if ((p.getFirstName() != null && p.getFirstName().toLowerCase().contains(lowerKeyword)) ||
                (p.getLastName() != null && p.getLastName().toLowerCase().contains(lowerKeyword)) ||
                (p.getPatientId() != null && p.getPatientId().toLowerCase().contains(lowerKeyword)) ||
                (p.getEmergencyContact() != null && p.getEmergencyContact().contains(lowerKeyword)) ||
                (p.getPhoneNumber() != null && p.getPhoneNumber().contains(lowerKeyword)) ||
                (p.getGender() != null && p.getGender().toString().toLowerCase().contains(lowerKeyword))) {

            p.displayInfo();
            found = true;
        }
    }

    if (!found) {
        System.out.println("No patients found matching");
    } else {
        System.out.println("Search completed");
    }
}

public void searchPatients(String firstName, String lastName){
    if (patientList.isEmpty()) {
        System.out.println("There are no Patient Available");
        return;
    }

    boolean found = false;

    for (Patient p : patientList) {
        if ((p.getFirstName() != null && p.getFirstName().equalsIgnoreCase(firstName)) &&
                (p.getLastName() != null && p.getLastName().equalsIgnoreCase(lastName))) {

            p.displayInfo();
            found = true;
        }
    }

    if (!found) {
        System.out.println("No patients found matching");
    } else {
        System.out.println("Search completed");
    }
}
public void displayPatients(){
    if (patientList.isEmpty()) {
        System.out.println("There are no Patient Available");
        return;
    }
    System.out.println("The List of The Patient");
    for (Patient p : patientList) {
        p.displayInfo();
    }
}
 public void displayPatients(String filter){
         if (patientList.isEmpty()) {
             System.out.println("There are no Patients Available");
             return;
         }

         if (HelperUtils.isNull(filter) ||!HelperUtils.isValidString(filter)) {
             System.out.println("Filter is empty. Displaying all patients:");
         }

         String lowerFilter = filter.toLowerCase();
         boolean found = false;

         for (Patient p : patientList) {
             if ((p.getFirstName() != null && p.getFirstName().toLowerCase().contains(lowerFilter)) ||
                        (p.getLastName() != null && p.getLastName().toLowerCase().contains(lowerFilter)) ||
                     (p.getPatientId() != null && p.getPatientId().toLowerCase().contains(lowerFilter)) ||
                     (p.getEmergencyContact() != null && p.getEmergencyContact().contains(lowerFilter)) ||
                     (p.getPhoneNumber() != null && p.getPhoneNumber().contains(lowerFilter)) ||
                        (p.getGender() != null && p.getGender().toString().toLowerCase().contains(lowerFilter))) {

                 p.displayInfo();
                 found = true;
             }
         }

         if (!found) {
             System.out.println("No patients found matching");
         } else {
             System.out.println("Search completed");
         }
     }

 public void displayPatients(int limit){
     if (patientList.isEmpty()) {
         System.out.println("There are no Patient Available");
         return;
     }
     if(HelperUtils.isNegative(limit)){
            System.out.println("Invalid limit number");
     }else {
         System.out.println("The List of The Patient");
         for (int i = 0; i < limit && i < patientList.size(); i++) {
             Patient p = patientList.get(i);
             p.displayInfo();
         }
     }
 }
}

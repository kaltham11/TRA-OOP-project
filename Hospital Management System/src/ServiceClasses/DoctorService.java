package ServiceClasses;

import EntityClasses.*;
import InterfaceClasses.Editable;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorService implements Manageable<Doctor>, Searchable, Editable<Doctor> {
    public static List<Doctor> doctorList =new ArrayList<>();
    @Override
    public Doctor add() {
        Doctor doctor=new Doctor();
        doctor.setId(HelperUtils.generateId("PER", 8));
        doctor.setDoctorId(HelperUtils.generateId("Doc",8));
        doctor.setFirstName(InputHandler.getStringInput("Enter a First Name for the Doctor"));
        doctor.setLastName(InputHandler.getStringInput("Enter a Last Name for the Doctor"));
        doctor.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Doctor in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for(Gender g:Gender.values()){
            System.out.println((g.ordinal()+1)+"."+g);
        }
        int genderOption=InputHandler.getIntInput("Enter option(1-"+Gender.values().length+"): ",
                1,Gender.values().length);
        Gender slectedGender=Gender.values()[genderOption -1];
        doctor.setGender(slectedGender);

        doctor.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Doctor"));
        doctor.setEmail(InputHandler.getStringInput("Enter the email for the Doctor"));
        doctor.setAddress(InputHandler.getStringInput("Enter the address for the Doctor"));
        doctor.setSpecialization(InputHandler.getStringInput("Enter Specialization for Doctor"));
        doctor.setQualification(InputHandler.getStringInput("Enter Qualification for Doctor"));
        doctor.setExperienceYears(InputHandler.getIntInput("Enter Experience Years for Doctor"));
        doctor.setConsultationFee(InputHandler.getDoubleInput("Enter Consultation Fee for Doctor"));
        List<String>availableSlots=new ArrayList<>();
        System.out.println("Enter available slots for Doctor like (Monday 10AM, or Wednesday 3PM) (type 'q' when finished):");
        boolean flag=true;
        while (flag){
            String slot= InputHandler.getStringInput("Enter available slot: ");
            if(slot.equalsIgnoreCase("q")){
                flag=false;
            }else {
                availableSlots.add(slot);
            }
        }
        doctor.setAvailableSlots(availableSlots);

        validate(doctor);
        return doctor;
    }
    public void save(Doctor doctor){
        if(HelperUtils.isNotNull(doctor)) {
            doctorList.add(doctor);
            System.out.println("The doctor information added successfully");
        }else {
            throw new IllegalArgumentException("Doctor object can't be null");
        }
    }
    @Override
    public Doctor edit() {
        if (doctorList.isEmpty()){
            System.out.println("There are no Doctors");
            return null;
        }
        String id = InputHandler.getStringInput("Please, Enter Doctor ID to edit");
        Doctor selectedDoctor = null;
        for (Doctor d: doctorList){
            if (d.getDoctorId().equals(id)){
                selectedDoctor = d;
                break;
            }
        }
        if (HelperUtils.isNull(selectedDoctor)){
            System.out.println("Doctor not found");
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
                    8- Specialization
                    9- Qualification
                    10- Experience Years
                    11- Consultation Fee
                    12- Available Slots
                    13- Assigned Patients
                    14- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    selectedDoctor.setFirstName(InputHandler.getStringInput("Please, Enter a new First Name"));
                    System.out.println("First Name updated successfully");
                }
                case 2 -> {
                    selectedDoctor.setLastName(InputHandler.getStringInput("Please, Enter a new Last Name"));
                    System.out.println("Last Name updated successfully");
                }
                case 3 -> {
                    LocalDate dob = InputHandler.getDateInput("Enter Date of Birth in format (YYYY-MM-DD)");
                    if (HelperUtils.isPastDate(dob) || HelperUtils.isToday(dob)) {
                        selectedDoctor.setDateOfBirth(dob);
                        System.out.println("Date of Birth updated successfully");
                    } else {
                        System.out.println("Invalid date. Please ensure the date is in the past and in the correct format.");
                    }
                }
                case 4 -> {
                    System.out.println("Select Gender");
                    for(Gender g:Gender.values()){
                        System.out.println((g.ordinal()+1)+"."+g);
                    }
                    int genderOption=InputHandler.getIntInput("Enter option(1-"+Gender.values().length+"): ",
                            1,Gender.values().length);
                    Gender slectedGender=Gender.values()[genderOption -1];
                    selectedDoctor.setGender(slectedGender);
                    System.out.println("Gender updated successfully");
                }
                case 5 -> {
                    selectedDoctor.setPhoneNumber(InputHandler.getStringInput("Please, Enter a new Phone Number"));
                    System.out.println("Phone Number updated successfully");
                }
                case 6 -> {
                    selectedDoctor.setEmail(InputHandler.getStringInput("Please, Enter a new Email"));
                    System.out.println("Email updated successfully");
                }
                case 7 -> {
                    selectedDoctor.setAddress(InputHandler.getStringInput("Please, Enter a new Address"));
                    System.out.println("Address updated successfully");
                }
                case 8 -> {
                    selectedDoctor.setSpecialization(InputHandler.getStringInput("Please, Enter a new Specialization"));
                    System.out.println("Specialization updated successfully");
                }
                case 9 -> {
                    selectedDoctor.setQualification(InputHandler.getStringInput("Please, Enter a new Qualification"));
                    System.out.println("Qualification updated successfully");
                }
                case 10 -> {
                    selectedDoctor.setExperienceYears(InputHandler.getIntInput("Please, Enter new Experience Years"));
                    System.out.println("Experience Years updated successfully");
                }
                case 11 -> {
                    selectedDoctor.setConsultationFee(InputHandler.getDoubleInput("Please, Enter new Consultation Fee"));
                    System.out.println("Consultation Fee updated successfully");
                }
                case 12 -> {
                    boolean flag=true;
                    System.out.println("Please, Enter available slots like (Monday 10AM, or Wednesday 3PM)(type 'q' when finished)");
                    List<String> updateSlots=new ArrayList<>();
                    while (flag){
                        String slot=InputHandler.getStringInput("Available Slot: ");
                        if(slot.equalsIgnoreCase("q")){
                            flag=false;
                        }else{
                            updateSlots.add(slot);
                        }
                    }
                    selectedDoctor.setAvailableSlots(updateSlots);
                    System.out.println("Available Slots updated successfully");
                }
                case 13 -> {
                    boolean flag2=true;
                    System.out.println("Please, Enter assigned Patients (type 'q' when finished)");
                    List<String> updateAssigned=new ArrayList<>();
                    while (flag2){
                        String patientId=InputHandler.getStringInput("Assigned Patient ID: ");
                        if(patientId.equalsIgnoreCase("q")){
                            flag2=false;
                        }else{
                            if(PatientService.checkIfIdPatientExit(patientId)){
                                updateAssigned.add(patientId);
                            }else{
                                System.out.println("Patient ID does not exist. Please enter a valid Patient ID");
                            }
                        }
                    }
                    selectedDoctor.setAssignedPatients(updateAssigned);
                    System.out.println("Assigned Patients updated successfully");
                }
                case 14 -> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selectedDoctor);
        return selectedDoctor;
    }

    @Override
    public void validate(Doctor entity) {
        if(HelperUtils.isNull(entity)){
            throw new IllegalArgumentException("Doctor object can't be null");
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
        if(!HelperUtils.isValidString(entity.getSpecialization())){
            throw new IllegalArgumentException("Specialization can't be empty");
        }
        if(!HelperUtils.isValidString(entity.getQualification())){
            throw new IllegalArgumentException("Qualification can't be empty");
        }
        if(HelperUtils.isNegative(entity.getExperienceYears())){
            throw new IllegalArgumentException("Experience Years must be a non-negative number");
        }
        if(HelperUtils.isNegative(entity.getConsultationFee())){
            throw new IllegalArgumentException("Consultation Fee must be a non-negative number");
        }

    }



    public void update(Doctor updatedDoctor){
        if(updatedDoctor==null){
            System.out.println("No updated to save");
            return;
        }
        for(int i=0;i<doctorList.size();i++){
            if(doctorList.get(i).getDoctorId().equalsIgnoreCase(updatedDoctor.getDoctorId())){
                doctorList.set(i,updatedDoctor);
                System.out.println("Doctor information updated successfully");
                return;
            }
        }
        System.out.println("Updated Doctor not found in list");
    }

    public String getDoctorToRemove(){
        if(doctorList.isEmpty()){
            System.out.println("There are no Doctors");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Doctor ID to remove Doctor");
    }

    @Override
    public void remove(String removeDoctorById) {
        if(!HelperUtils.isValidString(removeDoctorById)) {
            System.out.println("No Doctor removed, Invalid Input");
            return;
        }
       if (checkIfIdDoctorExist(removeDoctorById)) {
           doctorList.removeIf(d -> d.getDoctorId().equals(removeDoctorById));
           System.out.println("Doctor removed successfully.");
       } else {
           System.out.println("Doctor not found");
       }

    }
    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(doctorList);
    }

    public void displayAllDoctor() {
        if (doctorList.isEmpty()) {
            System.out.println("There are no Doctors Available");
            return;
        }
        System.out.println("The List of The Doctors");
        for (Doctor d : doctorList) {
            d.displayInfo();
        }
    }


    @Override
    public void search() {
        if (doctorList.isEmpty()) {
            System.out.println("There are no Doctors Available");
            return;
        }

        String doctorSpecialization=InputHandler.getStringInput("Enter Doctor Specialization search");

        boolean found=false;
        for (Doctor d : doctorList) {
            if(d.getSpecialization().equalsIgnoreCase(doctorSpecialization)){
                d.displayInfo();
                found=true;
            }
        }
        if(!found){
            System.out.println("No Doctor found with this name");
        }
    }

    @Override
    public void searchById() {
        if (doctorList.isEmpty()) {
            System.out.println("There are no Doctors Available");
            return;
        }
        String doctorId=InputHandler.getStringInput("Enter Doctor Id to search");

        boolean found=false;
        for (Doctor d : doctorList) {
            if(d.getDoctorId().equalsIgnoreCase(doctorId)){
                d.displayInfo();
                found=true;
            }
        }
        if(!found){
            System.out.println("No Doctor found with this id");
        }
    }

    public void viewAvailableDoctors(){
        if (doctorList.isEmpty()) {
            System.out.println("There are no Doctors Available");
            return;
        }
        String slot=InputHandler.getStringInput("Enter the time slot (e.g., 'Monday 10AM', 'Evening', etc.): ");
        boolean found=false;
        for(Doctor d:doctorList){
            List<String> slots=d.getAvailableSlots();
            if(!slots.isEmpty() && slots.stream().anyMatch(s -> s.equalsIgnoreCase(slot))) {
                d.displaySummary();
                found=true;
            }
        }
        if(!found){
            System.out.println("No Doctors available at the specified time slot.");
        } else {
            System.out.println("The List of The Available Doctors");
        }
    }

    public void assignPatientToDoctor(){
        if(doctorList.isEmpty()){
            System.out.println("There are no doctors");
        }
        String idDoctor=InputHandler.getStringInput("Enter doctor ID");
        Doctor selectedDoctor=null;
        for(Doctor d:doctorList){
            if(d.getDoctorId().equals(idDoctor)){
                selectedDoctor=d;
                break;
            }
        }
        if(!HelperUtils.isValidString(selectedDoctor)){
            System.out.println("Doctor ID not found");
            return;
        }
        boolean flag2=true;
        List<String>assignedPatients=new ArrayList<>();
        while (flag2) {
            String patientId = InputHandler.getStringInput("Enter assigned Patient ID (type 'q' when finished)");
            if (patientId.equalsIgnoreCase("q")) {
                flag2 = false;
            } else {
                if (PatientService.checkIfIdPatientExit(patientId)) {
                    assignedPatients.add(patientId);
                } else {
                    System.out.println("Patient ID does not exist. Please enter a valid Patient ID");
                }
            }
        }

        selectedDoctor.setAssignedPatients(assignedPatients);
        System.out.println("Patients assigned successfully to Dr. " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName());

    }

    public static Boolean checkIfIdDoctorExist(String idDoctor) {
        for (Doctor d : doctorList) {
            if (d.getDoctorId().equals(idDoctor)) {
                return true;
            }
        }
        return false;
    }

    public static Doctor getDoctorById(String id) {
        for (Doctor d : doctorList) {
            if (d.getDoctorId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    public Doctor addDoctor(String name, String specialization, String phone){
        Doctor doctor=new Doctor();
        doctor.setId(HelperUtils.generateId("PER", 8));
        doctor.setDoctorId(HelperUtils.generateId("Doc",8));
        doctor.setFirstName(name);
        doctor.setPhoneNumber(phone);
        doctor.setSpecialization(specialization);
        validate(doctor);
        return doctor;
    }
    public Doctor addDoctor(String name, String specialization, String phone, double consultationFee){
        Doctor doctor=new Doctor();
        doctor.setId(HelperUtils.generateId("PER", 8));
        doctor.setDoctorId(HelperUtils.generateId("Doc",8));
        doctor.setFirstName(name);
        doctor.setPhoneNumber(phone);
        doctor.setSpecialization(specialization);
        doctor.setConsultationFee(consultationFee);
        validate(doctor);
        return doctor;
    }
    public Doctor addDoctor(Doctor doctor){
        validate(doctor);
        return doctor;
    }
    public void assignPatient(String doctorId, String patientId){
        Doctor doctor=getDoctorById(doctorId);
        if(HelperUtils.isNull(doctor)){
            System.out.println("Doctor ID not found");
            return;
        }
        if(!PatientService.checkIfIdPatientExit(patientId)){
            System.out.println("Patient ID does not exist. Please enter a valid Patient ID");
            return;
        }
        doctor.getAssignedPatients().add(patientId);
        System.out.println("Patient assigned successfully to Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
    }
 public void assignPatient(Doctor doctor, Patient patient) {
        if (HelperUtils.isNull(doctor)) {
            System.out.println("Doctor object is null");
            return;
        }
        if (HelperUtils.isNull(patient)) {
            System.out.println("Patient object is null");
            return;
        }
        doctor.getAssignedPatients().add(patient.getPatientId());
        System.out.println("Patient assigned successfully to Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
    }

public void  assignPatient(String doctorId, List<String> patientIds) {
    if(!HelperUtils.isValidString(doctorId)){
        System.out.println("Doctor ID is invalid");
        return;
    }
    Doctor doctor=getDoctorById(doctorId);
    if(HelperUtils.isNull(doctor)) {
        System.out.println("Doctor ID not found");
        return;
    }
    for(String patientId:patientIds){
        if(!PatientService.checkIfIdPatientExit(patientId)){
            System.out.println("Patient ID "+patientId+" does not exist. Please enter a valid Patient ID");
        }else{
            doctor.getAssignedPatients().add(patientId);
        }
    }
    System.out.println("Patients assigned successfully to Dr. " + doctor.getFirstName() + " " + doctor.getLastName());
}

public  void displayDoctors(){
    if(doctorList.isEmpty()){
        System.out.println("There are no Doctors Available");
        return;
    }
    System.out.println("The List of The Doctors");
    for(Doctor d:doctorList){
        d.displayInfo();
    }
}
public void  displayDoctors(String specialization){
    if(doctorList.isEmpty()){
        System.out.println("There are no Doctors Available");
        return;
    }
    boolean found=false;
    for(Doctor d:doctorList){
        if(d.getSpecialization().equalsIgnoreCase(specialization)){
            d.displayInfo();
            found=true;
        }
    }
    if(!found){
        System.out.println("No Doctors found with the specialization: "+specialization);
    }
}
public void displayDoctors(String departmentId, boolean showAvailableOnly) {
    if (doctorList.isEmpty()) {
        System.out.println("There are no Doctors Available");
        return;
    }
    boolean found = false;
    for (Doctor d : doctorList) {
        if (d.getDepartmentId().equalsIgnoreCase(departmentId)) {
            if (showAvailableOnly) {
                if (!d.getAvailableSlots().isEmpty()) {
                    d.displayInfo();
                    found = true;
                }
            } else {
                d.displayInfo();
                found = true;
            }
        }
    }
    if (!found) {
        System.out.println("No Doctors found in the department: " + departmentId);
    }
}
}

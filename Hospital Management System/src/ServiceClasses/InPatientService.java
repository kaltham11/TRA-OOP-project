package ServiceClasses;

import EntityClasses.Doctor;
import EntityClasses.EmergencyPatient;
import EntityClasses.Gender;
import EntityClasses.InPatient;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InPatientService implements Manageable<InPatient>, Searchable {
    public static List<InPatient> inPatientList = new ArrayList<>();
    public static PatientService patientService = new PatientService();

    @Override
    public InPatient add() {
        boolean flag = true;
        InPatient inPatient=new InPatient();
        System.out.println("Creating a new In-Patient record");
        inPatient.setId(HelperUtils.generateId("PER", 8));
        inPatient.setPatientId(HelperUtils.generateId("PAT", 8));
        inPatient.setFirstName(InputHandler.getStringInput("Enter a First Name for the Patient"));
        inPatient.setLastName(InputHandler.getStringInput("Enter a Last Name for the Patient"));
        inPatient.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Patient in format (YYYY-MM-DD)"));
        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        inPatient.setGender(selectedGender);
        inPatient.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Patient"));
        inPatient.setEmail(InputHandler.getStringInput("Enter the email for the Patient"));
        inPatient.setAddress(InputHandler.getStringInput("Enter the address for the Patient"));
        inPatient.setBloodGroup(InputHandler.getStringInput("Enter Blood Group (A+, A-, B+, B-, AB+, AB-, O+, O-)"));

        List<String> allergies = new ArrayList<>();
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
        inPatient.setAllergies(allergies);
       inPatient.setEmergencyContact(InputHandler.getStringInput("Enter emergency Contact"));
        inPatient.setInsuranceId(InputHandler.getStringInput("Enter Insurance ID"));
        // InPatient specific fields
        inPatient.setAdmissionDate(InputHandler.getDateInput("Enter Admission Date for the Patient in format (YYYY-MM-DD)"));
        inPatient.setDischargeDate(InputHandler.getDateInput("Enter Discharge Date for the Patient in format (YYYY-MM-DD)"));
        inPatient.setRoomNumber(InputHandler.getStringInput("Enter Room Number"));
        inPatient.setBedNumber(InputHandler.getStringInput("Enter Bed Number"));
        inPatient.setAdmittingDoctorId(InputHandler.getStringInput("Enter Admitting Doctor ID"));
        inPatient.setDailyCharges(InputHandler.getDoubleInput("Enter Daily Charges for the InPatient"));
        inPatient.setRegistrationDate(LocalDate.now());
        patientService.save(inPatient);
        return inPatient;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<InPatient> getAll() {
        return new ArrayList<>();
    }


    @Override
    public void search() {

    }

    @Override
    public void searchById() {

    }
    public static void addSampleInPatients() {
        DoctorService doctorService=new DoctorService();
        List<Doctor> doctors = doctorService.getAll();
        if(doctors.isEmpty()){
            System.out.println("No Doctors available to assign!");
            return;
        }
        for (int i = 1; i < 3; i++) {
            InPatient inPatient = new InPatient();
            inPatient.setId(HelperUtils.generateId("PER", 8));
            inPatient.setPatientId(HelperUtils.generateId("PAT", 8));
            inPatient.setFirstName("InPatient" + i);
            inPatient.setLastName("Ward" + i);
            inPatient.setEmail("inpatient" + i + "@mail.com");
            inPatient.setPhoneNumber("9911" + i);
            inPatient.setAddress("Ward Address " + i);
            inPatient.setBloodGroup(i % 2 == 0 ? "B+" : "AB-");
            inPatient.setDateOfBirth(LocalDate.of(1985 + i, 2, i));
            inPatient.setGender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE);
            inPatient.setEmergencyContact("9888" + i);
            inPatient.setInsuranceId("INSIN" + i);
            List<String> allergies = new ArrayList<>();
            if (i % 2 == 0) allergies.add("Pollen");
            if (i % 3 == 0) allergies.add("Gluten");
            inPatient.setAllergies(allergies);
            inPatient.setAdmissionDate(LocalDate.now().minusDays(i));
            inPatient.setDischargeDate(LocalDate.now().plusDays(i * 2));
            inPatient.setRoomNumber("R" + i);
            inPatient.setBedNumber("B" + i);

            int doctorIndex = new Random().nextInt(doctors.size());
            inPatient.setAdmittingDoctorId(doctors.get(doctorIndex).getDoctorId());

            inPatient.setDailyCharges(250.0 + (i * 50));

            inPatient.setRegistrationDate(LocalDate.now());

            patientService.save(inPatient);
        }
    }
}

package ServiceClasses;

import EntityClasses.EmergencyPatient;
import EntityClasses.Gender;
import EntityClasses.OutPatient;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutPatientService implements Manageable<OutPatient>, Searchable {
    private static final List<OutPatient> outPatientList = new ArrayList<>();
    public static PatientService patientService = new PatientService();

    @Override
    public OutPatient add() {
        boolean flag = true;
        OutPatient outPatient=new OutPatient();
        System.out.println("Creating a new out-Patient record");
        outPatient.setId(HelperUtils.generateId("PER", 8));
        outPatient.setPatientId(HelperUtils.generateId("PAT", 8));
        outPatient.setFirstName(InputHandler.getStringInput("Enter a First Name for the Patient"));
        outPatient.setLastName(InputHandler.getStringInput("Enter a Last Name for the Patient"));
        outPatient.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Patient in format (YYYY-MM-DD)"));
        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        outPatient.setGender(selectedGender);
        outPatient.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Patient"));
        outPatient.setEmail(InputHandler.getStringInput("Enter the email for the Patient"));
        outPatient.setAddress(InputHandler.getStringInput("Enter the address for the Patient"));
        outPatient.setBloodGroup(InputHandler.getStringInput("Enter Blood Group (A+, A-, B+, B-, AB+, AB-, O+, O-)"));

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
        outPatient.setAllergies(allergies);
        outPatient.setEmergencyContact(InputHandler.getStringInput("Enter emergency Contact"));
        outPatient.setInsuranceId(InputHandler.getStringInput("Enter Insurance ID"));
        // OutPatient specific fields
        outPatient.setVisitCount(InputHandler.getIntInput("Enter Visit Count for the OutPatient"));
        outPatient.setLastVisitDate(InputHandler.getDateInput("Enter Last Visit Date for the Patient in format (YYYY-MM-DD)"));
        outPatient.setPreferredDoctorId(InputHandler.getStringInput("Enter Preferred Doctor ID"));
        outPatient.setNextVisitDate(InputHandler.getDateInput("Enter Next Visit Date for the Patient in format (YYYY-MM-DD)"));
        outPatient.setRegistrationDate(LocalDate.now());
        patientService.save(outPatient);
        return outPatient;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<OutPatient> getAll() {
        return new ArrayList<>();
    }


    @Override
    public void search() {

    }

    @Override
    public void searchById() {

    }
}

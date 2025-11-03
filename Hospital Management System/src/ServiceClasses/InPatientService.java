package ServiceClasses;

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

public class InPatientService implements Manageable<InPatient>, Searchable {
    private static final List<InPatient> inPatientList = new ArrayList<>();
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
}

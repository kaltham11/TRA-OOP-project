package ServiceClasses;

import EntityClasses.ArrivalMode;
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

public class EmergencyPatientService implements Manageable<EmergencyPatient>, Searchable {

        private static final List<EmergencyPatient> emergencyPatientList = new ArrayList<>();
        public static PatientService patientService = new PatientService();

        @Override
        public EmergencyPatient add() {
            boolean flag = true;
            EmergencyPatient emergencyPatient=new EmergencyPatient();
            System.out.println("Creating a new out-Patient record");
            emergencyPatient.setId(HelperUtils.generateId("PER", 8));
            emergencyPatient.setPatientId(HelperUtils.generateId("PAT", 8));
            emergencyPatient.setFirstName(InputHandler.getStringInput("Enter a First Name for the Patient"));
            emergencyPatient.setLastName(InputHandler.getStringInput("Enter a Last Name for the Patient"));
            emergencyPatient.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Patient in format (YYYY-MM-DD)"));
            System.out.println("Select Gender");
            for (Gender g : Gender.values()) {
                System.out.println((g.ordinal() + 1) + "." + g);
            }
            int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                    1, Gender.values().length);
            Gender selectedGender = Gender.values()[genderOption - 1];
            emergencyPatient.setGender(selectedGender);
            emergencyPatient.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Patient"));
            emergencyPatient.setEmail(InputHandler.getStringInput("Enter the email for the Patient"));
            emergencyPatient.setAddress(InputHandler.getStringInput("Enter the address for the Patient"));
            emergencyPatient.setBloodGroup(InputHandler.getStringInput("Enter Blood Group (A+, A-, B+, B-, AB+, AB-, O+, O-)"));

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
            emergencyPatient.setAllergies(allergies);
            emergencyPatient.setEmergencyContact(InputHandler.getStringInput("Enter emergency Contact"));
            emergencyPatient.setInsuranceId(InputHandler.getStringInput("Enter Insurance ID"));
            // InPatient specific fields
            emergencyPatient.setAdmissionDate(InputHandler.getDateInput("Enter Admission Date for the Patient in format (YYYY-MM-DD)"));
            emergencyPatient.setDischargeDate(InputHandler.getDateInput("Enter Discharge Date for the Patient in format (YYYY-MM-DD)"));
            emergencyPatient.setRoomNumber(InputHandler.getStringInput("Enter Room Number"));
            emergencyPatient.setBedNumber(InputHandler.getStringInput("Enter Bed Number"));
            emergencyPatient.setAdmittingDoctorId(InputHandler.getStringInput("Enter Admitting Doctor ID"));
            emergencyPatient.setDailyCharges(InputHandler.getDoubleInput("Enter Daily Charges for the InPatient"));
            // EmergencyPatient specific fields
            emergencyPatient.setEmergencyType(InputHandler.getStringInput("Enter emergency Type (e.g., Accident, Heart Attack, etc.)"));
            System.out.println("Enter Arrival Mode (Ambulance/Walk-in)for the Patient");
            for(ArrivalMode mode : ArrivalMode.values()){
                System.out.println((mode.ordinal()+1)+"."+mode);
            }
            int arrivalOption= InputHandler.getIntInput("Enter option(1-"+ ArrivalMode.values().length + "): ",
                    1, ArrivalMode.values().length);
            ArrivalMode selectedArrivalMode = ArrivalMode.values()[arrivalOption - 1];
            emergencyPatient.setArrivalMode(selectedArrivalMode);
            emergencyPatient.setTriageLevel(InputHandler.getIntInput("Enter Triage Level (1-5)", 1, 5));
            emergencyPatient.setAdmittedViaER(InputHandler.getConfirmation("Enter if the Patient was admitted via ER (Y/N)"));
            emergencyPatient.setRegistrationDate(LocalDate.now());
            patientService.save(emergencyPatient);
            return emergencyPatient;
        }

        @Override
        public void remove(String id) {

        }

        @Override
        public List<EmergencyPatient> getAll() {
            return new ArrayList<>();
        }


        @Override
        public void search() {

        }

        @Override
        public void searchById() {

        }


}

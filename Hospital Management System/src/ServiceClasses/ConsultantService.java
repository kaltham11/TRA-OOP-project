package ServiceClasses;

import EntityClasses.Appointment;
import EntityClasses.Consultant;
import EntityClasses.Gender;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultantService implements Manageable<Consultant>, Searchable {
    public static DoctorService doctorService = new DoctorService();

    @Override
    public Consultant add() {
        boolean flag = true;
        Consultant consultant = new Consultant();
        System.out.println("Creating a new Consultant record");
        consultant.setId(HelperUtils.generateId("PER", 8));
        consultant.setDoctorId(HelperUtils.generateId("CON", 8));
        consultant.setFirstName(InputHandler.getStringInput("Enter a First Name for the Consultant"));
        consultant.setLastName(InputHandler.getStringInput("Enter a Last Name for the Consultant"));
        consultant.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Consultant in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        consultant.setGender(selectedGender);

        consultant.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Consultant"));
        consultant.setEmail(InputHandler.getStringInput("Enter the email for the Consultant"));
        consultant.setAddress(InputHandler.getStringInput("Enter the address for the Consultant"));
        consultant.setSpecialization(InputHandler.getStringInput("Enter Specialization for Consultant"));
        consultant.setQualification(InputHandler.getStringInput("Enter Qualification for Consultant"));
        consultant.setExperienceYears(InputHandler.getIntInput("Enter Experience Years for Consultant"));
        consultant.setConsultationFee(InputHandler.getDoubleInput("Enter Consultation Fee for Consultant"));

        List<String> availableSlots = new ArrayList<>();
        System.out.println("Please, Enter available slots like (Monday 10AM, or Wednesday 3PM)(type 'q' when finished)");
        while (flag) {
            String slot = InputHandler.getStringInput("Enter available slot: ");
            if (slot.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                availableSlots.add(slot);
            }
        }
        consultant.setAvailableSlots(availableSlots);

        // Consultant specific fields
        flag = true;
        List<String> consultationTypes = new ArrayList<>();
        System.out.println("Enter consultation types (e.g., 'Initial', 'Follow-up') (type 'q' to finish):");
        while (flag) {
            String type = InputHandler.getStringInput("Consultation Type: ");
            if (type.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                consultationTypes.add(type);
            }
        }
        consultant.setConsultationTypes(consultationTypes);

        consultant.setOnlineConsultationAvailable(InputHandler.getConfirmation("Is online consultation available?"));
        consultant.setConsultationDuration(InputHandler.getIntInput("Enter consultation duration in minutes"));

        // validate and save using DoctorService
        doctorService.validate(consultant);
        doctorService.save(consultant);
        return consultant;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Consultant> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void search() {

    }

    @Override
    public void searchById() {

    }

    public static void addSampleConsultants() {
        for (int i = 1; i <= 3; i++) {
            Consultant consultant = new Consultant();
            consultant.setId(HelperUtils.generateId("PER", 8));
            consultant.setDoctorId(HelperUtils.generateId("CON", 8));
            consultant.setFirstName("Consultant" + i);
            consultant.setLastName("Doctor" + i);
            consultant.setEmail("consultant" + i + "@hospital.com");
            consultant.setPhoneNumber("9777" + i);
            consultant.setAddress("Address " + i);
            consultant.setDateOfBirth(LocalDate.of(1980 + i, 5, i));
            consultant.setGender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE);
            consultant.setSpecialization(i % 2 == 0 ? "Cardiology" : "Neurology");
            consultant.setQualification("MD");
            consultant.setExperienceYears(5 + i);
            consultant.setConsultationFee(100 + i * 20);
            List<String> availableSlots = new ArrayList<>();
            availableSlots.add("Monday 10AM");
            availableSlots.add("Wednesday 3PM");
            if (i % 2 == 0) availableSlots.add("Thursday 11AM");
            consultant.setAvailableSlots(availableSlots);
            List<String> consultationTypes = new ArrayList<>();
            consultationTypes.add("Initial");
            consultationTypes.add("Follow-up");
            consultant.setConsultationTypes(consultationTypes);
            consultant.setOnlineConsultationAvailable(i % 2 == 0);
            consultant.setConsultationDuration(30 + i * 5);
            doctorService.save(consultant);
        }
    }

}

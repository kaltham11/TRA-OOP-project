package ServiceClasses;

import EntityClasses.EmergencyPatient;
import EntityClasses.GeneralPractitioner;
import EntityClasses.Gender;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GeneralPractitionerService implements Manageable<GeneralPractitioner>, Searchable {
    public static DoctorService doctorService = new DoctorService();

    @Override
    public GeneralPractitioner add() {
        GeneralPractitioner gp = new GeneralPractitioner();
        gp.setId(HelperUtils.generateId("PER", 8));
        gp.setDoctorId(HelperUtils.generateId("GP", 8));
        gp.setFirstName(InputHandler.getStringInput("Enter a First Name for the General Practitioner"));
        gp.setLastName(InputHandler.getStringInput("Enter a Last Name for the General Practitioner"));
        gp.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the General Practitioner in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        gp.setGender(selectedGender);

        gp.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the General Practitioner"));
        gp.setEmail(InputHandler.getStringInput("Enter the email for the General Practitioner"));
        gp.setAddress(InputHandler.getStringInput("Enter the address for the General Practitioner"));
        gp.setSpecialization(InputHandler.getStringInput("Enter Specialization for General Practitioner"));
        gp.setQualification(InputHandler.getStringInput("Enter Qualification for General Practitioner"));
        gp.setExperienceYears(InputHandler.getIntInput("Enter Experience Years for General Practitioner"));
        gp.setConsultationFee(InputHandler.getDoubleInput("Enter Consultation Fee for General Practitioner"));

        List<String> availableSlots = new ArrayList<>();
        System.out.println("Enter available slots for General Practitioner (type 'q' when finished):");
        boolean flag = true;
        while (flag) {
            String slot = InputHandler.getStringInput("Enter available slot: ");
            if (slot.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                availableSlots.add(slot);
            }
        }
        gp.setAvailableSlots(availableSlots);

        // GP specific fields
        gp.setWalkingAvailable(InputHandler.getConfirmation("Is walking consultation available?"));
        gp.setHomeVisitAvailable(InputHandler.getConfirmation("Is home visit available?"));
        gp.setVaccinationCertified(InputHandler.getConfirmation("Is the GP vaccination certified?"));
        doctorService.validate(gp);
        doctorService.save(gp);
        return gp;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<GeneralPractitioner> getAll() {
        return new ArrayList<>();
    }


    @Override
    public void search() {

    }

    @Override
    public void searchById() {

    }

    public static void addSampleGeneralPractitioners() {
        for (int i = 1; i <= 2; i++) {
            GeneralPractitioner gp = new GeneralPractitioner();
            gp.setId(HelperUtils.generateId("PER", 8));
            gp.setDoctorId(HelperUtils.generateId("GP", 8));
            gp.setFirstName("GP" + i);
            gp.setLastName("Doctor" + i);
            gp.setEmail("gp" + i + "@hospital.com");
            gp.setPhoneNumber("9666" + i);
            gp.setAddress("GP Address " + i);
            gp.setDateOfBirth(LocalDate.of(1985 + i, 3, i));
            gp.setGender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE);
            gp.setSpecialization("General Medicine");
            gp.setQualification("MBBS");
            gp.setExperienceYears(3 + i);
            gp.setConsultationFee(50 + i * 10);

            List<String> availableSlots = new ArrayList<>();
            availableSlots.add("Monday 10AM");
            availableSlots.add("Tuesday 2PM");
            availableSlots.add("Thursday 11AM");
            if (i % 2 == 0) availableSlots.add("Friday 3PM");
            gp.setAvailableSlots(availableSlots);

            gp.setWalkingAvailable(i % 2 == 0);
            gp.setHomeVisitAvailable(i % 3 == 0);
            gp.setVaccinationCertified(true);

            doctorService.save(gp);
        }
    }

}


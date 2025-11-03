package ServiceClasses;

import EntityClasses.Surgeon;
import EntityClasses.Gender;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class SurgeonService implements Manageable<Surgeon>, Searchable {
    public static DoctorService doctorService = new DoctorService();

    @Override
    public Surgeon add() {
        boolean flag = true;
        Surgeon surgeon = new Surgeon();
        System.out.println("Creating a new Surgeon record");
        surgeon.setId(HelperUtils.generateId("PER", 8));
        surgeon.setDoctorId(HelperUtils.generateId("SUR", 8));
        surgeon.setFirstName(InputHandler.getStringInput("Enter a First Name for the Surgeon"));
        surgeon.setLastName(InputHandler.getStringInput("Enter a Last Name for the Surgeon"));
        surgeon.setDateOfBirth(InputHandler.getDateInput("Enter Date of Birth for the Surgeon in format (YYYY-MM-DD)"));

        System.out.println("Select Gender");
        for (Gender g : Gender.values()) {
            System.out.println((g.ordinal() + 1) + "." + g);
        }
        int genderOption = InputHandler.getIntInput("Enter option(1-" + Gender.values().length + "): ",
                1, Gender.values().length);
        Gender selectedGender = Gender.values()[genderOption - 1];
        surgeon.setGender(selectedGender);

        surgeon.setPhoneNumber(InputHandler.getStringInput("Enter the phoneNumber for the Surgeon"));
        surgeon.setEmail(InputHandler.getStringInput("Enter the email for the Surgeon"));
        surgeon.setAddress(InputHandler.getStringInput("Enter the address for the Surgeon"));
        surgeon.setSpecialization(InputHandler.getStringInput("Enter Specialization for Surgeon"));
        surgeon.setQualification(InputHandler.getStringInput("Enter Qualification for Surgeon"));
        surgeon.setExperienceYears(InputHandler.getIntInput("Enter Experience Years for Surgeon"));
        surgeon.setConsultationFee(InputHandler.getDoubleInput("Enter Consultation Fee for Surgeon"));

        List<String> availableSlots = new ArrayList<>();
        System.out.println("Enter available slots for Surgeon (type 'q' when finished):");
        while (flag) {
            String slot = InputHandler.getStringInput("Enter available slot: ");
            if (slot.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                availableSlots.add(slot);
            }
        }
        surgeon.setAvailableSlots(availableSlots);

        // Surgeon specific fields
        surgeon.setSurgeriesPerformed(InputHandler.getIntInput("Enter number of surgeries performed so far"));

        // collect surgery types
        flag = true;
        List<String> surgeryTypes = new ArrayList<>();
        System.out.println("Enter surgery types the surgeon performs (type 'q' to finish)");
        while (flag) {
            String type = InputHandler.getStringInput("Surgery Type: ");
            if (type.equalsIgnoreCase("q")) {
                flag = false;
            } else {
                surgeryTypes.add(type);
            }
        }
        surgeon.setSurgeryTypes(surgeryTypes);

        surgeon.setOperationTheatreAccess(InputHandler.getConfirmation("Does the surgeon have operation theatre access?"));


        doctorService.validate(surgeon);
        doctorService.save(surgeon);
        return surgeon;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Surgeon> getAll() {
        return new ArrayList<>();
    }

    @Override
    public void search() {

    }

    @Override
    public void searchById() {

    }
}

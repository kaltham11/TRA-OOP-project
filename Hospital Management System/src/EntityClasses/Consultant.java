package EntityClasses;

import InterfaceClasses.Displayable;
import ServiceClasses.DoctorService;
import ServiceClasses.PatientService;
import Utils.InputHandler;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Consultant extends Doctor implements Displayable {
    private List<String> consultationTypes;
    private Boolean onlineConsultationAvailable;
    private Integer consultationDuration; //(int - in minutes)

    public Consultant() {
        super();
    }

    public Consultant(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, Integer experienceYears, String departmentId, Double consultationFee, List<String> availableSlots, List<String> assignedPatients, List<String> consultationTypes, Boolean onlineConsultationAvailable, Integer consultationDuration) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients);
        this.consultationTypes = consultationTypes;
        this.onlineConsultationAvailable = onlineConsultationAvailable;
        this.consultationDuration = consultationDuration;
    }

    public List<String> getConsultationTypes() {
        return consultationTypes;
    }

    public void setConsultationTypes(List<String> consultationTypes) {
        if (HelperUtils.isNull(consultationTypes)) {
            this.consultationTypes = new ArrayList<>();
            return;
        }
        this.consultationTypes = consultationTypes;
    }

    public Boolean getOnlineConsultationAvailable() {
        return onlineConsultationAvailable;
    }

    public void setOnlineConsultationAvailable(Boolean onlineConsultationAvailable) {
        if (HelperUtils.isNull(onlineConsultationAvailable)) {
            this.onlineConsultationAvailable = false;
            return;
        }
        this.onlineConsultationAvailable = onlineConsultationAvailable;
    }

    public Integer getConsultationDuration() {
        return consultationDuration;
    }

    public void setConsultationDuration(Integer consultationDuration) {
        if (HelperUtils.isNull(consultationDuration) || !HelperUtils.isPositive(consultationDuration)) {
            throw new IllegalArgumentException("Consultation Duration must be a positive integer");
        }
        this.consultationDuration = consultationDuration;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    public void  scheduleConsultation(){
        System.out.println("Scheduling a new consultation...");
        String doctorId = InputHandler.getStringInput("Enter Doctor ID");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId=InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID");
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId=InputHandler.getStringInput("Patient ID does not exist, Please enter a valid Patient ID");
        }
        String date = InputHandler.getStringInput("Enter Consultation Date (YYYY-MM-DD)");
        String time = InputHandler.getStringInput("Enter Consultation Time (e.g., 10:30 AM)");


        if (consultationTypes == null || consultationTypes.isEmpty()) {
            System.out.println("No consultation types available for this doctor.");
            return;
        }

        System.out.println("Available Consultation Types:");
        for (int i = 0; i < consultationTypes.size(); i++) {
            System.out.println((i + 1) + ". " + consultationTypes.get(i));
        }

        int choice = InputHandler.getIntInput("Select a consultation type (1-" + consultationTypes.size() + "): ", 1, consultationTypes.size());
        String selectedType = consultationTypes.get(choice - 1);

        if (Boolean.TRUE.equals(onlineConsultationAvailable)) {
            System.out.println("This consultation can be conducted online.");
        }
    }

    public void provideSecondOpinion(){
        String doctorId = InputHandler.getStringInput("Enter Doctor ID");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId=InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID");
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId=InputHandler.getStringInput("Patient ID does not exist, Please enter a valid Patient ID");
        }
        String previousDiagnosis = InputHandler.getStringInput("Enter Previous Diagnosis: ");
        String doctorOpinion = InputHandler.getStringInput("Enter Your Second Opinion: ");
    }
}

package EntityClasses;

import ServiceClasses.DoctorService;
import ServiceClasses.PatientService;
import Utils.InputHandler;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.List;

public class GeneralPractitioner extends Doctor {

    private Boolean walkingAvailable;
    private Boolean homeVisitAvailable;
    private Boolean vaccinationCertified;

    public GeneralPractitioner() {
        super();
    }

    public GeneralPractitioner(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, Integer experienceYears, String departmentId, Double consultationFee, List<String> availableSlots, List<String> assignedPatients, Boolean walkingAvailable, Boolean homeVisitAvailable, Boolean vaccinationCertified) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients);
        this.walkingAvailable = walkingAvailable;
        this.homeVisitAvailable = homeVisitAvailable;
        this.vaccinationCertified = vaccinationCertified;
    }

    public Boolean getWalkingAvailable() {
        return walkingAvailable;
    }

    public void setWalkingAvailable(Boolean walkingAvailable) {
        if (HelperUtils.isNull(walkingAvailable)) {
            this.walkingAvailable = false;
            return;
        }
        this.walkingAvailable = walkingAvailable;
    }

    public Boolean getHomeVisitAvailable() {
        return homeVisitAvailable;
    }

    public void setHomeVisitAvailable(Boolean homeVisitAvailable) {
        if (HelperUtils.isNull(homeVisitAvailable)) {
            this.homeVisitAvailable = false;
            return;
        }
        this.homeVisitAvailable = homeVisitAvailable;
    }

    public Boolean getVaccinationCertified() {
        return vaccinationCertified;
    }

    public void setVaccinationCertified(Boolean vaccinationCertified) {
        if (HelperUtils.isNull(vaccinationCertified)) {
            this.vaccinationCertified = false;
            return;
        }
        this.vaccinationCertified = vaccinationCertified;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    public void scheduleHomeVisit() {
        if (!Boolean.TRUE.equals(homeVisitAvailable)) {
            System.out.println("This doctor does not offer home visits.");
            return;
        }

        System.out.println("Scheduling a Home Visit...");
        String doctorId = InputHandler.getStringInput("Enter Doctor ID: ");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId = InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID: ");
        }

        String patientId = InputHandler.getStringInput("Enter Patient ID: ");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId = InputHandler.getStringInput("Patient ID does not exist, Please enter a valid Patient ID: ");
        }

        String date = InputHandler.getStringInput("Enter Visit Date (YYYY-MM-DD): ");
        String time = InputHandler.getStringInput("Enter Visit Time (e.g., 3:00 PM): ");
        System.out.println("Home visit scheduled successfully on " + date + " at " + time + " for patient " + patientId);
    }


    public void administerVaccine() {
        if (!Boolean.TRUE.equals(vaccinationCertified)) {
            System.out.println("This doctor is not certified to administer vaccines.");
            return;
        }

        String doctorId = InputHandler.getStringInput("Enter Doctor ID: ");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId = InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID: ");
        }

        String patientId = InputHandler.getStringInput("Enter Patient ID: ");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId = InputHandler.getStringInput("Patient ID does not exist, Please enter a valid Patient ID: ");
        }

        String vaccineType = InputHandler.getStringInput("Enter Vaccine Type: ");
        System.out.println("Vaccine " + vaccineType + " administered successfully to patient " + patientId);
    }

        }

package EntityClasses;

import InterfaceClasses.Displayable;
import ServiceClasses.AppointmentService;
import ServiceClasses.MedicalRecordService;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Main.MainApplication.patient;


public class Patient extends Person implements Displayable {

    private String patientId;
    private String bloodGroup;
    private List<String> allergies = new ArrayList<>();
    private String emergencyContact;
    private LocalDate registrationDate;
    private String insuranceId;
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String patientId, String bloodGroup, List<String> allergies, String emergencyContact, LocalDate registrationDate, String insuranceId, List<MedicalRecord> medicalRecords, List<Appointment> appointments) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
        this.emergencyContact = emergencyContact;
        this.registrationDate = registrationDate;
        this.insuranceId = insuranceId;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        if (!HelperUtils.isValidString(patientId)) {
            throw new IllegalArgumentException("Patient ID can't be null or empty");
        }
        this.patientId = patientId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        if (!HelperUtils.isValidString(bloodGroup)) {
            throw new IllegalArgumentException("Blood Group can't be null or empty");
        }
        String bloodRegex = "^(A|B|AB|O)[+-]$";
        if (!HelperUtils.isValidString(bloodGroup, bloodRegex)) {
            throw new IllegalArgumentException("Blood Group must be one of the following: A+, A-, B+, B-, AB+, AB-, O+, O-");
        }
        this.bloodGroup = bloodGroup;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        if (HelperUtils.isNull(allergies)) {
            this.allergies = new ArrayList<>();
        }
        this.allergies = allergies;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        if (!HelperUtils.isValidString(emergencyContact)) {
            throw new IllegalArgumentException("Emergency Contact can't be null or empty");
        }
        this.emergencyContact = emergencyContact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (!HelperUtils.isValidDate(registrationDate)) {
            throw new IllegalArgumentException("Registration Date can't be null");
        }
        this.registrationDate = registrationDate;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        if (!HelperUtils.isValidString(insuranceId)) {
            throw new IllegalArgumentException("Insurance ID can't be null or empty");
        }
        this.insuranceId = insuranceId;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        if (HelperUtils.isNull(medicalRecords)) {
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords = medicalRecords;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        if (HelperUtils.isNull(appointments)) {
            this.appointments = new ArrayList<>();
        }
        this.appointments = appointments;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Patient Id: " + patientId);
        System.out.println("Blood Group: " + bloodGroup);
        if (HelperUtils.isNull(allergies) || allergies.isEmpty()) {
            System.out.println("The Patient hasn't any allergies");
        }
        for (String all : allergies) {
            System.out.println("The Patient allergy is: " + "-" + all);
        }
        System.out.println("Emergency Contact: " + emergencyContact);
        System.out.println("Registration Date: " + registrationDate);
        System.out.println("Insurance ID: " + insuranceId);
        if (HelperUtils.isNull(medicalRecords) || medicalRecords.isEmpty()) {
            System.out.println("The Patient hasn't any Medical Records");
        } else {
            for (MedicalRecord rec : medicalRecords) {
                rec.displayInfo();
            }
        }

        if (HelperUtils.isNull(appointments) || appointments.isEmpty()) {
            System.out.println("The Patient hasn't any appointments");
        } else {
            for (Appointment app : appointments) {
                app.displayInfo();
            }
        }
    }

    @Override
    public void displaySummary() {
        System.out.println("Patient ID: " + patientId + ", Name: " + getFirstName() + " " + getLastName() + ", Blood Group: " + bloodGroup + ", Emergency Contact: " + emergencyContact);
    }

    public void addMedicalRecord(MedicalRecord Record) {
        if (!HelperUtils.isNotNull(medicalRecords)) {
            throw new IllegalArgumentException("Medical Record can't be null");
        } else {
            medicalRecords.add(Record);
            System.out.println("Medical Record added successfully" + patientId);
        }

    }

    public void addAppointment(Appointment appointmentPatient) {
        if (!HelperUtils.isNotNull(appointmentPatient)) {
            throw new IllegalArgumentException("Appointment can't be null");
        } else {
            appointments.add(appointmentPatient);
            System.out.println("Appointment added successfully");
        }
    }


    public void updateInsurance(String insuranceUpdate) {
        setInsuranceId(insuranceUpdate);
        System.out.println("Insurance update successfully");
    }

    public void updateContact(String phone) {
        setPhoneNumber(phone);
        System.out.println("Phone number updated successfully");
    }

    public void updateContact(String phone, String email) {
        setPhoneNumber(phone);
        setEmail(email);
        System.out.println("Phone number and email updated successfully");
    }

    public void updateContact(String phone, String email, String address) {
        setPhoneNumber(phone);
        setEmail(email);
        setAddress(address);
        System.out.println("Phone number, email and address updated successfully");
    }
}

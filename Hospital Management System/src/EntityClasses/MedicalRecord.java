package EntityClasses;

import InterfaceClasses.Displayable;

import java.time.LocalDate;
import Utils.HelperUtils;

public class MedicalRecord implements Displayable {
    private String recordId;
    private String patientId;
    private String doctorId;
    private LocalDate visitDate;
    private String diagnosis;
    private String prescription;
    private String testResults;
    private String notes;

    public MedicalRecord() {
    }

    public MedicalRecord(String recordId, String patientId, String doctorId, LocalDate visitDate, String diagnosis, String prescription, String testResults, String notes) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.testResults = testResults;
        this.notes = notes;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        if (!HelperUtils.isValidString(recordId)) {
            throw new IllegalArgumentException("Record ID can't be null or empty");
        }
        this.recordId = recordId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        if (!HelperUtils.isValidString(doctorId)) {
            throw new IllegalArgumentException("Doctor ID can't be null or empty");
        }
        this.doctorId = doctorId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        if (!HelperUtils.isValidDate(visitDate)) {
            throw new IllegalArgumentException("Visit Date can't be null");
        }
        if (HelperUtils.isFutureDate(visitDate)) {
            throw new IllegalArgumentException("Visit Date can't be in the future");
        }
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        if (!HelperUtils.isValidString(diagnosis)) {
            throw new IllegalArgumentException("Diagnosis can't be null or empty");
        }
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Override
    public void displayInfo() {
        System.out.println("---------------------------------------------------");
        System.out.println("Record ID: " + recordId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor Id: " + doctorId);
        System.out.println("Visit Date: " + visitDate);
        System.out.println("The diagnosis: " + diagnosis);
        System.out.println("Prescription: " + prescription);
        System.out.println("The Test Results: " + testResults);
        System.out.println("The notes: " + notes);
        System.out.println("---------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println("Record ID: " + recordId + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Visit Date: " + visitDate);
    }
}

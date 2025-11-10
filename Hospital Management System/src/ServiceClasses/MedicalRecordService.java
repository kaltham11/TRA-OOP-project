package ServiceClasses;

import EntityClasses.Appointment;
import EntityClasses.Doctor;
import EntityClasses.MedicalRecord;
import EntityClasses.Patient;
import InterfaceClasses.Editable;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MedicalRecordService implements Manageable<MedicalRecord>, Searchable, Editable<MedicalRecord> {
    public static List<MedicalRecord> medicalRecordList = new ArrayList<>();


    @Override
    public MedicalRecord add() {
        MedicalRecord record = new MedicalRecord();
        record.setRecordId(HelperUtils.generateId("REC", 8));

        String patientId = InputHandler.getStringInput("Enter Patient ID for the Medical Record");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId = InputHandler.getStringInput("Patient ID does not exist,,Please enter a valid Patient ID");
        }
        record.setPatientId(patientId);

        String doctorId = InputHandler.getStringInput("Enter Doctor ID for the Medical Record");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId=InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID");
        }
        record.setDoctorId(doctorId);

        record.setVisitDate(InputHandler.getDateInput("Enter Visit Date for the Medical Record in format (YYYY-MM-DD)"));


        record.setDiagnosis(InputHandler.getStringInput("Enter Diagnosis"));
        record.setPrescription(InputHandler.getStringInput("Enter Prescription"));
        record.setTestResults(InputHandler.getStringInput("Enter Test Results"));
        record.setNotes(InputHandler.getStringInput("Enter Notes"));

        validate(record);
        return record;
    }

    public void save(MedicalRecord record) {
        if (HelperUtils.isNotNull(record)) {
            medicalRecordList.add(record);
            System.out.println("The medical record added successfully");
            Patient patient = PatientService.getPatientById(record.getPatientId());
            if (!HelperUtils.isNull(patient)) {
                List<MedicalRecord> patientRecords = patient.getMedicalRecords();
                if (HelperUtils.isNull(patientRecords)) {
                    patientRecords = new ArrayList<>();
                }
                patientRecords.add(record);
                patient.setMedicalRecords(patientRecords);
            }
        } else {
            throw new IllegalArgumentException("MedicalRecord object can't be null");
        }
    }


    @Override
    public MedicalRecord edit() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records");
            return null;
        }
        String id = InputHandler.getStringInput("Please, Enter Medical Record ID to edit");
        MedicalRecord selectedRecord = null;
        for (MedicalRecord r : medicalRecordList) {
            if (r.getRecordId().equals(id)) {
                selectedRecord = r;
                break;
            }
        }
        if (selectedRecord == null) {
            System.out.println("Medical Record not found");
            return null;
        }

        boolean editingFlag = true;
        while (editingFlag) {
            System.out.println("""
                    ==============================================
                    Enter the option number to edit item:
                    1- Visit Date
                    2- Diagnosis
                    3- Prescription
                    4- Test Results
                    5- Notes
                    6- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    LocalDate visitDate = InputHandler.getDateInput("Enter Visit Date in format (YYYY-MM-DD)");
                    if (HelperUtils.isPastDate(visitDate) || HelperUtils.isToday(visitDate)) {
                        selectedRecord.setVisitDate(visitDate);
                        System.out.println("Visit Date updated successfully");
                    } else {
                        System.out.println("Invalid date. Please ensure the date is not in the future.");
                    }
                }
                case 2 -> {
                    selectedRecord.setDiagnosis(InputHandler.getStringInput("Please, Enter a new Diagnosis"));
                    System.out.println("Diagnosis updated successfully");
                }
                case 3 -> {
                    selectedRecord.setPrescription(InputHandler.getStringInput("Please, Enter a new Prescription"));
                    System.out.println("Prescription updated successfully");
                }
                case 4 -> {
                    selectedRecord.setTestResults(InputHandler.getStringInput("Please, Enter new Test Results"));
                    System.out.println("Test Results updated successfully");
                }
                case 5 -> {
                    selectedRecord.setNotes(InputHandler.getStringInput("Please, Enter new Notes"));
                    System.out.println("Notes updated successfully");
                }
                case 6 -> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selectedRecord);
        return selectedRecord;
    }

    public void update(MedicalRecord updatedRecord) {
        if (updatedRecord == null) {
            System.out.println("No updated to save");
            return;
        }
        for (int i = 0; i < medicalRecordList.size(); i++) {
            if (medicalRecordList.get(i).getRecordId().equalsIgnoreCase(updatedRecord.getRecordId())) {
                medicalRecordList.set(i, updatedRecord);
                System.out.println("Medical Record updated successfully");
                return;
            }
        }
        System.out.println("Updated Medical Record not found in list");
    }

    @Override
    public void validate(MedicalRecord entity) {
        if (HelperUtils.isNull(entity)) {
            throw new IllegalArgumentException("MedicalRecord object can't be null");
        }
        if (!HelperUtils.isValidString(entity.getRecordId())) {
            throw new IllegalArgumentException("Record ID can't be empty");
        }
        if (!HelperUtils.isValidDate(entity.getVisitDate())) {
            throw new IllegalArgumentException("Visit Date can't be null");
        }
        if (HelperUtils.isFutureDate(entity.getVisitDate())) {
            throw new IllegalArgumentException("Visit Date can't be in the future");
        }
        if (!HelperUtils.isValidString(entity.getDiagnosis())) {
            throw new IllegalArgumentException("Diagnosis can't be empty");
        }
    }

    public String getMedicalRecordToRemove() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Medical Record ID to remove");
    }

    @Override
    public void remove(String id) {
        if (!HelperUtils.isValidString(id)) {
            System.out.println("No MedicalRecord removed, Invalid Input");
            return;
        }
        if (checkIfIdRecordExist(id)) {
            medicalRecordList.removeIf(r -> r.getRecordId().equals(id));
            System.out.println("Medical Record removed successfully.");
        } else {
            System.out.println("Medical Record not found");
        }
    }
    @Override
    public List<MedicalRecord> getAll() {
        return new ArrayList<>(medicalRecordList);
    }

    public void displayAllMedicalRecord() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        System.out.println("The List of Medical Records");
        for (MedicalRecord r : medicalRecordList) {
            r.displayInfo();
        }
    }


    @Override
    public void search() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID to search medical records");
        boolean found = false;
        for (MedicalRecord r : medicalRecordList) {
            if (r.getPatientId().equalsIgnoreCase(patientId)) {
                r.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Medical Records found for this patient");
        }
    }

    @Override
    public void searchById() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        String recordId = InputHandler.getStringInput("Enter Medical Record Id to search");
        boolean found = false;
        for (MedicalRecord r : medicalRecordList) {
            if (r.getRecordId().equalsIgnoreCase(recordId)) {
                r.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Medical Record found with this id");
        }
    }
    public void getRecordsByPatientId() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID to get medical records");
        boolean found = false;
        for (MedicalRecord r : medicalRecordList) {
            if (r.getPatientId().equalsIgnoreCase(patientId)) {
                r.displayInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("No Medical Records found for this patient");
        }
    }

    public void getRecordsByDoctorId() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        String doctorId = InputHandler.getStringInput("Enter Doctor ID to get medical records");
        boolean found = false;
        for (MedicalRecord r : medicalRecordList) {
            if (r.getDoctorId().equalsIgnoreCase(doctorId)) {
                r.displayInfo();
                found = true;
            }
        }
        if(!found){
            System.out.println("No Medical Records found for this patient");
        }
    }

    public void displayPatientHistory() {
        if (medicalRecordList.isEmpty()) {
            System.out.println("There are no Medical Records Available");
            return;
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID to display medical history");
        boolean found = false;
        System.out.println("\n================ PATIENT MEDICAL HISTORY ================");
        System.out.println("Patient ID: " + patientId);
        medicalRecordList.stream().filter(r -> r.getPatientId().equals(patientId)).
                sorted((r1, r2) -> r1.getVisitDate().compareTo(r2.getVisitDate())).forEach(
                        MedicalRecord::displayInfo);
        long count = medicalRecordList.stream().filter(r -> r.getPatientId().equals(patientId)).count();
        if (count == 0) {
            System.out.println("No Medical Records found for this patient");
        } else {
            System.out.println("Total Records Found: " + count);
        }
    }



    public static Boolean checkIfIdRecordExist(String idRecord) {
        for (MedicalRecord r : medicalRecordList) {
            if (r.getRecordId().equals(idRecord)) {
                return true;
            }
        }
        return false;
    }
    public static void addSampleMedicalRecords() {
        PatientService patientService=new PatientService();
        DoctorService doctorService=new DoctorService();
        List<Patient> patients = patientService.getAll();
        List<Doctor> doctors = doctorService.getAll();

        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("No patients or doctors available to create sample medical records.");
            return;
        }

        for (int i = 1; i <= 5; i++) {
            MedicalRecord record = new MedicalRecord();
            record.setRecordId(HelperUtils.generateId("REC", 8));

            int patientIndex = new Random().nextInt(patients.size());
            Patient selectedPatient=patients.get(patientIndex);
            record.setPatientId(selectedPatient.getPatientId());

            int doctorIndex = new Random().nextInt(doctors.size());
            record.setDoctorId(doctors.get(doctorIndex).getDoctorId());

            record.setVisitDate(LocalDate.now().minusDays(i));
            record.setDiagnosis("Diagnosis " + i);
            record.setPrescription("Prescription " + i);
            record.setTestResults("Test Results " + i);
            record.setNotes("No additional notes for record " + i);

            medicalRecordList.add(record);
            selectedPatient.addMedicalRecord(record);
        }
    }


}


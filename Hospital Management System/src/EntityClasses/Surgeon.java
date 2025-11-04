package EntityClasses;

import InterfaceClasses.Displayable;
import ServiceClasses.MedicalRecordService;
import ServiceClasses.PatientService;
import Utils.InputHandler;
import Utils.HelperUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Surgeon extends Doctor implements Displayable {
    private Integer surgeriesPerformed=0;
    private List<String> surgeryTypes;
    private Boolean operationTheatreAccess;
    MedicalRecord record =new MedicalRecord();

    public Surgeon() {
        super();
    }

    public Surgeon(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address, String doctorId, String specialization, String qualification, Integer experienceYears, String departmentId, Double consultationFee, List<String> availableSlots, List<String> assignedPatients, Integer surgeriesPerformed, List<String> surgeryTypes, Boolean operationTheatreAccess) {
        super(id, firstName, lastName, dateOfBirth, gender, phoneNumber, email, address, doctorId, specialization, qualification, experienceYears, departmentId, consultationFee, availableSlots, assignedPatients);
        this.surgeriesPerformed = surgeriesPerformed;
        this.surgeryTypes = surgeryTypes;
        this.operationTheatreAccess = operationTheatreAccess;
    }

    public Integer getSurgeriesPerformed() {
        return surgeriesPerformed;
    }

    public void setSurgeriesPerformed(Integer surgeriesPerformed) {
        if (HelperUtils.isNull(surgeriesPerformed)) {
            throw new IllegalArgumentException("Surgeries Performed can't be null");
        }
        if (HelperUtils.isNegative(surgeriesPerformed)) {
            throw new IllegalArgumentException("Surgeries Performed must be a non-negative integer");
        }
        this.surgeriesPerformed = surgeriesPerformed;
    }

    public List<String> getSurgeryTypes() {
        return surgeryTypes;
    }

    public void setSurgeryTypes(List<String> surgeryTypes) {
        if (HelperUtils.isNull(surgeryTypes)) {
            this.surgeryTypes = new ArrayList<>();
            return;
        }
        this.surgeryTypes = surgeryTypes;
    }

    public Boolean getOperationTheatreAccess() {
        return operationTheatreAccess;
    }

    public void setOperationTheatreAccess(Boolean operationTheatreAccess) {
        if (HelperUtils.isNull(operationTheatreAccess)) {
            this.operationTheatreAccess = false;
            return;
        }
        this.operationTheatreAccess = operationTheatreAccess;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Surgeries Performed: " + surgeriesPerformed);
        if (HelperUtils.isNull(surgeryTypes) || surgeryTypes.isEmpty()) {
            System.out.println("Surgery Types: None");
        } else {
            System.out.println("Surgery Types: " + String.join(", ", surgeryTypes));
        }
        System.out.println("Operation Theatre Access: " + (Boolean.TRUE.equals(operationTheatreAccess) ? "Yes" : "No"));
    }

    @Override
    public void displaySummary() {
        super.displaySummary();
    }

    public void performSurgery(){
        String recordId=InputHandler.getStringInput("Enter Medical Record ID: ");
        while(!MedicalRecordService.checkIfIdRecordExist(recordId)){
            recordId=InputHandler.getStringInput("Invalid Record ID. Please enter a valid Medical Record ID: ");
        }
        record.setRecordId(recordId);
        String patientId= InputHandler.getStringInput("Enter Patient ID for surgery: ");
        while (!PatientService.checkIfIdPatientExit(patientId)){
            patientId=InputHandler.getStringInput("Invalid Patient ID. Please enter a valid Patient ID: ");
        }
        record.setPatientId(patientId);
        String surgeryType= InputHandler.getStringInput("Enter Surgery Type: ");
        record.setDiagnosis(surgeryType);
        LocalDate dateOfSurgery= InputHandler.getDateInput("Enter Date of Surgery (YYYY-MM-DD): ");
        record.setVisitDate(dateOfSurgery);
        String surgeonId=this.getDoctorId();
        record.setDoctorId(surgeonId);


    }

    public void  updateSurgeryCount(){
       this.surgeriesPerformed++;
        System.out.println("Updated surgery count: " + this.surgeriesPerformed);

    }
}

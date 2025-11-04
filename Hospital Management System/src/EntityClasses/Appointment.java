package EntityClasses;

import InterfaceClasses.Displayable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Utils.HelperUtils;

public class Appointment implements Displayable {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String appointmentTime;
    Status status;
    private String reason;
    private String notes;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientId, String doctorId, LocalDate appointmentDate, String appointmentTime, Status status, String reason, String notes) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        if (!HelperUtils.isValidString(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID can't be null or empty");
        }
        this.appointmentId = appointmentId;
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        if (!HelperUtils.isValidDate(appointmentDate)) {
            throw new IllegalArgumentException("Appointment date can't be null");
        }
        if (HelperUtils.isPastDate(appointmentDate)) {
            throw new IllegalArgumentException("Appointment date can't be in the past");
        }
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        if (!HelperUtils.isValidString(appointmentTime)) {
            throw new IllegalArgumentException("Appointment time can't be null or empty");
        }
        this.appointmentTime = appointmentTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (!HelperUtils.isValidString(status)) {
            throw new IllegalArgumentException("Status can't be null");
        }
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient ID: " + patientId);
        System.out.println("Doctor Id: " + doctorId);
        System.out.println("AppointmentDate: " + appointmentDate);
        System.out.println("Appointment Time: " + appointmentTime);
        System.out.println("Status: " + status);
        System.out.println("Reason: " + reason);
        System.out.println("The notes: " + notes);
        System.out.println("---------------------------------------------------");
    }

    @Override
    public void displaySummary() {
        System.out.println("Patient ID: " + patientId + ", Date: " + appointmentDate + ", Time: " + appointmentTime + ", Status: " + status);
    }

    public void reschedule(LocalDate newDate, String newTime){
       this.appointmentDate= newDate;
       this.appointmentTime=newTime;
       this.status= Status.RESCHEDULED;
        System.out.println("The rescheduled appointment is "+newDate+" at "+newTime);
    }

    public void cancel(){
       this.status= Status.CANCELLED;
        System.out.println("The Appointment cancel Successfully");
    }

    public void complete(){
        this.status= Status.COMPLETED;
        System.out.println("The Appointment completed Successfully");
    }

    public void addNotes(String notes){
         setNotes(notes);
          System.out.println("Notes added successfully");
    }
public void addNotes(String notes, String addedBy){
        setNotes(notes);
        System.out.println("Notes added by "+addedBy+" successfully");
}
public void addNotes(String notes, String addedBy, LocalDateTime timestamp){
        setNotes(notes);
        System.out.println("Notes added by "+addedBy+" on "+timestamp+" successfully");
}
}

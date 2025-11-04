package ServiceClasses;

import EntityClasses.Appointment;
import EntityClasses.Patient;
import EntityClasses.Status;
import InterfaceClasses.Appointable;
import InterfaceClasses.Editable;
import InterfaceClasses.Manageable;
import InterfaceClasses.Searchable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppointmentService implements Manageable<Appointment>, Searchable, Editable<Appointment>, Appointable {
    private static final List<Appointment> appointmentList = new ArrayList<>();

    @Override
    public Appointment add() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(HelperUtils.generateId("APP", 8));

        String patientId = InputHandler.getStringInput("Enter Patient ID for the Appointment");
        while (!PatientService.checkIfIdPatientExit(patientId)) {
            patientId = InputHandler.getStringInput("Patient ID does not exist,,Please enter a valid Patient ID");
        }
        appointment.setPatientId(patientId);

        String doctorId = InputHandler.getStringInput("Enter Doctor ID for the Appointment");
        while (!DoctorService.checkIfIdDoctorExist(doctorId)) {
            doctorId = InputHandler.getStringInput("Doctor ID does not exist, Please enter a valid Doctor ID");
        }
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(InputHandler.getDateInput("Enter Appointment Date in format (YYYY-MM-DD)"));

        appointment.setAppointmentTime(InputHandler.getStringInput("Enter Appointment Time (e.g., '10:00 AM')"));

        appointment.setReason(InputHandler.getStringInput("Enter Reason for Appointment"));
        System.out.println("Enter Notes");
        String notes = InputHandler.scanner.nextLine().trim();
        appointment.setNotes(notes);
        appointment.setStatus(Status.SCHEDULED);
        validate(appointment);
        return appointment;
    }

    public void save(Appointment appointment) {
        if (HelperUtils.isNotNull(appointment)) {
            appointmentList.add(appointment);
            System.out.println("The appointment added successfully");

            Patient patient = PatientService.getPatientById(appointment.getPatientId());
            if (!HelperUtils.isNull(patient)) {
                List<Appointment> patientAppointments = patient.getAppointments();
                if (HelperUtils.isNull(patientAppointments)) {
                    patientAppointments = new ArrayList<>();
                }
                patientAppointments.add(appointment);
                patient.setAppointments(patientAppointments);
            }
        } else {
            throw new IllegalArgumentException("Appointment object can't be null");
        }
    }

    @Override
    public Appointment edit() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments");
            return null;
        }
        String id = InputHandler.getStringInput("Please, Enter Appointment ID to edit");
        Appointment selected = null;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equals(id)) {
                selected = a;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Appointment not found");
            return null;
        }

        boolean editingFlag = true;
        while (editingFlag) {
            System.out.println("""
                    ==============================================
                    Enter the option number to edit it
                    1- Patient ID
                    2- Doctor ID
                    3- Appointment Date
                    4- Appointment Time
                    5- Status
                    6- Reason
                    7- Notes
                    8- Exit
                    ==============================================
                    """);
            int option = InputHandler.getIntInput("Enter the number of option");
            switch (option) {
                case 1 -> {
                    String patientId = InputHandler.getStringInput("Please, Enter a new Patient ID");
                    if (PatientService.checkIfIdPatientExit(patientId)) {
                        selected.setPatientId(patientId);
                        System.out.println("Patient ID updated successfully");
                    } else {
                        System.out.println("Patient ID does not exist");
                    }
                }
                case 2 -> {
                    String doctorId = InputHandler.getStringInput("Please, Enter a new Doctor ID");
                    if (DoctorService.checkIfIdDoctorExist(doctorId)) {
                        selected.setDoctorId(doctorId);
                        System.out.println("Doctor ID updated successfully");
                    } else {
                        System.out.println("Doctor ID does not exist");
                    }
                }
                case 3 -> {
                    LocalDate apptDate = InputHandler.getDateInput("Enter Appointment Date in format (YYYY-MM-DD)");
                    if (!HelperUtils.isPastDate(apptDate)) {
                        selected.setAppointmentDate(apptDate);
                        System.out.println("Appointment Date updated successfully");
                    } else {
                        System.out.println("Appointment date can't be in the past");
                    }
                }
                case 4 -> {
                    selected.setAppointmentTime(InputHandler.getStringInput("Please, Enter a new Appointment Time"));
                    System.out.println("Appointment Time updated successfully");
                }
                case 5 -> {
                    System.out.println("Select Status:");
                    for (Status s : Status.values()) {
                        System.out.println((s.ordinal() + 1) + "." + s);
                    }
                    int statusOption = InputHandler.getIntInput("Enter option(1-" + Status.values().length + "): ", 1, Status.values().length);
                    Status selectedStatus = Status.values()[statusOption - 1];
                    selected.setStatus(selectedStatus);
                    System.out.println("Status updated successfully");
                }
                case 6 -> {
                    selected.setReason(InputHandler.getStringInput("Please, Enter a new Reason"));
                    System.out.println("Reason updated successfully");
                }
                case 7 -> {
                    System.out.println("Please, Enter new Notes (leave empty to clear)");
                    String newNotes = InputHandler.scanner.nextLine();
                    selected.setNotes(newNotes.trim());
                    System.out.println("Notes updated successfully");
                }
                case 8 -> {
                    System.out.println("Exiting edit menu...");
                    editingFlag = false;
                }
                default -> System.out.println("Please, Enter valid number from the Menu");
            }
        }
        validate(selected);
        return selected;
    }

    @Override
    public void validate(Appointment entity) {
        if (HelperUtils.isNull(entity)) {
            throw new IllegalArgumentException("Appointment object can't be null");
        }
        if (!HelperUtils.isValidString(entity.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment ID can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getPatientId()) || !PatientService.checkIfIdPatientExit(entity.getPatientId())) {
            throw new IllegalArgumentException("Patient ID is invalid or does not exist");
        }
        if (!HelperUtils.isValidString(entity.getDoctorId()) || !DoctorService.checkIfIdDoctorExist(entity.getDoctorId())) {
            throw new IllegalArgumentException("Doctor ID is invalid or does not exist");
        }
        if (!HelperUtils.isValidDate(entity.getAppointmentDate())) {
            throw new IllegalArgumentException("Appointment Date can't be null");
        }
        if (HelperUtils.isPastDate(entity.getAppointmentDate())) {
            throw new IllegalArgumentException("Appointment Date can't be in the past");
        }
        if (!HelperUtils.isValidString(entity.getAppointmentTime())) {
            throw new IllegalArgumentException("Appointment Time can't be empty");
        }
        if (!HelperUtils.isValidString(entity.getReason())) {
            throw new IllegalArgumentException("Reason can't be empty");
        }
    }

    public void update(Appointment updatedAppointment) {
        if (updatedAppointment == null) {
            System.out.println("No updated to save");
            return;
        }
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getAppointmentId().equalsIgnoreCase(updatedAppointment.getAppointmentId())) {
                appointmentList.set(i, updatedAppointment);
                System.out.println("Appointment updated successfully");
                return;
            }
        }
        System.out.println("Updated Appointment not found in list");
    }

    public String getAppointmentToRemove() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments");
            return null;
        }
        return InputHandler.getStringInput("Please Enter the Appointment ID to remove");
    }

    @Override
    public void remove(String id) {
        if (!HelperUtils.isValidString(id)) {
            System.out.println("No Appointment removed, Invalid Input");
            return;
        }
        if (checkIfIdAppointmentExist(id)) {
            appointmentList.removeIf(a -> a.getAppointmentId().equals(id));
            System.out.println("Appointment removed successfully.");
        } else {
            System.out.println("Appointment not found");
        }
    }


    public void displayAllAppointment() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        System.out.println("The List of Appointments");
        for (Appointment a : appointmentList) {
            a.displayInfo();
        }
    }

    @Override
    public List<Appointment> getAll() {
        return new ArrayList<>(appointmentList);
    }


    @Override
    public void search() {
    }

    public void getAppointmentsByDoctor() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        String doctorId = InputHandler.getStringInput("Enter Doctor ID to search appointments");
        boolean found = false;
        for (Appointment a : appointmentList) {
            if (a.getDoctorId().equalsIgnoreCase(doctorId)) {
                a.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Appointments found for this Doctor");
        }
    }

    public void getAppointmentsByPatient() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        String patientId = InputHandler.getStringInput("Enter Patient ID to search appointments");
        boolean found = false;
        for (Appointment a : appointmentList) {
            if (a.getPatientId().equalsIgnoreCase(patientId)) {
                a.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Appointments found for this patient");
        }
    }

    public void getAppointmentsByDate() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        LocalDate date = InputHandler.getDateInput("Enter Appointment Date in format (YYYY-MM-DD)");
        boolean found = false;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentDate().equals(date)) {
                a.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Appointments found in this date");
        }
    }

    public void completeAppointment() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments");
            return;
        }
        String id = InputHandler.getStringInput("Please, Enter Appointment ID to complete");
        Appointment selectedAppointment = null;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equals(id)) {
                selectedAppointment = a;
                break;
            }
        }
        if (selectedAppointment == null) {
            System.out.println("Appointment not found");
            return;
        }
        selectedAppointment.setStatus(Status.COMPLETED);
        System.out.println("Appointment " + id + " Completed successfully");
    }

    public void viewUpcomingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        LocalDate today = LocalDate.now();
        List<Appointment> upComingAppointments = appointmentList.stream().
                filter(a -> a.getAppointmentDate() != null).
                filter(a -> a.getAppointmentDate().isAfter(today)).
                filter(a -> a.getStatus() == Status.SCHEDULED).
                sorted(Comparator.comparing(Appointment::getAppointmentDate)).toList();
    if(upComingAppointments.isEmpty()){
        System.out.println("There are no Upcoming Appointments");
        return;
    }
        System.out.println("Upcoming Appointments:");
        for (Appointment a : upComingAppointments) {
            a.displayInfo();
        }
}

    @Override
    public void searchById() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments Available");
            return;
        }
        String appointmentId = InputHandler.getStringInput("Enter Appointment Id to search");
        boolean found = false;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                a.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Appointment found with this id");
        }
    }



    @Override
    public void scheduleAppointment() {
      Appointment newAppointment=add();
      if(newAppointment!=null){
          newAppointment.setStatus(Status.SCHEDULED);
          save(newAppointment);
      }else {
          System.out.println("Failed to schedule appointment");
      }
    }

    @Override
    public void cancelAppointment() {
        if( appointmentList.isEmpty()) {
            System.out.println("There are no Appointments");
            return;
        }
        String id = InputHandler.getStringInput("Please, Enter Appointment ID to cancel");
        Appointment selectedAppointment = null;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equals(id)) {
                selectedAppointment = a;
                break;
            }
        }
        if(selectedAppointment==null){
            System.out.println("Appointment not found");
            return;
        }
        selectedAppointment.setStatus(Status.CANCELLED);
        System.out.println("Appointment "+ id +"cancelled successfully");
    }

    @Override
    public void rescheduleAppointment() {
        if (appointmentList.isEmpty()) {
            System.out.println("There are no Appointments");
            return;
        }
        String id = InputHandler.getStringInput("Please, Enter Appointment ID to reschedule");
        Appointment selected = null;
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equals(id)) {
                selected = a;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Appointment not found");
            return;
        }
        LocalDate newDate = InputHandler.getDateInput("Enter new Appointment Date in format (YYYY-MM-DD)");
        if (HelperUtils.isPastDate(newDate)) {
            System.out.println("Appointment date can't be in the past");
            return;
        }
        selected.setAppointmentDate(newDate);
        selected.setAppointmentTime(InputHandler.getStringInput("Enter new Appointment Time (e.g., '10:00 AM')"));
        selected.setStatus(Status.RESCHEDULED);
        System.out.println("Appointment "+id+ "rescheduled successfully");
    }

    public static Boolean checkIfIdAppointmentExist(String idAppointment) {
        for (Appointment a : appointmentList) {
            if (a.getAppointmentId().equals(idAppointment)) {
                return true;
            }
        }
        return false;
    }
    public Appointment createAppointment(String patientId, String doctorId, LocalDate date) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(HelperUtils.generateId("APP", 8));
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(date);
        appointment.setStatus(Status.SCHEDULED);
        validate(appointment);
        return appointment;
    }
    public Appointment createAppointment(String patientId, String doctorId, LocalDate date, String time){
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(HelperUtils.generateId("APP", 8));
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(date);
        appointment.setAppointmentTime(time);
        appointment.setStatus(Status.SCHEDULED);
        validate(appointment);
        return appointment;
    }
    public Appointment createAppointment(Appointment appointment){
        validate(appointment);
        return appointment;
    }
public  void rescheduleAppointment(String appointmentId, LocalDate newDate) {
    if (appointmentList.isEmpty()) {
        System.out.println("There are no Appointments");
        return;
    }
    Appointment selected = null;
    for (Appointment a : appointmentList) {
        if (a.getAppointmentId().equals(appointmentId)) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Appointment not found");
        return;
    }
    if (HelperUtils.isPastDate(newDate)) {
        System.out.println("Appointment date can't be in the past");
        return;
    }
    selected.setAppointmentDate(newDate);
    selected.setStatus(Status.RESCHEDULED);
    System.out.println("Appointment " + appointmentId + " rescheduled successfully");
}
public void  rescheduleAppointment(String appointmentId, LocalDate newDate, String newTime) {
    if (appointmentList.isEmpty()) {
        System.out.println("There are no Appointments");
        return;
    }
    Appointment selected = null;
    for (Appointment a : appointmentList) {
        if (a.getAppointmentId().equals(appointmentId)) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Appointment not found");
        return;
    }
    if (HelperUtils.isPastDate(newDate)) {
        System.out.println("Appointment date can't be in the past");
        return;
    }
    selected.setAppointmentDate(newDate);
    selected.setAppointmentTime(newTime);
    selected.setStatus(Status.RESCHEDULED);
    System.out.println("Appointment " + appointmentId + " rescheduled successfully");
}
public void rescheduleAppointment(Appointment appointment, LocalDate newDate, String newTime, String reason) {
    if (appointmentList.isEmpty()) {
        System.out.println("There are no Appointments");
        return;
    }
    Appointment selected = null;
    for (Appointment a : appointmentList) {
        if (a.getAppointmentId().equals(appointment.getAppointmentId())) {
            selected = a;
            break;
        }
    }
    if (selected == null) {
        System.out.println("Appointment not found");
        return;
    }
    if (HelperUtils.isPastDate(newDate)) {
        System.out.println("Appointment date can't be in the past");
        return;
    }
    selected.setAppointmentDate(newDate);
    selected.setAppointmentTime(newTime);
    selected.setReason(reason);
    selected.setStatus(Status.RESCHEDULED);
    System.out.println("Appointment " + appointment.getAppointmentId() + " rescheduled successfully");
}
public void  displayAppointments(LocalDate date) {
    if (appointmentList.isEmpty()) {
        System.out.println("There are no Appointments Available");
        return;
    }
    boolean found = false;
    for (Appointment a : appointmentList) {
        if (a.getAppointmentDate().equals(date)) {
            a.displayInfo();
            found = true;
        }
    }
    if (!found) {
        System.out.println("No Appointments found in this date");
    }
}
public void  displayAppointments(String doctorId, LocalDate startDate, LocalDate endDate){
    if (appointmentList.isEmpty()) {
        System.out.println("There are no Appointments Available");
        return;
    }
    boolean found = false;
    for (Appointment a : appointmentList) {
        if (a.getDoctorId().equalsIgnoreCase(doctorId) &&
                ( !a.getAppointmentDate().isBefore(startDate) && !a.getAppointmentDate().isAfter(endDate))) {
            a.displayInfo();
            found = true;
        }
    }
    if (!found) {
        System.out.println("No Appointments found for this Doctor in the specified date range");
    }
}
}

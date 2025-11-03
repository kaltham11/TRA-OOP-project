package InterfaceClasses;

import EntityClasses.Appointment;

import java.time.LocalDate;


public interface Appointable {
    void scheduleAppointment();
    void cancelAppointment();
     void rescheduleAppointment();
}

package ServiceClasses;

import EntityClasses.*;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final PatientService patientService;


    public ReportService(AppointmentService appointmentService,
                         DoctorService doctorService,
                         DepartmentService departmentService,
                         PatientService patientService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.departmentService = departmentService;
        this.patientService = patientService;
    }

    public void dailyAppointmentsReport() {
        LocalDate date = InputHandler.getDateInput("Enter date (YYYY-MM-DD)");
        System.out.println("----- Daily Appointments Report (" + date + ") -----");

        List<Appointment> appts = appointmentService.getAll();
        if (appts == null || appts.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        boolean found = false;
        for (Appointment a : appts) {
            if (a != null && date.equals(a.getAppointmentDate())) {
                a.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for " + date + ".");
        }
    }



    public void doctorPerformanceReport() {
        System.out.println("----- Doctor Performance Report -----");
        List<Doctor> doctors = doctorService.getAll();
        List<Appointment> appts = appointmentService.getAll();

        for (Doctor d : doctors) {
            long completedAppointments = appts.stream()
                    .filter(a -> a.getDoctorId().equals(d.getDoctorId()))
                    .filter(a -> a.getStatus() == Status.COMPLETED)
                    .count();

            System.out.println(d.getFirstName() + " " + d.getLastName() +
                    " - Completed Appointments: " + completedAppointments);
        }
    }

    public void departmentOccupancyReport() {
        System.out.println("----- Department Occupancy Report -----");
        List<Department> deps = departmentService.getAll();

        for (Department dep : deps) {
            int doctorsCount = dep.getDoctors() == null ? 0 : dep.getDoctors().size();
            int nursesCount = dep.getNurses() == null ? 0 : dep.getNurses().size();

            System.out.println(dep.getDepartmentName() +
                    " | Doctors: " + doctorsCount +
                    " | Nurses: " + nursesCount);
        }
    }

    public void patientStatistics() {
        System.out.println("----- Patient Statistics -----");
        List<Patient> patients = patientService.getAll();

        System.out.println("Total Patients: " + (patients == null ? 0 : patients.size()));

        long inPatients = patients.stream()
                .filter(p -> p instanceof InPatient)
                .count();

        long emergencyPatients = patients.stream()
                .filter(p -> p instanceof EmergencyPatient)
                .count();

        System.out.println("In-Patients: " + inPatients);
        System.out.println("Emergency Patients: " + emergencyPatients);
    }

    public void emergencyCasesReport() {
        System.out.println("----- Emergency Cases Report -----");
        List<Patient> patients = patientService.getAll();

        patients.stream()
                .filter(p -> p instanceof EmergencyPatient)
                .forEach(Patient::displayInfo);
    }
}

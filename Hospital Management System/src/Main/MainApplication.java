package Main;
import ServiceClasses.*;
import Utils.InputHandler;

import java.util.Scanner;

public class MainApplication {

    public static Integer mainHospitalOption = 0;

    public static PatientService patient = new PatientService();
    public static DoctorService doctor = new DoctorService();
    public static NurseService nurse = new NurseService();
    public static MedicalRecordService medicalRecord = new MedicalRecordService();
    public static AppointmentService appointmentService = new AppointmentService();
    public static DepartmentService department = new DepartmentService();
    public static InPatientService inPatient = new InPatientService();
    public static OutPatientService outPatient = new OutPatientService();
    public static EmergencyPatientService emergencyPatient = new EmergencyPatientService();
    public static SurgeonService surgeonDoctor = new SurgeonService();
    public static ConsultantService consultantDoctor = new ConsultantService();
    public static GeneralPractitionerService gpDoctor = new GeneralPractitionerService();
    public static ReportService reportService = new ReportService(appointmentService, doctor, department, patient);

    public static void main(String[] args) {
        while (true) {
            showHospitalMenu();
            System.out.println("------------------------------------------");
            mainHospitalOption = InputHandler.getIntInput("Please, Enter an option from Hospital Menu");
            switch (mainHospitalOption) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> nurseMenu();
                case 4 -> appointmentMenu();
                case 5 -> medicalRecordsMenu();
                case 6 -> departmentMenu();
                case 7 -> reportMenu();
                case 8 -> {
                    System.out.println("Exit Application");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Patient Menu");
            }
        }
    }

    public static void patientMenu() {
        Integer mainPatientOption = 0;
        while (true) {
            showPatientMenu();
            System.out.println("------------------------------------------");
            mainPatientOption = InputHandler.getIntInput("Please, Enter an option from Patient Menu");
            switch (mainPatientOption) {
                case 1 -> patient.save(patient.add());
                case 2 -> inPatient.add();
                case 3 -> outPatient.add();
                case 4 -> emergencyPatient.add();
                case 5 -> patient.displayAllPatient();
                case 6 -> patient.searchById();
                case 7 -> patient.update(patient.edit());
                case 8 -> patient.remove(patient.getPatientToRemove());
                case 9 -> medicalRecord.displayPatientHistory();
                case 10 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Patient Menu");
            }
        }
    }

    public static void doctorMenu() {
        Integer mainDoctorOption = 0;
        while (true) {
            showDoctorMenu();
            System.out.println("------------------------------------------");
            mainDoctorOption = InputHandler.getIntInput("Please, Enter an option from Doctor Menu");
            switch (mainDoctorOption) {
                case 1 -> doctor.save(doctor.add());
                case 2 -> surgeonDoctor.add();
                case 3 -> consultantDoctor.add();
                case 4 -> gpDoctor.add();
                case 5 -> doctor.displayAllDoctor();
                case 6 -> doctor.search();
                case 7 -> doctor.viewAvailableDoctors();
                case 8 -> doctor.assignPatientToDoctor();
                case 9 -> doctor.update(doctor.edit());
                case 10 -> doctor.remove(doctor.getDoctorToRemove());
                case 11 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Doctor Menu");
            }
        }
    }

    public static void nurseMenu() {
        Integer mainNurseOption = 0;
        while (true) {
            showNurseMenu();
            System.out.println("------------------------------------------");
            mainNurseOption = InputHandler.getIntInput("Please, Enter an option from Nurse Menu");
            switch (mainNurseOption) {
                case 1 -> nurse.save(nurse.add());
                case 2 -> nurse.displayAllNurse();
                case 3 -> nurse.search();
                case 4 -> nurse.searchByShift();
                case 5 -> nurse.assignNurseToPatient();
                case 6 -> nurse.update(nurse.edit());
                case 7 -> nurse.remove(nurse.getNurseToRemove());
                case 8 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Nurse Menu");
            }
        }
    }

    public static void appointmentMenu() {
        Integer mainAppointmentOption = 0;
        while (true) {
            showAppointmentMenu();
            System.out.println("------------------------------------------");
            mainAppointmentOption = InputHandler.getIntInput("Please, Enter an option from Appointment Menu");
            switch (mainAppointmentOption) {
                case 1 -> appointmentService.save(appointmentService.add());
                case 2 -> appointmentService.displayAllAppointment();
                case 3 -> appointmentService.getAppointmentsByPatient();
                case 4 -> appointmentService.getAppointmentsByDoctor();
                case 5 -> appointmentService.getAppointmentsByDate();
                case 6 -> appointmentService.rescheduleAppointment();
                case 7 -> appointmentService.cancelAppointment();
                case 8 -> appointmentService.completeAppointment();
                case 9 -> appointmentService.viewUpcomingAppointments();
                case 10 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Appointment Menu");
            }
        }
    }

    public static void medicalRecordsMenu() {
        Integer mainRecordsOption = 0;
        while (true) {
            showRecordsMenu();
            System.out.println("------------------------------------------");
            mainRecordsOption = InputHandler.getIntInput("Please, Enter an option from Medical Records Menu");
            switch (mainRecordsOption) {
                case 1 -> medicalRecord.save(medicalRecord.add());
                case 2 -> medicalRecord.displayAllMedicalRecord();
                case 3 -> medicalRecord.getRecordsByPatientId();
                case 4 -> medicalRecord.getRecordsByDoctorId();
                case 5 -> medicalRecord.update(medicalRecord.edit());
                case 6 -> medicalRecord.remove(medicalRecord.getMedicalRecordToRemove());
                case 7 -> medicalRecord.displayPatientHistory();
                case 8 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Medical Records Menu");
            }
        }
    }

    public static void departmentMenu() {
        Integer mainDepartmentOption = 0;
        while (true) {
            showDepartmentMenu();
            System.out.println("------------------------------------------");
            mainDepartmentOption = InputHandler.getIntInput("Please, Enter an option from Department Menu");
            switch (mainDepartmentOption) {
                case 1 -> department.save(department.add());
                case 2 -> department.displayAllDepartment();
                case 3 -> department.viewDepartmentDetails();
                case 4 -> department.assignDoctorToDepartment();
                case 5 -> department.assignNurseToDepartment();
                case 6 -> department.update(department.edit());
                case 7 -> department.viewDepartmentStatistics();
                case 8 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Medical Records Menu");
            }
        }
    }

    public static void reportMenu() {
        Integer mainReportOption = 0;
        while (true) {
            showReportMenu();
            System.out.println("------------------------------------------");
            mainReportOption = InputHandler.getIntInput("Please, Enter an option from Report Menu");
            switch (mainReportOption) {
                case 1 -> reportService.dailyAppointmentsReport();
                case 2 -> reportService.doctorPerformanceReport();
                case 3 -> reportService.departmentOccupancyReport();
                case 4 -> reportService.patientStatistics();
                case 5 -> reportService.emergencyCasesReport();
                case 6 -> {
                    System.out.println("Returning to Hospital Menu");
                    return;
                }
                default -> System.out.println("Please, Enter valid Option from Report Menu");
            }
        }
    }

    public static void showHospitalMenu() {
        System.out.println("""
                ===========================================
                1. Patient Management
                2. Doctor Management
                3. Nurse Management
                4. Appointment Management
                5. Medical Records Management
                6. Department Management
                7. Reports and Statistics
                8- Exit
                """);
    }

    public static void showPatientMenu() {
        System.out.println("""
                ===========================================
                1. Register New Patient
                2. Register InPatient
                3. Register OutPatient
                4. Register Emergency Patient
                5. View All Patients
                6. Search Patient
                7.Update Patient Information
                8.Remove Patient
                9.View Patient Medical History
                10.Exit
                """);
    }

    public static void showDoctorMenu() {
        System.out.println("""
                =========================================
                      1. Add Doctor
                      2. Add Surgeon
                      3. Add Consultant
                      4. Add General Practitioner
                      5. View All Doctors
                      6. Search Doctor by Specialization
                      7. View Available Doctors
                      8. Assign Patient to Doctor
                      9. Update Doctor Information
                      10. Remove Doctor
                      11.Exit
                """);
    }


    public static void showNurseMenu() {
        System.out.println("""
                =========================================
                      1.Add Nurse
                      2.View All Nurses
                      3.View Nurses by Department
                      4.View Nurses by Shift
                      5.Assign Nurse to Patient
                      6.Update Nurse Information
                      7.Remove Nurse
                      8.Exit
                """);
    }

    public static void showAppointmentMenu() {
        System.out.println("""
                =========================================
                      1.Schedule New Appointment
                      2.View All Appointments
                      3.View Appointments by Patient
                      4.View Appointments by Doctor
                      5.View Appointments by Date
                      6.Reschedule Appointment
                      7. Cancel Appointment
                      8. Complete Appointment
                      9.View Upcoming Appointments
                      10.Exit
                """);
    }

    public static void showRecordsMenu() {
        System.out.println("""
                =========================================
                      1. Create Medical Record
                      2.View All Records
                      3.View Records by Patient
                      4.View Records by Doctor
                      5.Update Medical Record
                      6.Delete Medical Record
                      7. Generate Patient History Report
                      8.Exit
                """);
    }

    public static void showDepartmentMenu() {
        System.out.println("""
                =========================================
                      1. Add Department
                      2. View All Departments
                      3. View Department Details
                      4. Assign Doctor to Department
                      5. Assign Nurse to Department
                      6. Update Department Information
                      7. View Department Statistics
                      8.Exit
                """);
    }

    public static void showReportMenu() {
        System.out.println("""
                =========================================
                      1. Daily Appointments Report
                      2. Doctor Performance Report
                      3. Department Occupancy Report
                      4. Patient Statistics
                      5. Emergency Cases Report
                      6.Exit
                """);
    }


}


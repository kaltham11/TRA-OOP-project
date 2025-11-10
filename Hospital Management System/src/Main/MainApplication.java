package Main;
import EntityClasses.*;
import ServiceClasses.*;
import Utils.InputHandler;

public class MainApplication {

    public static Integer mainHospitalOption = 0;

    public static PatientService patientService = new PatientService();
    public static DoctorService doctorService = new DoctorService();
    public static NurseService nurseService = new NurseService();
    public static MedicalRecordService medicalRecordService = new MedicalRecordService();
    public static AppointmentService appointmentService = new AppointmentService();
    public static DepartmentService departmentService = new DepartmentService();
    public static InPatientService inPatientService = new InPatientService();
    public static OutPatientService outPatientService = new OutPatientService();
    public static EmergencyPatientService emergencyPatientService = new EmergencyPatientService();
    public static SurgeonService surgeonDoctorService = new SurgeonService();
    public static ConsultantService consultantDoctorService = new ConsultantService();
    public static GeneralPractitionerService gpDoctorService = new GeneralPractitionerService();
    public static ReportService reportService = new ReportService(appointmentService, doctorService, departmentService, patientService);

    public static void main(String[] args) {
        addSampleDataForAll();
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
                case 1 -> patientService.save(patientService.add());
                case 2 -> inPatientService.add();
                case 3 -> outPatientService.add();
                case 4 -> emergencyPatientService.add();
                case 5 -> patientService.displayAllPatient();
                case 6 -> patientService.searchById();
                case 7 -> patientService.update(patientService.edit());
                case 8 -> patientService.remove(patientService.getPatientToRemove());
                case 9 -> medicalRecordService.displayPatientHistory();
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
                case 1 -> doctorService.save(doctorService.add());
                case 2 -> surgeonDoctorService.add();
                case 3 -> consultantDoctorService.add();
                case 4 -> gpDoctorService.add();
                case 5 -> doctorService.displayAllDoctor();
                case 6 -> doctorService.search();
                case 7 -> doctorService.viewAvailableDoctors();
                case 8 -> doctorService.assignPatientToDoctor();
                case 9 -> doctorService.update(doctorService.edit());
                case 10 -> doctorService.remove(doctorService.getDoctorToRemove());
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
                case 1 -> nurseService.save(nurseService.add());
                case 2 -> nurseService.displayAllNurse();
                case 3 -> nurseService.search();
                case 4 -> nurseService.searchByShift();
                case 5 -> nurseService.assignNurseToPatient();
                case 6 -> nurseService.update(nurseService.edit());
                case 7 -> nurseService.remove(nurseService.getNurseToRemove());
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
                case 1 -> medicalRecordService.save(medicalRecordService.add());
                case 2 -> medicalRecordService.displayAllMedicalRecord();
                case 3 -> medicalRecordService.getRecordsByPatientId();
                case 4 -> medicalRecordService.getRecordsByDoctorId();
                case 5 -> medicalRecordService.update(medicalRecordService.edit());
                case 6 -> medicalRecordService.remove(medicalRecordService.getMedicalRecordToRemove());
                case 7 -> medicalRecordService.displayPatientHistory();
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
                case 1 -> departmentService.save(departmentService.add());
                case 2 -> departmentService.displayAllDepartment();
                case 3 -> departmentService.viewDepartmentDetails();
                case 4 -> departmentService.assignDoctorToDepartment();
                case 5 -> departmentService.assignNurseToDepartment();
                case 6 -> departmentService.update(departmentService.edit());
                case 7 -> departmentService.viewDepartmentStatistics();
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

    public static void addSampleDataForAll() {
        SurgeonService.addSampleSurgeons();
        GeneralPractitionerService.addSampleGeneralPractitioners();
        ConsultantService.addSampleConsultants();
        PatientService.addSamplePatients();
        InPatientService.addSampleInPatients();
        OutPatientService.addSampleOutPatients();
        EmergencyPatientService.addSampleEmergencyPatients();
        NurseService.sampleDataNurse();
        DepartmentService.addSampleDepartments();
        AppointmentService.sampleDataAppointment();
        MedicalRecordService.addSampleMedicalRecords();
        linkSampleData();

    }
    public static void linkSampleData() {
        // Link Doctors to Departments
        for (int i = 0; i < DoctorService.doctorList.size(); i++) {
            Doctor doctor = DoctorService.doctorList.get(i);
            Department dept = DepartmentService.departmentList.get(i % DepartmentService.departmentList.size());
            doctor.setDepartmentId(dept.getDepartmentId());
            dept.assignDoctor(doctor.getDoctorId());
        }
        // Link Nurses to Departments
        for (int i = 0; i < NurseService.nurseList.size(); i++) {
            Nurse nurse = NurseService.nurseList.get(i);
            Department dept = DepartmentService.departmentList.get(i % DepartmentService.departmentList.size());
            nurse.setDepartmentId(dept.getDepartmentId());
            dept.assignNurse(nurse.getNurseId());
        }
        // Assign Patients to Doctors and Nurses
        for (int i = 0; i < PatientService.patientList.size(); i++) {
            Patient patient = PatientService.patientList.get(i);
            Doctor doctor = DoctorService.doctorList.get(i % DoctorService.doctorList.size());
            doctor.getAssignedPatients().add(patient.getPatientId());

            Nurse nurse = NurseService.nurseList.get(i % NurseService.nurseList.size());
            nurse.getAssignedPatients().add(patient.getPatientId());

            if (patient instanceof InPatient inPatient) {
                inPatient.setAdmittingDoctorId(doctor.getDoctorId());
            } else if (patient instanceof EmergencyPatient emergencyPatient) {
                emergencyPatient.setAdmittingDoctorId(doctor.getDoctorId());
            } else if (patient instanceof OutPatient outPatient) {
                outPatient.setPreferredDoctorId(doctor.getDoctorId());
            }
        }
        System.out.println("Data linking completed successfully.");
    }


}





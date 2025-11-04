package EntityClasses;

import InterfaceClasses.Displayable;
import Utils.HelperUtils;
import Utils.InputHandler;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Displayable {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String address;

    public Person() {
    }

    public Person(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(!HelperUtils.isValidString(id)){
            throw new IllegalArgumentException("ID can't be null or empty");
        }
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(!HelperUtils.isValidString(firstName)){
            throw new IllegalArgumentException("First Name can't be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(!HelperUtils.isValidString(lastName)){
            throw new IllegalArgumentException("Last Name can't be null or empty");
        }
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(!HelperUtils.isValidDate(dateOfBirth)){
            throw new IllegalArgumentException("Date of Birth can't be null");
        }
        if(HelperUtils.isFutureDate(dateOfBirth)){
            throw new IllegalArgumentException("Date of Birth can't be in the Future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if(!HelperUtils.isValidString(gender)){
            throw new IllegalArgumentException("Gender can't be null or empty");
        }
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!HelperUtils.isValidString(phoneNumber)){
            throw new IllegalArgumentException("Phone Number can't be null or empty");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        while (true){
            try {
                if(!HelperUtils.isValidString(email)){
                    this.email = email;
                    break;
                }
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if(!HelperUtils.isValidString(email,emailRegex)){
                    throw new IllegalArgumentException("Invalid Email Format");
                }
                this.email = email;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                email = InputHandler.getStringInput("Please enter a valid email or(leave empty)");
            }
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(!HelperUtils.isValidString(address)){
            throw new IllegalArgumentException("Address can't be null or empty");
        }
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public void displayInfo() {
        System.out.println("---------------------------------------------------");
        System.out.println("The Name: " + firstName + " " + lastName);
        System.out.println("ID: " + id);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("The date of Birth: " + dateOfBirth);
        System.out.println("Email: " + email);
        System.out.println("The Address: " + address);
    }

    @Override
    public void displaySummary() {
        System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName);
    }

}

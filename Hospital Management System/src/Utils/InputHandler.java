package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    public static final Scanner scanner=new Scanner(System.in);

    public static String getStringInput(String prompt){
        System.out.println(prompt);
        String input=scanner.nextLine().trim();
        while (!HelperUtils.isValidString(input)){
            System.out.println("Invalid input. Please try again");
            System.out.println(prompt);
            input=scanner.nextLine().trim();
        }
        return input;
    }
    public static Integer getIntInput(String prompt){
        System.out.println(prompt);
        Integer input=scanner.nextInt();
        scanner.nextLine();
        while (!HelperUtils.isPositive(input)){
            System.out.println("Invalid input. Please try again");
            System.out.println(prompt);
            input=scanner.nextInt();
        }
        return input;
    }
    public static Integer getIntInput(String prompt, int min, int max){
        System.out.println(prompt);
        Integer input=scanner.nextInt();
        scanner.nextLine();
        while (!HelperUtils.isValidNumber(input,min,max)){
            System.out.println("Invalid input. Please try again");
            System.out.println(prompt);
            input=scanner.nextInt();
        }
        return input;
    }
    public static Double getDoubleInput(String prompt){
        System.out.println(prompt);
        while (!scanner.hasNextDouble()){
            System.out.println("Invalid input. Please try again");
            scanner.next(); //ignore the invalid input
            System.out.println(prompt);
        }
        Double input=scanner.nextDouble();
        scanner.nextLine();
        return input;
    }
    public static LocalDate getDateInput(String prompt){
        System.out.println(prompt+"Format: YYYY-MM-DD");
        String input=scanner.nextLine().trim();
        while (!HelperUtils.isValidDate(input)){
            System.out.println("Invalid input. Please try again");
            System.out.println(prompt+"Format: YYYY-MM-DD");
            input=scanner.nextLine().trim();
        }try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return getDateInput(prompt);
        }
    }
    public static Boolean getConfirmation(String prompt){
        System.out.println(prompt+" (yes/no)");
        String input=scanner.nextLine().trim().toLowerCase();
        while (!input.equals("yes")&&!input.equals("no")){
            System.out.println("Invalid input. Please try again (yes/no)");
            System.out.println(prompt+" (yes/no)");
            input=scanner.nextLine().trim().toLowerCase();
        }
        return input.equals("yes");
    }
}

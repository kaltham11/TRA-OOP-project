package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class HelperUtils {

//Null Check Methods (Overloaded)
    public static Boolean isNull(Object obj){
        return obj==null;
    }

    public static Boolean isNull(String str){

        return str==null || str.isEmpty();
    }

    public static Boolean isNotNull(Object obj){
        return !isNull(obj);
    }

    public static Boolean isNotNull(String str){
        return !isNull(str);
    }
//String Validation Methods (Overloaded)
    public static Boolean isValidString(String str){
        return  isNotNull(str);
    }
    public static Boolean isValidString(Object obj){
        return  isNotNull(obj);
    }

    public static Boolean isValidString(String str, int minLength) {
       return isValidString(str) && str.length()>=minLength;
    }

    public static Boolean isValidString(String str, int minLength, int maxLength) {
        return isValidString(str) && str.length()>=minLength && str.length()<=maxLength;
    }

    public static Boolean isValidString(String str, String regex) {
        return isValidString(str)&& str.matches(regex);
    }
//ID Generation Methods (Overloaded)
    public static String generateId(){
      return UUID.randomUUID().toString();
    }

    public static String generateId(String prefix){
        return prefix+"-"+generateId();
    }

    public static String generateId(String prefix, int length){
        String id=generateId().replace("-","");
        id=id.substring(0,Math.min(length, id.length()));
        return prefix+"-"+id;
    }

    public static String generateId(String prefix, String suffix){

        return prefix+"-"+generateId()+"-"+suffix;
    }
//Date Validation Methods (Overloaded)
    public static boolean isValidDate(LocalDate date){
        return isNotNull(date);
    }

    public static boolean isValidDate(String dateStr){
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(LocalDate date, LocalDate minDate, LocalDate maxDate){
     return isValidDate(date) && !date.isBefore(minDate) && !date.isAfter(maxDate);
    }

    public static boolean isFutureDate(LocalDate date){
        return isValidDate(date) && date.isAfter(LocalDate.now());
    }

    public static boolean isPastDate(LocalDate date){
        return isValidDate(date) && date.isBefore(LocalDate.now());
    }

    public static boolean isToday(LocalDate date){
        return isValidDate(date) && date.isEqual(LocalDate.now());
    }

    //Numeric Validation Methods (Overloaded)

    public static boolean isValidNumber(Integer num, Integer min, Integer max){
        return num>=min && num<=max;
    }
    public static boolean isValidNumber(Double num, Double min, Double max){
        return num>=min && num<=max;
    }
    public static boolean isPositive(Integer num){
        return num>0;
    }
    public static boolean isPositive(Double num){
        return num>0;
    }
    public static boolean isNegative(Integer num){
        return num<0;
    }
   public static boolean isNegative(Double num){
        return num<0;
   }
  //Input Validation Methods (Overloaded)
  public static boolean isValidAge(Integer age) {
        return age>=0 && age<=200;
  }
public static boolean isValidAge(LocalDate dateOfBirth){
    if(!isValidDate(dateOfBirth)){
        return false;
    }
    int age= LocalDate.now().getYear()-dateOfBirth.getYear();
    return isValidAge(age);
}

}

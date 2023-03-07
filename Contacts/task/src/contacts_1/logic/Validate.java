package contacts_1.logic;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validate {
    public static String formatName(String name) {
        if (name == null || name.isEmpty())
            return "[no data]";
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String formatPhone(String phoneNumber) {
        try {
            if (isNotValid(phoneNumber)) {
                throw new InputValidationException("Wrong number format!");
            }
            return phoneNumber;
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return "[no number]";
        }
    }

    public static String formatDate(String date) {
        try {
            if (!isValidDate(date)) {
                throw new InputValidationException("Bad birth date!");
            }
            return date;
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return "[no data]";
        }
    }

    public static String formatGender(String gender) {
        try {
            if (!isValidGender(gender)) {
                throw new InputValidationException("Bad gender!");
            }
            return gender;
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return "[no data]";
        }
    }


    private static boolean isNotValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true;
        }
        String p1 = "^(\\+)?(\\d )?(\\()?\\w+(\\))?$";
        String p2 = "^(\\+\\d )?(\\()?\\d{3,}(\\))?[- ](\\()?\\w{2,}(\\))?($|([- ][-\\w ]{2,})$)";
        Pattern p1p = Pattern.compile(p1);
        Pattern p2p = Pattern.compile(p2);
        return !p1p.matcher(phoneNumber).find() && !p2p.matcher(phoneNumber).find();
    }

    public static boolean isValidInput(int input, int max) {
        try {
            if (input < 1 || input > max)
                throw new InputValidationException("Invalid id!");
            return true;
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean isValidDate(String testDate) {
        if (testDate == null || testDate.isEmpty()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(testDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean isValidGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return false;
        }
        String male = "M";
        String female = "F";
        return !gender.matches(male) && !gender.matches(female);
    }
}





package util;

import domain.core.model.enums.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class InputUtil {


    private static InputUtil instance;

    public static InputUtil getInstance() {
        if (instance == null) {
            instance = new InputUtil();
        }
        return instance;
    }

    private InputUtil() {
    }

    public String inputTypeString(String title) {
        Scanner scan = new Scanner(System.in);
        System.out.print(title);
        return scan.nextLine();
    }

    public Long inputTypeLong(String title) {
        Scanner scan = new Scanner(System.in);
        long input;
        while (true) {
            try {
                System.out.print(title);
                input = Long.parseLong(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return input;
    }

    public LocalDate inputTypeLocalDate(String title) {
        Scanner scan = new Scanner(System.in);
        LocalDate input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            try {
                System.out.print(title);
                String inputString = scan.nextLine();
                input = LocalDate.parse(inputString, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input. Please enter a date in the format yyyy-MM-dd.");
            }
        }
        return input;

    }

    public Integer inputTypeInteger(String title) {
        Scanner scan = new Scanner(System.in);
        int input;
        while (true) {
            try {
                System.out.print(title);
                input = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return input;
    }

    public Byte inputTypeByte(String title) {
        Scanner scan = new Scanner(System.in);
        byte input;
        while (true) {
            try {
                System.out.print(title);
                input = Byte.parseByte(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid byte.");
            }
        }
        return input;
    }

    public Double inputTypeDouble(String title) {
        Scanner scan = new Scanner(System.in);
        double input;
        while (true) {
            try {
                System.out.print(title);
                input = Double.parseDouble(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid byte.");
            }
        }
        return input;
    }


    public Role inputTypeRole(String title) {
        Scanner scan = new Scanner(System.in);
        Role inputRole;

        while (true) {
            try {
                System.out.print(title);
                String inputString = scan.nextLine().trim().toUpperCase();

                inputRole = Role.valueOf(inputString);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid role from " + String.join(", ", Role.names()));
            }
        }

        return inputRole;
    }


    public boolean inputTypeBoolean(String title) {
        Scanner scan = new Scanner(System.in);
        boolean input;

        while (true) {
            try {
                System.out.print(title);
                String inputString = scan.nextLine().trim().toLowerCase();
                if ("true".equals(inputString)) {
                    input = true;
                    break;
                } else if ("false".equals(inputString)) {
                    input = false;
                    break;
                } else {
                    throw new IllegalArgumentException("Invalid input. Please enter 'true' or 'false'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }


}

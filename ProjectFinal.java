package mypack;

import java.util.Random;
import java.util.Scanner;

public class ProjectFinal {
    Scanner scnr = new Scanner(System.in);

    public String password = null;
    public int passLength;
    public int lowercaseLetters;
    public int capitalLetters;
    public int numbers;
    public int symbols;
    public int rating;

    public void submitPassword() {
        lowercaseLetters = 0;
        capitalLetters = 0;
        numbers = 0;
        symbols = 0;

        passLength = password.length();

        for (int i = 0; i < passLength; ++i) {
            char var = password.charAt(i);

            if (Character.isLetter(var)) {
                if (Character.isUpperCase(var)) {
                    capitalLetters++;
                } else {
                    lowercaseLetters++;
                }
            } else if (Character.isDigit(var)) {
                numbers++;
            } else {
                symbols++;
            }
        }
    }

    public void checkStrength() {
        System.out.println("\nPassword: " + password);
        System.out.println("Lowercase Letters: " + lowercaseLetters);
        System.out.println("Capital Letters: " + capitalLetters);
        System.out.println("Numbers: " + numbers);
        System.out.println("Symbols: " + symbols);

        rating = (lowercaseLetters + capitalLetters * 2 + numbers * 2 + symbols * 5);

        System.out.println("Password strength rating: " + rating);

        if (rating <= 10) {
            System.out.println("Extremely weak password.");
        } else if (rating <= 25) {
            System.out.println("Weak password.");
        } else if (rating <= 40) {
            System.out.println("Average password.");
        } else if (rating <= 60) {
            System.out.println("Strong password.");
        } else {
            System.out.println("Very strong password.");
        }
    }

    public void suggestions() {
        if (rating >= 60) {
            return;
        }

        System.out.println("\nSuggestions to improve your password:");

        if (passLength < 12) {
            System.out.println("- Increase the length to at least 12 characters.");
        }

        if (lowercaseLetters == 0) {
            System.out.println("- Add lowercase letters.");
        }

        if (capitalLetters == 0) {
            System.out.println("- Add uppercase letters.");
        }

        if (numbers == 0) {
            System.out.println("- Add numbers.");
        }

        if (symbols == 0) {
            System.out.println("- Add special symbols (e.g., !@#$%).");
        }

        if (symbols > 0 && symbols < 2) {
            System.out.println("- Consider adding more symbols for extra strength.");
        }

        if (numbers > 0 && numbers < 2) {
            System.out.println("- Consider adding more numbers.");
        }

        if (password.toLowerCase().contains("password")) {
            System.out.println("- Avoid using common words like 'password'.");
        }

        if (password.matches(".*(.)\\1{2,}.*")) {
            System.out.println("- Avoid repeating the same character multiple times.");
        }
    }

    public String generateNewPassword() {
        Random r = new Random();
        String l = "abcdefghijklmnopqrstuvwxyz";
        String u = l.toUpperCase();
        String s = "!@#$%^&*()-_=+<>?";
        String password = "";

        boolean lo, up, sym, n;

        while (true) {
            password = "";
            lo = false;
            up = false;
            sym = false;
            n = false;

            int length = r.nextInt(12, 21);

            for (int i = 0; i < length; i++) {
                int num = r.nextInt(4);

                if (num == 0) {
                    password += l.charAt(r.nextInt(l.length()));
                    lo = true;
                } else if (num == 1) {
                    password += u.charAt(r.nextInt(u.length()));
                    up = true;
                } else if (num == 2) {
                    password += s.charAt(r.nextInt(s.length()));
                    sym = true;
                } else {
                    password += r.nextInt(10);
                    n = true;
                }
            }

            if (lo && up && sym && n) {
                return password;
            }
        }
    }

    public static void main(String[] args) {
        ProjectFinal app = new ProjectFinal();

        while (true) {
            System.out.println("\nEnter a password (leave blank to generate one):");
            String input = app.scnr.nextLine();

            if (input.equals("")) {
                app.password = app.generateNewPassword();
                System.out.println("\nGenerated password: " + app.password);

                while (true) {
                    System.out.println("\nChoose an option:");
                    System.out.println("1. Get another generated password");
                    System.out.println("2. Enter my own password");
                    System.out.println("3. End program");

                    String choice = app.scnr.nextLine();

                    if (choice.equals("1")) {
                        app.password = app.generateNewPassword();
                        System.out.println("\nGenerated password: " + app.password);

                    } else if (choice.equals("2")) {
                        break;

                    } else if (choice.equals("3")) {
                        System.out.println("Program ended.");
                        return;

                    } else {
                        System.out.println("Invalid input.");
                    }
                }

            } else {
                app.password = input;

                app.submitPassword();
                app.checkStrength();

                if (app.rating < 60) {
                    app.suggestions();
                }
            }
        }
    }
}

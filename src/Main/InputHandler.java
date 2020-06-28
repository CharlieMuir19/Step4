import java.text.DecimalFormat;
import java.util.Scanner;

//The InputHandler class decides whether input is valid and within range of scope for each process
// farmCode, batchWeight, deciding which option to take.
public class InputHandler {
    private static final Scanner reader = new Scanner(System.in); // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public InputHandler() {
    }

    //based upon the limits set in minChoice and maxChoice that is passed into the method,
    //the method calculates whether the input is valid, and if its valid then passes it back to be assigned in the Processor class.
    public static int decide(int minChoice, int maxChoice) {
        int tempchoice = 0;
        int choice = -1;
        boolean valid = false;
        if (reader.hasNextInt()) {
            tempchoice = reader.nextInt();
            if (tempchoice < minChoice) {
                System.out.println("No valid input - too low");
                decide(1, 2);
            } else if (tempchoice > maxChoice) {
                System.out.println("No valid input - too high");
                decide(1, 2);
            } else if (tempchoice >= minChoice && tempchoice <= maxChoice) {
                choice = tempchoice;
                valid = true;
            }
        } else if (reader.next().matches(".*[a-z].*")) {
            System.out.println("Invalid Input - TEXT");
            choice = -1;
        }
        return choice;
    }

    //the input handler recieves the filename from the user and returns back to the main processor
    public static String getFile() {
        String filename = "";
        if (reader.hasNext()) {
            filename = reader.next();
        }
        return filename;
    }

    //the input handler recieves the grade from the user and returns back to the main processor
    //after validating it is between limits to begin (0 to 100)
    public static int getGrade() {
        int grade = 0;
        if (reader.hasNextInt()) {
            int temp = reader.nextInt();
            if (temp < 0) {
                System.out.println("No valid input - too low");
                getGrade();
            } else if (temp > 100) {
                System.out.println("No valid input - too high");
            } else if (temp >= 0 && temp <= 100) {
                grade = temp;
            }
            return grade;
        }else if (reader.next().matches(".*[a-z].*")){
            System.out.println("Invalid Input - TEXT");
            grade = -1;
        }
        return grade;
    }

    //this method is used to get the price from user, and validate whether it is positive or negative.
    //if negative, we ask again to repeat back in the method where this is called,
    //if positive we pass the value back to be used and stored.
    //we format all input to 2 decimal places.
    public static double getPrice() {
        //sourced the decimal format for the price from an answer on a stack overflow forum
        //https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places

        double price = 0;
        if(reader.hasNextDouble()){
            double temp = reader.nextDouble();
            if (temp<0){
                System.out.println("No valid input - too low");
                price = -1;
            } else{
                DecimalFormat df = new DecimalFormat("####0.00");
                price = Double.parseDouble(df.format(temp));
            }
        } else if (reader.next().matches(".*[a-z].*")){
            System.out.println("Invalid Input - TEXT");
            price = -1;
        } return price;
    }
}

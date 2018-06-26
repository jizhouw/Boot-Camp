// import the necessary packages
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class to hold all methods running the program
 * The program is to accept one number and a list of positive numbers
 * Then the result would return all the pairs of numbers in the list which add up to that number
 * Tips:
 *      All numbers including the first number and numbers in the list must be the integer
 *      All numbers in the list should be positive, which are greater than 0
 *      The duplicate numbers in the list would be ignored
 *      Two same numbers in one pair with different orders would be considered only once
 */
public class PairFinding {

    // The integer variable to hold the value of the first number
    private int firstNumber;

    // The integers arrayList to hold the list of all numbers (duplicate deleted)
    private ArrayList<Integer> integerList;

    // The strings arrayList to hold the list of all pairs
    private ArrayList<String> resultPairs;

    /**
     * The constructor without parameter to initialize some fields
     * Call the readInput method to read all user inputs
     * Call the printResult method to print the final output
     */
    private PairFinding(){
        integerList = new ArrayList<>();
        resultPairs = new ArrayList<>();
        this.readInput();
        this.getResult();
    }

    /**
     * The method to find all pairs in the list (duplicate numbers are ignored)
     * Insert every pair to the array list of resultPairs
     */
    private void findPairs(){
        // Loop to delete the duplicate elements in the list
        for (int i = 0; i < integerList.size() - 1; i++ ){
            for (int j = integerList.size() - 1; j > i; j-- ){
                if (integerList.get(j).equals(integerList.get(i))){
                    integerList.remove(j);
                }
            }
        }
        // Two for loops to control every two numbers are compared and added up together once
        for (int i = 0; i < integerList.size(); i++){
            int first = integerList.get(i);
            for (int j = i + 1; j < integerList.size(); j++){
                int second = integerList.get(j);
                if (first + second == firstNumber && first != second){
                    resultPairs.add("[" + integerList.get(i) + "," +  integerList.get(j) +  "] ");
                }
            }
        }
    }

    /**
     * The method to check the input of firstNumber is valid or not
     * The boolean value of true would be returned if the firstNumber is valid
     * The boolean value of false would be returned if the firstNumber is not valid
     *
     * @param input
     * @return boolean
     */
    private boolean firstNumberValid(String input){
        // Use try-catch to parse the string
        // If the exception is caught, it means the input value is invalid, the false would be returned
        try {
            firstNumber = Integer.parseInt(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * The method to check the input of the list of positive numbers is valid or not
     * The boolean value of true would be returned if the list numbers is valid
     * The boolean value of false would be returned if the list numbers is not valid
     *
     * @param inputList
     * @return boolean
     */
    private boolean numberListValid(String inputList){
        // Use try-catch to parse the string
        // If the exception is caught, it means the input value is invalid, the false would be returned
        try{
            // Split the string to store all numbers in the array
            String[] tempList = inputList.split(",");
            // If the array is empty, the input is invalid
            if (tempList.length == 0){
                return false;
            }
            // For loop to check every number in the array is valid or not
            for (String number: tempList){
                int tempNumber = Integer.parseInt(number.trim());
                // If statement to check the number is positive or not
                if (tempNumber <= 0){
                    return false;
                } else {
                    // Else add the input number to the list
                    integerList.add(tempNumber);
                }
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * The method to read all user inputs
     * Complete the process of the user interaction
     */
    private void readInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the first number: ");

        // Local boolean variable to check the input is valid or not
        boolean flag = false;

        // While loop to control the user input of first number to be valid
        while (!flag) {
            String tempFirstNumber = scanner.nextLine().trim();
            // Call firstNumberValid method to check the input and get the return value to flag
            flag = this.firstNumberValid(tempFirstNumber);
            if (!flag){
                System.out.print("(Invalid input! The number should be integer!) Please enter the first number again: ");
            }
        }

        System.out.println("Please input the list of positive integers (Split By ,):");

        // Control the flag to be false again to accept the input the list of positive numbers
        flag = false;
        while (!flag){
            String inputList = scanner.nextLine().trim();
            // Call numberListValid method to check the input and get the return value to flag
            flag = this.numberListValid(inputList);
            if (!flag){
                System.out.println("(Invalid List!) The number list should be all positive number (split by ,): ");
                integerList = new ArrayList<>();
            }
        }
    }

    /**
     * Method to print the final result
     */
    private void getResult(){
        System.out.println("**************************************************");
        System.out.println("The First Number is: " + firstNumber);
        System.out.print("The Positive Number List is: ");
        for (int number: integerList){
            System.out.print(number + ", ");
        }
        System.out.println();

        // Call findPairs method to assign all pairs to the resultPairs
        this.findPairs();

        // If the size of the resultPairs is 0, the console would output no result
        // If the size is not 0, all pairs would be returned by using for loop
        if (resultPairs.size() == 0){
            System.out.println("No number pairs are found!");
        } else {
            System.out.println("The pairs of numbers which add up to " + firstNumber + " are:");
            for (String pairList : resultPairs) {
                System.out.print(pairList);
            }
        }
        System.out.println();
        System.out.println("**************************************************");
    }

    /**
     * Main method to run the program
     * The program would be automatically run when the class is initialized
     * @param args
     */
    public static void main(String[] args) {
        // Initialize the new object of the class to run the default constructor for the class
        new PairFinding();
    }
}

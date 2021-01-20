

import java.util.Scanner;

/**
 * A simulation of Undo in an editor
 * 
 * @author mhernan
 * Created: Oct 20, 2020
 */
public class Undo {

    /**
     * Run to perform the Undo typing
     * Asks for user input
     * Performs undo typing using stacks
     * 
     * @param args
     */
    public static void main(String[] args) {
        String enteredLine;
        System.out.print("Enter a line: ");
        Scanner scnr = new Scanner(System.in);
        ArrayStackHW5<Character> undo = new ArrayStackHW5<>();

        enteredLine = scnr.nextLine();
        for (int i = 0; i < enteredLine.length(); i++) {
            if ((enteredLine.charAt(i) == '1') && (i != 0)) {
                undo.pop();
            } else if (enteredLine.charAt(i) == '9') {
                break;
            } else if ((Character.isUpperCase(enteredLine.charAt(i)))
                    || (Character.isLowerCase(enteredLine.charAt(i)))) {
                undo.push(enteredLine.charAt(i));
            } else if (enteredLine.charAt(i) == 95) {
                undo.push(enteredLine.charAt(i));
            }
            scnr.close();
        }
        String reverse = "";
        while (!undo.empty()) {
            reverse = reverse + undo.pop(); //Mirrors the contents of the stack
        }
        System.out.print(reverse); //Prints mirrored string
    }
}

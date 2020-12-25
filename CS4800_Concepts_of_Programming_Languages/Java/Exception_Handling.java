/** This program demonstrates exception handling in Java, and it performs the following features.
* Open a file (if fail, raise an exception and handle it by printing a file not found message).
* Read in two integer values from the file (if not integer type value or don’t have at least two values, raise exception and handle it by printing wrong data message),
* Divide the first value by the second one (raise the exception and handle it by assigning 0.0 as the quotient).
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exception_Handling {
	public static void main(String args[]) throws IOException {
        String filename = "number.txt";
        open_file (filename);
    }

    static void open_file(String filename) throws RuntimeException {
        try {
            BufferedReader rd = new BufferedReader (new FileReader (new File (filename)));
            Scanner read_file = new Scanner (rd);
            ArrayList<Integer> data = new ArrayList<> ();
            try {
                while (read_file.hasNext ()){
                    data.add (read_file.nextInt ());
                }
                if(data.size() < 2) {
                    throw new NumberFormatException();
                }
                for (int i = 0; i < data.size (); i++){
                    System.out.println (data.get (i));
                }
                try {
                    int x = (int) data.get (0);
                    int y = (int) data.get (1);
                    if(y == 0) {
                        throw new RuntimeException();
                    }
                    int divide = x/y;
                    System.out.println ("The division of number is " + divide);
                    read_file.close();
                }
                catch (RuntimeException e){
                    System.out.println ("The denominator cannot be zero.");

                }
            }
            catch (InputMismatchException e){
                System.out.println ("wrong data message: not integer type value");
            }
            catch (NumberFormatException e){
                System.out.println ("wrong data message: don’t have at least two values");
            }
        }
        catch (FileNotFoundException e){
            System.out.println ("a file not found");
            e.printStackTrace ();
        }
    }

}

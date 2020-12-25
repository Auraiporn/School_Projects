
//
// Name: 		Auksorn, Auraiporn
// Project: 	1
// Due: 		Friday September 20,2019
// Course:		cs-1400-03-f19
// Description:
// 				This program will prompt for the values of a b c to compute the quadratic equation.
//				The program will verify for the valid input values to compute the quadratic equation.
//				If the value a is equal to 0, it is not defined as a quadratic equation.
//				If the discriminant is less than 0, it will have an imaginary solution.
//				Otherwise, this program will print out the value of x1 and x2.

import java.util.Scanner;
public class QuadraticEquation {
	public static void main (String [] args) {
		System.out.println("A. Auksorn's Quadratic Equation Slover");
		System.out.println("");
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter values for a b c? (Enter each value then press enter): ");
		double a = keyboard.nextDouble();
		double b = keyboard.nextDouble();
		double c = keyboard.nextDouble();
		if (a == 0.0){
			System.out.println("Not a quadratic equation.");
		} else {
			double discriminant = (double) Math.pow(b,2) - (4.0*a*c);
			if (discriminant < 0.0) {
				System.out.println("Roots are imaginary.");
			} else {
				double x1, x2;
				x1 = (double) (-b + Math.sqrt(discriminant))/ (2.0 * a);
				x2 = (double) (-b - Math.sqrt(discriminant))/ (2.0 * a);
				System.out.println("x1 = " + x1);
				System.out.println("x2 = " + x2);
			}
		}
	}
}

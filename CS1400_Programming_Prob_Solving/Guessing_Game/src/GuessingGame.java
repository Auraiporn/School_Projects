// 
// Name: 		Auksorn, Auraiporn 
// Project: 	2 
// Due: 		Monday October 7, 2019 
// Course: 		cs-1400-03-f19 
// Description: 
//  			The program will generate a random number and obtain a secret number. Then, it prompts a user to guess a number.
//				The user only have 8 times to guess a number. If the guessing number is too high or too low, it will ask the user to 
//				keep guessing until reaching 8 times. When the user complete 8 times, it will print out the secret number. 
// 				If the user wants to stop playing and press "0" to exit, the program will also print out the secret number.
//				Otherwise, if the user's guess is right, it will tell how many guesses the user has been trying. 

import java.util.Random; 
import java.util.Scanner; 

public class GuessingGame { 
	public static void main (String [] args){ 
		System.out.println("A. Auksorn's Number Guessing Game\n"); 
		System.out.println("A secret number between 1-100 has been generated. . .\nType '0' to EXIT. . .\n"); 
		
	
		// Get a random number 
		Random generate = new Random(); 
		int secretnum = generate.nextInt(100); 
		Scanner keyboard = new Scanner(System.in); 
		int i = 1, count = 8, guesses = 1, guessnum = 0; 
		final int EXIT = 0;
		
		while (i <= count && i!= EXIT){ 
			System.out.print("Enter your guess? "); 
			guessnum = keyboard.nextInt(); 
			if (guessnum == secretnum){ 
				i += count; 
			} 
			else if (guessnum == 0){ 
				i += count; 
			} 
			else if (guessnum < secretnum){ 
				System.out.println("Guess is low, try again"); 
				guesses++; 
			} 
			else if (guessnum > secretnum) { 
				System.out.println("Guess is high, try again"); 
				guesses++; 
			} 
			i++; 
		} 
		if (guessnum == secretnum){
			System.out.printf("Correct in %d guesses.\n", guesses ); 
		}else { 
			System.out.printf("The secret number is %d.\n", secretnum); 
		}		
	} 
 }
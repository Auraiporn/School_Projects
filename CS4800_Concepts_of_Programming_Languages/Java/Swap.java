/**
 *  This program demonstrates a Java swap method that could swap values of two integer variables, and 
 *   a method call that swaps the values of the following two variables. 
 *   					int first = 7, second = 5;
*/
public class Swap {
	
	public static void swapObject(Seat one, Seat two){
		int temp = one.seatNo;
		one.seatNo = two.seatNo;
		two.seatNo = temp;
	}
		 
	public static void swap(int first, int second){
		int temp = first;
		first = second;
		second = temp;
		System.out.println("The value after swapping, first: " + first + " second " + second + "\n");
	}

	public static void main(String [] args){
		// Regular swap method for swapping two integers
		int first = 7, second = 5;
		System.out.println("The value before swapping, first: " + first + " second: " + second);
		swap(first, second);
		// Swap method to swap objects
		Seat one = new Seat(7);
		Seat two = new Seat(5);
		System.out.println("Before swapping the seat: ");
		System.out.println("Seat Number One: " + one.seatNo);
		System.out.println("Seat Number Two: " + two.seatNo + "\n");
		swapObject(one,two);
		System.out.println("After swapping the seat: ");
		System.out.println("Seat Number One: " + one.seatNo);
		System.out.println("Seat Number Two: " + two.seatNo);
	}
}

class Seat{
	int seatNo;
	Seat(int seatNo){
		this.seatNo = seatNo;
	}
}



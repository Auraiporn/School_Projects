public class PileOfBooksTest {

	public static void main(String[] args) {
		
		PileOfBooksInterface<String> myBooks = new PileOfBooks<>();
		// Check isEmpty() method. If it is empty return true, if it is not empty return false. 
		System.out.println("Is a pile of books empty?" + " " + myBooks.isEmpty());
		
		myBooks.add("English");
		myBooks.add("Geology");
		myBooks.add("Biology");
		myBooks.add("Philosophy");
		myBooks.add("History");
		myBooks.add("Math");
		
		System.out.println("These are the books'title in a pile of books:\n"+ myBooks.toString());
		int totalOfBooks = myBooks.getCurrentSize();
		String topBook = myBooks.displayContentOfTopBook(); // Return Math
		System.out.println(topBook + " is at the top of a pile of books."); 
		System.out.println("The total of a pile of books is " + totalOfBooks+"."); 
		topBook = myBooks.remove();
		System.out.println(topBook + " is removed from a pile of books.");
		topBook = myBooks.displayContentOfTopBook();
		System.out.println(topBook + " is now at the top of a pile of books.");
		topBook = myBooks.remove();
		System.out.println(topBook + " is already removed from a pile of books.");
		myBooks.clear();
		System.out.println("");
				
		PileOfBooksInterface<String> books = new PileOfBooksLinkedNode<>();
		books.add("Computer Science");
		books.add("Chemistry");
		System.out.println("Now, we have " + books.getCurrentSize() + " books.");
		books.remove();
		System.out.println("We remove one entry, and after removing one entry, the top index is " + books.displayContentOfTopBook() +".");
		books.add("Astronomy");
		books.add("Sociology");
		books.remove();
		System.out.println("We remove one entry, and now the top book is " + books.displayContentOfTopBook() + ".");	
		books.add("English");
		books.add("Geology");
		books.add("Biology");
		books.add("Philosophy");
		books.add("History");
		books.add("Math");
		books.clear();
		System.out.println(books.displayContentOfTopBook());
	}
}

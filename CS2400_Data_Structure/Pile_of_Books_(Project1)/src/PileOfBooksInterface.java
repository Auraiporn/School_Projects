/**An interface that describes the operations of pile's methods. */
public interface PileOfBooksInterface<T>
{
	/**Gets the current number of entries in a pile of books. 
		@return the integer number of entries currently in the pile of books. */ 
	public int getCurrentSize();
	
	/** See whether a pile of books is empty.
		@return True if a pile of books is empty, or false if not.*/
	public boolean isEmpty();
	
	/**Adds a new entry to a pile of books.
	   @param newEntry The object to be added as new entry.
	   @return True if the addition is successful, or false if not. */
	public void add(T newEntry);
	
	/**Removes one unspecified entry from a pile of books, if possible.
		@return Either the removed entry, if the removal was successful, or null. */
	public T remove();
	
	/** Display the item on the top of stack.
	  	@return Either the top index or throws an exception if the stack is empty.*/
	public T displayContentOfTopBook();
	
	/**Removes all entries in a pile of books */
	public void clear();
	 
} //end PileOfBooksInterface

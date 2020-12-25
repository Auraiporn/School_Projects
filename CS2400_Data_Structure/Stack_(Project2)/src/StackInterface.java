/**An interface that describes the operations of stack methods. */
public interface StackInterface <T>{

/** Add a new entry on the top of stack */
public void push(T newEntry);

/**Remove an entry from the top of stack*/
public T pop();

/**Retrieve the top of stack.*/
public T peek();

/**Check whether there is an element in the stack */
public boolean isEmpty();

/**Remove all entries or elements in the stack*/
public void clear();

}

/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class Stack<T>
{

    /*
     * Instance variables
     *
     * You should not add anything here or change the existing ones.
     */
    private LinkedList<T> ll;

    /*
     * Constructor for our Stack
     *
     * Initialize the instance variables to their default values
     */
    public Stack()
    {
        ll = new LinkedList<T>();
    }

    /*
     * peek
     *
     * return the value at the top of the stack
     *
     * Note: Return null if the stack is empty. This should not
     *       remove the element at the top of the stack.
     */
    public T peek()
    {
        if (ll.getSize() != 0)
        {
            return ll.getData(ll.getSize() - 1);
        }
        
        else 
        {
            return null;
        }
        
    }

    /*
     * pop
     *
     * return and remove the value at the top of the stack
     *
     * Note: Return null if the stack is empty.
     */
    public T pop()
    {
        if (ll.getSize() != 0)
        {
            T temp = ll.getData(ll.getSize() - 1);
            ll.remove(ll.getSize() - 1);
            return temp;
        }

        else
        {
            return null;
        }
    }

    /*
     * push
     * 
     * push data to the top of the stack
     *
     * data - T; The data to be pushed to the stack
     */
    public void push(T data)
    {
        ll.insert(ll.getSize(), data);
    }

    /*
     * clear
     * 
     * empty the stack
     */
    public void clear()
    {
        ll.clear();
    }

    /*
     * isEmpty
     * 
     * return true if there are no elements in the stack and false otherwise
     */
    public boolean isEmpty()
    {
        return (ll.getSize() == 0);
    }

    /*
     * getSize
     * 
     * return the number of elements in the stack
     */
    public int getSize()
    {
        return ll.getSize();
    }

    /*
     * toString
     * 
     * You do not have to implement this method, it is just to help you debug.
     * When you submit your assignment, your code should never print anything
     * meaning it should not have any System.out.println.
     * If it does you will get an automatic 0 as this breaks the autograder.
     */   
    public String toString()
    {
        return "";
    }
}
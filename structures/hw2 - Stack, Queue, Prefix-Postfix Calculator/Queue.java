/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class Queue<T>
{

    /*
     * Instance variables
     *
     * You should not add anything here or change the existing ones.
     * 
     * Capacity holds the value for the maximum number of elements
     * allowed in the queue at any time. If capacity is -1, the queue
     * can hold an unlimited amount of elements. Capacity will always be
     * either -1 or a positive integer (never 0).
     */
    private LinkedList<T> ll;
    private int capacity;
    
    /*
     * Constructor for the Queue
     *
     * Initialize the instance variables to their default values
     * 
     * The default value for capacity is -1
     */
    public Queue()
    {
        ll = new LinkedList<T>();
        this.capacity = -1;
    }

    /*
     * Constructor for the Queue
     *
     * Initialize the instance variables
     * 
     * Note: Capacity will always be either -1 or a positive integer (not 0)
     * You do not have to worry about incorrect capacity values being passed
     */
    public Queue(int capacity)
    {
        ll = new LinkedList<T>();
        this.capacity = capacity;
    }

    /*
     * peek
     *
     * return the value at the front of the queue
     *
     * Note: Return null if the queue is empty. This should not
     *       remove the element from the front of the queue.
     */
    public T peek()
    {
        return ll.getData(0);
    }

    /*
     * dequeue
     *
     * return and remove the value at the front of the queue
     *
     * Note: Return null if the queue is empty.
     */    
    public T dequeue()
    {
        if (!isEmpty())
        {
            T temp = ll.getData(0);
            ll.remove(0);
            return temp;
        }

        else
        {
            return null;
        }
    }

    /*
     * enqueue
     * 
     * enqueue data to the back of the queue
     *
     * data - T; The data to be added to the queue
     * 
     * Note: If the queue is full, you should remove the element at the front
     * of the queue before adding the new data
     */
    public void enqueue(T data)
    {
        if (isFull()) {ll.remove(0);}
        ll.insert(ll.getSize(), data);
    }

    /*
     * clear
     * 
     * empty the queue
     */
    public void clear()
    {
        ll.clear();
        this.capacity = -1;
    }

    /*
     * isEmpty
     * 
     * return true if there are no elements in the queue and false otherwise
     */
    public boolean isEmpty()
    {
        return ll.getSize() == 0;
    }

    /*
     * isFull
     * 
     * return true if the queue is at maximum capacity and false otherwise
     */
    public boolean isFull()
    {
        if (capacity == -1) 
        {
            return false;
        } 
        
        else 
        {
            return ll.getSize() == capacity;
        }
    }
    /*
     * getSize
     * 
     * return the number of elements in the queue
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
/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class CircularLinkedList<T>
{
    private Node<T> head;
    private int size;

    /*
     * Constructor for our Circular Linked List
     *
     * Initialize the instance variables to their default values
     */
    public CircularLinkedList()
    {
        head = null;
        size = 0;
    }

    /*
     * insert
     *
     * Insert a new node with the specified data into the specified index
     *
     * index - int; the location in which to insert the new node (indexed
     *              from 0)
     *
     * data - T; the data to be stored in the newly created node
     *
     * Note:  If the index is out of range ([0, size]), nothing should be
     *        inserted.
     *
     * Note: If the list is not empty and you insert at index 0, it should
     *       be inserted before the head and become the new head. However, if
     *       you insert at index |size|, it should be inserted before the head
     *       but should NOT become the new head.
     */
    public void insert(int index, T data)
    {
        if(index < 0 || index > size){
            return;
        } else {
            Node<T> newNode = new Node<T>(data);
            if (isEmpty()){
                head = newNode;
                newNode.setNext(head);
                newNode.setPrev(head);
            } else {
                Node<T> temp = head;
                for (int i = 0; i < index; i++){
                    temp = temp.getNext();
                }
                newNode.setNext(temp);
                newNode.setPrev(temp.getPrev());
                newNode.getPrev().setNext(newNode);
                newNode.getNext().setPrev(newNode);
                if (index == 0){head = newNode;}
            }
            size++;
        }
    }

    /*
     * insertAtHead
     *
     * Insert a new node at the head with the specified data
     *
     * data - T; the data to be stored in the newly created node
     *
     * Note: This should work even if the list is empty.
     */
    public void insertAtHead(T data)
    {
        insert(0, data);
    }

    /*
     * insertAtTail
     *
     * Insert a new node at the tail with the specified data
     *
     * data - T; the data to be stored in the newly created node
     *
     * Note: This should insert even if the list is empty.
     */
    public void insertAtTail(T data)
    {
        insert(size, data);
    }

    /*
     * remove
     *
     * Remove the first node with the specified data, if it exists
     *
     * data - T; the data to find and remove from the list
     *
     * Note: You should use x.equals(y) to compare generic data
     */
    public void remove(T data)
    {
        Node<T> temp = head;
        for (int i = 0; i < size; i++){
            if(temp.getData().equals(data)){
                temp.getPrev().setNext(temp.getNext());
                temp.getNext().setPrev(temp.getPrev());
                if (temp == head){head = temp.getNext();}
                size--;
            }
        }
    }

    /*
     * removeIndex
     *
     * Remove the node at the specified index
     *
     * index - int; the index to remove from
     *
     * Note: If the index is out of range do nothing
     */
    public void removeIndex(int index)
    {
        if(index < 0 || index > size || isEmpty()){
            return;
        } else if (size == 1) {
            clear();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index; i++){
                temp = temp.getNext();
            }
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
            if (index == 0){head = head.getNext();}
            size--;
        }
    }

    /*
     * removeFromHead
     *
     * Remove the head node of the list, if it exists
     */
    public void removeFromHead()
    {
        removeIndex(0);
    }

    /*
     * removeFromTail
     *
     * Remove the tail node of the list, if it exists
     */
    public void removeFromTail()
    {
        removeIndex(size-1);
    }

    /*
     * contains
     *
     * Check to see if the list contains a node with the specified data
     *
     * data - T; the data to check for
     *
     * Note: You should use x.equals(y) to compare generic data
     */
    public boolean contains(T data)
    {
        Node<T> temp = head;
        for (int i = 0; i < size; i++){
            if (temp.getData().equals(data)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /*
     * getIndex
     *
     * Return the index of the first node in the list with the specified data
     * or -1 if it does not exist
     *
     * data - T; the data to check for
     *
     * Note: You should use x.equals(y) to compare generic data
     */
    public int getIndex(T data)
    {
        Node<T> temp = head;
        for (int i = 0; i < size; i++){
            if (temp.getData().equals(data)){
                return i;
            }
            temp = temp.getNext();
        }
        return -1;
    }

    /*
     * getElementAtIndex
     *
     * Return the node at the specified index or null if it is out of range
     *
     * index - int; the desired index
     */
    public Node<T> getElementAtIndex(int index)
    {
        if (index < 0 || index > size){
            return null;
        } else if (index == 0){
            return head;
        } else if (index == size){
            return head.getPrev();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index; i++){
                temp = temp.getNext();
            }
            return temp;
        }
    }

    /*
     * clear
     *
     * Clear the linked list
     */
    public void clear()
    {
        head = null;
        size = 0;
    }

    /*
     * isEmpty
     *
     * Return whether or not the list is empty
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }

    /*
     * getHead
     *
     * Return the head node (possibly null)
     */
    public Node<T> getHead()
    {
        return head;
    }

    /*
     * getSize
     *
     * Return the number of elements in the linked list
     */
    public int getSize()
    {
        return size;
    }

    /*
     * toString
     *
     * Return a string representation of the circular linked list
     *
     * Note: We will not be grading this, it is only for you to be able to
     *       easily display the contents of a list by using something
     *       like "System.out.println(cll);". Remember not to have any printed
     *       output in any of the other methods.
     */
    public String toString()
    {
        return "";
    }

    /*
     * Node<T> class
     *
     * This class describes a generic node object.
     *
     * You should not edit anything below this line but please note exactly what
     * is implemented so that you can use it in your linked list code
     */
    public class Node<T>
    {
        private Node<T> prev, next;
        private T data;

        public Node(T data)
        {
            this.data = data;
        }

        public void setNext(Node<T> next)
        {
            this.next = next;
        }

        public void setPrev(Node<T> prev)
        {
            this.prev = prev;
        }

        public void setData(T data)
        {
            this.data = data;
        }

        public Node<T> getNext()
        {
            return this.next;
        }

        public Node<T> getPrev()
        {
            return this.prev;
        }

        public T getData()
        {
            return this.data;
        }

        public void clearNext()
        {
            this.next = null;
        }

        public void clearPrev()
        {
            this.prev = null;
        }
    }
}

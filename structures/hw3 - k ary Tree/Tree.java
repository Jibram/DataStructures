/*
 * Name:
 * Student ID:
 */


/*
 * You should NOT import anything else.
 * 
 * For a quick summary of ArrayLists, see https://www.tutorialspoint.com/java/util/java_util_arraylist.htm
 */

import java.lang.Math;
import java.util.ArrayList;

public class Tree<T>
{

    /*
     * Instance variables
     *
     * You may add new instance variables and methods, but you should not
     * modify the variables and methods that are already defined.
     * 
     * size holds the number of elements currently in the tree
     * 
     * k holds the number of children a node can have
     * 
     * root holds the root node of the tree
     */
    private int size, k;
    private Node<T> root;

    /*
     * Constructor for the tree
     *
     * Initialize the instance variables to their default values
     * 
     * The default value for k is 2, meaning it is a binary tree
     */
    public Tree()
    {
        this.size = 0;
        this.k = 2;
        this.root = null;
    }

    /*
     * Constructor for the tree
     *
     * k - int; the number of children a node can have
     * 
     * Note: If k = 2, it is considered a binary tree
     * 
     * Note: k will always be an integer greater than or equal to 2.
     *       You do not have to consider edge cases where an invalid
     *       value for k is passed as an input.
     */
    public Tree(int k)
    {
        this.size = 0;
        this.k = k;
        this.root = null;
    }


    /*
     * insert
     *
     * Insert the data into the next available position
     * 
     * data - T; the data to be inserted
     * 
     * Note: The data should be inserted into the next available slot.
     *       Remember that the tree must always be complete, meaning
     *       each level must be full before starting on a new level and
     *       nodes are always added from left to right
     */
    public void insert(T data)
    {
        if (this.root == null){this.root = new Node<T>(data, null, this.k);}
        else
        {
            iterativeSearchWithQueue().addChild(data);
        }
        size++;
    }

    public Node<T> iterativeSearchWithQueue()
    {
        ArrayList<Node<T>> listQueue = convertToArrayList();
        listQueue.add(root);
        int index = 0;
        Node<T> temp = this.root;
        while (true)
        {
            if (temp.getChildren().size() < k) {return temp;}
            for (int i = 0; i < k; i++)
            {
                listQueue.add(temp.getChildren().get(i));
            }
            index++;
            temp = listQueue.get(index);
        }
    }

    /*
     * remove
     *
     * Remove the "last" node in the tree
     * 
     * Note: The tree must always be complete, so you this method should
     *       remove the right most node in the lowest level of the tree.
     */
    public void remove()
    {
        if (this.root == null) {return;}
        else if (this.size == 1) {clear();}
        else
        {
            ArrayList<Node<T>> tempTree = convertToArrayList();
            tempTree.get(tempTree.size() - 1).getParent().removeChild();
        }
        this.size--;
    }

    /*
     * preorder
     *
     * Return an arraylist of the nodes in the tree in preorder traversal order
     * 
     * Note: This should be implemented for all values of k.
     * 
     * Note: If the tree is empty, return null.
     */
    public ArrayList<Node<T>> preorder()
    {
        ArrayList<Node<T>> preOrderList = new ArrayList<Node<T>>();
        return preOrderTraversal(preOrderList, root);
    }

    public ArrayList<Node<T>> preOrderTraversal(ArrayList<Node<T>> preOrderList, Node<T> node)
    {
        preOrderList.add(node);
        for (int i = 0; i < node.getChildren().size(); i++) 
        {
            preOrderTraversal(preOrderList,node.getChildren().get(i));
        }
        return preOrderList;
    }

    /*
     * postorder
     *
     * Return an arraylist of the nodes in the tree in postorder traversal order
     * 
     * Note: This should be implemented for all values of k.
     * 
     * Note: If the tree is empty, return null.
     */
    public ArrayList<Node<T>> postorder()
    {
        ArrayList<Node<T>> postOrderList = new ArrayList<Node<T>>();
        return postOrderTraversal(postOrderList, root);
    }

    public ArrayList<Node<T>> postOrderTraversal(ArrayList<Node<T>> postOrderList, Node<T> node)
    {
        for (int i = 0; i < node.getChildren().size(); i++)
        {
            postOrderTraversal(postOrderList, node.getChildren().get(i));
        }
        postOrderList.add(node);
        return postOrderList;
    }

    /*
     * inorder
     *
     * Return an arraylist of the nodes in the tree in inorder traversal order
     * 
     * Note: This should be implemented only for binary trees. If the
     *       tree is not a binary tree, you should return null.
     * 
     * Note: If the tree is empty, return null.
     */
    public ArrayList<Node<T>> inorder()
    {
        ArrayList<Node<T>> inOrderList = new ArrayList<Node<T>>();
        return inOrderTraversal(inOrderList, root);
    }

    public ArrayList<Node<T>> inOrderTraversal(ArrayList<Node<T>> inOrderList, Node<T> node)
    {
        if (node.getChildren().size() < k)
        {
            for (int i = 0; i < node.getChildren().size(); i++)
            {
                inOrderTraversal(inOrderList,node.getChildren().get(i));
            }
            inOrderList.add(node);
        }
        else
        {
            for (int i = 0; i < k - 1; i++)
            {
                inOrderTraversal(inOrderList,node.getChildren().get(i));
            }
            inOrderList.add(node);
            inOrderTraversal(inOrderList,node.getChildren().get(k - 1));
        }
        return inOrderList;
    }

    /*
     * convertToArrayList
     *
     * Return an ArrayList representing the tree.
     * 
     * Note: See page 19 of Lecture 5 for information.
     * 
     * Note: This should be implemented for all values of k. For example,
     *       the children of a node at index i in a k-ary tree should
     *       be stored from index k*i+1 to index k*i+k. The root should be
     *       at index 0.
     * 
     * Note: If the tree is empty, return null.
     * 
     */
    public ArrayList<Node<T>> convertToArrayList()
    {
        ArrayList<Node<T>> treeArrayList = new ArrayList<Node<T>>();
        treeArrayList.add(this.root);
        Node<T> temp = this.root;
        int index = 0;
        while (temp.getChildren().size() > 0)
        {
            for (int i = 0; i < temp.getChildren().size(); i++)
            {
                treeArrayList.add(temp.getChildren().get(i));
            }
            index++;
            temp = treeArrayList.get(index);
        }
        return treeArrayList;
    }

    /*
     * getSize
     *
     * Return the number of elements in the tree
     */
    public int getSize()
    {
        return this.size;
    }

    /*
     * clear
     *
     * Clear the tree
     */
    public void clear()
    {
        this.size = 0;
        this.root = null;
    }

    /*
     * contains
     * 
     * data - T; the data to be searched for
     *
     * Return true if there is a node in the tree with the specified data and
     * false otherwise.
     */
    public boolean contains(T data)
    {
        ArrayList<Node<T>> tempTree = convertToArrayList();
        for (int i = 0; i < tempTree.size(); i++)
        {
            if (tempTree.get(i).equals(data)) {return true;}
        }
        return false;
    }

    /*
     * getDepth
     *
     * Return the number of levels in the tree.
     * 
     * Note: The root node counts as one level. The only tree with depth
     *       equal to 0 is the empty tree.
     */
    public int getDepth()
    {
        if (this.root == null) {return 0;}
        else
        {
            Node<T> temp = root;
            int depth = 1;
            while (temp.getChildren().size() > 0)
            {
                temp = temp.getChildren().get(0);
                depth++;
            }
            return depth;
        }
    }

    /*
     * isPerfect
     *
     * Return true if the tree is currently perfect and false otherwise.
     * 
     * Note: A perfect tree is a complete k-ary tree where all of the levels
     *       are full. In other words, inserting a node into this tree would
     *       force it to begin another level.
     * 
     * Note: The empty tree is perfect.
     */
    public boolean isPerfect()
    {
        int depth = 0;
        if (this.root == null) {return true;}
        else
        {
            Node<T> temp = this.root;
            depth = 1;
            while (temp.getChildren().get(k - 1) != null)
            {
                temp = temp.getChildren().get(k - 1);
                depth++;
            }
            return (getDepth() == depth);
        }
    }

    /*
     * getLast
     *
     * Return the right most node in the lowest level of the tree
     * 
     * Note: If the tree is empty, return null.
     */   
    public Node<T> getLast()
    {
        if (this.root == null) {return null;}
        else if (getSize() == 1) {return this.root;}
        else
        {
            ArrayList<Node<T>> temp = convertToArrayList();
            return temp.get(temp.size() - 1);
        }
    }

    /*
     * getRoot
     *
     * Return the root of the tree
     */   
    public Node<T> getRoot()
    {
        return root;
    }

    /*
    * You should NOT change anything below this line.
    * 
    * Pay close attention to what has been implemented already and
    * what you need to implement on your own (in the Tree class).
    */
    public class Node<T>
    {
        private T data;
        private Node<T> parent;
        private ArrayList<Node<T>> children;
        //This arraylist contains the children of the node in order from left to right

        public Node(T data, Node<T> parent, int k)
        {
            this.parent = parent;
            this.data = data;
            children = new ArrayList<Node<T>>(k);
        }

        public Node<T> getParent()
        {
            return this.parent;
        }

        public T getData()
        {
            return this.data;
        }

        public ArrayList<Node<T>> getChildren()
        {
            return children;
        }
        
        /*
         * This will append to the end of the children arraylist.
         * You need to perform the bounds checks yourself for when
         * the node has the maximum amount of children.
         */
        public void addChild(T data)
        {
            children.add(new Node<T>(data, this, k));
        }

        /*
         * This will remove the right most child of the current node,
         * if it exists.
         */
        public void removeChild()
        {
            if(children.size() > 0)
                children.remove(children.size()-1);
        }

        public void setParent(Node<T> n)
        {
            parent = n;
        }

        public void clearParent()
        {
            parent = null;
        }
    }
}
import javax.net.ssl.ExtendedSSLSession;

/* 
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class AVLTree
{
    /* 
     * Our instance variables.
     *
     * root - AVLTreeNode, root of our AVLTree
     * size - int, the number of elements in our AVLTree
     * 
     * You are NOT allowed to add your own instance variables
     */    
    
    private AVLTreeNode root;
    private int size;

    /*
    * Our constructor. 
    * Initialize the instance variables to their default values
    */
    public AVLTree()
    {
        root = null;
        size = 0;
    }

    /*
    * Constructs a new AVLTreeNode and inserts it into our AVLTree
    *
    * return the inserted AVLTreeNode or null if a node with the same data
    * already exists
    */
    public AVLTreeNode insert(int data)
    {
        AVLTreeNode node = new AVLTreeNode(data);
        if (isEmpty()) {root = node;}
        else {node =  insertHelper(root, node);}
        if (node != null) {size++;}
        return node;
    }

    public AVLTreeNode insertHelper(AVLTreeNode treeNode, AVLTreeNode node)
    {
        if (treeNode.getData() == node.getData())
        {
            return null;
        }
        else if (node.getData() < treeNode.getData())
        {
            if (treeNode.getLeft() == null) 
            {
                treeNode.setLeft(node); 
                return node;
            }
            else {insertHelper(treeNode.getLeft(), node);}
        }
        else if (treeNode.getData() < node.getData())
        {
            if (treeNode.getRight() == null) 
            {
                treeNode.setRight(node); 
                return node;
            }
            else {insertHelper(treeNode.getRight(), node);}
        }
        
        treeNode.setHeight(height(treeNode));
        treeNode.setBalanceFactor(height(treeNode.getRight()) - height(treeNode.getLeft()));

        System.out.println(treeNode.getData() + " height is " + treeNode.getHeight() + " and balance factor is " + treeNode.getBalanceFactor());
        System.out.println(treeNode.getRight().getData() + " height is " + treeNode.getRight().getHeight() + " and balance factor is " + treeNode.getRight().getBalanceFactor());
        
        if (treeNode.getBalanceFactor() < -1 && treeNode.getLeft().getBalanceFactor() == -1) {return rotateRight(treeNode);}
        else if (treeNode.getBalanceFactor() > 1 && treeNode.getRight().getBalanceFactor() == 1) {return rotateLeft(treeNode);}
        else if (treeNode.getBalanceFactor() < -1 && treeNode.getLeft().getBalanceFactor() == 1)
        {
            treeNode.setLeft(rotateLeft(treeNode.getLeft()));
            return rotateRight(treeNode);
        }
        else if (treeNode.getBalanceFactor() > 1 && treeNode.getRight().getBalanceFactor() == -1)
        {
            treeNode.setRight(rotateRight(treeNode.getRight()));
            return rotateLeft(treeNode);
        }


        return node;
    }

    public AVLTreeNode rotateRight(AVLTreeNode node)
    {
        AVLTreeNode node2 = node.getLeft();
        AVLTreeNode node2Tree = node2.getRight();

        node2.setRight(node);
        node.setLeft(node2Tree);

        height(node);
        height(node2);

        if (root == node)
        {
            root = node2;
        }
        else
        {
            AVLTreeNode parent = findParent(root, node);
            if (parent.getLeft() == node) {parent.setLeft(node2);}
            else {parent.setRight(node2);}
        }
        return node2;
    }

    public AVLTreeNode rotateLeft(AVLTreeNode node)
    {
        AVLTreeNode node2 = node.getRight();
        AVLTreeNode node2Tree = node2.getLeft();

        node2.setLeft(node);
        node.setRight(node2Tree);

        height(node);
        height(node2);

        if (root == node)
        {
            root = node2;
        }
        else
        {
            AVLTreeNode parent = findParent(root, node);
            if (parent.getLeft() == node) {parent.setLeft(node2);}
            else {parent.setRight(node2);}
        }
        return node2;
    }

    public AVLTreeNode findParent(AVLTreeNode possibleParent, AVLTreeNode node)
    {
        if (node == getRoot()) {return null;}
        if (possibleParent.getLeft().getData() == node.getData() || possibleParent.getRight().getData() == node.getData())
        {
            return possibleParent;
        }
        else if (node.getData() < possibleParent.getData())
        {
            return findParent(possibleParent.getLeft(), node);
        }
        else
        {
            return findParent(possibleParent.getRight(), node);
        }
    }


    /*
    * returns the node in the tree containing the desired data
    *
    * return null if such a node does not exist
    */
    public AVLTreeNode retrieve(int data)
    {
        if (isEmpty()) {return null;}
        else {return retrieveHelper(root, data);}
    }

    public AVLTreeNode retrieveHelper(AVLTreeNode node, int data)
    {
        if (node.getData() == data){return node;}
        else if (data < node.getData()) 
        {
            if (node.getLeft() == null){return null;}
            else {return retrieveHelper(node.getLeft(), data);}
        }
        else 
        {
            if (node.getRight() == null){return null;}
            else {return retrieveHelper(node.getLeft(), data);}
        }
    }

    /*
    * return whether or not the tree contains a node with the desired data
    */
    public boolean contains(int data)
    {
        AVLTreeNode node = new AVLTreeNode(data);
        return containsHelper(getRoot(), node);
    }

    public boolean containsHelper(AVLTreeNode parent, AVLTreeNode node)
    {
        if (parent == null)
        {
            return false;
        }        
        else if (node.getData() < parent.getData())
        {
            return containsHelper(parent.getLeft(), node);
        }
        else if (parent.getData() < node.getData())
        {
            return containsHelper(parent.getRight(), node);
        }
        else
        {
            return true;
        }
    }

    /*
    * remove and return the AVLTreeNode with the desired data
    *
    * return null if the data is not in the AVLTree
    */
    public AVLTreeNode remove(int data)
    {
        if (isEmpty()) {return null;}
    }

    /*
    * clear the AVLTree
    */
    public void clear()
    {
        root = null;
        size = 0;
    }

    /*
    * return whether or not the AVLTree is empty
    */
    public boolean isEmpty()
    {
        if (root == null) {return true;}
        else {return false;}
    }

    /*
    * return the root of the AVLTree
    */
    public AVLTreeNode getRoot()
    {
        return root;
    }

    /*
    * return the size of the AVLTree
    */
    public int getSize()
    {
        return size;
    }

    /*
    * return the height of the AVLTree
    */
    public int getHeight()
    {
        if (root == null) {return -1;}
        else
        {
            return height(getRoot());
        }
    }

    public int height(AVLTreeNode node)
    {
        if (node == null) {return -1;}
    
        int left = height(node.getLeft());
        int right = height(node.getRight());

        node.setBalanceFactor(right - left);

        if (left > right) {node.setHeight(left + 1); return left + 1;}
        else {node.setHeight(right + 1); return right + 1;}
    }
}

class AVLTreeNode
{

    /* 
     * Our instance variables.
     *
     * data - int, the data the AVLTreeNode will hold
     * height - int, the height of the AVLTreeNode
     * balanceFactor - int, the balance factor of the node
     * left - AVLTreeNode, the left child of the node
     * right - AVLTreeNode, the right child of the node
     */

    private int data, height, balanceFactor;
    private AVLTreeNode left, right;

    /*
    * Our constructor. 
    * Initialize the instance variables to their default values or the
    * values passed as paramters
    */
    public AVLTreeNode(int data)
    {
        setData(data);
        setHeight(0);
        setBalanceFactor(0);
        setLeft(null);
        setRight(null);
    }

    /*
    * Set the value stored in data
    */
    public void setData(int data)
    {
        this.data = data;
    }

    /*
    * Set the value stored in height
    */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /*
    * Set the value stored in balanceFactor
    */
    public void setBalanceFactor(int balanceFactor)
    {
        this.balanceFactor = balanceFactor;
    }

    /*
    * Set the left child
    */
    public void setLeft(AVLTreeNode left)
    {
        this.left = left;
    }


    /*
    * Set the right child
    */
    public void setRight(AVLTreeNode right)
    {
        this.right = right;
    }

    /*
    * clear the left child
    */
    public void clearLeft()
    {
        left = null;
    }

    /*
    * clear the right child
    */
    public void clearRight()
    {
        right = null;
    }

    /*
    * get the data stored in the AVLTreeNode
    */
    public int getData()
    {
        return data;
    }

    /*
    * get the height of the AVLTreeNode
    */
    public int getHeight()
    {
        return height;
    }

    /*
    * get the balanceFactor of the AVLTreeNode
    */
    public int getBalanceFactor()
    {
        return balanceFactor;
    }

    /*
    * get the left child
    */
    public AVLTreeNode getLeft()
    {
        return left;
    }

    /*
    * get the right child
    */
    public AVLTreeNode getRight()
    {
        return right;
    }
}
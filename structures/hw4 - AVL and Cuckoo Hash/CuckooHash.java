/* 
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

import java.util.Random;

/* 
 * java.util.Random is so you can generate your own hash functions
 * You should not import anything else
 */

public class CuckooHash
{
    /* 
    * The instance variables.
    *
    * a1 - int, a in the first hash function
    * b1 - int, b in the first hash function
    * a2 - int, a in the second hash function
    * b2 - int, b in the second hash function
    * n - int, the initial size of each array
    * numElements - int, the number of elements currently in the Cuckoo Hash
    * chainLength - int, the length of the allowed chain before we resize
    * threshold - double, the point at which the arrays are too full and we resize
    * array1 - int[], the first hash table
    * array2 - int[], the second hash table
    * resized - boolean, set to true if the previous insertion caused a resize
    *           and false otherwise
    */
    private int a1, b1, a2, b2, n, numElements, chainLength;
    private double threshold;
    private int[] array1, array2;
    private boolean resized;

    /*
    * The constructor. Initialize the instance variables to their default
    * value or the value passed as a parameter.
    *
    * array1, array2 should initially be filled with 0's
    */
    public CuckooHash(int n, int chainLength, double threshold)
    {
        this.n = n;
        Random r = new Random();
        this.a1 = r.nextInt(n - 1) + 1;
        this.b1 = r.nextInt(n - 1);
        this.a2 = r.nextInt(n - 1) + 1;
        this.b2 = r.nextInt(n - 1);
        this.numElements = 0;
        this.chainLength = chainLength;
        this.threshold = threshold;
        this.array1 = new int[n];
        this.array2 = new int[n];
        for (int i = 0; i < n; i++)
        {
            this.array1[i] = 0;
            this.array2[i] = 0;
        }
    }

    /*
    * insert data into the CuckooHash if it is not already contained
    * be sure to update resized if necessary
    */
    public void insert(int data)
    {
        if (contains(data)) {return;}
        int insertValue = data;
        int tempValue;
        int bumpCount = 0;
        int arrayNumber = 1;
        if ((numElements+1) / (2 * n) >= threshold) {System.out.println("Resize due to threshold"); resize();}
        
        while(true)
        {
            if (arrayNumber == 1)
            {
                if (bumpCount > chainLength) {bumpCount = 0; System.out.println("Resize due to bumpCount"); resize();}
                if (array1[hash1(insertValue)] != 0)
                {
                    tempValue = array1[hash1(insertValue)];
                    array1[hash1(insertValue)] = insertValue;
                    insertValue = tempValue;
                    bumpCount++;
                    arrayNumber = 2;
                }
                else
                {
                    array1[hash1(insertValue)] = insertValue;
                    break;
                }
            }

            if (arrayNumber == 2)
            {   
                if (bumpCount > chainLength) {bumpCount = 0; System.out.println("Resize due to bumpCount"); resize();}
                if (array2[hash2(insertValue)] != 0)
                {
                    tempValue = array2[hash2(insertValue)];
                    array2[hash2(insertValue)] = insertValue;
                    insertValue = tempValue;
                    bumpCount++;
                    arrayNumber = 1;
                }
                else
                {
                    array2[hash2(insertValue)] = insertValue;
                    break;
                }
            }
        }
        numElements++;
    }

    /*
    * remove data from the CuckooHash
    */
    public void remove(int data)
    {
        if (array1[hash1(data)] == data) {array1[hash1(data)] = 0;}
        else if (array2[hash2(data)] == data) {array2[hash2(data)] = 0;}
        else {return;}
        numElements--;
    }
    /*
    * checks containment
    *
    * return true if data is in the CuckooHash
    */
    public boolean contains(int data)
    {
        return (array1[hash1(data)] ==  data || array2[hash2(data)] == data);
    }

    /*
    * The first hash function
    * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
    *
    * return the value computed by the first hash function
    */
    public int hash1(int x)
    {
        return (a1 * x + b1) % n;
    }

    /*
    * The second hash function
    * Remember, hashes are defined as (a,b,N) = ax+b (mod N)
    *
    * return the value computed by the second hash function
    */
    public int hash2(int x)
    {
        return (a2 * x + b2) % n;
    }

    /*
    * resize the CuckooHash and make new hash functions
    */
    public void resize()
    {
        //All varaibles
        this.n = n * 2;
        Random r = new Random();
        this.a1 = r.nextInt(n - 1) + 1;
        this.b1 = r.nextInt(n - 1);
        this.a2 = r.nextInt(n - 1) + 1;
        this.b2 = r.nextInt(n - 1);
        this.numElements = 0;
        //All actual resizing
        int[] tempArray1 = array1;
        int[] tempArray2 = array2;
        this.array1 = new int[n];
        this.array2 = new int[n];
        for (int i = 0; i < n; i++)
        {
            this.array1[i] = 0;
            this.array2[i] = 0;
        }
        for (int i = 0; i < tempArray1.length; i++)
        {
            if (tempArray1[i] != 0){insert(tempArray1[i]);}
        }
        for (int i = 0; i < tempArray2.length; i++)
        {
            if (tempArray2[i] != 0){insert(tempArray2[i]);}
        }
    }

    /*
    * return a1
    */
    public int getA1()
    {
        return a1;
    }

    /*
    * return b1
    */
    public int getB1()
    {
        return b1;
    }

    /*
    * return a2
    */
    public int getA2()
    {
        return a2; 
    }

    /*
    * return b2
    */
    public int getB2()
    {
        return b2;    
    }

    /*
    * return n
    */
    public int getN()
    {
        return n;
    }

    /*
    * return threshold
    */
    public double getThreshold()
    {
        return threshold;
    }

    /*
    * return chainLength
    */
    public int getChainLength()
    {  
        return chainLength;
    }

    /*
    * return array1
    */
    public int[] getArray1()
    {
        return array1;
    }

    /*
    * return array2
    */
    public int[] getArray2()
    {
        return array2;
    }

    /*
    * return the number of elements in the Cuckoo Hash
    */
    public int getNumElements()
    {
        return numElements;
    }

    /*
    * return the resized variable
    */
    public boolean getResized()
    {
        return resized;
    }
}
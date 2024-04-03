
package q1;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Ulaş YİĞİT
 */
public class NumberPlacementGame {

  //Write a programthat places the numbers from 0 to 24 on a 5x5 table according
  //to the rules given below.Implement your java program using methods.
  //Display all possible correct placements of your numbers in your program
    public static void main(String[] args) {
       int[][] table = new int[5][5];  //Creates a 5x5 table
         HashSet<String> setOfMatrix = new HashSet<String>();
        
        for(int z = 0; z<table.length; z++){
		for(int w=0; w<table.length; w++) {
		for(int i=0; i < 5; i++)
                Arrays.fill(table[i],-1);
        
        // Will Start with the Zero at every position and then call the
        // function to put other numbers in the matrix  
        for(int i=z;i<5;i++){
            for(int j=w;j<5;j++){
                putNumbers(table,5,5,i,j,0,setOfMatrix);
                    }
                }              
            }
        }
    }

    static int count=0;
    static void Matrix(int[][] table) //Print the Matrix 
    {
        System.out.println("Table number is "+count);
        for(int i=0;i<table.length;i++){
            for(int j=0;j<table[i].length;j++){
                System.out.print("["+table[i][j]+"] ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // This will add the final REsulted Matrix into the HashSet and if HashSet
    // already contains a similar matrix then return false else true
    
    public static boolean addInSet(HashSet<String> set, int[][] table,int r,int c)
    {
        String finalMatrix = new String("");
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                finalMatrix += table[i][j]+" ";
            }
        }
        if(set.contains(finalMatrix))
            return false;
        set.add(finalMatrix);
        return true;
    }
    
    // To check if placing the number
    public static boolean isEmpty(int i,int j,int r,int c,int[][] table)
    {
        if(i>=0 && i<r && j>=0 && j<c && table[i][j] == -1)
            return true;
        return false;
    }
    
    public static void putNumbers(int[][] table,int r,int c,int i,int j,int value,HashSet<String> set)
    {
        table[i][j] = value;
        if(value == 24)
        {
            if(addInSet(set,table,r,c))
                {
                    count++;
                    Matrix(table);
                }
            table[i][j] = -1;
            return;
        }
        // These both array will together tell the direction in which we can
        // keep the points and if empty then proceed towards next
        int[] row = new int[]{2, -2, 2, -2, 3, 0, -3, 0};
        int[] col = new int[]{-2,-2, 2, 2, 0, 3, 0, -3};
        
        
        for(int k=0;k<row.length;k++)
        {
            int currx = (i+row[k]);
            int curry = (j+col[k]);
            if( isEmpty(currx, curry, r, c, table) )
            {
                table[currx][curry] = value;
                putNumbers(table,r,c,currx,curry,value+1,set);
                table[currx][curry] = -1;
            }
        } 
    }
}
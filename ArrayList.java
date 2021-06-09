import java.util.Scanner;  
public class ArrayList   
{  
public static void main(String[] args)   
{  
int n;  
Scanner sc=new Scanner(System.in);  
System.out.print("How was the game:  ");  

n=sc.nextInt();  

//creates an array in the memory of length 10  
int[] array = new int[10];  
System.out.println("What should be improved: ");  
for(int i=0; i<n; i++)  
{  
//reading array elements from the user   
array[i]=sc.nextInt();  
}  
System.out.println("Did you win: ");  
// accessing array elements using the for loop  
for (int i=0; i<n; i++)   
{  
System.out.println(array[i]);  
    }  
  }  
} 
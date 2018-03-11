package sjsu.moon.cs146.project3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Tester {	
	 public static void main(String[] args) throws FileNotFoundException 
	 {
		 File file = new File("dictionary.txt");
		 Scanner sc = new Scanner(file);
		 RedBlackTree dictionary = new RedBlackTree();
		 double time = System.currentTimeMillis();
		 while(sc.hasNext())
		 {
			 dictionary.insert(sc.nextLine());
		 }
		 double endtime = System.currentTimeMillis();
		 System.out.println("Time of Inserting Dictionary Data into Red Black Tree");
		 System.out.println(endtime-time+" ms");
		 System.out.println();
		 file = new File("turing.txt");
		 sc = new Scanner(file);
		 System.out.println("Following Words were found in the RedBlackTree Dictionary:");
		 String result = "";
		 time = System.currentTimeMillis();
		 while(sc.hasNext())
		 {
			 result = dictionary.lookup(sc.next().toLowerCase()).key + " "+result;				 
		 }
		 endtime = System.currentTimeMillis();
		 System.out.print(result);
		 System.out.println();
		 System.out.println();
		 
		 System.out.println("Total Time of Looking Up Words from turing.txt in RedBlackTree Dictionary");
		 System.out.println(endtime-time+" ms");
		 System.out.println();
		 sc.close();
	 }
}

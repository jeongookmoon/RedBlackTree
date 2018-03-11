package sjsu.moon.cs146.project3;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

public class RBTTester {

	@Test
	public void test() {
		RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     
		"Color: 1, Key:D Parent: \n"+
		"Color: 1, Key:B Parent: D\n"+
		"Color: 1, Key:A Parent: B\n"+
		"Color: 1, Key:C Parent: B\n"+
		"Color: 1, Key:F Parent: D\n"+
		"Color: 1, Key:E Parent: F\n"+
		"Color: 0, Key:H Parent: F\n"+
		"Color: 1, Key:G Parent: H\n"+
		"Color: 1, Key:I Parent: H\n"+
		"Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
	@Test
	public void DictionaryTestNotFound()
	{	
		RedBlackTree dictionary = new RedBlackTree();	
		RedBlackTree test = new RedBlackTree();
		String fileName = "dictionary.txt";
		try
		{
			FileReader ifstream = null;
			ifstream = new FileReader(fileName);
			String line = null;
			BufferedReader in = new BufferedReader(ifstream);
			// reading each line and saving it in line, if this is null then while loop stops.
			
			double time = System.currentTimeMillis();
			while((line = in.readLine())!= null)
			{
				dictionary.insert(line);
			}
			double endtime = System.currentTimeMillis();
			System.out.println("Time of Inserting Dictionary Data into Red Black Tree");
			System.out.println(endtime-time+" ms");
			in.close();
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		String keyword = "sajkjkldsa";
		double time = System.currentTimeMillis();
		String expected = ""; // "" meaning not found
		assertEquals(expected, dictionary.lookup(keyword).key);
		double endtime = System.currentTimeMillis();
		System.out.println("Lookup Time for keyword, "+keyword);
		System.out.println(endtime-time+" ms");
		System.out.println();
	}
	
	@Test
	public void DictionaryTestFound()
	{	
		RedBlackTree dictionary = new RedBlackTree();	
		RedBlackTree test = new RedBlackTree();
		String fileName = "dictionary.txt";
		try
		{
			FileReader ifstream = null;
			ifstream = new FileReader(fileName);
			String line = null;
			BufferedReader in = new BufferedReader(ifstream);
			// reading each line and saving it in line, if this is null then while loop stops.
			
			double time = System.currentTimeMillis();
			while((line = in.readLine())!= null)
			{
				dictionary.insert(line);
			}
			double endtime = System.currentTimeMillis();
			System.out.println("Time of Inserting Dictionary Data into Red Black Tree");
			System.out.println(endtime-time+" ms");
			in.close();
		}
		catch (FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		String keyword = "minemaster";
		double time = System.currentTimeMillis();
		String expected = "minemaster"; // same as keyword
		assertEquals(expected, dictionary.lookup(keyword).key);
		double endtime = System.currentTimeMillis();
		System.out.println("Lookup Time for keyword, "+keyword);
		System.out.println(endtime-time+" ms");
		System.out.println();
	}
    
    public static String makeString(RedBlackTree t)
    {
       class MyVisitor implements RedBlackTree.Visitor {
          String result = "";
          public void visit(RedBlackTree.Node n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree t) {
    	{
    	       class MyVisitor implements RedBlackTree.Visitor {
    	          String result = "";
    	          public void visit(RedBlackTree.Node n)
    	          {
    	        	  
    	        	  if(!(n.key).equals("")){
    	        		  result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	        	  }
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }    
 }
  
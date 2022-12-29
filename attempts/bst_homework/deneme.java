import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class deneme {
 
	public static void main(String[] args) 
	{
		ArrayList<Integer>	bilal = new ArrayList<Integer>(Arrays.asList(1,2,3,4));

		Iterator<Integer>	iterator = bilal.iterator();

		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
	}
 
}
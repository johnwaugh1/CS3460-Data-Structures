import java.lang.Integer;
import java.util.Random;
import java.util.LinkedList;

public class Adversary
{
	public static void main(String [] args)
	{	
		if (args.length != 2)
		{
			System.out.println("Usage: java Adversary <input size> <hash function>");
			System.exit(0);
		}

		int n = Integer.parseInt(args[0]);
		int p = Integer.parseInt(args[1]);

		switch(p)
		{
			case 1: func1(n); break;
			case 2: func2(n); break;
			case 3: func3(n); break;
			case 4: func4(n); break;
			default: System.out.println("Invalid function number. Expected 1-4.");
		}
	}

	private static void func1(int n) {
	// TODO: Code to generate n keys that all get hashed to the same index using hash1.	
    int key = 0;
	for (int i = 0; i < n; i++)
    {
            System.out.println(key);
	    	key += n;
	}
	}

	private static void func2(int n) {
	// TODO: Code to generate n keys that all get hashed to the same index using hash2.
	for(int i = 0; i < n; i++)
    {
	    int key = (n + i);
        System.out.println(key);
	}
	}

	private static void func3(int n) {
	// TODO: Code to generate n keys that all get hashed to the same index using hash3.	
	for(int i = 0; i < n; i++)
    {
        int key = (i * 128189);
        System.out.println(key);
	}
	}

	private static void func4(int n) {
	// TODO: Code to generate n keys that all get hashed to the same index using hash4.
	int[] count = new int[n];
	@SuppressWarnings("unchecked")
	LinkedList<Integer>[] list = new LinkedList[n];

	for(int i = 0; i < n; i++)
    {	
	    list[i] = new LinkedList<>();
		count[i] = 0;
	}

	HashFunctions hf = new HashFunctions(n);
    int k = 0;
	int index = 0;
	boolean flag = false;

	for(int i = 0; i < n * n; i++)
	{
		k = i;
		index = hf.hash4(k);

		if(!list[index].contains(k))
		{
			list[index].add(k);
			count[index]++;
		}

		for(int j = 0; j < count.length; j++)
		{
			if(count[j] == 100)
			{			
				index = j;
				flag = true;
				break;
			}
		}

		if(flag = true)
		{
			break;
		}
	}	

		for(int i = 0; i < count[index]; i++)
		{
			System.out.println("Key: " + list[index].get(i));
			System.out.println("Hash: " + list[index]);
		}

	}
}


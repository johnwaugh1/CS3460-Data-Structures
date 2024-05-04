public class StringSet {
	private static class Node
	{
		public String data;
		public Node next;

		public Node(String data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}

	private Node[] table;
	private int size;

	public StringSet()
	{
		size = 0;
		table = new Node[100];
	}

	public void insert(String key)
	{

		if (size == table.length)
		{
			Node[] newTable = new Node[table.length * 2];
			for(int i = 0; i < table.length; i++)
			{
				Node current = table[i];
				while(current != null) 
				{
					int index = hash(current.data, newTable.length);
					if(newTable[index] == null)
					{
						newTable[index] = new Node(current.data, null);
					}
					else
					{
						Node newNode = new Node(current.data, null);
						Node last = newTable[index];
						while(last.next != null)
						{
							last = last.next;
						}
						last.next = newNode;
					}
					current = current.next;
				}
			}
			table = newTable;
		}
		
		// TODO: code for insert
		int hash = hash(key, table.length);

		if(table[hash] == null)
		{
			table[hash] = new Node(key, null);
		}
		else
		{
			Node newNode = new Node(key, null);
						Node last = table[hash];
						while(last.next != null)
						{
							last = last.next;
						}
						last.next = newNode;
		}
		size++;
	}

	public boolean find(String key)
	{
		// TODO: return true if the key is present, false otherwise
		Node currentNode = table[hash(key, table.length)];
		while(currentNode != null)
		{
			if((currentNode.data).equals(key))
			{
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	public void print()
	{
		// TODO: print the contents of the hash table
		for(int i = 0; i < table.length; i++)
		{
			Node current = table[i];
			while(current != null)
			{
				System.out.println("Key: " + current.data);
				System.out.println("Hash: " + hash(current.data, table.length));
				current = current.next;
			}
		}
	}

	public int hash(String key, int tableLength)
	{
		int h = 0;
		int prime = 919;
		// TODO: compute a polynomial hash function for the String k
		// the returned value should be a valid index into the table
		// i.e, in the range [0..n-1] where n = table.length
		for(int i = 0; i < key.length(); i++)
		{
			h = (h * prime + key.charAt(i)) % tableLength; //change to newTable length somehow
		}
		return h;
	}
}

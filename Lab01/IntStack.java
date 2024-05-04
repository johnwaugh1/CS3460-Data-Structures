public class IntStack
{
	// declare your private fields here
	private int[] stack;
	private int top;
	private int max;
	/**
	 * Create an empty stack.
	 */
	public IntStack(int size)
	{
		max = size;
		stack = new int[max];
		top = -1;
	}

	/** 
	 * Pushes an item onto the top of this stack.
	 * @param x
	 */
	public void push(int x)
	{
		if(top < max - 1)
		{
			stack[++top] = x;
		}
	}
	
	/** 
	 * Removes the object at the top of this stack and returns that object as the value of this function.
	 * @return int The value removed from the stack. If empty, returns -1
	 */
	public int pop()
	{
		if(top >= 0)
		{
			int value = stack[top--];
			return value;
		}
		else
		{
			return -1;
		}
	}

	public int peek()
	{
		if(top == -1)
		{
			return 0;
		}
		return stack[top];
	}
}

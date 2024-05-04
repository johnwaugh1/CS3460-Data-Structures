import java.util.Scanner;

public class MolecularMass {

    public static int value(char c)
    {
        char C = Character.toUpperCase(c);
        if(C == 'H')
        {
            return 1;
        }
        if(C == 'O')
        {
            return 16;
        }
        else
        {
            return 12;
        }
    }

    public MolecularMass(String molecule)
    {
        IntStack stack = new IntStack(molecule.length() + 1);
        stack.push(-2);

        for(int i = 0; i < molecule.length(); i++)
        {
            char c = molecule.charAt(i);
            if(c == '(')
            {
                stack.push(-1);
            }
            else if(c == 'C' || c == 'H' || c == 'O')
            {
                stack.push(value(c));
            }
            else if(c == ')')
            {
                int x = 0;
                while(stack.peek() != -1)
                {
                    x += stack.pop();
                }
                stack.pop();
                stack.push(x);
            }
            else if(Character.isDigit(c))
            {
                int x = c - 48;
                x *= stack.pop();
                stack.push(x);
            }
        }
        int result = 0;
        while(stack.peek() != -2)
        {
            result += stack.pop();            
        }
        System.out.println("The molecular mass of " + molecule + " is " + result);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the molecule: ");
        String molecule = scanner.nextLine();
        
        new MolecularMass(molecule);
    }
}

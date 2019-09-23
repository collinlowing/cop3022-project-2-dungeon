import java.util.Scanner;

public class Menu 
{
	public void menu(Room room)
	{
		String command = "";
		
		while (!command.equals("q"))
		{
			Scanner input = new Scanner(System.in);
			command = input.next();
			
			switch(command)
			{
				case "h":
					printHelp();
					break;
				case "l":
					System.out.print();
					break;
				case "i":
					
					break;
				case "g":
					
					break;
				case "d":
					
					break;
				case "t":
					
					break;
				case "q":
					
					break;
				default:
					System.err.print("ERROR: Please input a valid command.");
			}
		}
	}
}

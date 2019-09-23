import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Room [] storedRoom = parse(args);
		checkErrors(args);
		menu(storedRoom);
	}
	
	// Operates the player menu
	public static void menu(Room [] room)
	{
		Player player = new Player(room[0]);
		Item item;
		int current = 0;
		String command = "";
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Dungeon!");
		System.out.println("Type h for help");
		
		room[current].printRoom();
		room[current].toString();
		do
		{
			System.out.println("Command: ");
			
			command = input.next();
			String [] string;
			
			if(command.contains(" "))
			{
				string = command.split(" ");
			}
			
			else
			{
				string = new String [2];
				string[0] = command;
			}
			
			switch(string[0])
			{
				case "help":
				case "h":
					printHelp();
					break;
				case "look":
				case "l":
					room[current].printDesc();
					break;
				case "inventory":
				case "i":
					if(player.getInventory() == null)
					{
						System.out.println("No item is held.");
					}
					else
					{
						item = player.getInventory();
						System.out.print(item.toString());
					}
					break;
				case "go":
				case "g":
					if(string[1].equalsIgnoreCase("north"))
					{
						
					}
					if(string[1].equalsIgnoreCase("east"))
					{
						
					}
					if(string[1].equalsIgnoreCase("south"))
					{
						
					}
					if(string[1].equalsIgnoreCase("west"))
					{
						
					}
					player.setCurrentRoom(room[current]);
					
					room[current].printRoom();
					room[current].printDesc();
					break;
				case "drop":
				case "d":
					player.removeItem();
					break;
				case "take":
				case "t":
					item = room[current].getItem();
					if(item.getDescription().equals(string[1]))
					{
						player.addItem(room[current].getItem());
					}
					else
					{
						System.err.println("ERROR: No item was found.");
					}
					break;
				case "quit":
				case "q":
					System.out.println("Quitting program...");
					System.out.println("Goodbye!");
					System.exit(0);
					break;
				default:
					System.err.println("ERROR: Please input a valid command.");
			}
		}
		while(!(command.equals("q")));	
	}
	
	//
	// Prints help menu
	public static void printHelp()
	{
		System.out.println("h(elp) - print help menu");
		System.out.println("l(ook) - describe current location");
		System.out.println("i(nventory) - show current item");
		System.out.println("g(o) [direction] - go to portal N, E, S or W");
		System.out.println("d(rop) [item] - drop held item");
		System.out.println("t(ake) [item] - take item");
		System.out.println("q(uit) - exit program");
	}
	
	//
	// Parse command line arguments into variables
	public static Room [] parse(String[] string)
	{
		// Initialize counters
		int counter = 0;
		int i = 0;
		int j = 0;
		
		Room [] rooms = new Room[20];
		
		while(i < string.length)
		{
			rooms[j] = new Room();
			switch(counter)
			{
			case 0:
				rooms[j].setName(string[i]);
				counter++;
				i++;
				
				break;
			case 1: 
				rooms[j].setDesc(string[i]);
				counter++;
				i++;
				
				break;
			case 2:
				parseItem(string[i], i, rooms[j]);
				counter++;
				i++;
				
				break;
			case 3:
				for(int k = 0; k < 4; k++)
				{
					setAdjacent(Integer.parseInt(string[i]), rooms, k, j);
					i++;
				}
				counter = 0;
				j++;
				
				break;
			}
		}
		
		return rooms;
	}
	
	//
	// Parse the item and separate between description and value
	public static void parseItem(String string, int index, Room room)
	{
		Item [] item = new Item[20];
		String itemDesc [] = new String[20];
		int itemVal [] = new int[20];
		// If the string is still initialized as an empty string then there is no item in the room.
		if(!string.equals(""))
		{
			// Splits the string into a item name and value
			String delim = ":";
			String [] temp = string.split(delim);
			
			for(int i = 0; i < temp.length; i++)
			{
				switch(i)
				{
				case 0:
					itemDesc[index] = temp[i];
					break;
				case 1:
					itemVal[index] = Integer.parseInt(temp[i]);
					break;
				}
			}
			item[index] = new Item(itemDesc[index], itemVal[index]);
		}
		room.addItem(item[index]);
	}
	
	//
	//setting adjacent rooms
	public static void setAdjacent(int adj, Room [] room, int k, int j)
	{
		if(adj != -1)
		{
			switch(k)
			{
			case 0:
				room[j].setAdjacent(Directions.NORTH, room[adj]);
				break;
			case 1:
				room[j].setAdjacent(Directions.EAST, room[adj]);
				break;
			case 2:
				room[j].setAdjacent(Directions.SOUTH, room[adj]);
				break;
			case 3:
				room[j].setAdjacent(Directions.WEST, room[adj]);
				break;
			}
		}
	}

		//
		// Check for errors that would break program
		public static void checkErrors(String [] string)
		{
			// Checking for enough command line arguments to create a room.
			if(string.length < 7)
			{
				System.err.print("ERROR: Missing valid arguements, please try again with valid dungeon layout.");
				System.exit(1);
			}
			
			// Checking if item description is in the format of itemName:value
			for(int i = 2; i < string.length; i += 7)
			{
				if(!string[2].contains(":") && !(string[2].equals("")))
				{
					System.err.print("ERROR: Item arguement is invalid, please try again.");
					System.exit(1);
				}
			}
		}
	
	
}

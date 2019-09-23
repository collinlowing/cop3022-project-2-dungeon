//done
public class Room
{
	private String name;
	private String description;
	private Item contents;
	private Room [] adjRooms = new Room [4];
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDesc(String description)
	{
		this.description = description;
	}

	public void addItem(Item item)
	{
		this.contents = item;
	}
	
	public void removeItem(Item item)
	{
		this.contents = null;
	}
	
	public void setAdjacent(Directions direction, Room room)
	{
		switch(direction)
		{
		case NORTH:
			this.adjRooms[0] = room;
			break;
		case EAST:
			this.adjRooms[1] = room;
			break;
		case SOUTH:
			this.adjRooms[2] = room;
			break;
		case WEST:
			this.adjRooms[3] = room;
			break;
		}
	}
	
	public Boolean isAdj(Room room)
	{
		for(int i = 0; i < 4; i++)
		{
			if(this.adjRooms[i] == room)
				return true;
		}
		return false;
	}
	
	public void printDesc()
	{
		System.out.print(this.description);
	}
	
	public String toString()
	{
		return name + "\n" + description;
	}
	
	public void printRoom()
	{
		int r = 0;
		// North
		if(this.adjRooms[r] == null)
		{
			System.out.print("+");
			for(int j = 0; j < 22; j++)
			{
				System.out.print("-");
			}
			System.out.println("+");
		}
		else
		{
			System.out.print("+");
			for(int j = 0; j < 21; j++)
			{
				if(j == 10)
					System.out.print("  ");
				else
					System.out.print("-");
			}
			System.out.println("+");
		}
		r++;
		
		// West
		for(int i = 0; i < 3; i++)
		{
			System.out.print("|");
			System.out.format("%22s", " ");
			System.out.println("|");
		}
		if(this.adjRooms[r] == null)
		{
			System.out.print("|");
			System.out.format("%22s", " ");
		}
		else
		{
			System.out.format("%23s", " ");
		}
		r++;
		
		// East
		if(this.adjRooms[r] == null)
		{
			System.out.print("|");
		}
		else
		{
			System.out.print(" ");
		}
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print("|");
			System.out.format("%22s", " ");
			System.out.println("|");
		}
		r++;
		
		// South
		if(this.adjRooms[r] == null)
		{
			System.out.print("+");
			for(int i = 0; i < 22; i++)
			{
				System.out.print("-");
			}
			System.out.println("+");
		}
		else
		{
			System.out.print("+");
			for(int i = 0; i < 21; i++)
			{
				if(i == 10)
					System.out.print("  ");
				else
					System.out.print("-");
			}
			System.out.println("+");
		}
	}
}

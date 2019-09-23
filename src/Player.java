//
public class Player 
{
	private Item itemHeld;
	private Room currentLocation;
	
	public Player(Room room)
	{
		this.currentLocation = room;
	}
	
	public Room getCurrentRoom()
	{
		return currentLocation;
	}
	
	public Boolean setCurrentRoom(Room currentRoom)
	{
		if(currentLocation.isAdj(currentRoom))
		{
			currentLocation = currentRoom;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Item getInventory()
	{
		return itemHeld;
	}
	
	public int getNetWorth()
	{
		return itemHeld.getValue();
	}
	
	public void addItem(Item item)
	{
		itemHeld = item;
	}
	
	public void removeItem()
	{
		itemHeld = null;
		System.out.println("Item dropped.");
	}
}

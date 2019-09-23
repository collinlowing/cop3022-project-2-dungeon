//done
public class Item 
{
	private String description;
	private int value;
	
	public Item(String description, int value)
	{
		this.description = description;
		this.value = value;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public String getDescription()
	{
		
		return this.description;
	}
	
	public String toString()
	{
		return this.description + ":" + this.value;
	}
}

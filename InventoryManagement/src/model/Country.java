package model;

/*The class Country defines the details of each of the inventories*/

public class Country
{

	private String name;
	private int cost, qty;
	
	public Country(String name, int cost)
	{
		this.name = name;
		this.cost =cost;
		qty = 100;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public int getQuantity()
	{
		return qty;
	}
	
	public int getRemaining(int purchased)
	{
		return (qty - purchased);
	}
}


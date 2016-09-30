package controller;
import model.Order;
import view.DisplayOutput;

public class InventoryManagement 
{
	public static String output;
	
	public static void main(String args[])
	{
		Order order = new Order();
		order.takeOrder();
		
		DisplayOutput displayoutput = new DisplayOutput();
		displayoutput.display(output);
	}
	
	
}

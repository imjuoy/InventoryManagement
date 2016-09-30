package model;
import java.util.Scanner;
import controller.InventoryManagement;

/*The class Order accepts purchase order from users and processes them*/

public class Order 
{
	private String input;
	private int noOfUnits;
	private char countryCode;
	private int  transportCost;
	
	public Order()
	{
		input = null;
		noOfUnits = 0;
		countryCode = '\0';
		transportCost = 400;
		
	}
	
	public void takeOrder()
	{
		System.out.println("Enter your order (in the format <purchase_country>:<number_of_units_to be_ordered>):\n");
		Scanner sc = new Scanner(System.in);
		input = sc.next();
		sc.close();
		if(input!=null && !input.isEmpty())
			processOrder(input);
	}
	
	private void processOrder(String input)
	{
		Country brazil = new Country("Brazil",100);
		Country argentina = new Country("Argentina",50);
		
		
		int brazilCost = 0, argentinaCost = 0;
		int unitsToTransfer,extra;
		
		String[] splitInput = input.split(":");
		noOfUnits = Integer.parseInt(splitInput[1].trim());
		
		if(splitInput[0].trim().equalsIgnoreCase(brazil.getName()))
			countryCode = 'B';
		else if(splitInput[0].equalsIgnoreCase(argentina.getName()))
			countryCode = 'A';
		else
		{
			InventoryManagement.output = "We do not have any inventory in this country!\n";
			return;
		}
		
		if(noOfUnits < 1)
		{
			InventoryManagement.output = "0 : " + brazil.getQuantity() + " : " + argentina.getQuantity();
			return;
		}
		if(noOfUnits>200)
		{
			InventoryManagement.output = "Out of Stock : " + brazil.getQuantity() + " : " + argentina.getQuantity();
			return;
		}
			
		
		
		if(countryCode == 'B')
		{
			
			processor(brazil,argentina,noOfUnits);
			
		}
			
		else
		{	
			processor(argentina,brazil,noOfUnits);		
		}
	}
		
	public void processor(Country obj1,Country obj2,int noOfUnits)
	{
		int brazilCost=0,argentinaCost=0,unitsToTransfer=0,extra=0;
		if(noOfUnits<obj1.getQuantity())
		{
			brazilCost = noOfUnits * obj1.getCost();
			if(noOfUnits<10)
				unitsToTransfer = 1;
			else if(noOfUnits>10 && noOfUnits%10 != 0)
				unitsToTransfer = ((noOfUnits/10) + 1);
			else
				unitsToTransfer = (noOfUnits/10);
			argentinaCost = (noOfUnits * obj2.getCost()) + (unitsToTransfer*transportCost);
			if (brazilCost<= argentinaCost)
				InventoryManagement.output = brazilCost + " : " + (obj1.getQuantity() - noOfUnits) + " : " + obj2.getQuantity();
			else
				InventoryManagement.output = argentinaCost + " : " + obj1.getQuantity()  + " : " + (obj2.getQuantity() - noOfUnits);
				
		}
		
		if(noOfUnits>obj1.getQuantity())
		{
			brazilCost = obj1.getQuantity() * obj1.getCost();
			extra = noOfUnits-obj1.getQuantity();
			if(extra<10)
				unitsToTransfer = 1;
			else if(extra>10 && extra%10 != 0)
				unitsToTransfer = ((extra/10) + 1);
			else
				unitsToTransfer = (extra/10);
			argentinaCost = (extra * obj2.getCost()) + (unitsToTransfer*transportCost);
			InventoryManagement.output = (brazilCost+argentinaCost) + " : " + obj1.getRemaining(obj1.getQuantity()) + " : " + (obj2.getRemaining(extra));
			
		}
		
	}
	}
	



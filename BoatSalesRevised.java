	/*This is a boat sales Receipt generator, it calculates the cost of a boat ordered by someone, revised edition.
	 *By Robert Hannah
	 *	12/17/2018 
	 */

    import java.text.*;
	import java.util.*;
	
public class BoatSalesRevised {
	//Input variables
	static String iString;
	static String iBoat = "O";
	static String StringInt;
	//Integers
	static int iQty;
	static int iInt;
	static int Counter = 0;
	//Doubles
	static double cBoatCost;
	static double cPrepCost;
	static double cAcc;
	static double percent;
	static double MarkUp;
	static double Subtotal;
	static double Tax;
	static double Total;
	static double cGTotal = 0;
	//Output variables
	static String oGTotal;
	static String oTotal;
	static String oTax;
	static String oSub;
	static String oMark;
	static String oPrep;
	static String oCost;
	static String oAcc;
	//Loop varible
	static String inputNext = "Y";

	static Scanner myScanner;
	static NumberFormat nf;
	
	public static void main(String[] args) {
		
		//Call initialization(Init)
		init();
	
		while (inputNext.equals("Y")) {
		//call input
		input();
		
		//call calculations
		calcs();
		
		//call output
		output();
		//Prompt for next receipt
		System.out.print("Enter another Boat Receipt? Y or N: ");
		inputNext = myScanner.next().toUpperCase();
		}
		//call closing
		closing();
				
		}

	public static void init() {
		//set scanner to the Console
		myScanner = new Scanner(System.in);
		//change delimiter from blank space to Enter key
		//to allow spaces in strings
		myScanner.useDelimiter(System.getProperty("line.separator"));
		
		//set formatter to use U.S. currency format
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	}

	public static void input() {
		
		//get the boat type and make sure that it isn't invalid
		while(iBoat.equals("O")) {
				System.out.println("Please Enter Boat type  'B'  'P'  'S'  'C'  :  ");
				iString = myScanner.next().toUpperCase();
		//case structure to see what is selected, using a string as char doesn't easily work here.
				switch(iString) {
				case "B":
					iBoat = "Bass";
					percent = 0.33;
					break;
				case "P":
					iBoat = "Pontoon";
					percent = 0.25;
					break;
				case "S":
					iBoat = "Ski";
					percent = 0.425;
					break;
				case "C":
					iBoat = "Canoe";
					percent = 0.20;
					break;
				default:
					iBoat = "O";
					break;
				}				
				if(iBoat == "O") {
					System.out.println("Choice invalid, please choose the avalible choices");
				}
		}
		//get accessories value and make sure its valid
		do {
			try {
				System.out.println("Please Enter Boat Accesory  1 electronics, 2 ski package, 3 fishing package  :  ");
				iString = myScanner.next();
				iInt = Integer.parseInt(iString);
			}
					
			catch (Exception e) {
						System.out.println("Choice invalid, must be 1, 2 or 3");
						iInt = 0;
			}
				
			
			try {
				switch (iInt) {
				case 1:
					StringInt = "Electronics";
					cAcc = 5415.30	;
					break;
				case 2:
					StringInt = "Ski Package";
					cAcc = 3980.00;
					break;
				case 3:
					StringInt = "Fishing Package";
					cAcc = 345.45;
					break;
				default:
					System.out.println("Choice invalid, Must be 1, 2 or 3");
					iInt = 0;
				}
			}
			
			catch (Exception e) {
					System.out.println("Choice invalid, Must be numerical 1, 2 or 3 ");
					iInt = 0;
			}
		}while(iInt == 0);
	
		//get quantity and make sure that the value isn't invalid
		while(iQty > 25 || iQty < 1) {
				
			System.out.println("Please enter quantity '1-25 only' ");
			try {
				iString = myScanner.next();
				iQty = Integer.parseInt(iString);
			}
			catch (Exception e) {
				iQty = 0;
			}
			if (iQty > 25 || iQty < 1) {
				System.out.println("Choice invalid, must be between 1 and 25");
			}
		}
		
		//get the boat cost and make sure it isn't invalid
		while(cBoatCost <2500.00 || cBoatCost > 150000.00) {
			try {
				System.out.println("Please enter Boat cost: ");
				iString = myScanner.next();
				cBoatCost = Double.parseDouble(iString);
			}
			
			catch (Exception e) {
				cBoatCost = 0;
			}
			
			if (cBoatCost <2500.00 || cBoatCost > 150000.00) {
				System.out.println("Choice invalid, must be between 2,500.00 and 150,000.00");
			}
		}
		//get prep cost and also make sure the unit is valid and isn't invalid
		while(cPrepCost < 100.00 || cPrepCost > 9999.99) {
			try {
				System.out.println("Please enter Prep cost: ");
				iString = myScanner.next();
				cPrepCost = Double.parseDouble(iString);
			}
			
			catch (Exception e) {
				cPrepCost = 0;
			}
			
			if (cPrepCost < 100.00 || cPrepCost > 9999.99) {
				System.out.println("Choice invalid, must be between 100.00 and 9999.99");
			}
		}
		
	}
	
	public static void calcs() {
		//mathmatical calculations
		MarkUp = percent * cBoatCost;
		Subtotal = cBoatCost + cAcc + cPrepCost + MarkUp * iQty;
		Tax = Subtotal * 0.07;
		Total = Subtotal + Tax;
		
		//round the numbers off
		MarkUp = Math.round(MarkUp);
		Tax = Math.round(Tax);
		
		//add to grand total accumulator
		cGTotal += Total;
		Counter += 1;
	
	}
	public static void output() {
		//Formatting to monitary value to USD
		oTotal = nf.format(Total);
		oTax = nf.format(Tax);
		oSub = nf.format(Subtotal); 
		oMark = nf.format(MarkUp);
		oPrep = nf.format(cPrepCost); 
		oCost = nf.format(cBoatCost);
		oAcc = nf.format(cAcc);
		//Display
		System.out.format("%-30s%11s%n", "Boat Type: ", iBoat);
		System.out.format("%-30s%11s%n", "Accesories: ", StringInt);
		System.out.format("%-30s%11s%n", "Qauntity: ", iQty);
		System.out.format("%-30s%11s%n", "Boat Cost: ", oCost);
		System.out.format("%-30s%11s%n", "Accesories Cost: ", oAcc);
		System.out.format("%-30s%11s%n", "Prep Cost: ", oPrep);
		System.out.format("%-30s%11s%n", "Mark Up: ", oMark);
		System.out.format("%-30s%11s%n", "Subtotal: ", oSub);
		System.out.format("%-30s%11s%n", "Tax: ", oTax);
		System.out.format("%-30s%11s%n", "Total: ", oTotal);
		//zero out for the loop, otherwise it would just skip past using the previously entered data.
		iBoat = "O";
		iQty = 0;
		iInt = 0;
		cBoatCost = 0;
		cPrepCost = 0;
	
 	}
	public static void closing() {
		//format and display the grand totals
		oGTotal = nf.format(cGTotal);
		System.out.format("%-30s%11s%n", "Grand Total: ", oGTotal);
		System.out.format("%-30s%11s%n", "Total Orders: ", Counter);
		System.out.println("Program ended, have a good Day!");
	}

}


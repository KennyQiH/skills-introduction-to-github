import java.util.ArrayList;
import java.util.Scanner;

public class MainExpense {
	
	public static ArrayList<Float> Expenses = new ArrayList<Float>();
	public static ArrayList<String> Product = new ArrayList<String>();
	public static ArrayList<String> Food = new ArrayList<String>();
	public static ArrayList<String> Utilities = new ArrayList<String>();
	public static ArrayList<String> Transport = new ArrayList<String>();
	public static ArrayList<Float> FoodExpense = new ArrayList<Float>();
	public static ArrayList<Float> UtilitiesExpense = new ArrayList<Float>();
	public static ArrayList<Float> TransportExpense = new ArrayList<Float>();
	//ArrayList are set as a public list because of its multiple uses within different methods and it being
	//called multiple times.
	
	public static float StringToFloat(String str) {
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format, please re-enter a valid number");
			return -1;
		}
	} //This is a method to check if the input is a float and if not it returns a negative value
	  //so it loops
	
	public static int StringToInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, please re-enter using numbers");
			return -1;
		}
	}
	
 	public static void addExpense(){
		int y = 0;
		float amount = 0;
		int addFilter = 0;
		Scanner scanner = new Scanner(System.in);
		//They are being called at the start so that the variables can become global variables
		
		System.out.println("Enter the the name of the product: ");
		String product = scanner.next();
		Product.add(product);
		//The user enters an input the scanner then records the input and stores it in the variable
		//then the value of that variable is then added to the Product list
		
		while (amount < 1) {
			System.out.println("Enter the cost of the product: ");
			amount = StringToFloat(scanner.next());
			if (amount >= 0) {
				Expenses.add(amount);
				break;
			} else {
				System.out.println("This is an invalid amount.");
			}
		}	
		//This while loop checks if the string has a float value and depending on the condition,
		//the condition that is met is then ran and if not it asks the question again
	
		while (y < 1) {	
			System.out.println("Is this product a 'Food', 'Transport' or 'Utilities'");
			System.out.println("Enter the corresponding number \n1: Food \n2: Transport \n3: Utilities");
			addFilter = StringToInteger(scanner.next()); //Stores the corresponding category with an integer value

			switch (addFilter) {
			case 1:
				Food.add(product);
				FoodExpense.add(amount);
				y++; 					//Case 1 checks if 'addFilter' has the value of 1 (Food) category
				break;					//which stores then stores it in the Food lists and increments 1
			case 2:
				Transport.add(product);
				TransportExpense.add(amount);
				y++;					//Case 2 is similar to case 1 but storing it in transport lists
				break;					//and then increments 1 to prevent looping again
			case 3:
				Utilities.add(product);
				UtilitiesExpense.add(amount);
				y++;					//Case 3 is also similar but storing it in utilities lists
				break;
			default:					//This is output if neither of the conditions are met
				System.out.print("Invalid input.");
			}
		
		}
	}
	
	public static void viewExpenses() {
		int x = 0;
		for (String i : Product) {
			String products = Product.get(x);
			float expenses = (float) Expenses.get(x);
			System.out.println((x+1) + ". " + products + " costs " + expenses);
			x++;
		}
	}
	//This method allows the user to see all the inputs they have made, except the category as there
	//is a method to view the items by categories
	
	public static void calculateTotal() {
		int x = 0;
		float total = 0;
		for (Float i : Expenses) {
			Float expenses = Expenses.get(x);
			total += expenses;
			x++;
		}
		System.out.println("Total expenses: " + total);
	}
	//This method takes in all the float values and give a total at the end
	
	public static void filterByCategory() {
		int filter = 0;
		Scanner scanner = new Scanner(System.in);
		
		String[] categories = {"Food", "Transport", "Utilities"};
		ArrayList<ArrayList<String>> itemLists = new ArrayList<>();
		itemLists.add(Food);		//This adds the item into the food category
		itemLists.add(Utilities);	//This adds the item into the utilities category
		itemLists.add(Transport);	//This adds the item into the transport category
		
		ArrayList<ArrayList<Float>> expenseLists = new ArrayList<>();
		expenseLists.add(FoodExpense);		//
		expenseLists.add(UtilitiesExpense);	//All three do the same as itemLists.add()
		expenseLists.add(TransportExpense);	//
		
		boolean validSelection = false;
		
		while (!validSelection) {
			System.out.println("Select how you want it to be filtered.");
			System.out.println("Enter with the following number corresponding to the category:");
			System.out.println("1. Food"); 
			System.out.println("2. Transport"); 
			System.out.println("3. Utilities");
			filter = StringToInteger(scanner.next());
			
			if (filter >= 1 && filter <= 3) {	//Checks if filter meets the condition between 1 and 3
				validSelection = true;
				int index = filter - 1;			//index is one smaller than filter so it can call the
				System.out.println(categories[index] + " Expenses:"); //right items
				printCategoryExpenses(itemLists.get(index), expenseLists.get(index));
			} else {
				System.out.println("Invalid input, please re-enter.");
			}
		}
	}
	//This method allows the user to filter each item by its category
	
	public static void printCategoryExpenses(ArrayList<String> item, ArrayList<Float> expenses) {
		if (item.isEmpty()) {
			System.out.println("There are no expenses in this category");
		} else {
			for (int i = 0; i < item.size(); i++) {
				System.out.println(item.get(i) + " - £" + expenses.get(i));
			}
		}
	}
	//This method has been created to prevent repeated code and to prevent any empty lists
	//from being return and causing it to crash
	
	public static void main(String[] agrs) {
		int selection = 0;
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
		
		System.out.println("\n1: Add an expense \n2: View all expenses \n3: Calculate total expenses \n4: Filter expenses by catagory \n5: Exit");
		
		selection = StringToInteger(scanner.next());
		if (selection == 1) {
			addExpense();
			
		} else if (selection == 2) {
			viewExpenses();
			
		} else if (selection == 3) {
			calculateTotal();
			
		} else if (selection == 4) {
			filterByCategory();
			
		} else if (selection == 5) {
			System.out.println("Closing list.");
			break;
		} else {
			System.out.println("Reselect the following options: ");
			}
		}
		//This is the main function where all the methods are called
	}
}

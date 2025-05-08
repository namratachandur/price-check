import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PriceCheck 
{

	public static void main(String[] args) 
	{
		try
		{
			// Prompt the user for the name of the file containing grocery data
			Scanner input = new Scanner(System.in);
			System.out.println("\nEnter the name of the file containing the grocery list data: ");
			String file_name = input.nextLine();
			
			// Check if the file name is empty
			if (file_name.isEmpty())
			{
				System.out.println("File name cannot be empty. Exiting program.");
				input.close(); // Close the scanner
				return; // Exit if the file name is empty
			}
			
			// Initialize arrays to store grocery names and prices
			String[] names = new String[50];
			double[] prices = new double[50];
			
			// Call the loadGroceryData method to read grocery data from the file
			loadGroceryData(file_name, names, prices);
			input.close();
		}
		catch (Exception e)
		{
			System.out.println("Error: Exception found: " + e.getMessage());
		}
	}
	
	public static void loadGroceryData(String fileName, String[] names, double[] prices)
	{
		// Reads grocery data from a file named groceries.txt
		try
		{
			Scanner fileInput = new Scanner(new File(fileName));
			int count = 0;
			// Read the grocery names and prices from the file
			while(fileInput.hasNextLine() && count < 50)
			{
				String line = fileInput.nextLine();
				String[] parts = line.split(",");
				if (parts.length == 2)
				{
					names[count] = parts[0].trim(); // Store grocery name
					prices[count] = Double.parseDouble(parts[1].trim()); // Store grocery price
					count++; // Increment the count of groceries
				}
			}
			
			// Check if the grocery data is empty
			if (count == 0)
			{
				System.out.println("\nNo grocery data found.");
				fileInput.close();
				return; // Exit if no data is found
			}
			
			// Pass data to the calculateAveragePrice method and call it
			double average = calculateAverage(prices, count);
			
			// Pass data to the writeReport method and call it
			writeReport(names, prices, count, average);
			System.out.println("\nGrocery data written successfully to file named gorcery_report.txt.");
			fileInput.close();
		}
		catch (Exception e)
		{
			System.out.println("\nError reading file: " + e.getMessage());
			return;
		}
	}
	
	public static double calculateAverage(double[] prices, int count)
	{
		// Method to calculate average price
		if (count == 0)
		{
			System.out.println("\nNo grocery data available.");
			return 0;
		}
		else
		{
			double sum = 0;
			for (int i = 0; i < count; i++)
			{
				sum += prices[i]; // Sum up the prices
			}
			double average = sum / count; // Calculate the average price
			return average; // Return the average price
		}
	}
	
	public static void writeReport(String[] names, double[] prices, int count, double average)
	{
		// Write the report to a file named grocery_report.txt
		try
		{
			PrintWriter output = new PrintWriter(new File("grocery_report.txt"));
			output.println("Grocery Items Report");
			output.println("---------------------");
			for (int i = 0; i < count; i++)
			{
				output.printf("%s: $%.2f%n", names[i], prices[i]); // Print each grocery name and price
			}
			output.printf("\nAverage Price: $%.2f%n", average); // Print the average price
			output.close();
		}
		catch (Exception e)
		{
			System.out.println("Error while printing file: " + e.getMessage());
		}
	}

}

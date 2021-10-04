package edu.wit.scds.comp2000.bag.app;

import java.util.Scanner;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.FileNotFoundException;

import edu.wit.scds.comp2000.bag.BagInterface ;

import edu.wit.scds.comp2000.bag.adt.ResizableArrayBag;

public class GroceryBagger{
	
	public static void main( final String[] args ) throws FileNotFoundException {
		/**
		 * Scan through data line by line to grab each item
		 * Run certain attributes through enums to make them easier to use
		 * Check existing bags for compatibility, create new ones if needed 
		 */
		Scanner conveyorBelt =  new Scanner(  new BufferedReader( new FileReader("./data/groceries.txt" ) ) ) ;
		
		
		int numberOfItems = 0 ;
		
		while ( conveyorBelt.hasNextLine() )
	     {
			String itemFromConveyorBelt = conveyorBelt.nextLine() ; 
			String [] itemAttributes = itemFromConveyorBelt.split( "\t" );
						
			/**
			 * Assign attributes to variables
			 * Attribute order is: size, weight, hardness, rigidity, breakability, perishiability
			 */
			
			String size = itemAttributes[1] ;
			String weight = itemAttributes[2] ;
			String hardness = itemAttributes[3] ;
			String rigidity = itemAttributes[4] ;
			String breakable = itemAttributes[5] ;
			String perishable = itemAttributes[6] ;
			
			GroceryItemSize enumSize = GroceryItemSize.interpretDescription( size ) ;
			
			/*
			 * 
			 */

			
	     }


	}
	
	// Checks if items of a certain attribute have been already added to each bag
	// finalResult indicates what method contains has returned
	private static void checkAttribute( final BagInterface<String> aBag, 
										final String test)
		{
		aBag.contains( test ); 
		}
	

}

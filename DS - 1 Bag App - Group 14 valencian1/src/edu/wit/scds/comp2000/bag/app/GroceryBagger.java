package edu.wit.scds.comp2000.bag.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.BufferedReader;

import java.io.FileReader;

import java.io.FileNotFoundException;

import edu.wit.scds.comp2000.bag.BagInterface ;

import edu.wit.scds.comp2000.bag.adt.ResizableArrayBag;



/**
 * 
 * @author Audrey Nichols
 * @author
 * @author
 *
 */
public class GroceryBagger{
	
	public static void main( final String[] args ) throws FileNotFoundException {
		/**
		 * Scan through data line by line to grab each item
		 * Run certain attributes through enums to make them easier to use
		 * Check existing bags for compatibility, create new ones if needed 
		 */
		Scanner conveyorBelt =  new Scanner(  new BufferedReader( new FileReader("./data/groceries.txt" ) ) ) ;
		
		
		int numBags = 0 ;
		
		
		
		
		final BagInterface<String> oneBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> twoBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> threeBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> fourBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> fiveBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> sixBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> sevenBag = new ResizableArrayBag<>(10) ;
		final BagInterface<String> eightBag = new ResizableArrayBag<>(10) ;

		ArrayList<BagInterface> cart = new ArrayList<BagInterface>() ;
		
		cart.add(oneBag) ;
		cart.add(twoBag) ;
		cart.add(threeBag) ;
		cart.add(fourBag) ;
		cart.add(fiveBag) ;
		cart.add(sixBag) ;
		cart.add(sevenBag) ;
		cart.add(eightBag) ;
		
		
		while ( conveyorBelt.hasNextLine() )
	     {
			String itemFromConveyorBelt = conveyorBelt.nextLine() ; 
			String [] itemAttributes = itemFromConveyorBelt.split( "\t" ) ;
						
			/**
			 * Assign attributes to variables
			 * Attribute order is: size, weight, hardness, rigidity, breakability, perishiability
			 */
			
			String itemSizeText = itemAttributes[1] ;
			String itemWeight = itemAttributes[2] ;
			String itemHardness = itemAttributes[3] ;
			String itemRigidity = itemAttributes[4] ;
			String itemBreakable = itemAttributes[5] ;
			String itemPerishable = itemAttributes[6] ;
			
	          /*
	           * Utilize GroceryItemSize.java to enumerate 
	           * item size to help determine when bags are full
	           */
			GroceryItemSize itemSize = GroceryItemSize.interpretDescription( itemSizeText ) ;
			int tempSize = itemSize.sizeValue ;
			
			sort(itemFromConveyorBelt, cart) ;

			
	     }
		
		System.out.println("\n-------------------- ") ;
		System.out.println("\nLoaded " + numBags + " bags:\n") ;

		
		displayBag(oneBag, 1) ;
		displayBag(twoBag, 2) ;
		displayBag(threeBag, 3) ;
		displayBag(fourBag, 4) ;
		displayBag(fiveBag, 5) ;
		displayBag(sixBag, 6) ;
		displayBag(sevenBag, 7) ;
		displayBag(eightBag, 8) ;


	}
	
	private static void sort( final String item, 
							  final ArrayList<BagInterface> cart)
		{
		String [] itemAttributes = item.split( "\t" ) ;
		
		String itemName = itemAttributes[0] ;
		String itemSizeText = itemAttributes[1] ;
		String itemWeight = itemAttributes[2] ;
		String itemHardness = itemAttributes[3] ;
		String itemRigidity = itemAttributes[4] ;
		String itemBreakable = itemAttributes[5] ;
		String itemPerishable = itemAttributes[6] ;
		
		
		int bagNumber = 0 ;
		
		for ( BagInterface<String> aBag : cart) {
			bagNumber++ ;
			
			if ( aBag.isEmpty() ) {
				aBag.add( itemName ) ;
				aBag.add( itemPerishable ) ;
				aBag.add( itemBreakable ) ;
				aBag.add( itemWeight ) ;
				aBag.add( itemSizeText ) ;
				aBag.add( itemHardness ) ;
				break ;
			}
			else {
				if ( !bagFull(aBag) ) {
					
					if ( checkAttributes( aBag, item ) ) {
						aBag.add( itemName ) ;
						aBag.add( itemSizeText ) ;
						aBag.add( itemHardness ) ;
						aBag.add( itemBreakable ) ;
						break ;
					}
				}
				
			}
		}
		
		
		
		System.out.println("Put " + itemName + " in bag " + bagNumber) ;
		
		
		
		}
	
	/*
	 * Tests compatibility of attributes already within bag to that of the current item being held
	 * In order to make sure an item is compatible, we will make sure that there are no non-compatible items
	 * existing in the bag
	 */
	private static boolean checkAttributes( final BagInterface<String> aBag, 
										    final String item )
		{
		String [] itemAttributes = item.split( "\t" ) ;
		String itemSizeText = itemAttributes[1] ;		
		String itemWeight = itemAttributes[2] ;

		String itemHardness = itemAttributes[3] ;
		String itemRigidity = itemAttributes[4] ;
		String itemBreakable = itemAttributes[5] ;
		String itemPerishable = itemAttributes[6] ;
		
		if ( aBag.contains( itemPerishable ) ) {
			if ( itemWeight.equals( "light" ) ) 
			{
				return true;
			}
			else if ( !itemWeight.equals( "heavy" ) && itemRigidity.equals( "flexible" )) {
				if ( !itemSizeText.equals( "large" ) )
					return true;
			}
			else {
				if ( aBag.contains( itemBreakable ) )
					if ( itemWeight.equals( "heavy" ) ) 
					{
						if ( aBag.contains( itemWeight ) )
							if ( aBag.contains( "hard" ) && itemHardness.equals( "hard" )) 
							{
								return true;
							}
							else if ( !aBag.contains( "hard ") && !itemHardness.equals( "hard" ) ) {
								return true;
							}
					}
					
			}
			
			
		}
		
		return false;
		
		
		
		}
	
	

	private static boolean bagFull( final BagInterface<String> aBag) {
		int full = aBag.getFrequencyOf( "small" ) + 2*aBag.getFrequencyOf( "large" ) + 3*aBag.getFrequencyOf( "large" ) ;
		
		if (full > 8) {
			return true;
		}
		return false;
	}

    // Tests the method toArray while displaying the bag.
    private static void displayBag( final BagInterface<String> aBag,
    								final int numBag)
        {
    	
    	String [] attributeList = { "small", "medium", "large", "light", "heavy", "soft", "firm",
				"hard", "flexible", "rigid", "breakable", "nonbreakable", "perishable", "nonperishable"} ;   	
    	
    	String descriptors = "" ;
    	
		
		if ( aBag.contains( "breakable" ) ) {
			descriptors = descriptors + "breakables" ;
		}
		if ( aBag.contains( "perishable" ) ) {
			descriptors = descriptors + ", perishables" ;
		}
		if ( aBag.contains( "heavy" ) ) {
			descriptors = descriptors + "heavy items" ;
		}
		if ( bagFull(aBag) ) {
			descriptors = descriptors + ", full" ;
		}
    	

		for (String element: attributeList) {
			while ( aBag.contains( element ) ) {
				aBag.remove( element ) ;
			}
		}
		
    	
        System.out.println( "Bag " + numBag + " contains " + aBag.getCurrentSize() +
                            " items (" + descriptors + "):" ) ;
        
        final Object[] bagArray = aBag.toArray() ;
        
        for ( final Object element : bagArray )
            {
            System.out.println("(" + aBag.getFrequencyOf( (String) element ) + ") " + element  ) ;
            } // end for

        System.out.println() ;

        }  // end displayBag()

}

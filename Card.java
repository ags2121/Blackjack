package edu.uchicago.cs.java.lec03.console21;

import javax.swing.ImageIcon;

/**
 * This class is a simple bean used to store face values for a card in a blackjack game.
 * 
 * @author Adam Gerber
 * @version 1.0
 */
public class Card {

	// ===============================================
	// ==FIELD
	// ===============================================
	/**
	 * @uml.property  name="face"
	 */
	
	private String name;
	private int value;
	private ImageIcon image;
	
	//end static
	
	
	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================

	public Card (String strName, int nV, ImageIcon cImages){
		name = strName;
		value = nV;
		image = cImages;
	} 

	// ===============================================
	// ==METHODS
	// ===============================================

	/**
	 * This method lorem ipsum.
	 * @uml.property  name="face"
	 * @return Image representing the face of the card.
	 */
	public ImageIcon getCrdImage() {
		return image;
	}
	
	public int getCrdValue(){
		return value;
	}

	public void setCrdValue(int value) {
		this.value = value;
	}

	public String getCrdName() {
		return name;
	}

}

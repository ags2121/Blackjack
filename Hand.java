package edu.uchicago.cs.java.lec03.console21;

import java.util.ArrayList;

public class Hand {
	// ===============================================
	// ==FIELDS
	// ===============================================
	/**
	 * @uml.property   name="crdCards"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="edu.uchicago.cs.java.lec03.console21.Card"
	 */
	private ArrayList<Card> crdCards;
	/**
	 * @uml.property  name="hidden"
	 */
	private boolean bHidden;

	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================
	public Hand() {
		crdCards = new ArrayList<Card>();
	}

	public ArrayList<Card> getCards() {
		return this.crdCards;
	}

	public void setCards(ArrayList<Card> crdCards) {
		this.crdCards = crdCards;
	}

	/**
	 * @return
	 * @uml.property  name="hidden"
	 */
	public boolean isHidden() {
		return this.bHidden;
	}

	/**
	 * @param hidden
	 * @uml.property  name="hidden"
	 */
	public void setHidden(boolean hidden) {
		this.bHidden = hidden;
	}

	
	public ArrayList<Card> returnCards() {

		ArrayList<Card> crdReturns = new ArrayList<Card>();
		while (!crdCards.isEmpty()) {
			crdReturns.add(crdCards.remove(0));
		}

		return crdReturns;
	}
	

	public String getStatus() {

		int nState = getValue();
		if (nState < 21)
			return "PLAYING";
		else if (nState == 21)
			return "TWENTY-ONE";
		else
			return "BUST";

	}

	public int getValue() {

		//sum the hand's value; Ace is always 11
		int nVal = 0;
		
		for(int i=0;i<crdCards.size();i++){
			nVal += crdCards.get(i).getCrdValue();
		}
		
		return nVal;

	}

	public void add(Card crd) {
		//if card to be added is an ace and if adding it will bust the hand, make the value 1
		if(crd.getCrdValue()==11 && (getValue()+crd.getCrdValue()) > 21){
			crd.setCrdValue(1);
		}
		if(getValue()+crd.getCrdValue() > 21){
			makeAcesSoft();
		}
		crdCards.add(crd);
		}
	
	public void makeAcesSoft(){
		for (Card crd : getCards()) {
			//make all aces soft
			if(crd.getCrdValue()==11){
				crd.setCrdValue(1);
			}
		}
	}
	
}

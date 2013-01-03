package edu.uchicago.cs.java.lec03.console21;

import java.util.ArrayList;

public class Player {

	// ===============================================
	// ==FIELDS
	// ===============================================

	/**
	 * @uml.property  name="name"
	 */
	private String strName;
	/**
	 * @uml.property  name="money"
	 */
	private double dMoney;
	//private ArrayList<Card> crdCards;
	/**
	 * @uml.property  name="hnd"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Hand hnd;
	

	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================

	public Player(String name, double money) {
		//super();
		this.strName = name;
		this.dMoney = money;
		
		//crdCards = new ArrayList<Card>();
		hnd = new Hand();
	}

	// ===============================================
	// ==GETTERS AND SETTERS & OTHER METHODS
	// ===============================================
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return this.strName;
	}

	/**
	 * @param name
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.strName = name;
	}

	/**
	 * @return
	 * @uml.property  name="money"
	 */
	public double getMoney() {
		return this.dMoney;
	}

	/**
	 * @param money
	 * @uml.property  name="money"
	 */
	public void setMoney(double money) {
		this.dMoney = money;
	}
	
	public Hand getHand() {
		return this.hnd;
	}

	public void setHand(Hand hnd) {
		this.hnd = hnd;
	}
	
	public void setHidden(boolean bHidden){
		hnd.setHidden(bHidden);
	}
	public int getHandValue(){
		return hnd.getValue();
	}
	
	public ArrayList<Card> returnCards() {
		
		return hnd.returnCards();
	}

	public void hit(Card crd) {
		hnd.add(crd);
	}



}

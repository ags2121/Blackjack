package edu.uchicago.cs.java.lec03.console21;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;


public class Chute {

	// ===============================================
	// ==FIELDS
	// ===============================================

	private int nRemaining;
	private ImageIcon[] cImages;
	private ArrayList<Card> deck;
	private Card[] decktemp;
	
	
	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================
	public Chute() throws IOException{
		//instantiate the arrayList

		nRemaining = 52;
		
		decktemp = new Card[312];
		
		cImages = new ImageIcon[52];
		
		fillImageArray();
		
		String[] cNames = {"Ace of", "King of", "Queen of", "Jack of", "10 of", "9 of", "8 of", "7 of", "6 of",
				"5 of", "4 of", "3 of", "2 of", "1 of"};
		
		//load the chute
		for (int nC = 0; nC < 6; nC++){ 
			for (int nD = 0; nD < 13; nD++){ 
				int y = 52*nC;
				int x = 4*nD;
				//nCon retrieves the blackjack values each card
				int nCon = valueConvert(nD);
				decktemp[x+y] = new Card(cNames[nD].concat(" Clubs"), nCon, cImages[x]);
				decktemp[x+1+y] = new Card(cNames[nD].concat(" Spades"), nCon, cImages[x+1]);
				decktemp[x+2+y] = new Card(cNames[nD].concat(" Hearts"), nCon, cImages[x+2]);
				decktemp[x+3+y] = new Card(cNames[nD].concat(" Diamonds"), nCon, cImages[x+3]);
				  
			}
		}
		
		deck = new ArrayList<Card>(312); 

		Collections.addAll(deck, decktemp); 
				

	}//end constructor
	
	
	
	
	
	// ===============================================
	// ==METHODS
	// ===============================================
	public ArrayList<Card> getCards() {
		return this.deck;
	}

	public void setCards(ArrayList<Card> crdCards) {
		this.deck = crdCards;
	}


	public void recapture(ArrayList<Card> crdParams){
		
		for (Card crd : crdParams) {
			//set default ace value back to 11
			if(crd.getCrdValue()==1){
				crd.setCrdValue(11);
			}
			deck.add(crd);
		}
	}

	public void add(Card crd){
		deck.add(crd);
		
	}
	public Card deal(){

		if (nRemaining == 0){
			shuffle();
			nRemaining = 52;
		} 
		
		    Card crd;
			crd = deck.remove(0);
			nRemaining--;
			return crd;
		

	}
	
	public int getNumCards(){
		return deck.size();
	}
	
	public int getNunCardsRemainingBeforeShuffle(){
		return nRemaining;
	}

	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public void fillImageArray() throws IOException{
		for (int i = 0; i < cImages.length; i++){
			try {
				java.net.URL url = getClass().getResource("cards-classic/"+(i+1)+".png");
				if(url != null){
					cImages[i] = new ImageIcon(url);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int valueConvert(int nIn){
		
		if(nIn == 0){
			return 11;
		}
		else if(nIn < 4 && nIn > 0){
			return 10;
		}
		else if(nIn == 4){
			return 10;
		}
		else if(nIn == 5){
			return 9;
		}
		else if(nIn == 6){
			return 8;
		}
		else if(nIn == 8){
			return 6;
		}
		else if(nIn == 9){
			return 5;
		}
		else if (nIn == 10){
			return 4;
		}
		else if(nIn == 11){
			return 3;
		}
		else if (nIn == 12){
			return 2;
		}
		return 7;
	}
	

	
}

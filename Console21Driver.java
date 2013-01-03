package edu.uchicago.cs.java.lec03.console21;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Console21Driver {

	
	//Welcome to Console21
	//What is your name?
	//Create two players, player and dealer
	//while user wants to continue to play
		//deal two cards to each player/dealer
		//dealers hand should be hidden
		//if player and dealer have 21
			//push
			//play again?
		//else if dealer has 21
			//player loses 10$
		//else if player has 21
			//player wins 10$
		//else
			//show both hands
			//while player hand <= 21 && not stick
				//hit player
			//if player busted
				//player loses 10$, 
				//play again?
			//else
				//while dealer hand <17
					//hit dealer
				//if dealer busts
					//player wins 10$
					//play again?
				//else
					//if player > dealer
						//player wins 10$
						//play again:
					//else if dealer > player	
						//player loses 10$
						//play again?
					//else (push)
						//push
						//play again
	
		//return cards to chute
	//thank you for playing
	
	public static final int START_MONEY = 1000;
	private static int BET = 100;
	//DELAY constant is the delay between game prompts, in milliseconds
	private static final long DELAY = 2030;
	
	private static Table gameTable;
	private static Chute cht;
	private static Player plyPlayer;
	private static Player plyDealer;
	
	private static Boolean isAsking;
	private static Boolean choice;
	private static Boolean isStick;
	private static Boolean isDD;
	private static Boolean isDeciding;
	private static Boolean isLess17;
	
	//optionDialog image
	private static ImageIcon dialogImage;
	@SuppressWarnings("unused")
	private static Console21Driver game;
	
	public Console21Driver(){
		loadDialogImage("optionpaneImage.png");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		//constructor used just to load inputDialog image
		game = new Console21Driver();
		
		cht = new Chute();
		cht.shuffle();

		String strName = (String)JOptionPane.showInputDialog(null, "What is your name?", "WELCOME TO BLACKJACK", -1, dialogImage, null, null);
		if(strName==null){
			System.exit(0);
		}
		
		//instantiate two players
		plyPlayer = new Player(strName, 1000.0);
		plyDealer = new Player("Dealer", 0.0); 
		
		while(true){
			//create new table
			gameTable = new Table();
			//deal out the cards
			plyPlayer.hit(cht.deal());
			plyDealer.hit(cht.deal());
			plyPlayer.hit(cht.deal());			
			plyDealer.hit(cht.deal());
			
			//dealer must hit if his hand is less than 17
			while(plyDealer.getHandValue() < 17){
				plyDealer.hit(cht.deal());
			}
			
			//if dealer has any soft aces in his hand, and if he has a hand value of 17
			//then he must hit
			ArrayList<Card> temp = plyDealer.getHand().getCards();
			for(Card crd : temp){
				if(crd.getCrdValue()==1){
					if(plyDealer.getHandValue()==17){
						plyDealer.hit(cht.deal());
					}
				}
			}
			
			//make sure that dealer's first card remains hidden
			plyDealer.setHidden(true);
			
			//if both player and dealer have 21 
			if (plyPlayer.getHandValue() == 21 && plyDealer.getHandValue() == 21 ){
				gameTable.setTextLabelTop("PUSH");
				gameTable.showHands(plyPlayer, plyDealer);
				reportChuteStatus(cht);
				//no change in bank
				reportBankStatus(plyPlayer);
				
				if(!playAgainGUI()){
					break;
				}
				
			} 
			//if dealer has blackjack
			else if ( plyDealer.getHandValue() == 21 ){
				gameTable.setTextLabelTop("DEALER HAS BLACKJACK");
				gameTable.showHands(plyPlayer, plyDealer);
				reportChuteStatus(cht);
				//player loses 10 dollars
				plyPlayer.setMoney(plyPlayer.getMoney() - BET);
				reportBankStatus(plyPlayer);
				
				if(!playAgainGUI()){
					break;
				}
			}
			
			//if you have blackjack
			else if ( plyPlayer.getHandValue() == 21 ){
				gameTable.setTextLabelTop("YOU HAVE BLACKJACK");
				gameTable.showHands(plyPlayer, plyDealer);
				reportChuteStatus(cht);
				//player gains dollars
				plyPlayer.setMoney(plyPlayer.getMoney() + BET*1.5);
				reportBankStatus(plyPlayer);
				
				if(!playAgainGUI()){
					break;
				}
				
			}
			else {

				//show the hands
				gameTable.showHandD(plyDealer);
				gameTable.showHandP(plyPlayer);
				
				//keep asking for hit or stick
				do {
					setIsStick(false);
					setIsDD(false);
					isDeciding=false;

					gameTable.setTextLabelBot(reportGameStatus());
					
					while(!getIsDeciding()){
						gameTable.setTextLabelTop("Press SPACEBAR for hit, S for stick, or D for double down");
					}
					
				} while(getIsStick() == false && plyPlayer.getHandValue() < 21 && getIsDD() == false);
				
				
				//player busts
				if (plyPlayer.getHandValue() > 21){
					gameTable.setTextLabelTop("YOU BUSTED");
					gameTable.showHandD(plyDealer);
					gameTable.showHandP(plyPlayer);
				
					reportChuteStatus(cht);
					
					plyPlayer.setMoney(plyPlayer.getMoney() - BET);
					reportBankStatus(plyPlayer);
					
					if(!playAgainGUI()){
						break;
					}
				
				} 
				//dealers turn to autodeal
				else {
					while (plyDealer.getHandValue() < 17){
						plyDealer.hit(cht.deal());
						
					}
					
					//dealer busted
					if (plyDealer.getHandValue() > 21){
						
						gameTable.setTextLabelTop("DEALER BUSTED");
						gameTable.showHands(plyPlayer, plyDealer);
						reportChuteStatus(cht);
						
						plyPlayer.setMoney(plyPlayer.getMoney() + BET);
						reportBankStatus(plyPlayer);
						
						if(!playAgainGUI()){
							break;
						}

					}
					else {
						
						//you lose
						 if (plyDealer.getHandValue() > plyPlayer.getHandValue()){
							 	gameTable.setTextLabelTop("YOU LOSE");
							 	gameTable.showHands(plyPlayer, plyDealer);
								reportChuteStatus(cht);
								
								plyPlayer.setMoney(plyPlayer.getMoney() - BET);
								reportBankStatus(plyPlayer);
								
								if(!playAgainGUI()){
									break;
								}
								
							}
						 //you win
							else if (plyDealer.getHandValue() < plyPlayer.getHandValue()){
								gameTable.setTextLabelTop("YOU WIN");
								gameTable.showHands(plyPlayer, plyDealer);
								reportChuteStatus(cht);
								
								plyPlayer.setMoney(plyPlayer.getMoney() + BET);
								reportBankStatus(plyPlayer);
								
								if(!playAgainGUI()){
									break;
								}
								
							}
						 //tie
							else {
								gameTable.setTextLabelTop("PUSH");
								gameTable.showHands(plyPlayer, plyDealer);
								reportChuteStatus(cht);
								reportBankStatus(plyPlayer);
								
								if(!playAgainGUI()){
									break;
								}
								
							}
						
					}//end else not busted
					

				}//end else dealers turn to autodeal
				
				
			}//end not blackjack
			//get the cards back from the players and put them back into the chute
			cht.recapture(plyDealer.returnCards());
			cht.recapture(plyPlayer.returnCards());
			//reinitialize default bet
			setBet(100);
				
		}//end while true
		
		reportBankStatus(plyPlayer);
		gameTable.setTextLabelTop("Thanks for playing.");
		tickN(1100);
		System.exit(0);
		
	}
	
	private static boolean playAgainGUI(){
	Object[] options = { "YES", "NO" };
	int response = JOptionPane.showOptionDialog(null, "Ya wanna play again?", "Play again?",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			dialogImage, options, options[0]);
	if(response == 0){
		gameTable.getFrame().setVisible(false);
		gameTable.getFrame().dispose();
		return true;
	}
	return false;
	
	}

	public static void reportChuteStatus(Chute cht) {
		tick();
		gameTable.setTextLabelBot("Number of cards left in chute and number before reshuffling: " + 
		       cht.getNumCards() + " : " +cht.getNunCardsRemainingBeforeShuffle());
	}
	
	public static void reportBankStatus(Player ply) {
		tick();
		DecimalFormat dfm = new DecimalFormat("$0.00");
		gameTable.setTextLabelTop(ply.getName() + " has " + dfm.format(ply.getMoney()) + " in his/her account." );
	}
	
	public static String reportGameStatus(){
		return (plyPlayer.getName()+": "+plyPlayer.getHand().getValue() + " " + plyPlayer.getHand().getStatus());
	}
	
	public static boolean getIsAsking() {
		return isAsking;
	}

	public static void setIsAsking(boolean isAsking) {
		Console21Driver.isAsking = isAsking;
	}

	public static boolean getChoice() {
		return choice;
	}

	public static void setChoice(boolean choice) {
		Console21Driver.choice = choice;
	}
	
	public static void tick(){
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		while(end - start < DELAY){
			end = System.currentTimeMillis();
		}
	}
	
	public static void tickN(int nIn){
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		while(end - start < nIn){
			end = System.currentTimeMillis();
		}
	}
	
	public static Chute getCht(){
		return cht;
	}
	
	public static Player getPlayer(){
		return plyPlayer;
	}
	
	public static Player getDealer(){
		return plyDealer;
	}

	public static Boolean getIsStick() {
		return isStick;
	}

	public static void setIsStick(Boolean isStick) {
		Console21Driver.isStick = isStick;
	}

	public static Boolean getIsDD() {
		return isDD;
	}

	public static void setIsDD(Boolean isDD) {
		Console21Driver.isDD = isDD;
	}

	public ImageIcon getDialogImage() {
		return dialogImage;
	}

	public void setDialogImage(ImageIcon image) {
		dialogImage = image;
	}
	
	public void loadDialogImage(String path)
	{
		try {
			BufferedImage i = ImageIO.read(this.getClass().getResource(path));
			dialogImage = new ImageIcon(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getBet() {
		return BET;
	}
	public static void setBet(int newBet) {
		BET = newBet;
	}

	public static Boolean getIsDeciding() {
		return isDeciding;
	}

	public static void setIsDeciding(Boolean isDeciding) {
		Console21Driver.isDeciding = isDeciding;
	}

	public static Boolean getIsLess17() {
		return isLess17;
	}

	public static void setIsLess17(Boolean isLess17) {
		Console21Driver.isLess17 = isLess17;
	}

}

package edu.uchicago.cs.java.lec03.console21;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.JLabel;

public class tester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Chute test = new Chute();
		for(Card crd : test.getCards()){
			System.out.println(crd.getCrdName()+" "+crd.getCrdValue());
		}
		
//		JLabel test = new JLabel();
//		Icon i = test.getIcon();
//		System.out.println(i);

	}

}

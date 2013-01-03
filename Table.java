package edu.uchicago.cs.java.lec03.console21;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Table extends JFrame implements KeyListener{

	private JFrame frame;
	
	private JPanel DealerPanel;
	private JPanel PlayerPanel;
	
	@SuppressWarnings("unused")
	private JLabel pCard1;
	private JLabel pCard2;
	private JLabel pCard3;
	private JLabel pCard4;
	private JLabel pCard5;
	
	private JLabel dCard1;
	private JLabel dCard2;
	private JLabel dCard3;
	private JLabel dCard4;
	private JLabel dCard5;

	private JPanel textPanel;
	private JLabel textLabeltop;
	private static JLabel textLabelBot;
	
	private JLabel[] JLabelArrP = new JLabel[5];
	private JLabel[] JLabelArrD = new JLabel[5];
	
	private ImageIcon crdDefault;
	
	private final int 
			HIT = 32, // space key
			STICK = 83, //s key
			DOUBLE_DOWN = 68; // d key

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table window = new Table();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Table() throws IOException {
		initialize();
		getFrame().setVisible(true);
		pack();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public void initialize() throws IOException {
		setFrame(new JFrame());
		getFrame().setBackground(Color.WHITE);
		getFrame().setBounds(200, 200, 550, 400);
		getFrame().setResizable(false);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setTitle("BlackJack");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		getFrame().getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		DealerPanel = new JPanel();
		DealerPanel.setBackground(new Color(0, 102, 0));
		mainPanel.add(DealerPanel);
		DealerPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		dCard1 = new JLabel("", JLabel.CENTER);
		DealerPanel.add(dCard1);
		
		dCard2 = new JLabel("", JLabel.CENTER);
		DealerPanel.add(dCard2);
		
		dCard3 = new JLabel("", JLabel.CENTER);
		DealerPanel.add(dCard3);
		
		dCard4 = new JLabel("", JLabel.CENTER);
		DealerPanel.add(dCard4);
		
		dCard5 = new JLabel("", JLabel.CENTER);
		DealerPanel.add(dCard5);
		
		//add dealer card labels to ArrayD
		JLabelArrD[0]=dCard1; JLabelArrD[1]=dCard2; JLabelArrD[2]=dCard3; JLabelArrD[3]=dCard4; JLabelArrD[4]=dCard5;
		
		PlayerPanel = new JPanel();
		PlayerPanel.setBackground(new Color(0, 102, 0));
		mainPanel.add(PlayerPanel);
		PlayerPanel.setLayout(new GridLayout(1, 8, 0, 0));
		
		//load crdDefault image
		BufferedImage cd = ImageIO.read(this.getClass().getResource("carddefault.png"));
		crdDefault = new ImageIcon(cd);
		
		//add default card to every panel and sets initial visibility to false
		JLabel pCard1 = new JLabel("", JLabel.CENTER);
		PlayerPanel.add(pCard1);
		
		pCard2 = new JLabel("", JLabel.CENTER);
		PlayerPanel.add(pCard2);
		
		pCard3 = new JLabel("", JLabel.CENTER);
		PlayerPanel.add(pCard3);
		
		pCard4 = new JLabel("", JLabel.CENTER);
		PlayerPanel.add(pCard4);
		
		pCard5 = new JLabel("", JLabel.CENTER);
		PlayerPanel.add(pCard5);
		
		//add player card labels to ArrayP
		JLabelArrP[0]=pCard1; JLabelArrP[1]=pCard2; JLabelArrP[2]=pCard3; JLabelArrP[3]=pCard4; JLabelArrP[4]=pCard5;
		
		textPanel = new JPanel();
		textPanel.setBorder(new MatteBorder(6, 6, 2, 6, (Color) new Color(0, 0, 0)));
		getFrame().getContentPane().add(textPanel, BorderLayout.NORTH);
		textPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		textLabeltop = new JLabel("", JLabel.CENTER);
		textLabeltop.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
		textPanel.add(textLabeltop);
		
		textLabelBot = new JLabel("", JLabel.CENTER);
		textLabelBot.setFont(new Font("Helvetica", Font.PLAIN, 12));
		textPanel.add(textLabelBot);
		
		getFrame().addKeyListener(this);
		
	}
	
	public void setTextLabelTop(String strText){
		textLabeltop.setText(strText);
	}
	
	public String getTextLabelTop(){
		return textLabeltop.getText();
	}
	
	public void setTextLabelBot(String strText){
		textLabelBot.setText(strText);
	}
	
	public String getTextLabelBot(){
		return textLabelBot.getText();
	}

	public ImageIcon getCrdDefault() {
		return crdDefault;
	}

	public void setCrdDefault(ImageIcon crdDefault) {
		this.crdDefault = crdDefault;
	}
	
	//display player hand
	public void showHandP(Player ply){
		//copy players hand
		ArrayList<Card> pTemp = ply.getHand().getCards();
		//update player's Jlabels
		for(int i=0; i<pTemp.size(); i++){
			JLabelArrP[i].setIcon(pTemp.get(i).getCrdImage());
		}
	}
	
	//display player hand
	public void showHandD(Player deal){
		//copy players hand
		ArrayList<Card> dTemp = deal.getHand().getCards();
		//if dealers hand is not hidden
		if(!deal.getHand().isHidden()){
			//update Dealer's Jlabels
			for(int i=0; i<dTemp.size(); i++){
				JLabelArrD[i].setIcon(dTemp.get(i).getCrdImage());
			}
		}
		else{
			JLabelArrD[0].setIcon(crdDefault);
			for(int i=1; i<dTemp.size(); i++){
				JLabelArrD[i].setIcon(dTemp.get(i).getCrdImage());
			}
			
		}
	}
	
	public void showHands(Player ply, Player deal){
		deal.setHidden(false);
		showHandP(ply);
		showHandD(deal);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int nKey = e.getKeyCode();
//		 System.out.println(nKey);

			switch (nKey) {
			case DOUBLE_DOWN:
				Console21Driver.getPlayer().hit(Console21Driver.getCht().deal());
				Console21Driver.setBet(2*Console21Driver.getBet());
				showHandP(Console21Driver.getPlayer());
				setTextLabelBot(Console21Driver.reportGameStatus());
				Console21Driver.setIsDD(true);
				Console21Driver.setIsDeciding(true);
				break;
			case STICK:
				Console21Driver.setIsStick(true);
				Console21Driver.setIsDeciding(true);
				break;
			case HIT:
				Console21Driver.getPlayer().hit(Console21Driver.getCht().deal());
				Console21Driver.setIsDeciding(true);
				setTextLabelBot(Console21Driver.reportGameStatus());
				showHandP(Console21Driver.getPlayer());
				break;
//			case 65:
//				dCard1.setIcon(crdDefault);
			default:
				break;
			}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}

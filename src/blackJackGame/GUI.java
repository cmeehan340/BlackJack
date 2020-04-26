package blackJackGame;
import java.awt.Graphics2D;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
	
    int width = 1200; // Screen Height
    int height = 900; // Screen Width

   
    Font fontButton = new Font("Times New Roman", Font.PLAIN, 20);
    Font fontButton2 = new Font("Times New Roman", Font.PLAIN, 18);
    Font fontCard = new Font("Times New Roman", Font.BOLD, 30);
    Font labelFont = new Font("Liberation Serif",Font.BOLD| Font.ITALIC,20);
   
    Color colorBackground = new Color(0, 128, 0);
    Color colorButton = new Color(250, 250, 250);
    
    JButton buttonHit = new JButton();
    JButton buttonStand = new JButton();
    JButton buttonRules = new JButton();
    JButton buttonYes = new JButton();
    JButton buttonNo = new JButton();

    
    int gridX = 50;
    int gridY = 50;
    int gridW = 900;//Grid Width
    int gridH = 400;// Grid Height
    
    
   
    

    // Display Cards
    int cardSpacing = 10;
    int cardEdge = 10;
    int cardTW = gridW / 6;
    int cardTH = gridH / 2;
    int cardAW = cardTW - 2 * cardSpacing;
    int cardAH = cardTH - 2 * cardSpacing;


    public Dealer dealer;
    public Player player;



    public void updateDealerCards()
    {
    	loadDealerCards(dealer.getHand().getCards());
    }

    
    
    ArrayList <Card> playerCards = new ArrayList <Card>();
    ArrayList <Card> dealerCards = new ArrayList <Card>();
    
    public void loadDealerCards(ArrayList <Card> cards) {
 	   	int j = 0;
        for (Card c: cards) {
            System.out.println("Dealer has card" + " " + c.getName() + c.getNum() + " " + "of" + " " + c.getShape());
            char shape = c.getShape().charAt(0);
            char lShape = Character.toLowerCase(shape);
            String path = "imgs/" + c.getNum() + lShape + ".gif";
            System.out.println(path);
            ImageIcon image = new ImageIcon(path);
            JLabel imageLabel = new JLabel(image);
            add(imageLabel);
            imageLabel.setBounds(gridX + j * cardTW + cardSpacing, gridY + cardSpacing + 25, cardAW, cardAH);
            j++;
            imageLabel.setVisible(true);

        }
    }


    public void updatePlayerCards()
    {
    	loadPlayerCards(dealer.getPlayers().get(0).getHand().getCards());
    }
    public void loadPlayerCards(ArrayList <Card> cards) {
    	 int i = 0;
         for (Card c: cards) {
             System.out.println(("Player has card" + " " + c.getName() + c.getNum() + " " + "of" + " " + c.getShape()));
             char shape = c.getShape().charAt(0);
             char lShape = Character.toLowerCase(shape);
             String path = "imgs/" + String.valueOf(c.getNum()) + lShape + ".gif";
             System.out.println(path);
             ImageIcon image = new ImageIcon(path);
             JLabel imageLabel = new JLabel(image);
             add(imageLabel);

             imageLabel.setBounds(gridX + i * cardTW + cardSpacing, gridY + cardSpacing + cardTH, cardAW, cardAH);
             i++;
             imageLabel.setVisible(true);
         }
    }

    public GUI() {
    	
    	
        this.setSize(width, height);
        this.setTitle("BlackJack");
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Board board = new Board();
        this.setContentPane(board);
        this.setLayout(null);
   
        //Hit button
        Hit hit = new Hit();
        buttonHit.addActionListener(hit);
        buttonHit.setBounds(1000, 50, 120, 40);
        buttonHit.setFont(fontButton);
        buttonHit.setBackground(colorButton);
        buttonHit.setText("HIT");
        board.add(buttonHit);

        //Stand button
        Stand stand = new Stand();
        buttonStand.addActionListener(stand);
        buttonStand.setBounds(1000, 180, 120, 40);
        buttonStand.setFont(fontButton);
        buttonStand.setBackground(colorButton);
        buttonStand.setText("STAND");
        board.add(buttonStand);

        //Rules button
        Rules rules = new Rules();
        buttonRules.addActionListener(rules);
        buttonRules.setBounds(1000, 310, 120, 40);
        buttonRules.setFont(fontButton);
        buttonRules.setBackground(colorButton);
        buttonRules.setText("RULES");
        board.add(buttonRules);

        dealer = new Dealer();
        player = dealer.getPlayers().get(0);
        dealer.deal();

        ArrayList <Card> playerCards = new ArrayList <Card>();
        ArrayList <Card> dealerCards = new ArrayList <Card>();
        dealerCards = dealer.getHand().getCards();
        playerCards = player.getHand().getCards();

        loadDealerCards(dealerCards);
        loadPlayerCards(playerCards);
    }

    public class Board extends JPanel {
    	
    	
        public void paintComponent(Graphics g) {
        	super.paintComponent(g);
            g.setColor(colorBackground);
            g.fillRect(0, 0, width, height);
            //Temp grid
            g.setColor(Color.black);
            g.drawRect(gridX, gridY, gridW, gridH);
            Graphics2D eg = (Graphics2D) g;
            eg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Color white = new Color(255,255,255);
            eg.setColor(white);
            eg.setFont(labelFont);
            eg.drawString("PLAYER", 100,100);
            eg.drawString("DEALER", 100, 275);
            eg.drawString("SCORE", 100,500);
           


        }

    }

    public void addPlayerCard(Card newCard)
    {
    	//Add validations
    	dealer.getPlayers().get(0).getHand().addCard(newCard);
    	//playerCards = dealer.getPlayers().get(0).getHand().getCards();
    }
    public void addDealerCard(Card newCard)
    {
    	//Add validations
    	dealer.getHand().addCard(newCard);
    	//dealerCards = dealer.getHand().getCards();
    }

    public boolean checkForGameCompletion()
    {
    	boolean isGameCompleted = false;
        /*if( player.getTotalValue() == 21 && dealer.getTotalValue() == 21 )
        {
        	JOptionPane.showMessageDialog(null, "It's a tie, will start a new game", "Tie", JOptionPane.INFORMATION_MESSAGE);
        	isGameCompleted = true;
        }
        else if( player.getTotalValue() == 21 )
        {
        	JOptionPane.showMessageDialog(null, "Excellent: It's 21", "Won the game", JOptionPane.INFORMATION_MESSAGE);
        	isGameCompleted = true;
        }
        else if( dealer.getTotalValue() == 21 )
        {
        	JOptionPane.showMessageDialog(null, "Tough Luck: Dealer got a 21", "Lost the game", JOptionPane.INFORMATION_MESSAGE);
        	isGameCompleted = true;
        }
        if(isGameCompleted)
        {
        	playGame.newGame();
        }*/
        return isGameCompleted;
    }

    public void pressStandButton()
    {
    	buttonStand.doClick();
    }
}

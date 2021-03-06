package blackJackGame;
import javax.swing.*;
import java.util.*;
import java.awt.*;


public class GUI extends JFrame {

	int width = 1200; // Screen Height
	int height = 900; // Screen Width


	Font fontButton = new Font("Times New Roman", Font.PLAIN, 20);
	Font fontButton2 = new Font("Times New Roman", Font.PLAIN, 18);
	Font fontCard = new Font("Times New Roman", Font.BOLD, 30);
	Font labelFont = new Font("Liberation Serif", Font.BOLD | Font.ITALIC, 20);

	Color colorBackground = new Color(0, 128, 0);
	Color colorButton = new Color(250, 250, 250);

	JButton buttonHit = new JButton();
	JButton buttonStand = new JButton();
	JButton buttonRules = new JButton();
	JButton buttonYes = new JButton();
	JButton buttonNo = new JButton();
	JButton buttonNew = new JButton();
	JButton buttonBetting = new JButton();
   

	int gridX = 50;
	int gridY = 50;
	int gridW = 900; //Grid Width
	int gridH = 600; // Grid Height


	// Display Cards
	int cardSpacing = 10;
	int cardEdge = 10;
	int cardTW = gridW / 6;
	int cardTH = gridH / 3;
	int cardAW = cardTW - 2 * cardSpacing;
	int cardAH = cardTH - 2 * cardSpacing;

	JLabel cardBackSideLabel = null;


	public Dealer dealer;
	//public ArrayList <Player> players = new ArrayList <Player>();
	//public Player player;


	public void refreshDealerCards() {
		loadDealerCards(dealer.getHand().getCards());
	}



	ArrayList < Card > playerCards = new ArrayList < Card > ();
	ArrayList < Card > dealerCards = new ArrayList < Card > ();

	public void loadDealerCards(ArrayList < Card > cards) {

		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
		String methodName = e.getMethodName();
		if(methodName.equals("<init>"))
		{
			int j = 0;
			for (Card c: cards) {
				System.out.println("Dealer has card" + " " + c.getName() + c.getNum() + " " + "of" + " " + c.getShape());
				char shape = c.getShape().charAt(0);
				char lShape = Character.toLowerCase(shape);
				String path = "imgs/" + c.getNum() + lShape + ".gif";
				if( j == 1 )
				{
					path = "imgs/cardBackSide.gif";
				}
				System.out.println(path);
				ImageIcon image = new ImageIcon(path);
				JLabel imageLabel = new JLabel(image);
				if( j == 1 )
				{
					cardBackSideLabel = imageLabel;
				}
				add(imageLabel);
				imageLabel.setBounds(gridX + j * cardTW + cardSpacing, gridY + cardSpacing + 25, cardAW, cardAH);
				j++;
				imageLabel.setVisible(true);
			}
		}
		else
		{
			int j = 0;
			for (Card c: cards) {
				System.out.println("Dealer has card" + " " + c.getName() + c.getNum() + " " + "of" + " " + c.getShape());
				char shape = c.getShape().charAt(0);
				char lShape = Character.toLowerCase(shape);
				String path = "imgs/" + c.getNum() + lShape + ".gif";
				System.out.println(path);
				ImageIcon image = new ImageIcon(path);
				JLabel imageLabel = new JLabel(image);
				if( j == 1 )
				{
					remove(cardBackSideLabel);
				}
				add(imageLabel);
				imageLabel.setBounds(gridX + j * cardTW + cardSpacing, gridY + cardSpacing + 25, cardAW, cardAH);
				j++;
				imageLabel.setVisible(true);
			}
		}
	}



	public void updatePlayerCards(int playerNum) {
		loadPlayerCards(dealer.getPlayers().get(playerNum-1).getHand().getCards(),playerNum);
		//String handValStr = String.valueOf(dealer.getPlayers().get(playerNum-1).getTotalValue());
		//JOptionPane.showMessageDialog(null, handValStr, "Your hand's value is: ", JOptionPane.INFORMATION_MESSAGE);

	}
	public void loadPlayerCards(ArrayList < Card > cards, int playerNum) {
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

			imageLabel.setBounds(gridX + i * cardTW + cardSpacing, gridY + cardSpacing + cardTH*playerNum, cardAW, cardAH);
			i++;
			imageLabel.setVisible(true);
		}
	}

	public void addPlayerCard(Player player, Card newCard ) {
		//Add validations
		player.getHand().addCard(newCard);
	}
	public void addDealerCard(Card newCard) {
		//Add validations
		dealer.getHand().addCard(newCard);
	}

	public void pressStandButton() {
		buttonStand.doClick();
	}

	public GUI(Dealer dealer) {

		if(null == dealer || null == dealer.players)
		{
			System.out.println("Dealer or players is null");
			return;
		}

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
		buttonHit.setBounds(1000, 50, 160, 40);
		buttonHit.setFont(fontButton);
		buttonHit.setBackground(colorButton);
		buttonHit.setText("HIT");
		board.add(buttonHit);

		//Stand button
		Stand stand = new Stand();
		buttonStand.addActionListener(stand);
		buttonStand.setBounds(1000, 180, 160, 40);
		buttonStand.setFont(fontButton);
		buttonStand.setBackground(colorButton);
		buttonStand.setText("STAND");
		board.add(buttonStand);

		//Rules button
		Rules rules = new Rules();
		buttonRules.addActionListener(rules);
		buttonRules.setBounds(1000, 310, 160, 40);
		buttonRules.setFont(fontButton);
		buttonRules.setBackground(colorButton);
		buttonRules.setText("RULES");
		board.add(buttonRules);

		//New Game Button
		NewGame newG = new NewGame();
		buttonNew.addActionListener(newG);
		buttonNew.setBounds(1000, 440, 160, 40);
		buttonNew.setFont(fontButton);
		buttonNew.setBackground(colorButton);
		buttonNew.setText("NEW GAME");
		board.add(buttonNew);

		//Betting Button
		Betting betting= new Betting();
		buttonBetting.addActionListener(betting);
		buttonBetting.setBounds(1000, 570, 160, 40);
		buttonBetting.setFont(fontButton);
		buttonBetting.setBackground(colorButton);
		buttonBetting.setText("BETTING");
		board.add(buttonBetting);	
	

		

		this.dealer = dealer;
		dealer.deal();
		ArrayList < Card > dealerCards = new ArrayList < Card > ();
		dealerCards = dealer.getHand().getCards();
		loadDealerCards(dealerCards);

		for( int cnt = 0; cnt < dealer.getPlayers().size(); ++cnt )
		{
			Player player = dealer.getPlayers().get(cnt);
			ArrayList < Card > playerCards = new ArrayList < Card > ();
			playerCards = player.getHand().getCards();
			loadPlayerCards(playerCards, cnt+1);
			if( cnt == 0 )
				player.setToPlayerActivity(true);
			else
				player.setToPlayerActivity(false);
		}

	}

	public class Board extends JPanel {


		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if( null == dealer || null == dealer.players )
			{
				System.out.println("Dealer or players is not set correctly.");
				return;
			}
			g.setColor(colorBackground);
			g.fillRect(0, 0, width, height);
			//Temp grid
			g.setColor(Color.black);
			g.drawRect(gridX, gridY, gridW, gridH);
			Graphics2D eg = (Graphics2D) g;
			eg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			Color white = new Color(255, 255, 255);
			eg.setColor(white);
			eg.setFont(labelFont);
			eg.drawString("DEALER", 100, 100);
			eg.drawString("Player1 Total Wins: ", 100, 700);
			eg.drawString(String.valueOf(dealer.getPlayers().get(0).getGamesWon()), 300, 700);
			eg.drawString("Player2 Total Wins: ", 100, 750);
			eg.drawString(String.valueOf(dealer.getPlayers().get(1).getGamesWon()), 300, 750);
			eg.drawString("Player1 Balance: $", 800, 700);
			eg.drawString(String.valueOf(dealer.getPlayers().get(0).getBalance()), 980, 700);
			eg.drawString("Player2 Balance: $", 800, 750);
			eg.drawString(String.valueOf(dealer.getPlayers().get(1).getBalance()), 980, 750);
			
			//eg.drawString(String.valueOf(dealer.getPlayers().get(0).getScore()), 246, 700); //Games Won
			for( int cnt = 1; cnt <= dealer.getPlayers().size(); ++cnt )
			{
				if( dealer.getPlayers().get(cnt-1).isCurrentPlayer() )
				{
					Color currentColor = eg.getColor();
					eg.setColor(Color.ORANGE);
					eg.drawString("PLAYER"+(cnt)+" Turn", 100, 275+((cnt-1)*175));
					eg.drawString("PLAYER"+(cnt)+" HAND: ", 500, 700+(cnt*50));
					eg.setColor(currentColor);
				}
				else
				{
					eg.drawString("PLAYER"+(cnt), 100, 275+((cnt-1)*175));
					eg.drawString("PLAYER"+(cnt)+" HAND: ", 500, 700+(cnt*50));
				}
				eg.drawString(String.valueOf(dealer.getPlayers().get(cnt-1).getTotalValue()), 676, 700+(cnt*50)); //Player Hand Value
			}
			eg.drawString("DEALER HAND: ", 500, 700);
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
			boolean isCalledAfterCheckForWin = false;
			for( StackTraceElement stackEle : stacktrace )
			{
				if(stackEle.getMethodName().equals("checkForWin"))
					isCalledAfterCheckForWin = true;
			}
			if( isCalledAfterCheckForWin )
			{
				eg.drawString(String.valueOf(dealer.getDealerValue()), 676, 700); //Dealer Hand Value
			}
			else
			{
				eg.drawString(String.valueOf(dealer.getHand().getCards().get(0).getCardValue()), 676, 700); //Dealer Hand Value
			}
		}
	}
}
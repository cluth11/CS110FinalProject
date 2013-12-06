import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random; 
import java.util.Collections;
import java.util.*;

//Colin Luther
//CS110
//War GUI game

/**
   GUI-based implementation of the kid’s card game, War.
*/
public class warGUI extends JFrame
{
   //declare fields
   private War game; 
   private JPanel topPanel,leftPanel, rightPanel, bottomPanel;  
   private JLabel topStatus, leftStatus, leftImageLabel, rightStatus, rightImageLabel;   
   private JButton flip;
   private Card card1, card2;
   private ArrayList<Card> list = new ArrayList<Card>();

   /**
      This method constructs the GUI.
   */
   public warGUI()
   {
      //frame settings
      game = new War();      
      setTitle("THIS IS WAR!");
      setLayout(new FlowLayout());
      setBackground(Color.red);
      
      //top panel settings
      topPanel = new JPanel();
      topPanel.setBackground(Color.black);
      topStatus = new JLabel("Test your might!");
      topStatus.setForeground(Color.white);
      topPanel.add(topStatus);
      add(topPanel);
      
      //player one panel settings
      leftPanel = new JPanel();
      leftPanel.setBackground(Color.black);
      leftImageLabel = new JLabel(new ImageIcon("cardPics/back.jpg"));
      leftPanel.add(leftImageLabel);
      leftStatus = new JLabel("26");
      leftStatus.setForeground(Color.white);
      leftPanel.add(leftStatus);
      add(leftPanel);

      //player two panel settings
      rightPanel = new JPanel();
      rightPanel.setBackground(Color.black);
      rightImageLabel = new JLabel(new ImageIcon("cardPics/back.jpg"));
      rightPanel.add(rightImageLabel);
      rightStatus = new JLabel("26");
      rightStatus.setForeground(Color.white);
      rightPanel.add(rightStatus);
      add(rightPanel);
      
      //push button panel settings
      bottomPanel = new JPanel();
      bottomPanel.setBackground(Color.black);
      flip = new JButton("FLIP");
      flip.addActionListener(new ButtonListener()); 
      flip.setVisible(true);
      bottomPanel.add(flip);
      add(bottomPanel);
   }  
   
   /**
      When ButtonListener is pressed, it acts as a turn in war, and changes the settings of 
      the GUI according to what the War object gives it.
   */ 
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {     
         //As long as neither player's hand is empty, this code will execute.
         if(game.playerOneSize()!=0&&game.playerTwoSize()!=0)
         {
            //Creates two card objects and sets GUI to their images.
            card1 = new Card(game.getPlayerOne());
            leftImageLabel.setIcon(new ImageIcon(card1.getImage()));
            card2 = new Card(game.getPlayerTwo());
            rightImageLabel.setIcon(new ImageIcon(card2.getImage()));
            list.add(card1);
            list.add(card2);
            
            //If player one wins the turn add cards to their hand.
            if(game.fight(game.getPlayerOne(),game.getPlayerTwo())==1)
            {
               topStatus.setText("Player one wins this battle, flip again.");
               while(list.isEmpty()==false)
               {
                  game.addPlayerOne(list.remove(0));
               }
               leftStatus.setText(Integer.toString(game.playerOneSize()));
               rightStatus.setText(Integer.toString(game.playerTwoSize()));
            }
            
            //If player one wins the turn add cards to their hand.
            else
            {
               topStatus.setText("Player two wins this battle fight again.");
               while(list.isEmpty()==false)
               {
                  game.addPlayerTwo(list.remove(0));
               }
               rightStatus.setText(Integer.toString(game.playerTwoSize()));
               leftStatus.setText(Integer.toString(game.playerOneSize()));
            }
         }
         
         //If player one's hand runs out then player two wins.
         else if(game.playerOneSize()==0)
         {
            topStatus.setText("Player two wins the war!");
            flip.setVisible(false);
         }
         
         //If player two's hand runs out, then player one wins.
         else
         {
            topStatus.setText("Player one wins the war!");
            flip.setVisible(false);
         }
      }
   } 
   
   /**
      This is the main driver method that creates the warGUI  
   */
   public static void main(String [] args)
   {
      JFrame frame = new warGUI();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(800,800);
      frame.validate();
      frame.setVisible(true);
   }    
   
   /**
      War class is the gut of the game. Whenever the button on the GUI is
      clicked, it activates methods of a War class object.
   */
   public class War
   {
      //declare fields
      private ArrayList<Card> playerOne = new ArrayList<Card>();
      private ArrayList<Card> playerTwo = new ArrayList<Card>();
      private ArrayList<Card> deck = new ArrayList<Card>();
      private Random r = new Random();
      
      /**
         Constructor adds 52 new Card objects to the deck ArrayList, and activates
         freshDeck method.
      */
      public War()
      {     
         deck.add(new Card("cardPics/aces.jpg",14));
         deck.add(new Card("cardPics/acec.jpg",14));
         deck.add(new Card("cardPics/aced.jpg",14));
         deck.add(new Card("cardPics/aceh.jpg",14));
         deck.add(new Card("cardPics/2s.jpg",2));
         deck.add(new Card("cardPics/2c.jpg",2));
         deck.add(new Card("cardPics/2d.jpg",2));
         deck.add(new Card("cardPics/2h.jpg",2));
         deck.add(new Card("cardPics/3s.jpg",3));
         deck.add(new Card("cardPics/3c.jpg",3));
         deck.add(new Card("cardPics/3d.jpg",3));
         deck.add(new Card("cardPics/3h.jpg",3));
         deck.add(new Card("cardPics/4s.jpg",4));
         deck.add(new Card("cardPics/4c.jpg",4));
         deck.add(new Card("cardPics/4d.jpg",4));
         deck.add(new Card("cardPics/4h.jpg",4));   
         deck.add(new Card("cardPics/5s.jpg",5));     
         deck.add(new Card("cardPics/5c.jpg",5));
         deck.add(new Card("cardPics/5d.jpg",5));
         deck.add(new Card("cardPics/5h.jpg",5));
         deck.add(new Card("cardPics/6s.jpg",6));
         deck.add(new Card("cardPics/6c.jpg",6));
         deck.add(new Card("cardPics/6d.jpg",6));
         deck.add(new Card("cardPics/6h.jpg",6));
         deck.add(new Card("cardPics/7s.jpg",7));
         deck.add(new Card("cardPics/7c.jpg",7));
         deck.add(new Card("cardPics/7d.jpg",7));
         deck.add(new Card("cardPics/7h.jpg",7));
         deck.add(new Card("cardPics/8s.jpg",8));
         deck.add(new Card("cardPics/8c.jpg",8));
         deck.add(new Card("cardPics/8d.jpg",8));
         deck.add(new Card("cardPics/8h.jpg",8));
         deck.add(new Card("cardPics/9s.jpg",9));
         deck.add(new Card("cardPics/9c.jpg",9));
         deck.add(new Card("cardPics/9d.jpg",9));
         deck.add(new Card("cardPics/9h.jpg",9));
         deck.add(new Card("cardPics/10s.jpg",10));
         deck.add(new Card("cardPics/10c.jpg",10));
         deck.add(new Card("cardPics/10d.jpg",10));
         deck.add(new Card("cardPics/10h.jpg",10));
         deck.add(new Card("cardPics/jacks.jpg",11));
         deck.add(new Card("cardPics/jackc.jpg",11));
         deck.add(new Card("cardPics/jackd.jpg",11));
         deck.add(new Card("cardPics/jackh.jpg",11));
         deck.add(new Card("cardPics/queens.jpg",12));
         deck.add(new Card("cardPics/queenc.jpg",12));
         deck.add(new Card("cardPics/queend.jpg",12));
         deck.add(new Card("cardPics/queenh.jpg",12));
         deck.add(new Card("cardPics/kings.jpg",13));
         deck.add(new Card("cardPics/kingc.jpg",13));
         deck.add(new Card("cardPics/kingd.jpg",13));
         deck.add(new Card("cardPics/kingh.jpg",13));
         freshDeck();         
      } 
      
      /**
         freshDeck shuffles the deck, then splits the deck object in half and adds half to the playerOne 
         ArrayList and half to the playerTwo ArrayList. It then shuffles the two arraylists.
      */
      public void freshDeck()  
      {
         Collections.shuffle(deck);
         for(int i = 0; deck.isEmpty()==false; i++)
         {
            playerOne.add(deck.remove(0));
            i++;
            playerTwo.add(deck.remove(0));
         }
         Collections.shuffle(playerOne);
         Collections.shuffle(playerTwo);
      } 
      
      /**
         returns a Card object from playerOne ArrayList and removes it from the ArrayList.
      */
      public Card getPlayerOne()
      {
         int n = r.nextInt(playerOne.size());
         return playerOne.remove(n);
      }
      
      /**
         returns a Card object from playerTwo ArrayList and removes it from the ArrayList.
      */
      public Card getPlayerTwo()
      {
         int n = r.nextInt(playerTwo.size());
         return playerTwo.remove(n);
      }
     
      /**
         adds a Card object to the playerOne ArrayList.
      */
      public void addPlayerOne(Card c)
      {
         playerOne.add(c);
      }
      
      /**
         adds a Card object to the playerTwo ArrayList.      
      */
      public void addPlayerTwo(Card c)
      {
         playerTwo.add(c);
      }
      
      /**
         returns the size of player one's hand
      */
      public int playerOneSize()
      {
         return playerOne.size();
      }
      
      /**
         returns the size of player two's hand
      */
      public int playerTwoSize()
      {
         return playerTwo.size();
      }

      /**
         returns 1 if the first card is higher, 2 if the second card is higher, 
         and 3 if it's a tie.
      */
      public int fight(Card p1, Card p2)
      {
         int victor = 3;
         if(p1.getRank() > p2.getRank())
            victor = 1;
         else if(p1.getRank() < p2.getRank())
            victor = 2;
         else
            victor = 0;
         return victor;
      }

   }
}
//Colin Luther
//CS110
//Card

/**
   Card class represents a card used in the War game.
*/
public class Card
{
   //delcare fields
   private String imageString;
   private int rank;
   
   /**
      Constructor takes in a string and an integer and assigns them to imageString and rank
      respectively.
   */
   public Card(String s, int r)
   {
      imageString = s;
      rank = r;
   }
   
   /**
      Copy constructor.
   */
   public Card(Card c)
   {
   this.rank=c.rank;
   this.imageString=c.imageString;
   }
   
   /**
      Returns the imageString variable.
   */
   public String getImage()
   {
      return imageString;
   }
   
   /**
      Returns the rank variable.
   */
   public int getRank()
   {
      return rank;
   }
   
}
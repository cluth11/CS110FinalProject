/**
   CargoShip is a subclass of Ship
*/

public class CargoShip extends Ship
{
   //Declare Fields
   private int capacity;
   
   /**
      Constructors
   */ 
   
   //default
   public CargoShip()
   {
      super(); 
   }
   
   //copy
   public CargoShip(CargoShip o)
   {
      super(o);
      this.capacity = o.capacity;
   }
   
   //variable constructor
   public CargoShip(String n,String y,int c)
   {
      super(n,y);
      capacity = c;
   }
   
	/**
      Setters
   */
   
   //sets capacity
   public void setCapacity(int c)
   {
      capacity = c;
   }
   
   /**
      Getters
   */
   
   //returns capacity
   public int getCapacity()
   {
      return capacity;
   }
   
   /**
      To String method
   */
   public String toString()
   {
      return (super.getName() + " has a capacity of " + capacity + " tonnes.");
   }
}
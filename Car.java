import java.awt.Color;

public class Car
{
	public Color c;
	public int row;
	public int col;
	public int size;
	public boolean direction;
	
	public Car(Color c, int startrow, int startcol, int width, boolean direction) 
	{
		this.c = c;
		this.row = startrow;
		this.col = startcol;
		this.size = width;
		this.direction = direction;
	}
	//returns size (either 2 or 3)
	public int getSize(){
		return size;
	}
	//returns boolean (left or right, true of false)
	public boolean getDirection(){
		return direction;
	}
	//return Color of car, identifier for valid moves
	public Color getColour(){
		return c;
	}
	//returns location
	public int[] getLocation(){
	    return new int[] {row, col};
	}
	//sets location
	public void setLocation(int r, int c){
	    this.row = r;
	    this.col = c;
	}
}

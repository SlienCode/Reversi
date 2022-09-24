
/* A class describing a move in the board
 * Every produced child corresponds to a move
 * and we need to keep the moves as well as the states.
 */

/* Project by:
 * Dimitrios Tsiompikas AM : 3180223
 * Nikolaos Xristodoulou AM : 3180206
 * Panagiotis Panagiotou AM : 3180139
 */
 
public class Move
{
	private int row;
	private int col;
	private int value;
	
	public Move()
	{
		row = -1;
		col = -1;
		value = 0;
	}
	
	public Move(int row, int col)
	{
		this.row = row;
		this.col = col;
		this.value = -1;
	}
	
	public Move(int value)
	{
		this.row = -1;
		this.col = -1;
		this.value = value;
	}
	
	public Move(int row, int col, int value)
	{
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
}

import java.util.ArrayList;

/* Project by:
 * Dimitrios Tsiompikas AM : 3180223
 * Nikolaos Xristodoulou AM : 3180206
 * Panagiotis Panagiotou AM : 3180139
 */

public class Board
{
    //Variables for the Boards values
	public static final int W = 1; // White pawn player
	public static final int B = -1; // Black pawn player
	public static final int EMPTY = 0;
    
    //Immediate move that lead to this board
    private Move lastMove;

    /* Variable containing who played last; whose turn resulted in this board
     * Even a new Board has lastLetterPlayed value; it denotes which player will play first
     */
	private int lastLetterPlayed;

	private int [][] gameBoard;
	
	public Board()
	{
		lastMove = new Move();
		lastLetterPlayed = B;
		gameBoard = new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				gameBoard[i][j] = EMPTY;
			}
		}
		gameBoard[3][3] = 1;
		gameBoard[3][4] = -1;
		gameBoard[4][3] = -1;
		gameBoard[4][4] = 1;
	}
	
	public Board(Board board) // copy constructor.
	{
		lastMove = board.lastMove;
		lastLetterPlayed = board.lastLetterPlayed;
		gameBoard = new int[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}
		
	public Move getLastMove()
	{
		return lastMove;
	}
	
	public int getLastLetterPlayed()
	{
		return lastLetterPlayed;
	}
	
	public int[][] getGameBoard()
	{
		return gameBoard;
	}

	public void setLastMove(Move lastMove)
	{
		this.lastMove.setRow(lastMove.getRow());
		this.lastMove.setCol(lastMove.getCol());
		this.lastMove.setValue(lastMove.getValue());
	}
	
	public void setLastLetterPlayed(int lastLetterPlayed)
	{
		this.lastLetterPlayed = lastLetterPlayed;
	}
	
	public void setGameBoard(int[][] gameBoard)
	{
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				this.gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}

    //Make a move; it places a letter in the board and captures the letters before it.
	public void makeMove(int row, int col, int letter)
	{
		gameBoard[row][col] = letter;
		lastMove = new Move(row, col);
		lastLetterPlayed = letter;
	}
	
	public void Flip(int row, int col, int before, int now){ //flips the enemy pawns a player can capture using a specific move
															// works like isValid except it uncludes the flip function
		boolean inflag = true;
		int tempRow = row;
		int tempCol = col;
		if (tempRow != 7){ 
			while ((gameBoard[tempRow + 1][tempCol] == before) && (inflag)){
				tempRow++;
				if (tempRow == 7) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol] == now){ // flips enemy pawns from the starting point to the ending point
																// we found at least on of our pawns like this 
																// B W W W B -> B B B B B
																// checks all 8 directions (as shown below)
					while (tempRow != row) {
						gameBoard[tempRow][col] = now;
						tempRow--;
					}
					inflag = false;
				}
				else if(gameBoard[tempRow + 1][tempCol] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempRow != 0){ 
			while ((gameBoard[tempRow - 1][tempCol] == before) && (inflag)){
				tempRow--;
				if (tempRow == 0) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol] == now){
					while (tempRow != row) {
						gameBoard[tempRow][col] = now;
						tempRow++;
					}
						inflag = false;
				}
				else if(gameBoard[tempRow - 1][tempCol] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempCol != 0){ 
			while ((gameBoard[tempRow][tempCol - 1] == before) && (inflag)){
				tempCol--;
				if (tempCol == 0) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol - 1] == now){
					while (tempCol != col) {
						gameBoard[row][tempCol] = now;
						tempCol++;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol - 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempCol != 7){
			while ((gameBoard[tempRow][tempCol + 1] == before) && (inflag)){
				tempCol++;
				if (tempCol == 7){
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol + 1] == now){
					while (tempCol != col) {
						gameBoard[row][tempCol] = now;
						tempCol--;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 7) && (tempCol != 0)){
			while ((gameBoard[tempRow + 1][tempCol - 1] == before) && (inflag)){
				tempRow++;
				tempCol--;
				if ((tempRow == 7) || (tempCol == 0)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol - 1] == now){
					while ((tempRow != row) && (tempCol != col)) {
						gameBoard[tempRow][tempCol] = now;
						tempRow--;
						tempCol++;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol - 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 7) && (tempCol != 7)){
			while ((gameBoard[tempRow + 1][tempCol + 1] == before) && (inflag)){
				tempRow++;
				tempCol++;
				if ((tempRow == 7) || (tempCol == 7)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol + 1] == now){
					while ((tempRow != row) && (tempCol != col)) {
						gameBoard[tempRow][tempCol] = now;
						tempRow--;
						tempCol--;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 0) && (tempCol != 0)){
			while ((gameBoard[tempRow - 1][tempCol - 1] == before) && (inflag)){
				tempRow--;
				tempCol--;
				if ((tempRow == 0) || (tempCol == 0)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol- 1] == now){
					while ((tempRow != row) && (tempCol != col)) {
						gameBoard[tempRow][tempCol] = now;
						tempRow++;
						tempCol++;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol- 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 0) && (tempCol != 7)){
			while ((gameBoard[tempRow - 1][tempCol + 1] == before) && (inflag)){
				tempRow--;
				tempCol++;
				if ((tempRow == 0) || (tempCol == 7)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol + 1] == now){
					while ((tempRow != row) && (tempCol != col)) {
						gameBoard[tempRow][tempCol] = now;
						tempRow++;
						tempCol--;
					}
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
	}

    /* Elegxei pou mporei na kinhthei.
	 * sto Reversi prepei PANTA na yparxei pioni 
	 * tou allou paikth se sygkeniko keli apo ayto poy theloume
	 * na kinhthoume alliws h kinhsh einai mh egkyrh.
	 * epishs an vgei ektos oriwn to AI me thn kinhsh einai akyrh.
	*/
	public boolean isValidMove(int row, int col, int before, int now)
	{
		// an h epomenh kinhsh vgalei ektos oriwn einai lathos.
		if ((row < 0) || (col < 0) || (row > 7) || (col > 7))
		{
			return false;
		}
		if (gameBoard[row][col] != EMPTY)
		{
			return false;
		}

		int tempRow = row;
		int tempCol = col;
		boolean inflag = true; // works as a "break" command inside if and while statements
		if (tempRow != 7){ 
			while ((gameBoard[tempRow + 1][tempCol] == before) && (inflag)){
				tempRow++;
				if (tempRow == 7) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol] == now){
					return true;
				}
				else if(gameBoard[tempRow + 1][tempCol] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempRow != 0){ 
			while ((gameBoard[tempRow - 1][tempCol] == before) && (inflag)){
				tempRow--;
				if (tempRow == 0) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol] == now){
					return true;
				}
				else if(gameBoard[tempRow - 1][tempCol] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempCol != 0){ 
			while ((gameBoard[tempRow][tempCol - 1] == before) && (inflag)){
				tempCol--;
				if (tempCol == 0) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol - 1] == now){
					return true;
				}
				else if (gameBoard[tempRow][tempCol - 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if (tempCol != 7){
			while ((gameBoard[tempRow][tempCol + 1] == before) && (inflag)){
				tempCol++;
				if (tempCol == 7){
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow][tempCol + 1] == now){
					return true;
				}
				else if (gameBoard[tempRow][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 7) && (tempCol != 0)){
			while ((gameBoard[tempRow + 1][tempCol - 1] == before) && (inflag)){
				tempRow++;
				tempCol--;
				if ((tempRow == 7) || (tempCol == 0)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol - 1] == now){
					return true;
				}
				else if (gameBoard[tempRow + 1][tempCol - 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 7) && (tempCol != 7)){
			while ((gameBoard[tempRow + 1][tempCol + 1] == before) && (inflag)){
				tempRow++;
				tempCol++;
				if ((tempRow == 7) || (tempCol == 7)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow + 1][tempCol + 1] == now){
					return true;
				}
				else if (gameBoard[tempRow + 1][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 0) && (tempCol != 0)){
			while ((gameBoard[tempRow - 1][tempCol - 1] == before) && (inflag)){
				tempRow--;
				tempCol--;
				if ((tempRow == 0) || (tempCol == 0)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol- 1] == now){
					return true;
				}
				else if (gameBoard[tempRow - 1][tempCol- 1] == EMPTY){
					inflag = false;
				}
			}
		}
		inflag = true;
		tempRow = row;
		tempCol = col;
		if ((tempRow != 0) && (tempCol != 7)){
			while ((gameBoard[tempRow - 1][tempCol + 1] == before) && (inflag)){
				tempRow--;
				tempCol++;
				if ((tempRow == 0) || (tempCol == 7)) {
					tempRow = row;
					tempCol = col;
					inflag = false;
				}
				else if (gameBoard[tempRow - 1][tempCol + 1] == now){
					return true;
				}
				else if (gameBoard[tempRow - 1][tempCol + 1] == EMPTY){
					inflag = false;
				}
			}
		}
		return false; // if no valid moves were found, return false
		             //  if at least one valid move was found, return true (inside the if statements)
	}

    /* Generates the children of the state
     * Any square in the board that is empty results to a child
     */
	public ArrayList<Board> getChildren(int letter)
	{
		int AI;
		int human;
		if (letter == W) {
			AI = W;
			human = B;
		}
		else {
			AI = B;
			human = W;
		}
		
		ArrayList<Board> children = new ArrayList<Board>();
		for(int row=0; row<8; row++)
		{
			for(int col=0; col<8; col++)
			{
				if(isValidMove(row, col, human, AI))
				{
					Board child = new Board(this);
					child.makeMove(row, col, letter);
					children.add(child);
				}
			}
		}
		return children;
	}

	/*
     * The heuristic we use to evaluate is
     * the number of pawns on our board
     */
	public int evaluate()
	{
		int Wlines = 0;
		int Blines = 0;
        int sum;

        // Checking rows and columns in order to find who has the most pawns.
		for(int row=0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++){
				if (gameBoard[row][col] == 1){
					Wlines++;
				}else if(gameBoard[row][col] == -1){
					Blines++;
				}
			}
		}
		return Wlines - Blines;
	}
	
	public boolean Available(Board b, int opponent, int player) { // checks if a player has any available moves
		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++){
				if (b.isValidMove(row, col, opponent, player)) {
					return true;
				}
			}
		}
		return false;
	}

    /*
     * A state is terminal if there are 64 pawns on the board
     * or no empty tiles are available
     */
    public boolean Terminal(int gameover)
    {
		int Wcount = 0;
		int Bcount = 0; 
        for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++){
				if (gameBoard[row][col] == 1){
					Wcount++;
				}else if(gameBoard[row][col] == -1){
					Bcount++;
				}
			}
		}
		if (gameover == 2) {
			if (Wcount > Bcount) {
				System.out.println("Black: " + Bcount + "\nWhite: " + Wcount + "\nWhite won the game!");
			}
			else if (Bcount > Wcount) {
				System.out.println("Black: " + Bcount + "\nWhite: " + Wcount + "\nBlack won the game!");
			}
			else {
				System.out.println("Black: " + Bcount + "\nWhite: " + Wcount + "\nIt's a draw!");
			}
			return true;
		}
		return false;
    }

    //Prints the board
	public void print()
	{
		// array for printing vertical numbers.
		int[] arr = new int[8];
		for (int i = 0; i < 8; i++){
			arr[i] = i + 1;
		}
		System.out.println("  A B C D E F G H");
		for(int row=0; row<8; row++)
		{
			System.out.print(arr[row] + " ");
			for(int col=0; col<8; col++)
			{
				switch (gameBoard[row][col])
				{
					case W:
						System.out.print("W ");
						break;
					case B:
						System.out.print("B ");
						break;
					case EMPTY:
						System.out.print("_ ");
						break;
					default:
						break;
				}
			}
			System.out.println(arr[row] + " ");
		}
		System.out.println("  A B C D E F G H");
	}
}

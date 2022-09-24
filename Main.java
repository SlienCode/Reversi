import java.util.*;

/* Project by:
 * Dimitrios Tsiompikas AM : 3180223
 * Nikolaos Xristodoulou AM : 3180206
 * Panagiotis Panagiotou AM : 3180139
 */
 
public class Main
{
	public static void main(String[] args)
	{
		Board b = new Board();
		b.print();
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter depth: ");
		int depth = scan.nextInt();
		System.out.print("Go first? Y/N: ");
		String start = scan.next();
		int turn = 0; //turn == 1 means it's the humans turn, turn == 2 means it's the AI's turn
		int human = 0;
		int AI = 0;
		int gameover = 0; //gameover == 1 means one player couldn't make a move
		                  //gameover == 2 means no players could make a move so the game ends
		GamePlayer AIPlayer = new GamePlayer(0, 0);
		if ((start.equals("Y")) || (start.equals("y"))) {
			human = Board.B; //declaring human's color
			AI = Board.W; //declaring AI's color
			turn = 1;
		}
		else if ((start.equals("N")) || (start.equals("n"))){
			human = Board.W; //declaring human's color
			AI = Board.B;//declaring AI's color
			turn = 2;
		}
		AIPlayer.maxDepth = depth;
		AIPlayer.playerLetter = AI;
		b.setLastLetterPlayed(Board.W); // By reversi rules , the player with White pawns starts second.
        // When gameover == 2 the game ends
		while(gameover < 2){ //gameover == 2 only when both the players can't make a move in a row
			System.out.println();
			if (turn == 1){ // human's turn
				if (human == Board.B){
					System.out.println("Black moves");
				}
				else {
					System.out.println("White moves");
				}
				if (b.Available(b, AI, human)) {
					gameover = 0; // if a player can make a move the game has to go on, so we need to refresh the gameover streak
					boolean flag = true;
					do {
						System.out.print("Give row: ");
						int row = scan.nextInt();
						row--; //gameboard array takes values from 0 to 7
						System.out.print("Give col: ");
						char usercol = scan.next().charAt(0);
						int col = (int)usercol;
						if ((col >= 65) && (col <= 72)) { //char ASCII code to int so it can be used in the gameboard array
							col = col - 65;
						}
						else if ((col >= 97) && (col <= 104)){
							col = col - 97; 
						}
						System.out.println();
						Move HMove = new Move(row, col);
						if (b.isValidMove(HMove.getRow(),HMove.getCol(), AI, human)){
							b.makeMove(HMove.getRow(),HMove.getCol(), human);
							b.Flip(HMove.getRow(),HMove.getCol(), AI, human);
							flag = false;
						}
						else{
							System.out.println("You can't make that move, try an other one!");
						}
					}
					while (flag);
					b.print();
				}
				else{
					gameover++;
					if (gameover == 2) {
						System.out.println("No player has available moves, game ends here.\n");
					}
					else {
						System.out.println("You have no available moves, switching to opponent.\n");
					}
				}
				turn = 2;
			}
			System.out.println();
			if ((gameover < 2) && (turn == 2)){ // AI's turn
				if (human == Board.B){
					System.out.println("White moves");
				}
				else {
					System.out.println("Black moves");
				}
				if (b.Available(b, human, AI)) {
					gameover = 0; // if a player can make a move the game has to go on, so we need to refresh the gameover streak
					Move AIMove = AIPlayer.MiniMax(b);
					if (!b.isValidMove(AIMove.getRow(),AIMove.getCol(), human, AI)){ // we use this if statement in case the MiniMax algorithm doesn't work
																					// such instances are very rare
						Move correct = new Move(0,0);
						boolean flagerino = false;
						int temprow = 0;
						int tempcol = 0;
						while ((temprow < 8) && (!flagerino)) {
							while ((tempcol < 8) && (!flagerino)) {
								flagerino = b.isValidMove(temprow, tempcol, human, AI);
								if (flagerino) {
									correct.setRow(temprow);
									correct.setCol(tempcol);
								}
								tempcol++;
							}
							tempcol = 0;
							temprow++;
						}
						b.makeMove(correct.getRow(), correct.getCol(), AI);
						b.Flip(correct.getRow(), correct.getCol(), human, AI);
					}
					else {
						b.makeMove(AIMove.getRow(), AIMove.getCol(), AI);
						b.Flip(AIMove.getRow(), AIMove.getCol(), human, AI);
					}
					b.print();
				}
				else{
					gameover++;
					if (gameover == 2) { //gameover == 2 only when both the players can't make a move in a row
						System.out.println("No player has available moves, game ends here.\n");
					}
					else {
						System.out.println("Opponent has no available moves, switching to you.\n");
					}
				}
				turn = 1;
			}
		}
		b.Terminal(gameover);
	}

}

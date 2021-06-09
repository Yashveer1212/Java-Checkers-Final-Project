import java.util.*;
class Main 
{
    static String switchColor;

  	public static void main(String[] args) 
  	{
		// Preconditions:
		System.out.println();
		Scanner scan = new Scanner(System.in);
		CheckerBoard board = new CheckerBoard(); //print the board
		String answer;
		boolean playing = true;

		// Part 1: Intro into the game
		System.out.println("Welcome to Checkers!");
		
		System.out.println("Do you know the rules for checkers? (yes/no)");
		answer = scan.nextLine();//"yes";//scan.nextLine(); remove this comment

		while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
		{
			System.out.println("Error: Invalid input. Enter (yes/no)");
			answer = scan.nextLine(); 
		}

		if (answer.equalsIgnoreCase("no"))
		{
			String rules = "Rules: Welcome to my checkers game. 2 players with 12 checker will play on the a 64 sqaure board. The players will use thier pieces to capture their opponents pieces. When a player has no available moves or checkers left, they lose the game. Checkers can only move diagonally and player can jump over an opponent's checker to remove it from play. Proper input for this program is:\n(OriginLetter)(OriginNumber) (DestinationLetter)(DestinationNumber)";
			System.out.println(rules);
		}

		// Part 1: Start playing the game
		System.out.println("\nWould you like to start the game? (yes/no)");
		answer = scan.nextLine(); 

		while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
		{
			System.out.println("Error: Invalid input. Enter (yes/no)");
			answer = scan.nextLine();
		}

		while (answer.equalsIgnoreCase("yes"))
		{
            int count = 0;
			    board.displayBoard(); // first board showing
            while (playing) // each turn
			{
                CheckerBoard.jumpHappened = false;
                String temp1, temp2;

                // CHANGES TURN COLOR 
                if(count % 2 == 0)
                    switchColor = "WT";
                else
                    switchColor = "RD";

                // REQUEST USER INPUT
                // Prints directions
                if (switchColor.equals("WT"))
                    System.out.println("\nEnter WHITE move (example: a3 b4):");
                else   
                    System.out.println("\nEnter RED move (example: b6 c5):");
                temp1 = scan.next(); temp2 = scan.next();

                // checks syntax, valid token being moved, if its a valid move
                while (board.incorrectSyntax(temp1, temp2) || !(switchColor.charAt(0) == board.currentTurn(temp1,temp2).charAt(0)) || !board.validMove(temp1, temp2))
                {



                    if (switchColor.equals("WT"))
                        System.out.println("Error: Invalid move entered. Enter WHITE move.");
                    else
                        System.out.println("Error: Invalid move entered. Enter RED move.");
                    temp1 = scan.next(); temp2 = scan.next();
                }
                
                board.updateBoard(temp1, temp2);
                System.out.println();
                board.displayBoard();
                


                // after the piece moves and there's another move after...
                while (board.jumpAvailable(temp2) && CheckerBoard.jumpHappened) // jumpHappened is in valid move method 
                {
                    System.out.println("You have the option to jump again. Enter (yes/no)");
                    scan.nextLine();
                    String r = scan.nextLine();
                    while (!r.equalsIgnoreCase("yes") && !r.equalsIgnoreCase("no"))
                    {
                        System.out.println("Error: Invalid input. Enter (yes/no)");
                        answer = scan.nextLine();
                    }
                    if(r.equalsIgnoreCase("yes"))
                    {
                        // System.out.println(CheckerBoard.jumpToPosition);
                        CheckerBoard.jumpHappened = false;
                        board.jump(temp2, CheckerBoard.jumpToPosition);
                        temp2 = CheckerBoard.jumpToPosition;
                        board.displayBoard();
                    }
                    else
                        break;
                }

                // checks if someone won the game. 
				if (board.winConditionsMet())
				{
                    playing = false;
					System.out.println(board.getWinner() + " is the winner. Good Game");
                    break;
				}
                count++; // changes turn.
            }


            System.out.println("Would you like to play again? (yes/no)");
            scan.nextLine();
            answer = scan.nextLine();

            while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
            {
                System.out.println("Error: Invalid input. Enter (yes/no)");
                answer = scan.nextLine();
            }



            if (answer.equalsIgnoreCase("yes"))
            {
                board.resetBoard();
                playing = true;
                System.out.println("\nNew Game: ");
            }
		}
		//****************

		System.out.println("\nThank you for playing checkers!");
    System.out.println("\nBy Yashveer Sekhon, AP CSA, Period 5");

  	}
}
import java.util.*;
class CheckerBoard
{
    public static CheckerPiece[][] board;
    public static boolean jumpHappened;
    public static String jumpToPosition = "";
    private char[] letters = new char[]{'h','g','f','e','d','c','b','a'};
	  private int[] numberIndex = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
	  private String winner;
    private Scanner scan;
	
    //******************************************************
    // CheckerBoard constructor used to put pieces on board.
    //******************************************************
	public CheckerBoard()
	{
     scan = new Scanner(System.in);
		board = new CheckerPiece[8][8];
        jumpHappened = false;

        for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
                if((i < 3) && (i % 2 == 0 && j%2 == 1 || i == 1 && j%2 == 0))
                    board[i][j] = new CheckerPiece("RD");
                else
                {
                    if((i > 4) && (i % 2 == 1 && j%2 ==0 || i == 6 && j%2 == 1))
                        board[i][j] = new CheckerPiece("WT");
                    else
				        board[i][j] = new CheckerPiece("  ");
                }
			}
		}
	}

    //**********************************************
    // Method displays the board to user each round.
    //**********************************************
	public void displayBoard()
	{
		for(int i = 0; i < 8; i++)
		{
            System.out.print(8-i);
			for (int j = 0; j < 8; j++)
			{
				System.out.print("[" + board[i][j] + "]");
                if(j ==7)
                    System.out.println();
			}
		}
        System.out.println("   A   B   C   D   E   F   G   H");
	}

    //******************************************
    // Updates the board when a white checker 
    // piece moves from one position to another.
    //******************************************
    public void updateBoard(String from, String to)
    {
        //remove initial position
        int num = 0;
        int num2 = from.charAt(1) - '1';
        char let = from.charAt(0);
        Character.toLowerCase(let);
        
        for(int i = 0; i < letters.length; i++)
            if(let == letters[i])
                num = i;
        
        num = 7 - num;
        num2 = 7 - num2;

        String initialColor = board[num2][num].getColor();
         
        for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if(i == num2 && j == num)
                {
                    board[i][j].setColor("  ");
                }
			}
		}

        //set in new position
        num = 0;
        num2 = to.charAt(1) - '1';
        let = to.charAt(0);
        Character.toLowerCase(let);

        for(int i = 0; i < letters.length; i++)
            if(let == letters[i])
                num = i;

        num = 7 - num;
        num2 = 7 - num2;
        
        for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if(i == num2 && j == num)
                {
                    board[i][j].setColor(initialColor);
                }
			}
		}

        //checks if a piece should be kinged
        checkKingsRow();
    }

    //******************************************************************
    // This method checks if a move was moved into Kings' Row.
    // This is called in UpdateBoard, so after the piece is moved.
    // If a normal piece was moved into the opposing side's Kings' row,
    // then it becomes a piece.
    //******************************************************************
    public void checkKingsRow()
    {
        if(Main.switchColor.equals("WT"))
        {
            for(int i = 0; i < 8; i++)
                if(board[0][i].getColor().equals("WT"))
                    board[0][i].setColor("WK");
        }
        else
        {
            for(int i = 0; i < 8; i++)
                if(board[7][i].getColor().equals("RD"))
                    board[7][i].setColor("RK");
        }
    }

    //***********************************************************
    // Returns true if a move is valid.
    // Method assumes true until its not.
    //***********************************************************
    public boolean validMove(String from, String to) //unfinished
    {
        if (from.equals(to)) 
        {
            System.out.println("Error: Cannot move token into same place.");
            return false;
        }

        from = from.toLowerCase();
        to = to.toLowerCase();

        int num, num2, num3, num4;    
        // num  - horizonal component of initial pos
        // num2 - vertical conponent of initial pos
        // num3 - horizonal component of final pos
        // num4 - vertical conponent of final pos

        String numVals = getNums(from, to);
        num = numVals.charAt(0) - '0';
        num2 = numVals.charAt(1) - '0';
        num3 = numVals.charAt(2) - '0';
        num4 = numVals.charAt(3) - '0';

        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if(i == num4 && j == num3)
                {
                    if(!(board[i][j].getColor().equals("  ")))
                        return false;
                }
            }
        }

        // if white piece moves backwards... NOT KING
        if(!(board[num2][num].getColor().equals("WK") || board[num2][num].getColor().equals("RK")))
        {
            if (Main.switchColor.equals("WT"))
            {
                // System.out.println(num + " " + num2 + " " + num3 + " "+ num4);
                if (num2 < num4) // if initial vertical less
                {
                    System.out.println("Error: White pawns cannot move backwards.");
                    return false;
                }
            }
            // if red piece moves backwards ... NOT KING
            if (Main.switchColor.equals("RD"))
            {
                if (num2 > num4)
                {
                    System.out.println("Error: Red pawns cannot move backwards.");
                    return false;
                }
            }
        }

        // if out of bounds. This might be useless due to syntax checking...
        if ((num > 7 || num < 0) || (num2 > 7 || num2 < 0) || (num3 > 7 || num3 < 0) || (num4 > 7 || num4 < 0))
        {
            System.out.println("Error: Out of bounds.");
            return false;
        }
            
        // if move is non diagonal
        if (num == num3 || num2 == num4 || (Math.abs(num3 - num) != Math.abs(num4 - num2)))
        {
            System.out.println("Error: Only diagonal moves allowed.");
            return false;
        }       

        // if move is too far
        if (Math.abs(num3 - num) > 1 || Math.abs(num4 - num2) > 1)
        {
            //check if user is capturing
            if (Math.abs(num3 - num) == 2 || Math.abs(num4 - num2) == 2)
            {   
                //find the in between piece
                int num5 = (num3 + num)/2;
                int num6 = (num4 + num2)/2;

                if (board[num6][num5].getColor().charAt(0) != Main.switchColor.charAt(0) && !board[num6][num5].getColor().equals("  "))
                {
                    board[num6][num5].setColor("  ");
                    jumpHappened = true;
                    return true;
                }
                // for(int i = 0; i < 8; i++)
                // {
                //     for (int j = 0; j < 8; j++)
                //     {
                //         if(i == num6 && j == num5)
                //         {
                //             if(!(board[i][j].getColor().equals(Main.switchColor)))
                //             {     
                //                 board[i][j].setColor("  ");
                //                 jumpHappened = true;
                //                 return true;
                //             }
                //         }
                //     }
                // }
            }
            System.out.println("Error: Move is too far.");
            return false;     
        }
        return true;
    }

    //*********************************************************
    // Returns numbered coordinates from user input.
    //*********************************************************
    public String getNums(String from, String to)
    {
        //get ACTUAL coords of initial position
        int num = 0;
        int num2 = from.charAt(1) - '1';
        char let = from.charAt(0);
        Character.toLowerCase(let);
        
        for (int i = 0; i < letters.length; i++)
            if (let == letters[i])
                num = i;
        
        num = 7 - num;
        num2 = 7 - num2;

        // get coords of final position
        int num3 = 0;
        int num4 = to.charAt(1) - '1';
        let = to.charAt(0);
        Character.toLowerCase(let);

        for (int i = 0; i < letters.length; i++)
            if (let == letters[i])
                num3 = i;

        num3 = 7 - num3;
        num4 = 7 - num4;

        return num + "" + num2 + "" + num3 + "" + num4;
    }

    //*********************************************************
    // Method checks if an available jump is possible.
    // Returns true if move is available.
    // False if not.
    //*********************************************************
    public boolean jumpAvailable(String startPos)
    {
        String numVals = getNums(startPos, "  ");
        int num = numVals.charAt(0) - '0';
        int num2 = numVals.charAt(1) - '0';

        // System.out.println("startpos: " + num + " " + num2);
    
        // (num - 2 and num2 - 2 (top left) == blank space) && (num-1 and num2-1 == opposite color piece)
        // (num - 2 and num2 + 2 (bot left) == blank space) && (num-1 and num2+1 == opposite color piece)
        // (num + 2 and num2 - 2 (top right) == blank space) && (num+1 and num2-1 == opposite color piece)
        // (num + 2 and num2 + 2 (bot right) == blank space) && (num+1 and num2+1 == opposite color piece)

        // White jumps
        if (Main.switchColor.equals("WT") || board[num2][num].getColor().charAt(1) == 'K')
        {
            // minus minus
            // if num-2 and num2-2 is a blank space, num-1 and num2-1 are NOT the same color or blank.
            if (num2 - 2 > -1 && num - 2 > -1)
            {
                // System.out.println((num2-2) + " " + (num-2) + " color is " + board[num2-2][num-2].getColor());
                // System.out.println("num-1 num2-1 color is " + board[num-1][num2-1].getColor());
                if (board[num2-2][num-2].getColor().equals("  ") && !(board[num2-1][num-1].getColor().equals(Main.switchColor) || (board[num2-1][num-1].getColor().equals("  "))))
                {
                    //System.out.println("joe ");
                    setVariableNamedJumpToPosition(num2-2, num-2);
                    return true;
                }
            }

            // minus plus
            if (num2 - 2 > -1 && num + 2 < 8)
            {
                if (board[num2-2][num+2].getColor().equals("  ") && !(board[num2-1][num+1].getColor().equals(Main.switchColor) || (board[num2-1][num+1].getColor().equals("  "))))
                {
                    // System.out.println("chris ");
                    setVariableNamedJumpToPosition(num2-2, num+2);
                    return true;
                }
            }
            
        }
        
        // Red jumps
        if (Main.switchColor.equals("RD") || board[num2][num].getColor().charAt(1) == 'K')
        {
            // plus minus
            if (num2 + 2 < 8 && num - 2 > -1) 
            {
                if (board[num2+2][num-2].getColor().equals("  ") && !(board[num2+1][num-1].getColor().equals(Main.switchColor) || (board[num2+1][num-1].getColor().equals("  "))))
                {
                    //System.out.println("ben ");
                    setVariableNamedJumpToPosition(num2+2, num-2);
                    return true;
                }
            }

            // plus plus
            if (num2 + 2 < 8 && num + 2 < 8)
            {
                if (board[num2+2][num+2].getColor().equals("  ") && !(board[num2+1][num+1].getColor().equals(Main.switchColor) || (board[num2+1][num+1].getColor().equals("  "))))
                {
                    // System.out.println("jackson ");
                    setVariableNamedJumpToPosition(num2+2, num+2);
                    return true;
                }
            }

        
        }

        
        // System.out.println("Have you prayed today?");
        /*
        a3 b4
        f6 e5
        b4 a5
        b6 c5
        b2 a3
        c7 b6
        c1 b2
        e5 f4
        * g3 e5
        yes
        e7 f6
        h2 g3
        d8 e7
        c7 d8
        f6 e5
        d8 c7







        */

        return false;
    }

    //***************************************************************
    // Quick method to get the game coords from the actual coords
    //***************************************************************
    public static void setVariableNamedJumpToPosition(int num, int num2)
    {
        jumpToPosition = "";
        // System.out.println(num + " " + num2);
        if (num2 == 0)
            jumpToPosition += "a";
        else if (num2 == 1)
            jumpToPosition += "b";
        else if (num2 == 2)
            jumpToPosition += "c";
        else if (num2 == 3)
            jumpToPosition += "d";
        else if (num2 == 4)
            jumpToPosition += "e";
        else if (num2 == 5)
            jumpToPosition += "f";
        else if (num2 == 6)
            jumpToPosition += "g";
        else // if (num == 7)
            jumpToPosition += "h";

        if (num == 0)
            jumpToPosition += "8";
        else if (num == 1)
            jumpToPosition += "7";
        else if (num == 2)
            jumpToPosition += "6";
        else if (num == 3)
            jumpToPosition += "5";
        else if (num == 4)
            jumpToPosition += "4";
        else if (num == 5)
            jumpToPosition += "3";
        else if (num == 6)
            jumpToPosition += "2";
        else // if (num == 7)
            jumpToPosition += "1";
    }

    //*********************************************************
    // Method jumps piece (used for mandatory jumps)
    //*********************************************************
    public void jump(String from, String to)
    {
        int num, num2, num3, num4;    
        // num  - horizonal component of initial pos
        // num2 - vertical conponent of initial pos
        // num3 - horizonal component of final pos
        // num4 - vertical conponent of final pos

        String numVals = getNums(from, to);
        num = numVals.charAt(0) - '0';
        num2 = numVals.charAt(1) - '0';
        num3 = numVals.charAt(2) - '0';
        num4 = numVals.charAt(3) - '0';

        if (Math.abs(num3 - num) > 1 || Math.abs(num4 - num2) > 1)
        {
            //check if user is capturing
            if (Math.abs(num3 - num) == 2 || Math.abs(num4 - num2) == 2)
            {   
                //find the in between piece
                int num5 = (num3 + num)/2;
                int num6 = (num4 + num2)/2;

                for(int i = 0; i < 8; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        if(i == num6 && j == num5)
                        {
                            if(!(board[i][j].getColor().equals(Main.switchColor)))
                            {     
                                board[i][j].setColor("  ");
                            }
                        }
                    }
                }
            }
        }
        updateBoard(from,to);
    }

    //*************************************************
    // Returns color of the token at coordinate system.
    //*************************************************
    public String currentTurn(String from, String to)
    {
        int num = 0;
        int num2 = from.charAt(1) - '1';
        char let = from.charAt(0);
        Character.toLowerCase(let);
        
        for(int i = 0; i < letters.length; i++)
            if(let == letters[i])
                num = i;
        
        num = 7 - num;
        num2 = 7 - num2;

        String initialColor = board[num2][num].getColor();
        return initialColor;
    }
    
    //***********************************
    // Checks if a win condition was met.
    //***********************************
    public boolean winConditionsMet()
    {
        boolean returnVal = false;
		int redCheckers = 0;
		int whiteCheckers = 0;
		
		// Counts checkers
		for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if(board[i][j].getColor().equals("RD"))
                    redCheckers++;
				else if (board[i][j].getColor().equals("WT"))
					whiteCheckers++;
				else if (board[i][j].getColor().equals("R*"))
					redCheckers++;
				else if (board[i][j].getColor().equals("W*"))
					whiteCheckers++;
			}
		}

		// if one person is out of checkers
		if (redCheckers == 0)
		{
			returnVal = true;
			winner = "White";
		}
		else if (whiteCheckers == 0)
		{
			returnVal = true;
			winner = "Red";
		}
		
		// if no available moves are available.
		if (CheckerPiece.RedNoValidMoves())
		{
			returnVal = true;
			winner = "White";
		}
		else if (CheckerPiece.WhiteNoValidMoves())
		{
			returnVal = true;
			winner = "Red";
		}


		return returnVal;
    }

    //********************************
    // Checks that the input is valid.
    // true if bad input
    // false if good 2 go
    //********************************
    public boolean incorrectSyntax(String s1, String s2)
    {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        // checks length first
        if (s1.length() != 2 || s2.length() != 2)
        {
            System.out.println("Error: Incorrect input length.");
            return true;
        }
        
        // checks characters in each string
        // checks chars
        if (!(s1.charAt(0) > 61 && s1.charAt(0) < 'z'+1) || !(s2.charAt(0) > 61 && s2.charAt(0) < 'z'+1))
        {
            System.out.println("Error: Invalid characters used.");
            return true;
        } 

        // checks number
        if (!(s1.charAt(1) > '0' && s1.charAt(1) < '9') || !(s2.charAt(1) > '0' && s2.charAt(1) < '9'))
        {
            System.out.println("Error: Invalid number.");
            return true;
        }

        return false;
    }

    //***********************************
    // Method returns name of the winner. 
    //***********************************
    public String getWinner()
    {
        return winner;
    }

    //******************
    // Resets the board.
    //******************
	public void resetBoard()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
                if((i < 3) && (i % 2 == 0 && j%2 == 1 || i == 1 && j%2 == 0))
                    board[i][j] = new CheckerPiece("RD");
                else
                {
                    if((i > 4) && (i % 2 == 1 && j%2 ==0 || i == 6 && j%2 == 1))
                        board[i][j] = new CheckerPiece("WT");
                    else
				        board[i][j] = new CheckerPiece("  ");
                }
			}
		}
	}
}
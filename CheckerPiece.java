class CheckerPiece extends Piece
{
    private String color;

    //************
    // Sets color.
    //************
	public CheckerPiece(String input)
    {
        color = input;
    }

    //***************************************************
	// Checks if white has valid moves for win condition.
    // If all existing pieces do not have valid moves. 
    //***************************************************
	public static boolean WhiteNoValidMoves()
	{
		boolean returnVal = false;

        int count = 0;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                
            }
        }
        /*
        for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (CheckerBoard.board[i][j].getColor().equals("[WT]")) // get position of piece
                {
                    try {
                        if (CheckerBoard.board[i-1][j-1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                    try {
                        if (CheckerBoard.board[i+1][j-1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                }
                else if (CheckerBoard.board[i][j].getColor().equals("[WK]"))
                {
                    try {
                        if (CheckerBoard.board[i-1][j-1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                    try {
                        if (CheckerBoard.board[i+1][j-1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                }
			}
		}
        */

		return returnVal;
	}

    //*************************************************
	// Checks if red has valid moves for win condition.
    //*************************************************
	public static boolean RedNoValidMoves()
	{
		boolean returnVal = false;

        /*
        for(int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (CheckerBoard.board[i][j].getColor().equals("[RD]")) // get position of piece
                {
                    try {
                        if (CheckerBoard.board[i-1][j+1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                    try {
                        if (CheckerBoard.board[i+1][j+1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                }
                else if (CheckerBoard.board[i][j].getColor().equals("[RK]"))
                {
                    try {
                        if (CheckerBoard.board[i-1][j+1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                    try {
                        if (CheckerBoard.board[i+1][j+1].getColor() == null)
                            returnVal = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // do nothing
                    }
                }
			}
		}
        */

		return returnVal;
	}

    //***************************
    // Sets color based on input.
    //***************************
    public void setColor(String input)
    {
        color = input;
    }

    //********************
    // Get color of piece.
    //********************
    public String getColor()
    {
        return color;
    }

    //*******************
    // Prints toString().
    //*******************
    public String toString()
    {
        return color;
    }
}

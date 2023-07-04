/**Manage the user interface of the game "Bulls And Cows".
 * 
 *@author Idan Calvo
 *@version 1.0
 */

import javax.swing.JOptionPane;

public class BullsAndCows {

	private final String GAME_NAME = "Bulls And Cows";		//The game's name
	private final int CONTINUE = 0;							//Continue the process  
	private final int STOP = 1;								//End of process
	private final int MAX_POINTS = 100;						//Max point in single match
	private final int GUESS_POINTS = -5;					//The cost for each guess (point)
	
//#Fields
	private int wins;										//Counts the number of wins
	private int losses;										//Counts the number of losses
	private int point;										//Counts the Points in campaign

//#Constructor
	public BullsAndCows() {
		super();
	}
	
//#Methods
	/**Start a new campaign
	 */
	public void newCampaign() {
		this.wins = 0;
		this.losses = 0;
		this.point = 0;
		int AnotherGame = CONTINUE;	
		
		JOptionPane.showMessageDialog(null,							
										"Welcome to the " + GAME_NAME+ " game",
										GAME_NAME,
										JOptionPane.INFORMATION_MESSAGE); //Message: Welcome
		
		while (AnotherGame == CONTINUE) {
			newGame();
			AnotherGame = JOptionPane.showConfirmDialog(null,		
												"You have "+ this.point +" points\nDo you want to play again?",
												GAME_NAME 
												,JOptionPane.YES_NO_OPTION);	//Message: Play again?
		}
		
		JOptionPane.showMessageDialog(null,							 
					"In this campaign you have achieved\n" 
							+ this.wins + " wins\n" 
							+ this.losses +  " losses\n"
							+ this.point +" points\n"
							+ "\nSee you soon!",
					GAME_NAME,
					JOptionPane.INFORMATION_MESSAGE);	//Message:The end of Campaign and presenting the score
	}//newCampaign()
			
	/**Start a new game
	 */
	private void newGame() {
		int AnotherGuess = CONTINUE;	
		Game game = new Game ();
		
		JOptionPane.showMessageDialog(null,
					"The characters allowed are : \n" 
							+ game.getVALID_CHAR() +"\nLength of code is "
							+ game.getCODE_LENGTH() +" different characters \n",
					GAME_NAME,
					JOptionPane.INFORMATION_MESSAGE); //Message: rules
		
		while (AnotherGuess == CONTINUE) {	
			AnotherGuess = newGuess(game);					//Guessing
		}
	}//newGame()
	
	/**Management guesses
	 * @param game
	 * @return Will there be another guess?
	 */
	private int newGuess(Game game) {	
		int AnotherGuess = CONTINUE;
		
		String UserGuess = JOptionPane.showInputDialog(null,
				"What's your guess?",
				GAME_NAME,
				JOptionPane.QUESTION_MESSAGE);	//Message: requests from the user to add a guess
		
		if (UserGuess == null) {							//The user has requested to log out
			this.losses++;
			AnotherGuess =  STOP;
		}
		else {	
			if (game.Guess(UserGuess)) {					//The guess was accepted
				if(game.getWin()) {							//The user has won
					userWin (game);							
					AnotherGuess =  STOP;
				}
				else {										
					JOptionPane.showMessageDialog(null,
							game.getGuessHistory(),
							GAME_NAME,
							JOptionPane.INFORMATION_MESSAGE);	//Message: Displays the history of the guesses
				}
			}
			else {											//The guess was not accepted, invalid input
				JOptionPane.showMessageDialog(null,
						"Invalid input!!!",
						GAME_NAME,
						JOptionPane.ERROR_MESSAGE);	//Message: invalid input
			}
		}
		return AnotherGuess;
	}//newGuess()

	/**Calculation of points
	 * @param NumOfGuesses = The number of user guesses 
	 * @return Points in the game
	 */
	private int CalculPoints(int NumOfGuesses) {
		return MAX_POINTS + (NumOfGuesses-1)*GUESS_POINTS;
		 
	}//CalculPoints()
	
	
	/**Management of victory summary
	 * @param game
	 */
	private void userWin (Game game) {
		int tempPoints = CalculPoints(game.getNumOfGuesses());
		
		JOptionPane.showMessageDialog(null,
				game.getGuessHistory() + "You got "+ tempPoints +" points",
				GAME_NAME,
				JOptionPane.INFORMATION_MESSAGE);	//Game statistics
		
		this.point += tempPoints;
		this.wins++;
	}//userWin()
	
}//class - BullsAndCows 

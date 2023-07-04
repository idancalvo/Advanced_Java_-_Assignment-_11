/**Game management of "Bulls And Cows". 
 *
 *@author Idan Calvo
 *@version 1.0
 */
public class Game {

	private final String VALID_CHAR = "0123456789"; // The code constructed from the following characters
	private final int CODE_LENGTH = 4; // The code length (mast be </= from the number of characters in VALID_CHAR)

//#Fields
	private String secretCode; // Stores the secret code
	private int numOfGuesses; // Counts the number of guesses
	private String GuessHistory; // Stores the guess history
	private boolean win; // Stores if the user complete the mission (win)

//#Constructor

	public Game() {
		this.win = false;
		this.numOfGuesses = 0;
		this.GuessHistory = new String("");
		setRandCode(); // sets a random code
	}

//#Methods

	// set Code methods---------------------------------------------------
	/**Initializes random code in 'secretCode'
	 */
	private void setRandCode() {
		String tempValChar = new String(VALID_CHAR);
		this.secretCode = new String("");
		int tempRandNum; // Save the random number

		for (int i = 0; i < CODE_LENGTH; i++) {
			tempRandNum = random(0, tempValChar.length() - 1); // Choosing a random number from the characters which left in
															// 'tempStr'
			secretCode += tempValChar.charAt(tempRandNum); // Adding the selected digit in 'secretCode'
			tempValChar = tempValChar.substring(0, tempRandNum) // Removing the selected character from 'tempStr'
					+ tempValChar.substring(tempRandNum + 1);
		}
		 System.out.println(this.secretCode); //***to debug***
	}//setRandCode()

	/**Returns a random number between minimum and maximum
	 * @param min = The lower limit
	 * @param max = The top limit
	 * @return a random number between min and max
	 */
	private static int random(int min, int max) {
		return (int) ((Math.random() * (max - min + 1)) + min);
	}//random()

	// main methods ------------------------------------------------------
	/**Manage the user guesses
	 * @param UserGuess = The user guess
	 * @return True if the input is correct, False if the input is wrong
	 */
	public boolean Guess(String UserGuess) {
		boolean ValidInput = false;
		
		if(UserGuess!=null) {
			UserGuess = UserGuess.replaceAll(" ", ""); // Removing spaces
			UserGuess = UserGuess.replaceAll("	", ""); // Removing tab characters

			ValidInput = lengthOfGuess(UserGuess); // Is the length correct?
			ValidInput &= ValidChar(UserGuess); // Are the characters exist in 'VALID_CHAR'?
			ValidInput &= NoDuplexInGuess(UserGuess); // Are all characters different from each other?
	
			if (ValidInput) {
				this.numOfGuesses++;
				int bulls = checkBulls(UserGuess); // Counting the number of bulls
				int cows = checkCows(UserGuess); // Counting the numbers of cows
	
				this.GuessHistory += numOfGuesses + " ) " // Adding this guess to the user history of guesses
						+ "Guess : " + UserGuess + " ,  " + "bulls : " + bulls + " ,  " + "Cows : " + cows + "\n";
	
				if (bulls == CODE_LENGTH) { // Checking if the user win the game
					this.win = true;
					this.GuessHistory += "\nyou win !!!\n\n";
					this.GuessHistory += "The number of guesses: " + this.numOfGuesses +"\n";
				}
			}
		}
		return ValidInput;
	}//Guess()

	// check valid input methods -----------------------------------------
	/**Checking if the length of the user's guess is valid
	 * @param UserGuess = The user guess
	 * @return True if the length is valid, False if the length is invalid
	 */
	private boolean lengthOfGuess(String UserGuess) {
		boolean ans = false;
		if(UserGuess!=null) {
			ans = (UserGuess.length() == CODE_LENGTH);
		}				
		return ans;
	}//lengthOfGuess()

	/**Checking if the characters in the user's guess exist in the 'VALID_CHAR'
	 * @param UserGuess = The user guess
	 * @return True if the characters exist, False if not
	 */
	private boolean ValidChar(String UserGuess) {
		boolean ans = false;
		if(UserGuess!=null) {
			ans = true;
			for (int i = 0; i < UserGuess.length(); i++) {
				if (!VALID_CHAR.contains(UserGuess.charAt(i) + "")) {
					ans = false;
				}
			}
		}
		return ans;
	}//ValidChar()

	/**Checking if there are duplication characters in the user's guess
	 * @param UserGuess = The user guess
	 * @return True if there are no duplication character, False if there are
	 *         duplication characters
	 */
	private boolean NoDuplexInGuess(String UserGuess) {
		boolean ans = false;
		if(UserGuess!=null) {
			ans = true;
			for (int i = 0; i < UserGuess.length(); i++) {
				for (int j = 0; j < UserGuess.length(); j++) {
					if (i != j && UserGuess.charAt(i) == UserGuess.charAt(j)) {
						ans = false;
					}
				}
			}
		}
		return ans;
	}//NoDuplexInGuess()


	// check Bulls & Cows methods -----------------------------------------
	/**Compare between the guess and the secretCode to find bulls
	 * @param UserGuess = User guess
	 * @return The amount of bulls
	 */
	private int checkBulls(String UserGuess) {
		int numOfBull = 0;
		if(UserGuess!=null) {
			for (int i = 0; i < UserGuess.length() && i < secretCode.length(); i++) {
				if (UserGuess.charAt(i) == secretCode.charAt(i)) { 	// checking if there is a match of location and character
					numOfBull++;
				}
			}
		}
		return numOfBull;
	}//checkBull()

	/**Compare between the guess and the secretCode to find Cows
	 * @param UserGuess = User guess
	 * @return The amount of Cows
	 */
	private int checkCows(String UserGuess) {
		int numOfCows = 0;
		if(UserGuess!=null) {
			for (int i = 0; i < secretCode.length(); i++) { // checking if the character exists in the'secretCode'
				for (int j = 0; j < UserGuess.length(); j++) {
					if (i != j && secretCode.charAt(i) == UserGuess.charAt(j)) { // checking if the characters are not in same location
						numOfCows++;
					}
				}
			}
		}
		return numOfCows;
	}//checkCows()

	// Get methods --------------------------------------------------------
	public String getGuessHistory() {
		return new String(GuessHistory);
	}
	public String getVALID_CHAR() {
		return new String(VALID_CHAR);
	}
	public int getCODE_LENGTH() {
		return CODE_LENGTH;
	}
	public boolean getWin() {
		return this.win;
	}
	public int getNumOfGuesses() {
		return numOfGuesses;
	}
	
}//Game

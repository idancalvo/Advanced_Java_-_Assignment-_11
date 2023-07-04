/**A small calculator to display calculations on rational numbers
 *
 *@author Idan Calvo
 *@version 1.0
 */
import java.util.Scanner;

public class MainOperator {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		testMiniCalculator ();
	}//main
	
	
	
	public static void testMiniCalculator () {
		int flag = 0; 													//out of loop or repeat calculation
		System.out.println("Welcome to \nthe mini calculator\nof Rational numbers");
		
		while ( flag == 0 ) { 
			System.out.println("choose two rational numbers...");
			Rationa r1 = chooseRationa("first");						//First number selection
			Rationa r2 = chooseRationa("second");						//second number selection
			
			System.out.println( showReduction(r1,r2) );					//Displays the reduction of the fractions
			System.out.println( showSL(r1, r2) );						//Presenting <>= between two rational numbers
			System.out.println( showArithmetic(r1, r2, '+') );			//Displays the addition operation
			System.out.println( showArithmetic(r1, r2, '-') ) ;			//Displays the subtraction operation
			System.out.println( showArithmetic(r1, r2, '*') );			//Displays the multiplication operation	
			
			
			System.out.println("\nFor further calculation press 0");
			System.out.println("To exit, click on any other number");
			flag = ((sc.next()).charAt(0)) - '0';
		}
	}//testMiniCalculator
	
	/**Selecting a Rational number
	 *	@param str "first" or "second" 
	 *  @return Rational number according to the user choice
	 * */
	public static Rationa chooseRationa (String str) {
		int p1,q1;														//Temporary numerator and denominator
		System.out.print("\nThe Numerator of the " + str + " number is:");
		p1 = sc.nextInt();
		System.out.print("The Denominator of the " + str + " number is:");
		q1 = sc.nextInt();
		return new Rationa(p1, q1);										//Create a rational number
	}//chooseRationa

	/**Displays the reduction of the fractions
	 * @param r1 The first rational number
	 * @param r2 The second rational number
	 * @return String of the reduction result
	 * */
	public static String showReduction (Rationa r1,Rationa r2) {
		String str = new String (); 									//String that displays the calculation
		str += "\nFirst, we will make a reduction: \n";
		
		str += "first number:\n";
		str += r1.toString();											//The number before the reduction
		str +=" = ";
		str +=(r1.reduce()).toString() + "\n";							//The number after the reduction
		
		str +="second number:\n";
		str +=r2.toString();											//The number before the reduction
		str +=" = ";
		str +=(r2.reduce()).toString() + "\n";							//The number after the reduction
		return str;
	}//showReduction
	
	/**Displays the elementary arithmetic
	 * @param r1 The first rational number
	 * @param r2 The second rational number
	 * @param c the elementary arithmetic +/-/X
	 * @return String that displays the arithmetic exercise between the two rational numbers
	 */
	public static String showArithmetic(Rationa r1, Rationa r2, char c) {
		String str = new String (); 									//String that displays the calculation
		
		str += (r1.reduce()).toString();								//The first rational number after the reduction
		str += " "+c+" ";
		str += (r2.reduce()).toString();								//The second rational number after the reduction
		str += " = ";
		
		if (c == '+') {
			str  += (  (r1.plus(r2)).reduce()  ).toString();			//Addition and reduction operations
		}
		else {
			if(c == '-') {
				str  += (  (r1.minus(r2)).reduce()  ).toString();		//Subtraction and reduction operations
			}
			else {
				str  += (  (r1.multiply(r2)).reduce()  ).toString();	//Multiplication and reduction operations
			}
		}
		return str;
	}//showArithmetic

	/**Displays an equality or inequality between the two rational numbers
	 * @param r1 The first rational number
	 * @param r2 The second rational number
	 * @return	String that displays the equality or inequality between the two rational numbers
	 */
	public static String showSL(Rationa r1, Rationa r2) {
		String str = new String ();										//String that displays the calculation
		
		str += (r1.reduce()).toString();								//The first rational number after the reduction
		
		if (r1.equals(r2)) {											//Are they equal ?
			str += " = ";
		}
		else{
			if(r1.greaterThan(r2)) {									//Is R1 bigger than R2?
				str += " > ";
			}
			else {														//Is R2 bigger than R1 ?
				str += " < ";											
			}
		}
		
		str += (r2.reduce()).toString();								//The second rational number after the reduction

		return str;
	}//showSL

}//MainOperator


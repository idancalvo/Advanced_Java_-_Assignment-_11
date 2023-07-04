/**Representation of rational numbers
 *
 *@author Idan Calvo
 *@version 1.0
 */

public class Rationa {

//#Fields
	private int P ;							//The Numerator
	private int Q ;							//The Denominator
	
	
//#Constructor	
	/**Standard Constructor
	 * @param P	The Numerator
	 * @param Q The Denominator
	 */
	public Rationa (int P, int Q) {
		if(Q == 0) {						//If the denominator is 0, the fraction is not defined
			this.P = 0;
			this.Q = 1;
		}
		else {
			if(Q < 0) {						// (+/-)-->(-/+) OR (-/-)-->(+/+)
				this.P = -1 * P;
				this.Q = -1 * Q;
			}	
			else {
				this.P = P;
				this.Q = Q;
					
			}

		}
	}
	
	
//#Methods	
	/**Checking if this rational number is bigger than another rational number
	 * @param R1 the other rational number
	 * @return True = if this rational number is bigger than R1
	 */
	public boolean greaterThan (Rationa R1) {
		if (R1==null) {							
			R1 = new Rationa (1,1);
		}
		return ( this.P * R1.getDenominator() ) > (R1.getNumerator() * this.Q) ;
	}//greaterThan
	
	/**Checking if this rational number is equal to another rational number
	 * @param R1 the other rational number
	 * @return True = if this rational number is equal to R1
	 */
	@Override
	public boolean equals (Object R1) {
		boolean ans = true;
		if ( !(R1 instanceof Rationa) || R1==null ) {
			ans = false; 
		}
		else {
			ans = ( this.P * ((Rationa)R1).getDenominator() ) == (((Rationa)R1).getNumerator() * this.Q) ;
		}
		return ans;
	}//equals
	
	/**Sum of two rational numbers
	 * @param R1 The other rational number
	 * @return Sum of this rational number and R1
	 */
	public Rationa plus (Rationa R1) {	
		if (R1==null) {
			R1 = new Rationa (1,1);
		}
		int p1,q1,p0,q0;					//Temporary numerator and denominator
		p1 = R1.getNumerator();
		q1 = R1.getDenominator();
		
		p0 = this.P * q1 + p1 * this.Q;		//Numerator calculator
		q0 = q1 * this.Q;					//Denominator calculator 

		return (new Rationa (p0,q0)) ;
	}//plus
	
	/** Subtraction between two rational numbers
	 * @param R1 The other rational number
	 * @return Subtraction of this rational number and R1
	 */
	public Rationa minus (Rationa R1) {	
		if (R1==null) {
			R1 = new Rationa (1,1);
		}
		int p1,q1,p0,q0;					//Temporary numerator and denominator
		p1 = R1.getNumerator();			
		q1 = R1.getDenominator();
		
		p0 = this.P * q1 - p1 * this.Q; 	//Numerator calculator
		q0 = q1 * this.Q;					//Denominator calculator 

		return (new Rationa (p0,q0)) ;
	}
	
	/** Multiplication between two rational numbers
	 * @param R1 The other rational number
	 * @return Multiple of this rational number and R1
	 */
	public Rationa multiply (Rationa R1) {	
		if (R1==null) {
			R1 = new Rationa (1,1);
		}
		int p1,q1,p0,q0;					//Temporary numerator and denominator
		p1 = R1.getNumerator();
		q1 = R1.getDenominator();
		
		p0 = this.P * p1;					//Numerator calculator
		q0 = this.Q * q1;					//Denominator calculator
		
		return (new Rationa (p0,q0)) ;
	}//multiply

	/**Reducing this fracture
	 * @return Rational number after reduction
	 */
	public Rationa reduce () {
		int divider = gcd (this.P,this.Q); 		//Finding a maximum divider
		return new Rationa (this.P/divider,this.Q/divider) ;	
	}

	/**A recursive function to find the maximum common divisor
	 * @param x First number 
	 * @param y Second number
	 * @return the maximum common divisor
	 */
	private int gcd (int x , int y) {
		if (y==0) {							//Euclidean algorithm
			return x;		
		}
		return gcd(y, x%y); 
	}//gcd
	
	public int getNumerator() {
		return this.P;
	}
	public int getDenominator() {
		return this.Q;
	}
	public String toString () {
		return this.P + "/"+ this.Q;
	}

}//class Rationa


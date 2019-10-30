package fractioncalculator;

public class Fraction {

	private int numerator;
	
	private int denominator;

	public Fraction() {
		super();
		this.numerator = 0;
		this.denominator = 1;
	}

	public Fraction(int numerator, int denominator) {
		super();
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero"); 
		} else if (denominator < 0) {
			this.denominator = denominator * -1;
			this.numerator = numerator * -1;
		} else {
		this.numerator = numerator;
		this.denominator = denominator;
		}
	}	

	public Fraction(int numeratorObject) {
		super();
		this.numerator = numeratorObject;
		this.denominator = 1;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	} 
	
	@Override
	public String toString() {
	    return String.format(numerator + "/" + denominator); //this.numerator
	}
	
	public double ToDouble () {
		double result = numerator / denominator; 
		return result;
	}
	
	public Fraction add(Fraction other) { //long code to understand better what is the function doing
		int numb = this.numerator * other.getDenominator();
		int denomi = this.denominator * other.getNumerator();
		int sum = numb + denomi;
		Fraction newFraction = new Fraction (sum, this.denominator * other.getDenominator());
		newFraction.toLowestTerms();
		return newFraction;
	}
	
	public Fraction substract(Fraction other) { //similar to the add function but eliminating redundancy and making it simpler 
		Fraction newFraction = new Fraction (((this.numerator * other.getDenominator()) - (this.denominator * other.getNumerator())),
				(this.denominator * other.getDenominator())); 
		newFraction.toLowestTerms();
		return newFraction;
	}
	
	public Fraction multiply(Fraction other) {
		Fraction newFraction = new Fraction((this.numerator * other.numerator), (this.denominator * other.denominator));
		newFraction.toLowestTerms();
		return newFraction;
	}
	
	public Fraction divide(Fraction other) {
		if(other.numerator == 0) {
			throw new IllegalArgumentException(); 
		} else {
		Fraction newFraction = new Fraction((this.numerator * other.denominator), (this.denominator * other.numerator));
		newFraction.toLowestTerms();
		return newFraction;
		}
	}
	
	private static int gcd(int numerator, int denominator) { //gcd=greatest common denominator
	    while (denominator != 0 && numerator != 0) {
	    	int remainder = numerator % denominator;
	    	numerator = denominator;
	    	denominator = remainder;
	    }
	    return numerator;
	}
	
	public void toLowestTerms() { 
		int gcd = gcd(this.numerator, this.denominator); 
		numerator = this.numerator / gcd;
		denominator = this.denominator / gcd;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Fraction) { //instanceof operator makes downcasting possible
			Fraction actualFraction = new Fraction(this.numerator, this.denominator);
			actualFraction.toLowestTerms();
			Fraction newFraction = (Fraction) other; //downcasting 
			newFraction.toLowestTerms();
			return ((actualFraction.numerator == newFraction.numerator) && (actualFraction.denominator == newFraction.denominator));
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
}

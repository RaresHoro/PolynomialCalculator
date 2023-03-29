package org.example.dev.model;

public class PolinomialTest {
    public static void main(String[] args) {
        // create monomials for testing
        Monomial m1 = new Monomial(3.0, 2);
        Monomial m2 = new Monomial(3.0, 2);
        Monomial m3 = new Monomial(2.0, 1);
        Monomial m4 = new Monomial(5.0, 0);

        // create polynomials for testing
        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m3);
        p1.addMonomial(m4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m2);
        p2.addMonomial(m3);
        p2.addMonomial(m4);

        // test add method
       // System.out.println("Addition: " + p1.add(p2).toString());

        // test subtract method
       // System.out.println("Subtraction: " + p1.subtract(p2).toString());

        // test multiply method
      //  System.out.println("Multiplication: " + p1.multiply(p2).toString());

        // test divide method
     //   System.out.println("Division: " + p1.divide(p2).toString());

        // test derivative method
        System.out.println("Derivative: " + p1.differentiate().toString());

        // test integral method
        System.out.println("Integral: " + p1.integrate().toString());
    }
}

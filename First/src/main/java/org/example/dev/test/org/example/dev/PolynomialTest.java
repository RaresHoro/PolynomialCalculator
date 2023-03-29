package org.example.dev;

import org.example.dev.model.Monomial;
import org.example.dev.model.Polynomial;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

class PolynomialTest {

    @Test
     void add() {
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
        Polynomial result = p1.add(p2);
        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6.0, 2));
        expected.addMonomial(new Monomial(4.0, 1));
        expected.addMonomial(new Monomial(10.0, 0));
        assertEquals(expected, result);
    }

    @Test
    void subtract() {
        Monomial m1 = new Monomial(3.0, 2);
        Monomial m2 = new Monomial(3.0, 2);
        Monomial m3 = new Monomial(2.0, 1);
        Monomial m4 = new Monomial(5.0, 0);


        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m3);
        p1.addMonomial(m4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m2);
        p2.addMonomial(m3);
        p2.addMonomial(m4);


        Polynomial result = p1.subtract(p2);
        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(0.0, 2));
        expected.addMonomial(new Monomial(0.0, 1));
        expected.addMonomial(new Monomial(0.0, 0));
        assertEquals(expected, result);
    }

    @Test
    void multiply() {
        Monomial m1 = new Monomial(3.0, 2);
        Monomial m2 = new Monomial(3.0, 2);
        Monomial m3 = new Monomial(2.0, 1);
        Monomial m4 = new Monomial(5.0, 0);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m3);
        p1.addMonomial(m4);

        Polynomial p2 = new Polynomial();
        p2.addMonomial(m2);
        p2.addMonomial(m3);
        p2.addMonomial(m4);

        Polynomial result = p1.multiply(p2);
        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(9.0, 4));
        expected.addMonomial(new Monomial(12.0, 3));
        expected.addMonomial(new Monomial(31.0, 2));
        expected.addMonomial(new Monomial(34.0, 1));
        expected.addMonomial(new Monomial(25.0, 0));
        assertEquals(expected, result);
    }

     /*@Test
     void divide() {
         Monomial m1 = new Monomial(3.0, 2);
         Monomial m2 = new Monomial(2.0, 1);
         Monomial m3 = new Monomial(1.0, 0);

         Polynomial p1 = new Polynomial();
         p1.addMonomial(m1);
         p1.addMonomial(m2);
         p1.addMonomial(m3);

         Monomial m4 = new Monomial(1.0, 1);
         Monomial m5 = new Monomial(1.0, 0);

         Polynomial p2 = new Polynomial();
         p2.addMonomial(m4);
         p2.addMonomial(m5);

         Pair<Polynomial, Polynomial> result = p1.divide(p2);
         Polynomial quotient = result.first();
         Polynomial remainder = result.second();

         Polynomial expectedQuotient = new Polynomial();
         expectedQuotient.addMonomial(new Monomial(3.0, 1));
         Polynomial expectedRemainder = new Polynomial();
         expectedRemainder.addMonomial(new Monomial(-1.0, 0));

         assertEquals(expectedQuotient, quotient);
         assertEquals(expectedRemainder, remainder);
     }
*/
    @Test
    void differentiate() {
        Monomial m1 = new Monomial(3.0, 2);
        Monomial m2 = new Monomial(2.0, 1);
        Monomial m3 = new Monomial(1.0, 0);

        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);

        Polynomial result = p1.differentiate();
        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6.0, 1));
        expected.addMonomial(new Monomial(2.0, 0));
        assertEquals(expected, result);
    }

    @Test
    void integrate() {
        Monomial m1 = new Monomial(3.0, 2);
        Monomial m2 = new Monomial(2.0, 1);
        Monomial m3 = new Monomial(1.0, 0);
        Polynomial p1 = new Polynomial();
        p1.addMonomial(m1);
        p1.addMonomial(m2);
        p1.addMonomial(m3);
        Polynomial result = p1.integrate();
        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(1.0, 3));
        expected.addMonomial(new Monomial(1.0, 2));
        expected.addMonomial(new Monomial(1.0, 1));
        expected.addMonomial(new Monomial(0.0, 0));
        assertEquals(expected, result);
    }
}
package org.example.dev.model;

import org.testng.internal.collections.Pair;

import java.util.*;

public class Polynomial {
    private TreeMap<Integer, Monomial> monomials;

    public Polynomial() {
        monomials = new TreeMap<>();
    }


    public static Polynomial parsePolynomial(String input) {

        input = input.replaceAll("\\s+", "");

        Polynomial polynomial = new Polynomial();

        // Split the input by the "+" or "-" operators
        String[] terms = input.split("(?=[+-])");


        for (String term : terms) {

            if (term.isEmpty()) {
                continue;
            }

            Monomial monomial = Monomial.parseMonomial(term);

            polynomial.addMonomial(monomial);
        }
        return polynomial;
    }

    public Polynomial(TreeMap<Integer, Monomial> monomials) {
        this.monomials = monomials;
    }

    public void addMonomial(Monomial monomial) {
        int degree = monomial.getDegree();
        Monomial existingMonomial = monomials.getOrDefault(degree, new Monomial(0, degree));
        existingMonomial.setCoefficient(existingMonomial.getCoefficient() + monomial.getCoefficient());
        monomials.put(degree, existingMonomial);
    }

    public Polynomial add(Polynomial p) {
        TreeMap<Integer, Monomial> result = new TreeMap<>(monomials);
        for (Map.Entry<Integer, Monomial> entry : p.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            Monomial existingMonomial = result.getOrDefault(degree, new Monomial(0, degree));
            existingMonomial.setCoefficient(existingMonomial.getCoefficient() + monomial.getCoefficient());
            result.put(degree, existingMonomial);
        }
        return new Polynomial(result);
    }

    public Polynomial subtract(Polynomial p) {
        TreeMap<Integer, Monomial> result = new TreeMap<>(monomials);
        for (Map.Entry<Integer, Monomial> entry : p.monomials.entrySet()) {
            int degree = entry.getKey();
            Monomial monomial = entry.getValue();
            Monomial existingMonomial = result.getOrDefault(degree, new Monomial(0, degree));
            existingMonomial.setCoefficient(existingMonomial.getCoefficient() - monomial.getCoefficient());
            result.put(degree, existingMonomial);
        }
        return new Polynomial(result);
    }

    public Polynomial multiply(Polynomial p) {
        TreeMap<Integer, Monomial> result = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry1 : monomials.entrySet()) {
            for (Map.Entry<Integer, Monomial> entry2 : p.monomials.entrySet()) {
                int degree = entry1.getKey() + entry2.getKey();

                Monomial monomial = entry1.getValue().multiply(entry2.getValue());

                Monomial existingMonomial = result.getOrDefault(degree, new Monomial(0, degree));
                existingMonomial.setCoefficient(existingMonomial.getCoefficient() + monomial.getCoefficient());
                result.put(degree, existingMonomial);
            }
        }
        return new Polynomial(result);
    }

    public Pair<Polynomial, Polynomial> divide(Polynomial divisor) {
        if (divisor.isZero()) {
            throw new ArithmeticException("Cannot divide by zero polynomial");
        }
        // long division algorithm
        Polynomial dividend = new Polynomial(new TreeMap<>(monomials));
        TreeMap<Integer, Monomial> quotient = new TreeMap<>();
        while (!dividend.isZero() && dividend.degree() >= divisor.degree()) {
            Monomial dividendLeadingTerm = dividend.leadingTerm();
            Monomial divisorLeadingTerm = divisor.leadingTerm();
            // Compute the quotient term
            Monomial quotientTerm = dividendLeadingTerm.divide(divisorLeadingTerm);
            // Add the quotient term to the quotient polynomial
            quotient.put(quotientTerm.getDegree(), quotientTerm);
            // Subtract the product of the divisor and quotient term from the dividend
            Map<Integer, Monomial> productMap = new TreeMap<>();
            productMap.put(quotientTerm.getDegree(), quotientTerm);
            Polynomial product = divisor.multiply(new Polynomial((TreeMap<Integer, Monomial>) productMap));
            dividend = dividend.subtract(product);
        }
        // The remaining dividend is the remainder
        Polynomial remainder = dividend;
        return new Pair<>(new Polynomial(quotient), remainder);
    }
    public boolean isZero() {

        for (Monomial monomial : monomials.values()) {
            if (monomial.getCoefficient() != 0) {
                return false;
            }
        }
        return true;
    }
    public int degree() {

        if (isZero()) {
            return Integer.MIN_VALUE;
        }

        // return the degree of the leading term
        return monomials.lastEntry().getValue().getDegree();
    }
    public Monomial leadingTerm() {
        if (isZero()) {
            throw new ArithmeticException("Cannot determine leading term of zero polynomial");
        }
        return monomials.lastEntry().getValue();
    }

    public Polynomial differentiate() {
        TreeMap<Integer, Monomial> result = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial monomial = entry.getValue();
            double coefficient = monomial.getCoefficient();
            int degree = monomial.getDegree();
            if (degree > 0) {
                Monomial derivative = new Monomial(coefficient * degree, degree - 1);
                result.put(derivative.getDegree(), derivative);
            }
        }
        return new Polynomial(result);
    }

    public Polynomial integrate() {
        TreeMap<Integer, Monomial> result = new TreeMap<>();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial monomial = entry.getValue();
            double coefficient = monomial.getCoefficient();
            int degree = monomial.getDegree();
            Monomial integral = new Monomial(coefficient / (degree + 1), degree + 1);
            result.put(integral.getDegree(), integral);
        }
        // Add the constant of integration
        result.put(0, new Monomial(0, 0));
        return new Polynomial(result);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Monomial> entry : monomials.entrySet()) {
            Monomial monomial = entry.getValue();
            double coefficient = monomial.getCoefficient();
            int degree = monomial.getDegree();
            if (coefficient == 0) {
                continue;
            } else if (coefficient > 0 && builder.length() > 0) {
                builder.append("+");
            }
            builder.append(monomial.toString());
        }
        if (builder.length() == 0) {
            builder.append("0");
        }
        return builder.toString();
    }
}
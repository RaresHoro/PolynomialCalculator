package org.example.dev.model;

public class Monomial implements Comparable<Monomial> {
    private double coefficient;
    private int degree;

    public Monomial(double coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
    public static Monomial parseMonomial(String input) throws NumberFormatException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new NumberFormatException("Input string is empty");
        }
        String[] parts = input.split("x");
        double coefficient;
        int degree;
        if (parts.length == 1) {
            coefficient = Double.parseDouble(parts[0]);
            degree = 0;
        } else if (parts.length == 2) {
            if (parts[0].isEmpty()) {
                coefficient = 1.0;
            } else if (parts[0].equals("-")) {
                coefficient = -1.0;
            } else {
                coefficient = Double.parseDouble(parts[0]);
            }
            degree = Integer.parseInt(parts[1].replace("^", "").trim());
        } else {
            throw new NumberFormatException("Invalid input format: " + input);
        }
        return new Monomial(coefficient, degree);
    }
    public Monomial multiply(Monomial m) {
        return new Monomial(coefficient * m.getCoefficient(), degree + m.getDegree());
    }
    public Monomial divide(Monomial divisor) {
        if (divisor.coefficient == 0) {
            throw new ArithmeticException("Cannot divide by zero monomial");
        }
        double quotientCoeff = coefficient / divisor.coefficient;
        int quotientDegree = degree - divisor.degree;
        return new Monomial(quotientCoeff, quotientDegree);
    }
    public Monomial derivative() {
        if (degree == 0) {
            return new Monomial(0, 0);
        } else {
            return new Monomial(coefficient * degree, degree - 1);
        }
    }
    public Monomial integrate() {
        return new Monomial(coefficient / (degree + 1), degree + 1);
    }

    public double evaluate(double x) {
        return coefficient * Math.pow(x, degree);
    }

    @Override
    public int compareTo(Monomial other) {
        return Integer.compare(degree, other.degree);
    }

    @Override
    public String toString() {
        if (degree == 0) {
            return Double.toString(coefficient);
        } else if (degree == 1) {
            return coefficient + "x";
        } else {
            return coefficient + "x^" + degree;
        }
    }
}

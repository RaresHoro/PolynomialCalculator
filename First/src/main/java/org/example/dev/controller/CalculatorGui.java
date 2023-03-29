package org.example.dev.controller;

import org.example.dev.model.Polynomial;
import org.testng.internal.collections.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGui extends JDialog{
    private JTextField tf1;
    private JTextField tf2;
    private JButton btnPlus;
    private JButton btnMinus;
    private JButton btnDerive;
    private JButton btnMul;
    private JButton btnDiv;
    private JButton btnInt;
    private JTextField tfResult;

    private JPanel CalculatorPanel;

    public CalculatorGui(JFrame parent) {
        super(parent);
        setTitle("Polynomial Calculator");
        setContentPane(CalculatorPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);

        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();
                String input2 = tf2.getText();
                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial p2 = Polynomial.parsePolynomial(input2);
                    Polynomial sum = p1.add(p2);
                    tfResult.setText(sum.toString());
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for add operation");
                }
            }
        });
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();
                String input2 = tf2.getText();
                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial p2 = Polynomial.parsePolynomial(input2);
                    Polynomial sub = p1.subtract(p2);
                    tfResult.setText(sub.toString());
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for minus operation");
                }
            }
        });


        btnMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();
                String input2 = tf2.getText();
                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial p2 = Polynomial.parsePolynomial(input2);
                    Polynomial product = p1.multiply(p2);
                    tfResult.setText(product.toString());
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for multiply operation");
                }
            }
        });

        btnDiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();
                String input2 = tf2.getText();
                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial p2 = Polynomial.parsePolynomial(input2);
                    Pair<Polynomial, Polynomial> divideResult = p1.divide(p2);
                    Polynomial quotient = divideResult.first();
                    Polynomial remainder = divideResult.second();
                    String resultString = "Quotient: " + quotient.toString() + "\nRemainder: " + remainder.toString();
                    tfResult.setText(resultString);
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for divide operation");
                }
            }
        });


        btnDerive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();

                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial sum = p1.differentiate();
                    tfResult.setText(sum.toString());
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for derive operation");
                }
            }
        });
        btnInt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = tf1.getText();

                try {
                    Polynomial p1 = Polynomial.parsePolynomial(input1);
                    Polynomial sum = p1.integrate();
                    tfResult.setText(sum.toString());
                } catch (IllegalArgumentException ex) {
                    tfResult.setText("Incorrect input for integrate operation");
                }
            }
        });
        setVisible(true);
    }
}


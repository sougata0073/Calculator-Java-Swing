import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.DRG_MODE;
import parser.*;

class MainFrame extends JFrame {
    JTextField inputScreen, outputScreen;
    JPanel screensPanel, buttonsPanel, degRadPanel, bracketsPanel;
    JButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero;
    JButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonPercentage, buttonDecimal, buttonPower;
    JButton buttonClear, buttonBackspace, buttonEquals;
    JButton buttonOpeningBracket, buttonClosingBracket, buttonFactorial, buttonPi, buttonE;
    JRadioButton degree, radian;
    JCheckBox inverse;
    ButtonGroup buttonGroupDegRad;
    JButton sin, cos, tan;
    MathExpression expr;
    String sinString = "sin(", cosString = "cos(", tanString = "tan(";

    public MainFrame() {
        GridBagLayout mainGridbagLayout = new GridBagLayout();
        setLayout(mainGridbagLayout);
        GridBagConstraints mainGBC = new GridBagConstraints();
        mainGBC.fill = GridBagConstraints.BOTH;

        inputScreen = new JTextField("0");
        inputScreen.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
        inputScreen.setEditable(false);
        outputScreen = new JTextField();
        outputScreen.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        outputScreen.setEditable(false);

        screensPanel = new JPanel(new GridLayout(2, 1));
        screensPanel.add(inputScreen);
        screensPanel.add(outputScreen);
        mainGBC.gridx = 0;
        mainGBC.gridy = 0;
        mainGBC.weightx = 1;
        mainGBC.weighty = 1;
        add(screensPanel, mainGBC);

        degree = new JRadioButton("Deg");
        radian = new JRadioButton("Rad");
        buttonGroupDegRad = new ButtonGroup();
        buttonGroupDegRad.add(degree);
        buttonGroupDegRad.add(radian);
        inverse = new JCheckBox("Inv");
        inverse.addActionListener(e -> {
            if (inverse.isSelected()) {
                sinString = "asin(";
                cosString = "acos(";
                tanString = "atan(";
                sin.setText("sin^-1");
                cos.setText("cos^-1");
                tan.setText("tan^-1");
            } else {
                sinString = "sin(";
                cosString = "cos(";
                tanString = "tan(";
                sin.setText("sin");
                cos.setText("cos");
                tan.setText("tan");
            }
        });
        degRadPanel = new JPanel(new GridLayout(2, 2));
        degRadPanel.add(degree);
        degRadPanel.add(radian);
        degRadPanel.add(inverse);

        sin = new JButton("sin");
        sin.addActionListener(e -> {
            String s = inputScreen.getText();
            if (s.equals("0"))
                inputScreen.setText(sinString);
            else
                inputScreen.setText(s.concat(sinString));
        });
        cos = new JButton("cos");
        cos.addActionListener(e -> {
            String s = inputScreen.getText();
            if (s.equals("0"))
                inputScreen.setText(cosString);
            else
                inputScreen.setText(s.concat(cosString));
        });
        tan = new JButton("tan");
        tan.addActionListener(e -> {
            String s = inputScreen.getText();
            if (s.equals("0"))
                inputScreen.setText(tanString);
            else
                inputScreen.setText(s.concat(tanString));
        });

        class BracketPiFactEularButtonHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputScreen.getText();
                if (e.getSource().equals(buttonOpeningBracket)) {
                    if (s.equals("0"))
                        inputScreen.setText("(");
                    else
                        inputScreen.setText(s.concat("("));
                } else if (e.getSource().equals(buttonClosingBracket)) {
                    if (s.equals("0"))
                        inputScreen.setText(")");
                    else
                        inputScreen.setText(s.concat(")"));
                } else if (e.getSource().equals(buttonFactorial)) {
                        inputScreen.setText(s.concat("!"));
                } else if (e.getSource().equals(buttonPi)) {
                    if (s.equals("0"))
                        inputScreen.setText("π");
                    else
                        inputScreen.setText(s.concat("π"));
                } else if (e.getSource().equals(buttonE)) {
                    if (s.equals("0"))
                        inputScreen.setText("e");
                    else
                        inputScreen.setText(s.concat("e"));
                }
            }
        }

        BracketPiFactEularButtonHandler bpfeButtonHandler = new BracketPiFactEularButtonHandler();
        buttonOpeningBracket = new JButton("(");
        buttonOpeningBracket.addActionListener(bpfeButtonHandler);
        buttonClosingBracket = new JButton(")");
        buttonClosingBracket.addActionListener(bpfeButtonHandler);
        buttonFactorial = new JButton("!");
        buttonFactorial.addActionListener(bpfeButtonHandler);
        buttonPi = new JButton("π");
        buttonPi.addActionListener(bpfeButtonHandler);
        buttonE = new JButton("e");
        buttonE.addActionListener(bpfeButtonHandler);

        bracketsPanel = new JPanel(new GridLayout(1, 2));
        bracketsPanel.add(buttonOpeningBracket);
        bracketsPanel.add(buttonClosingBracket);

        class NumberButtonHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputScreen.getText();
                if (e.getSource().equals(buttonZero)) {
                    if (s.equals("0"))
                        inputScreen.setText("0");
                    else
                        inputScreen.setText(s.concat("0"));
                } else if (e.getSource().equals(buttonOne)) {
                    if (s.equals("0"))
                        inputScreen.setText("1");
                    else
                        inputScreen.setText(s.concat("1"));
                } else if (e.getSource().equals(buttonTwo)) {
                    if (s.equals("0"))
                        inputScreen.setText("2");
                    else
                        inputScreen.setText(s.concat("2"));
                } else if (e.getSource().equals(buttonThree)) {
                    if (s.equals("0"))
                        inputScreen.setText("3");
                    else
                        inputScreen.setText(s.concat("3"));
                } else if (e.getSource().equals(buttonFour)) {
                    if (s.equals("0"))
                        inputScreen.setText("4");
                    else
                        inputScreen.setText(s.concat("4"));
                } else if (e.getSource().equals(buttonFive)) {
                    if (s.equals("0"))
                        inputScreen.setText("5");
                    else
                        inputScreen.setText(s.concat("5"));
                } else if (e.getSource().equals(buttonSix)) {
                    if (s.equals("0"))
                        inputScreen.setText("6");
                    else
                        inputScreen.setText(s.concat("6"));
                } else if (e.getSource().equals(buttonSeven)) {
                    if (s.equals("0"))
                        inputScreen.setText("7");
                    else
                        inputScreen.setText(s.concat("7"));
                } else if (e.getSource().equals(buttonEight)) {
                    if (s.equals("0"))
                        inputScreen.setText("8");
                    else
                        inputScreen.setText(s.concat("8"));
                } else if (e.getSource().equals(buttonNine)) {
                    if (s.equals("0"))
                        inputScreen.setText("9");
                    else
                        inputScreen.setText(s.concat("9"));
                }
            }
        }

        NumberButtonHandler nButtonHandler = new NumberButtonHandler();
        buttonZero = new JButton("0");
        buttonZero.addActionListener(nButtonHandler);
        buttonOne = new JButton("1");
        buttonOne.addActionListener(nButtonHandler);
        buttonTwo = new JButton("2");
        buttonTwo.addActionListener(nButtonHandler);
        buttonThree = new JButton("3");
        buttonThree.addActionListener(nButtonHandler);
        buttonFour = new JButton("4");
        buttonFour.addActionListener(nButtonHandler);
        buttonFive = new JButton("5");
        buttonFive.addActionListener(nButtonHandler);
        buttonSix = new JButton("6");
        buttonSix.addActionListener(nButtonHandler);
        buttonSeven = new JButton("7");
        buttonSeven.addActionListener(nButtonHandler);
        buttonEight = new JButton("8");
        buttonEight.addActionListener(nButtonHandler);
        buttonNine = new JButton("9");
        buttonNine.addActionListener(nButtonHandler);

        class OperatorButtonHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputScreen.getText();
                if (e.getSource().equals(buttonPlus)) {
                    if (!(s.equals("0") || s.isEmpty()))
                        inputScreen.setText(s.concat("+"));
                } else if (e.getSource().equals(buttonMinus))
                    inputScreen.setText(s.concat("-"));
                else if (e.getSource().equals(buttonMultiply)) {
                    if (!(s.equals("0") || s.isEmpty()))
                        inputScreen.setText(s.concat("*"));
                } else if (e.getSource().equals(buttonDivide)) {
                    if (!s.isEmpty())
                        inputScreen.setText(s.concat("/"));
                } else if (e.getSource().equals(buttonPercentage)) {
                    if (!s.isEmpty())
                        inputScreen.setText(s.concat("%"));
                } else if (e.getSource().equals(buttonDecimal)) {
                    if (s.isEmpty())
                        inputScreen.setText(".");
                    else
                        inputScreen.setText(s.concat("."));
                } else if (e.getSource().equals(buttonPower)) {
                    if (!s.isEmpty())
                        inputScreen.setText("^");
                }
            }
        }

        OperatorButtonHandler operatorButtonHandler = new OperatorButtonHandler();
        buttonPlus = new JButton("+");
        buttonPlus.addActionListener(operatorButtonHandler);
        buttonMinus = new JButton("-");
        buttonMinus.addActionListener(operatorButtonHandler);
        buttonMultiply = new JButton("*");
        buttonMultiply.addActionListener(operatorButtonHandler);
        buttonDivide = new JButton("/");
        buttonDivide.addActionListener(operatorButtonHandler);
        buttonPercentage = new JButton("%");
        buttonPercentage.addActionListener(operatorButtonHandler);
        buttonDecimal = new JButton(".");
        buttonDecimal.addActionListener(operatorButtonHandler);
        buttonPower = new JButton("^");
        buttonPower.addActionListener(operatorButtonHandler);

        buttonClear = new JButton("CLR");
        buttonClear.addActionListener(e -> inputScreen.setText("0"));
        buttonBackspace = new JButton("<-");
        buttonBackspace.addActionListener(e -> {
            String s = inputScreen.getText();
            if (s.equals("0"))
                inputScreen.setText("0");
            else if (s.isEmpty())
                inputScreen.setText("0");
            else if (s.matches(".*asin\\("))
                inputScreen.setText(s.substring(0, s.length() - 5));
            else if (s.matches(".*acos\\("))
                inputScreen.setText(s.substring(0, s.length() - 5));
            else if (s.matches(".*atan\\("))
                inputScreen.setText(s.substring(0, s.length() - 5));
            else if (s.matches(".*sin\\("))
                inputScreen.setText(s.substring(0, s.length() - 4));
            else if (s.matches(".*cos\\("))
                inputScreen.setText(s.substring(0, s.length() - 4));
            else if (s.matches(".*tan\\("))
                inputScreen.setText(s.substring(0, s.length() - 4));
            else
                inputScreen.setText(s.substring(0, s.length() - 1));
        });
        buttonEquals = new JButton("=");
        buttonEquals.addActionListener(e -> {
            try {
                expr = new MathExpression(inputScreen.getText());
                expr.setDRG(DRG_MODE.DEG);
                if (radian.isSelected()) {
                    expr.setDRG(DRG_MODE.RAD);
                }
                outputScreen.setText(expr.solve());
            }catch (Exception ignored){ }
        });

        buttonsPanel = new JPanel(new GridLayout(7, 4, 5, 5));
        buttonsPanel.add(degRadPanel);
        buttonsPanel.add(sin);
        buttonsPanel.add(cos);
        buttonsPanel.add(tan);
        buttonsPanel.add(buttonPi);
        buttonsPanel.add(buttonE);
        buttonsPanel.add(buttonFactorial);
        buttonsPanel.add(bracketsPanel);
        buttonsPanel.add(buttonPercentage);
        buttonsPanel.add(buttonClear);
        buttonsPanel.add(buttonBackspace);
        buttonsPanel.add(buttonEquals);
        buttonsPanel.add(buttonSeven);
        buttonsPanel.add(buttonEight);
        buttonsPanel.add(buttonNine);
        buttonsPanel.add(buttonDivide);
        buttonsPanel.add(buttonFour);
        buttonsPanel.add(buttonFive);
        buttonsPanel.add(buttonSix);
        buttonsPanel.add(buttonMultiply);
        buttonsPanel.add(buttonOne);
        buttonsPanel.add(buttonTwo);
        buttonsPanel.add(buttonThree);
        buttonsPanel.add(buttonMinus);
        buttonsPanel.add(buttonPower);
        buttonsPanel.add(buttonZero);
        buttonsPanel.add(buttonDecimal);
        buttonsPanel.add(buttonPlus);
        mainGBC.gridy = 1;
        mainGBC.weighty = 2;
        add(buttonsPanel, mainGBC);

        setSize(400, 600);
    }
}

public class MainClass {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ignored) {
        }

        MainFrame mf = new MainFrame();
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}
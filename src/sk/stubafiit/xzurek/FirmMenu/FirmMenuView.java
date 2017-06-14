package sk.stubafiit.xzurek.FirmMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adam Zurek on 3/11/2016.
 *
 * <u>View class from FirmMenu.</u>
 * Extends class DefaultViewParent from Defaults package.
 */
class FirmMenuView extends DefaultViewParent{

    private JLabel writing;
    private JTextField[] texField;
    private JButton butEnd, butNext;
    private JPasswordField[] passField;

    private GridBagConstraints gbc;

    /**
     * Constructor FirmMenu View.
     * initialize GUI components and calls setMethods.
     */
    FirmMenuView() {
        con = getContentPane();
        gbc = new GridBagConstraints();
        labField = new JLabel[11];
        texField = new JTextField[11];
        passField=new JPasswordField[2];
        writing=new JLabel();

        setLayout(new GridBagLayout());
        setLabels();
        setTextFields();
        setButtons();
        setFrame(con);
    }

    //SET FRAME

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons() {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        butNext=createButton("Next",6,24,con,gbc);

        gbc.anchor = GridBagConstraints.WEST;
        butEnd=createButton("Quit",0,24,con,gbc);
    }

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels() {
        gbc.gridwidth = 7;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor=GridBagConstraints.CENTER;
        writing=createLabel("Your company has not been registered yet.",0,0,con,gbc);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 24));
        gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;
        writing=createLabel("Company registration: ",0,1,con,gbc);
        writing.setForeground(new Color(58, 50, 129));
        writing.setFont(new Font("Calibri", Font.BOLD, 18));
        gbc.insets = new Insets(0, 10, 0, 10);

        gbc.anchor = GridBagConstraints.LINE_START;
        writing=createLabel("Name:",0,2,con,gbc);
        writing=createLabel("Legal form:",0,4,con,gbc);
        writing=createLabel("Adress:",0,6,con,gbc);
        writing=createLabel("Telephone:",0,8,con,gbc);
        writing=createLabel("Domain:",0,10,con,gbc);
        writing=createLabel("Business ID:",0,12,con,gbc);
        writing=createLabel("Tax ID:",0,14,con,gbc);
        writing=createLabel("VAT reg. no.:",0,16,con,gbc);
        writing=createLabel("Password:",0,18,con,gbc);
        writing=createLabel("Password ver.:",0,20,con,gbc);
        writing=createLabel("Date (DD MM YYYY):",0,22,con,gbc);
        writing=createLabel("Day:",1,22,con,gbc);
        writing=createLabel("Month:",3,22,con,gbc);
        writing=createLabel("Year:",5,22,con,gbc);

        gbc.gridwidth = 5;
        for (int i = 0; i < labField.length; ++i) {
            labField[i]=createLabel(" ",1,2*i+3,con,gbc);
            labField[i].setForeground(new Color(255,4,0));
            labField[i].setFont(new Font("Calibri", Font.ITALIC, 12));
        }
        gbc.gridwidth = 1;
    }

    /**
     * Implemented method setTexFields sets parameters.
     */
    @Override
    public void setTextFields() {
        gbc.gridwidth = 6;
        for (int i = 0; i < texField.length-3; ++i) {
            texField[i] = createTexField(30,1,2*(i+1),con,gbc);
        }
        passField[0]=createPassField(30,1,18,con,gbc);
        passField[1]=createPassField(30,1,20,con,gbc);
        gbc.gridwidth = 1;
        texField[texField.length-3] = createTexField(2,2,22,con,gbc);
        texField[texField.length-2] = createTexField(2,4,22,con,gbc);
        texField[texField.length-1] = createTexField(3,6,22,con,gbc);
    }

    //GETTERS

    /**
     * Getter gets PassField.
     * @return password field.
     */
    JPasswordField[] getPassField() {return passField;}
    /**
     * Getter gets TexField.
     * @return text field.
     */
    JTextField[] getTexField() {return texField;}
    /**
     * Getter gets ButNext.
     * @return button next.
     */
    JButton getButNext() {return butNext;}
    /**
     * Getter gets ButEnd.
     * @return button quit.
     */
    JButton getButEnd() {return butEnd;}
}
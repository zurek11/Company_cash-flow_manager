package sk.stubafiit.xzurek.LogMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zurek on 3/27/2016.
 *
 * <u>View class from LogMenu</u>
 * Extends class DefaultViewParent from Defaults package.
 */
class LogMenuView extends DefaultViewParent{
    private JTextField texField;
    private JPasswordField passField;
    private JButton butBack,butLog;

    private GridBagConstraints gbc;

    /**
     * Constructor LogMenuView.
     * initialize GUI components and calls setMethods.
     */
    LogMenuView(){
        con=getContentPane();
        gbc=new GridBagConstraints();

        texField=new JTextField();
        passField=new JPasswordField();
        labField=new JLabel[2];

        setLayout(new GridBagLayout());
        setLabels();
        setTextFields();
        setButtons();
        setFrame(con);
    }

    //FRAME SETTERS

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels() {
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor=GridBagConstraints.EAST;
        createLabel("Account name:",0,1,con,gbc);
        gbc.insets = new Insets(0, 10, 0, 10);
        createLabel("Password:",0,3,con,gbc);

        for (int i=0;i<labField.length;++i) {
            labField[i]=createLabel(" ",1,2*i+2,con,gbc);
            labField[i].setForeground(new Color(255,4,0));
            labField[i].setFont(new Font("Calibri", Font.ITALIC, 12));
        }
    }

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons() {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor=GridBagConstraints.WEST;
        butBack=createButton("Back",0,5,con,gbc);
        gbc.anchor=GridBagConstraints.EAST;
        butLog=createButton("Login",1,5,con,gbc);
    }

    /**
     * Implemented method setTexFields sets parameters.
     */
    @Override
    public void setTextFields() {
        gbc.insets = new Insets(10, 10, 0, 10);
        texField=createTexField(15,1,1,con,gbc);
        gbc.insets = new Insets(0, 10, 0, 10);
        passField=createPassField(15,1,3,con,gbc);
    }

    //GETTERS

    /**
     * Getter gets ButBack.
     * @return button back.
     */
    JButton getButBack() {return butBack;}

    /**
     * Getter gets ButLog.
     * @return button log.
     */
    JButton getButLog() {return butLog;}

    /**
     * Getter gets texFields
     * @return texFields
     */
    JTextField getTexField() {return texField;}

    /**
     * Getter gets passField
     * @return passField
     */
    JPasswordField getPassField() {return passField;}
}
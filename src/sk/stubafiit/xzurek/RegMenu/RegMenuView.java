package sk.stubafiit.xzurek.RegMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zurek on 3/24/2016.
 *
 * <u>View class from RegMenu</u>
 * Extends class DefaultViewParent from Defaults package.
 */
class RegMenuView extends DefaultViewParent{

    private JLabel writing;
    private JTextField texField;
    private JPasswordField[] passField;
    private JButton butBack, butReg;
    private JCheckBox checAdmin;

    private GridBagConstraints gbc;

    /**
     * Constructor RegMenuView.
     * initialize GUI components and calls setMethods.
     */
    RegMenuView(){
        con = getContentPane();
        gbc = new GridBagConstraints();
        labField = new JLabel[4];
        texField = new JTextField();
        passField=new JPasswordField[3];
        writing=new JLabel();

        setLayout(new GridBagLayout());
        setLabels();
        setTextFields();
        setButtons();
        setCheckBoxes();
        setFrame(con);
    }

    //SET FRAME

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons(){
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        butReg=createButton("Register",1,11,con,gbc);

        gbc.anchor = GridBagConstraints.WEST;
        butBack=createButton("Back",0,11,con,gbc);
    }

    /**
     * Implemented method setCheckBoxes sets parameters.
     */
    @Override
    public void setCheckBoxes(){
        gbc.insets = new Insets(0, 10, 0, 10);
        checAdmin=createCheckBox("Admin",0,9,con,gbc);
        checAdmin.setBackground(new Color(210, 244, 255));
        checAdmin.setSelected(false);
    }

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels(){
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor=GridBagConstraints.CENTER;
        writing=createLabel("New employee registration",0,0,con,gbc);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 24));
        gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;
        writing=createLabel("Registration: ",0,1,con,gbc);
        writing.setForeground(new Color(58, 50, 129));
        writing.setFont(new Font("Calibri", Font.BOLD, 18));
        gbc.insets = new Insets(0, 10, 0, 10);

        writing=createLabel("Account name",0,3,con,gbc);
        writing=createLabel("Password:",0,5,con,gbc);
        writing=createLabel("Password ver.:",0,7,con,gbc);

        for(int i=0;i<labField.length;++i){
            labField[i]=createLabel(" ",1,i*2+4,con,gbc);
            labField[i].setForeground(new Color(255,4,0));
            labField[i].setFont(new Font("Calibri", Font.ITALIC, 12));
        }
    }

    /**
     * Implemented method setTextFields sets parameters.
     */
    @Override
    public void setTextFields(){
        texField=createTexField(15,1,3,con,gbc);
        for (int i=0;i<passField.length;++i) {
            passField[i]=createPassField(15,1,2*i+5,con,gbc);
        }
        passField[passField.length-1].setVisible(false);
    }

    //GETTERS

    /**
     * Getter gets JTextField.
     * @return JTextField texField.
     */
    JTextField getTexField() {return texField;}

    /**
     * Getter gets JPassField
     * @return JPassField passField
     */
    JPasswordField[] getPassField() {return passField;}

    /**
     * Getter gets JButton
     * @return JButton butReg
     */
    JButton getButNext() {return butReg;}

    /**
     * Getter gets JButton
     * @return JButton butBack
     */
    JButton getButEnd() {return butBack;}

    /**
     * Getter gets JButton
     * @return JButton checAdmin
     */
    JCheckBox getChecAdmin() {return checAdmin;}
}
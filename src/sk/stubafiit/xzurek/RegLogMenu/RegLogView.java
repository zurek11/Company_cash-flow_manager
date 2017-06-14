package sk.stubafiit.xzurek.RegLogMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zurek on 3/26/2016.
 *
 * <u>View class from RegLogMenu</u>
 * Extends class DefaultViewParent from Defaults package.
 */
class RegLogView extends DefaultViewParent{

    private JButton butReg, butLog, butEnd;
    private JLabel writing;

    private GridBagConstraints gbc;

    /**
     * Constructor RegLogView.
     * initialize GUI components and calls setMethods.
     */
    RegLogView(){
        con=getContentPane();
        gbc=new GridBagConstraints();
        writing=new JLabel();

        setLayout(new GridBagLayout());
        setLabels();
        setButtons();
        setFrame(con);
    }

    //FRAME SETTERS

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons() {
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.insets = new Insets(60, 120, 10, 120);
        butLog=createButton("      Login      ",0,1,con,gbc);
        gbc.insets = new Insets(10, 120, 10, 120);
        butReg=createButton("Registration",0,2,con,gbc);
        gbc.insets = new Insets(10, 120, 120, 120);
        butEnd=createButton("        Quit        ",0,3,con,gbc);
    }

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels() {
        gbc.insets = new Insets(10, 120, 10, 120);
        writing=createLabel("Main menu",0,0,con,gbc);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 24));
    }

    //GETTER METHODS

    /**
     * Getter getButReg gets JButton butReg
     * @return JButton
     */
    JButton getButReg() {
        return butReg;
    }

    /**
     * Getter getButLog gets JButton butLog
     * @return JButton
     */
    JButton getButLog() {
        return butLog;
    }

    /**
     * Getter getButEnd gets JButton butEnd
     * @return JButton
     */
    JButton getButEnd() {
        return butEnd;
    }
}

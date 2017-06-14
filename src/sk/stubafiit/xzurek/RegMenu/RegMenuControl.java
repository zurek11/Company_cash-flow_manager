package sk.stubafiit.xzurek.RegMenu;


import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by zurek on 3/24/2016.
 *
 * <u>Control class from RegMenu</u>
 * Extends class DefaultControllerParent from Defaults package.
 */
public class RegMenuControl extends DefaultControllerParent{

    private RegMenuView view;
    private RegMenuModel model;

    /**
     * Constructor RegMenuControl.
     * Create objects model, view and calls method init.
     */
    public RegMenuControl(){
        view=new RegMenuView();
        model=new RegMenuModel();
        init();
    }

    /**
     * Method init initializes listeners to buttons.
     */
    private void init(){
        view.getButNext().addActionListener(regListener);
        view.getButEnd().addActionListener(backListener);
        view.getChecAdmin().addActionListener(checkListener);
    }

    //FRAME LISTENERS

    /**
     * Listener to Reg button
     */
    private ActionListener regListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {

            //FULLING VALUES
            model.getValues()[0]=view.getTexField().getText();
            model.getValues()[1]= String.valueOf(view.getPassField()[0].getPassword());
            model.getValues()[2]= String.valueOf(view.getPassField()[1].getPassword());
            model.getValues()[3]= String.valueOf(view.getPassField()[2].getPassword());
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year  = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();
            model.getValues()[4]=(Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year));
            model.getValues()[5]=(Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year));

            //CHECK EMPTYNESS
            if(emptyCheck(view,model,1) | !passCheck(view,model.getValues()[1],model.getValues()[2],2)){
                if(!emptyCheck(view,model,1) | !passCheck(view,model.getValues()[1],model.getValues()[2],2));
                verifyAdminPassword();
            }
            else {
                if (!view.getPassField()[view.getPassField().length-1].isVisible() | verifyAdminPassword()) {
                    model.setDatabase(model.getValues());
                    view.setVisible(false);
                    view.dispose();
                    new RegLogControl();
                }
            }
        }
    };
    /**
     * Listener to Back button
     */
    private ActionListener backListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            view.dispose();
            new RegLogControl();
        }
    };
    /**
     * Listener to Check button
     */
    private ActionListener checkListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //CHECK BOX

            if(view.getChecAdmin().isSelected()){
                view.getPassField()[view.getPassField().length-1].setVisible(true);
                model.setHierarchy(1);
                view.revalidate();
            }
            else {
                view.getPassField()[view.getPassField().length-1].setVisible(false);
                model.setHierarchy(0);
                view.getLabField()[3].setText(" ");
                view.revalidate();
            }
        }
    };

    //METHODS

    /**
     * Method verifies AdminPassword
     * @return Boolean true if is false if not
     */
    private boolean verifyAdminPassword(){
        if (view.getPassField()[view.getPassField().length-1].isVisible() &&
                !(model.getValues()[model.getValues().length-3].equals(model.getPasswordDatabase()))) {
            view.getLabField()[3].setText("Heslo sa nezhoduje!");
            view.getCon().revalidate();
            return false;
        }
        view.getLabField()[3].setText(" ");
        return true;
    }
}


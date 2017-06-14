package sk.stubafiit.xzurek.LogMenu;

import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.AdminMenu.AdminMenuControl;
import sk.stubafiit.xzurek.Defaults.DefaultUser;
import sk.stubafiit.xzurek.ManagerMenu.ManagerMenuControl;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by zurek on 3/27/2016.
 *
 * <u>Control class from LogMenu</u>
 * Extends class DefaultControllerParent from Defaults package.
 */
public class LogMenuControl extends DefaultControllerParent{
    private LogMenuView view;
    private LogMenuModel model;
    private DefaultUser user;

    /**
     * Constructor LogMenuControl.
     * Create objects model, view and calls method init.
     */
    public LogMenuControl() {
        user = DefaultUser.getINSTANCE();
        view = new LogMenuView();
        model = new LogMenuModel();
        init();
    }

    /**
     * Method init initializes listeners to buttons.
     */
    private void init(){
        view.getButLog().addActionListener(logListener);    //Agregacia => admin/manager control
        view.getButBack().addActionListener(backListener);  //Agregacia => admin/manager control
    }

    //FRAME LISTENERS

    /**
     * Listener to Log button
     */
    private ActionListener logListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {

            //FULLING VALUES
            model.getValues()[0]=view.getTexField().getText();
            model.getValues()[1]= String.valueOf(view.getPassField().getPassword());
            model.getDatabase();

            //CHECK TEXFIELDS

            if(emptyCheck(view,model,0) || (model.getHierarchy()!=1 && model.getHierarchy()!=0)){
                if(!emptyCheck(view,model,0) || !(model.getHierarchy()!=1 && model.getHierarchy()!=0)) {
                    view.getLabField()[1].setText("Wrong login or password!");
                    view.getCon().revalidate();
                }
            }
            else{
                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year  = localDate.getYear();
                int month = localDate.getMonthValue();
                int day = localDate.getDayOfMonth();
                user.setUsername(model.getUsername());
                user.setId(model.getId());
                user.setDate(Integer.toString(day)+"."+Integer.toString(month)+"."+Integer.toString(year));
                if(model.getHierarchy()==1) {
                    view.setVisible(false);
                    view.dispose();
                    new AdminMenuControl(user);
                }
                if(model.getHierarchy()==0){
                    view.setVisible(false);
                    view.dispose();
                    new ManagerMenuControl(user);
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
}
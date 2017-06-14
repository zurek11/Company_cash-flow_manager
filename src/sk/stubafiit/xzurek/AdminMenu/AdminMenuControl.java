package sk.stubafiit.xzurek.AdminMenu;

import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.Defaults.DefaultUser;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>Control class from AdminMenu</u>
 * Extends class DefaultControllerParent from Defaults package.
 */
public class AdminMenuControl extends DefaultControllerParent{
    private AdminMenuView view;
    private AdminMenuModel model;
    private DefaultUser user;

    /**
     * Constructor AdminMenuControl Control.
     * Create objects model, view and calls method init.
     * @param user aggregated Object user with parameters
     */
    public AdminMenuControl(DefaultUser user){
        view=new AdminMenuView();
        model=new AdminMenuModel();
        this.user = user;
        this.init();
    }

    /**
     * Method init initializes listeners to buttons.
     */
    private void init() {
        model.setlogDateDatabase(user.getId(),user.getDate());
        model.getIdDatabase();
        model.clearEventId();
        model.getConfirmDatabase();
        view.setView(model.getArrId(),model.getArrEventId(),user.getUsername(),user.getId());
        model.clearArrays();
        view.getListEmp().addActionListener(listEmpListener);
        view.getListEv().addActionListener(listEvListener);
        view.getDelete().addActionListener(deleteListener);
        view.getConfirm().addActionListener(confirmedListener);
        view.getLogout().addActionListener(logoutListener);
        view.getLogList().addActionListener(logListListener);
    }

    //FRAME LISTENERS

    /**
     * Frame listener to EmpList button.
     */
    private ActionListener listEmpListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getDatabase();
            view.getArea().setText("");
            for(int i=0;i<model.getArrId().length;++i){
                view.getArea().append(String.format("ID: %-2s | Username: %-10s | Password: %-15s | Admin: %-1s\n"
                        ,model.getArrId()[i], model.getArrUserName()[i], model.getArrPassword()[i], model.getArrHierarchy()[i]));
            }
            model.clearArrays();
        }
    };

    /**
     * Frame listener to EvList button.
     */
    private ActionListener listEvListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            listEvents(model,view);
        }
    };

    /**
     * Frame listener to Delete button.
     */
    private ActionListener deleteListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getIdDatabase();
            for(int i=0;i<model.getArrId().length;++i){
                if(view.getDeleteComb().getSelectedItem().toString().equals(model.getArrId()[i])){
                    if(Integer.parseInt(model.getArrId()[i])==(user.getId())){
                        view.dispose();
                        new RegLogControl();
                    }
                    model.setDeleteDatabase(Integer.parseInt(model.getArrId()[i]));
                }
            }
            model.clearArrays();
            model.getIdDatabase();
            view.getDeleteComb().removeAllItems();
            view.setDelComboBox(model.getArrId());
            model.clearArrays();
        }
    };

    /**
     * Frame listener to Confirm button.
     */
    private ActionListener confirmedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getConfirmDatabase();
            for(int i=0;i<model.getArrEventId().length;++i){
                System.out.println(model.getArrEventId()[i]);
            }
            for (int i = 0; i < model.getArrEventId().length; ++i) {
                if (view.getConfirmComb().getSelectedItem().toString().equals(model.getArrEventId()[i])) {
                    model.setConfirmDatabase(Integer.parseInt(model.getArrEventId()[i]));
                }
            }
            model.clearArrays();
            model.getConfirmDatabase();
            view.getConfirmComb().removeAllItems();
            view.setConComboBox(model.getArrEventId());
            model.clearArrays();
        }
    };
    /**
     * Frame listener to Logout button.
     */
    private ActionListener logoutListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            new RegLogControl();
        }
    };

    /**
     * Frame listener to logList button.
     */
    private ActionListener logListListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getlogDateDatabase();
            view.getArea().setText("");
            for(int i=0;i<model.getArrLDate().length;++i){
                view.getArea().append(String.format("ID: %-2s | Name: %-10s | Log Date: %-10s | Reg Date: %-10s\n"
                        ,model.getArrId()[i],model.getArrUserName()[i],model.getArrLDate()[i], model.getArrRDate()[i]));
            }
            model.clearArrays();
        }
    };
}
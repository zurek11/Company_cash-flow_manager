package sk.stubafiit.xzurek.ManagerMenu;

import sk.stubafiit.xzurek.Defaults.DefaultControllerParent;
import sk.stubafiit.xzurek.Defaults.DefaultUser;
import sk.stubafiit.xzurek.RegLogMenu.RegLogControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>Control class from ManagerMenu</u>
 * Extends class DefaultControllerParent from Defaults package.
 */
public class ManagerMenuControl extends DefaultControllerParent{
    private ManagerMenuView view;
    private ManagerMenuModel model;
    private DefaultUser user;

    /**
     * Constructor ManagerMenuControl Control.
     * Create objects model, view and calls method init.
     * @param user aggregated Object user with parameters
     */
    public ManagerMenuControl(DefaultUser user){
        this.user=user;
        view=new ManagerMenuView();
        model=new ManagerMenuModel();
        init();
    }

    /**
     * Method init initializes listeners to buttons.
     */
    private void init(){
        model.setlogDateDatabase(user.getId(),user.getDate());
        model.getDeleteDatabase();
        view.setView(model.getArrEventId(),user.getUsername(),user.getId());
        model.clearArrays();
        view.getList().addActionListener(listListener);
        view.getDelete().addActionListener(deleteListener);
        view.getLogout().addActionListener(logoutListener);
        view.getInfo().addActionListener(infoListener);
        view.getAssets().addActionListener(assListener);
        view.getAdd().addActionListener(addListener);
    }

    //FRAME LISTENERS

    /**
     * Frame listener to Info button.
     */
    private ActionListener infoListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getFirmDatabase();
            view.getArea().setText("");
            view.getArea().append(String.format("Name:         %s\nLegal form:   %s\nAdress:       %s\nTelephone:    %s\n" +
                            "Domain:       %s\nBusiness ID:  %s\nTax ID:       %s\nVAT reg. no.: %s\nDate:         %s\n"
                    ,model.getFirm()[0],model.getFirm()[1],model.getFirm()[2],model.getFirm()[3],model.getFirm()[4],model.getFirm()[5]
                    ,model.getFirm()[6],model.getFirm()[7],model.getFirm()[8]));
        }
    };
    /**
     * Frame listener to List button.
     */
    private ActionListener listListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            listEvents(model,view);
        }
    };
    /**
     * Frame listener to Delete button.
     */
    private ActionListener deleteListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.checkEmptyDatabase()){
                model.getDeleteDatabase();
                for(int i=0;i<model.getArrEventId().length;++i){
                    if(view.getDeleteComb().getSelectedItem().toString().equals(model.getArrEventId()[i])){
                        model.setDeleteDatabase(Integer.parseInt(model.getArrEventId()[i]));
                    }
                }
                model.clearArrays();
                model.getDeleteDatabase();
                view.getDeleteComb().removeAllItems();
                view.setComboBox(model.getArrEventId());
                model.clearArrays();
            }
            else{
                JOptionPane.showMessageDialog(view,"No events in the database","Inane warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    };
    /**
     * Frame listener to Logout button.
     */
    private ActionListener logoutListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            view.dispose();
            new RegLogControl();
        }
    };
    /**
     * Frame listener to Add button.
     */
    private ActionListener addListener = new ActionListener(){
        @Override
        @SuppressWarnings( "deprecation" )
        public void actionPerformed(ActionEvent e) {
            view.disable();
            view.setDialogAdd();
            view.getDiaAddAdd().addActionListener(diaAddListener);
            view.getDiaAddBack().addActionListener(diaAddBackListener);
        }
    };
    /**
     * Frame listener to Ass button.
     */
    private ActionListener assListener = new ActionListener(){
        @Override
        @SuppressWarnings( "deprecation" )
        public void actionPerformed(ActionEvent e) {
            if(model.checkEmptyDatabase()){
                view.disable();
                view.setDialogAssets(Double.toString(model.getSumDatabase())+" EUR");
                view.getDiaAssBack().addActionListener(diaAssbackListener);
                view.getDiaAssEuro().addActionListener(diaAssEuroListener);
                view.getDiaAssDollar().addActionListener(diaAssDollarListener);
                view.getDiaAssLibre().addActionListener(diaAssLibreListener);
            }
            else{
                JOptionPane.showMessageDialog(view,"No events in the database","Inane warning",JOptionPane.WARNING_MESSAGE);
            }
        }
    };

    //DIALOG_ADD LISTENER

    /**
     * Dialog listener to Add button.
     */
    private ActionListener diaAddListener = new ActionListener(){
        @Override
        @SuppressWarnings( "deprecation" )
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < model.getValues().length-3; ++i) {
                model.getValues()[i] = view.getTexField()[i].getText();
            }
            for(int i=3;i<model.getValues().length;i++){
                model.getValues()[i] = view.getTexField()[i-1].getText();
            }
            if(emptyCheck(view,model,0) || !isNumeric(view,model.getValues()[1],1) || !dayCheck(view,model.getValues()[3],2)
                    || !monthCheck(view,model.getValues()[4],2) || !yearCheck(view,model.getValues()[5],2)){
                if(!model.getValues()[1].isEmpty() && isNumeric(view,model.getValues()[1],1));
                if(model.getValues()[3].isEmpty() || !dayCheck(view,model.getValues()[3],2));
                if(model.getValues()[4].isEmpty() || !monthCheck(view,model.getValues()[4],2));
                if(model.getValues()[5].isEmpty() || !yearCheck(view,model.getValues()[5],2));
            }
            else {
                if(view.getCurrency().getSelectedItem().toString().equals("Euro")){
                    model.setValue("Euro",2);
                }
                if(view.getCurrency().getSelectedItem().toString().equals("Dollar")){
                    model.setValue(Double.toString(0.88*Double.parseDouble(model.getValues()[1])),1);
                    model.setValue("Dollar",2);
                }
                if(view.getCurrency().getSelectedItem().toString().equals("Pound")){
                    model.setValue(Double.toString(1.24*Double.parseDouble(model.getValues()[1])),1);
                    model.setValue("Pound",2);
                }
                model.getValues()[3] += "." + model.getValues()[4] + "." + model.getValues()[5];
                model.setDatabase(model.getValues());
                model.clearArrays();
                model.getDeleteDatabase();
                view.getDeleteComb().removeAllItems();
                view.setComboBox(model.getArrEventId());
                model.clearArrays();
                view.enable();
                view.getAddDialog().dispose();
            }
        }
    };
    /**
     * Dialog listener to Back button.
     */
    private ActionListener diaAddBackListener = new ActionListener() {
        @Override
        @SuppressWarnings( "deprecation" )
        public void actionPerformed(ActionEvent e) {
            view.enable();
            view.getAddDialog().dispose();
        }
    };

    //DIALOG_ASS LISTENER

    /**
     * Dialog listener to Back button.
     */
    private ActionListener diaAssbackListener = new ActionListener() {
        @Override
        @SuppressWarnings( "deprecation" )
        public void actionPerformed(ActionEvent e) {
            view.enable();
            view.getDialogAssets().dispose();
        }
    };
    /**
     * Dialog listener to Euro button.
     */
    private ActionListener diaAssEuroListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWriting().setText("Sum: "+Double.toString(model.getSumDatabase())+" EUR");
        }
    };
    /**
     * Dialog listener to Dollar button.
     */
    private ActionListener diaAssDollarListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWriting().setText("Sum: "+Double.toString(model.getSumDatabase()/0.88)+" USD");
        }
    };
    /**
     * Dialog listener to Libre button.
     */
    private ActionListener diaAssLibreListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getWriting().setText("Sum: "+Double.toString(model.getSumDatabase()/1.24)+" GBL");
        }
    };
}
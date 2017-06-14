package sk.stubafiit.xzurek.AdminMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>View class from AdminMenu</u>
 * Extends class DefaultViewParent from Defaults package.
 */
class AdminMenuView extends DefaultViewParent{

    private String username;
    private int id;

    private JButton listEmp,listEv,logList,logout,delete,confirm;
    private JComboBox<String> deleteComb,confirmComb;
    private JLabel writing;
    private GridBagConstraints gbc;

    /**
     * Constructor AdminMenuView.
     * initialize GUI components and calls setMethods.
     */
    AdminMenuView(){
        con=getContentPane();
        gbc=new GridBagConstraints();
        writing=new JLabel();

        setLayout(new GridBagLayout());
    }

    //SET FRAME

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons(){
        gbc.insets=new Insets(10,10,0,10);
        gbc.anchor = GridBagConstraints.WEST;
        listEmp=createButton("List of employees",0,1,con,gbc);
        listEv=createButton("List of events",1,1,con,gbc);
        logList=createButton("Log list",2,1,con,gbc);
        gbc.insets=new Insets(10,10,0,0);
        delete=createButton("Delete (ID):",3,1,con,gbc);
        confirm=createButton("Confirm (ID):",5,1,con,gbc);
        gbc.insets=new Insets(10,10,0,10);
        gbc.anchor = GridBagConstraints.EAST;
        logout=createButton("Logout",7,1,con,gbc);
    }

    /**
     * Method setComboBoxes sets parameters.
     * @param delList array of Strings which represents id's of non deleted users
     * @param conList array of Strings which represents id's of non confirmed events
     */
    private void setComboBoxes(String[] delList,String[] conList){
        gbc.anchor=GridBagConstraints.WEST;
        gbc.insets=new Insets(10,0,0,10);
        deleteComb=createComboBox(delList,4,1,con,gbc);
        confirmComb=createComboBox(conList,6,1,con,gbc);
    }

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels(){
        gbc.gridwidth=3;
        gbc.anchor= GridBagConstraints.WEST;
        gbc.insets=new Insets(10,10,0,10);
        writing=createLabel("Admin: "+this.username,0,0,con,gbc);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 20));
        gbc.gridwidth=1;
        gbc.anchor=GridBagConstraints.EAST;
        writing=createLabel("ID: "+this.id,7,0,con,gbc);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 20));
    }

    /**
     * Implemented method setAreas sets parameters.
     */
    @Override
    public void setAreas(){
        gbc.insets=new Insets(10,10,10,10);
        gbc.anchor= GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 3;
        gbc.weighty = 1;
        gbc.gridwidth=8;
        area = new JTextArea("",20,110);
        area.setBackground(new Color(0x77C3D9));
        area.setFont(new Font("Consolas", Font.PLAIN, 12));
        area.setEditable(true);
        area.setLineWrap(true);
        JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        con.add(pane,gbc);
        gbc.gridwidth=1;
    }
    void setView(String[] delList,String[] conList,String username,int id){
        this.username=username;
        this.id=id;
        setLabels();
        setComboBoxes(delList,conList);
        setButtons();
        setAreas();
        setFrame(con);
    }

    //GETTERS

    /**
     * Getter gets listEmp.
     * @return JButton listEmp.
     */
    JButton getListEmp() {return listEmp;}

    /**
     * Getter gets listEv.
     * @return JButton listEv.
     */
    JButton getListEv() {return listEv;}

    /**
     * Getter gets logout.
     * @return JButton logout.
     */
    JButton getLogout() {return logout;}

    /**
     * Getter gets logList.
     * @return JButton logList.
     */
    JButton getLogList() {return logList;}

    /**
     * Getter gets delete.
     * @return JButton delete.
     */
    JButton getDelete() {return delete;}

    /**
     * Getter gets confirm.
     * @return JButton confirm.
     */
    JButton getConfirm() {return confirm;}

    /**
     * Getter gets deleteComb.
     * @return JComboBox deleteComb.
     */
    JComboBox<String> getDeleteComb() {return deleteComb;}

    /**
     * Getter gets confirmComb.
     * @return JComboBox confirmComb.
     */
    JComboBox<String> getConfirmComb() {return confirmComb;}

    /**
     * Getter gets con.
     * @return Container con.
     */
    public Container getCon(){return con;}

    //SETTERS

    /**
     * Method which sets comboBox to delete boxes.
     * @param delList String array which will be deleted.
     */
    void setDelComboBox(String[] delList) {
        for(String del: delList)
            deleteComb.addItem(del);
    }

    /**
     * Method which sets comboBox to delete boxes.
     * @param delList String array which will be deleted.
     */
    void setConComboBox(String[] delList) {
        for(String del: delList)
            confirmComb.addItem(del);
    }
}
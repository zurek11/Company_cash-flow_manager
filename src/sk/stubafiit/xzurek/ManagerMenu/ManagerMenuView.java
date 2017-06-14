package sk.stubafiit.xzurek.ManagerMenu;

import sk.stubafiit.xzurek.Defaults.DefaultViewParent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>View class from ManagerMenu</u>
 * Extends class DefaultViewParent from Defaults package.
 */

class ManagerMenuView extends DefaultViewParent {

    private String username;
    private int id;

    private GridBagLayout gblPanAdd,gblPanAss;
    private JButton info,list,delete,logout,add,assets,diaAddBack,diaAddAdd,diaAssBack,diaAssEuro,diaAssDollar,diaAssLibre;
    private JTextField[] texField;
    private JLabel writing;
    private JPanel panAdd,panAss;
    private JDialog dialogAdd,dialogAssets;
    private JComboBox<String> currency,deleteComb;

    private GridBagConstraints gbcFrame,gbcDiaAdd,gbcDiaAss;

    /**
     * Constructor ManagerMenuView.
     * initialize GUI components and calls setMethods.
     */
    ManagerMenuView(){
        texField=new JTextField[5];
        labField=new JLabel[3];
        con=getContentPane();
        gbcFrame=new GridBagConstraints();
        gbcDiaAdd=new GridBagConstraints();
        gbcDiaAss=new GridBagConstraints();
        writing=new JLabel();

        setLayout(new GridBagLayout());
    }

    //VIEW SETTERS

    /**
     * Implemented method setButtons sets parameters.
     */
    @Override
    public void setButtons(){
        gbcFrame.insets=new Insets(10,10,0,10);
        gbcFrame.anchor = GridBagConstraints.CENTER;
        info=createButton("Information",0,1,con,gbcFrame);
        gbcFrame.anchor = GridBagConstraints.WEST;
        list=createButton("List of events",1,1,con,gbcFrame);
        add=createButton("Add events",2,1,con,gbcFrame);
        assets=createButton("Asset",3,1,con,gbcFrame);
        gbcFrame.insets=new Insets(10,10,0,0);
        delete=createButton("Delete (ID):",4,1,con,gbcFrame);
        gbcFrame.insets=new Insets(10,10,0,10);
        gbcFrame.anchor = GridBagConstraints.EAST;
        logout=createButton("Logout",6,1,con,gbcFrame);
    }

    /**
     * Implemented method setAreas sets parameters.
     */
    @Override
    public void setAreas(){
        gbcFrame.insets=new Insets(10,10,10,10);
        gbcFrame.anchor=GridBagConstraints.CENTER;
        gbcFrame.fill=GridBagConstraints.VERTICAL;
        gbcFrame.gridx=0;
        gbcFrame.gridy=2;
        gbcFrame.weightx=5;
        gbcFrame.weighty=1;
        gbcFrame.gridwidth=7;
        area = new JTextArea("",20,110);
        area.setBackground(new Color(0x77C3D9));
        area.setFont(new Font("Consolas", Font.PLAIN, 12));
        area.setEditable(true);
        area.setLineWrap(true);
        JScrollPane pane=new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        con.add(pane,gbcFrame);
        gbcFrame.gridwidth=1;
    }

    /**
     * Implemented method setLabels sets parameters.
     */
    @Override
    public void setLabels(){
        gbcFrame.insets=new Insets(10,10,0,10);
        gbcFrame.anchor=GridBagConstraints.WEST;
        gbcFrame.gridwidth=6;
        writing=createLabel("Manager: "+this.username,0,0,con,gbcFrame);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 16));
        gbcFrame.gridwidth=1;
        gbcFrame.anchor=GridBagConstraints.EAST;
        writing=createLabel("ID: "+this.id,6,0,con,gbcFrame);
        writing.setForeground(new Color(197, 32, 25));
        writing.setFont(new Font("Calibri", Font.BOLD, 16));
        gbcFrame.anchor=GridBagConstraints.EAST;
    }

    /**
     * Method setComboBoxes sets parameters.
     * @param delList array of Strings which represents name of the specific boxes.
     */
    private void setComboBoxes(String[] delList){
        gbcFrame.anchor=GridBagConstraints.WEST;
        gbcFrame.insets=new Insets(10,0,0,10);
        deleteComb=createComboBox(delList,5,1,con,gbcFrame);
    }

    /**
     * Method setView which sets the whole view from the controller.
     * @param delList array of Strings which represents name of the specific boxes.
     * @param username String which represents username of the user
     * @param id Integer which represents id of the user
     */
    void setView(String[] delList,String username, int id){
        this.username=username;
        this.id=id;
        setLabels();
        setButtons();
        setAreas();
        setComboBoxes(delList);
        setFrame(con);
    }

    //DialogADD SETTERS

    /**
     * Method which sets the dialogADD frame
     */
    void setDialogAdd(){
        panAdd=new JPanel();
        dialogAdd= new JDialog();
        gblPanAdd= new GridBagLayout();
        panAdd.setLayout(gblPanAdd);
        gbcDiaAdd.insets=new Insets(10,10,10,10);
        gblPanAdd.setConstraints(panAdd, gbcDiaAdd);

        setAddDialogLabels();
        setAddDialogTexFields();
        setDialogComboBox();
        setAddDialogLabFields();
        setDialogButtons();

        panAdd.setBackground(new Color(210, 244, 255));
        dialogAdd.add(panAdd);
        dialogAdd.setVisible(true);
        dialogAdd.setResizable(false);
        dialogAdd.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogAdd.pack();
        dialogAdd.setLocationRelativeTo(null);
    }

    /**
     * Method which sets buttons to the dialogADD frame
     */
    private void setDialogButtons(){
        gbcDiaAdd.insets=new Insets(0,10,10,10);
        gbcDiaAdd.anchor=GridBagConstraints.WEST;
        diaAddBack=createButton("Back",0,6,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.gridwidth=2;
        gbcDiaAdd.anchor=GridBagConstraints.EAST;
        diaAddAdd=createButton("Add",5,6,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.gridwidth=1;
    }

    /**
     * Method which sets Combo boxes to the dialogADD frame
     */
    private void setDialogComboBox(){
        gbcDiaAdd.gridwidth=2;
        gbcDiaAdd.anchor=GridBagConstraints.EAST;
        currency=createComboBox(new String[]{"Euro", "Dollar", "Pound"},5,2,panAdd,gbcDiaAdd,gblPanAdd);
        currency.setSelectedIndex(0);
        gbcDiaAdd.gridwidth=1;
    }

    /**
     * Method which sets Labels to the dialogADD frame
     */
    private void setAddDialogLabels(){
        gbcDiaAdd.anchor=GridBagConstraints.WEST;
        gbcDiaAdd.insets = new Insets(10, 10, 0, 5);
        writing=createLabel("Name of event: ",0,0,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.insets = new Insets(0, 10, 0, 5);
        writing=createLabel("Sum of event: ",0,2,panAdd,gbcDiaAdd,gblPanAdd);
        writing=createLabel("Date of event: ",0,4,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.insets = new Insets(0, 10, 0, 5);
        writing=createLabel("Day:",1,4,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.insets = new Insets(0, 0, 0, 5);
        writing=createLabel("Month:",3,4,panAdd,gbcDiaAdd,gblPanAdd);
        writing=createLabel("Year:",5,4,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.insets = new Insets(0, 10, 0, 10);
    }

    /**
     * Method which sets TextFields to the dialogADD frame`
     */
    private void setAddDialogTexFields(){
        gbcDiaAdd.gridwidth=7;
        texField[0]=createTexField(22,1,0,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.gridwidth=5;
        texField[1]=createTexField(16,1,2,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.gridwidth=1;
        gbcDiaAdd.anchor=GridBagConstraints.EAST;
        texField[2]=createTexField(2,2,4,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.anchor=GridBagConstraints.WEST;
        texField[3]=createTexField(2,4,4,panAdd,gbcDiaAdd,gblPanAdd);
        gbcDiaAdd.anchor=GridBagConstraints.WEST;
        texField[4]=createTexField(3,6,4,panAdd,gbcDiaAdd,gblPanAdd);
    }

    /**
     * Method which sets LabFields to the dialogADD frame`
     */
    private void setAddDialogLabFields(){
        gbcDiaAdd.gridwidth=7;
        gbcDiaAdd.anchor=GridBagConstraints.WEST;
        for(int i=0;i<labField.length;++i){
            labField[i]=createLabel(" ",1,(2*i)+1,panAdd,gbcDiaAdd,gblPanAdd);
            labField[i].setForeground(new Color(255,4,0));
            labField[i].setFont(new Font("Calibri", Font.ITALIC, 12));
        }
        gbcDiaAdd.gridwidth=1;
    }

    //DialogAssets SETTERS

    /**
     * Method which sets the dialogAssets frame
     * @param number String which represents number of the asset of the firm.
     */
    void setDialogAssets(String number){
        panAss=new JPanel();
        dialogAssets= new JDialog();
        gblPanAss= new GridBagLayout();
        panAss.setLayout(gblPanAss);
        gbcDiaAss.insets=new Insets(10,10,10,10);
        gblPanAss.setConstraints(panAss,gbcDiaAss);

        //Setters
        setDialogAssetsLabels(number);
        setDialogAssetsButtons();

        panAss.setBackground(new Color(210, 244, 255));
        dialogAssets.add(panAss);
        dialogAssets.setVisible(true);
        dialogAssets.setResizable(false);
        dialogAssets.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialogAssets.pack();
        dialogAssets.setLocationRelativeTo(null);
    }

    /**
     * Method which sets buttons to the dialogAssets frame
     */
    private void setDialogAssetsButtons(){
        diaAssBack=createButton("Back",0,2,panAss,gbcDiaAss,gblPanAss);
        diaAssEuro=createButton("Euro",0,1,panAss,gbcDiaAss,gblPanAss);
        diaAssDollar=createButton("Dollar",1,1,panAss,gbcDiaAss,gblPanAss);
        diaAssLibre=createButton("Pound",2,1,panAss,gbcDiaAss,gblPanAss);
    }

    /**
     * Method which sets labels to the dialogAssets frame
     * @param number String which represents number of the asset of the firm.
     */
    private void setDialogAssetsLabels(String number){
        gbcDiaAss.gridwidth=3;
        writing=createLabel("Sum: "+number,0,0,panAss,gbcDiaAss,gblPanAss);
        gbcDiaAss.gridwidth=1;
    }

    //GETTERS

    /**
     * Getter gets con.
     * @return Container con.
     */
    public Container getCon(){return con;}

    /**
     * Getter gets info.
     * @return JButton info.
     */
    JButton getInfo() {return info;}

    /**
     * Getter gets list.
     * @return JButton list.
     */
    JButton getList() {return list;}

    /**
     * Getter gets logout.
     * @return JButton logout.
     */
    JButton getLogout() {return logout;}

    /**
     * Getter gets add.
     * @return JButton add.
     */
    JButton getAdd() {return add;}

    /**
     * Getter gets assets.
     * @return JButton assets.
     */
    JButton getAssets() {return assets;}

    /**
     * Getter gets diaAddBack.
     * @return JButton diaAddBack
     */
    JButton getDiaAddBack() {return diaAddBack;}

    /**
     * Getter gets diaAddAdd.
     * @return JButton diaAddAdd.
     */
    JButton getDiaAddAdd() {return diaAddAdd;}

    /**
     * Getter gets diaAssBack.
     * @return JButton diaAssBack.
     */
    JButton getDiaAssBack() {return diaAssBack;}

    /**
     * Getter gets diaAssEuro.
     * @return JButton diaAssEuro.
     */
    JButton getDiaAssEuro() {return diaAssEuro;}

    /**
     * Getter gets diaAssDollar.
     * @return JButton diaAssDollar.
     */
    JButton getDiaAssDollar() {return diaAssDollar;}

    /**
     * Getter gets diaAssLibre.
     * @return JButton diaAssLibre.
     */
    JButton getDiaAssLibre() {return diaAssLibre;}

    /**
     * Getter gets delete.
     * @return JButton delete.
     */
    JButton getDelete() {return delete;}

    /**
     * Getter gets writing.
     * @return JLabel writing.
     */
    JLabel getWriting() {return writing;}

    /**
     * Getter gets currency.
     * @return JComboBox currency.
     */
    JComboBox getCurrency() {return currency;}

    /**
     * Getter gets deleteComb.
     * @return JComboBox deleteComb.
     */
    JComboBox<String> getDeleteComb() {return deleteComb;}

    /**
     * Getter gets texField.
     * @return JTextField texField.
     */
    JTextField[] getTexField() {return texField;}

    /**
     * Getter gets dialogAdd.
     * @return JDialog dialogAdd.
     */
    JDialog getAddDialog() {return dialogAdd;}

    /**
     * Getter gets dialogAssets.
     * @return JDialog dialogAssets.
     */
    JDialog getDialogAssets() {return dialogAssets;}

    //SETTERS

    /**
     * Method which sets comboBox to delete boxes.
     * @param delList String array which will be deleted.
     */
    void setComboBox(String[] delList) {
        for(String del: delList)
            deleteComb.addItem(del);
    }
}
package sk.stubafiit.xzurek.AdminMenu;

import sk.stubafiit.xzurek.Defaults.DefaultModelParent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>Model class from AdminMenu</u>
 * Extends class DefaultModelParent from Defaults package.
 */
class AdminMenuModel extends DefaultModelParent{

    private Connection con;
    private PreparedStatement stm;
    private ResultSet result;
    private ArrayList<String> arrPassword;
    private ArrayList<String> arrHierarchy;

    /**
     * Constructor AdminMenuModel Model.
     * Initialize ArraysLists.
     */
    AdminMenuModel(){
        arrUserName= new ArrayList<>();
        arrPassword= new ArrayList<>();
        arrHierarchy= new ArrayList<>();
        arrId= new ArrayList<>();
    }

    //SET_DATABASE

    /**
     * Method setDeleteDatabase delete specific row from the users table which represents id.
     * @param id Integer which represents the specific row which will be deleted.
     */
    void setDeleteDatabase(int id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            stm=con.prepareStatement("DELETE FROM users WHERE id = ?");
            stm.setInt(1,id);
            stm.executeUpdate();
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Method setConfirmDatabase confirm specific row from the events table which represents id.
     * @param id Integer which represents the specific row which will be updated.
     */
    void setConfirmDatabase(int id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            stm=con.prepareStatement("UPDATE events SET confirmed = 1 WHERE id = ?");
            stm.setInt(1,id);
            stm.executeUpdate();
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(-1);
        }
    }

    //GET_DATABASE

    /**
     * Method which get only id's from the events table which confirm = 0.
     */
    void getConfirmDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT id FROM events WHERE confirmed = 0");
            result = stm.executeQuery();
            while (result.next()) {
                arrEventId.add(result.getString("id"));
            }
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }

    /**
     * Overridden method getDatabase
     * @return false if problem occurs
     */
    @Override
    public boolean getDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT id,name,password,hierarchy FROM users");
            result = stm.executeQuery();

            while(result.next()){
                arrId.add(result.getString("id"));
                arrUserName.add(result.getString("name"));
                arrPassword.add(result.getString("password"));
                arrHierarchy.add(result.getString("hierarchy"));
            }
            stm.close();
            con.close();
            return true;
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
    }

    /**
     * Method which get id's from the users and events table.
     * @return false if problem occurs
     */
    boolean getIdDatabase(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT id FROM users");
            result = stm.executeQuery();
            while(result.next()) {
                arrId.add(result.getString("id"));
            }
            stm.close();
            con.close();
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT id FROM events");
            result = stm.executeQuery();
            while(result.next()) {
                arrEventId.add(result.getString("id"));
            }
            stm.close();
            con.close();
            return true;
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
    }

    //METHODS

    /**
     * Method which clear all arrays from the model.
     */
    void clearArrays(){
        arrUserName.clear();
        arrPassword.clear();
        arrHierarchy.clear();
        arrId.clear();
        arrEventId.clear();
        arrLDate.clear();
        arrRDate.clear();
        arrEventId.clear();
        arrEventName.clear();
        arrEventSum.clear();
        arrEventCurrency.clear();
        arrEventDate.clear();
    }

    /**
     * Method which clear only EventId from the model.
     */
    void clearEventId(){
        arrEventId.clear();
    }

    //GETTERS

    /**
     * Method getArrPassword.
     * @return array of String[].
     */
    String[] getArrPassword() {return arrPassword.toArray(new String[this.arrPassword.size()]);}

    /**
     * Method getArrHierarchy.
     * @return array of String[].
     */
    String[] getArrHierarchy() {return arrHierarchy.toArray(new String[this.arrHierarchy.size()]);}
}
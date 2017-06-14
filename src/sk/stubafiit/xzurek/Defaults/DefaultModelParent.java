package sk.stubafiit.xzurek.Defaults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by zurek on 3/24/2016.
 *
 * parent class DefaultModelParent
 * which is extended by Models from the project
 */
public class DefaultModelParent implements DefaultModelInterface {

    protected String[] values;
    protected ArrayList<String> arrLDate= new ArrayList<>();
    protected ArrayList<String> arrRDate= new ArrayList<>();
    protected ArrayList<String> arrUserName=new ArrayList<>();
    protected ArrayList<String> arrId=new ArrayList<>();
    protected ArrayList<String> arrEventId= new ArrayList<>();
    protected ArrayList<String> arrEventName= new ArrayList<>();
    protected ArrayList<String> arrEventSum= new ArrayList<>();
    protected ArrayList<String> arrEventCurrency= new ArrayList<>();
    protected ArrayList<String> arrEventDate= new ArrayList<>();
    private ArrayList<String> arrEventConfirmed=new ArrayList<>();

    /**
     * Method to check if database is empty
     * @return false if problem occurs
     */
    public boolean checkEmptyDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            PreparedStatement stm = con.prepareStatement("SELECT count(*) FROM events");
            ResultSet result = stm.executeQuery();
            if(result.next()){
                con.close();
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     * Method checks if String is empty
     *
     * @param value String with value which is checked
     * @return true if is empty and false if not
     */
    boolean strCheck(String value) {
        return (value.equals(""));
    }

    /**
     * Method getValues gets array of values
     * @return array of values
     */
    public String[] getValues() {
        return this.values;
    }

    /**
     * Method sets specific index from values array
     * @param value String which is to be replaced
     * @param index index where value is to be replaced
     */
    public void setValue(String value, int index) {
        this.values[index] = value;
    }

    /**
     * Overridden method setDatabase
     * @param values String array of values
     */
    @Override
    public void setDatabase(String[] values) {
    }

    /**
     * Method to set log information of users to the database
     * @param id id of user
     * @param string date of log
     */
    public void setlogDateDatabase(int id, String string) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            PreparedStatement stm = con.prepareStatement("UPDATE users SET lDate = ? WHERE id = ?");
            stm.setString(1, string);
            stm.setInt(2, id);
            stm.executeUpdate();
            stm.close();
            con.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Overridden method getDatabase
     * @return false if problem occurs
     */
    @Override
    public boolean getDatabase() {return false;}

    /**
     * Method to get log information from the database
     */
    public void getlogDateDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            PreparedStatement stm = con.prepareStatement("SELECT id,name,lDate,rDate FROM users");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                arrId.add(result.getString("id"));
                arrUserName.add(result.getString("name"));
                arrLDate.add(result.getString("lDate"));
                arrRDate.add(result.getString("rDate"));
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * Method to get event information from the database
     * @return false if problem occurs
     */
    boolean getEventDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            PreparedStatement stm = con.prepareStatement("SELECT id,name,sum,currency,date,confirmed FROM events");
            ResultSet result = stm.executeQuery();

            while(result.next()){
                arrEventId.add(result.getString("id"));
                arrEventName.add(result.getString("name"));
                arrEventSum.add(result.getString("sum"));
                arrEventCurrency.add(result.getString("currency"));
                arrEventDate.add(result.getString("date"));
                arrEventConfirmed.add(result.getString("confirmed"));
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
     * Method to clear all arrayLists.
     */
    void clearArrays(){
        arrUserName.clear();
        arrId.clear();
        arrLDate.clear();
        arrRDate.clear();
        arrEventId.clear();
        arrEventName.clear();
        arrEventSum.clear();
        arrEventCurrency.clear();
        arrEventDate.clear();
        arrEventConfirmed.clear();
    }

    /**
     * Method getArrEventDate
     * @return array of String[]
     */
    public String[] getArrLDate() {return arrLDate.toArray(new String[this.arrLDate.size()]);}

    /**
     * Method getArrEventDate
     * @return array of String[]
     */
    public String[] getArrRDate() {return arrRDate.toArray(new String[this.arrRDate.size()]);}

    /**
     * Method getArrUserName
     * @return array of String[]
     */
    public String[] getArrUserName() {return arrUserName.toArray(new String[this.arrUserName.size()]);}

    /**
     * Method getArrId
     * @return array of String[]
     */
    public String[] getArrId() {return arrId.toArray(new String[this.arrId.size()]);}
    /**
     * Method getArrEventId
     * @return array of String[]
     */
    public String[] getArrEventId() {return arrEventId.toArray(new String[this.arrEventId.size()]);}
    /**
     * Method getArrEventName
     * @return array of String[]
     */
    String[] getArrEventName() {return arrEventName.toArray(new String[this.arrEventName.size()]);}
    /**
     * Method getArrEventSum
     * @return array of String[]
     */
    String[] getArrEventSum() {return arrEventSum.toArray(new String[this.arrEventSum.size()]);}
    /**
     * Method getArrEventCurrency
     * @return array of String[]
     */
    String[] getArrEventCurrency() {return arrEventCurrency.toArray(new String[this.arrEventCurrency.size()]);}
    /**
     * Method getArrEventDate
     * @return array of String[]
     */
    String[] getArrEventDate() {return arrEventDate.toArray(new String[this.arrEventDate.size()]);}

    /**
     * Method getArrEventConfirmed
     * @return array of String[]
     */
    String[] getArrEventConfirmed() {return arrEventConfirmed.toArray(new String[this.arrEventConfirmed.size()]);}
}

package sk.stubafiit.xzurek.ManagerMenu;

import sk.stubafiit.xzurek.Defaults.DefaultModelParent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zurek on 3/28/2016.
 *
 * <u>Model class from ManagerMenu</u>
 * Extends class DefaultModelParent from Defaults package.
 */
class ManagerMenuModel extends DefaultModelParent{

    private Connection con;
    private PreparedStatement stm;
    private ResultSet result;

    private String[] firm;

    /**
     * Constructor ManagerMenuModel Model.
     * Initialize ArraysLists and arrays of String[] values.
     */
    ManagerMenuModel(){
        values=new String[6];
        firm=new String[9];
    }

    //SET_DATABASE

    /**
     * Method setDatabase creates table events where it will add specific events.
     * @param values Array of string with specific values
     */
    @Override
    public void setDatabase(String[] values){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm=con.prepareStatement("CREATE TABLE IF NOT EXISTS events (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name CHAR(100)" +
                    ",sum INTEGER,currency CHAR(6), date CHAR(10),confirmed BOOLEAN)");
            stm.executeUpdate();
            stm=con.prepareStatement("INSERT INTO events (name,sum,currency,date,confirmed) VALUES (?,?,?,?,0)");
            for(int i=0;i<values.length-2;++i){
                stm.setString(i+1,values[i]);
            }
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
     * Method setDeleteDatabase delete row with specific id
     * @param id Integer id to indicate which row to delete
     */
    void setDeleteDatabase(int id){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            stm=con.prepareStatement("DELETE FROM events WHERE id = ?");
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
     * Method to get firm information from firm table
     * @return false if problem occurs
     */
    boolean getFirmDatabase(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT name,rights,adress,telephone,domain,ico,dic,icDic,date FROM firm");
            result = stm.executeQuery();

            for(int i=0;i<firm.length-1;++i){
                firm[i]=result.getString(i+1);
            }
            firm[8]=result.getString(9);
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
     * Method to sum event prices
     * @return Double zero if problem occurs else if number is valid
     */
    double getSumDatabase(){
        try{
            double d;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT SUM(sum) FROM events WHERE confirmed = 1");
            result = stm.executeQuery();
            d=result.getDouble("SUM(sum)");
            stm.close();
            con.close();
            return d;
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return 0;
        }
    }

    /**
     * Method to get ids from the database
     * @return false if problem occurs
     */
    boolean getDeleteDatabase(){
        try{
            Class.forName("org.sqlite.JDBC");
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
            return false;
        }
    }

    //METHODS

    /**
     * Method to clear specific arrays
     */
    void clearArrays(){
        arrEventId.clear();
        arrEventName.clear();
        arrEventSum.clear();
        arrEventCurrency.clear();
        arrEventDate.clear();
    }

    //GETTERS

    /**
     * Method to get array of String which represents information about registered firm
     * @return array of Strings firm
     */
    String[] getFirm() {return firm;}
}
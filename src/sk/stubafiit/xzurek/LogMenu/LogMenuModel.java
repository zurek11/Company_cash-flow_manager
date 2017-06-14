package sk.stubafiit.xzurek.LogMenu;

import sk.stubafiit.xzurek.Defaults.DefaultModelParent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * Created by zurek on 3/27/2016.
 *
 * <u>Model class from LogMenu</u>
 * Extends class DefaultModelParent from Defaults package.
 */

class LogMenuModel extends DefaultModelParent{

    private int hierarchy;
    private String username;
    private int id;

    /**
     * Constructor LogMenuModel Model.
     * Initialize String[] values and Integer hierarchy.
     */
    LogMenuModel(){
        values=new String[2];
        hierarchy=-1;
    }

    //GET DATABASE

    /**
     * Overridden method getDatabase
     * @return false if problem occurs
     */
    @Override
    public boolean getDatabase(){
        ResultSet result;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            PreparedStatement stm = con.prepareStatement("SELECT id,name,password,hierarchy FROM users");
            result = stm.executeQuery();

            while(result.next()){
                if(Objects.equals(result.getString("name"),values[0]) && Objects.equals(result.getString("password"),values[1])){
                    hierarchy=result.getInt("hierarchy");
                    username=result.getString("name");
                    id=result.getInt("id");
                }
            }
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    //GETTERS

    /**
     * Method getHierarchy
     * @return Integer hierarchy
     */
    int getHierarchy() {return hierarchy;}

    /**
     * Method getUsername
     * @return String username
     */
    String getUsername() {return username;}

    /**
     * Method getId
     * @return Integer id
     */
    int getId() {return id;}
}
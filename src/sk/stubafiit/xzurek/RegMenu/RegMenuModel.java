package sk.stubafiit.xzurek.RegMenu;

import sk.stubafiit.xzurek.Defaults.DefaultModelParent;

import java.sql.*;

/**
 * Created by zurek on 3/24/2016.
 *
 * <u>Model class from RegMenu</u>
 * Extends class DefaultModelParent from Defaults package.
 */
class RegMenuModel extends DefaultModelParent{

    private int hierarchy=0;
    private String temp;
    private PreparedStatement stm;
    private Connection con;

    /**
     * Constructor RegMenuModel Model.
     * Initialize String[] values.
     */
    RegMenuModel(){values=new String[6];}

    //SET DATABASE

    /**
     * Overridden method setDatabase
     * @param values String array of values
     */
    @Override
    public void setDatabase(String[] values){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name CHAR(20),password CHAR(20),hierarchy BOOLEAN,rDate CHAR(10),lDate CHAR(10))");
            stm.executeUpdate();

            stm =con.prepareStatement("INSERT INTO users (name,password,hierarchy,rDate,lDate) VALUES (?,?,?,?,?)");
            stm.setString(1,values[0]);
            stm.setString(2,values[1]);
            stm.setString(3,String.valueOf(hierarchy));
            stm.setString(4,values[4]);
            stm.setString(5,values[5]);
            stm.executeUpdate();
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(-1);
        }
    }

    //GET DATABASE

    /**
     * Method getPasswordDatabase
     * @return String with password value
     */
    String getPasswordDatabase() {
        ResultSet result;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:firm.db");

            stm = con.prepareStatement("SELECT password FROM firm");
            result = stm.executeQuery();
            if (result.next()) {
                temp=result.getString("password");
                stm.close();
                con.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
        return temp;
    }

    //SETTERS

    /**
     * Method sets Integer hierarchy which depends if admin is checked or not
     * @param hierarchy Integer
     */
    void setHierarchy(int hierarchy) {this.hierarchy = hierarchy;}
}

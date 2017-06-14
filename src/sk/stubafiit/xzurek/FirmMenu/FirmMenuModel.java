package sk.stubafiit.xzurek.FirmMenu;

import sk.stubafiit.xzurek.Defaults.DefaultModelParent;

import java.sql.*;

/**
 * Created by  Adam Zurek on 3/16/2016.
 *
 * <u>Model class from FirmMenu</u>
 * Extends class DefaultModelParent from Defaults package.
 */
class FirmMenuModel extends DefaultModelParent{

    /**
     * Constructor FirmMenu Model.
     * Initialize String[] values.
     */
    FirmMenuModel(){
        values=new String[13];
    }

    //SET DATABASE

    /**
     * @param values Method setDatabase creates firm.db where it will add table users and firm.
     */
    @Override
    public void setDatabase(String[] values){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:firm.db");
            PreparedStatement stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name CHAR(20),password CHAR(20),hierarchy BOOLEAN,rDate CHAR(10),lDate CHAR(10))");
            stm.executeUpdate();
            stm=con.prepareStatement("CREATE TABLE IF NOT EXISTS firm (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,name CHAR(100),rights CHAR(50)" +
                    ", adress TEXT,telephone CHAR(20),domain CHAR(100),ico CHAR(8),dic CHAR(10),icDic CHAR(12)" +
                    ",password CHAR(20),date CHAR(10))");
            stm.executeUpdate();
            stm=con.prepareStatement("INSERT INTO firm (name,rights,adress,telephone,domain,ico,dic,icDic,password,date) VALUES (?,?,?,?,?,?,?,?,?,?)");
            for(int i=0;i<9;++i){
                stm.setString(i+1,values[i]);
            }
            stm.setString(10,values[10]);
            stm.executeUpdate();
            stm.close();
            con.close();
        }
        catch (Exception e){
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(-1);
        }
    }
}
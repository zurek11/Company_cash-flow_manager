package sk.stubafiit.xzurek.Defaults;

/**
 * Created by zurek on 4/5/2016.
 *
 * class DefaultUser is a singleton class and is used as an object
 * of every user logged in.
 */

public class DefaultUser {
    private static final DefaultUser INSTANCE=new DefaultUser();
    private String username;
    private String date;
    private int id;

    public static DefaultUser getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Private constructor of singleton
     */
    private DefaultUser(){}
    /**
     * Method setUsername sets user's username
     * @param username String with user's name
     */
    public void setUsername(String username) {this.username=username;}
    /**
     * Method setId sets user's id
     * @param id integer with user's id
     */
    public void setId(int id) {this.id=id;}

    /**
     * Method setDate sets user's log date
     * @param date String with user date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method getUsername gets user's username
     * @return String with user's name
     */
    public String getUsername() {return username;}

    /**
     * Method getId gets user's id
     * @return integer with user's id
     */
    public int getId() {return id;}

    /**
     * Method getDate gets user's date
     * @return String with user's date
     */
    public String getDate() {
        return date;
    }
}
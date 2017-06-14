package sk.stubafiit.xzurek.Defaults;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by zurek on 3/28/2016.
 *
 * parent class DefaultControllerParent includes basic methods
 * which is extended by all Controllers from the project
 */
public class DefaultControllerParent{

    /**
     * Method emptyCheck checks if values are empty
     * @param view polymorphism - parent of class view
     * @param model polymorphism - parent of class model
     * @param length index of length of string
     * @return boolean true if empty false if not
     */
    protected boolean emptyCheck(DefaultViewParent view, DefaultModelParent model,int length){
        boolean control=false;

        for (int i = 0; i < view.getLabField().length-length; ++i) {
            if (model.strCheck(model.getValues()[i])) {
                view.getLabField()[i].setText("Box no." + (i + 1)+" is empty!");
                view.getCon().revalidate();
                control = true;
            } else {
                view.getLabField()[i].setText(" ");
            }
        }
        return control;
    }

    /**
     * Method isLength checks if String is a telephone number
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @return boolean true if is false if not
     */
    protected boolean isLength(DefaultViewParent view,String string,int index){
        if(string.length()==9){
            return true;
        }
        else{
            view.getLabField()[index].setText("Please enter number without dialing code.");
            view.getCon().revalidate();
            return false;
        }
    }

    /**
     * Overloaded method isLength checks if String has some specific length
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @param length Integer how String is to be supposed length
     * @return boolean true if is false if not
     */
    protected boolean isLength(DefaultViewParent view,String string,int index,int length){
        if(string.length()==length){
            return true;
        }
        else{
            view.getLabField()[index].setText("Size must be "+length+" characters.");
            view.getCon().revalidate();
            return false;
        }
    }

    /**
     * Method isNumeric checks if String is numeric
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @return boolean true if is false if not
     */
    protected boolean isNumeric(DefaultViewParent view,String string,int index){
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(string);
        }
        catch(NumberFormatException nfe) {
            view.getLabField()[index].setText("Authorized only for numeric characters.");
            view.getCon().revalidate();
            return false;
        }
        return true;
    }

    /**
     * Method checks if passwords are equal
     * @param view polymorphism - parent of class view
     * @param pass1 String password1
     * @param pass2 String password2
     * @param index Integer where to write an error
     * @return true if passwords are equal adn false if not
     */
    protected boolean passCheck(DefaultViewParent view,String pass1,String pass2,int index){
        if(!pass1.equals(pass2)){
            view.getLabField()[index].setText("Passwords do not match!");
            view.getCon().revalidate();
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Method checks if input String is real day number
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @return boolean true if it is the day, false if not
     */
    protected boolean dayCheck(DefaultViewParent view,String string,int index){
        int day;

        try{
            day=Integer.parseInt(string);
            if(day<=31 && day>=0){
                return true;
            }
            else {
                view.getLabField()[index].setText("Day format not allowed.");
                return false;
            }
        }
        catch(NumberFormatException nfe) {
            view.getLabField()[index].setText("Day format not allowed.");
            return false;
        }
    }

    /**
     * Method checks if input String is real month number
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @return boolean true if it is the month, false if not
     */
    protected boolean monthCheck(DefaultViewParent view,String string,int index){
        int month;

        try{
            month=Integer.parseInt(string);
            if(month<=12 && month>=1){
                return true;
            }
            else {
                view.getLabField()[index].setText("Month format not allowed.");
                return false;
            }
        }
        catch(NumberFormatException nfe) {
            view.getLabField()[index].setText("Month format not allowed.");
            return false;
        }
    }

    /**
     * Method checks if input String is real year number
     * @param view polymorphism - parent of class view
     * @param string controlled string
     * @param index index where to write error
     * @return boolean true if it is the year, false if not
     */
    protected boolean yearCheck(DefaultViewParent view,String string,int index){
        int year;

        try{
            year=Integer.parseInt(string);
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int maxYear  = localDate.getYear();
            if(year<=maxYear && year>=1000){
                return true;
            }
            else {
                view.getLabField()[index].setText("Year format not allowed.");
                return false;
            }
        }
        catch(NumberFormatException nfe) {
            view.getLabField()[index].setText("Year format not allowed.");
            return false;
        }
    }

    /**
     * Method used for actionListener to list events from the database to text area
     * @param model polymorphism - parent of class model
     * @param view polymorphism - parent of class view
     */
    protected void listEvents(DefaultModelParent model,DefaultViewParent view){
        if(!model.checkEmptyDatabase()){
            JOptionPane.showMessageDialog(view,"No events in the database","Inane warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            view.getArea().setText("");
            model.getEventDatabase();
            for (int i = 0; i < model.getArrEventId().length; ++i) {
                view.getArea().append(String.format("| ID: %-2s | Name: %-15s |"
                        ,model.getArrEventId()[i], model.getArrEventName()[i]));
                if(model.getArrEventCurrency()[i].equals("Euro")){
                    view.getArea().append(String.format(" Sum: %-15f |",Double.parseDouble(model.getArrEventSum()[i])));
                }
                if(model.getArrEventCurrency()[i].equals("Dollar")){
                    view.getArea().append(String.format(" Sum: %-15f |",Double.parseDouble(model.getArrEventSum()[i])/0.88));
                }
                if(model.getArrEventCurrency()[i].equals("Pound")){
                    view.getArea().append(String.format(" Sum: %-15f |",Double.parseDouble(model.getArrEventSum()[i])/1.24));
                }
                view.getArea().append(String.format(" Currency: %-6s | Date: %-10s | Confirmed: %-1s |\n"
                        ,model.getArrEventCurrency()[i], model.getArrEventDate()[i], model.getArrEventConfirmed()[i]));
            }
            model.clearArrays();
        }
    }
}
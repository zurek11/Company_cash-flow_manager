package sk.stubafiit.xzurek.Defaults;

/**
 * Created by zurek on 4/7/2016.
 *
 * Interface implemented by class DefaultModelParent
 */
interface DefaultModelInterface {
    /**
     * Method setDatabase set the specific database with parameters
     * @param values String array of values
     */
    void setDatabase(String[] values);

    /**
     * Method getDatabase get specific values from the database
     * @return false if problem occurs
     */
    boolean getDatabase();
}

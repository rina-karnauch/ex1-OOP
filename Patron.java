/*
 * Representes a code for a class of PATRON(borrower of books in a library)
 * the patron's informations can be given or set by it's methods.
 * @author API by OOP staff API and methods by rina karnauch
 */
public class Patron {

    // CLASS MEMBERS
    /**
     * the first name of the Patron
     */
    final String firstName;

    /**
     * the last name of the Patron
     */
    final String lastName;

    /**
     * value of comic Tendency of patron
     */
    int comicTendency;

    /**
     * value of dramatic Tendency of patron
     */
    int dramaticTendency;

    /**
     * value of educational Tendency of patron
     */
    int educationalTendency;

    /**
     * value of wanted Enjoyment atleast rate for Patron
     */
    int EnjoymentThreshold;

    //CONS

    /**
     * Creates a new Patron with the given characteristic.
     * @param patronFirstName the Patron's first name
     * @param patronLastName the Patron's last name
     * @param comicTendency the Patron's comic tendency value
     * @param dramaticTendency the Patron's dramatic tendency value
     * @param educationalTendency the Patron's educational tendency value
     * @param patronEnjoymentThreshold the Patron's requirment for enjoyment threshold
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {

        this.firstName = patronFirstName;
        this.lastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.EnjoymentThreshold = patronEnjoymentThreshold;
    }

    /**
     * Returns a string representation of the patron. which is a sequence of its first and last name,
     * separated by a single white space. For example, if the patron's first name is "Ricky" and his last name
     * is "Bobby" this method will return the String "Ricky Bobby".
     * @return string representation of the patron.
     */
    String stringRepresentation() {
        return (this.firstName + " " + this.lastName);
    }

    // METHODS

    /**
     * @param book The book to asses.
     * @return the literary value this patron assigns to the given book.
     */
    // no need to check for null
    // exists in API
    int getBookScore(Book book) {
        // those lines are written so i have a peace of mind and not a code written like a monkey with
        // million calculations at the same line, which makes it easier for me to detect problems ahead
        int comicMul = this.comicTendency * book.comicValue;
        int educationalMul = this.educationalTendency * book.educationalValue;
        int dramaticMul = this.dramaticTendency * book.dramaticValue;
        return (dramaticMul + comicMul + educationalMul);
    }

    /**
     * @param book The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    // no need to check for null
    // exists in API
    boolean willEnjoyBook(Book book) {
        return (this.getBookScore(book) >= EnjoymentThreshold);
    }
}

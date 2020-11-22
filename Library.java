/*
 * Representes a code for a class of LIBRARY, which uses books and patrons objects.
 * the library informations can be given or set by it's methods.
 * @author API by OOP staff API and methods by rina karnauch
 */
class Library {

    // CLASS MEMBERS
    /**
     * library capacity of books.
     */
    int libraryCapacity;

    /**
     * limit of books to be borrowed by registered patrons.
     */
    int borrowedCapacity;

    /**
     * limit of amount of registered patrons in the library
     */
    int patronsCapacity;

    /**
     * data base of books registered in the library.
     */
    Book[] books;

    /**
     * data base of patrons registered in the library at each current time.
     */
    Patron[] registeredPatrons;

    /**
     * data base of borrowed books by patrons registered in the library at each current time amount is placed
     * at the index of the patron's ID and is stored there.
     */
    int[] patronBorrowedBooksCounter;


    /**
     * the counter of current amount of registered books.
     */
    int bookCount = 0; // always the next index(place) to add to


    /**
     * the counter of current amount of registered patrons.
     */
    int patronCount = 0; // always the next index(place) to add to


    /**
     * a const of 0 because we don't like no magic numbers.
     */
    static final int ZERO = 0;


    /**
     * a const of 1 because we don't like no magic numbers.
     */
    static final int ONE = 1;


    /**
     * a const of -1 because we don't like no magic numbers.
     */
    static final int ERROR = -1;

    // CONS

    /**
     * Creates a new library with the given parameters.
     * @param maxBookCapacity The maximal number of books this library can hold.
     * @param maxBorrowedBooks The maximal number of books this library allows a single patron to
     *         borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    // exists in API
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {

        this.libraryCapacity = maxBookCapacity;
        this.borrowedCapacity = maxBorrowedBooks;
        this.patronsCapacity = maxPatronCapacity;

        books = new Book[maxBookCapacity];
        registeredPatrons = new Patron[maxPatronCapacity];
        patronBorrowedBooksCounter = new int[maxPatronCapacity];
    }

    // METHODS

    /**
     * Check if the book exists in the Library
     * @param book the book to be checked
     * @return non negative index if book is inside(index of book) and -1 if the book isn't inside.
     */
    // DOESN'T exists in API
    int containsByBook(Book book) {
        for (int i = ZERO; i < bookCount; i++) {
            if (books[i] == book) {
                return i;
            }
        }
        return ERROR;
    }

    /**
     * Check if the patron exists in the Library records
     * @param patron the patron object to check
     * @return non negative index if book is inside(index of book) and -1 if the book isn't inside.
     */
    // DOESN'T exists in API
    int containsPatron(Patron patron) {
        for (int i = ZERO; i < patronCount; i++) {
            if (registeredPatrons[i] == patron) {
                return i;
            }
        }
        return ERROR;
    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     * @param book the book to be added
     * @return non-negative id number for the book if there was a spot and the book was successfully added, or
     *         if the book was already in the library; a negative number otherwise.
     */
    // no need to check for null,
    // BUT check if book is already inside.
    // exists in API
    int addBookToLibrary(Book book) {
        int indexOfBook = containsByBook(book);
        if (indexOfBook != ERROR) {
            // we already have this book because is has an index so the value that would be returned is not
            // -1.
            return indexOfBook;
        } else if (bookCount == libraryCapacity) {
            return ERROR;
        }
        books[bookCount] = book;
        bookCount += ONE;
        return bookCount - ONE;
    }

    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    // exists in API
    boolean isBookIdValid(int bookId) {
        // invalid bookID
        return bookId >= ZERO && bookId < this.libraryCapacity && bookId < bookCount;

    }

    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    // check if book is even present inside
    // exists in API
    int getBookId(Book book) {
        return containsByBook(book);
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    // exists in API
    boolean isBookAvailable(int bookId) {
        if (bookId < ZERO || bookId > libraryCapacity) {
            // bookId invalid
            return false;
        } else if (books[bookId] == null) {
            return false;
        } else {
            return (books[bookId].getCurrentBorrowerId() == ERROR);
        }
    }

    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     *         registered or if the patron was already registered. a negative number otherwise.
     */
    // exists in API
    int registerPatronToLibrary(Patron patron) {
        if (patron == null) {
            // patron is not valid
            return ERROR;
        }

        int index = containsPatron(patron);
        if (index != ERROR) {
            // no such patron registered
            return containsPatron(patron);
        } else if (patronCount >= patronsCapacity) {
            // patrons are overflowed
            return ERROR;
        }
        // we can add if we have reached here
        registeredPatrons[patronCount] = patron;
        patronBorrowedBooksCounter[patronCount] = ZERO;
        patronCount += ONE;
        return patronCount - ONE;
    }

    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     * @param patronId The patron for which to find the id number.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    // exists in API
    boolean isPatronIdValid(int patronId) {

        // because index is smaller than count in one.
        return patronId >= ZERO && patronId <= patronCount - ONE;
    }

    /**
     * Returns the non-negative id number of the given patron if he is registered to this library, -1
     * otherwise.
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library, -1
     *         otherwise.
     */
    // exists in API
    int getPatronId(Patron patron) {
        for (int patronId = ZERO; patronId < patronCount; patronId++) {
            // if patron exists, its ID is its index in the patrons array
            if (registeredPatrons[patronId] == patron) {
                return patronId;
            }
        }
        return ERROR;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
     * book is available, the given patron isn't already borrowing the maximal number of books allowed, and if
     * the patron will enjoy this book.
     * @param bookId The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    // check if id is valid
    // exists in API
    boolean borrowBook(int bookId, int patronId) {
        if (bookId < ZERO || bookId > bookCount - ONE) {
            // book ID is not valid
            return false;
        } else if (!isPatronIdValid(patronId)) {
            // we don't have such a patron
            return false;
        } else if (patronBorrowedBooksCounter[patronId] >= borrowedCapacity) {
            // we are at full borrowed capacity
            return false;
        } else if (!isBookAvailable(bookId)) {
            // if book is not valid
            return false;
        }
        books[bookId].setBorrowerId(patronId);
        patronBorrowedBooksCounter[patronId] += ONE;
        return true;
    }

    /**
     * Return the given book.
     * @param bookId The id number of the book to return.
     */
    // check if id is valid
    // exists in API
    void returnBook(int bookId) {
        if (bookId < ZERO || bookId >= bookCount) {
            return;
        }
        int patronId = books[bookId].getCurrentBorrowerId();
        if (patronId > ERROR && patronId < patronCount) {
            books[bookId].returnBook();
            if (patronBorrowedBooksCounter[patronId] > ZERO) {
                patronBorrowedBooksCounter[patronId] -= ONE;
            }
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
     * will enjoy, if any such exist.
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given ID will enjoy the most. Null if no book is
     *         available.
     */
    // exists in API
    Book suggestBookToPatron(int patronId) {
        if (patronId < ZERO || patronId > patronCount - ONE) {
            return null;
        }
        Patron p = registeredPatrons[patronId];
        int currentMax = p.EnjoymentThreshold  - ONE;
        Book recommended = null;
        for (int i = ZERO; i < bookCount; i++) {
            boolean available = isBookAvailable(i);
            boolean aboveMaxValue = (p.getBookScore(books[i]) > currentMax);
            if (available && aboveMaxValue) {
                currentMax = p.getBookScore(books[i]);
                recommended = books[i];
            }
        }
        return recommended;
    }
}

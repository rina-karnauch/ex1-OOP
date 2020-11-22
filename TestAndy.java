public class TestAndy {
    static final int NUMBER_OF_TESTS = 44;
    public static void main(String[] args)
    {
        /* Test Array (What I found from my program) */
        boolean[] Test = new boolean[NUMBER_OF_TESTS];
        int testCount = 0;

        int[] TestInt = {12, -1, -1, 12, -1, -1, 14, 11, -1, 15, 12, -1, 16, 13, -1, 78, 0, 1, 2, 3, 4, 0};
        int testIntCount = 0;

        String[] TestString = {"[first,A,2000,12]", "[first,A,2000,12]", "[third,C,2002,14]",
                "[fourth,D,2003,15]", "[fifth,E,2004,16]", "Guy K"};
        int testStringCount = 0;

        boolean[] TestBool = {true, true, true, false, false, true, false, true, false, false, false, true,
                false, true};
        int testBoolCount = 0;

        /* Books array datas */
        String[] booksTitle = {"first", "first", "third", "fourth", "fifth"};
        String[] booksAuthor = {"A", "A", "C", "D", "E"};
        int[] booksYear = {2000, 2000, 2002, 2003, 2004};
        int[] booksComicValue = {1, 1, 3, 4, 5};
        int[] booksDramaticValue = {5, 5, 3, 2, 1};
        int[] booksEducationalValue = {6, 6, 8, 9, 10};
        int[] booksBorrowerId = {-1, -1, 11, 12, 13};

        /* Initializes Books */
        Book[] books = new Book[5];

        for (int i = 0; i < books.length; i++)
        {
            books[i] = new Book(booksTitle[i], booksAuthor[i], booksYear[i], booksComicValue[i],
                                booksDramaticValue[i], booksEducationalValue[i]);
            books[i].setBorrowerId(booksBorrowerId[i]);
        }

        /* Checks Book methods */
        for (Book book : books)
        {
            Test[testCount++] = (book.stringRepresentation().equals(TestString[testStringCount++]));
            Test[testCount++] = (book.getLiteraryValue() == TestInt[testIntCount++]);
            Test[testCount++] = (book.getCurrentBorrowerId() == TestInt[testIntCount++]);
            book.returnBook();
            Test[testCount++] = (book.getCurrentBorrowerId() == TestInt[testIntCount++]);
        }

        /* Initializes Patron */
        Patron patron = new Patron("Guy", "K", 8, 8, 5, 10);

        /* Checks Patron methods */
        Test[testCount++] = (patron.stringRepresentation().equals(TestString[testStringCount++]));
        Test[testCount++] = (patron.getBookScore(books[0]) == TestInt[testIntCount++]);
        Test[testCount++] = (patron.willEnjoyBook(books[0]) == TestBool[testBoolCount++]);

        /* Initializes Library */
        Library library = new Library(5, 1, 1);

        /* Checks Library methods */
        for (Book book : books)
        {
            int res = library.addBookToLibrary(book);
            Test[testCount++] = (res == TestInt[testIntCount++]);
        }

        Test[testCount++] = (library.isBookIdValid(1) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isBookIdValid(0) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isBookIdValid(-1) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isBookIdValid(5) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isBookAvailable(0) == TestBool[testBoolCount++]);

        Test[testCount++] = (library.registerPatronToLibrary(patron) == TestInt[testIntCount++]);
        Test[testCount++] = (library.suggestBookToPatron(0) == books[4]);
        Test[testCount++] = (library.suggestBookToPatron(1) == null);

        Test[testCount++] = (library.isPatronIdValid(1) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isPatronIdValid(0) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.isPatronIdValid(-1) == TestBool[testBoolCount++]);

        int bestBookId = library.getBookId(library.suggestBookToPatron(0));
        Test[testCount++] = (library.borrowBook(0, 1) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.borrowBook(5, 0) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.borrowBook(bestBookId, 0) == TestBool[testBoolCount++]);
        Test[testCount++] = (library.borrowBook(0, 0) == TestBool[testBoolCount++]);

        library.returnBook(0);
        library.returnBook(1);
        library.returnBook(bestBookId);
        Test[testCount++] = (library.borrowBook(0, 0) == TestBool[testBoolCount++]);

        for (int i = 0; i < testCount; i++)
        {
            if (!Test[i])
            {
                System.err.println("We found something different!");
                return;
            }
        }
        System.out.println("We found the same values.");
    }
}

package database;

import model.Book;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class BookDAOTest {

    static DAO<Book> dao;
    static Book bookCompare;
    static Book testBook;

    @BeforeClass
    public static void setUpDatabase() {
        dao = new BookDAO();

        testBook = new Book();
        testBook.setAuthor("Junit Test");
        testBook.setIsbn("0000");
        testBook.setTitle("Test Knjiga");

        dao.add(testBook);
    }

    @Before
    public void setUp() {
        bookCompare = new Book();
        bookCompare.setId(7);
        bookCompare.setAuthor("Junit Test");
        bookCompare.setIsbn("0000");
        bookCompare.setTitle("Test Knjiga");
    }

    @AfterClass
    public static void tearDown() {
        dao.delete(testBook);
        testBook = null;
        bookCompare = null;
        dao = null;
    }

    @After
    public void gone(){

    }

    @Test
    public void getByIdTest() {
        assertEquals(true,bookCompare.equals(dao.get("id = 7")));
    }

    @Test
    public void getByIsbnTest() {
        assertEquals(true,bookCompare.equals(dao.get("isbn = 0000")));
    }

    @Test
    public void getAllTest() {
        assertEquals(6,dao.getAll().size());
    }


}

package by.laniakea.repository;

import by.laniakea.config.TestConfiguration;
import by.laniakea.model.books.Author;
import by.laniakea.model.books.Book;
import by.laniakea.model.users.Employee;
import by.laniakea.model.users.Reader;
import by.laniakea.model.users.User;
import by.laniakea.repository.*;
import by.laniakea.util.InitDataBaseHelp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(/*SpringRunner.class*/SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class TestUserRepository {

    @Autowired
    private InitDataBaseHelp initDataBaseHelp;


    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Before
    public void init(){
        initDataBaseHelp.cleanDatabase();
        initDataBaseHelp.prepareDate();
    }

    @Test
    public void check() throws Exception{
        readerRepository.findAll().forEach(System.out::println);
        employeeRepository.findAll().forEach(System.out::println);



    }

    @Test
    public void checkFindByIdReader(){
        Optional<Reader> user =  readerRepository.findById(2L);
        System.out.println("****************" + user + "*****************");
        Assert.assertTrue(user.isPresent());

    }

    @Test
    public void checkFindByIdEmployee(){
        Optional<Employee> user =  employeeRepository.findById(1L);
        System.out.println("****************" + user + "*****************");
        Assert.assertTrue(user.isPresent());

    }

   /* @Test
    public void checkFindBookByAuthor(){
        List<Book> books = bookRepository.findByAuthor("Palanik");
        books.stream().forEach(System.out::println);
        Assert.assertTrue(!books.isEmpty());
        Assert.assertNotNull(books);
    }*/

    @Test
    public void checkFindBookByGenre(){
        /*List<Book> books = bookRepository.findByGenre("ALTER");
        books.stream().forEach(System.out::println);
        Assert.assertTrue(!books.isEmpty());
        Assert.assertNotNull(books);*/

    }

   /* @Test
    public void checkFindBookByTitle(){
        List<Book> books = bookRepository.findByTitle("Fight Club");
        books.stream().forEach(System.out::println);
        Assert.assertTrue(!books.isEmpty());
        Assert.assertNotNull(books);

    }*/

    /*@Test
    public void checkFindAuthorByLastName(){
        List<Author> authors = authorRepository.findAuthorByLastName("Palanik");
        authors.stream().forEach(System.out::println);
        Assert.assertTrue(!authors.isEmpty());
        Assert.assertNotNull(authors);
    }*/

    /*@Test
    public void checkFindReaderFromBlackList(){
        readerRepository.findAllReaderFromBlackList().forEach(System.out::println);
    }*/



}

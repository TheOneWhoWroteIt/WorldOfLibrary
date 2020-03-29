package by.laniakea.util;

import by.laniakea.model.books.Author;
import by.laniakea.model.books.Book;
import by.laniakea.model.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDataBaseHelp {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public InitDataBaseHelp(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Author").executeUpdate();
        entityManager.createQuery("delete from Book").executeUpdate();
        entityManager.createQuery("delete from Genre").executeUpdate();
        entityManager.createQuery("delete from Published").executeUpdate();
        entityManager.createQuery("delete from BlackList").executeUpdate();
        entityManager.createQuery("delete from OrderReader").executeUpdate();
        entityManager.createQuery("delete from RequestFromReader").executeUpdate();
        entityManager.createQuery("delete from Employee").executeUpdate();
        entityManager.createQuery("delete from Reader").executeUpdate();
        entityManager.createQuery("delete from Role").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareDate(){

        createReaders();
        createEmployee();
        /*createAuthorAndBook();*/


    }


    public void createReaders(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Address addressReader = new Address();
        addressReader.setCity("NY");
        addressReader.setHomeNumber(10);
        addressReader.setRoomNumber(12);
        addressReader.setStreet("freedomStreet");
        //addressReader.setZipCode(1234);

        Address addressReader2 = new Address();
        addressReader2.setCity("LA");
        addressReader2.setHomeNumber(15);
        addressReader2.setRoomNumber(2);
        addressReader2.setStreet("freedomStreet2");
        //addressReader2.setZipCode(3421);




        User reader = new Reader();
        reader.setFirstName("Tom");
        reader.setLastName("Tomov");
        reader.setBirthday(LocalDate.parse("1980-02-20", formatter));
        reader.setEmail("tom@com");
        reader.setLogin("tom");
        reader.setPassword("1");
        reader.setPhoneNumber("323-23-45");
        ((Reader) reader).setBlackList(true);
        reader.setAddress(addressReader);

        User reader2 = new Reader();
        reader2.setFirstName("Tom2");
        reader2.setLastName("Tomov2");
        reader2.setBirthday(LocalDate.parse("1980-12-15", formatter));
        reader2.setEmail("tom2@com");
        reader2.setLogin("tom2");
        reader2.setPassword("1");
        reader2.setPhoneNumber("345-32-45");
        reader2.setAddress(addressReader2);


        entityManager.persist(reader);
        entityManager.persist(reader2);



        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void createEmployee(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Address addressLibrarer = new Address();
        addressLibrarer.setCity("NY");
        addressLibrarer.setHomeNumber(10);
        addressLibrarer.setRoomNumber(12);
        addressLibrarer.setStreet("freedomStreet");
        //addressLibrarer.setZipCode(1234);

        Address addressAdmin = new Address();
        addressAdmin.setCity("LA");
        addressAdmin.setHomeNumber(10);
        addressAdmin.setRoomNumber(12);
        addressAdmin.setStreet("LAstreet");
        //addressAdmin.setZipCode(1234);

        Role role = new Role("LIBRA");
        Role role2 = new Role("ADMIN");

        entityManager.persist(role);
        entityManager.persist(role2);

        Employee library = new Employee();
        library.setFirstName("Librarer");
        library.setLastName("Librar");
        library.setBirthday(LocalDate.parse("1970-10-25", formatter));
        library.setEmail("libra@com");
        library.setLogin("libra");
        library.setPassword("1");
        library.setPhoneNumber("567-32-45");
        library.setPhotoPath("path");
        library.setPosition("librarer");
        library.setStartWork(LocalDate.parse("2019-11-11", formatter));
        library.setRole(role);
        library.setAddress(addressLibrarer);

        Employee admin = new Employee();
        admin.setFirstName("Admin");
        admin.setLastName("Adminych");
        admin.setBirthday(LocalDate.parse("1990-10-25", formatter));
        admin.setEmail("admin@com");
        admin.setLogin("admin");
        admin.setPassword("1");
        admin.setPhoneNumber("476-32-45");
        admin.setPhotoPath("path");
        admin.setPosition("administrator");
        admin.setStartWork(LocalDate.parse("2019-11-11", formatter));
        admin.setRole(role2);
        admin.setAddress(addressAdmin);

        entityManager.persist(library);
        entityManager.persist(admin);


        entityManager.getTransaction().commit();
        entityManager.close();

    }

    // TODO: 22.01.2020 переписать тесты для создания книг

   /* public void createAuthorAndBook(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        Author palanik = new Author("Chack", "Palanik", "description");
        *//*Genre alternative = new Genre("ALTER");
        Published published = new Published("AST");*//*

        entityManager.persist(palanik);
       *//* entityManager.persist(alternative);
        entityManager.persist(published);*//*

        List<Author> authors = new ArrayList<>();
        authors.add(palanik);

        Book dnevnik = new Book("123-234-567", "description", "path", "320", authors,
                alternative,"Dnevnik", published, 2008, 1);

        Book fightClub = new Book("654-234-567", "description", "path", "400", authors,
                alternative,"Fight Club", published, 2000, 1);

        entityManager.persist(dnevnik);
        entityManager.persist(fightClub);






        entityManager.getTransaction().commit();
        entityManager.close();
    }*/
}

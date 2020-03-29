package by.laniakea.repository;

import by.laniakea.model.books.Book;
import by.laniakea.model.list.RequestFromReader;
import by.laniakea.model.users.Reader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RequestFromReaderServiceRepository {

    private final RequestFromReaderRepository requestFromReaderRepository;


    public void save(RequestFromReader object) {
        requestFromReaderRepository.save(object);

    }

    public List<RequestFromReader> findAll(){
        List<RequestFromReader> requestFromReaders = new ArrayList<>();
        Iterable<RequestFromReader> iterable = requestFromReaderRepository.findAll();
        iterable.forEach(requestFromReaders::add);
        return requestFromReaders;
    }

    public Page<RequestFromReader> findAll(Pageable pageable){
        return requestFromReaderRepository.findAll(pageable);
    }

    public Page<RequestFromReader> findAllRequest(Pageable pageable){
        /*LocalDate currentDate = LocalDate.now();*/
        return requestFromReaderRepository.findAllRequest(pageable);
    }

    public RequestFromReader findByID(Long id){

        return requestFromReaderRepository.findById(id).get();
    }

    public void delete(RequestFromReader requestFromReader){
        requestFromReaderRepository.delete(requestFromReader);
    }

    public RequestFromReader findByReader(Reader reader){
        return requestFromReaderRepository.findByReader(reader);
    }

    public Set<Book> bookListForOpenOrder(Set<Book> books, String[] bookId){
        Set<Book> booksList = new HashSet<>();

        for (Book book : books) {
            for (int i = 0; i < bookId.length; i++) {
                if (book.getId().toString().equals(bookId[i])){
                    book.setPrintEditionCount(book.getPrintEditionCount()-1);
                    booksList.add(book);
                }
            }
        }

        return booksList;
    }

    public Set<Book> bookListForCloseOrder(Set<Book> books, String[] bookId){
        Set<Book> booksList = new HashSet<>();

        for (Book book : books) {
            for (int i = 0; i < bookId.length; i++) {
                if (book.getId().toString().equals(bookId[i])){
                    book.setPrintEditionCount(book.getPrintEditionCount()+1);
                    booksList.add(book);
                }
            }
        }

        return booksList;
    }

    public RequestFromReader findByReaderId(Long id){
        return requestFromReaderRepository.findByReaderId(id);
    }

    public String findRequestByReaderId(Long id){
        return requestFromReaderRepository.findRequestByReaderId(id);
    }


}

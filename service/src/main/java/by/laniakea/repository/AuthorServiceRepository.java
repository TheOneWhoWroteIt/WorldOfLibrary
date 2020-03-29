package by.laniakea.repository;


import by.laniakea.dto.AuthorDTO;
import by.laniakea.model.books.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class AuthorServiceRepository  {

    private final AuthorRepository authorRepository;


    public void save(AuthorDTO object) {

        Author author = new Author(object.getFirstName(), object.getLastName(),
                                    object.getAuthorDescription());

        authorRepository.save(author);

    }

    public List<Author> findAll(){
        Iterable<Author> authorIterable = authorRepository.findAll();
        List<Author> authors = new ArrayList<>();
        authorIterable.forEach(authors::add);

        return authors;
    }

    public Page<Author> findAll(Pageable pageable){
        return authorRepository.findAll(pageable);
    }

    public Author findByID(Long id){
        Author author = authorRepository.findById(id).get();
        return author;
    }

    public void update(AuthorDTO authorDTO, Author author){

        if(authorDTO.getFirstName()!= null && !authorDTO.getFirstName().isEmpty()){
            author.setFirstName(authorDTO.getFirstName());
        }
        if(authorDTO.getLastName()!= null && !authorDTO.getLastName().isEmpty()){
            author.setLastName(authorDTO.getLastName());
        }
        if(authorDTO.getAuthorDescription()!= null && !authorDTO.getAuthorDescription().isEmpty()){
            author.setAuthorDescription(authorDTO.getAuthorDescription());
        }

        authorRepository.save(author);

    }

    public Page<Author> findByLastName(String lastName, Pageable pageable){
        return authorRepository.findAuthorByLastName(lastName, pageable);
    }


}

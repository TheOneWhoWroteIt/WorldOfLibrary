package by.laniakea.repository;


import by.laniakea.dto.ReaderDTO;
import by.laniakea.model.books.Book;
import by.laniakea.model.list.RequestFromReader;
import by.laniakea.model.users.Address;
import by.laniakea.model.users.Reader;
import by.laniakea.model.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ReaderServiceRepository {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ReaderRepository readerRepository;
    private final RequestFromReaderRepository requestFromReaderRepository;


    public void save(ReaderDTO readerDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(readerDTO.getBirthday(), formatter);

        Address address = new Address(readerDTO.getCity(), readerDTO.getStreet(),
                                        Integer.valueOf(readerDTO.getHomeNumber()),
                                        Integer.valueOf(readerDTO.getRoomNumber()));

        String password = passwordEncoder.encode(readerDTO.getPassword());

        Reader reader = new Reader(readerDTO.getFirstName(), readerDTO.getLastName(), birthday,
                readerDTO.getEmail(), readerDTO.getPhoneNumber(), readerDTO.getLogin(), password,
                address);

        Role role = roleRepository.findByRoleName("READER");


        reader.setRole(role);

        if(readerDTO.getPassword().equals(readerDTO.getRepitPassword()) && readerDTO.isAgree()){
            readerRepository.save(reader);
        }

    }

    public void update(ReaderDTO readerDTO, Reader reader){

        if(readerDTO.getFirstName() != null && !readerDTO.getFirstName().isEmpty()){
            reader.setFirstName(readerDTO.getFirstName());
        }
        if(readerDTO.getLastName() != null && !readerDTO.getLastName().isEmpty()){
            reader.setLastName(readerDTO.getLastName());
        }
        if(readerDTO.getBirthday() != null && !readerDTO.getBirthday().isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthday = LocalDate.parse(readerDTO.getBirthday(), formatter);
            reader.setBirthday(birthday);
        }
        if(readerDTO.getEmail() != null && !readerDTO.getEmail().isEmpty()){
            reader.setEmail(readerDTO.getEmail());
        }
        if(readerDTO.getPhoneNumber() != null && !readerDTO.getPhoneNumber().isEmpty()){
            reader.setPhoneNumber(readerDTO.getPhoneNumber());
        }
        if(readerDTO.getCity() != null && !readerDTO.getCity().isEmpty()){
            reader.getAddress().setCity(readerDTO.getCity());
        }
        if(readerDTO.getStreet() != null && !readerDTO.getStreet().isEmpty()){
            reader.getAddress().setStreet(readerDTO.getStreet());
        }
        if(readerDTO.getHomeNumber() != null && !readerDTO.getHomeNumber().isEmpty()){
            reader.getAddress().setHomeNumber(Integer.parseInt(readerDTO.getHomeNumber()));
        }
        if(readerDTO.getRoomNumber() != null && !readerDTO.getRoomNumber().isEmpty()){
            reader.getAddress().setRoomNumber(Integer.parseInt(readerDTO.getRoomNumber()));
        }
        if(readerDTO.getLogin() != null && !readerDTO.getLogin().isEmpty()){
            reader.setLogin(readerDTO.getLogin());
        }
        if(readerDTO.getPassword() != null && readerDTO.getRepitPassword() != null
                && !readerDTO.getLogin().isEmpty() && !readerDTO.getRepitPassword().isEmpty()
                && readerDTO.getPassword().equals(readerDTO.getRepitPassword())){
            String password = passwordEncoder.encode(readerDTO.getPassword());
            reader.setPassword(password);
        }

        readerRepository.save(reader);

    }


    public List<Reader> findAll() {
        Iterable<Reader> readerIterable = readerRepository.findAll();
        List<Reader> readers = new ArrayList<>();
        readerIterable.forEach(readers::add);

        return readers;
    }

    public Page<Reader> findAll(Pageable pageable){
        return readerRepository.findAll(pageable);
    }

    public Reader findByID(Long id){
        Reader reader = readerRepository.findById(id).get();
        return reader;
    }

    public Reader findByLogin(String login){
        return readerRepository.findByLogin(login);
    }

    public void createRequestBooks(Set<Book> books){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Reader reader = readerRepository.findByLogin(userName);

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.now();

        LocalDate valid = start.plusDays(2);

        RequestFromReader requestFromReader = new RequestFromReader(start, valid, books, reader);

        requestFromReaderRepository.save(requestFromReader);


    }

    public void saveReader(Reader reader){
        readerRepository.save(reader);
    }

    public Page<Reader> findAllReaderFromBlackList(Pageable pageable){
        return readerRepository.findAllReaderFromBlackList(pageable);
    }

    public String loadInfoByReader(String name){
        return readerRepository.loadInfoByReader(name);
    }


    public String findReaderSecurityByLogin(String login) {
        return readerRepository.findReaderSecurityByLogin(login);
    }
}

package by.laniakea.controllers;

import by.laniakea.dto.ReaderDTO;

import by.laniakea.repository.ReaderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RegistrationController {

    private final ReaderServiceRepository readerServiceRepository;



   /* @Autowired
    public void setReaderServiceRepository(ReaderServiceRepository readerServiceRepository) {
        this.readerServiceRepository = readerServiceRepository;
    }*/

    @GetMapping("/registration")
    public String registrationShow(Model model){

        model.addAttribute("readerDTO", new ReaderDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveReader(@Valid @ModelAttribute(name = "readerDTO") ReaderDTO readerDTO,
                            BindingResult result){

        if (result.hasErrors()) {
            return "registration";
        }

        readerServiceRepository.save(readerDTO);

        return "redirect:/login";
    }
}

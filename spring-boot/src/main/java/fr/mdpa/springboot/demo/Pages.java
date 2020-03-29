package fr.mdpa.springboot.demo;

import fr.mdpa.springboot.demo.dto.FormulaireDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Pages {
    private final static Logger LOGGER = LoggerFactory.getLogger(Pages.class);

    @GetMapping(value = {"/", "/index"})
    public ModelAndView welcome() {

        return new ModelAndView("welcome.html");
    }

    @GetMapping(value = {"/formulaire"})
    public ModelAndView formulaire() {

        return new ModelAndView("formulaire.html");
    }

    @PostMapping("/formulaire")
    public ModelAndView validerFormulaire( FormulaireDto formulaireDto) {
        LOGGER.info("résultat " + formulaireDto.getMonTexte());

        final ModelAndView modelAndView = new ModelAndView("formulaire.html");

        modelAndView.addObject(formulaireDto);

        return modelAndView;
    }

}

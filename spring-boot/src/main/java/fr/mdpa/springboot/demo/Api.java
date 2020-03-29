package fr.mdpa.springboot.demo;

import fr.mdpa.springboot.demo.dto.ResultDto;
import fr.mdpa.springboot.demo.dto.SigninDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    private final static Logger LOGGER = LoggerFactory.getLogger(Api.class);

    @PutMapping("/api/email")

    public ResultDto signin(@RequestBody SigninDto signinDto) {
        LOGGER.info("L'utilisateur a envoyé : " + signinDto.getEmail());
        return new ResultDto(true);
    }

}

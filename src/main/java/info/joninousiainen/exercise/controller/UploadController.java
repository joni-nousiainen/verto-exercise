package info.joninousiainen.exercise.controller;

import info.joninousiainen.exercise.model.StringSet;
import info.joninousiainen.exercise.repo.StringSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private StringSetRepository stringSetRepository;

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity<Long> upload(@Valid @RequestBody String body) {
        log.info("Received upload: {}", body);

        String[] strings = body.split("\\s");
        StringSet stringSet = new StringSet(strings);
        StringSet savedStringSet = stringSetRepository.save(stringSet);

        ResponseEntity<Long> response = new ResponseEntity<Long>(savedStringSet.getId(), HttpStatus.CREATED);

        return response;
    }
}

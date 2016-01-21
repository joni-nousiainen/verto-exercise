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
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
public class UploadController {
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private StringSetRepository stringSetRepository;

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> upload(@Valid @RequestBody String body) {
        log.info("Upload: {}", body);

        String[] strings = body.split("\\s");
        Set<String> set = new LinkedHashSet<>(Arrays.asList(strings));

        boolean containsDuplicates = strings.length != set.size();
        ResponseEntity<String> response;

        if (containsDuplicates) {
            response = new ResponseEntity<String>("set must not contain duplicate strings", HttpStatus.BAD_REQUEST);
        }
        else {
            StringSet stringSet = new StringSet(set);
            StringSet savedStringSet = stringSetRepository.save(stringSet);
            response = new ResponseEntity<>(savedStringSet.getId().toString(), HttpStatus.CREATED);
        }

        return response;
    }
}

package info.joninousiainen.exercise.controller;

import info.joninousiainen.exercise.model.StringSet;
import info.joninousiainen.exercise.repo.StringSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {
    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private StringSetRepository stringSetRepository;

    @RequestMapping(
        value = "/search/{string}",
        method = RequestMethod.GET
    )
    public ResponseEntity<String> search(@PathVariable String string) {
        log.info("Searching for sets containing string: {}", string);

        Iterable<StringSet> allStringSets = stringSetRepository.findAll();
        Set<Long> ids = StreamSupport.stream(allStringSets.spliterator(), false)
                .filter(stringSet -> stringSet.getStrings().contains(string))
                .map(StringSet::getId)
                .collect(Collectors.toSet());

        return new ResponseEntity<String>(StringUtils.collectionToDelimitedString(ids, " "), HttpStatus.OK);
    }
}

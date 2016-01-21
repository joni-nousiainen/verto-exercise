package info.joninousiainen.exercise.controller;

import info.joninousiainen.exercise.model.StringSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchControllerTest extends ControllerTest {
    @Before
    public void addSampleData() {
        stringSetRepository.save(new StringSet("foo", "oomph", "hgf"));
        stringSetRepository.save(new StringSet("hij", "jkl", "jkm", "lmn"));
        stringSetRepository.save(new StringSet("abc", "cde", "cdf", "fuf", "fgh"));
    }

    @Test
    public void SimpleSearch() {
        ResponseEntity<String> result = search("abc");
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), equalTo("3"));
    }

    private ResponseEntity<String> search(String query) {
        return restTemplate.getForEntity("http://localhost:" + port + "/search/" + query, String.class);
    }
}

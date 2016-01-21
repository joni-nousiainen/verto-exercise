package info.joninousiainen.exercise.controller;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UploadControllerTest extends ControllerTest {
    @Test
    public void FourSimpleStrings() {
        ResponseEntity<String> response = upload("one two\nthree\tfour");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Long id = Long.parseLong(response.getBody());

        Set<String> strings = stringSetRepository.findOne(id).getStrings();
        assertEquals(4, strings.size());
        assertThat(strings, hasItems("one", "two", "three", "four"));
    }

    private ResponseEntity<String> upload(String request) {
        return restTemplate.postForEntity("http://localhost:" + port + "/upload", request, String.class);
    }

    @Test
    public void CheckForDuplicateStrings() {
        ResponseEntity<String> response = upload("one two two three");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("set must not contain duplicate strings", response.getBody());
    }

    // TODO: No strings, only whitespace, min & max string length.
}

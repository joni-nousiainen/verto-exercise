package info.joninousiainen.exercise.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class StringSet implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> strings;

    public StringSet() {
    }

    public StringSet(String[] strings) {
        getStrings().addAll(Arrays.asList(strings));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getStrings() {
        if (strings == null) {
            strings = new LinkedHashSet<>();
        }
        return strings;
    }

    public void setStrings(Set<String> strings) {
        this.strings = strings;
    }
}

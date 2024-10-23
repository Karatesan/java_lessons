import java.util.Optional;

public class Person2 {
    private final String name;
    private final Optional<String> country;

    public Person2(String name, Optional<String> country) {
        this.name = name;
        this.country = country;
    }

    public Optional<String> getCountry() {
        return country;
    }
}

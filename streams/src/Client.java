import java.util.Optional;

class Client {
    private final String name;
    private final Optional<String> email;

    public Client(String name, Optional<String> email) {
        this.name = name;
        this.email = email;
    }

    public Optional<String> getEmail() {
        return email;
    }
}
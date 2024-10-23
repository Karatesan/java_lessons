import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceExample {
    public void loadResource() {
        InputStream inputStream = getClass().getResourceAsStream("/config.properties");
        if (inputStream != null) {
            // Process the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Resource not found");
        }
    }
}

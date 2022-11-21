import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.*;

public class AppTest {
    JSONArray userArray;
    void setup() {
        JSONParser jsonParser = new JSONParser();
        try {
            userArray = (JSONArray) jsonParser.parse(new FileReader("admin_users.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testUser(){
        //here we are checking if the user is empty
        setup();
        assert userArray.isEmpty(): "User doesn't have any user data recorded yet.";
    }

    void testId() {
        setup();
        for (Object o : userArray) {
            JSONObject user = (JSONObject) o;
            assert user.containsKey("id"): "User doesn't contains Id";
        }
    }
    @Test
    void testName() {
        setup();
        for (Object o : userArray) {
            JSONObject user = (JSONObject) o;
            assert user.containsKey("name"): "User doesn't contains Name";
        }
    }
    @Test
    void testAddress() {
        setup();
        for (Object o : userArray) {
            JSONObject user = (JSONObject) o;
            assert user.containsKey("address"): "User doesn't contains Address";
        }
    }
}


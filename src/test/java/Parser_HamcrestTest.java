import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Parser_HamcrestTest {
    private final String inputTestFileName = "output_data.json";

    @Test
    public void testMain_readString() {
        JSONParser parser = new JSONParser();
        List<JSONObject> jsonObjectsList = new ArrayList<>();
        try {
            Object obj = parser.parse("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}");
            jsonObjectsList.add((JSONObject) obj);
            obj = parser.parse("{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}");
            jsonObjectsList.add((JSONObject) obj);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        assertThat(Main.readString(inputTestFileName), equalTo(jsonObjectsList));
    }

    @Test
    public void testMain_jsonsToList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "John", "Smith", "USA", 25));
        employeeList.add(new Employee(2, "Inav", "Petrov", "RU", 23));
        List<JSONObject> jsonObjects = Main.readString(inputTestFileName);

        assertThat(Main.jsonsToList(jsonObjects), equalTo(employeeList));
    }
}

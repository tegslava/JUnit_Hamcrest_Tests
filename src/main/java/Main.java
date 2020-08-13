import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import parsers.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<JSONObject> jsons = readString("output_data.json");
        List<Employee> list = jsonsToList(jsons);
        if (list != null) {
            list.forEach(System.out::println);
        } else System.out.println("Не удалось создать объекты");
    }

    public static List<Employee> jsonsToList(List<JSONObject> jsons) {
        if ((jsons == null) || (jsons.size() == 0)) return null;
        try {
            List<Employee> emplList = new ArrayList<>();
            jsons.forEach(jsonObject -> {
                Employee employee = new Employee();
                fromJson(jsonObject, employee);
                emplList.add(employee);
            });
            return emplList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void fromJson(JSONObject jsonObject, Employee employee) {
        employee.id = Long.parseLong(jsonObject.get("id").toString());
        employee.firstName = (String) jsonObject.get("firstName");
        employee.lastName = (String) jsonObject.get("lastName");
        employee.age = Integer.parseInt(jsonObject.get("age").toString());
        employee.country = (String) jsonObject.get("country");
    }

    public static List<JSONObject> readString(String inputFileName) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new BufferedReader(new FileReader(inputFileName)));
            JSONArray jsonObjects = (JSONArray) obj;
            return (List<JSONObject>) jsonObjects.stream().collect(Collectors.toList());
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package domain;

import json.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected String name;
    protected String surname;
    protected Integer year;
    protected Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        LinkedList<JsonPair> jsonPairs = new LinkedList<>();
        JsonPair studentName = new JsonPair("name", new JsonString(name));
        JsonPair studentSurname = new JsonPair("surname", new JsonString(surname));
        JsonPair studentYear = new JsonPair("year", new JsonNumber(year));
        jsonPairs.add(studentName);
        jsonPairs.add(studentSurname);
        jsonPairs.add(studentYear);

        ArrayList<JsonObject> jsonObjectsForJsonArray = new ArrayList<>();
        for(Tuple<String, Integer> element: exams) {
            boolean ifPassed = true;
            if(element.value <= 2) {
                ifPassed = false;
            }
            jsonObjectsForJsonArray.add(new JsonObject(new JsonPair("course", new JsonString(element.key)), new JsonPair("mark", new JsonNumber(element.value)),
                    new JsonPair("passed", new JsonBoolean(ifPassed))));
        }
        JsonObject[] arr = new JsonObject[jsonObjectsForJsonArray.toArray().length];
        for(int i = 0; i < jsonObjectsForJsonArray.size(); i++) {
            arr[i] = jsonObjectsForJsonArray.get(i);
        }
        jsonPairs.add(new JsonPair("exams", new JsonArray(arr)));
        JsonPair[] result = new JsonPair[jsonPairs.size()];

        for(int j = 0; j < jsonPairs.size(); j++) {
            result[j] = jsonPairs.get(j);
        }
        return new JsonObject(result);
    }
}
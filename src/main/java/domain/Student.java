package domain;

import json.*;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private LinkedHashMap<String, Integer> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new LinkedHashMap<String, Integer>();
        for (Tuple<String, Integer> exam: exams) {
            this.exams.put(exam.key, exam.value);
        }

    }

    public JsonObject toJsonObject() {
        JsonObject json = super.toJsonObject();
        ArrayList<JsonObject> examsList = new ArrayList<JsonObject>();
        for (String exam: exams.keySet()) {
            JsonObject course = new JsonObject();
            course.add(new JsonPair("course", new JsonString(exam)));
            course.add(new JsonPair("mark", new JsonNumber(exams.get(exam))));
            course.add(new JsonPair("passed", new JsonBoolean(exams.get(exam) >= 3)));
            examsList.add(course);
        }
        JsonObject[] examsArr = new JsonObject[examsList.size()];
        int counter = -1;
        for (JsonObject o: examsList) {
            counter++;
            examsArr[counter] = o;
        }
        json.add(new JsonPair("exams", new JsonArray(examsArr)));
        return json;
    }
}
//course': 'Math',
//'mark': 2,
//'passed': false

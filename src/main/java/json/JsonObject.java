package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new ArrayList<JsonPair>();
        for (JsonPair pair : jsonPairs) {
            add(pair);
        }

    }

    public boolean contains(String s) {
        for (JsonPair pair: pairs) {
            if (pair.key.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toJson() {
        if (pairs.size() == 0) {
            return "{}";
        }
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (JsonPair pair : pairs) {
            s.append(pair.toString());
            s.append(", ");
        }
        String res = s.toString();
        res = res.substring(0, res.length() - 2);
        res += "}";
        return res;
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair pair : pairs) {
            if (pair.key.equals(name)) {
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject newObj = new JsonObject();
        for (String name : names) {
            Json copy = find(name);
            if (copy == null) {
                continue;
            }
            newObj.add(new JsonPair(name, copy));
        }
        return newObj;
    }
}

package json;

import java.util.LinkedHashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private LinkedHashMap<String, Json> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new LinkedHashMap<>();
        for (JsonPair pair : jsonPairs) {
            add(pair);
        }

    }

    public boolean contains(String s) {
        return pairs.containsKey(s);
    }

    @Override
    public String toJson() {
        if (pairs.size() == 0) {
            return "{}";
        }
        StringBuilder s = new StringBuilder();
        s.append("{");

        for (String key : pairs.keySet()) {
            s.append(new JsonPair(key, pairs.get(key)).toString());
            s.append(", ");
        }
        String res = s.toString();
        res = res.substring(0, res.length() - 2);
        res += "}";
        return res;
    }

    public void add(JsonPair jsonPair) {
        pairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        for (String key : pairs.keySet()) {
            if (key.equals(name)) {
                return pairs.get(key);
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

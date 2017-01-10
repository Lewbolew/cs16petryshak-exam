package json;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Set<JsonPair> jsonPairsSet = new HashSet<>();

    public JsonObject(JsonPair... jsonPairs) {
        for(JsonPair element: jsonPairs) {
            jsonPairsSet.add(element);
        }
    }

    @Override
    public String toJson() {
        if(jsonPairsSet.size() == 0) {
            return "{}";
        }
        StringBuilder result = new StringBuilder("{");

        for(JsonPair element: jsonPairsSet) {
            result.append("'");
            result.append(element.key);
            result.append("'");
            result.append(": ");
            result.append(element.value.toJson());
            result.append(",");
        }
        result.append("}");
        result.delete(result.length() - 2,result.length() - 1);
        return result.toString();
    }

    public void add(JsonPair jsonPair) {
        for(JsonPair jsonPair1: jsonPairsSet) {
            if (jsonPair1.key.equals(jsonPair.key)) {
                jsonPairsSet.remove(jsonPair1);
                break;
            }
        }
        jsonPairsSet.add(jsonPair);
    }

    public Json find(String name) {
        for(JsonPair jsonPair: jsonPairsSet) {
            if(jsonPair.key.equals(name)) {
                return jsonPair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        LinkedList<JsonPair> jsonPairs = new LinkedList<>();
        for(int i = 0; i < names.length; i++) {
            for(JsonPair jsonPair: jsonPairsSet) {
                if(jsonPair.key == names[i]) {
                    jsonPairs.add(jsonPair);
                    break;
                }
            }
        }
        JsonPair[] arr = new JsonPair[jsonPairs.size()];
        for(int j = 0; j < jsonPairs.size(); j++) {
            arr[j] = jsonPairs.get(j);
        }
        return new JsonObject(arr);
    }

    public boolean contains(String name) {
        for(JsonPair jsonPair: jsonPairsSet) {
            if(jsonPair.key.equals(name)) {
                return true;
            }
        }
        return false;
    }
}

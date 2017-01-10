package json;

import java.util.HashSet;
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
        // ToDo
        return null;
    }
}

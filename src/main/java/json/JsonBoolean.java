package json;


public class JsonBoolean extends Json {
    private final Boolean boolValue;
    public JsonBoolean(Boolean bool) {
        boolValue = bool;
    }

    @Override
    public String toJson() {
        return boolValue.toString();
    }
}

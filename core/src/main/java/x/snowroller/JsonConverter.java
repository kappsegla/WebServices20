package x.snowroller;

import com.google.gson.Gson;

public class JsonConverter {

    private Gson gson;

    public JsonConverter() {
        gson = new Gson();
    }

    public String convertToJson(Object object){
        return gson.toJson(object);

    }
}

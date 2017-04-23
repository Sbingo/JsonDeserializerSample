package sbingo.jsondeserializersample;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


/**
 * Author: Sbingo
 * Date:   2017/4/23
 */

public class SizeDeserializer implements JsonDeserializer<Size> {
    @Override
    public Size deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Size size = new Size();
        if (json.isJsonObject()) {
            //类型正确
            JsonObject jsonObject = json.getAsJsonObject();
            size.setLength(jsonObject.get("length").getAsString());
            size.setWidth(jsonObject.get("width").getAsString());
            size.setHeight(jsonObject.get("height") == null ? "" : jsonObject.get("height").getAsString());
        } else {
            //类型错误
            String value = json.getAsString();
            size = new Gson().fromJson(value, Size.class);
        }
        return size;
    }
}

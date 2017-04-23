package sbingo.jsondeserializersample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

public class AppDeserializer implements JsonDeserializer<App[]> {

    @Override
    public App[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray()) {
            //类型正确
            JsonArray jsonArray = json.getAsJsonArray();
            App[] apps = new App[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                App app = new App();
                //获取app方法一
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                app.setAppName(jsonObject.get("appName") == null ? "" : jsonObject.get("appName").getAsString());
                app.setDeveloper(jsonObject.get("developer") == null ? "" : jsonObject.get("developer").getAsString());
                //获取app方法二
//                app = context.deserialize(jsonObject, App.class);
                apps[i] = app;
            }
            return apps;
        } else if (json.isJsonObject()) {
            //类型错误1
            return null;
        } else if (json.isJsonPrimitive()) {
            //类型错误2，多为String
            String value = "";
            try {
                value = json.getAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("".equals(value)) {
                return null;
            } else {
                App[] apps = new Gson().fromJson(value, App[].class);
                return apps;
            }
        } else {
            //一般不出现这种情况
            return null;
        }
    }
}

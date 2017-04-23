package sbingo.jsondeserializersample;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Sbingo
 * Date:   2017/4/23
 */

public class AppListDeserializer implements JsonDeserializer<Phone> {

    @Override
    public Phone deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Phone phone = new Phone();
        JsonObject jsonObject = json.getAsJsonObject();
        //此时不考虑size的类型错误情况
        if (jsonObject.get("size") != null) {
            jsonObject = jsonObject.get("size").getAsJsonObject();
            Size size = context.deserialize(jsonObject, Size.class);
            phone.setSize(size);
        }
        JsonElement appsArray = jsonObject.get("apps");
        if (appsArray != null) {
            List<App> apps = new ArrayList<>();
            if (appsArray.isJsonArray()) {
                //类型正确
                JsonArray jsonArray = appsArray.getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jo = jsonArray.get(i).getAsJsonObject();
                    App app = context.deserialize(jo, App.class);
                    apps.add(app);
                }
            } else {
                //类型错误
                String value = appsArray.getAsString();
                App[] a = new Gson().fromJson(value, App[].class);
                apps = Arrays.asList(a);
            }
            phone.setApps(apps);
        }
        return phone;
    }
}

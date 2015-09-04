package me.nithanim.gw2api.v2.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import me.nithanim.gw2api.v2.api.characters.SpecializationType;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
            return null;
        }

        final Map<String, T> map = new HashMap<>();
        for (T constant : rawType.getEnumConstants()) {
            if (rawType == SpecializationType.class) {
                //For the only enum that is returned in lowercase by the api
                //we need a better way to do this
                map.put(constant.toString().toLowerCase(), constant);
            } else {
                map.put(toCamelCase(constant), constant);
            }
        }

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public T read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else {
                    return map.get(in.nextString());
                }
            }
        };
    }

    private static <T> String toCamelCase(T e) {
        StringBuilder sb = new StringBuilder(e.toString().toLowerCase());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));

        int underscore;
        while ((underscore = sb.indexOf("_")) != -1) {
            sb.setCharAt(underscore + 1, Character.toUpperCase(sb.charAt(underscore + 1)));
            sb.deleteCharAt(underscore);
        }
        return sb.toString();
    }
}

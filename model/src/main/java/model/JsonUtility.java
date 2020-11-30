package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class JsonUtility {
    public static <T> List<T> parseJSONList(String jsonString, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(jsonString, clazz);
        return Arrays.asList(arr);
    }

}

package zsbpj.lccpj.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class GsonUtils {

	public GsonUtils() {

	}

	public static String createGsonString(Object object) {
		Gson gson = new Gson();
		String gsonString = gson.toJson(object);
		return gsonString;
	}

	public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		T t = gson.fromJson(gsonString, cls);
		return t;
	}

	public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		List<T> list_person = gson.fromJson(gsonString,
				new TypeToken<List<T>>() {
				}.getType());
		return list_person;
	}

	public static List<Map<String, Object>> changeGsonToListMaps(String gsonString) {
		List<Map<String, Object>> list = null;
		Gson gson = new Gson();
		list = gson.fromJson(gsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		return list;
	}

}

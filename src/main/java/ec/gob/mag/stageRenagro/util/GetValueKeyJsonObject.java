package ec.gob.mag.stageRenagro.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

/**
 * CLASE PARA RECORRER DE 3 MANERAS DIFERENTES UN OBJECTO JSON
 * 
 * @author PITPPA
 */
@Setter
@Getter
@Service("getValueKeyJsonObject")
public class GetValueKeyJsonObject {

	public Object finalresult = null;

	public Object searchingJson(JSONObject x, String y) throws JSONException {
		JSONArray keys = x.names();
		for (int i = 0; i < keys.length(); i++) {
			if (finalresult != null) {
				return finalresult; // To kill the recursion
			}

			String current_key = keys.get(i).toString();

			if (current_key.equals(y)) {
				finalresult = x.get(current_key);
				return finalresult;
			}

			if (x.get(current_key).getClass().getName().equals("org.json.JSONObject")) {
				searchingJson((JSONObject) x.get(current_key), y);
			} else if (x.get(current_key).getClass().getName().equals("org.json.JSONArray")) {
				for (int j = 0; j < ((JSONArray) x.get(current_key)).length(); j++) {
					if (((JSONArray) x.get(current_key)).get(j).getClass().getName().equals("org.json.JSONObject")) {
						searchingJson((JSONObject) ((JSONArray) x.get(current_key)).get(j), y);
					}
				}
			}
		}
		return null;
	}

	// RECORRER JSONOBJECT
	public Object checkKey(JSONObject JsonObj, String searchedKey) {
		boolean exists = JsonObj.has(searchedKey);
		Object obj = null;
		if (JsonObj.isNull(searchedKey)) {
			return searchedKey = "";
		} else {
			if (exists) {
				obj = JsonObj.get(searchedKey);
			}
			if (!exists) {
				Set<String> keys = JsonObj.keySet();
				for (String key : keys) {
					if (JsonObj.get(key) instanceof JSONObject) {
						obj = checkKey((JSONObject) JsonObj.get(key), searchedKey);
					}
				}
			}
			return obj;
		}

	}

	// RECORRER JSONOBJECT
	public Object recurseKeys(JSONObject jObj, String findKey) throws JSONException {
		Object finalValue = "";
		if (jObj == null) {
			return "";
		}
		Iterator<String> keyItr = jObj.keys();
		Map<String, String> map = new HashMap<>();
		while (keyItr.hasNext()) {
			String key = keyItr.next();
			map.put(key, jObj.getString(key));
		}
		for (Map.Entry<String, String> e : (map).entrySet()) {
			String key = e.getKey();
			if (key.equalsIgnoreCase(findKey)) {
				return jObj.getString(key);
			}
			Object value = jObj.get(key);
			if (value instanceof JSONObject) {
				finalValue = recurseKeys((JSONObject) value, findKey);
			}
		}
		return finalValue;
	}

	// CONVERTIR A STRING JSON OBJECT
	public String getPostDataString(JSONObject params) throws Exception {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		Iterator<String> itr = params.keys();
		while (itr.hasNext()) {
			String key = itr.next();
			Object value = params.get(key);
			if (first)
				first = false;
			else
				result.append("&");
			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value.toString(), "UTF-8"));
		}
		return result.toString();
	}
}

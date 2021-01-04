package com.hagz_hotels.hotels_booking.Util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonResponse {
    private HashMap<String, String> mp;
    public JsonResponse() {
        mp = new HashMap<>();
    }
    public void setAttr(String key, String value) {
        mp.put(key, value);
    }
    public String toString() {
        StringBuilder ret = new StringBuilder("{\n");
        Iterator it = mp.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ret.append("\"" + pair.getKey() + "\"");
            ret.append(": ");
            ret.append("\"" + pair.getValue() + "\"");
            if (it.hasNext())
                ret.append(",\n");
        }
        ret.append("\n}");
        return ret.toString();
    }
}

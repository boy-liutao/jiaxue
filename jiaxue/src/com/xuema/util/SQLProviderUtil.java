package com.xuema.util;

public class SQLProviderUtil {
    public static String StringToSQLList(String[] arrays) {
        String queryList = null;
        if (arrays != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrays.length; i++) {
                sb.append(",\'" + arrays[i] + "\'");
            }
            if (sb.length() != 0)
                queryList = sb.substring(1);
        }
        return queryList;
    }
}

/*
 -----------------------------------------
   StringUtils
   Copyright (c) 2015
   Digital Provisioners
   All Right Reserved
 -----------------------------------------
 */

package com.omnia.docclassifier.utils;

import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StringUtils {

    public static final String EMPTYSTR				= "";
    public static final UUID EMPTY_UUID				= UUID.nameUUIDFromBytes(new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
    public static final int B512                    = 512;

    /**
     * getActiveProfilesStr retiurns a CSV list of all
     * active Spring profiles
     * @param env - Environment
     * @return String
     */
    public static String getActiveProfilesStr(Environment env) {
        // extract all active profiles
        String[] profiles = env.getActiveProfiles();
        if (profiles.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String profile : profiles) {
                sb.append(profile);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        else {
            return "No active profiles";
        }
    }

    /**
     * snags the first line of a block of text
     * @param body - String
     * @return String - first line, if it exists else empty string
     */
    public static String getFirstLine(String body) {
        String line1 = "";
        if (!StringUtils.isNullOrEmpty(body)) {
            String lines[] = body.split("[\\r\\n]+");
            if (lines.length > 0) {
                line1 = lines[0];
            }
        }
        return line1;
    }

    // helper methods to read a resource
    private static String streamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * helper method to read a text resource
     * @param parent - Object, the parent object that owns the resource
     * @param resourceName - String, name of the resource file
     * @return String, content of resource
     * @throws IOException - when the resource cannot be read
     */
    public static String readResource(Object parent, String resourceName) throws IOException {
        InputStream is = parent.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            throw new IOException(String.format("Could not read %s", resourceName));
        }
        String txt;
        try {
            txt = streamToString(is);
        }
        finally {
            is.close();
        }
        return txt;
    }

    /**
     * shorthand method for checking if a string is null
     * or empty
     * @param str - String
     * @return boolean
     */
    public static boolean isNullOrEmpty(String str)
    {
        return ((str == null) || (str.length() == 0));
    }


    /**
     * handy one-liner for formatting toString when the object
     * might be null
     * @param obj - Object
     * @param dflt - String
     * @return String
     */
    public static String formatToString(Object obj, String dflt) {
        return (obj != null) ? obj.toString() : dflt;
    }

    /**
     * Given a map instance, mapToNameValueString serializes the map into a name-value
     * string with configurable delimiters (like a querystring: &name1=val1&name2=val2)
     * @param map
     * @param setDelim - String
     * @param nvDelim - String
     * @return String
     */
    public static String mapToKeyValueString(Map<String, String> map, String setDelim, String nvDelim) {
        if(map == null)
        { return EMPTYSTR; }

        StringBuilder sb = new StringBuilder(B512);
        for(Map.Entry<String, String> entry : map.entrySet())
        {
            sb.append(entry.getKey());
            sb.append(nvDelim);
            sb.append(entry.getValue());
            sb.append(setDelim);
        }
        // trim trailing delimiter
        if (sb.length() > 0)
        { sb.setLength(sb.length() - (setDelim.length())); }
        return sb.toString();
    }

    /**
     * takes a delimited key-value string (like: dog=lab,cat=siamese,pet=hamster)
     * and generates a Map<String, String> from it.
     * @param src - String, delimited key value
     * @param delim - String, delimiter
     * @param sep - String, key-value separator
     * @return Map<String, String>
     */
    public static Map<String, String> keyValueStringToMap(String src, String delim, String sep)
    {
        HashMap<String, String> map = new HashMap<String, String>();

        String[] kvpairs = src.split(delim);
        String[] kv;

        for (String pair : kvpairs) {
            kv  = pair.split(sep);
            if(kv.length == 2) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return  map;
    }


}

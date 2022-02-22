package com.tremorinternational.kickstart;

import org.jsoup.nodes.Element;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.Objects;

public class HTMLParseUtil {

    private static int getSize(String data) {
        final char space = " ".toCharArray()[0];

        int n = 0;

        for (int i = data.indexOf("</span>") + "</span>".length() - 1; i < data.length(); i++) {
            if (data.charAt(i) != space)
                n++;
        }

        return n;
    }

    private static String getLastName(String data){
        char[] buf = new char[data.length()];

        final char space = " ".toCharArray()[0];
        int n = 0;

        for (int i = data.indexOf("</span>") + "</span>".length() - 1; i < data.length(); i++) {
            if (data.charAt(i) != space) {
                buf[n] = data.charAt(i);
                n++;
            }
        }

        char[] lastName = new char[getSize(data)];
        System.arraycopy(buf, 0, lastName, 0, lastName.length);
        return new StringBuilder(String.valueOf(lastName)).deleteCharAt(0).toString();
    }

    /* gets name of role */

    public static Name getName(Element element, String role) {
        Name name = new Name();

        name.firstName = element.select("div.name").select("span.firstname").html();
        name.lastName = getLastName(element.select("div.name").html());

        String text = RoleFinder.getRoleOfString(element.select("p").get(1).html());

        if (text == null) return null;
        if (text.equals(role))
            return name;

        return null;
    }
}
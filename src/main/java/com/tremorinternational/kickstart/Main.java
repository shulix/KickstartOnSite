package com.tremorinternational.kickstart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main {
    private static final String SITE_URL = "https://www.tremorinternational.com/";

    private static void printFullName(Element element, String role) {
        Name name = HTMLParseUtil.getName(element, role);

        if (name != null) {
            System.out.printf("The %s Name Is %s %s%n", role, name.firstName, name.lastName);
        }
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(SITE_URL).get();
        Elements elements = doc.select("section.management");

        Elements mItems = elements.select("div.management-item");

        String[] roles = new RoleFinder().getRoleToKeywords().keySet().toArray(new String[0]);
        for (Element mItem : mItems) {
            for (String role : roles)
                printFullName(mItem, role);
        }
    }

}
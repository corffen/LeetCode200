package com.gordon.regex;

import java.util.regex.Pattern;

public class GRegexUtils {
    String patter = "https://(\\s)+";
    String regex = "https://([a-z0-9]+)";
    Pattern p = Pattern.compile("https://([a-z0-9]+)");
}

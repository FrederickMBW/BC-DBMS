package com.company;
//A class to help with searching for things
//Only made to keep DRY
public class SearchHelper {
    //Edit a string input by the user to a string to be used for MySql Like
    //If the string is blank, replace it with a %
    public static String searchHelper(String str) {
        if (str.equals("")) {
            return "%";
        }
        return str;
    }
}

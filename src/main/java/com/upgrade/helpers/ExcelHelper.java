package com.upgrade.helpers;

public class ExcelHelper {
    public static int columnToIndex(String columnName) {
        int result = 0;
        for (int i = 0; i < columnName.length(); i++) {
            char c = columnName.charAt(i);

            int value = c - 'A' + 1;

            result = result * 26 + value;
        }
        return result - 1;
    }

    public static String indexToColumn(int index) {
        StringBuilder sb = new StringBuilder();

        index++;

        while (index > 0) {
            index--; // adjust for 1-based Excel letters
            int remainder = index % 26;
            sb.append((char) ('A' + remainder));
            index /= 26;
        }

        return sb.reverse().toString();
    }

    public static String getRowText(String[] columnValues) {
        StringBuilder result = new StringBuilder();

        result.append("\"");

        for(int i = 0; i < columnValues.length; i++) {
            if(columnValues[i] != null && !columnValues[i].equals("")) {
                result.append(columnValues[i]);
            }
            else result.append("");

            result.append("\"");
            if(i != columnValues.length - 1) { result.append(",\""); }
        }

        return result.toString();
    }
}

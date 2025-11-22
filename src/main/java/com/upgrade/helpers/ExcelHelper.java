package com.upgrade.helpers;

import com.upgrade.model.general.Section;

import java.util.StringTokenizer;

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

        for(int i = 0; i < columnValues.length; i++) {
            if(!columnValues.equals("") && !columnValues.equals(null)) {
                result.append(columnValues[i]);
            }

            if(i != columnValues.length - 1) { result.append(","); }
        }

        return result.toString();
    }
}

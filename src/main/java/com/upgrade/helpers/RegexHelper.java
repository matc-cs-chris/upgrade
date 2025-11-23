package com.upgrade.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    private static final String ASSIGNMENT_COLUMN_HEADER_REGEX =
            "\\s*(.+)\\s+\\((\\d+\\.?\\d*)\\)";
    private static final int ASSIGNMENT_NAME_REGEX_GROUP = 1;
    private static final int TOTAL_POINTS_REGEX_GROUP = 2;

    public static String[] getAssignmentNames(String[] rawNames) {
        return parseHeaders(rawNames, ASSIGNMENT_NAME_REGEX_GROUP);
    }

    public static int[] getTotalPoints(String[] rawNames) {
        String[] totalPointsText = parseHeaders(rawNames, TOTAL_POINTS_REGEX_GROUP);
        int[] totalPoints = new int[totalPointsText.length];

        for (int i = 0; i < totalPointsText.length; i++) {
            try {
                totalPoints[i] = Integer.parseInt(totalPointsText[i]);
            }
            catch (Exception e) {
                totalPoints[i] = -1;
            }
        }

        return totalPoints;
    }


    private static String[] parseHeaders(String[] rawNames, int group) {
        String[] result = new String[rawNames.length];
        Pattern pattern = Pattern.compile(ASSIGNMENT_COLUMN_HEADER_REGEX);

        for (int i = 0; i < rawNames.length; i++) {
            String rawText = rawNames[i];
            if (rawText != null && Pattern.matches(ASSIGNMENT_COLUMN_HEADER_REGEX, rawText)) {
                Matcher matcher = pattern.matcher(rawText);
                matcher.find();
                result[i] = matcher.group(group);
            }
            else result[i] = null;
        }

        return result;
    }


}

package com.upgrade.helpers;

import java.util.LinkedList;
import java.util.List;

public class SearchHelper {
    public static <S> S linearSearch(List<S> list, S searchedItem) {
        for(S s : list) {
            if(s.equals(searchedItem)) return s;
        }

        return null;
    }
}

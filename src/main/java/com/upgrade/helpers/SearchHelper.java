package com.upgrade.helpers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SearchHelper {
    public static <S> S linearSearch(List<S> list, Predicate<S> predicate) {
        for(S s : list) {
            if(predicate.test(s)) return s;
        }

        return null;
    }

    public static <S,R> R[] getInfo(S[] array, Function<S, R> function) {
        ArrayList<R> results = new ArrayList<>(array.length);

        for(S s : array) {
            results.add(function.apply(s));
        }

        return (R[]) results.toArray();
    }
}

package com.practice.datasructures.lists;

import java.util.Collections;

public class ListUtils {
	public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
	    return iterable == null ? Collections.<T>emptyList() : iterable;
	}
}

package com.filesharer.common.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.filesharer.common.core.Closure;
import com.filesharer.common.core.Function;
import com.filesharer.common.core.Predicate;

public class CollectionUtils {
	
	public static <T> Collection<T> union(Collection<T> c1, Collection<T> c2) {
		
		if (c1 == null && c2 == null) {
			return Collections.emptyList();
		} else if (c1 == null) {
			return c2;
		} else if (c2 == null) {
			return c1;
		} else {
			Collection<T> res = new ArrayList<T>();
			res.addAll(c1);
			res.addAll(c2);
			return res;
		}
	}
	
	public static <T> Collection<T> intersection(Collection<T> c1, Collection<T> c2) {
		if (c1 == null || c2 == null) return Collections.emptyList();
		Collection<T> res = new ArrayList<T>();
		res.addAll(c1);
		res.retainAll(c2);
		return res;
	}
	
	public static <T> Collection<T> subtract(Collection<T> c1, Collection<T> c2) {
		if (c1 == null) {
			return Collections.emptyList();
		} else if (c2 == null) {
			return c1;
		} else {
			Collection<T> res = new ArrayList<T>();
			res.addAll(c1);
			res.removeAll(c2);
			return res;
		}
	}
	
	public static <T> Collection<T> select(Collection<T> c, Predicate<T> p) {
		if (c == null) return Collections.emptyList();
		Collection<T> res = new ArrayList<T>();
		res.addAll(c);
		Iterator<T> iter = res.iterator();
		while (iter.hasNext()) {
			T obj = iter.next();
			if (!p.fit(obj)) {
				iter.remove();
			}
		}
		return res;
	}
	
	public static <T> void foreach(Collection<T> col, Closure<T> closure) {
		if (col == null) return;
		for (T t : col) {
			closure.execute(t);
		}
	}
	
	public static <T, K> Collection<K> map(Collection<T> col, Function<T, K> function) {
		if (col == null) return Collections.emptyList();
		Collection<K> res = new ArrayList<K>();
		for (T t : col) {
			res.add(function.apply(t));
		}
		return res;
	}

}

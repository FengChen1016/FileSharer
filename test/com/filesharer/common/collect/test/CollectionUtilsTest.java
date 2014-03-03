package com.filesharer.common.collect.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.filesharer.common.core.Closure;
import com.filesharer.common.core.Predicate;

import static com.filesharer.common.collect.CollectionUtils.*;

public class CollectionUtilsTest {
	static Collection<String> c1;
	static Collection<String> c2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// one-line initialize, actually anonymous class, subclass of ArrayList
		List<String> l1 = new ArrayList<String>() {{
			add("first");
			add("second");
			add("third");
			add("fourth");
			add("fifth");
			add("c2-2");
		}};  
		
		List<String> l2 = Arrays.asList("c2-1", "c2-2", "second", "c2-4", "someone", "fifth", "-0-");
		c1 = Collections.unmodifiableList(l1);
		c2 = Collections.unmodifiableList(l2);
	}

	@Test
	public void testUnion() {
		List<String> all = new ArrayList<String>(c1);
		all.addAll(c2);
		assertEquals(Collections.unmodifiableList(all), union(c1, c2));
		System.out.println(union(c1, c2));
		assertEquals(0, union(Arrays.asList(), Arrays.asList()).size());
		assertEquals(0, union(null, null).size());
		assertEquals(1, union(null, Arrays.asList("1")).size());
	}
	
	@Test
	public void testSelect() {
		assertEquals(4, select(c2, new Predicate<String>() {
			@Override
			public boolean fit(String obj) {
				return obj.contains("-");
			}
		}).size());
	}
	
	@Test
	public void testForeach() {
		final AtomicInteger i = new AtomicInteger(0);
		foreach(c1, new Closure<String>() {
			@Override
			public void execute(String obj) {
				System.out.println(obj);
				i.incrementAndGet();
			}
		});
		assertEquals(c1.size(), i.get());
	}
	
	@Test
	public void testMap() {
		
	}

}

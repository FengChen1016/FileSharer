package com.filesharer.common.core;

public interface Function<T, K> {
	K apply(T obj);
}

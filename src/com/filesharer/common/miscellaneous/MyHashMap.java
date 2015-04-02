package com.filesharer.common.miscellaneous;

// NotThreadSafe
public class MyHashMap<K, V> {
	
	
	Entry[] bucket = {};
	
	int initCapacity;
	
	int threshold;
	
	int size = 0;
	
	final float loadFactor;
	
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public MyHashMap(int initCapacity, float loadFactor) {
		if (initCapacity < 0)
			throw new IllegalArgumentException("Illegal initial capacity:" + initCapacity);
		this.initCapacity = initCapacity;
		if (loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal load factor:" + loadFactor);
		this.loadFactor = loadFactor;
		
		this.threshold = (int) (this.initCapacity * this.loadFactor);
		bucket = new Entry[this.initCapacity];
		
	}
	
	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public V put(K key, V value) {
		if (key == null)
			putNullKey(value);
		int hash = key.hashCode();
		int hashIndex = hashIndex(hash, bucket.length);
		for (Entry<K, V> e = bucket[hashIndex]; e != null; e = e.next) {
			if (e.key == null) {
				V old = e.value;
				e.value = value;
				return old;
			}
		}
		addEntry(hash, key, value, hashIndex);
		
		return null;
	}
	
	private void addEntry(int hash, K key, V value, int hashIndex) {
		Entry<K, V> e = bucket[hashIndex];
		bucket[hashIndex] = new Entry<K, V>(key, value, hash, e);
		if (size++ >= threshold) {
			resize(2 * bucket.length);  // rehash
		}
		
	}

	private void resize(int newCapacity) {
		Entry<K, V>[] oldBucket = bucket;
		int oldCapacity = bucket.length;
		Entry[] newBucket = new Entry[newCapacity];
		
		// rehash...  transfer old bucket to enlarged new bucket
		for (Entry<K, V> e : oldBucket) {
			if (e != null) {
				while (e != null) {
					Entry<K, V> next = e.next;
					int hash = e.hash;
					int newHashIndex = hashIndex(hash, newCapacity);
					e.next = newBucket[newHashIndex];
					newBucket[newHashIndex] = e;
					e = next;
				}
				e = null;
			}
		}
		
		bucket = newBucket;
		threshold = (int) (newCapacity * loadFactor);
		
	}

	private V putNullKey(V value) {
		for (Entry<K, V> e = bucket[0]; e != null; e = e.next) {
			if (e.key == null) {
				V old = e.value;
				e.value = value;
				return old;
			}
		}
		
		addEntry(0, null, value, 0);
		return null;
	}

	public V get(K key) {
		if (key == null) {
			return getValueOfNullKey();
		}
		int hash = key.hashCode();
		int hashIndex = hashIndex(hash, bucket.length);  // hashIndex(key);
		for (Entry<K, V> e = bucket[hashIndex]; e != null; e = e.next) {
			if (e.hash == hash && key.equals(e.key)) {
				return e.value;
			}
		}
		
		return null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	// get index in bucket
	private int hashIndex(int hash, int length) {
		return hash%length;
	}

	private V getValueOfNullKey() {
		// traverse all bucket to get null key
		for (Entry<K, V> e = bucket[0]; e != null ; e = e.next) {
			if (e.key == null)
				return e.value;
		}
		return null;
	}

	private static class Entry<K, V> {
		final K key;
		V value;
		int hash;
		Entry<K, V> next = null;
		
		Entry(K key, V value, int hash) {
			this.key = key;
			this.value = value;
			this.hash = hash;
		}
		
		Entry(K key, V value, int hash, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

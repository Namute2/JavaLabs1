package com.georg.boredapi.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * The type My cache.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
@Component
public class MyCache<K, V> {

  private final int maxSize;
  private final Map<K, V> cache;

  /** Instantiates a new My cache. */
  public MyCache() {
    maxSize = 5;
    this.cache =
        new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
          @Override
          protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > MyCache.this.maxSize;
          }
        };
  }

  /**
   * Get v.
   *
   * @param key the key
   * @return the v
   */
  public V get(K key) {
    return cache.get(key);
  }

  /**
   * Put.
   *
   * @param key the key
   * @param value the value
   */
  public void put(K key, V value) {
    cache.put(key, value);
  }

  /**
   * Remove.
   *
   * @param key the key
   */
  public void remove(K key) {
    cache.remove(key);
  }

  /** Clear. */
  public void clear() {
    cache.clear();
  }
}

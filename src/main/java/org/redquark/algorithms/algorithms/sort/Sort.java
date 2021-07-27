package org.redquark.algorithms.algorithms.sort;

@FunctionalInterface
public interface Sort<T> {

    /**
     * @param data - array to be sorted
     * @return sorted array
     */
    T[] sort(T[] data);
}

package by.issoft.store.util;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface SortingInterface<T,E> {

    Map<T,E> sortByParam(List<T> list, E param);
}

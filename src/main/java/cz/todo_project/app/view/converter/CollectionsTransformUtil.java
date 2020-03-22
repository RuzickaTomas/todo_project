package cz.todo_project.app.view.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class CollectionsTransformUtil {
 
	public static <E, T> List<E> transform(List<T> from, Function<T, E> fce) {
		List<E> to = new ArrayList<>();
		for (var element : from) {
			E result = fce.apply(element);
			to.add(result);
		}
		return to;
	}
	
	public static <E, T> Set<E> transform(Set<T> from, Function<T, E> fce) {
		Set<E> to = new HashSet<>();
		for (var element : from) {
			E result = fce.apply(element);
			to.add(result);
		}
		return to;
	}
}

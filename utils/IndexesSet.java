package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

// TODO : check if it works
public class IndexesSet implements Set<Integer> {

	private int[] indexes;
	private ArrayList<Integer> allIndexes;
	private int size;

	public IndexesSet(int capacity) {
		indexes = new int[capacity];
		allIndexes = new ArrayList<Integer>(capacity);
		size = 0;
		clear();
	}

	private void clean() {
		int i = 0;
		for (int index : indexes)
			if (index != -1) {
				indexes[i] = index;
				allIndexes.set(i, index);
				i++;
			}
		while (allIndexes.size() > size)
			allIndexes.remove(i);
		while (i < indexes.length) {
			indexes[i] = -1;
			i++;
		}
	}

	@Override
	public boolean add(Integer index) { // 0 <= index < capacity
		if (indexes[index] == -1) {
			if (allIndexes.size() == indexes.length)
				clean();
			indexes[index] = allIndexes.size();
			allIndexes.add(index);
			size++;
			return true;
		} else
			return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> cIndex) {
		boolean result = false;
		for (Integer index : cIndex)
			result = add(index) || result;
		return result;
	}

	@Override
	public void clear() {
		for (int i = 0; i < indexes.length; i++)
			indexes[i] = -1;
		allIndexes.clear();
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o instanceof Integer) {
			Integer index = (Integer) o;
			if (index >= 0 && index < indexes.length)
				return indexes[index] != -1;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o))
				return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public int index = 0;

			@Override
			public Integer next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				index++;
				while (allIndexes.get(index) == -1)
					index++;
				return allIndexes.get(index);
			}

			@Override
			public boolean hasNext() {
				return index < size;
			}
		};
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Integer) {
			Integer index = (Integer) o;
			if (index >= 0 && index < indexes.length && indexes[index] != -1) {
				allIndexes.set(indexes[index], -1);
				indexes[index] = -1;
				size--;
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c)
			result = remove(o) || result;
		return result;
	}

	private boolean contain(Collection<?> c, int index0) {
		for (Object o : c)
			if (o instanceof Integer) {
				Integer index1 = (Integer) o;
				if (index1 == index0)
					return true;
			}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		for (Integer index0 : allIndexes)
			if (index0 != -1 && !contain(c, index0))
				result = remove(index0) || result;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return toArray(new Integer[size]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a instanceof Integer[]) {
			Integer[] intA = (Integer[]) a;
			Integer[] array;
			if (a.length < size)
				array = (Integer[]) new Object[size];
			else
				array = intA;
			int i = 0;
			for (int index : allIndexes)
				if (index != -1) {
					array[i] = index;
					i++;
				}
			while (i < a.length) {
				array[i] = null;
				i++;
			}
			return (T[]) array;
		} else
			return (T[]) new Object[Math.max(size, a.length)];
	}

}

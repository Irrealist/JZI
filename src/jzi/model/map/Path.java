package jzi.model.map;

import java.util.LinkedList;

public class Path {
	private LinkedList<ICoordinates> path;
	private int index = 0;
	private ICoordinates start;
	private ICoordinates end;

	public void setPath(LinkedList<ICoordinates> path) {
		this.path = path;

		start = path.getFirst();
		end = path.getLast();
	}

	public ICoordinates peek(int peekIndex) {
		if (peekIndex >= path.size()) {
			return null;
		}

		return path.get(peekIndex);
	}

	public ICoordinates peekNext() {
		return peek(index);
	}

	public ICoordinates getNext() {
		return peek(index++);
	}

	public ICoordinates getStart() {
		return start;
	}

	public ICoordinates getEnd() {
		return end;
	}
}

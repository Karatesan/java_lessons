package org.example;

import java.util.Objects;

public class Link{
	public String ref;
	// in the future there will be more fields
	public Link(String ref) {
		this.ref=ref;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Link link = (Link) o;
		return Objects.equals(ref, link.ref);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ref);
	}

	@Override
	public String toString() {
		return "Link{" +
				"ref='" + ref + '\'' +
				'}';
	}
}
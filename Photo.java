import java.util.Set;

public class Photo {
	private int id;
	private char orientation;
	private Set<String> tags;

	public Photo(int id, char orientation, Set<String> tags) {
		super();
		this.id = id;
		this.orientation = orientation;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public int getSize() {
		return tags.size();
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", orientation=" + orientation + ", tags=" + tags + "]";
	}

}

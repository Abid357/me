import java.util.Iterator;
import java.util.Set;

public class Slide {
	private Photo p1;
	private Photo p2;

	public Slide(Photo photo) {
		this(photo, null);
	}

	public Slide(Photo photo1, Photo photo2) {
		this.p1 = photo1;
		this.p2 = photo2;
	}

	public static int similarTagsCount(Slide s1, Slide s2) {
		int count = 0;
		String[] tagsOfSlide1 = s1.getTags().toArray(new String[s1.getTags().size()]);
		String[] tagsOfSlide2 = s2.getTags().toArray(new String[s2.getTags().size()]);
		for (String tag1 : tagsOfSlide1)
			for (String tag2 : tagsOfSlide2)
				if (tag1.equals(tag2))
					count++;
		return count;
	}

	public Photo getPhoto1() {
		return p1;
	}

	public Photo getPhoto2() {
		return p2;
	}

	public boolean isHorizontal() {
		return p1.getOrientation() == 'H';
	}

	public void setPhoto2(Photo photo) {
		this.p2 = photo;
	}

	public Set<String> getTags() {
		Set<String> tags = p1.getTags();
		if (p2 != null) {
			Iterator<String> iterator = p2.getTags().iterator();
			while (iterator.hasNext())
				tags.add(iterator.next());
		}
		return tags;
	}

	@Override
	public String toString() {
		return "Slide [p1=" + p1 + ", p2=" + p2 + "]";
	}

}

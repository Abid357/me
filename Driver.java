import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Driver {

	static List<Photo> photos;
	static List<Slide> slides;
	static Slide previousSlide;
	static int size = 0;

	public static void main(String[] args) {

		// read input file
		File file = new File("b_lovely_landscapes.txt");
		photos = new ArrayList<Photo>();
		slides = new ArrayList<Slide>();
		Scanner reader = null;
		try {
			reader = new Scanner(file);
			size = Integer.parseInt(reader.nextLine());
			int count = 0;
			for (int i = 0; i < size; i++) {
				String[] tokens = reader.nextLine().split(" ");
				int tagCount = Integer.parseInt(tokens[1]);
				Set<String> tags = new HashSet<String>();
				for (int j = 0; j < tagCount; j++)
					tags.add(tokens[j + 2]);
				photos.add(new Photo(count++, tokens[0].charAt(0), tags));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
		}

		boolean done = false;
		previousSlide = new Slide(photos.remove(0));
		// int iterations = 0;
		// int countOfPreviousPhotosUsed = 0;
		int limit = 0;
		while (!done) {
			// selection photo 1

			int index = determineNextSlide();
			if (index == -1) {
				previousSlide = new Slide(photos.remove(0));
				done = photos.size() == 0;
				continue;
			}
			slides.add(previousSlide);
			Slide slide = new Slide(photos.remove(index));
			previousSlide = slide;

			/*
			 * boolean isVertical = false; boolean isSecondPhotoFound = false; if
			 * (!slide.isHorizontal()) { // select photo 2 isVertical = true; int secondId =
			 * 0; while ((photosUsed.contains(secondId) ||
			 * photos.get(secondId).getOrientation() != 'V') && secondId < size) ++secondId;
			 * if (secondId == size) continue; else { isSecondPhotoFound = true;
			 * slide.setPhoto2(photos.get(secondId)); photosUsed.add(secondId); } }
			 * 
			 * if (isVertical && !isSecondPhotoFound) { photosUsed.remove(currentId); } else
			 * slides.add(slide);
			 * 
			 * 
			 * 
			 * if (countOfPreviousPhotosUsed != photosUsed.size()) {
			 * countOfPreviousPhotosUsed = photosUsed.size(); iterations = 0; } else {
			 * ++iterations; }
			 * 
			 * if (iterations == 3 || photosUsed.size() == size) done = true;
			 */

			done = photos.size() == 0;
		}

		// write output file
		PrintWriter writer = null;
		try {
			file = new File("b.txt");
			writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			writer.println(slides.size());
			for (int i = 0; i < slides.size(); i++) {
				String string = Integer.toString(slides.get(i).getPhoto1().getId());
				if (slides.get(i).getPhoto2() != null)
					string += " " + slides.get(i).getPhoto2().getId();
				writer.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}
	}

	private static int determineNextSlide() {
		// TODO Auto-generated method stub
		System.out.println("Photos left: " + photos.size() + "\tSlides created: " + slides.size());
		//int tagCountForPhoto1 = previousSlide.getTags().size();
		int i = 0;
		int maxIndex = -1;
		do {
			Slide currentSlide = new Slide(photos.get(i++));
			//int tagCountForPhoto2 = currentSlide.getTags().size();
			int tagCountForSimilars = Slide.similarTagsCount(previousSlide, currentSlide);

//			double maxFlooredAvg = 0.0;
//			if (tagCountForSimilars > 0) {
//				int tagCountONLYPhoto1 = tagCountForPhoto1 - tagCountForSimilars;
//				int tagCountONLYPhoto2 = tagCountForPhoto2 - tagCountForSimilars;
//				double flooredAvg = Math.floor((tagCountONLYPhoto1 + tagCountONLYPhoto2 + tagCountForSimilars) / 3.0);
//				if (flooredAvg > maxFlooredAvg) {
//					maxFlooredAvg = flooredAvg;
//					maxIndex = i;
//				}
//			}

			if (tagCountForSimilars > 0) {
				return i;
			}
		} while (i < photos.size() - 1);
		return maxIndex;
	}
}

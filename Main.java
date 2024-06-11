import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) {
		int lineCount = 0;
		List<File> listOfFiles = new ArrayList<File>();
		File folder = new File(
				"C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\AppLogic");
		File[] files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));

		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\Core");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\DBMS");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File(
				"C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\Facade");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\Frame");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Software for Dad\\Packages\\GUI");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		

		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\cards");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\cores");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\databases");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\frames");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\globals");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\cFlow\\src\\handlers");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		

		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Business Cube\\B-Cube Printer\\src");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));
		folder = new File("C:\\Users\\Abid357\\Dropbox\\Projects and Presentations\\Business Cube\\B-Cube\\src");
		files = folder.listFiles();
		listOfFiles.addAll(Arrays.asList(files));

		for (File file : listOfFiles) {
			try {
				if (file.getAbsolutePath().endsWith(".java"))
					lineCount += countLines(file.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(lineCount);
	}
}

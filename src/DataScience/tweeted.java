package DataScience;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yanwen Jin
 *
 * 
 */
public class tweeted {

	private final File mDir;
	private final File mLog;

	private static HashMap<String, Integer> wordsCount;

	public tweeted(String dirPath, String logPath) {
		String filePath = new File("").getAbsolutePath();
		mDir = new File(filePath + dirPath);
		mLog = new File(filePath + logPath);
		wordsCount = new HashMap<String, Integer>();

	}

	private void detect() {
		// System.out.println(mLog.getAbsolutePath());
		if (!mDir.exists() || !mDir.canRead()) {
			System.out
					.println("Document directory is not specified correctly.");
			System.exit(0);
		}

		processFile();
		writeFileForCount();
	}

	private void processFile() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(mDir);
		} catch (FileNotFoundException e) {
			// System.out.println("Can not find input file" );
			System.exit(0);
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		try {
			ArrayList<String> temps = new ArrayList<String>();
			String line = br.readLine();

			while (line != null) {
				temps.add(line);
				line = br.readLine();
			}
			for (int i = 0; i < temps.size(); i++) {
				String[] tmp = temps.get(i).split(" ");
				for (String e : tmp) {
					if (wordsCount.containsKey(e)) {
						wordsCount.put(e, wordsCount.get(e) + 1);
					} else {
						wordsCount.put(e, 1);
					}
				}

			}

		} catch (IOException e) {

		} finally {
			try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Write to the log file
	 * 
	 */
	private void writeFileForCount() {

		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(mLog));
			List<String> sortedKey = new ArrayList(wordsCount.keySet());
			Collections.sort(sortedKey);
			for (String e : sortedKey) {
				String output = String.format("%-24s  %5d", e,
						wordsCount.get(e));
				w.write(output);
				w.newLine();
			}

			w.close();

		} catch (IOException x) {
			System.out.format("createFile error: %s%n", x);
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		tweeted t = new tweeted(args[0], args[1]);
		// tweeted t = new
		// tweeted("/tweet_input/tweets.txt","/tweet_output/ft1.txt");
		t.detect();
	}

}

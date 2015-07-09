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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yanwen Jin
 *
 * 
 */

public class medianUnique {

	private final File mDir;
	private final File mLog2;

	private static Map<Integer, Integer> content;

	public medianUnique(String dirPath, String logPath2) {
		String filePath = new File("").getAbsolutePath();
		mDir = new File(filePath + dirPath);

		mLog2 = new File(filePath + logPath2);

		content = new HashMap<Integer, Integer>();

	}

	private void detect() {
		if (!mDir.exists() || !mDir.canRead()) {
			System.out
					.println("Document directory is not specified correctly.");
			System.exit(0);
		}
		processFile();
		writeMedian();
	}

	private void processFile() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(mDir);
		} catch (FileNotFoundException e) {
			System.out.println("Can't find input file ");
			System.exit(0);
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		try {
			String line = br.readLine();
			int count = 1;
			while (line != null) {
				ArrayList<String> temps = new ArrayList<String>();
				temps.add(line);
				int num = 0;
				Set<String> unique = new HashSet<String>();
				for (int i = 0; i < temps.size(); i++) {
					String[] tmp = temps.get(i).split(" ");
					for (String e : tmp) {
						if (!unique.contains(e)) {
							unique.add(e);
							num++;
						}

					}
				}
				content.put(count, num);
				count++;
				line = br.readLine();
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

	private void writeMedian() {
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(mLog2));
			ArrayList<Integer> number = new ArrayList<Integer>();
			for (Integer n : content.values()) {
				number.add(n);
				String output = Double.toString(getMedian(number));
				w.write(output);
				w.newLine();
			}

			w.close();

		} catch (IOException x) {
			System.out.format("createFile error: %s%n", x);
			System.exit(0);
		}
	}

	private double getMedian(ArrayList<Integer> list) {
		double median = 0;
		Collections.sort(list);
		if (list.size() % 2 == 0)
			median = ((double) list.get(list.size() / 2) + (double) list
					.get(list.size() / 2 - 1)) / 2;
		else
			median = (double) list.get(list.size() / 2);

		return median;
	}

	public static void main(String[] args) {
		medianUnique t = new medianUnique("/tweet_input/tweets.txt",
				"/tweet_output/ft2.txt");
		// t.detect();
		// medianUnique t = new medianUnique(args[0],args[1]);
		t.detect();

	}

}

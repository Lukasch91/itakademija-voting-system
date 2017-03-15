package _utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giedrius Mauza on 2017-02-09.
 */
public class FileReaderMain {

	public static List<String> getTestDataFromTxtFile(String fileName) {

		List<String> records = new ArrayList<String>();

		try {
			File fileDir = new File(fileName);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF-8"));
			String str;

			while ((str = in.readLine()) != null) {
				records.add(str);
			}

			in.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return records;

	}
}

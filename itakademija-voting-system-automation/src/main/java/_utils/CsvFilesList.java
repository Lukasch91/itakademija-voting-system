package _utils;

import java.io.File;

public class CsvFilesList {

	public static String partyCanSemicolon = absolutPath("src/main/resources/CsvFiles/PartyCanSemicolon.csv");;
	public static String partyCanComma = absolutPath("src/main/resources/CsvFiles/PartyCanComma.csv");
	public static String constituencyCanSemicolon = absolutPath("src/main/resources/CsvFiles/ConstituencyCanSemicolon.csv");
	public static String constituencyCanComma = absolutPath("src/main/resources/CsvFiles/ConstituencyCanComma.csv");

	public static String absolutPath(String path) {
		File currentDirFile = new File(path);
		String helper = currentDirFile.getAbsolutePath();
		return helper;

	}
}

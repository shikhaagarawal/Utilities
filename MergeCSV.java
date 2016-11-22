import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MergeCSV {
	public static void main(String args[]) throws IOException{
		String[] headers = null;
		String firstFile = "C:\\ExportReport.csv";
		String file2 ="C:\\\ExportReport (1).csv";
		String file3 ="C:\\ExportReport (2).csv";
		String file4 ="C:\\ExportReport (3).csv";
		String file5 ="C:\\ExportReport (4).csv";
		Scanner scanner = new Scanner(new File(firstFile));

		if (scanner.hasNextLine()){
			scanner.nextLine();
			scanner.nextLine();
		    headers = scanner.nextLine().split(",");
		}

		scanner.close();
		List<File> listOfFilesToBeMerged = new ArrayList<File>();
		listOfFilesToBeMerged.add(new File(file2));
		listOfFilesToBeMerged.add(new File(file3));
		listOfFilesToBeMerged.add(new File(file4));
		listOfFilesToBeMerged.add(new File(file5));
	
		Iterator<File> iterFiles = listOfFilesToBeMerged.iterator();
		BufferedWriter writer = new BufferedWriter(new FileWriter(firstFile, true));

		while (iterFiles.hasNext()) {
		  File nextFile = iterFiles.next();
		  BufferedReader reader = new BufferedReader(new FileReader(nextFile));

		  String line = null;
		  String[] firstLine = null;
		  reader.readLine();
		  reader.readLine();
		  if ((line = reader.readLine()) != null)
		    firstLine = line.split(",");

		  if (!Arrays.equals (headers, firstLine))
		    System.out.println("Header mis-match between CSV files: '" +
		              firstFile + "' and '" + nextFile.getAbsolutePath());

		  while ((line = reader.readLine()) != null) {
		    writer.write(line);
		    writer.newLine();
		  }

		  reader.close();
		}
		writer.close();
		
		File f = new File(firstFile);
		f.renameTo(new File("C:\\output.csv"));
		f = new File(file2);
		f.delete();
		f = new File(file3);
		f.delete();
		f = new File(file4);
		f.delete();
		f = new File(file5);
		f.delete();

	}
}

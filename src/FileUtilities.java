import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author hank.duan
 * 
 */
public class FileUtilities {

	/**
	 * write (String data, String fileloc) writes data to a file with its
	 * location defined in fileloc
	 * 
	 * @param data
	 *            : data to be written
	 * @param fileloc
	 *            : location of file to be written to
	 */
	public static void write(String data, String fileloc) {
		// Create directory folders (and possibly parent folders) if the
		// directory does not exist
		File f = new File(fileloc + "/..");
		try {
			f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Create file
			FileWriter fstream = new FileWriter(fileloc);
			BufferedWriter out = new BufferedWriter(fstream);

			// Write
			out.write(data);
			// Close the output stream
			out.close();
		} catch (Exception ex) {// Catch exception if any
			ex.printStackTrace();
		}
	}
	
	public static void append(String data, String fileloc) {
		// Create directory folders (and possibly parent folders) if the
		// directory does not exist
		File f = new File(fileloc + "/..");
		try {
			f.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// Create file
			FileWriter fstream = new FileWriter(fileloc, true);
			BufferedWriter out = new BufferedWriter(fstream);

			// Write
			out.write(data + "\r\n");
			// Close the output stream
			out.close();
		} catch (Exception ex) {// Catch exception if any
			ex.printStackTrace();
		}
	}

	public static String read(String fileloc) {
		
		String contents = "";

		try {
			BufferedReader input = new BufferedReader(new FileReader(fileloc));

			String line = null;

			while ((line = input.readLine()) != null) {
				contents += line;
				contents += System.getProperty("line.separator");
			}

			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return contents;
	}

}
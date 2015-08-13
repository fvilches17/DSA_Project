package DSA_Project.Breakout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Francisco Vilches ID1115994
 * @version 1.0
 * 
 * This class is used to access any system file relevant to the program. This class can either add or retrieve information
 * from the file being accessed.
 * 
 * Relevant system files for this program are:
 * 1. CheckOutLog.bin - contains customer purchase details
 * 2. ErrorLog.bin - contains a log of exceptions thrown by the program
 * 3. UsersDataBase.bin - contains a database of Users(and their details) that have created an account
 */
public class FileAccessor implements Serializable {
	private static final long serialVersionUID = -3302967941066846101L;

	/**
	 * This method is used to retrieve information from any system file containing any type of ArrayList.
	 * NOTE: this method can only be used with system files that contact a single ArrayList
	 * 
	 * @param fileName is a String representing the name of the system file from which to retrieve information.
	 * E.g "ErrorLog.bin"
	 * @return an ArrayList of any type from the system file specified in the parameter
	 */
	public static ArrayList<?> getArrayFromFile(String fileName) {
		ArrayList<?> array = new ArrayList<Object>();
		ObjectInputStream in=null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName)); //Accessing system file
			array = (ArrayList<?>) in.readObject(); //reading/deserializing file and assigning an ArrayList to variable array
			in.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Unkown System Error while reading internal file in read");
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException | NullPointerException e1) {
				System.err.println("Unkown System Error while reading internal file");
				e.printStackTrace();
			}
		} 
		
		return array;
	}
	
	/**
	 * This method accesses a given system file and overwrites the file's data with the ArrayList which is input
	 * in the method's parameter.
	 * 
	 * @param array any type of ArrayList
	 * @param fileName is a String representing the system file which to access. E.g. "ErrorLog.bin"
	 * @return false if the specified file could not be accessed, true if file is accessed 
	 */
	public static boolean writeArrayIntoFile(ArrayList<?> array, String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName)); //Accessing system file
			out.writeObject(array); //Overwriting file and inserting arrayList from parameter
			out.close();
		} catch (IOException e) {
			System.err.println("Unkown System Error while accessing internal file");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

//TODO check how program handles IO errors

package infpp.oceanlife;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

/**
 * This is the class which implements the Deserialization to load the ocean and it's objects.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public class Deserialization {
	
	/**
	 * This is the filename of the file to be loaded.
	 */
	private static String filename = "OceanLifeSavingData"; // This could be done with a scanner so that the user would choose the file.
	
	/**
	 * This method loads the ocean and it's objects by means of deserialization. If the loading operation is successful, the loaded ocean is returned. If not, the old ocean will be returned.
	 * @return Returns the deserialized (loaded) ocean.
	 * @param width This is the width the ocean has before loading.
	 * @param depth This is the depth the ocean has before loading.
	 * @param oceanObjects This is the LinkedList with the oceanObjects the ocean has before loading.
	 * @exception Exception Throws an exception if there occurs one of many possible problems (e.g. IOException, ClassNotFoundException,..) during the access of the file.
	 */
	 public static Ocean deserialize(int width, int depth, LinkedList<OceanObject> oceanObjects) {
		
		 Ocean ocean = new Ocean(width, depth, oceanObjects);
		  
		 try {
			 InputStream input = new FileInputStream(filename + ".ser");
			 ObjectInputStream objectinput = new ObjectInputStream(input); 
			 ocean = (Ocean) objectinput.readObject();
			 objectinput.close();
			 System.out.println("Deserialization succeeded");
		 } catch (Exception e) {
			 System.out.println("Deserialization failed");
		 }
		 return ocean;
	 }
}

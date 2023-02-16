package infpp.oceanlife;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * This is the class which implements the Serialization to save the ocean and it's objects.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public class Serialization {
	
	/**
	 * This is the filename of the file to be saved.
	 */
	private static String filename = "OceanLifeSavingData"; // This could be done with a scanner so that the user would choose the file name.
	
	/**
	 * This method saves the ocean and it's objects by means of serialization.
	 * @param ocean The parameter is the ocean which is to be saved.
	 * @exception Exception Throws an exception if there occurs one of many possible problems during the access of the file.
	 * @exception IOException Throws an IOException if an I/O error occurs. 
	 */
	 public static void serialize(Ocean ocean) {
		 OutputStream fos = null;
		 try {
			 fos = new FileOutputStream(filename + ".ser");
			 ObjectOutputStream out = new ObjectOutputStream(fos);
			 out.writeObject(ocean);
			 System.out.println("Serialization succeeded");
			 out.close();
		 } catch(Exception e) {
			 System.out.println("Serialization failed");
			 System.out.println();
			 }
		 finally {
			 try {
				 fos.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 }
	 }
}
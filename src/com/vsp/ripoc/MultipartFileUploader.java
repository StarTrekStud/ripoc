package com.vsp.ripoc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class MultipartFileUploader {

	private static final String FILENAME = "C:\\ripoc\\swap.txt";
	 
	    public String fileRead() {
	    	String swap = null;
	        try {
	            FileReader reader = new FileReader(FILENAME);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            swap = bufferedReader.readLine();
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return swap;
	    }
	 
	    public static void fileWrite(String filename) {
	        try {
	            FileWriter writer = new FileWriter(FILENAME, true);
	            writer.write(filename);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void clearFile() throws IOException {
	        FileWriter fwOb = new FileWriter(FILENAME, false); 
	        PrintWriter pwOb = new PrintWriter(fwOb, false);
	        pwOb.flush();
	        pwOb.close();
	        fwOb.close();
	    }

	public static void fileUploader(String user, String rightEye, String leftEye) {
		String charset = "UTF-8";
		File uploadFile1 = new File(rightEye);
		File uploadFile2 = new File(leftEye);
		String requestURL = "http://ec2-35-165-236-9.us-west-2.compute.amazonaws.com/master/upload";

		try {
			MultipartUtility multipart = new MultipartUtility(requestURL, charset);

			multipart.addFormField("username", user);
			multipart.addFormField("password", "UploadingRetinalImagesI$MyJ08!");
			multipart.addFilePart("left", uploadFile1);
			multipart.addFilePart("right", uploadFile2);

			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				System.out.println(line);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}

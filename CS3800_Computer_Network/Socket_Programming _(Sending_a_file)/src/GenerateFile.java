import java.io.*;
public class GenerateFile {

	
	public static String generate_file(String input_data_file) {
		try {
			FileWriter fw = new FileWriter(input_data_file);
			long filesize = 7000000;
			for(int i=0; i < filesize; i++) {
				fw.write("A");
			}
			fw.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return input_data_file;
	}
	
	public static void main(String[] args) {
		
		//generate_file("data500B.txt");
		//generate_file("data10000B.txt");
		//generate_file("data80000B.txt");
		//generate_file("data400000B.txt");
		//generate_file("data2000000B.txt");
		generate_file("data7000000B.txt");
		
	}

}

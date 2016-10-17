package ai_assignment2;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReader {
	//class variables
	
	public FileReader(){
	
	}
	
	//STEP 1: USE THIS TO CONVERT PICTURE INTO ARRAY
		public static char[][] getPicture(String filename){
			  File dictionary = new File(filename);
			  char[][] picture = new char[12][12];
			  String line ="";
			  try {
			    Scanner diskf = new Scanner(dictionary);
			    for (int i =0; i<12; i++){
			    	line = diskf.nextLine();
					for (int j =0; j<12; j++){
						  picture[i][j]=line.charAt(j);
					}
			    }
			    diskf.close();
			      
			  }
			  catch (FileNotFoundException e){
			    e.printStackTrace();
			  }
			  return picture;
			}
		
	//CONTROLLER
	public static float[] makeDataSet(String filename){
		char[][] picture = getPicture(filename);
		float[] dataset = convertPictureToFloat(picture);
		return dataset;
	}
	
	
	//STEP 2: Convert to a 1d float array
	public static float[] convertPictureToFloat(char[][] picture){
		float[] data = new float[12*12];
		int count = 0;
		for (int i = 0; i < 12 ; i++ ){
			for ( int j =0; j < 12; j++){
				if (picture[i][j]==('*')){
					data[count]=(float)1;
					count++;
				}
				else{
					data[count]=(float)0;
					count++;
				}
			}
		}
		return data;
	}
	// this method creates an entire dataset for a set of patterns
	public static List<float[]> makeDataSet_all(List<char[][]> patterns){
		List<float[]> DataSet= new ArrayList<>();
		for (int i = 0 ; i< patterns.size(); i++){
			char[][] picture = patterns.get(i);
			DataSet.add(convertPictureToFloat(picture));
		}
		return DataSet;
	}
	
	// making the the patterns
	public static List<char[][]> makeTrainingPatterns(){
		List<char[][]> patterns = new ArrayList<>();
		List<String> names = makeTrainingNamesList();
		for ( int i = 0; i < names.size(); i++){
			String name = names.get(i);
			patterns.add(getPicture(name));
		}	
		return patterns;
	}
	
	public static List<char[][]> makeTest10Patterns(){
		List<char[][]> patterns = new ArrayList<>();
		List<String> names = makeTest10NamesList();
		for ( int i = 0; i < names.size(); i++){
			String name = names.get(i);
			patterns.add(getPicture(name));
		}	
		return patterns;
	}
	
	public static List<char[][]> makeTest5Patterns(){
		List<char[][]> patterns = new ArrayList<>();
		List<String> names = makeTest5NamesList();
		for ( int i = 0; i < names.size(); i++){
			String name = names.get(i);
			patterns.add(getPicture(name));
		}	
		return patterns;
	}
	// loading filenames for the patterns
	public static List<String> makeTrainingNamesList(){
		List<String> names = new ArrayList<String>();
		names.add("Pattern Files/Clock.txt");
		names.add("Pattern Files/Cross.txt");
		names.add("Pattern Files/Exclamation.txt");
		names.add("Pattern Files/Face.txt");
		names.add("Pattern Files/Giveway.txt");
		names.add("Pattern Files/House.txt");
		names.add("Pattern Files/Info.txt");
		names.add("Pattern Files/Smile.txt");
		names.add("Pattern Files/Stand.txt");
		names.add("Pattern Files/Stop.txt");
		names.add("Pattern Files/Tick.txt");
		names.add("Pattern Files/Walk.txt");
		return names;	
	}
	
	public static List<String> makeTest5NamesList(){
		List<String> names = new ArrayList<String>();
		names.add("Test Files 5/Clock.noise.txt");
		names.add("Test Files 5/Cross.noise.txt");
		names.add("Test Files 5/Exclamation.noise.txt");
		names.add("Test Files 5/Face.noise.txt");
		names.add("Test Files 5/Giveway.noise.txt");
		names.add("Test Files 5/House.noise.txt");
		names.add("Test Files 5/Info.noise.txt");
		names.add("Test Files 5/Smile.noise.txt");
		names.add("Test Files 5/Stand.noise.txt");
		names.add("Test Files 5/Stop.noise.txt");
		names.add("Test Files 5/Tick.noise.txt");
		names.add("Test Files 5/Walk.noise.txt");
		return names;		
	}
	
	public static List<String> makeTest10NamesList(){
		List<String> names = new ArrayList<String>();
		names.add("Test Files 10/Clock.noise.txt");
		names.add("Test Files 10/Cross.noise.txt");
		names.add("Test Files 10/Exclamation.noise.txt");
		names.add("Test Files 10/Face.noise.txt");
		names.add("Test Files 10/Giveway.noise.txt");
		names.add("Test Files 10/House.noise.txt");
		names.add("Test Files 10/Info.noise.txt");
		names.add("Test Files 10/Smile.noise.txt");
		names.add("Test Files 10/Stand.noise.txt");
		names.add("Test Files 10/Stop.noise.txt");
		names.add("Test Files 10/Tick.noise.txt");
		names.add("Test Files 10/Walk.noise.txt");
		return names;		
	}

	
	// shows a 2d char [][] of * to show the picture to a user
	public static void showPicture (char[][] picture){
		//System.out.println("Showing picture");
		  for (int i =0; i<12; i++){
			  System.out.println();
			  for (int j =0; j<12; j++){
				  System.out.print(picture[i][j]);			  
			  }
		   }
		  System.out.println();
		}
	// for use in testing
	public static void showData (float[] dataSet){
		System.out.println();
		  for (int i =0; i<5; i++){
			  System.out.print((dataSet[i])+" ");			  
		   }
		  System.out.println();
		}
	// shows the entire dataset
	public static void ShowDataSet(List<float[]> dataSet){
		for (int i = 0; i < dataSet.size(); i++){
			showData(dataSet.get(i));
		}
	}
	// show all patterns to the user
	public static void ShowPatterns(List<char[][]> patterns){
		for (int i = 0; i < patterns.size(); i++){
			showPicture(patterns.get(i));
		}
	}
	// used for testing
	public static float getValue(float[][] dataset){
		float value = 0;
		for (int i =0; i<12; i++){
			  for (int j =0; j<12; j++){
				  value = value + dataset[i][j];			  
			  }
		   }
		return value;
	}
}

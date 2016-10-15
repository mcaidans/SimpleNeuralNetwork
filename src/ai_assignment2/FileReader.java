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
	
	
	public static List<String> makeTrainingNamesList(){
		List<String> names = new ArrayList<String>();
		names.add("Pattern Files/Clock.txt");
		return names;	
	}

	
	
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
	public static void showData (float[] dataSet){
		System.out.println();
		  for (int i =0; i<5; i++){
			  System.out.print((dataSet[i])+" ");			  
		   }
		  System.out.println();
		}
	
	public static void ShowDataSet(List<float[]> dataSet){
		for (int i = 0; i < dataSet.size(); i++){
			showData(dataSet.get(i));
		}
	}
	public static void ShowPatterns(List<char[][]> patterns){
		for (int i = 0; i < patterns.size(); i++){
			showPicture(patterns.get(i));
		}
	}
	
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

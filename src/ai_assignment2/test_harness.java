package ai_assignment2;
import java.util.*;
import java.io.*;
public class test_harness {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		// setup data stores
		List<char[][]> trainingPatterns = FileReader.makeTrainingPatterns();
		List<char[][]> test5Patterns = FileReader.makeTest5Patterns();
		List<char[][]> test10Patterns = FileReader.makeTest10Patterns();		
		List<float[]> trainingDataSet = FileReader.makeDataSet_all(trainingPatterns);
		List<float[]> test5DataSet = FileReader.makeDataSet_all(test5Patterns);
		List<float[]> test10DataSet = FileReader.makeDataSet_all(test10Patterns);
	// setup network	
		int ils = 12*12; // input layer size
		int hls = 7; // hidden layer size
		int ols = 12; // output layer size
		
		Network neuralNet = new Network(ils,hls,ols);
		
	// running network
		neuralNet.runNetwork();
	// setup test images for run
		Boolean finish = false;
		while (!finish){
			System.out.println("enter image to test");
			int testCode = keyboard.nextInt();
			float[] testingImage1 = test10DataSet.get(testCode);		
			neuralNet.setInput(testingImage1);
			neuralNet.forwardPropagate();
			System.out.println();
			System.out.println("Testing Noise 10 for: " + testCode);
			float[] outputData = neuralNet.getOutputData();
			for (int i = 0;i < outputData.length; i++){
				System.out.print(outputData[i] + " ");
			}
			
		}
		
	}
	
}


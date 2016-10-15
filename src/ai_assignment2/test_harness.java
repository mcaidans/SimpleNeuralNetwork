package ai_assignment2;
import java.util.*;
public class test_harness {

	public static void main(String[] args) {

		
		float[] image1 = FileReader.makeDataSet();
		
		int ils = 12*12; // input layer size
		int hls = 5; // hidden layer size
		int ols = 12; // output layer size
		
		Network neuralNet = new Network(ils,hls,ols);
		neuralNet.setInput(image1);
		//neuralNet.printInput();
		

		

		
		
		neuralNet.forwadPropagate();
	
		System.out.println();
		System.out.println("Output:");
		float[] outputData = neuralNet.getOutputData();
		for (int i = 0;i < outputData.length; i++){
			System.out.print(outputData[i] + " ");
		}
	}
	
}


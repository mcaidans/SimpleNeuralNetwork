package ai_assignment2;
import java.util.*;

public class assignment2 {

	public static void main(String[] args) {
		// load the Scanner
		Scanner kb = new Scanner(System.in);

		// setup test data stores
		List<char[][]> trainingPatterns = FileReader.makeTrainingPatterns();
		List<char[][]> test5Patterns = FileReader.makeTest5Patterns();
		List<char[][]> test10Patterns = FileReader.makeTest10Patterns();
		List<float[]> trainingDataSet = FileReader.makeDataSet_all(trainingPatterns);
		List<float[]> test5DataSet = FileReader.makeDataSet_all(test5Patterns);
		List<float[]> test10DataSet = FileReader.makeDataSet_all(test10Patterns);
		
		//Set layer values
		int ils = 12*12; // input layer size
		int hls = 7; // hidden layer size
		int ols = 12; // output layer size
		Network neuralNet = new Network(ils,hls,ols);
		System.out.print("Start Training? Enter 'Y' to begin ");
		char yesno = kb.next().toUpperCase().charAt(0);
		if (yesno=='N'){
			System.out.print("Enter desired RMS success threshold (Betweeny 0.0075 and 0.0001): ");
			float RMS = kb.nextFloat();
			neuralNet.setRMS(RMS);
			System.out.print("Enter desired learning rate (Between 0.99 and 0.01): ");
			float learningRate = kb.nextFloat();
			neuralNet.setLearningRate(learningRate);
			System.out.print("Enter maximum number of iterations: ");
			int noIter = kb.nextInt();
			neuralNet.setMaxIterations(noIter);
		}
		
		// train the network
		System.out.println("Training Network...");
		neuralNet.runNetwork();
		System.out.println("Training Complete!");
		System.out.println();
		System.out.print("Would You like to run a custom file from the custom folder? Y/N: ");
		yesno = kb.next().toUpperCase().charAt(0);
		if (yesno=='Y'){
			System.out.print("Please Enter Custom filename including extension from the custom folder: ");
			String name = kb.next();
			char[][] picture = FileReader.getPicture("custom/"+name);
			float [] customData = FileReader.makeDataSet("custom/"+name);
			System.out.print("showing picture");
			FileReader.showPicture(picture);
			neuralNet.setInput(customData);
			neuralNet.forwardPropagate();
			System.out.println();
			System.out.println("Testing Custom data");
			System.out.println();
			float[] outputData = neuralNet.getOutputData();
			for (int i = 0;i < outputData.length; i++){
				System.out.print(outputData[i] + " ");
			}
			int pos = 0;
			float highest = (float)0;
			for (int i = 0; i< outputData.length;i++){
				if (highest < outputData[i]){
					highest = outputData[i];
					pos = i;
				}
			}
			System.out.println();
			System.out.println("Showing Closest Picture");
			FileReader.showPicture(trainingPatterns.get(pos));
		}
		else {

			boolean happy = false;
			int fiveOrTen = 0;
			while (!happy){
				System.out.print("which test data set would you like ?: 5 or 10 : ");
				fiveOrTen = kb.nextInt();
				if (fiveOrTen == 5 || fiveOrTen == 10){
					happy=true;
				}
				else{
					System.out.println("Please enter a valid choice");
				}
			}


			if (fiveOrTen==5){
				boolean happy2 = false;
				int oneToTwelve =0;
				while (happy2==false){
					System.out.print("which test data picture would you like ?: 1 to 12 : ");
					oneToTwelve = kb.nextInt();
					if (oneToTwelve >= 1 && oneToTwelve <= 12){
						happy2=true;
					}
					else{
						System.out.println("Please enter a valid choice");
					}
				}
				System.out.print("showing picture");
				FileReader.showPicture(test5Patterns.get(oneToTwelve-1));
				System.out.print("testing picture");
				// sending test image through		
				neuralNet.setInput(test5DataSet.get(oneToTwelve-1));
				neuralNet.forwardPropagate();
				System.out.println();
				System.out.println("Testing Noise 5 for: " + (oneToTwelve));
				System.out.println();
				float[] outputData = neuralNet.getOutputData();
				for (int i = 0;i < outputData.length; i++){
					System.out.print(outputData[i] + " ");
				}
				int pos = 0;
				float highest = (float)0;
				for (int i = 0; i< outputData.length;i++){
					if (highest < outputData[i]){
						highest = outputData[i];
						pos = i;
					}
				}
				System.out.println();
				System.out.println("Showing Closest Picture");
				FileReader.showPicture(trainingPatterns.get(pos));
			}
			if (fiveOrTen==10){
				boolean happy4 = false;
				int oneToTwelve3 =0;
				while (happy4 == false){
					System.out.print("which test data picture would you like ?: 1 to 12 : ");
					oneToTwelve3 = kb.nextInt();
					if (oneToTwelve3 >= 1 && oneToTwelve3 <= 12){
						happy4=true;
					}
					else{
						System.out.println("Please enter a valid choice");
					}
				}
				System.out.print("showing picture");
				FileReader.showPicture(test10Patterns.get(oneToTwelve3-1));
				System.out.print("testing picture");
				// sending test image through		
				neuralNet.setInput(test10DataSet.get(oneToTwelve3-1));
				neuralNet.forwardPropagate();
				System.out.println();
				System.out.println("Testing Noise 5 for: " + (oneToTwelve3));
				System.out.println();
				float[] outputData2 = neuralNet.getOutputData();
				for (int i = 0;i < outputData2.length; i++){
					System.out.print(outputData2[i] + " ");
				}
				int pos = 0;
				float highest = (float)0;
				for (int i = 0; i< outputData2.length;i++){
					if (highest < outputData2[i]){
						highest = outputData2[i];
						pos = i;
					}
				}
				System.out.println();
				System.out.println("Showing Closest Picture");
				FileReader.showPicture(trainingPatterns.get(pos));
			}
		}
	}
}


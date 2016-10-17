package ai_assignment2;
import java.util.*;

public class Network {
	
	//Variables
	protected static List<Neuron> inputLayer = new ArrayList<>();
	protected List<Neuron> hiddenLayer1 = new ArrayList<>();
	protected List<Neuron> outputLayer = new ArrayList<>();
	public float learningRate = (float).1;
	private float RMSvalue = (float) 0.007;
	private int iterationNumber = 120000;
	public ErrorCalc error;
	private int inputLayerSize;
	private int hiddenLayerSize;
	private int outputLayerSize;
	
	//Constructor
	public Network(int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		this.inputLayerSize = inputLayerSize;
		this.hiddenLayerSize = hiddenLayerSize;
		this.outputLayerSize = outputLayerSize;
		error = new ErrorCalc();	//Initialize error
		
		//creating output layer
		for ( int i = 0; i < outputLayerSize; i++){
			Neuron neuron = new Neuron("output");
			outputLayer.add(neuron);
		}
		// creating hiddenLayer1
		for ( int i = 0; i < hiddenLayerSize; i++){
			Neuron neuron = new Neuron("hidden");
			hiddenLayer1.add(neuron);
		}
		
		// creating inputLayer
		for ( int i = 0; i < inputLayerSize; i++){
			Neuron neuron = new Neuron("input");
			inputLayer.add(neuron);
		}
		
		//Creating Biases
		Neuron neuron = new Neuron("inputBias", true);
		inputLayer.add(neuron);
		neuron = new Neuron("hiddenBias", true);
		hiddenLayer1.add(neuron);
	
		//Creating Connections
		createConnections();
		
		
	}
	
	//Controller method
	public void runNetwork(){
		int runCount = 0;
		List<char[][]> trainingPatterns = FileReader.makeTrainingPatterns();
		List<float[]> trainingDataSet = FileReader.makeDataSet_all(trainingPatterns);
		while (runCount < iterationNumber){
			for ( int x = 0; x < trainingDataSet.size(); x++){
				setInput(trainingDataSet.get(x));
				setExpectedOutput(x,trainingDataSet.size());
				forwardPropagate();
				setActualOutput();
				error.calculateError();
				backPropagate(x);
			}
			runCount++;
			//System.out.println(error.calculateRMS());
			
			/*if(runCount %12 == 0){
				System.out.println(error.calculateRMS());
			}*/
			if (error.calculateRMS() < RMSvalue){
				System.out.println("RMS Goal Reached");
				System.out.println("Ending RMS for training is: " + error.calculateRMS());
				break;
			}
			if (!(runCount < iterationNumber)){
				System.out.println("Max iterations reached");
				System.out.println("Ending RMS for training is: " + error.calculateRMS());
			}
			error.resetValues();
		}
		
		System.out.println("Number of Iterations used " + runCount/12);
		
		
	}

	//createConnections - method to create a connection between every neuron (layer to layer)
	public void createConnections(){
		for ( int i = 0; i < hiddenLayerSize; i++){
			for ( int j = 0; j < outputLayerSize; j++){
				Connection connection = new Connection(hiddenLayer1.get(i), outputLayer.get(j));	//create all connections between hidden and output layers and 
				hiddenLayer1.get(i).addOutgoingConnection(connection);								//add them to the lists in the relevant neurons.
				outputLayer.get(j).addIncomingConnection(connection);
				
			}
			for ( int j = 0; j < inputLayerSize; j++){
				Connection connection = new Connection(inputLayer.get(j), hiddenLayer1.get(i));		//create all connections between hidden and input layers and
				inputLayer.get(j).addOutgoingConnection(connection);								//add them to the lists in the relevant neurons.
				hiddenLayer1.get(i).addIncomingConnection(connection);
			}
			
		}
	}
	
	//forwardPropagate - forward propagate the neural network and get outputs.
	public void forwardPropagate(){
		for(int i = 0; i < hiddenLayer1.size(); i++){
			float sum = 0;																																	//for all neurons in the hidden layer
			for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){																			//for all incoming connections to the hidden neuron
				sum += (hiddenLayer1.get(i).incomingConnections.get(j).getFrom().getOutput() * hiddenLayer1.get(i).incomingConnections.get(j).getWeight());			//get the product of outputOfConnectionNeuron and weightOfConnection
			}
			hiddenLayer1.get(i).setInput(sum);
		}
		
		for(int i = 0; i < outputLayer.size(); i++){
			float sum = 0;																																	//for all neurons in the output layer
			for(int j = 0; j < outputLayer.get(i).incomingConnections.size(); j++){																				//for all incoming connections to the output neuron
				sum += outputLayer.get(i).incomingConnections.get(j).getFrom().getOutput() * outputLayer.get(i).incomingConnections.get(j).getWeight();				//get the product of outputOfConnectionNeuron and weightOfConnection
			}																		
			outputLayer.get(i).setInput(sum);
		}
		
	}
	
	//backPropagate
	public void backPropagate(int position){
		for(int i = 0; i < outputLayer.size();i++){											
			if (i == position){																								
				outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)1.0));								//Calculate Delta of Output Neuron using desired = 1
			}
			else{
				outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)0.01));									//Calculate Delta of Output Neuron using desired= 0
			}
			for(int j = 0; j < outputLayer.get(i).incomingConnections.size(); j++){			//For all connections to output neuron
				calculateWeight(outputLayer.get(i).incomingConnections.get(j));					//Calculate new weight (calculateWeight)	
			}
		}
		for (int i = 0; i < hiddenLayer1.size(); i++){											//For all hidden neurons
			hiddenLayer1.get(i).setDelta(calculateHiddenDelta(hiddenLayer1.get(i)));				//Calculate Delta of Hidden Neuron
			for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){					//For all connections to hidden neuron
				calculateWeight(hiddenLayer1.get(i).incomingConnections.get(j));							//Calculate new weight
			}
		}	
	}
	
	
	//HELPERS///////////////////
	
	//Calculates the sum of all (weights*delta) of connections from a given neuron.
	public float getSumOfDeltaWeights(Neuron a){
		float sum = 0;														
		for (int i = 0; i < a.outgoingConnections.size(); i++){				
			sum += (a.outgoingConnections.get(i).getWeight() * a.outgoingConnections.get(i).getTo().getDelta());
		}
		return sum;
	}
	
	//caclulateOutputDelta - Calculates the delta of an output neuron
	public float calculateOutputDelta(Neuron neuron, float desiredOutput){
		float nout = neuron.getOutput();											
		float delta = nout*(1-nout)*(desiredOutput-nout);	
		return delta;
	}
	
	//calculateHiddenDelta - Calculates the Delta of a hidden neuron
	public float calculateHiddenDelta(Neuron neuron){
		float nout = neuron.getOutput();										
		float delta = nout*(1-nout)*getSumOfDeltaWeights(neuron);			
		return delta;
	}
	
	//calculateWeight - Calculates and assigns the new weight
	public float calculateWeight(Connection connection){					
		float oldWeight = connection.getWeight();		
		float neuronOutput = connection.getFrom().getOutput();				
		float delta = connection.getTo().getDelta();
		float weight = oldWeight + learningRate * neuronOutput * delta;		
		connection.setWeight(weight);
		return weight;										
	}
	
	//showOutputData - shows the current outputs of the neurons in the output layer
	public void showOutputData(){
		float[] outputData = getOutputData();		//calls function getOutputData
		for (int i = 0;i < outputData.length; i++){
			System.out.print(outputData[i] + " ");
		}
		System.out.println();	
	}

	//printInput - prints the current input of the inputLayer
	public void printInput(){
		for(int i = 0; i < inputLayer.size(); i++){
			System.out.print(inputLayer.get(i).getOutput() + " ");
		}
	}
	
	
	//GETTERS///////////////
	
	//gets output data
	public float[] getOutputData(){
		float[] outputData = new float[outputLayer.size()];
		for (int i = 0 ; i < outputLayer.size(); i++){
			outputData[i]=outputLayer.get(i).getOutput();
		}
		return outputData;
	}
	
	
	//SETTERS////////////////////
	
	//setInput - set the input of the neurons in the input layer
	public void setInput(float[] input){
		for(int i = 0; i < input.length; i++){
			inputLayer.get(i).setOutput(input[i]);
		}
	}
	//sets desired output for a training example in ErrorCalc
	public void setExpectedOutput(int position, int length){
		float[] expectedOutput = new float[length];
		for(int i = 0; i < length; i++){
			if (i == position){
				expectedOutput[i] = 1;
			}
			else {
				expectedOutput[i] = 0;
			}
		}
		error.setExpected(expectedOutput);
	}
	//sets the actual output of a training example in ErrorCalc
	public void setActualOutput(){
		float[] outputData = new float[outputLayer.size()];
		for (int i = 0 ; i < outputLayer.size(); i++){
			outputData[i]=outputLayer.get(i).getOutput();
		}
		error.setActual(outputData);
	}
	public void setLearningRate(float rate){
		this.learningRate = rate;
	}
	public void setRMS(float RMS){
		this.RMSvalue = RMS;
	}
	public void setMaxIterations(int max){
		this.iterationNumber = max*12;
	}
}


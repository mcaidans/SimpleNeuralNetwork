package ai_assignment2;
import java.util.*;

public class Network {
	
	// class variables
	protected static List<Neuron> inputLayer = new ArrayList<>();
	protected List<Neuron> hiddenLayer1 = new ArrayList<>();
	protected List<Neuron> outputLayer = new ArrayList<>();
	public float learningRate = (float).1;
	//private boolean goalState = false;
	public ErrorCalc error;
	
	private int inputLayerSize;
	private int hiddenLayerSize;
	private int outputLayerSize;
	
	
	public Network(int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		this.inputLayerSize = inputLayerSize;
		this.hiddenLayerSize = hiddenLayerSize;
		this.outputLayerSize = outputLayerSize;
		
		error = new ErrorCalc();
		
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
		Neuron neuron = new Neuron("inputBias", true);
		inputLayer.add(neuron);
		neuron = new Neuron("hiddenBias", true);
		hiddenLayer1.add(neuron);
		createConnections();
		
		
	}
	
	public void runNetwork(){
		int runCount = 0;
		List<char[][]> trainingPatterns = FileReader.makeTrainingPatterns();
		List<float[]> trainingDataSet = FileReader.makeDataSet_all(trainingPatterns);
		//float[] trainingImage1 = trainingDataSet.get(0);
		//float[] trainingImage2 = trainingDataSet.get(1);
		//List<float[]> trainingDataSet2 = new ArrayList<>();
		//trainingDataSet2.add(trainingImage1);
		//trainingDataSet2.add(trainingImage2);
		while (runCount < 1200000){
			for ( int x = 0; x < trainingDataSet.size(); x++){
				setInput(trainingDataSet.get(x));
				setExpectedOutput(x,trainingDataSet.size());
				forwardPropagate();
				setActualOutput();
				error.calculateError();
				backPropagate(x);
				//forwardPropagate();
				//if(runCount%1==0){
					//showOutputData();
					//}
				
				/*
				for(int i = 0; i < outputLayer.size();i++){											//For all output neurons
					if (i == x){
						outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)1.0));								//Calculate Delta of Output Neuron using desired = 1
					}
					else{
						outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)0.0));									//Calculate Delta of Output Neuron using desired= 0
						}
					for(int j = 0; j < outputLayer.get(i).incomingConnections.size(); j++){			//For all connections to output neuron
						calculateWeight(outputLayer.get(i).incomingConnections.get(j));					//Calculate new weight
						
					}
				}
				//System.out.println();
				
				for (int i = 0; i < hiddenLayer1.size(); i++){				//For all hidden neurons
					hiddenLayer1.get(i).setDelta(calculateHiddenDelta(hiddenLayer1.get(i)));									//Calculate Delta of Hidden Neuron
					for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){				//For all connections to hidden neuron
						calculateWeight(hiddenLayer1.get(i).incomingConnections.get(j));						//Calculate new weight
					}
				}*/
				//runCount++;
			}
			runCount++;
			System.out.println(error.calculateRMS());
			if (error.calculateRMS() < 0.0075){
				System.out.println("RMS Goal Reached");
				break;
			}
			error.resetValues();
		}
		
	}
	public void showOutputData(){
		//System.out.println();
		//System.out.println("Output after forward propagate:");
		float[] outputDat = getOutputData();
		for (int i = 0;i < outputDat.length; i++){
			System.out.print(outputDat[i] + " ");
		}
		System.out.println();	
	}
	
	public void printNeurons(){
		System.out.print("Input Layer: ");
		for ( int i = 0; i < inputLayerSize; i++){
			System.out.print(inputLayer.get(i).getOutput());
		}
		System.out.println();
		System.out.print("Hidden Layer: ");
		for ( int i = 0; i < hiddenLayerSize; i++){
			System.out.print(hiddenLayer1.get(i).getOutput());
		}
		System.out.println();
		System.out.print("Output Layer: ");
		for ( int i = 0; i < outputLayerSize; i++){
			System.out.print(outputLayer.get(i).getOutput());
		}
		
	}
	
	public void createConnections(){
		for ( int i = 0; i < hiddenLayerSize; i++){
			for ( int j = 0; j < outputLayerSize; j++){
				Connection connection = new Connection(hiddenLayer1.get(i), outputLayer.get(j));
				hiddenLayer1.get(i).addOutgoingConnection(connection);
				outputLayer.get(j).addIncomingConnection(connection);
				
			}
			for ( int j = 0; j < inputLayerSize; j++){
				Connection connection = new Connection(inputLayer.get(j), hiddenLayer1.get(i));
				inputLayer.get(j).addOutgoingConnection(connection);
				hiddenLayer1.get(i).addIncomingConnection(connection);
			}
			
		}
	}
	
	
	public void setInput(float[] input){
		for(int i = 0; i < input.length; i++){
			inputLayer.get(i).setOutput(input[i]);
		}
	}
	
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
	public void setActualOutput(){
		float[] outputData = new float[outputLayer.size()];
		for (int i = 0 ; i < outputLayer.size(); i++){
			outputData[i]=outputLayer.get(i).getOutput();
		}
		error.setActual(outputData);
	}
	
	
/*
	public void enterData(float[] dataset){
		for (int i = 0 ; i < dataset.length; i++){
			inputLayer.get(i).input(dataset[i]);
		}
	}*/
	public float[] getOutputData(){
		float[] outputData = new float[outputLayer.size()];
		for (int i = 0 ; i < outputLayer.size(); i++){
			outputData[i]=outputLayer.get(i).getOutput();
		}
		return outputData;
	}
	
	
	public void printInput(){
		for(int i = 0; i < inputLayer.size(); i++){
			System.out.print(inputLayer.get(i).getOutput() + " ");
		}
	}
	
	public void forwardPropagate(){
		for(int i = 0; i < hiddenLayer1.size(); i++){
			float sum = 0;
			for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){
				sum += (hiddenLayer1.get(i).incomingConnections.get(j).getFrom().getOutput() * hiddenLayer1.get(i).incomingConnections.get(j).getWeight());
			}
			hiddenLayer1.get(i).setInput(sum);
		}
		
		for(int i = 0; i < outputLayer.size(); i++){
			float sum = 0;
			for(int j = 0; j < outputLayer.get(i).incomingConnections.size(); j++){
				sum += outputLayer.get(i).incomingConnections.get(j).getFrom().getOutput() * outputLayer.get(i).incomingConnections.get(j).getWeight();
			}
			outputLayer.get(i).setInput(sum);
		}
		
	}
	
	/*
	public float getWeightedOutput(Neuron a, Neuron b){
		float weight = connection.getWeight();
		float output = b.getDelta()*weight;
		return output;
	}*/
	
	
	//Calculates the sum of all (weights*delta) of connections from a given neuron.
	public float getSumOfDeltaWeights(Neuron a){
		float sum = 0;														//Initialize sum
		for (int i = 0; i < a.outgoingConnections.size(); i++){				
			sum += (a.outgoingConnections.get(i).getWeight() * a.outgoingConnections.get(i).getTo().getDelta());
		}
		return sum;
	}
	
	//Calculates the delta of an output neuron
	public float calculateOutputDelta(Neuron neuron, float desiredOutput){
		float nout = neuron.getOutput();											//Get output of Neuron
		float delta = nout*(1-nout)*(desiredOutput-nout);
		//System.out.print("Desired Output: " + desiredOutput);
		//System.out.print(" Actual: " + nout);
		//System.out.println(" Delta: " + delta);		
		return delta;
	}
	
	//Calculates the Delta of a hidden neuron
	public float calculateHiddenDelta(Neuron neuron){
		float nout = neuron.getOutput();											//Get output of Neuron
		float delta = nout*(1-nout)*getSumOfDeltaWeights(neuron);			//Calculate Delta
		return delta;
	}
	
	//Calculates and assigns the new weight
	public float calculateWeight(Connection connection){					
		float oldWeight = connection.getWeight();							//Get old weight
		float neuronOutput = connection.getFrom().getOutput();				//Get output of Neuron
		float delta = connection.getTo().getDelta();
		float weight = oldWeight + learningRate * neuronOutput * delta;		//Get New Weight
		connection.setWeight(weight);
		return weight;										//Assign New Weight
	}
	
	public void backPropagate(int position){
			for(int i = 0; i < outputLayer.size();i++){											//For all output neurons
				if (i == position){
					outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)1.0));								//Calculate Delta of Output Neuron using desired = 1
				}
				else{
					outputLayer.get(i).setDelta(calculateOutputDelta(outputLayer.get(i), (float)0.01));									//Calculate Delta of Output Neuron using desired= 0
					}
				for(int j = 0; j < outputLayer.get(i).incomingConnections.size(); j++){			//For all connections to output neuron
					calculateWeight(outputLayer.get(i).incomingConnections.get(j));					//Calculate new weight
					
				}
			}
			//System.out.println();
			
			for (int i = 0; i < hiddenLayer1.size(); i++){				//For all hidden neurons
				hiddenLayer1.get(i).setDelta(calculateHiddenDelta(hiddenLayer1.get(i)));									//Calculate Delta of Hidden Neuron
				for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){				//For all connections to hidden neuron
					calculateWeight(hiddenLayer1.get(i).incomingConnections.get(j));						//Calculate new weight
				}
			}	
	}
}


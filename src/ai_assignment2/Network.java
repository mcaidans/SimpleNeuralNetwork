package ai_assignment2;
import java.util.*;

public class Network {
	
	// class variables
	protected static List<Neuron> inputLayer = new ArrayList<>();
	protected List<Neuron> hiddenLayer1 = new ArrayList<>();
	protected List<Neuron> outputLayer = new ArrayList<>();
	public float learningRate = (float)0.12;
	
	private int inputLayerSize;
	private int hiddenLayerSize;
	private int outputLayerSize;
	
	
	public Network(int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		this.inputLayerSize = inputLayerSize;
		this.hiddenLayerSize = hiddenLayerSize;
		this.outputLayerSize = outputLayerSize;
		
		
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
		createConnections();
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
	
	public void forwadPropagate(){
		for(int i = 0; i < hiddenLayer1.size(); i++){
			float sum = 0;
			for(int j = 0; j < hiddenLayer1.get(i).incomingConnections.size(); j++){
				sum += (hiddenLayer1.get(i).incomingConnections.get(j).getFrom().getOutput() * hiddenLayer1.get(i).incomingConnections.get(j).getWeight());
				//System.out.print(hiddenLayer1.get(i).incomingConnections.get(j).getFrom().getOutput() + " ");
				//System.out.print(" I:"+  i + " J:" + j);
			}
			System.out.print(sum);
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
	}
	
	
	//Calculates the sum of all (weights*delta) of connections from a given neuron.
	public float getSumOfDeltaWeights(Neuron a){
		float sum = 0;														//Initialize sum
		for (int i = 0; i < a.getConnections().size(); i++){				
			sum += (a.connections[i].weight	* a.delta);						//Add to total sum
		}
		return sum;
	}
	
	//Calculates the delta of an output neuron
	public float calculateOutputDelta(OutputNeuron neuron, float desiredOutput){
		float nout = neuron.output;											//Get output of Neuron
		float delta = nout*(1-nout)*(desiredOutput-nout);					//Calculate Delta
		return delta;
	}
	
	//Calculates the Delta of a hidden neuron
	public float calculateHiddenDelta(Neuron neuron, float desiredOutput){
		float nout = neuron.output;											//Get output of Neuron
		float delta = nout*(1-nout)*getSumOfDeltaWeights(neuron);			//Calculate Delta
		return delta;
	}
	
	//Calculates and assigns the new weight
	public void calculateWeight(Connection connection){					
		float oldWeight = connection.getWeight();							//Get old weight
		float neuronOutput = connection.getFrom().getOutput();				//Get output of Neuron
		float delta = connection.getTo().getDelta();						//Get delta of Neuron
		float weight = oldWeight + learningRate * neuronOutput * delta;		//Get New Weight
		connection.setWeight(weight);										//Assign New Weight
	}
	
	public void backPropagate(){
		//initial feed of inputs
		Boolean goalState = false;
		while(!goalState){
			for(int i = 0; i < outputLayer.size();i++){					//For all output neurons
				calculateOutputDelta(outputLayer.get(i), 1);			//Calculate Delta of Output Neuron
				for(int j = 0; j < connectionsTo.size();i++){			//For all connections to output neuron
					calculateWeight(connectionsTo[j]);					//Calculate new weight
				}
			}
			
			for (int i = 0; i < hiddenLayer1.size(); i++){				//For all hidden neurons
				calculateHiddenDelta();									//Calculate Delta of Hidden Neuron
				for(int j = 0; j < connectionsTo.size(); i++){				//For all connections to hidden neuron
					calculateWeight(connectionsTo[j]);						//Calculate new weight
				}
			}
		}
		
	
	}*/
}

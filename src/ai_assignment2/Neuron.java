package ai_assignment2;

import java.util.*;

public class Neuron {
	//VARIABLES
	protected float output;
	protected float input;
	public String name;
	private float delta;
	protected Boolean bias = false;
	protected List<Connection> outgoingConnections = new ArrayList<>();
	protected List<Connection> incomingConnections = new ArrayList<>();
	
	//Constructors
	public Neuron(String name){
		this.input = (float)0; 
		this.output = (float)0;
		this.name = name;
	}
	public Neuron(float input){
		this.input = input;
		this.output = (float)0;
	}
	public Neuron(String name, Boolean bias){
		this.input = (float)0; 
		this.output = (float)1;
		this.name = name;
		this.bias = bias;
	}

	//runFunction - Puts input through eulers algorithm and sets result to output
	public float runFunction(float input){
		float output = 1.0f / (1.0f + ((float)Math.exp(-input)));  //MAth.exp is eulers algorithm
		return output;
	}
	//NOT SURE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addToInput(float value) {
		this.input = input + value;
	}
	
	
	//GETTERS//////
	
	//Gets neurons input
	public float getInput() {	
		return input;
	}
	//Gets neurons delta
	public float getDelta(){
		return delta;
	}
	//Gets neurons output
	public float getOutput() {
		if(!bias){
			return output;
		}
		else return 1;
	}
	//Gets list of incoming connections
	public List<Connection> getIncomingConnections() {
		return incomingConnections;
	}
	//Gets list of outgoing connections
	public List<Connection> getOutgoingConnections() {
		return outgoingConnections;
	}
	
	//SETTERS/////////
	
	//Sets input 
	public void setInput(float input) {
		if (!bias){
			this.input = input;
			this.output = runFunction(input);		//Calls runFuction using input
		}
	}
	//Sets Delta
		public void setDelta(float delta){
			this.delta = delta;
		}
	//Sets output
	public void setOutput(float output) {
		if(!bias){
			this.output = output;
		}
	}
	//Adds connection to list of outgoing connections
	public void addOutgoingConnection(Connection connection){
		outgoingConnections.add(connection);
	}
	//Adds connection to list of incoming connections
	public void addIncomingConnection(Connection connection){
		incomingConnections.add(connection);
	}
}


	
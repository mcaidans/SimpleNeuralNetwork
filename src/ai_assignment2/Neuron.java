package ai_assignment2;

import java.util.*;

public class Neuron {
	// class variables
	protected float output;
	protected float input;
	public String name;
	protected List<Connection> outgoingConnections = new ArrayList<>();
	protected List<Connection> incomingConnections = new ArrayList<>();
	
	// Constructors
	public Neuron(String name){
		//this.connections = new ArrayList<>();
		this.input = (float)0; 
		this.output = (float)0;
		this.name = name;
	}
	
	public Neuron(float input){
		this.input = input;
		this.output = (float)0;
		//connections = new ArrayList<>();
	}
	
	// methods
	public float runFunction(float input){
		float output = 1.0f / (1.0f + ((float)Math.exp(-input)));  //MAth.exp is eulers algorithm
		return output;
	}
	/*
	public float calculateOutput(){
		float sum = 0;
		for(int i = 0; i < connectionsTo.size(); i++){
			sum += (connectionsTo.get(i).weight + connectionsTo.get(i).getFrom();
		}
		return runFunction(sum);
	}
*/
	
	public void addOutgoingConnection(Connection connection){
		outgoingConnections.add(connection);
	}
	
	public void addIncomingConnection(Connection connection){
		incomingConnections.add(connection);
	}
	
	public float getOutput() {
		return output;
	}

	public void setOutput(float output) {
		this.output = output;
	}

	public float getInput() {
		return input;
	}

	public void setInput(float input) {
		this.input = input;
		this.output = runFunction(input);
	}
	public void addToInput(float value) {
		this.input = input + value;
	}
	public List<Connection> getIncomingConnections() {
		return incomingConnections;
	}
	public List<Connection> getOutgoingConnections() {
		return outgoingConnections;
	}
}


	
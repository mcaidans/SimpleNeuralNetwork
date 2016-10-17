package ai_assignment2;

public class Connection {
	//VARIABLES
	private Neuron from;
	private Neuron to;
	private float weight;
	private float min;
	private float max;
	private float range;
	
	//Constructor - sets all values including random weights
	public Connection(Neuron a, Neuron b) {
		this.setFrom(a);
		this.setTo(b);
		this.max = (float) 0.5;
		this.min = (float) -0.5;
		this.range = Math.abs(this.max-this.min);
		this.setWeight((float) (Math.random()*this.range)+this.min);// this gives me a range -0.5 to +0.5
	}
	
	////NOT SURE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void runConnection(){
		to.addToInput(from.getOutput()*weight);
	}
	
	//GETTERS
	//Gets neuron connection is from
	public Neuron getFrom() {
		return from;
	}	
	//Gets neuron connection is to
	public Neuron getTo() {
		return to;
	}
	//Gets weight
	public float getWeight() {
		return weight;
	}
	
	//SETTERS
	//sets neuron connection is from
		public void setFrom(Neuron from) {
			this.from = from;
		}
	//Sets neuron connection is to
	public void setTo(Neuron to) {
		this.to = to;
	}
	//Sets weight
	public void setWeight(float weight) {
		this.weight = weight;
	}
}

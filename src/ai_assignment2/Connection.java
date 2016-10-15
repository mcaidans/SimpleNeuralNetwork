package ai_assignment2;

public class Connection {
	// class variables
	private Neuron from;
	private Neuron to;
	private float weight;
	private float min;
	private float max;
	private float range;
	
	public Connection(Neuron a, Neuron b) {
		this.setFrom(a);
		this.setTo(b);
		this.max = (float) 0.5;
		this.min = (float) -0.5;
		this.range = Math.abs(this.max-this.min);
		this.setWeight((float) (Math.random()*this.range)+this.min);// this gives me a range -0.5 to +0.5
	}

	public Neuron getFrom() {
		return from;
	}

	public void setFrom(Neuron from) {
		this.from = from;
	}

	public Neuron getTo() {
		return to;
	}

	public void setTo(Neuron to) {
		this.to = to;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void runConnection(){
		to.addToInput(from.getOutput()*weight);
	}
	

}

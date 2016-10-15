package ai_assignment2;

public class InputNeuron extends Neuron{
	public InputNeuron(String name) {
        super(name);
    }
	public InputNeuron(float input) {
        super(input);
	}

    public void input(float d) {
    	this.input = d;
        this.output = input;
    }
    
}



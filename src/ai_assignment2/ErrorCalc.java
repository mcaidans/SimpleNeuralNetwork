package ai_assignment2;

public class ErrorCalc {
	private double totalError;
	private int noOfErrors;
	private float[] expectedOutput;
	private float[] actualOutput;
	
	
	public void setExpected(float[] expected){
		this.expectedOutput = expected;
	}
	
	public void setActual(float[] actual){
		this.actualOutput = actual;
	}
	
	public void calculateError(){
		for(int i = 0; i < expectedOutput[i]; i++){
			double difference = expectedOutput[i] - actualOutput[i];
			totalError += difference * difference;
			noOfErrors += actualOutput.length;
		}
	}
	
	public double calculateRMS(){
		double err = Math.sqrt(totalError / noOfErrors);
		return err;
	}
	
	public void resetValues(){
		totalError = 0;
		noOfErrors = 0;
	}
	
	

}

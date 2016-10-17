package ai_assignment2;

public class ErrorCalc {
	private double totalError;			//Holds Summed error for all outputs
	private int noOfErrors;				//Holds the number of errors for averaging
	private float[] expectedOutput;		//Desired Output
	private float[] actualOutput;		//Actual Output
	
	//Calculates the error for one output
	public void calculateError(){
		for(int i = 0; i < expectedOutput[i]; i++){
			double difference = expectedOutput[i] - actualOutput[i];		//calculates difference between desired and actual output
			totalError += difference * difference;							
			noOfErrors += actualOutput.length;								
		}
	}
	
	//Calculates the RMS error
	public double calculateRMS(){
		double err = Math.sqrt(totalError / noOfErrors);
		return err;
	}
	
	//Resets the values used to calculate the RMS Error
	public void resetValues(){
		totalError = 0;
		noOfErrors = 0;
	}
	
	//SETTERS/////
	
	//Sets Desired Output
		public void setExpected(float[] expected){
			this.expectedOutput = expected;
		}
		
		//Sets actual output
		public void setActual(float[] actual){
			this.actualOutput = actual;
		}
}

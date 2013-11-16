package naive_bayes;

import utilities.GameData;
import utilities.HypothesisTest;
import utilities.helper_classes.Utilities;

public class NBMain {
	
	private static final int NUM_GAMES = 10;
	private static final double TRAIN_FRACTION = 0.7;

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		
		System.out.println("I am in");
		GameData myGameData = new GameData(NUM_GAMES, TRAIN_FRACTION);
		System.out.println("Just got game data");
		NBTrain trainingModel = new NBTrain();
		System.out.println("Created my model");
		long[][] frequencyTable = trainingModel.train(myGameData);
		System.out.println("Just finished training!");
		NBHypothesis myHypothesis = new NBHypothesis( frequencyTable, 
				trainingModel.getNumNonExpertMoves(), trainingModel.getNumExpertMoves() );
		System.out.println("About to test the hypothesis");
		HypothesisTest.test(myHypothesis, myGameData);
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + Utilities.msToString(endTime - startTime));
	}

}

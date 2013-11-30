package naive_bayes;

import utilities.GameData;
import utilities.HypothesisTest;
import utilities.helper_classes.Utilities;

public class NBMain {
	
	private static final boolean RUN_FROM_COMMAND_LINE = true;
	
	/* These values are used if RUN_FROM_COMMAND_LINE is false. They specify the size
	 * of the example set for the start round and the end round, and the amount by which
	 * to increment the size of the example set. E.g. if {START_SIZE, END_SIZE, INCREMENT} 
	 * == {10, 50, 10}, then the program will train on example sets of size 10, 20, ... 50. 
	 */
	private static final int START_SIZE = 10;
	private static final int END_SIZE = 50;
	private static final int INCREMENT = 10;

	private static final double TRAIN_FRACTION = 0.7;
	
	private static void trainAndTest(int numGames){
		System.out.println("-------------------------------");
		System.out.println("------START OF ROUND (" + (numGames) + ")------");
		System.out.println("-------------------------------\n");
		final long startTime = System.currentTimeMillis();
		
		System.out.println("Training and testing on " + numGames + " games...");
		
		GameData myGameData = new GameData(numGames, TRAIN_FRACTION);
		System.out.println("Finished fetching game data");
		
		NBTrain trainingModel = new NBTrain();
		System.out.println("Created the NB model");

		long[][] frequencyTable = trainingModel.train(myGameData);
		System.out.println("Just finished training!");
		
		System.out.println("About to evaluate model: creating a hypothesis...");
		NBHypothesis myHypothesis = new NBHypothesis( frequencyTable, 
				trainingModel.getNumNonExpertMoves(), trainingModel.getNumExpertMoves() );
		
		System.out.println("\nTesting hypothesis on TEST set...");
		myGameData.setMode(GameData.Mode.TEST);
		HypothesisTest.test(myHypothesis, myGameData);
		
		System.out.println("\nTesting hypothesis on TRAIN set...");
		myGameData.setMode(GameData.Mode.TRAIN);
		HypothesisTest.test(myHypothesis, myGameData);
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Round execution time: " + Utilities.msToString(endTime - startTime));
		System.out.println("\n------------------------");
		System.out.println("------END OF ROUND------");
		System.out.println("------------------------\n\n");
	}

	// Instructions to redirect console output to file:
	// right click NBMain.java -> Run as -> Run Configurations... -> select "Common" tab
	// 						   -> check "File" under "Standard Input and Output"
	//                         -> enter destination file name :D
	
	public static void main(String[] args) {
		final long totalStartTime = System.currentTimeMillis();
		
		if (RUN_FROM_COMMAND_LINE && args.length != 3){
			System.out.println("Error in NBMain: expects 3 arguments...\n"
					+ "Arguments: <min-example-set-size> <max-example-set-size> <increment> \n"
					+ "e.g. to train on example sets of size 10, 15, and 20, use arguments '10 20 5' \n"
					+ "e.g. to train on a single example set of 20 games, use arguments '20 20 1'");
			return;
		}
		
		int startSize = RUN_FROM_COMMAND_LINE ? Integer.parseInt(args[0]) : START_SIZE;
		int endSize = RUN_FROM_COMMAND_LINE ? Integer.parseInt(args[1]) : END_SIZE;
		int increment = RUN_FROM_COMMAND_LINE ? Integer.parseInt(args[2]) : INCREMENT;

		for (int x = startSize; x <= endSize; x += increment) {
			trainAndTest(x);
		}
		
		final long totalEndTime = System.currentTimeMillis();
		System.out.println("\n\nTotal execution time (from process running to termination): " 
					+ Utilities.msToString(totalEndTime - totalStartTime));
		
	}

}

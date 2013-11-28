package montecarlo;

import java.util.Random;

import utilities.helper_classes.ArimaaState;
import arimaa3.ArimaaEngine;
import arimaa3.ArimaaMove;
import arimaa3.MoveList;

public class ReflexAgent extends AbstractAgent {

	public ReflexAgent(double[] weights, boolean training) {
		super(weights, training);
	}

	@Override
	public ArimaaMove selectMove(final ArimaaState arimaaState, ArimaaEngine engine) {
		MoveList allPossibleMoves = engine.genRootMoves(arimaaState.getCurr());
		ArimaaMove bestMove = trainRandomly(allPossibleMoves.move_list);
		if (bestMove != null)
			return bestMove;
		
		double score = Double.NEGATIVE_INFINITY; //we want to maximize this value
		
		for (ArimaaMove possibleMove : allPossibleMoves){
			double evaluation = Utilities.logLinearEvaluation(arimaaState, possibleMove, weights);
			if (evaluation > score){
				score = evaluation;
				bestMove = possibleMove;
			}
		}
		
		return bestMove;
	}

}

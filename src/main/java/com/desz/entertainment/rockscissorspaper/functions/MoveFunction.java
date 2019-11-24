package com.desz.entertainment.rockscissorspaper.functions;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.desz.entertainment.rockscissorspaper.functions.Move.*;

public interface MoveFunction {

	static Move comparisonHandler(MoveHandler m1, MoveHandler m2) {
		switch (m1.getMove()) {
		case ROCK:
			return m2.getMove().equals(SCISSORS) ? ROCK : m2.getMove();
		case SCISSORS:
			return m2.getMove().equals(PAPER) ? SCISSORS : m2.getMove();
		case PAPER:
			return m2.getMove().equals(ROCK) ? PAPER : m2.getMove();
		default:
			break;

		}
		return null;
	}

	BiFunction<MoveHandler, MoveHandler, Move> compareMove = (m1, m2) -> {
		
		return m2.getMove().equals(m1.getMove()) ? DRAW : comparisonHandler(m1, m2);

	};

	Supplier<Move> getRandomMove = () -> {
		final Move[] moves = Move.values();
		Random random = new Random();
		return moves[random.nextInt(moves.length)];
	};

}

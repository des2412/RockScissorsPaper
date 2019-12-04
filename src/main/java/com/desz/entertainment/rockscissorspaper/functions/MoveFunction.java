package com.desz.entertainment.rockscissorspaper.functions;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.PAPER;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.Move.SCISSORS;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

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
	
	static Move comparisonHandler2(MoveHandler m1, MoveHandler m2) {
		final Move mv = m2.getMove();
		Move result = DRAW;
		switch (m1.getMove()) {
		case ROCK:
			result = mv.equals(SCISSORS) ? ROCK : result;
			break;
		case SCISSORS:
			result = mv.equals(PAPER) ? SCISSORS : result;
			break;
		case PAPER:
			result = mv.equals(ROCK) ? PAPER : result;
			break;
		default:
			break;

		}
		return result;
	}

	BiFunction<MoveHandler, MoveHandler, Move> compareMove = (m1, m2) -> {
		return m2.getMove().equals(m1.getMove()) ? DRAW : comparisonHandler(m1, m2);

	};

	/**
	 * Supply a randomly selected Move that is NOT a DRAW.
	 */
	Supplier<Move> supplyRandomMove = () -> {
		final List<Move> moves = of(Move.values()).filter(mv -> !mv.equals(DRAW)).collect(toList());
		return moves.get(new Random().nextInt(moves.size()));
	};

}

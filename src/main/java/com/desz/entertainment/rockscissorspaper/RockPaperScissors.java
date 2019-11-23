/**
 * 
 */
package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.getRandomMove;

import java.util.Arrays;
import java.util.List;

import com.desz.entertainment.rockscissorspaper.functions.Move;
import com.desz.entertainment.rockscissorspaper.functions.MoveLeger;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

/**
 * @author des
 *
 */
//@Slf4j
public class RockPaperScissors {

	String playGame(List<GamePlayer> players) {
		Move m = compareMove.apply(players.get(0).getMove(), players.get(1).getMove());
		return m.equals(DRAW) ? "Result is draw" : "winner is" + m.toString();

	}

	/**
	 * 
	 * @return the Move.
	 */
	Move getRandomMove() {
		final Move randomMove = getRandomMove.get();
		return randomMove.equals(DRAW) ? getRandomMove() : randomMove;

	}

	public static void main(String args[]) {

		RockPaperScissors o = new RockPaperScissors();
		// Get Random move
		Move randomMove = o.getRandomMove();

		GamePlayer random = GamePlayer.builder().id("random").isRandom(true).move(new MoveLeger(o.getRandomMove()))
				.build();

		GamePlayer rocker = GamePlayer.builder().id("rocker").isRandom(false).move(new MoveLeger(ROCK)).build();

		o.playGame(Arrays.asList(new GamePlayer[] { random, rocker }));

	}

}

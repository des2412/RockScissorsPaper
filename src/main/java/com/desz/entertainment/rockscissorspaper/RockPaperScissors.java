/**
 * 
 */
package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.getRandomMove;
import static java.util.stream.Collectors.toList;

import static java.util.Arrays.asList;
import java.util.List;
import java.util.stream.Stream;

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
		return m.equals(DRAW) ? "draw" : "winner:" + m.toString();

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

		List<GamePlayer> randomPlayers = Stream.generate(
				() -> GamePlayer.builder().id("random").isRandom(true).move(new MoveLeger(o.getRandomMove())).build())
				.limit(100).collect(toList());

		final GamePlayer rocker = GamePlayer.builder().id("rocker").isRandom(false).move(new MoveLeger(ROCK)).build();

		StringBuilder sb = new StringBuilder();
		for (GamePlayer player : randomPlayers) {
			sb.append("Random player plays:" + player.getMove().getMove().name() + " - ");
			sb.append(" " + o.playGame(asList(new GamePlayer[] { player, rocker })) + System.lineSeparator());
		}
		System.out.print(sb.toString());

	}

}

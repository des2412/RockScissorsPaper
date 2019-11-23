/**
 * 
 */
package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.getRandomMove;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.rightPad;

import java.util.List;
import java.util.stream.Stream;

import com.desz.entertainment.rockscissorspaper.functions.Move;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author des
 * 
 * the min application class.
 *
 */
@Slf4j
public class RockPaperScissors {

	/**
	 * 
	 * @param players
	 * @return the results for a game between two players.
	 */
	String playGame(List<GamePlayer> players) {
		Move m = compareMove.apply(players.get(0).getMoveHandler(), players.get(1).getMoveHandler());
		return m.equals(DRAW) ? "Draw" : "Winner:" + m.toString();

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

		List<GamePlayer> randomPlayers = Stream
				.generate(() -> GamePlayer.builder().id("random")
						.moveHandler(MoveHandler.builder().move(o.getRandomMove()).build()).build())
				.limit(100).collect(toList());

		final GamePlayer rocker = GamePlayer.builder().id("rocker")
				.moveHandler(MoveHandler.builder().move(ROCK).build()).build();

		final StringBuilder sb = new StringBuilder();

		sb.append(rightPad("Random Player", 25));
		sb.append(leftPad("Result", 5) + System.lineSeparator());
		sb.append(repeat("-", 38) + System.lineSeparator());

		for (GamePlayer player : randomPlayers) {
			sb.append(rightPad(player.getMoveHandler().getMove().name(), 25));
			sb.append(leftPad(o.playGame(asList(new GamePlayer[] { player, rocker })) + System.lineSeparator(), 5));
		}
		log.info(sb.toString());

	}

}

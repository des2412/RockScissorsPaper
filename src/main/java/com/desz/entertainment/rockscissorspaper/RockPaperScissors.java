/**
 * 
 */
package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.getRandomMove;
import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Stream.generate;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

import com.desz.entertainment.rockscissorspaper.functions.Move;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author des
 * 
 *         the main application class.
 *
 */
@Slf4j
public class RockPaperScissors {

	final GamePlayer player;
	final List<Integer> aggregatedScore = new ArrayList<>();
	static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

	static {
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);
	}

	public RockPaperScissors(GamePlayer player) {
		this.player = player;
	}

	public List<Integer> getAggregatedScore() {
		return aggregatedScore;
	}

	public GamePlayer getPlayer() {
		return player;
	}

	/**
	 * 
	 * @param players the GamePlayers.
	 * @return the result for a game between two players.
	 */
	String playGame(List<GamePlayer> players) {
		final boolean drawBothRock = players.stream().map(m -> m.getMoveHandler().getMove())
				.allMatch(m -> m.equals(player.getMoveHandler().getMove()));
		final Move m = compareMove.apply(players.get(0).getMoveHandler(), players.get(1).getMoveHandler());

		final int r = m.equals(player.getMoveHandler().getMove()) & !drawBothRock ? 1 : 0;
		aggregatedScore.add(r);
		return m.equals(DRAW) ? "Draw" : "Winner:" + m.name();

	}

	/**
	 * 
	 * @return randomly selected instance of Move that is not a Draw.
	 */
	Move getRandomMove() {
		final Move randomMove = getRandomMove.get();
		return !randomMove.equals(DRAW) ? randomMove : getRandomMove();

	}

	public static void main(String args[]) {

		// instantiate RockPaperScissors with the Rock!

		RockPaperScissors rockPaperScissors = new RockPaperScissors(
				GamePlayer.builder().id("rocker").moveHandler(MoveHandler.builder().move(ROCK).build()).build());

		// 100 random Move GamePlayers.
		final List<GamePlayer> randomPlayers = generate(() -> GamePlayer.builder().id("random")
				.moveHandler(MoveHandler.builder().move(rockPaperScissors.getRandomMove()).build()).build()).limit(100)
						.collect(toList());

		final StringBuilder resultsOutput = new StringBuilder();
		// Results Header.
		resultsOutput.append(rightPad("Random Player Move", 25));
		resultsOutput.append(leftPad("Result vs" + SPACE + rockPaperScissors.getPlayer().getId(), 5) + lineSeparator());
		resultsOutput.append(repeat("-", 38) + lineSeparator());

		// play game between random Move player and rocker.
		for (GamePlayer randomMovePlayer : randomPlayers) {
			resultsOutput.append(rightPad(randomMovePlayer.getMoveHandler().getMove().name(), 25));
			resultsOutput.append(leftPad(rockPaperScissors.playGame(
					asList(new GamePlayer[] { randomMovePlayer, rockPaperScissors.getPlayer() })) + lineSeparator(),
					5));
		}

		final IntSummaryStatistics stats = rockPaperScissors.verifyStats(rockPaperScissors.getAggregatedScore());
		final StringBuilder statsOutput = new StringBuilder();

		statsOutput.append("Player id:" + SPACE + rockPaperScissors.getPlayer().getId() + SPACE + "Average score:"
				+ SPACE + decimalFormat.format(stats.getAverage()) + SPACE + "Total Score:" + SPACE + stats.getSum());
		log.info(lineSeparator() + statsOutput.toString());
		log.info(lineSeparator() + resultsOutput.toString());

	}

	IntSummaryStatistics verifyStats(List<Integer> statCollection) {

		return statCollection.stream().collect(summarizingInt(Integer::intValue));

	}

}

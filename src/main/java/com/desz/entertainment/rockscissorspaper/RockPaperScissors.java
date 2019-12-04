/**
 * 
 */
package com.desz.entertainment.rockscissorspaper;

import static com.desz.entertainment.rockscissorspaper.builders.GameFactory.createGame;
import static com.desz.entertainment.rockscissorspaper.functions.Move.ROCK;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.summarizingInt;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import com.desz.entertainment.rockscissorspaper.builders.GameConfig;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;
import com.desz.entertainment.rockscissorspaper.player.GamePlayService;
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

	final List<Integer> aggregatedScore = new ArrayList<>();
	static final DecimalFormat DEC_FORMAT = new DecimalFormat("#.##");
	private GamePlayService gamePlayService;

	static {
		DEC_FORMAT.setRoundingMode(RoundingMode.FLOOR);
	}

	private StringBuilder results = new StringBuilder();

	/**
	 * 
	 * @param gameConfig
	 * @param player
	 * @param opponent
	 */
	public RockPaperScissors(GameConfig gameConfig, Optional<GamePlayer> player, Optional<GamePlayer> opponent) {

		// create the GamePlayService.

		gamePlayService = new GamePlayService(createGame(gameConfig));

		// if opponent present and gameConfig specifies a single opponent.

		gamePlayService = (opponent.isPresent() && gameConfig.isSingleOpponent())
				? new GamePlayService(createGame(gameConfig).toBuilder().opponent(opponent.get()).build())
				: gamePlayService;

		// if player present set it.
		gamePlayService = player.isPresent()
				? new GamePlayService(createGame(gameConfig).toBuilder().player(player.get()).build())
				: gamePlayService;

		

		final String playerId = gamePlayService.getGameBuilder().getPlayer().getId();
		log.info(String.format("Player:%s", playerId));
		// Results Header.

		results.append(lineSeparator() + String.format("Player Id:%-20s", playerId) + lineSeparator());
		results = opponent.isPresent()
				? results.append(String.format("Opponent:%s", gamePlayService.getGameBuilder().getOpponent().getId()))
				: results;
		results.append(String.format("%-40s", "") + lineSeparator());

	}

	public String getPlayerId() {
		return gamePlayService.getGameBuilder().getPlayer().getId();
	}

	public StringBuilder getResults() {
		return results.append(gamePlayService.getGameResult().getResults());
	}

	public List<Integer> getAggregatedScore() {
		return gamePlayService.getGameResult().getScores();
	}

	/**
	 * Main method of RockPaperScissors.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		// instantiate RockPaperScissors with the Rock!
		final MoveHandler handler = MoveHandler.builder().move(ROCK).build();

		RockPaperScissors rockPaperScissors = new RockPaperScissors(GameConfig.ROCKVSRANDOM,
				Optional.of(GamePlayer.builder().id(ROCK.name()).moveHandler(handler).build()), Optional.empty());

		// calculate the statistics for player against the random players.
		final IntSummaryStatistics stats = rockPaperScissors.collectStatistics(rockPaperScissors.getAggregatedScore());

		log.info(lineSeparator() + String.format("Player id:%s", rockPaperScissors.getPlayerId()) + lineSeparator()
				+ String.format("Average score:%s", DEC_FORMAT.format(stats.getAverage())) + lineSeparator()
				+ String.format("Total Score:%s", stats.getSum()));
		log.info(lineSeparator() + rockPaperScissors.getResults().toString());

	}

	/**
	 * 
	 * @param statCollection
	 * @return the IntSummaryStatistics.
	 */
	IntSummaryStatistics collectStatistics(List<Integer> statCollection) {

		return statCollection.stream().collect(summarizingInt(Integer::intValue));

	}

}

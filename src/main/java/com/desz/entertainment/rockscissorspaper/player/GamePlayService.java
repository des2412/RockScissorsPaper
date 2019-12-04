package com.desz.entertainment.rockscissorspaper.player;

import static com.desz.entertainment.rockscissorspaper.functions.Move.DRAW;
import static com.desz.entertainment.rockscissorspaper.functions.MoveFunction.compareMove;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import com.desz.entertainment.rockscissorspaper.builders.GameBuilder;
import com.desz.entertainment.rockscissorspaper.functions.Move;
import com.desz.entertainment.rockscissorspaper.functions.MoveHandler;

import lombok.NonNull;
import lombok.Value;

public class GamePlayService {

	@Value
	public class GameResult {
		@NonNull
		private List<Integer> scores;
		@NonNull
		private StringBuilder results;
	}

	private final GameBuilder gameBuilder;
	private final GameResult gameResult = new GameResult(new ArrayList<Integer>(), new StringBuilder());

	/**
	 * 
	 * @param gameBuilder
	 */
	public GamePlayService(GameBuilder gameBuilder) {
		this.gameBuilder = gameBuilder;
		final GamePlayer player = gameBuilder.getPlayer();

		if (!isNull(gameBuilder.getRandomPlayers())) {
			for (GamePlayer opponent : gameBuilder.getRandomPlayers()) {
				final PlayOutcome playOutcome = playGame(player, opponent);
				assignOutput(playOutcome);
			}
		} else {
			final PlayOutcome playOutcome = playGame(player, gameBuilder.getOpponent());
			assignOutput(playOutcome);
		}

	}
	
	private void assignOutput(PlayOutcome playOutcome) {
		gameResult.getScores().add(playOutcome.getScore());
		gameResult.getResults().append(playOutcome.getResult());
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public GameBuilder getGameBuilder() {
		return gameBuilder;
	}

	/**
	 * 
	 * @param player the GamePlayer.
	 * @param opponent the GamePlayer.
	 * @return the result for a game between two players.
	 */
	PlayOutcome playGame(final GamePlayer player, GamePlayer opponent) {
		requireNonNull(player);
		requireNonNull(opponent);
		
		final MoveHandler playerHandler = player.getMoveHandler();
		final MoveHandler opponentHandler = opponent.getMoveHandler();
		String s = String.format("Player:%-12s", playerHandler.getMove())
				+ String.format("Opponent:%-12s", opponentHandler.getMove());
				
		if (playerHandler.equals(opponentHandler))
			return new PlayOutcome(0, s + String.format("%-12s", DRAW) + System.lineSeparator());

		final Move winner = compareMove.apply(playerHandler, opponentHandler);
		// if player wins increment score.
		final int r = winner.equals(playerHandler.getMove()) ? 1 : 0;
		s = s + String.format("Winner:%-12s", winner);

		return new PlayOutcome(r, s + System.lineSeparator());
	}

	@Value
	class PlayOutcome {
		@NonNull
		private Integer score;
		@NonNull
		private String result;
	}

}

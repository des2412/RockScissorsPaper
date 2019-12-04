# RockPaperScissors

## Synopsis


### Maven Build 

`mvn package exec:java -P run-profile` 

builds and runs the application.

### Implementation Overview

Enum Move: Types ROCK, SCISSORS, PAPER, DRAW.

DRAW is not conceptually a Move and is not selected as a random Move.

class MoveHandler has an Enum Move attribute that represents the Move it beats.

class GamePlayer has a MoveHandler attribute and an id:String field. This class can easily be extended with additional Moves such that alternative Moves could be readily selected through randomised mechanism, i.e, a List attribute of MoveHandlers.

Both classes specify Builders.

Classes are package private.


### Configuration

tODO Configure GamePlayService using GameBuilder

Externalised configuration has not been implemented. The main class RockPaperScissors has constructor parameters for GamePlayer which could be read from configuration properties file. The number of random players may similarly be configured.


### Testing

Junit 4 testing utilised.

### Reporting

IntSummaryStatistics are utilised. Average and total scores are reported against the random GamePlayers.

 
















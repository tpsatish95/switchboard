package io.sudhir.switchboard.games;

import io.sudhir.switchboard.Choice;
import io.sudhir.switchboard.Goal;
import io.sudhir.switchboard.boards.Board;
import io.sudhir.switchboard.players.Player;
import java.util.Optional;

public class SinglePlayerSequentialGame implements Game {

  private final Player player;

  public SinglePlayerSequentialGame(Player player) {
    this.player = player;
  }

  @Override
  public Board run(Board startingBoard, Goal goal) {
    Board runningBoard = startingBoard;
    while (runningBoard.canProceed() && !runningBoard.isComplete()) {
      Optional<Choice> choice = player.bestChoiceFor(runningBoard, goal);
      runningBoard = runningBoard.choose(choice.get());
    }
    return runningBoard;
  }
}

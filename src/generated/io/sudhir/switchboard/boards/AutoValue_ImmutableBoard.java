package io.sudhir.switchboard.boards;

import io.sudhir.switchboard.Choice;
import io.sudhir.switchboard.Demand;
import io.sudhir.switchboard.Supply;
import java.util.Optional;
import java.util.Set;

final class AutoValue_ImmutableBoard extends $AutoValue_ImmutableBoard {

  private volatile boolean isComplete;

  private volatile boolean isComplete$Memoized;

  AutoValue_ImmutableBoard(Set<Supply> supplies$, Set<Demand> demands$, Optional<Choice> choice$,
      Optional<Board> board$) {
    super(supplies$, demands$, choice$, board$);
  }

  @Override
  public boolean isComplete() {
    if (!isComplete$Memoized) {
      synchronized (this) {
        if (!isComplete$Memoized) {
          isComplete = super.isComplete();
          isComplete$Memoized = true;
        }
      }
    }
    return isComplete;
  }
}

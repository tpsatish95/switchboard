
package io.sudhir.switchboard.boards;

import com.google.common.collect.ImmutableSet;
import io.sudhir.switchboard.Choice;
import io.sudhir.switchboard.Demand;
import io.sudhir.switchboard.Supply;
import org.checkerframework.checker.nullness.qual.Nullable;

 final class AutoValue_ImmutableBoard extends ImmutableBoard {

  private final ImmutableSet<Supply> supplies;
  private final ImmutableSet<Demand> demands;
  private final @Nullable Choice choice;
  private final @Nullable ImmutableBoard board;

  AutoValue_ImmutableBoard(
      ImmutableSet<Supply> supplies,
      ImmutableSet<Demand> demands,
      @Nullable Choice choice,
      @Nullable ImmutableBoard board) {
    if (supplies == null) {
      throw new NullPointerException("Null supplies");
    }
    this.supplies = supplies;
    if (demands == null) {
      throw new NullPointerException("Null demands");
    }
    this.demands = demands;
    this.choice = choice;
    this.board = board;
  }

  @Override
  ImmutableSet<Supply> supplies() {
    return supplies;
  }

  @Override
  ImmutableSet<Demand> demands() {
    return demands;
  }

  @Override
  @Nullable Choice choice() {
    return choice;
  }

  @Override
  @Nullable ImmutableBoard board() {
    return board;
  }

  @Override
  public String toString() {
    return "ImmutableBoard{"
        + "supplies=" + supplies + ", "
        + "demands=" + demands + ", "
        + "choice=" + choice + ", "
        + "board=" + board
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ImmutableBoard) {
      ImmutableBoard that = (ImmutableBoard) o;
      return (this.supplies.equals(that.supplies()))
           && (this.demands.equals(that.demands()))
           && ((this.choice == null) ? (that.choice() == null) : this.choice.equals(that.choice()))
           && ((this.board == null) ? (that.board() == null) : this.board.equals(that.board()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.supplies.hashCode();
    h *= 1000003;
    h ^= this.demands.hashCode();
    h *= 1000003;
    h ^= (choice == null) ? 0 : this.choice.hashCode();
    h *= 1000003;
    h ^= (board == null) ? 0 : this.board.hashCode();
    return h;
  }

}

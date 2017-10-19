package io.sudhir.switchboard.boards;

import static com.google.common.collect.ImmutableSet.toImmutableSet;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import io.sudhir.switchboard.Choice;
import io.sudhir.switchboard.Demand;
import io.sudhir.switchboard.Supply;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;

@AutoValue
abstract class ImmutableBoard implements Board {

  static ImmutableBoard create(Collection<Supply> supplies, Collection<Demand> demands) {
    return new AutoValue_ImmutableBoard(
        ImmutableSet.copyOf(supplies), ImmutableSet.copyOf(demands), null, null);
  }

  abstract ImmutableSet<Supply> supplies();

  abstract ImmutableSet<Demand> demands();

  @Nullable
  abstract Choice choice();

  @Nullable
  abstract ImmutableBoard board();

  @Override
  public Stream<Choice> choicesMade() {
    return historyStream().map(ImmutableBoard::choice).filter(Objects::nonNull);
  }

  @Override
  public Stream<? extends Board> history() {
    return historyStream();
  }

  private Stream<ImmutableBoard> historyStream() {
    return Stream.iterate(this, Objects::nonNull, ImmutableBoard::board);
  }

  @Override
  public Board choose(Choice choice) {
    return new AutoValue_ImmutableBoard(supplies(), demands(), choice, this);
  }

  @Override
  public boolean isComplete() {
    return !pendingDemands().findAny().isPresent();
  }

  @Override
  public boolean canProceed() {
    return availableChoices().findAny().isPresent();
  }

  @Override
  public Stream<Choice> availableChoices() {
    return pendingDemands().flatMap(this::availableChoices);
  }

  @Override
  public Stream<Choice> availableChoices(Demand demand) {
    return supplies()
        .parallelStream()
        .map(supply -> supply.estimateFor(demand, choicesMade()))
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  @Override
  public Stream<Demand> pendingDemands() {
    return Sets.difference(demands(), choicesMade().map(Choice::demand).collect(toImmutableSet()))
        .parallelStream();
  }

  @Override
  public double score() {
    return choicesMade().parallel().mapToDouble(Choice::score).sum();
  }

  @Override
  public double boardScore() {
    return availableChoices().mapToDouble(Choice::score).sum();
  }
}

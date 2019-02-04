package edu.cnm.deepdive.craps.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * This shows the actual variations of a craps game and the different scenarios past the four
 * main states that are of a potential outcome.
 *
 * @author Michael Sanchez, Deep Dive Coding, Java + Android cohort 6
 * @version 1.0
 */
public class Game {

  private int pointValue;
  private State state;
  private Random rng;
  private List<int[]> rolls;
  private long wins;
  private long losses;

  /**
   * This is a constructor, which establishes the need for a random number generator, <code>rng</code>,
   * The class is to be used through the rest of its current package.
   * @param rng Generates a number, which is then used within the <code>rolls</code> and <code>LinkedList</code>.
   *
   */
  public Game(Random rng) {
    this.rng = rng;
    rolls = new LinkedList<>();
  }

  /**
   * This is a reset which restarts the <code>COME_OUT</code> state.  In short, this allows the game
   * to restart.
   */
  public void reset() {
    state = State.COME_OUT;
    pointValue = 0;
    rolls.clear();
  }

  private void roll() {
    int die0 = rng.nextInt(6) + 1;
    int die1 = rng.nextInt(6) + 1;
    int sum = die0 + die1;
    State newState = state.change(sum, pointValue);
    if (state == State.COME_OUT && newState == State.POINT) {
      pointValue = sum;
    }
    state = newState;
    int[] diceRoll = {die0, die1};
    rolls.add(diceRoll);
  }

  /**
   * This adds a count to number of wins and losses from the game.
   * @return the result of a win or loss is returned to a tally.
   */
  public State play() {
    while (state != State.WIN && state != State.LOSS) {
      roll();
      if (state == State.WIN) {
        wins++;
      } else if (state == State.LOSS) {
        losses++;
      }
    }
    return state;
  }

  public int getPointValue() {
    return pointValue;
  }

  public State getState() {
    return state;
  }

  /**
   * This is a deep copy of the list.
   * @return deep copy and tally of the game.
   */
  public List<int[]> getRolls() {
    List <int[]> copy= new LinkedList<int[]>();
    for (int [] roll: rolls){
      copy.add(Arrays.copyOf(roll, roll.length));
    }
    return copy;
  }

  public long getWins() {
    return wins;
  }

  public long getLosses() {
    return losses;
  }

}

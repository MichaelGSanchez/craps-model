package edu.cnm.deepdive.craps.model;


/**
 * Use enum to set states of a craps game.  The outcomes are win or lose,
 * however the the <code>COME_OUT</code> roll and <code>POINT</code> value is a crucial part of the games
 * play process.
 *
 * @author Michael Sanchez, Deep Dive Coding, Java + Android cohort 6
 * @version 1.0
 */
public enum State {
  COME_OUT {
    @Override
    public State change(int roll, int pointValue) {
     switch (roll) {
       case 2:
       case 3:
       case 12:
         return LOSS;
       case 7:
       case 11:
         return WIN;
         default:
           return POINT;
     }
    }
  },
  POINT{
    @Override
    public State change(int roll, int pointValue) {
      if (roll == 7) {
        return LOSS;
      }
      if (roll == pointValue){
        return WIN;
      }
      return this;
    }
  },
  WIN,
  LOSS;

  /**
   * This returns the value of the different general outcomes of the game of craps.
   * @param roll Is used to gain a point value to show to the user.
   * @param pointValue Assigns value to the result of the roll.
   * @return returns the result of <code>role</code> and <code>pointValue</code>.
   */
  public State change(int roll, int pointValue){
    return this;
  }

}

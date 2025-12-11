package tema1.ej9;

import staticMethods.GlobalStaticMethods;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int id;
    int wantedValue;
    int currentValue;
    boolean isCompleted = false;
    int amountOfThrows =0;

    public Dice(int id, int value) {
        this.id = id;
        this.wantedValue = value;
    }
    public synchronized int getAmountOfThrows() {
        return amountOfThrows;
    }

    public synchronized boolean throwDice() {

        if(isCompleted) return true;
        amountOfThrows++;
        var roll = ThreadLocalRandom.current().nextInt(1, ej9.MAX_DICE_VALUE);
        if(GlobalStaticMethods.DEBUG_MESSAGES)System.out.printf("Dice %d current throw %d\n", id, roll);
        if (roll == wantedValue) {
            currentValue = roll;
            isCompleted = true;
            return true;
        }
        currentValue = roll;
        return false;
    }

    @Override
    public String toString() {
        return String.format("Dice %d:Resultado %d/%d, fue tirado %d vezes\n",id,currentValue,wantedValue,amountOfThrows);
    }
}

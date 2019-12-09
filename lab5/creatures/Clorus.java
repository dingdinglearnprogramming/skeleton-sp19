package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1.0);
    }

    public Color color() {
        return color(r,g,b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void stay() {
        energy -= 0.01;
    }

    public void move() {
        energy -= 0.03;
    }

    public Clorus replicate(){
        Clorus baby = new Clorus(energy / 2);
        energy = energy / 2;
        return baby;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbours) {
        Deque<Direction> emptyNeighbours = new ArrayDeque<Direction>();
        Deque<Direction> plips = new ArrayDeque<Direction>();

        for (Direction k : neighbours.keySet()) {
            if (neighbours.get(k).name() == "empty") {
                emptyNeighbours.add(k);
            }
            if (neighbours.get(k).name() == "plip") {
                plips.add(k);
            }
        }

        if (emptyNeighbours.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        if (!plips.isEmpty()) {
            Direction attacked = ramdomEntry(plips);
            return new Action(Action.ActionType.ATTACK,attacked);
        }

        Direction r = ramdomEntry(emptyNeighbours);
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, r);
        }
        return new Action(Action.ActionType.MOVE, r);
    }

    private Direction ramdomEntry(Deque<Direction> deque) {
        Object[] d = deque.toArray();
        int r = (int) (Math.random() * d.length);
        return (Direction) d[r];


        //return deque.element();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.objects;

import game.entities.Entity;
import game.entities.creatures.Creature;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kompas
 */
public class IteratorArea implements Iterator {

    private List<Creature> entityList;
    int position = 0;

    public IteratorArea() {
        entityList = new ArrayList<>();
    }

    @Override
    public void add(Object obj) {
        this.entityList.add((Creature) obj);
    }

    @Override
    public void remove(Object obj) {
        this.entityList.remove((Creature) obj);
    }

    @Override
    public Creature first() {
        return entityList.get(0);
    }

    @Override
    public Creature current() {
        return entityList.get(position);
    }

    @Override
    public Creature next() {
        if (this.hasNext()) {
            return entityList.get(position++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (position >= entityList.size() || entityList.get(position) == null) {
            return false;
        } else {
            return true;
        }
    }
}

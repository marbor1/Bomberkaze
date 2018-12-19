/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.factories;

import game.Handler;
import game.entities.Entity;
import game.entities.creatures.Creature;
import game.entities.objects.*;

/**
 *
 * @author Marius
 */
public class ObjectFactory implements IFactory{

    @Override
    public StaticObject createEnemy(String type, Handler handler, int x, int y, String BombsType) {
            if(type.equals("Box")){
            return new Box(handler, x, y);
        }

        return null;
    }

    
    
}

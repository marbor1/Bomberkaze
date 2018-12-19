/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entities.creatures.playerSkins;

import game.Handler;
import game.entities.creatures.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import visitor.Visitors;

/**
 *
 * @author nugal
 */
public interface IPlayerSkin {
    BufferedImage draw();
    public void accaptVisitor(Visitors visitor);
}

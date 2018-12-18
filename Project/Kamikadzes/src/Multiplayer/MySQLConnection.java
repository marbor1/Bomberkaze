/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer;

import java.sql.Connection;

/**
 *
 * @author a.pocius
 */
public interface MySQLConnection {

     public void connect(String username) throws Exception;
    
}

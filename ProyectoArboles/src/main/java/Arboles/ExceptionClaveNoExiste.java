/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

/**
 *
 * @author brandon
 */
public class ExceptionClaveNoExiste extends Exception {
    public ExceptionClaveNoExiste() {
        super("La clave NO existe en el arbol");
    }

    public ExceptionClaveNoExiste(String message) {
        super(message);
    } 
    
}

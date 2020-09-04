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
public class ExceptionClaveYaExiste extends Exception{

    public ExceptionClaveYaExiste() {
        super("La clave ya existe en el arbol");
    }

    public ExceptionClaveYaExiste(String message) {
        super(message);
    }
    
}

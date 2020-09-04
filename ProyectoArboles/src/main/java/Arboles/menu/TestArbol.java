/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles.menu;

import Arboles.ArbolAVL;
import Arboles.ArbolBusquedaBinario;
import Arboles.ExceptionClaveNoExiste;
import Arboles.ExceptionClaveYaExiste;
import Arboles.IArbolBusqueda;
import Arboles.Practico1.Abb;
import java.util.Scanner;

/**
 *
 * @author brandon
 */
public class TestArbol{
    
 public static void main(String[] args) throws ExceptionClaveYaExiste, ExceptionClaveNoExiste  { 
      IArbolBusqueda<Integer,String> arbolPrueba = new ArbolBusquedaBinario();
      Abb pregunta=new Abb<>();
      ArbolAVL arbolBalnaceado =new ArbolAVL<>();
              pregunta.insertar(20, "cristian soza");
              pregunta.insertar(7, "julio gonzales");
              pregunta.insertar(23, "llanos");
              pregunta.insertar(9, "mario");
              pregunta.insertar(11, "armando");
              pregunta.insertar(25, "julia");
              pregunta.insertar(4, "berta");
              pregunta.insertar(3, "marcela"); 
              pregunta.insertar(22, "carol");
              pregunta.insertar(10, "valeria");
              pregunta.insertar(8, "valeria");
              pregunta.insertar(24, "valeria");
              
              arbolBalnaceado.insertar(20, "cristian soza");
              arbolBalnaceado.insertar(7, "julio gonzales");
              arbolBalnaceado.insertar(23, "llanos");
              arbolBalnaceado.insertar(9, "mario");
              arbolBalnaceado.insertar(11, "armando");
              arbolBalnaceado.insertar(25, "julia");
              arbolBalnaceado.insertar(4, "berta");
              arbolBalnaceado.insertar(3, "marcela"); 
              arbolBalnaceado.insertar(22, "carol");
              arbolBalnaceado.insertar(10, "valeria");
              arbolBalnaceado.insertar(8, "valeria");
              arbolBalnaceado.insertar(24, "valeria");
             
          //  arbolBalnaceado.toString();
              System.out.println(pregunta);    
           
     int opcion= -1;
     Scanner entrada= new Scanner(System.in);
     Scanner nivel= new Scanner(System.in);
     
     System.out.println("*********************PRACTICO 1 ******************************* ");
     System.out.println("iNGRESE 0 PARA SALIR  ");
     System.out.println("1.-cantidad nodos hojas que existen en un árbol binario  ");
     System.out.println("2.-iterativo que retorne la cantidad nodos hojas que existen en un árbol binario ");
     System.out.println("3.-recursivo que retorne la cantidad nodos hojas que existen en un árbol binario, pero solo en el nivel N  ");
     System.out.println("4.-iterativo que retorne la cantidad nodos hojas que existen en un árbol binario, pero solo en el nivel N  ");
     System.out.println("5.- iterativo que retorne la cantidad nodos hojas que existen en un árbol binario, pero solo antes del nivel N ");
     System.out.println("6.-  recursivo que retorne verdadero, si un árbol binario esta balanceado según las reglas que establece un árbol AVL, "
             + "falso en caso contrario. ");
     System.out.println("7.-iterativo que la lógica de un recorrido en postorden que retorne verdadero, "
             + "si un árbol binario esta balanceado según las reglas que establece un árbol AVL, falso en caso contrario. ");
      // El reconstrir arbol me daba problemas al mostrar;
     System.out.println("8.- método privado que reciba un nodo binario de un árbol binario y que retorne"
             + " cual sería su sucesor inorden de la clave de dicho nodo");
     System.out.println("9.-privado que reciba un nodo binario de un árbol binario y que retorne "
             + "cuál sería su predecesor inorden de la clave de dicho nodo.");
     System.out.println("10.- Implemente el método eliminar de un árbol AVL  ");
     System.out.println("11.-que retorne la cantidad de nodos que tienen ambos hijos luego del nivel N.  ");
     
    while(opcion!=0){
       System.out.println("Introduca Una Opcion ");
       opcion=entrada.nextInt();
      
        switch(opcion){
            case 1:
                System.out.println(pregunta.cantidadDeNodosHoja());
             break;
             
            case 2:
                System.out.println(pregunta.cantidadDeNodosHojaIterativo());
             break;
             
            case 3:
                System.out.println("introduca el nivel: ");
                System.out.println(pregunta.cantidadNodoHojaR(nivel.nextInt()));
             break;
            
             case 4:
                System.out.println("introduca el nivel: ");
                System.out.println(pregunta.cantidadNodoHojaDeNivelIterativo(nivel.nextInt()));
 
             break;
             
             case 5:
                 System.out.println("introduca el nivel: ");
                System.out.println(pregunta.cantidadNodoHojaAntesNivelIterativo(nivel.nextInt()));
             break;
             
             case 6:
                System.out.println(pregunta.arbolBalanceadoRecursivo());
             break;
             
             case 7:
                System.out.println(pregunta.arbolBalanceadoConPostOrden());
                 
             break;
             
             case 8:
                System.out.println(pregunta.recorridoEnInOrden());
                System.out.println(pregunta.recorridoEnInOrdenParaValores());
                System.out.println("introduca el dato: ");
                System.out.println(pregunta.sucesorInOrden(nivel.nextInt()));
             break;
             
             case 9:
                 System.out.println(pregunta.recorridoEnInOrden());
                  System.out.println("introduca el dato: ");
                System.out.println(pregunta.predecesorInOrden(nivel.nextInt()));
             break;
             
             case 10:
                System.out.println("introduca el dato: "); 
                System.out.println(arbolBalnaceado.eliminar(nivel.nextInt()));  
                arbolBalnaceado.toString();
                 System.out.println(arbolBalnaceado);
             break;
             
             case 11:
                 System.out.println("introduca el Nivel: ");
                 System.out.println(pregunta.cantidadNodoAmbosHijosR(nivel.nextInt()));
                 
             break;   
        }
    } 
              
      
 }     
    
 }    

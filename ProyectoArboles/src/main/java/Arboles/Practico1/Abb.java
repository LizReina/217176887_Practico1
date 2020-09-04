/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles.Practico1;

import Arboles.ArbolAVL;
import Arboles.ArbolBusquedaBinario;
import Arboles.ExceptionClaveNoExiste;
import Arboles.NodoBinario;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author brandon
 */
public class Abb<K extends Comparable<K>,V> extends ArbolBusquedaBinario<K,V>  {

    //------------------------------------------EJERCICIOS-------------------------------------------
    //1. Implemente un método recursivo que retorne la cantidad nodos hojas que existen en un árbol binario
         public int cantidadDeNodosHoja(){
            return cantidadDeNodosHoja(this.raiz);
         }

        public int cantidadDeNodosHoja(NodoBinario<K,V> nodoActual){
            if(NodoBinario.esNodoVacio(nodoActual)){
                return 0;
            }

          int cantidadHojasHijoIzquierdo=cantidadDeNodosHoja(nodoActual.getHijoIzquierdo());
          int cantidadHojasHijoDerecho=cantidadDeNodosHoja(nodoActual.getHijoDerecho());
          if(nodoActual.esHoja()){

              return  cantidadHojasHijoDerecho + cantidadHojasHijoIzquierdo + 1;
            }
            return cantidadHojasHijoDerecho + cantidadHojasHijoIzquierdo;

         }
 //iterativo cantidad nodos hojas
   public int cantidadDeNodosHojaIterativo(){
       List<K> recorrido= new LinkedList<>();
       int cantidadHojas=0;

       if(esArbolVacio()){
           return cantidadHojas;
       }

   Queue<NodoBinario<K,V>> colaDeNodos =new LinkedList<>();
   colaDeNodos.offer(this.raiz);

        while(!colaDeNodos.isEmpty()){
          NodoBinario<K,V> nodoActual=colaDeNodos.poll();
          recorrido.add(nodoActual.getClave());

             if(!nodoActual.esVacioHijoIzquierdo()){
                 colaDeNodos.offer(nodoActual.getHijoIzquierdo());
             }

             if(!nodoActual.esVacioHijoDerecho()){
                 colaDeNodos.offer(nodoActual.getHijoDerecho());
             }

             if(nodoActual.esHoja()){
                 cantidadHojas++;
             }
        }

   return cantidadHojas;
   }



   //3. Implemente un método recursivo que retorne la cantidad nodos hojas que existen en un árbol binario, pero solo en el nivel N

   public int cantidadNodoHojaR(int nivelABuscar) {
        return cantidadNodoHojaR(this.raiz, nivelABuscar,0);
    }

    private int cantidadNodoHojaR(NodoBinario<K,V> nodoActual, int nivelABuscar,int nivelActual) {
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadNodosIzquierda =cantidadNodoHojaR(nodoActual.getHijoIzquierdo(),nivelABuscar,nivelActual+1);
        int cantidadNodosDerecha =cantidadNodoHojaR(nodoActual.getHijoDerecho(),nivelABuscar,nivelActual+1);

        if(nivelABuscar==nivelActual && nodoActual.esHoja()){
           return cantidadNodosIzquierda + cantidadNodosDerecha +1;
        }
        return cantidadNodosIzquierda + cantidadNodosDerecha;
    }
   //iteraivo
      public int cantidadNodoHojaDeNivelIterativo(int nivel){
      List<K> recorrido=new LinkedList<>();
      if(esArbolVacio()){
         return 0;
      }

      Queue<NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
      colaDeNodos.offer(this.raiz);
      int nivelActual=0;
      int cantidadHojas=0;

      while(!colaDeNodos.isEmpty() && nivelActual<= nivel){
        int nodoNivel=colaDeNodos.size();

        while(nodoNivel>0){
             NodoBinario<K,V> nodoActual=colaDeNodos.poll();
         if(!nodoActual.esVacioHijoIzquierdo()){
                 colaDeNodos.offer(nodoActual.getHijoIzquierdo());
             }

             if(!nodoActual.esVacioHijoDerecho()){
                 colaDeNodos.offer(nodoActual.getHijoDerecho());
             }

             if(nodoActual.esHoja() && nivelActual== nivel){
                 cantidadHojas++;
             }

            nodoNivel=nodoNivel-1;
        }
        nivelActual=nivelActual+1;

      }

      return cantidadHojas;
      }

      //5.-Implemente un método iterativo que retorne la cantidad nodos hojas que existen en un árbol binario, pero solo antes del nivel N

      public int cantidadNodoHojaAntesNivelIterativo(int nivel)throws ExceptionClaveNoExiste{
      List<K> recorrido=new LinkedList<>();
      if(esArbolVacio()){
         return 0;
      }

      Queue<NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
      colaDeNodos.offer(this.raiz);
      int nivelActual=0;
      int cantidadHojas=0;
        
      if(altura()<=nivel){
          throw new ExceptionClaveNoExiste("Fuera del nivel");
      }
      
      while(!colaDeNodos.isEmpty() && nivelActual< nivel){
        int nodoNivel=colaDeNodos.size();

        while(nodoNivel>0){
             NodoBinario<K,V> nodoActual=colaDeNodos.poll();
         if(!nodoActual.esVacioHijoIzquierdo()){
                 colaDeNodos.offer(nodoActual.getHijoIzquierdo());
             }

             if(!nodoActual.esVacioHijoDerecho()){
                 colaDeNodos.offer(nodoActual.getHijoDerecho());
             }

             if(nodoActual.esHoja() && nivelActual<= nivel){
                 cantidadHojas++;
             }

            nodoNivel=nodoNivel-1;
        }
        nivelActual=nivelActual+1;

      }

      return cantidadHojas;
      }

    //6. Implemente un método recursivo que retorne verdadero, si un árbol binario esta balanceado según las reglas que establece un árbol AVL,
      //falso en caso contrario.


      public boolean arbolBalanceadoRecursivo(){
          return arbolBalanceado(this.raiz);
      }

         private  boolean arbolBalanceado(NodoBinario<K,V> nodoActual){
          boolean arbolBalanceado=true;
          if(NodoBinario.esNodoVacio(nodoActual)){
              return arbolBalanceado;
          }

          arbolBalanceado=arbolBalanceado(nodoActual.getHijoIzquierdo());
          arbolBalanceado=arbolBalanceado(nodoActual.getHijoDerecho());

          int cantidadHijosIzquierdos=alturaRecursiva(nodoActual.getHijoIzquierdo());
          int catidadHijosDerechos= alturaRecursiva(nodoActual.getHijoDerecho());

          if (arbolBalanceado==false){
              return arbolBalanceado;
          }else if((cantidadHijosIzquierdos-catidadHijosDerechos) >1 || (cantidadHijosIzquierdos-catidadHijosDerechos) <-1){
               arbolBalanceado=false;
          }

          return arbolBalanceado;
      }

   //7. Implemente un método iterativo que la lógica de un recorrido en postorden que retorne verdadero,
  //si un árbol binario esta balanceado según las reglas que establece un árbol AVL, falso en caso contrario.

        public boolean arbolBalanceadoConPostOrden(){
             boolean arbolBalanceado=true;
             if(esArbolVacio()){
                 return false;
             }

          Stack<NodoBinario<K,V>> pilaDeNodos=new Stack<>();
          NodoBinario<K,V> nodoActual=this.raiz;
          this.ayuda(pilaDeNodos, nodoActual);
          int cantidadHijosIzquierdos=0;
          int cantidadHijosDerechos=0;

          while(!pilaDeNodos.isEmpty() && arbolBalanceado!=false){
             nodoActual=pilaDeNodos.pop();
             cantidadHijosIzquierdos=alturaRecursiva(nodoActual.getHijoIzquierdo());
             cantidadHijosDerechos=alturaRecursiva(nodoActual.getHijoDerecho());

             if(cantidadHijosIzquierdos-cantidadHijosDerechos >1 || cantidadHijosIzquierdos-cantidadHijosDerechos <-1){
               arbolBalanceado= false;
              }

             if(!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> tope= pilaDeNodos.peek();
                if(!tope.esVacioHijoDerecho() && tope.getHijoDerecho()!= nodoActual){
                    this.ayuda(pilaDeNodos,tope.getHijoDerecho());
                }
             }
          }

          return arbolBalanceado;
         }

   //  8. Implemente un método que reciba en listas de parámetros las llaves y valores de los recorridos en preorden e
   //  inorden respectivamente y que reconstruya el árbol binario original. Su método no debe usar el método insertar.
        
    public NodoBinario<K,V> reconstruirConPreOrden(List<K> recorridoClaveEnPreOrden,List<V> recorridoValorEnPreOrden,
   List<K> recorridoClaveEnInOrden,List<V> recorridoValorEnInOrden){
         
        if(recorridoClaveEnInOrden.isEmpty()){
            return (NodoBinario<K, V>) NodoBinario.nodoVacio();
        }
  
  int posDatoEnPreOrden=0;
  K datoClaveNodoActual=recorridoClaveEnPreOrden.get(posDatoEnPreOrden);
  V datoValorNodoActual=recorridoValorEnPreOrden.get(posDatoEnPreOrden);
  int posEnInOrden=this.buscarPosicionEnLista(recorridoClaveEnInOrden,datoClaveNodoActual);
  
 //lado Izquierdo 
  List<K> listaDeClavePreOrdenIzq=recorridoClaveEnPreOrden.subList(posDatoEnPreOrden+1, posEnInOrden-1);
  List<V> listaDeValorPreOrdenIzq=recorridoValorEnPreOrden.subList(posDatoEnPreOrden+1, posEnInOrden-1);
  List<K> listaDeClaveInOrdenIzq=recorridoClaveEnInOrden.subList(posDatoEnPreOrden, posEnInOrden-1);
  List<V> listaDeValorInOrdenIzq=recorridoValorEnInOrden.subList(posDatoEnPreOrden, posEnInOrden-1);
 
// lado derecho
  List<K> listaDeClavePreOrdenDer=recorridoClaveEnPreOrden.subList(posEnInOrden+1,recorridoClaveEnPreOrden.size()-1);
  List<V> listaDeValorPreOrdenDer=recorridoValorEnPreOrden.subList(posEnInOrden+1,recorridoValorEnPreOrden.size()-1);        
  List<K> listaDeClaveInOrdenDer=recorridoClaveEnInOrden.subList(posEnInOrden+1,recorridoClaveEnInOrden.size()-1);
  List<V> listaDeValorInOrdenDer=recorridoValorEnInOrden.subList(posEnInOrden+1,recorridoValorEnInOrden.size()-1);    
  
  NodoBinario<K,V> nodoActual=new NodoBinario<>(datoClaveNodoActual,datoValorNodoActual);
  
  NodoBinario<K,V> hijoIzquierdoDelNodoActual=this.reconstruirConPreOrden(listaDeClavePreOrdenIzq, listaDeValorPreOrdenIzq,
                                                listaDeClaveInOrdenIzq, listaDeValorInOrdenIzq);
  NodoBinario<K,V> hijoDerechoDelNodoActual=this.reconstruirConPreOrden(listaDeClavePreOrdenDer, listaDeValorPreOrdenDer,
                                                listaDeClaveInOrdenDer, listaDeValorInOrdenDer);
  
  nodoActual.setHijoIzquierdo(hijoIzquierdoDelNodoActual);
  nodoActual.setHijoDerecho(hijoDerechoDelNodoActual);
  
   return nodoActual;    
  }

 
    public int buscarPosicionEnLista(List<K> recorridoClsveEnInOrden, K datoClaveNodoActual) {
       int i=0;
       int posicion=0;
       
      while(i < recorridoClsveEnInOrden.size()){
          if(recorridoClsveEnInOrden.get(i).compareTo(datoClaveNodoActual)==0){
              posicion=i;
              i=recorridoClsveEnInOrden.size();
          }
        i++;
      } 
    return posicion;
    }
        
   //  9. Implemente un método privado que reciba un nodo binario de un árbol binario y que retorne cual sería su sucesor inorden
   //   de la clave de dicho nodo.
    
    public K sucesorInOrden(K clave)throws ExceptionClaveNoExiste{
      NodoBinario<K,V> nodoActual=new NodoBinario<>();
      nodoActual.setClave(clave);
     return buscarSucesor(nodoActual);
    }
           
    private K buscarSucesor(NodoBinario<K,V> nodoActual)throws ExceptionClaveNoExiste{
        List<K> listaInOrden=recorridoEnInOrden();
        int i=0;
        NodoBinario<K,V> sucessorIn= new NodoBinario<>(); 
         while(i < listaInOrden.size()){
          if(listaInOrden.get(i).compareTo(nodoActual.getClave())==0){
            sucessorIn.setClave(listaInOrden.get(i+1));
              i=listaInOrden.size();
          }
        i++;
      } 
      
    return sucessorIn.getClave();
        
    }
   //   10. Implemente un método privado que reciba un nodo binario de un árbol binario y que retorne cuál sería su predecesor inorden
   //    de la clave de dicho nodo.
    
     public K predecesorInOrden(K clave){
      NodoBinario<K,V> nodoActual=new NodoBinario<>();
      nodoActual.setClave(clave);
     return predecesorInOrden(nodoActual);
    }
           
    private K predecesorInOrden(NodoBinario<K,V> nodoActual){
        List<K> listaInOrden=recorridoEnInOrden();
        int i=0;
        NodoBinario<K,V> predecessorIn= new NodoBinario<>(); 
                while(i < listaInOrden.size()){
          if(listaInOrden.get(i).compareTo(nodoActual.getClave())==0){
            predecessorIn.setClave(listaInOrden.get(i-1));
              i=listaInOrden.size();
          }
        i++;
      } 
    return predecessorIn.getClave();
        
    }
       
  //   11. Implemente el método eliminar de un árbol AVL
       @Override
   public V eliminar(K clave)throws ExceptionClaveNoExiste{
     V valorRetornar=buscar(clave);
     ArbolAVL<K,V> avl= new ArbolAVL<>();
      if(valorRetornar==null){
           throw new ExceptionClaveNoExiste();
       }
       valorRetornar=avl.eliminar(clave);
       return valorRetornar;
      
   }



    //12 Para un árbol binario implemente un método que retorne la cantidad de nodos que tiene ambos hijos luego del nivel N.
       public int cantidadNodoAmbosHijosR(int nivelABuscar) {
        return cantidadNodoAmbosHijosR(this.raiz, nivelABuscar,0);
    }

    private int cantidadNodoAmbosHijosR(NodoBinario<K,V> nodoActual, int nivelABuscar,int nivelActual) {
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadNodosIzquierda =cantidadNodoAmbosHijosR(nodoActual.getHijoIzquierdo(),nivelABuscar,nivelActual+1);
        int cantidadNodosDerecha =cantidadNodoAmbosHijosR(nodoActual.getHijoDerecho(),nivelABuscar,nivelActual+1);
        if((nivelActual>nivelABuscar) && (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) ){
              return cantidadNodosIzquierda + cantidadNodosDerecha +1;

        }
        return cantidadNodosIzquierda + cantidadNodosDerecha;
    }
    
}

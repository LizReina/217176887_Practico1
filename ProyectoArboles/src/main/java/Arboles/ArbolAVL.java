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
public class ArbolAVL<K extends Comparable<K>,V>extends ArbolBusquedaBinario<K,V> {
  
    public static final byte DIFERENCIA_MAXIMA = 1;
 
  @Override
  public void insertar(K clave, V valor) throws ExceptionClaveYaExiste {
   this.raiz=insert(this.raiz,clave,valor);
  }

    private NodoBinario<K,V> insert(NodoBinario<K,V> nodoActual,K clave,V valor) throws ExceptionClaveYaExiste {
       if(NodoBinario.esNodoVacio(nodoActual)){
           NodoBinario<K,V> nuevoNodo=new NodoBinario<>(clave,valor);
           return nuevoNodo;
       }
        
      K claveActual=nodoActual.getClave();
       if (clave.compareTo(claveActual)>0){
           NodoBinario<K,V>  supuestoHijoDerecho=insert(nodoActual.getHijoDerecho(), clave, valor) ;
           nodoActual.setHijoDerecho(supuestoHijoDerecho);
           nodoActual=balancear(nodoActual);
           return nodoActual;
           
           }
       if(clave.compareTo(claveActual)<0) {
           NodoBinario<K,V>  supuestoHijoIzquierdo=insert(nodoActual.getHijoIzquierdo(), clave, valor) ;
           nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
           nodoActual=balancear(nodoActual);
           return nodoActual;
          }
          
        throw new ExceptionClaveYaExiste();
    
    }

private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
   int alturaIzquierda=alturaRecursiva(nodoActual.getHijoIzquierdo());
   int alturaDerecha=alturaRecursiva(nodoActual.getHijoDerecho());
   int diferenciaAltura=alturaIzquierda-alturaDerecha;
   
   if(diferenciaAltura>DIFERENCIA_MAXIMA){
      NodoBinario<K,V> hijoIzquierdo=nodoActual.getHijoIzquierdo();
       alturaIzquierda=alturaRecursiva(hijoIzquierdo.getHijoIzquierdo());
       alturaDerecha=alturaRecursiva(hijoIzquierdo.getHijoDerecho());
       
         if(alturaDerecha>alturaIzquierda){
           return  rotacionDobleAlaDerecha(nodoActual);
         }else{
           return rotacionSimpleADerecha(nodoActual);
         }
       //hay problema en la rama izquierda
   }else if (diferenciaAltura < -DIFERENCIA_MAXIMA){
       // hay problema en la rama derecha
      NodoBinario<K,V> hijoDerecho=nodoActual.getHijoDerecho();
       alturaIzquierda=alturaRecursiva(hijoDerecho.getHijoIzquierdo());
       alturaDerecha=alturaRecursiva(hijoDerecho.getHijoDerecho());
       
         if(alturaDerecha>alturaIzquierda){
           return rotacionSimpleAIzquierda(nodoActual);
         }else{
           return  rotacionDobleAlaIzquierda(nodoActual);
         }    
   }
   //no hay problema, entonces no habria cambio,
   //retornamos el mismo nodo
   return nodoActual;
}

    private NodoBinario<K, V> rotacionDobleAlaDerecha(NodoBinario<K, V> nodoActual) {
    nodoActual.setHijoIzquierdo(rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
    return rotacionSimpleADerecha(nodoActual);  
    }
    
    private NodoBinario<K, V> rotacionSimpleADerecha(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoARetornar=nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoARetornar.getHijoDerecho());
        nodoARetornar.setHijoDerecho(nodoActual);
        return nodoARetornar;
    } 


     private NodoBinario<K, V> rotacionDobleAlaIzquierda(NodoBinario<K, V> nodoActual) {
    nodoActual.setHijoDerecho(rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
    return rotacionSimpleAIzquierda(nodoActual);
      
    }
    
   private NodoBinario<K, V> rotacionSimpleAIzquierda(NodoBinario<K, V> nodoActual) {
        NodoBinario<K,V> nodoARetornar=nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoARetornar.getHijoIzquierdo());
        nodoARetornar.setHijoIzquierdo(nodoActual);
        return nodoARetornar;
    } 
   
   //ELIMINAR NODOS AVL
   @Override
   public V eliminar(K clave)throws ExceptionClaveNoExiste{
    V valorRetornar=buscar(clave);
      if(valorRetornar==null){
           throw new ExceptionClaveNoExiste();
       }
       this.raiz=eliminar(this.raiz,clave);
       return valorRetornar;
      
   }

    private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual,K claveAEliminar)throws ExceptionClaveNoExiste{ {
       if(NodoBinario.esNodoVacio(nodoActual)){
           throw new ExceptionClaveNoExiste();
       }
       
     K claveActual=nodoActual.getClave();
     if(claveAEliminar.compareTo(claveActual)<0){
         NodoBinario<K,V> supuestoHijoIzquierdo=eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
         nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
         return balancear(nodoActual);
      }
     
     if(claveAEliminar.compareTo(claveActual)>0){
         NodoBinario<K,V> supuestoHijoDerecho=eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
         nodoActual.setHijoDerecho(supuestoHijoDerecho);
         return balancear(nodoActual);
      }
     
  //caso 1
    if(nodoActual.esHoja()){
      return (NodoBinario<K, V>) NodoBinario.nodoVacio();
     }
    
   //caso 2
    if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
       return balancear(nodoActual.getHijoDerecho());
     }
    
    if(!nodoActual.esVacioHijoIzquierdo()&& nodoActual.esVacioHijoDerecho()) {
    return balancear(nodoActual.getHijoIzquierdo());
     }
    
   //caso 3
   NodoBinario<K,V> nodoReemplazo=buscarSucesor(nodoActual.getHijoDerecho());
   
   NodoBinario<K,V> posibleHijo=eliminar(nodoActual.getHijoDerecho(),nodoReemplazo.getClave());
   nodoActual.setHijoDerecho(posibleHijo);
    
   nodoActual.setHijoDerecho(nodoVacioParaElArbol());
   nodoActual.setHijoIzquierdo(nodoVacioParaElArbol());
       
       return balancear(nodoReemplazo);
   
    }
      
}

   private NodoBinario<K, V> buscarSucesor(NodoBinario<K, V> nodoActual) {
      if(!nodoActual.esVacioHijoIzquierdo()){
      nodoActual=buscarSucesor(nodoActual.getHijoIzquierdo());
    }
      return nodoActual; 
    }
    
}

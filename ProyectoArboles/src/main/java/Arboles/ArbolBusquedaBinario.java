/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author brandon
 */
public class ArbolBusquedaBinario <K extends Comparable<K>,V> 
                implements IArbolBusqueda<K,V> {
    
    protected NodoBinario<K,V> raiz;
    
    public ArbolBusquedaBinario() {
    }

    public NodoBinario<K,V> nodoVacioParaElArbol(){
       return (NodoBinario<K,V>)NodoBinario.nodoVacio(); 
    }
    
    @Override
    public void insertar(K clave, V valor) throws ExceptionClaveYaExiste {
        if(esArbolVacio()){
           this.raiz= new NodoBinario<K,V>(clave,valor);
           return;
        }
        
        NodoBinario<K,V> nodoActual= this.raiz;
        NodoBinario<K,V> nodoAnterior= nodoVacioParaElArbol();
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
          nodoAnterior=nodoActual;
          if (clave.compareTo(nodoActual.getClave())>0){
             nodoActual= nodoActual.getHijoDerecho();
          }else if(clave.compareTo(nodoActual.getClave())<0) {
             nodoActual= nodoActual.getHijoIzquierdo();
          }else{
                throw new ExceptionClaveYaExiste("la clave que quiere inserta "
                        + "ya existe en el arbol");  
                }
            
        }
        NodoBinario<K,V> nuevoNodo=new NodoBinario<>(clave,valor);
        
        if (clave.compareTo(nodoAnterior.getClave())>0){
           nodoAnterior.setHijoDerecho(nuevoNodo);
        }else{
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }
        
      
    }         
         
         
    @Override
    public V eliminar(K clave) throws ExceptionClaveNoExiste {
       V valorRetornar= buscar(clave);
       if(valorRetornar==null){
           throw new ExceptionClaveNoExiste();
       }
       this.raiz=eliminar(this.raiz,clave);
       return valorRetornar;
    }

   private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual,K claveAEliminar)throws ExceptionClaveNoExiste{
       if(NodoBinario.esNodoVacio(nodoActual)){
           throw new ExceptionClaveNoExiste();
       }
       
       K claveActual= nodoActual.getClave();
       
       if(claveAEliminar.compareTo(claveActual)>0){
            NodoBinario<K,V> supuestoHijoDerecho=eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
           nodoActual.setHijoDerecho(supuestoHijoDerecho);
           return nodoActual;
       }
       
       if(claveAEliminar.compareTo(claveActual)<0){
           NodoBinario<K,V> supuestoHijoIzquierdo=eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
           nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
           return nodoActual;
       }
       
     //caso 1 si es hoja
         if(nodoActual.esHoja()){
             return nodoVacioParaElArbol();
         }
         
     // caso 2 si tiene hijo
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
         if(!nodoActual.esVacioHijoDerecho()&& nodoActual.esVacioHijoIzquierdo()){
            return nodoActual.getHijoDerecho();
        }

      // caso 3 si tiene 2 hijos
          NodoBinario<K,V> nodoReemplazo=buscarSucesor(nodoActual.getHijoDerecho());
          
          NodoBinario<K,V> posibleNuevoHijo=eliminar(nodoActual.getHijoDerecho(),nodoReemplazo.getClave());
         nodoActual.setHijoDerecho(posibleNuevoHijo);
         
         nodoReemplazo.setHijoDerecho(nodoActual.getHijoDerecho());
         nodoReemplazo.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
         /************************************************/
          nodoActual.setHijoDerecho(nodoVacioParaElArbol());
          nodoActual.setHijoIzquierdo(nodoVacioParaElArbol());
       
       return nodoReemplazo;
   }
   
   
    @Override
    public V buscar(K clave){
      NodoBinario<K,V> nodoActual= this.raiz;
     
      while(!NodoBinario.esNodoVacio(nodoActual)){
          if (clave.compareTo(nodoActual.getClave())>0){
             nodoActual= nodoActual.getHijoDerecho();
          }else if (clave.compareTo(nodoActual.getClave())<0){
             nodoActual= nodoActual.getHijoIzquierdo(); 
          }else{
              return nodoActual.getValor();
          }
      }
      
      return null;
    }

  
    
    @Override
    public boolean contiene(K clave) {
        return buscar(clave)!=null;
        }

    @Override
    public int size() {
      if (esArbolVacio()){
         return 0; 
      }
      int contadorDeNodos=0;  
      Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
      colaDeNodos.offer(this.raiz);
      
      while(!colaDeNodos.isEmpty()){
        NodoBinario<K,V> nodoActual= colaDeNodos.poll();
        contadorDeNodos=contadorDeNodos+1;
        
        if (!nodoActual.esVacioHijoIzquierdo()){
          colaDeNodos.offer(nodoActual.getHijoIzquierdo());   
        }
        
        if (!nodoActual.esVacioHijoDerecho()){
          colaDeNodos.offer(nodoActual.getHijoDerecho());   
        }
      }
        
        return contadorDeNodos;
  
    }

    @Override
    public int altura() {
      
      return  alturaRecursiva(this.raiz);
    }
    
     public int alturaRecursiva(NodoBinario<K,V> nodoActual){
       if(NodoBinario.esNodoVacio(nodoActual)){
           return 0;
       }  
       int alturaPorLaIzquierda= alturaRecursiva(nodoActual.getHijoIzquierdo());
       int alturaPorLaDerecha= alturaRecursiva(nodoActual.getHijoDerecho());
       if(alturaPorLaIzquierda>alturaPorLaDerecha){
           return alturaPorLaIzquierda + 1;
       }
       return alturaPorLaDerecha+1;
     }
     
    @Override
     public int alturaIterativa(){
       if (esArbolVacio()){
         return 0; 
       }
        
      Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
       colaDeNodos.offer(this.raiz);
      int alturaDelArbol=0;
      
      while(!colaDeNodos.isEmpty()){
        int nodosDelNivel=colaDeNodos.size();
        
        while(nodosDelNivel > 0){
          NodoBinario<K,V> nodoActual= colaDeNodos.poll();
        
         if (!nodoActual.esVacioHijoIzquierdo()){
          colaDeNodos.offer(nodoActual.getHijoIzquierdo());   
         }
        
         if (!nodoActual.esVacioHijoDerecho()){
          colaDeNodos.offer(nodoActual.getHijoDerecho());   
         }
        nodosDelNivel= nodosDelNivel - 1;
       }
      alturaDelArbol=alturaDelArbol + 1;
      }
        
        return alturaDelArbol;
     }

    @Override
    public void vaciar() {
       this.raiz=(NodoBinario<K,V>)NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
       return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int nivel() {
        return this.altura()-1;
    }

    @Override
    public List<K> recorridoEnInOrden() {
       List<K> recorrido=new LinkedList<>();
       if (esArbolVacio()){
           return recorrido;
       }

    Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
    NodoBinario<K,V> nodoActual=this.raiz;
     meterHijosPorIzquierda(nodoActual,pilaDeNodos);
     
    while(!pilaDeNodos.isEmpty()){
         nodoActual=pilaDeNodos.pop();
         recorrido.add(nodoActual.getClave());
         
         if(!nodoActual.esVacioHijoDerecho()){
            nodoActual=nodoActual.getHijoDerecho();
            meterHijosPorIzquierda(nodoActual, pilaDeNodos);
        }
    }
    return recorrido; 

    }
    
     public List<V> recorridoEnInOrdenParaValores() {
       List<V> recorrido=new LinkedList<>();
       if (esArbolVacio()){
           return recorrido;
       }

    Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
    NodoBinario<K,V> nodoActual=this.raiz;
     meterHijosPorIzquierda(nodoActual,pilaDeNodos);
     
    while(!pilaDeNodos.isEmpty()){
         nodoActual=pilaDeNodos.pop();
         recorrido.add(nodoActual.getValor());
         
         if(!nodoActual.esVacioHijoDerecho()){
            nodoActual=nodoActual.getHijoDerecho();
            meterHijosPorIzquierda(nodoActual, pilaDeNodos);
        }
    }
    return recorrido; 

    }
   
    void meterHijosPorIzquierda(NodoBinario<K,V> nodoActual,Stack<NodoBinario<K,V>> pilaDeNodos ){
         while(!NodoBinario.esNodoVacio(nodoActual)){
             pilaDeNodos.push(nodoActual);
             nodoActual=nodoActual.getHijoIzquierdo();
        }
    }
   
        void meterHijosPorDerecha(NodoBinario<K,V> nodoActual,Stack<NodoBinario<K,V>> pilaDeNodos ){
         while(!NodoBinario.esNodoVacio(nodoActual)){
             pilaDeNodos.push(nodoActual);
             nodoActual=nodoActual.getHijoDerecho();
        }
    }

    @Override
      public List<K> recorridoInOrdenRecursivo(){
        List<K> recorrido=new LinkedList<>();
        recorridoInOrdenRecursivoAmigo(this.raiz,recorrido);
        return recorrido;  
      }  
      
      public void recorridoInOrdenRecursivoAmigo(NodoBinario<K,V> nodoActual,List<K> recorrido){
          if (NodoBinario.esNodoVacio(nodoActual)){
             return ; 
          }
          
          recorridoInOrdenRecursivoAmigo(nodoActual.getHijoIzquierdo(), recorrido);
          recorrido.add(nodoActual.getClave());
          recorridoInOrdenRecursivoAmigo(nodoActual.getHijoDerecho(), recorrido);
      }
      
    @Override
    public List recorridoEnPreOrden() {
        List<K> recorrido= new LinkedList<>();
      if (esArbolVacio()){
          return recorrido;
      }
     
    Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
    pilaDeNodos.push(this.raiz);
    
    while(!pilaDeNodos.isEmpty()){
        NodoBinario<K,V> nodoActual=pilaDeNodos.pop();
        recorrido.add(nodoActual.getClave());
         if (!nodoActual.esVacioHijoDerecho()){
            pilaDeNodos.push(nodoActual.getHijoDerecho());
        }
         
        if (!nodoActual.esVacioHijoIzquierdo()){
           pilaDeNodos.push(nodoActual.getHijoIzquierdo());
        }
      
    }
    return recorrido; 
    }

    public List recorridoEnPreOrdenParaValore() {
        List<V> recorrido= new LinkedList<>();
      if (esArbolVacio()){
          return recorrido;
      }
     
    Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
    pilaDeNodos.push(this.raiz);
    
    while(!pilaDeNodos.isEmpty()){
        NodoBinario<K,V> nodoActual=pilaDeNodos.pop();
        recorrido.add(nodoActual.getValor());
         if (!nodoActual.esVacioHijoDerecho()){
            pilaDeNodos.push(nodoActual.getHijoDerecho());
        }
         
        if (!nodoActual.esVacioHijoIzquierdo()){
           pilaDeNodos.push(nodoActual.getHijoIzquierdo());
        }
      
    }
    return recorrido; 
    }
    
    @Override
     public List<K> recorridoPreOrdenRecursivo(){
        List<K> recorrido=new LinkedList<>();
        recorridoPreOrdenRecursivo(this.raiz,recorrido);
        return recorrido;  
      }  
      
      public void recorridoPreOrdenRecursivo(NodoBinario<K,V> nodoActual,List<K> recorrido){
          if (NodoBinario.esNodoVacio(nodoActual)){
             return ; 
          }
          
          recorrido.add(nodoActual.getClave());
          recorridoInOrdenRecursivoAmigo(nodoActual.getHijoIzquierdo(), recorrido);
          recorridoInOrdenRecursivoAmigo(nodoActual.getHijoDerecho(), recorrido);
      }
      
       public void ayuda(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
       while(!NodoBinario.esNodoVacio(nodoActual)){
           pilaDeNodos.push(nodoActual);
             if (!nodoActual.esVacioHijoIzquierdo()){
                 nodoActual=nodoActual.getHijoIzquierdo();
             }else{
                 nodoActual=nodoActual.getHijoDerecho();
             }
       }
    }
      
    @Override
    public List<K> recorridoEnPostOrdenIterativo() {
       List<K> recorrido=new LinkedList<>();
        if (esArbolVacio()){
            return recorrido;
        }
       Stack<NodoBinario<K,V>> pilaDeNodos=new Stack<>();
       NodoBinario<K,V> nodoActual=this.raiz;
       this.ayuda(pilaDeNodos,nodoActual); 
       
        while(!pilaDeNodos.isEmpty()){
            nodoActual=pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!pilaDeNodos.isEmpty()){
                NodoBinario<K,V> tope=pilaDeNodos.peek();
                if(!tope.esVacioHijoDerecho() && tope.getHijoDerecho()!= nodoActual){
                    this.ayuda(pilaDeNodos, tope.getHijoDerecho());
                }
            }
        }
           return recorrido; 
    }
    
    @Override
        public List<K> recorridoEnPostOrden(){
        List<K> recorrido=new LinkedList<>();
        recorridoPostOrdenRecursivo(this.raiz,recorrido);
        return recorrido;  
      }  
      
      public void recorridoPostOrdenRecursivo(NodoBinario<K,V> nodoActual,List<K> recorrido){
          if (NodoBinario.esNodoVacio(nodoActual)){
             return; 
          }
          
          recorridoPostOrdenRecursivo(nodoActual.getHijoIzquierdo(), recorrido);
          recorridoPostOrdenRecursivo(nodoActual.getHijoDerecho(), recorrido);
          recorrido.add(nodoActual.getClave());
          
      }
      
    
    @Override
    public List<K> recorridoPorNiveles() {
      List<K> recorrido= new LinkedList<>();
      if (esArbolVacio()){
         return recorrido; 
      }
        
      Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
      colaDeNodos.offer(this.raiz);
      
      while(!colaDeNodos.isEmpty()){
        NodoBinario<K,V> nodoActual= colaDeNodos.poll();
        recorrido.add(nodoActual.getClave());
        
        if (!nodoActual.esVacioHijoIzquierdo()){
          colaDeNodos.offer(nodoActual.getHijoIzquierdo());   
        }
        
        if (!nodoActual.esVacioHijoDerecho()){
          colaDeNodos.offer(nodoActual.getHijoDerecho());   
        }
      }
        
        return recorrido;
    }
    
   
     private NodoBinario<K, V> buscarSucesor(NodoBinario<K, V> nodoActual) {
      if(!nodoActual.esVacioHijoIzquierdo()){
      nodoActual=buscarSucesor(nodoActual.getHijoIzquierdo());
    }
      return nodoActual; 
    }


 
   @Override 
     public String toString() {
        return this.generarCadenaDeArbol(this.raiz, "", true);
    }
    
    private String generarCadenaDeArbol(NodoBinario<K,V> nodoActual,
        String prefijo, boolean ponerCodo) {
        StringBuilder cadena = new StringBuilder();
        cadena.append(prefijo);
        
        if (prefijo.length() == 0) {
            cadena.append(ponerCodo ? "└──(R)" : "├──(R)"); //arbol vacio o no
        } else {
            cadena.append(ponerCodo ? "└──(D)" : "├──(I)");  //derecha o izquierda
        }
        if (NodoBinario.esNodoVacio(nodoActual)) {
            cadena.append("╣\n");
            return cadena.toString();
        }
        cadena.append(nodoActual.getClave().toString());
        cadena.append("\n");

        NodoBinario<K,V> nodoIzq = nodoActual.getHijoIzquierdo();
        String prefijoAux = prefijo + (ponerCodo ? "   ":"|   ");
        cadena.append(generarCadenaDeArbol(nodoIzq, prefijoAux, false));

        NodoBinario<K,V> nodoDer = nodoActual.getHijoDerecho();
        cadena.append(generarCadenaDeArbol(nodoDer, prefijoAux, true));

        return cadena.toString();
    }
  
    
}

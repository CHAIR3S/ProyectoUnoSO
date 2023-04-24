package main;

import clases.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class ProyectoUno {
    public static void main(String[] args){
        //Creamos la lista con la clase nodo.
        List<Nodo> list = new ArrayList<>();
        int numProceso, tamaño, tiempo, separacion;
        int matriz[][];
        String opcion = null;
        String nomProceso = null;
        String orden = "sin orden";
        //Este blucle sera el encargado de mostrar nuesto menu.
        do{
            try{
                opcion = JOptionPane.showInputDialog(null,
                        "1. Captura de datos."
                                + "\n2. Ordenar lista por tamaño."
                                + "\n3. Ordenar lista por tiempo."
                                + "\n4. Ordenar lista por numero de proceso."
                                + "\n5. Mostrar lista."+"\n6. Salir",
                        "Menu de opciones",1);
                
                switch(opcion){
                    case "1":
                        try{
                            numProceso = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero del proceso"));
                            nomProceso = JOptionPane.showInputDialog("Ingresa el nombre");
                            tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el tamaño del proceso"));
                            tiempo = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el tiempo del proceso",null));
                            list.add(new Nodo(numProceso,nomProceso,tamaño,tiempo));
                            
                        }catch(Exception e){ 
                            JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
                        }
                        break;
                    case "2":   
                        Collections.sort(list,  (Nodo a, Nodo b) -> a.tamaño.compareTo(b.tamaño));
                        orden = "tamaño";
                        break;
                    case "3":
                        Collections.sort(list,  (Nodo a, Nodo b) -> a.tiempo.compareTo(b.tiempo));
                        orden = "tiempo";
                        break;
                    case "4":
                        Collections.sort(list,  (Nodo a, Nodo b) -> a.numProceso.compareTo(b.numProceso));
                        orden = "número de proceso";
                        break;
                    case "5":
                        System.out.println("");
                        System.out.println("<------ Procesos ------>");
                        System.out.println("Lista ordenada por: "+ orden );
                        System.out.println(String.format("%-16s %-20s %-10s %-10s %-16s %-16s %-16s", "Numero proceso", "Nombre proceso", "Tamaño", "Tiempo", "Tiempo entrada", "Tiempo salida", "Tiempo en el sistema"));
                        
                        matriz = concatenar(list);
                        
                        int columna = 0;
                        for (Nodo nodo : list) {
                            System.out.print(nodo.toString());
                            System.out.print(String.format("%-16s %-16s %-16s", matriz[0][columna], matriz[1][columna], matriz[2][columna]));
                            columna++;
                            System.out.println("");
                        }
                        
                        System.out.println("");
                        
                        
                        break;
                    case "6":
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción incorrecta");
                }
                
            }catch(Exception e){
                if (opcion != null) {
                    JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
                }
                else
                {
                    opcion = "6";
                }
            }
        }while(opcion!="6");
    }
    
    
    public static int[][] concatenar(List<Nodo> list){
        int fila, columna;
        Nodo[] arrayNodos = listaArray(list);
        int[][] matriz = new int[3][list.size()];
        
        for(columna = 0; columna<matriz[0].length; columna++){
            for(fila = 0; fila<matriz.length; fila++){
                switch(fila){
                    case 0: 
                        if(columna == 0)
                            matriz[0][0] = 0;
                        else
                            matriz[fila][columna] = matriz[fila+1][columna-1];
                        break;
                    case 1:
                        if(columna == 0)
                            matriz[1][0] = arrayNodos[columna].tiempo;
                        else
                            matriz[fila][columna] = matriz[fila][columna-1] + arrayNodos[columna].tiempo;
                        break;
                    case 2:
                        matriz[fila][columna] = matriz[fila-1][columna];
                }
                    
                        
            }
        }
        
        return matriz;
    }
    
    public static Nodo[] listaArray(List<Nodo> lista){
        int contador = 0;
        Nodo[] arrayNodos = new Nodo[lista.size()];
        
        for (Nodo nodo : lista) {
            arrayNodos[contador] = nodo;
            contador++;
        }
        
        return arrayNodos;
    }
}

package main;

import clases.Nodo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class ProyectoUno {
    public static void main(String[] args){
        //Creamos la lista con la clase nodo.
        List<Nodo> list = new ArrayList<>();
        int numProceso, tamaño, tiempo, separacion;
        String opcion = null;
        boolean rehusar = false;
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
                                + "\n5. Mostrar lista."
                                + "\n6. Imprimir listas en archivo"
                                + "\n7. Salir",
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
                        
                        System.out.println(retornarImpresion(orden, list));
                        
                        break;
                    case "6":
                        
                        String imprimir = retornarImpresion(orden, list);
                        
                        imprimirArchivo(imprimir, rehusar);
                        
                        rehusar = true;
                        
                        break;
                    case "7":
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
                    opcion = "7";
                }
            }
        }while(opcion!="7");
    }
    
    
    public static int[][] concatenar(List<Nodo> list){
        int fila, columna, contador = 0, contadorTiempo = 0, index, tiempoMenor;
        Nodo[] arrayNodos = listaArray(list);
        int[][] matriz = new int[2][list.size()];
        int[] tiempos = new int[3];
        
        for(fila = 0; fila<matriz[0].length; fila++){
            for(columna = 0; columna<matriz.length; columna++){
                
                
                
                switch(columna){
                    case 0: 
                        if(fila < 3){
                            matriz[columna][fila] = 0;
                        }
                        else
                        {
                                                     
                            contadorTiempo+= tiempos[menorNumero(tiempos)];
                            tiempoMenor = tiempos[menorNumero(tiempos)];
                            index = menorNumero(tiempos);
                            
                            matriz[columna][fila] = contadorTiempo;
                            
                            for(int i = 0; i<tiempos.length; i++){
                                tiempos[i] = tiempos[i] - tiempoMenor;
                            }
                            
                            tiempos[index] = arrayNodos[fila].tiempo;
                            
                        }    
                            
                        break;
                    case 1:
                        if(fila < 3){
                            matriz[columna][fila] = arrayNodos[fila].tiempo;
                            tiempos[fila] = matriz[columna][fila];
                        }
                        else
                            matriz[columna][fila] = matriz[columna-1][fila] + arrayNodos[fila].tiempo;
                        break;
                }
                    
                        
            }
            
//            if(fila >= 3){
//                
//                for(int i = 0; i<tiempos.length; i++){
//                    tiempos[i] = tiempos[i] - arrayNodos[fila].tiempo;
//                }
//            }
            
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
    
    public static int menorNumero(int[] arreglo){
        int menor = arreglo[0];
        int index = 0, contador = 0;
        
        for(int i = 0; i<arreglo.length-1; i++){
            if(menor > arreglo[i+1]){
                menor = arreglo[i+1];
                index = contador+1;
            }
            contador++;
        }
        
        return index;
    }
    
    public static String retornarImpresion(String orden, List<Nodo> list) {
        
        int matriz[][];

        String impresion;
        
        impresion = "";
        impresion+= "\n<------ Procesos ------>";
        impresion+= "\nLista ordenada por: " + orden;
        impresion+= "\n" + String.format("%-16s %-20s %-10s %-10s %-16s %-16s", "Numero proceso", "Nombre proceso", "Tamaño", "Tiempo", "Tiempo entrada", "Tiempo salida");

        matriz = concatenar(list); //Concatena todos los datos en una sola matriz y regresa una matriz

        int columna = 0;
        for (Nodo nodo : list) {
            impresion+= "\n" + nodo.toString();
            impresion+= String.format("%-16s %-16s", matriz[0][columna], matriz[1][columna]);
            columna++;
            impresion+= "";
        }

        impresion+= "";
        
        
        return impresion;
    }
    
    public static void imprimirArchivo(String impresion, boolean rehusar){
        BufferedWriter bw = null;
		try {
			File fichero = new File("Procesos.txt");

			System.out.println("Archivo impreso en:\n" + fichero.getCanonicalPath()); // Path completo donde se creará el fichero.
                        System.out.println("");
                        
			bw = new BufferedWriter(new FileWriter("Procesos.txt", rehusar));
			bw.write(impresion);
                        bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close(); // Cerramos el buffer
			} catch (Exception e) {
			}
		}
    }
}

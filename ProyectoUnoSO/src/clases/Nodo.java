package clases;

public class Nodo{
    
    public Integer numProceso;
    public String nomProceso;
    public Integer tamaño;
    public Integer tiempo;
    
    public Nodo(Integer numProceso, String nomProceso, Integer tamaño, Integer tiempo){
        this.numProceso = numProceso;
        this.nomProceso = nomProceso;
        this.tamaño = tamaño;
        this.tiempo = tiempo;
    }

    public Integer getNumProceso() {
        return numProceso;
    }

    public String getNomProceso() {
        return nomProceso;
    }

    public Integer getTamaño() {
        return tamaño;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    @Override
    public String toString(){
        String cadenaNodo = String.format("%-16s %-20s %-10s %-11s", numProceso, nomProceso, tamaño, tiempo);
        
        return cadenaNodo;
    }
}

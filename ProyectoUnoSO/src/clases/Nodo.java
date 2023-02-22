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
        return "["+"Numero de Proceso: "+numProceso+" Nombre del proceso: "+nomProceso+" Tamaño del proceso: "+tamaño+" Tiempo del proceso: "+tiempo+"]------>";
    }
}

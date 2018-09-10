package com.example.eduardo.citascolor;

public class GeneradorCita {
    private Cita[] arregloCitas;

    GeneradorCita(){
        arregloCitas = new Cita[5];

        Cita crear = new Cita();
        crear.setAutor("Autor 0");
        crear.setTexto("Cita 0");
        arregloCitas[0] = crear;

        crear = new Cita();
        crear.setAutor("Autor 1");
        crear.setTexto("Cita 1");
        arregloCitas[1] = crear;

        crear = new Cita();
        crear.setAutor("Autor 2");
        crear.setTexto("Cita 2");
        arregloCitas[2] = crear;

        crear = new Cita();
        crear.setAutor("Autor 3");
        crear.setTexto("Cita 3");
        arregloCitas[3] = crear;

        crear = new Cita();
        crear.setAutor("Autor 4");
        crear.setTexto("Cita 4");
        arregloCitas[4] = crear;
    }

    public Cita getCitaAleatoria(){
        int index = Utils.getNumeroAleatorio(5);
        return arregloCitas[index];
    }
}

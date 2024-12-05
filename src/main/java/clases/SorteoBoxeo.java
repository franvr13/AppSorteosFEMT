package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SorteoBoxeo {
    private List<Participante> participantes;
    private List<Categoria> categorias;

    public SorteoBoxeo() {
        this.participantes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public void agregarParticipante(String nombre) {
        Participante participante = new Participante(nombre);
        participantes.add(participante);
    }

    public void agregarCategoria(String nombre) {
        Categoria categoria = new Categoria(nombre);
        categorias.add(categoria);
    }

    public void realizarSorteo() {
        if (participantes.size() < 2 || categorias.size() < 1) {
            System.out.println("No hay suficientes participantes o categorías para realizar el sorteo.");
            return;
        }

        Collections.shuffle(participantes);

        for (Categoria categoria : categorias) {
            System.out.println("Categoria: " + categoria.getNombre());

            int numParticipantes = participantes.size();
            int numCombates = numParticipantes / 2;

            for (int i = 0; i < numCombates; i++) {
                Participante participante1 = participantes.get(i);
                Participante participante2 = participantes.get(numParticipantes - i - 1);

                System.out.println("Combate " + (i + 1) + ": " + participante1.getNombre() + " vs " + participante2.getNombre());
            }

            if (numParticipantes % 2 != 0) {
                Participante participante = participantes.get(numCombates);
                System.out.println("Participante libre: " + participante.getNombre());
            }
        }
    }
}
package br.com.brduoo.diarioonline;


// import imglib.*;
import java.util.*;

public class Lista {
    private Vector<Artefato> L;
    private int numArtefatos;

    public Lista() {
        L = new Vector<Artefato>();
        numArtefatos=0;
    }

    public int busca(Ponto p) {
        int i;
        for (i=0; i < numArtefatos; i++) {
            if (L.get(i).buscaPonto(p)) {
                return i;
            }
        }
        return -1;
    }

    /* @ return Artefato */
    public Artefato pega(int i) {
        try {
            return L.get(i);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void insere(Artefato A) {
        L.add(A);
        numArtefatos++;
    }

    public int tamanho() {
        return numArtefatos;
    }
    public void remove(int k) {
        L.remove(k);
        numArtefatos--;
    }
}
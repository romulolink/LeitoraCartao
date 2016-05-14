package br.com.brduoo.diarioonline;

import java.util.*;

public class Artefato {
    private Vector<Ponto> pontos;
    private int numPontos;
    private Ponto centro;

    public Artefato() {
        pontos = new Vector<Ponto>();
        numPontos = 0;
        centro = new Ponto(0,0);
    }

    public void mudaCentro(int px, int py) {
        centro.setxy(px, py);
    }

    public void inserePonto(Ponto p) {
        pontos.add(p);
        numPontos++;
    }

    public void calcCentro() {
        int sx=0;
        int sy=0;
        int cx;
        int cy;
        int i;
        Ponto p;
        for (i=0; i < numPontos; i++) {
            p = pontos.get(i);
            sx += p.x();
            sy += p.y();
        }
        cx = (int)Math.round(sx/numPontos);
        cy = (int)Math.round(sy/numPontos);

        centro.setxy(cx, cy);
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public boolean buscaPonto(Ponto p) {
        int i;
        for (i=0; i < numPontos; i++) {
            if (pontos.get(i).vizinho(p)) {
                return true;
            }
        }
        return false;
    }

    public int tamanho() {
        return numPontos;
    }

    public Ponto pega(int k) {
        return pontos.get(k);
    }



    public void remove(int k) {
        pontos.remove(k);
        numPontos--;
    }
}

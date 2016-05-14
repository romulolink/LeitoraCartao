package br.com.brduoo.diarioonline;


public class Ponto {
    private int x;
    private int y;
    private int[] rgb;

    public Ponto() {
        x = 0;
        y = 0;
        rgb = new int[3];
        rgb[0] = 0;
        rgb[1] = 0;
        rgb[2] = 0;
    }
    public Ponto(int px, int py) {
        x = px;
        y = py;
        rgb = new int[3];
        rgb[0] = 0;
        rgb[1] = 0;
        rgb[2] = 0;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setx(int px) {
        x = px;
    }

    public void sety(int py) {
        y = py;
    }

    public void setxy(int px, int py) {
        x = px;
        y = py;
    }
    public double distancia(Ponto p) {
        int dx = x - p.x();
        int dy = y - p.y();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public boolean vizinho(Ponto p) {
        int dx = Math.abs(x - p.x());
        int dy = Math.abs(y - p.y());
        return (dx <= 1) && (dy <= 1);
    }
    public boolean igual(Ponto p) {
        return (x==p.x()) && (y==p.y());
    }
}

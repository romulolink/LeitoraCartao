package br.com.brduoo.diarioonline;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.*;
import java.awt.color.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class cImagem {

    private BufferedImage img;
    private int W;
    private int H;
    private Graphics2D g;
    private String arq;

    // RGB to HEX ?
    public int[] Pixel(int x, int y) {
        int pixel;
        int rgb[] = new int[3];
        pixel = img.getRGB(x, y);
        rgb[0] = (pixel >> 16) & 0xFF;
        rgb[1] = (pixel >> 8) & 0xFF;
        rgb[2] = pixel & 0xFF;
        return rgb;
    }
    public cImagem(String arquivo) {
        try {
            img = ImageIO.read(new File(arquivo));
            W = img.getWidth();
            H = img.getHeight();
            arq = arquivo;
            g = img.createGraphics();
        } catch (IOException e) {
            System.out.println(e);
            img = null;
        }
    }
    public cImagem(int w, int h) {
        img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        W = w;
        H = h;
        g = img.createGraphics();
    }

    public void salva(String s) {
        try {
            if (s.equals("")) {
                ImageIO.write(img, "png", new File(arq));
            } else {
                ImageIO.write(img, "png", new File(s));
            }
        } catch (IOException e) {

        }
    }
    public int pegaLargura() {
        return W;
    }
    public int pegaAltura() {
        return H;
    }

    public void setaPixel(int x, int y, int[] pix){
        img.setRGB(x, y, pix[0]<<16 | pix[1]<<8 | pix[2]);
    }
    public double brilho() {
        int x, y;
        int[] pix;
        double tdc, s;

        s = 0;
        for (y=0; y < H; y++) {
            for (x=0; x < W; x++) {
                pix = Pixel(x,y);
                tdc = (pix[0]+pix[1]+pix[2])/3.0;
                s = s + tdc;
            }
        }
        return s/(W*H);
    }

    public double contraste(){
        double brilho = brilho(), var;
        int x, y;
        int[] pix;
        double tdc, s;

        s = 0;
        for (y=0; y < H; y++) {
            for (x=0; x < W; x++) {
                pix = Pixel(x,y);
                tdc = (pix[0]+pix[1]+pix[2])/3.0;
                s = s+ Math.pow(tdc-brilho,2.0);
            }
        }
        var = s/(W*H);
        return Math.sqrt(var);
    }
    public void estatistica(){

        int x, y;
        int[] pix;
        int tdc;
        int[] cont = new int[256];

        for (x=0; x < 256; x++) {
            cont[x] = 0;
        }
        for (y=0; y < H; y++) {
            for (x=0; x < W; x++) {
                pix = Pixel(x,y);
                tdc = (int)Math.floor((pix[0]+pix[1]+pix[2])/3.0);
                cont[tdc]++;
            }
        }
        for (x=0; x < 256; x++) {
            System.out.println(x+": "+cont[x]);
        }
    }
}


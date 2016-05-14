
package br.com.brduoo.diarioonline;

/*
*  Foram realizados os seguintes testes:
*  A propria imagem fornecida
*  Marcações a mais adicionadas a imagem fornecida
*  Anulação de jogos na imagem fornecida
* */
public class Megasena {


    public static int minOcorrencias = 350;
    public static boolean cinza(int[] pix) {

        int intervalo = 40;

        /*
        As marcações estão em tonalidades de cinza logo:
        O Cinza é um Trio de números RGB que o modulo da diferença entre eles está em um intervalo curto.
         */
        return    pix[0] > 0 && // diferente de preto
                ((Math.abs(pix[0] - pix[1])) < intervalo) &&
                ((Math.abs(pix[0] - pix[2])) < intervalo) &&
                ((Math.abs(pix[1] - pix[2])) < intervalo) &&
                ( pix[0] + pix[1] + pix[2]) / 3 < 105  ; // seleciona tons de cinza mais escuros


    }

    public static boolean preto(int[] pix) {
        return pix[0] == 0 && pix[1] == 0 && pix[2] == 0;
    }

    public static  boolean isEquals(int x, int y){

        return x == y;
    }


    /*
    Encontra todas as ocorrencias na mesma linha entre os artefatos, deve haver pelo menos 100 ocorrencias entre os artefatos.
     */
    public static boolean comparaPontos(Artefato A, Artefato M) {

        int m = 0;
        int n = 0;
        int acc = 0;

        for (m = 0; m < A.tamanho(); m++) {

            for (n = 0; n < M.tamanho(); n++) {

                // verifica se estão na mesma linha
                if (A.pega(m).y() == M.pega(n).y()) {

                    // pelo menos cem ocorrencias no mesmo artefato
                    // Evitar marcação de Bola que perternce a outra linha
                    if(acc == minOcorrencias){
                      //  System.out.printf("Ocorrencia: x= " + A.pega(m).y() + " y= " + M.pega(n).y() + " ");

                        return true;
                    }
                    acc++;


                }


            }

        }
        return false;


    }



    public static void main(String[] args) {

        cImagem img = new cImagem("c:/assets/megasena_manual.png");


        int W = img.pegaLargura();
        int H = img.pegaAltura();
        int x;
        int y;
        int[] pix;
        int k,i,j;

        Lista Marcacoes = new Lista();
        Lista Bolinhas = new Lista();
        Ponto ptocinza;
        Artefato A;
        Artefato M;
        Artefato Base;




        // Encontrar Marcações
        for (y=0; y < H; y++) {  // Define o início para um posição definida para evitar tons de cinza em outras partes
            for (x = 0; x < W/26; x++) { // Limita o intrevalo da coluna para evitar tons de cinza em outras partes da imagen
                pix = img.Pixel(x, y);
                if (preto(pix)) {
                    System.out.printf("r = " + pix[0] + " g= " + pix[1] + " b = " + pix[2] + "\n");

                    ptocinza = new Ponto(x,y);
                    k = Marcacoes.busca(ptocinza);
                    if (k >= 0) {
                        Base = Marcacoes.pega(k);
                        Base.inserePonto(ptocinza);
                        i=k+1;
                        A = Marcacoes.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptocinza)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Marcacoes.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Marcacoes.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptocinza);
                        Marcacoes.insere(A);
                        System.out.println("Novo artefato " + Marcacoes.tamanho() + " x= " + A.pega(0).x() + " y=" +  A.pega(0).y() );
                    }
                }
            }
        }


        // Encontra Bolinhas
        for (y=0; y < H; y++) {
            for (x = 0; x < W; x++) {
                pix = img.Pixel(x, y);
                if (cinza(pix)) {

                    ptocinza = new Ponto(x,y);
                    k = Bolinhas.busca(ptocinza);
                    if (k >= 0) {
                        Base = Bolinhas.pega(k);
                        Base.inserePonto(ptocinza);
                        i=k+1;
                        A = Bolinhas.pega(i);
                        while (A != null) {
                            if (A.buscaPonto(ptocinza)) {
                                for (j=0; j < A.tamanho(); j++) {
                                    Base.inserePonto(A.pega(j));
                                }
                                Bolinhas.remove(i);
                                System.out.println("Agrupou");
                            }
                            i++;
                            A = Bolinhas.pega(i);
                        }
                    } else {
                        A = new Artefato();
                        A.inserePonto(ptocinza);
                        Bolinhas.insere(A);
                        System.out.println("Novo artefato " + Bolinhas.tamanho());
                    }
                }
            }
        }


        int numOcorrencias =0;
        boolean naopassou = true;

        System.out.printf("Comparações: \n");

        // Percorre todos as Marcadores e Bolinhas
        for (i=0; i < Marcacoes.tamanho(); i++) {

            naopassou = true;
            for (j=0; j < Bolinhas.tamanho(); j++){

                A = Marcacoes.pega(i);
                M = Bolinhas.pega(j);


                // Compara todos os Pontos dos Marcacoes, caso encontre pelo menos cem ocorrencias na mesma linha,
                // para que não se contabilize a marcação de outra linha, retorna true;
                if(comparaPontos(A,M)){

                    //System.out.printf("Houve uma occorencia de Linha x Marcação em \n");

                    // A variável i identifica a linha
                    if(i == 1){
                        System.out.printf("---------------------- NÚMEROS ESCOLHIDOS NO JOGO1 ----------------------\n");

                    }

                    // Se passar pela linha  8 significa que foi encontrada uma ocorrencia em comparaPontos
                    if(i == 7){
                        System.out.printf("\nO Jogo 1 foi Anulado\n");
                    }

                    // se a linha foi a de 1 à 7
                    else if(i > 0 && i < 8) {

                        // O número é igual a posicao x da linha
                        // dividido pela proporcao da coluna ( W /12 ) somado ao numero da linha vezes 10
                        //  ex. 2 + (1 * 10) = número 2 foi marcado
                        System.out.printf((Math.round(M.pega(0).x() / (W /12))) + ((i-1) * 10) + " - ");
                    }

                    if(i == 9 && naopassou){
                        naopassou = false;
                        System.out.printf("\n---------------------- NÚMEROS ESCOLHIDOS NO JOGO2 ----------------------\n");

                    }


                    if(i == 14){
                        System.out.printf("\nO Jogo 2 foi Anulado\n");
                    }


                    else if(i > 7 && i < 15) {

                        System.out.printf(Math.round(M.pega(0).x() / (W /12)) + (i-8) * 10 + " - ");
                    }


                    if(i == 15 && naopassou){
                        naopassou = false;
                        System.out.printf("\n---------------------- NÚMEROS ESCOLHIDOS NO JOGO3 ----------------------\n");

                    }


                    if(i == 21){
                        System.out.printf("\nO Jogo 2 foi Anulado\n");
                    }


                    else if(i > 14 && i < 21) {

                        System.out.printf(Math.round(M.pega(0).x() / (W /12)) + (i-15) * 10 + " - ");
                    }



                }





            }


        }





        /*// Exibe Marcacoes iguais agrupados
        for (Enumeration e=C.keys(); e.hasMoreElements();) {
            // editar por romulo em 08/12/2014
            z = (Integer)e.nextElement();
           //  System.out.println("Index: " + z + ": Marcacoes " + C.get(z));

        }*/

        System.out.printf("\nÁrea da Imagens de  W= " + W + " e H = " + H);

        System.out.printf("\nTotal Marcações na area demilitada:" + Marcacoes.tamanho());

        System.out.printf(" \n");

        System.out.printf("Total Circulos:" + Bolinhas.tamanho());

        System.out.printf(" \n");

        System.out.printf("Total Ocorrencias:" + numOcorrencias);

    }





}


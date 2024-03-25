package tabuleiro;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pedro.piva
 */
public class Jogada {

    private final ArrayList<Integer> jogadasI = new ArrayList();//jogadas na posicao I
    private final ArrayList<Integer> jogadasJ = new ArrayList();//jogadas na posicao J as duas se completam para gerar uma posicao
    private final ArrayList<Quadrado> quadrados = new ArrayList();//os 4 quadrados do tabuleiro
    private final int[][] centro = new int[4][2];//os centros dos quadrados para marcar o ponto

    public Jogada() {
        jogadasI.add(0);//adiciona as posicoes da jogada 1
        jogadasJ.add(1);

        jogadasI.add(0);//adiciona as posicoes da jogada 2
        jogadasJ.add(3);

        jogadasI.add(1);//adiciona as posicoes da jogada 3
        jogadasJ.add(0);

        jogadasI.add(1);//adiciona as posicoes da jogada 4
        jogadasJ.add(2);

        jogadasI.add(1);//adiciona as posicoes da jogada 5
        jogadasJ.add(4);

        jogadasI.add(2);//adiciona as posicoes da jogada 6
        jogadasJ.add(1);

        jogadasI.add(2);//adiciona as posicoes da jogada 7
        jogadasJ.add(3);

        jogadasI.add(3);//adiciona as posicoes da jogada 8
        jogadasJ.add(0);

        jogadasI.add(3);//adiciona as posicoes da jogada 9
        jogadasJ.add(2);

        jogadasI.add(3);//adiciona as posicoes da jogada 10
        jogadasJ.add(4);

        jogadasI.add(4);//adiciona as posicoes da jogada 11
        jogadasJ.add(1);

        jogadasI.add(4);//adiciona as posicoes da jogada 12
        jogadasJ.add(3);

        centro[0][0] = 1;
        centro[0][1] = 1;

        centro[1][0] = 1;
        centro[1][1] = 3;

        centro[2][0] = 3;
        centro[2][1] = 1;

        centro[3][0] = 3;
        centro[3][1] = 3;

        quadrados.add(new Quadrado(new Arestas(0, 0)));//cria o quadrado 1
        quadrados.add(new Quadrado(new Arestas(0, 0)));//cria o quadrado 2
        quadrados.add(new Quadrado(new Arestas(0, 0)));//cria o quadrado 3
        quadrados.add(new Quadrado(new Arestas(0, 0)));//cria o quadrado 4

        quadrados.get(0).addLinha(0, 1);//adiciona as pocisoes dos lados do quadrado 1
        quadrados.get(0).addLinha(1, 0);
        quadrados.get(0).addLinha(1, 2);
        quadrados.get(0).addLinha(2, 1);

        quadrados.get(1).addLinha(1, 2);//adiciona as pocisoes dos lados do quadrado 2
        quadrados.get(1).addLinha(0, 3);
        quadrados.get(1).addLinha(2, 3);
        quadrados.get(1).addLinha(1, 4);

        quadrados.get(2).addLinha(2, 1);//adiciona as pocisoes dos lados do quadrado 3
        quadrados.get(2).addLinha(3, 0);
        quadrados.get(2).addLinha(3, 2);
        quadrados.get(2).addLinha(4, 1);

        quadrados.get(3).addLinha(2, 3);//adiciona as pocisoes dos lados do quadrado 4
        quadrados.get(3).addLinha(3, 2);
        quadrados.get(3).addLinha(3, 4);
        quadrados.get(3).addLinha(4, 3);
    }

    public void removeJogada(int i, int j) {
        int index = jogadaEquivalente(i, j);
        jogadasI.remove(index);
        jogadasJ.remove(index);
    }

    public int getI(int index) {//retorna a posicao I da jogada
        return jogadasI.get(index);
    }

    public int getJ(int index) {//retorna a posicao J da jogada
        return jogadasJ.get(index);
    }

    public ArrayList<Integer> qualQuadrado(int jogada) {//procura em qual quadrado aquela jogada esta
        ArrayList<Integer> saida = new ArrayList();//cria um array de posicoes dos quadrados
        if ((quadrados.get(0).getLinha(0).getX() == getI(jogada) && quadrados.get(0).getLinha(0).getY() == getJ(jogada)) //verifica se a jogada foi no quadrado 1
                || (quadrados.get(0).getLinha(1).getX() == getI(jogada) && quadrados.get(0).getLinha(1).getY() == getJ(jogada))
                || (quadrados.get(0).getLinha(2).getX() == getI(jogada) && quadrados.get(0).getLinha(2).getY() == getJ(jogada))
                || (quadrados.get(0).getLinha(3).getX() == getI(jogada) && quadrados.get(0).getLinha(3).getY() == getJ(jogada))) {
            saida.add(0);//se for adiciona a posicao dele na array saida
        }
        if ((quadrados.get(1).getLinha(0).getX() == getI(jogada) && quadrados.get(1).getLinha(0).getY() == getJ(jogada))//verifica se a jogada foi no quadrado 2
                || (quadrados.get(1).getLinha(1).getX() == getI(jogada) && quadrados.get(1).getLinha(1).getY() == getJ(jogada))
                || (quadrados.get(1).getLinha(2).getX() == getI(jogada) && quadrados.get(1).getLinha(2).getY() == getJ(jogada))
                || (quadrados.get(1).getLinha(3).getX() == getI(jogada) && quadrados.get(1).getLinha(3).getY() == getJ(jogada))) {
            saida.add(1);//se for adiciona a posicao dele na array saida
        }
        if ((quadrados.get(2).getLinha(0).getX() == getI(jogada) && quadrados.get(2).getLinha(0).getY() == getJ(jogada))//verifica se a jogada foi no quadrado 3
                || (quadrados.get(2).getLinha(1).getX() == getI(jogada) && quadrados.get(2).getLinha(1).getY() == getJ(jogada))
                || (quadrados.get(2).getLinha(2).getX() == getI(jogada) && quadrados.get(2).getLinha(2).getY() == getJ(jogada))
                || (quadrados.get(2).getLinha(3).getX() == getI(jogada) && quadrados.get(2).getLinha(3).getY() == getJ(jogada))) {
            saida.add(2);//se for adiciona a posicao dele na array saida
        }
        if ((quadrados.get(3).getLinha(0).getX() == getI(jogada) && quadrados.get(3).getLinha(0).getY() == getJ(jogada))//verifica se a jogada foi no quadrado 4
                || (quadrados.get(3).getLinha(1).getX() == getI(jogada) && quadrados.get(3).getLinha(1).getY() == getJ(jogada))
                || (quadrados.get(3).getLinha(2).getX() == getI(jogada) && quadrados.get(3).getLinha(2).getY() == getJ(jogada))
                || (quadrados.get(3).getLinha(3).getX() == getI(jogada) && quadrados.get(3).getLinha(3).getY() == getJ(jogada))) {
            saida.add(3);//se for adiciona a posicao dele na array saida
        }
        return saida;//retorna o array de posicoes
    }

    public ArrayList<Integer> fechouQuadrado(char[][] tabuleiro, ArrayList<Integer> quadrado) {//verifica se a jogada fechou um quadrado
        ArrayList<Integer> pontos = new ArrayList();//array de quadrados que foram fechados
        if (!((tabuleiro[quadrados.get(quadrado.get(0)).getLinha(0).getX()][quadrados.get(quadrado.get(0)).getLinha(0).getY()] == ' ')//verifica se o quadrado nao foi completamente preenchido
                || (tabuleiro[quadrados.get(quadrado.get(0)).getLinha(1).getX()][quadrados.get(quadrado.get(0)).getLinha(1).getY()] == ' ')
                || (tabuleiro[quadrados.get(quadrado.get(0)).getLinha(2).getX()][quadrados.get(quadrado.get(0)).getLinha(2).getY()] == ' ')
                || (tabuleiro[quadrados.get(quadrado.get(0)).getLinha(3).getX()][quadrados.get(quadrado.get(0)).getLinha(3).getY()] == ' '))) {
            pontos.add(quadrado.get(0));//adiciona a posicao do quadrado na array
        }
        if (quadrado.size() > 1) {//verifica se a jogada inclui mais de 1 quadrado
            if (!((tabuleiro[quadrados.get(quadrado.get(1)).getLinha(0).getX()][quadrados.get(quadrado.get(1)).getLinha(0).getY()] == ' ')//verifica se o quadrado nao foi completamente preenchido
                    || (tabuleiro[quadrados.get(quadrado.get(1)).getLinha(1).getX()][quadrados.get(quadrado.get(1)).getLinha(1).getY()] == ' ')
                    || (tabuleiro[quadrados.get(quadrado.get(1)).getLinha(2).getX()][quadrados.get(quadrado.get(1)).getLinha(2).getY()] == ' ')
                    || (tabuleiro[quadrados.get(quadrado.get(1)).getLinha(3).getX()][quadrados.get(quadrado.get(1)).getLinha(3).getY()] == ' '))) {
                pontos.add(quadrado.get(1));//adiciona a posicao do quadrado na array
            }
        }
        return pontos;//retorna a array de posicoes
    }

    public int jogadaEquivalente(int i, int j) {
        int pos = 0;
        for (int k = 0; k < jogadasI.size(); k++) {
            if (jogadasI.get(k) == i && jogadasJ.get(k) == j) {
                pos = k;
            }
        }
        return pos;
    }

    public int procuraFilho(No no, int joga) {
        int pos = 0;
        for (int i = 0; i < no.getFilhos().size(); i++) {
            if (no.getFilhos().get(i).getJogada() == joga) {
                pos = i;
            }
        }
        return pos;
    }

    public int melhorJogada(No no) {
        int maior = Integer.MIN_VALUE;
        ArrayList<Integer> posicoes = new ArrayList();
        for (int i = 0; i < no.getFilhos().size(); i++) {
            if (no.getFilhos().get(i).getMinMax() > maior) {
                maior = no.getFilhos().get(i).getMinMax();

            }
        }
        for (int i = 0; i < no.getFilhos().size(); i++) {
            if (no.getFilhos().get(i).getMinMax() == maior) {
                posicoes.add(i);
            }
            //System.out.print((no.getFilhos().get(i).getJogada()) + " ");
            //no.getFilhos().get(i).mostraTabuleiro();
            //System.out.println("Somatorio: " + no.getFilhos().get(i).getMinMax());
        }
        Random gen = new Random();
        return posicoes.get(gen.nextInt(posicoes.size()));
    }

    public int centroI(int index) { //retorna a posicao I do centro index
        return centro[index][0];
    }

    public int centroJ(int index) { //retorna a posicao J do centro index
        return centro[index][1];
    }
}

package tab;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author pedro
 */
public class Tabuleiro {

    private char[][] tabuleiro;
    private int[][] mapa;
    private int contador;
    private ArrayList<Integer> jogadasPossiveis = new ArrayList();

    public Tabuleiro() {
        tabuleiro = new char[3][3];

        int cont = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
                jogadasPossiveis.add(cont);
                cont++;
            }
        }
        mapa = new int[9][2];

        mapa[0][0] = 0;
        mapa[0][1] = 0;

        mapa[1][0] = 0;
        mapa[1][1] = 1;

        mapa[2][0] = 0;
        mapa[2][1] = 2;

        mapa[3][0] = 1;
        mapa[3][1] = 0;

        mapa[4][0] = 1;
        mapa[4][1] = 1;

        mapa[5][0] = 1;
        mapa[5][1] = 2;

        mapa[6][0] = 2;
        mapa[6][1] = 0;

        mapa[7][0] = 2;
        mapa[7][1] = 1;

        mapa[8][0] = 2;
        mapa[8][1] = 2;

        contador = 1;
    }

    public boolean jogar(int jogada, boolean vez) {
        if (marcaPonto(jogada, vez)) {
            switch (verifica(tabuleiro, contador)) {
                case 'X' -> {
                    System.out.println("X Venceu");
                    return true;
                }
                case 'O' -> {
                    System.out.println("O Venceu");
                    return true;
                }
                case 'E' -> {
                    System.out.println("Velha");
                    return true;
                }
            }
        } else {
            System.out.println("Jogada Invalida");
        }
        contador++;
        return false;
    }

    public boolean marcaPonto(int jogada, boolean vez) {
        if (tabuleiro[getI(jogada)][getJ(jogada)] == ' ') {
            if (vez) {
                tabuleiro[getI(jogada)][getJ(jogada)] = 'X';
                jogadasPossiveis.remove(getPosicao(jogada));
            } else {
                tabuleiro[getI(jogada)][getJ(jogada)] = 'O';
                jogadasPossiveis.remove(getPosicao(jogada));
            }
            return true;
        } else {
            return false;
        }
    }

    public int getI(int jogada) {
        return mapa[jogada - 1][0];
    }

    public int getJ(int jogada) {
        return mapa[jogada - 1][1];
    }

    public char verifica(char[][] tab, int jogada) {
        if (jogada >= 5) {
            if (tab[0][0] == 'X' && tab[0][1] == 'X' && tab[0][2] == 'X') {
                return 'X';
            } else if (tab[1][0] == 'X' && tab[1][1] == 'X' && tab[1][2] == 'X') {
                return 'X';
            } else if (tab[2][0] == 'X' && tab[2][1] == 'X' && tab[2][2] == 'X') {
                return 'X';
            } else if (tab[0][0] == 'O' && tab[0][1] == 'X' && tab[0][2] == 'X') {
                return 'O';
            } else if (tab[1][0] == 'O' && tab[1][1] == 'O' && tab[1][2] == 'O') {
                return 'O';
            } else if (tab[2][0] == 'O' && tab[2][1] == 'O' && tab[2][2] == 'O') {
                return 'O'; //VERTICAL
            } else if (tab[0][0] == 'X' && tab[1][0] == 'X' && tab[2][0] == 'X') {
                return 'X';
            } else if (tab[0][1] == 'X' && tab[1][1] == 'X' && tab[2][1] == 'X') {
                return 'X';
            } else if (tab[0][2] == 'X' && tab[1][2] == 'X' && tab[2][2] == 'X') {
                return 'X';
            } else if (tab[0][0] == 'O' && tab[1][0] == 'O' && tab[2][0] == 'O') {
                return 'O';
            } else if (tab[0][1] == 'O' && tab[1][1] == 'O' && tab[2][1] == 'O') {
                return 'O';
            } else if (tab[0][2] == 'O' && tab[1][2] == 'O' && tab[2][2] == 'O') {
                return 'O';//HORIZONTAL
            } else if (tab[0][0] == 'X' && tab[1][1] == 'X' && tab[2][2] == 'X') {//DIAGONAL
                return 'X';
            } else if (tab[0][0] == 'O' && tab[1][1] == 'O' && tab[2][2] == 'O') {
                return 'O';
            } else if (tab[0][2] == 'X' && tab[1][1] == 'X' && tab[2][0] == 'X') {//DIAGONAL
                return 'X';
            } else if (tab[0][2] == 'O' && tab[1][1] == 'O' && tab[2][0] == 'O') {
                return 'O';
            } else if (jogada == 9) {
                return 'E';
            } else {
                return ' ';
            }
        } else {
            return ' ';
        }
    }

    public void mostraTabuleiro() {
        int contI = 0, contJ = 0;
        int cont = 0;
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 5; j++) {
                    if (j % 2 == 0) {
                        if (tabuleiro[contI][contJ] == ' ') {
                            System.out.print(jogadasPossiveis.get(cont) + " ");
                            cont++;
                        } else {
                            System.out.print(tabuleiro[contI][contJ] + " ");
                        }
                        contJ++;
                    } else {
                        System.out.print("|" + " ");
                    }
                }
                contI++;
                contJ = 0;
            } else {
                for (int j = 0; j < 5; j++) {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }

    public void teste() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getPosicao(int jogada) {
        for (int i = 0; i < jogadasPossiveis.size(); i++) {
            if (jogadasPossiveis.get(i) == jogada) {
                return i;
            }
        }
        return -1;
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getJogada(No no) {
        int pos = 0;
        int melhor = Integer.MIN_VALUE;
        for (int i = 0; i < no.getFilhos().size(); i++) {
            if (no.getFilhos().get(i).getMinMax() > melhor) {
                melhor = no.getFilhos().get(i).getMinMax();
                pos = i;
            }
            no.getFilhos().get(i).mostraTab();
            System.out.println(no.getFilhos().get(i).getMinMax());
        }
        return pos;
    }

    public int procuraFilho(int jogada, No no) {
        int pos = 0;
        for (int i = 0; i < no.getFilhos().size(); i++) {
            if (no.getFilhos().get(i).getJogada() == jogada) {
                pos = i;
            }
        }
        return pos;
    }
}

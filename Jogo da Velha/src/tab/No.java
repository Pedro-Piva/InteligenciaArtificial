/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tab;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class No {

    private char tabuleiro[][] = new char[3][3];
    private boolean vez;
    private int jogada;
    private ArrayList<No> filhos = new ArrayList();
    private boolean acabou = false;
    private int minMax = 0;

    public No(Tabuleiro t, boolean[] v, boolean vez, int jogada, int cont, char[][] tab) {
        if (jogada != -1) {
            for (int i = 0; i < 3; i++) {
                System.arraycopy(tab[i], 0, tabuleiro[i], 0, 3);
            }
            this.vez = vez;
            this.jogada = jogada;
            marcaJogada(t);
            switch (t.verifica(tabuleiro, cont)) {
                case 'X' -> {
                    this.minMax = -1;
                    acabou = true;
                }
                case 'O' -> {
                    this.minMax = 1;
                    acabou = true;
                }
                case 'E' -> {
                    this.minMax = 0;
                    acabou = true;
                }
            }
        }
        if (!acabou) {
            preencheArvore(t, v, this.vez, this.tabuleiro, cont);
        }
    }

    public final void preencheArvore(Tabuleiro t, boolean[] v, boolean vez, char[][] tab, int cont) {
        for (int i = 0; i < v.length; i++) {
            if (v[i]) {
                v[i] = false;
                cont++;
                filhos.add(new No(t, v, !vez, i + 1, cont, tab));
                cont--;
                v[i] = true;
            }
        }
        if (!filhos.isEmpty()) {
            if (vez) {
                int comp = Integer.MIN_VALUE;
                for (int i = 0; i < filhos.size(); i++) {
                    if (comp < filhos.get(i).getMinMax()) {
                        comp = filhos.get(i).getMinMax();
                    }
                }
                this.minMax = comp;
            } else {
                int comp = Integer.MAX_VALUE;
                for (int i = 0; i < filhos.size(); i++) {
                    if (comp > filhos.get(i).getMinMax()) {
                        comp = filhos.get(i).getMinMax();
                    }
                }
                this.minMax = comp;
            }
        }
    }

    public final void marcaJogada(Tabuleiro t) {
        if (vez) {
            tabuleiro[t.getI(jogada)][t.getJ(jogada)] = 'X';
        } else {
            tabuleiro[t.getI(jogada)][t.getJ(jogada)] = 'O';
        }
    }

    public int getMinMax() {
        return minMax;
    }

    public ArrayList<No> getFilhos() {
        return filhos;
    }
    
    public int getJogada(){
        return jogada;
    }
    
    public void mostraTab(){
        System.out.println("\nJogada: " + jogada);
        System.out.println(vez);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(tabuleiro[i][j] == ' '){
                    System.out.print("V ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}
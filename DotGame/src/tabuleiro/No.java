/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabuleiro;

import java.util.ArrayList;

/**
 *
 * @author pedro.piva
 */
public final class No {

    private int numero;
    private boolean minMax;
    private String[][] tabuleiro;
    private ArrayList<No> filhos = new ArrayList();

    public No(int numero, Boolean[] n) {
        this.numero = numero;
        tabuleiro = new String[5][5];
        preencheArvore(n);
    }

    public void preencheArvore(Boolean[] n) {
        for (int i = 0; i < n.length; i++) {
            if (n[i]) {
                n[i] = false;
                filhos.add(new No(i, n));
                n[i] = true;
            }
        }
    }
}

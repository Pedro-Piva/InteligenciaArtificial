/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tab;

import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        boolean[] vetor = new boolean[9];
        for (int i = 0; i < 9; i++) {
            vetor[i] = true;
        }
        boolean vez = true;
        System.out.print("Informe quem joga primeiro:\n1 - Maquina\n2 - Jogador\nSua Opcao: ");
        int primeiro = sc.nextInt();
        if (primeiro == 1) {
            System.out.println("Maquina Primeiro");
            vez = false;
        } else if (primeiro == 2) {
            System.out.println("Jogador Primeiro");
            vez = true;
        }
        No raiz = new No(tabuleiro, vetor, !vez, -1, 0, tabuleiro.getTabuleiro());
        while (true) {
            int jogada;
            if (vez) {
                System.out.println("Vez do X");
                tabuleiro.mostraTabuleiro();
                System.out.print("Sua Jogada: ");
                jogada = sc.nextInt();
                if (!raiz.getFilhos().isEmpty()) {
                    raiz = raiz.getFilhos().get(tabuleiro.procuraFilho(jogada, raiz));
                }
            } else {
                System.out.println("Vez do O");
                tabuleiro.mostraTabuleiro();
                if (!raiz.getFilhos().isEmpty()) {
                    raiz = raiz.getFilhos().get(tabuleiro.getJogada(raiz));
                }
                jogada = raiz.getJogada();
                System.out.println("Jogada da IA: " + jogada);
            }
            System.out.println("\n\n");
            if (tabuleiro.jogar(jogada, vez)) {
                tabuleiro.mostraTabuleiro();
                break;
            } else {
                vez = !vez;
            }
        }
    }

}

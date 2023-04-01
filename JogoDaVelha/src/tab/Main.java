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
        for(int i = 0; i < 9; i++){
            vetor[i] = true;
        }
        No raiz = new No(tabuleiro, vetor, false, -1, 0, tabuleiro.getTabuleiro());
        boolean vez = true;
        while (true) {
            int jogada;
            tabuleiro.mostraTabuleiro();
            if(vez){
                System.out.println("Vez do X");
                System.out.print("Sua Jogada: ");
                jogada = sc.nextInt();
                raiz = raiz.getFilhos().get(tabuleiro.procuraFilho(jogada, raiz));
            } else {
                System.out.println("Vez do O");
                raiz = raiz.getFilhos().get(tabuleiro.getJogada(raiz));
                jogada = raiz.getJogada();
                System.out.println("Jogada da IA: " + jogada);
            }
            System.out.println("\n\n");
            if(tabuleiro.jogar(jogada, vez)){
                tabuleiro.mostraTabuleiro();
                break;
            } else {
                vez = !vez;
            }     
        }
    }

}

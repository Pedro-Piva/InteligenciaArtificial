package tabuleiro;

import java.util.Scanner;

/**
 *
 * @author pedro.piva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();//objeto tabuleiro
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe o Jogador a comecar 1 ou 2: ");//dois jogadores, ou nao
        int inicial = sc.nextInt();
        tabuleiro.setJogador(inicial);
        sc.skip("\n");
        while (!tabuleiro.acabou()) {//enquanto nao acabou
            if (inicial != 2) {
                tabuleiro.vezJogador();
                int x;//posx da jogada no tabuleiro
                int y;//posy da jogada no tabuleiro
                String s = sc.nextLine();//jogada do jogador em String(01, 21, 34)
                x = Character.getNumericValue(s.charAt(0));
                y = Character.getNumericValue(s.charAt(1));
                tabuleiro.addJogada(x, y); //adiciona a jogada
            } else {
                inicial = 0;
                tabuleiro.addJogada(-1, -1);
            }
        }
        System.out.println("\n\n\n\n");
        tabuleiro.mostrarTabuleiro();//exibe como ficou o tabuleiro no final
        switch (tabuleiro.vencedor()) {//mostra o vencedor
            case 1 ->
                System.out.println("Jogador 1 Venceu!");
            case 2 ->
                System.out.println("Jogador 2 Venceu!");
            default ->
                System.out.println("Empatou!");
        }
    }

}

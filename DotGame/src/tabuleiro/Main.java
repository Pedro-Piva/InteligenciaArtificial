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
        Scanner sc = new Scanner(System.in); //entrada 
        System.out.print("Informe o Jogador a comecar 1 ou 2: ");//dois jogadores, ou nao
        int inicial = 0;
        try{
            inicial = sc.nextInt();
            tabuleiro.setJogador(inicial);
        } catch (Exception e){
            String lixo = sc.nextLine();
            System.out.println("Jogador Invalido");
            System.out.println("Jogador 1 Primeiro");
        }
        while (!tabuleiro.acabou()) {//enquanto nao acabou
            tabuleiro.vezJogador();//printa o tabuleiro e quem joga
            try{ //jogada = um numero
                int jogada = sc.nextInt(); //pega a jogada
                tabuleiro.addJogada(jogada); //adciona a jogada se for valida
            } catch (Exception e){ //se nao for um numero
                System.out.println("Informe um numero valido");
                String lixo = sc.nextLine(); //pega o resto para ser possivel executar de novo
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

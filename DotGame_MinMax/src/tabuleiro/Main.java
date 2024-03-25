package tabuleiro;

import java.util.Scanner;

/**
 * -
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
        int inicial = 0;
        int dificuldade = 0;
        boolean flag = true;
        while (flag) {//primeira jogada do player
            try {
                System.out.print("Informe a Dificuldade de 1-10 : ");
                dificuldade = sc.nextInt();
                if (dificuldade > 0 && dificuldade < 11) {
                    flag = false;//sai do loop
                }
            } catch (Exception e) {//jogada nao valida
                String lixo = sc.nextLine();
                System.out.println(lixo + " Nao eh um valor Valido" + "\nInforme um valor Valido");
            }
        }
        tabuleiro.vezJogador();//mostra o tabuleiro e o jogador a jogar
        tabuleiro.jogar(1);//adiciona a primeira jogada que eh na primeira posicao possivel
        System.out.println("Jogada da Maquina: 1");
        flag = true;
        while (flag) {//primeira jogada do player
            try {
                tabuleiro.vezJogador();//mostra o tabuleiro e o jogador a jogar
                inicial = sc.nextInt();//pega a primeira jogada
                tabuleiro.jogar(inicial);//adiciona a jogada ao tabuleiro
                if (inicial > 1 && inicial < 13) {
                    flag = false;//sai do loop
                }
            } catch (Exception e) {//jogada nao valida
                String lixo = sc.nextLine();
                System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
            }
        }
        Jogada joga = new Jogada(inicial - 1);//cria um objeto jogada e passa a inicial do jogador como parametro
        char[][] tab = joga.transformaTabuleiro(tabuleiro.getTabuleiro());
        boolean[] vetor = new boolean[10];//vetor de jogadas possiveis
        for (int i = 0; i < 10; i++) {
            vetor[i] = true;//preenche sendo todas possiveis
        }
        No raiz = new No(0, vetor, tab, joga, false, 0);//cria o no Raiz, para a criacao de todos os outros
        System.out.println("Aguarde a Maquina");
        raiz.preencheArvore(vetor, joga);
        while (!tabuleiro.acabou()) {//enquanto nao acabou
            tabuleiro.vezJogador();//printa o tabuleiro e quem joga
            if (tabuleiro.getJogador() == 1) {
                try { 
                    /*for (No filho : raiz.getFilhos()) {
                        System.out.println(filho.getMinMax() + " ");
                    }*/
                    int jogada = sc.nextInt(); //pega a jogada
                    tabuleiro.jogar(jogada); //adciona a jogada se for valida
                    raiz = raiz.getFilhos().get(joga.procuraFilho(raiz, joga.jogadaEquivalente(tabuleiro.jogadaI(jogada), tabuleiro.jogadaJ(jogada))));
                    //System.out.println(raiz.getMinMax());
                    //raiz.mostraTabuleiro();
                } catch (Exception e) { //se nao for um numero
                    String lixo = sc.nextLine(); //pega o resto para ser possivel executar de novo
                    System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
                }
            } else {
                /*for (No filho : raiz.getFilhos()) {
                    System.out.println(filho.getMinMax() + " ");
                }*/
                if (!raiz.getFilhos().isEmpty()) {
                    if(dificuldade >= 10){
                        raiz = raiz.getFilhos().get(joga.melhorJogada(raiz));
                    } else {
                        raiz = raiz.getFilhos().get(joga.jogadaAleatoria(raiz));
                    }
                }
                //raiz.mostraTabuleiro();
                //System.out.println("\n" + raiz.getMinMax());
                System.out.println("Jogada da Maquina: " + tabuleiro.jogadaEquivalente(joga.getI(raiz.getJogada()), joga.getJ(raiz.getJogada())));
                tabuleiro.addJogada(joga.getI(raiz.getJogada()), joga.getJ(raiz.getJogada()));
            }
            dificuldade++;
        }
        System.out.println("\n\n\n\n");
        tabuleiro.mostrarTabuleiro();//exibe como ficou o tabuleiro no final
        switch (tabuleiro.vencedor()) {//mostra o vencedor
            case 1 ->
                System.out.println("Maquina Venceu");
            case 2 ->
                System.out.println("Jogador Venceu!");
            default ->
                System.out.println("Empatou!");
        }
    }
}

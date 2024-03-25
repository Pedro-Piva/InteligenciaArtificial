package tabuleiro;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * -
 *
 * @author pedro.piva
 */
public class Main {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        ArrayList<Integer> jogadasPossiveis = new ArrayList();
        for (int i = 1; i < 13; i++) {
            jogadasPossiveis.add(i);
        }
        int dificuldade;
        int contadorJogada = 10;
        System.out.print("Informe a Dificuldade de 1 a 10: ");
        while (true) {
            try {
                int entrada = sc.nextInt();
                if (entrada < 11 && entrada > 0) {
                    dificuldade = entrada;
                    break;
                }
            } catch (Exception e) {
                String lixo = sc.nextLine();
                System.out.println(lixo + " Nao eh um valor valido" + "\nInforme um valor valido");
            }
        }
        System.out.println("1 Jogador\n2 Maquina");
        System.out.print("Escolha o Jogador a comecar: ");
        boolean flag = true;
        while (flag) {
            try {
                int entrada = sc.nextInt();
                switch (entrada) {
                    case 1 -> {
                        tabuleiro.setJogador(true);
                        flag = false;
                    }
                    case 2 ->
                        flag = false;
                }
            } catch (Exception e) {
                String lixo = sc.nextLine();
                System.out.println(lixo + " Nao eh um valor valido" + "\nInforme um valor valido");
            }
        }
        int inicial = 0;
        boolean[] vetor;
        Jogada joga = new Jogada();
        sc.skip("\n");
        if (tabuleiro.getJogador() == 0) {
            tabuleiro.vezJogador();
            int primeira = rand.nextInt(0, 12);
            tabuleiro.jogar(primeira + 1);
            joga.removeJogada(tabuleiro.jogadaI(primeira + 1), tabuleiro.jogadaJ(primeira + 1));
            jogadasPossiveis.remove(posicao(jogadasPossiveis, (primeira + 1)));
            System.out.println("Jogada da Maquina: " + (primeira + 1));
            flag = true;
            while (flag) {
                try {
                    tabuleiro.vezJogador();
                    inicial = sc.nextInt();
                    tabuleiro.jogar(inicial);
                    if (inicial > 0 && inicial < 13) {
                        joga.removeJogada(tabuleiro.jogadaI(inicial), tabuleiro.jogadaJ(inicial));
                        jogadasPossiveis.remove(posicao(jogadasPossiveis, inicial));
                        flag = false;
                    }
                } catch (Exception e) {
                    String lixo = sc.nextLine();
                    System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
                }
            }
            vetor = new boolean[10];
        } else {
            flag = true;
            while (flag) {
                try {
                    tabuleiro.vezJogador();
                    inicial = sc.nextInt();
                    if (inicial > 0 && inicial < 13) {
                        tabuleiro.jogar(inicial);
                        joga.removeJogada(tabuleiro.jogadaI(inicial), tabuleiro.jogadaJ(inicial));
                        jogadasPossiveis.remove(posicao(jogadasPossiveis, inicial));
                        flag = false;
                    }
                } catch (Exception e) {
                    String lixo = sc.nextLine();
                    System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
                }
            }
            int jogadaMaquina = -1;
            flag = true;
            tabuleiro.vezJogador();
            while (flag) {
                jogadaMaquina = rand.nextInt(0, 12);
                if (jogadaMaquina != inicial - 1) {
                    tabuleiro.jogar(jogadaMaquina + 1);
                    joga.removeJogada(tabuleiro.jogadaI(jogadaMaquina + 1), tabuleiro.jogadaJ(jogadaMaquina + 1));
                    System.out.println("Jogada da Maquina: " + (jogadaMaquina + 1));
                    jogadasPossiveis.remove(posicao(jogadasPossiveis, (jogadaMaquina + 1)));
                    flag = false;
                }
            }
            flag = true;
            int novaJogada;
            while (flag) {
                try {
                    tabuleiro.vezJogador();
                    novaJogada = sc.nextInt();
                    if (novaJogada > 0 && novaJogada < 13 && novaJogada != inicial && novaJogada != jogadaMaquina + 1) {
                        tabuleiro.jogar(novaJogada);
                        joga.removeJogada(tabuleiro.jogadaI(novaJogada), tabuleiro.jogadaJ(novaJogada));
                        jogadasPossiveis.remove(posicao(jogadasPossiveis, novaJogada));
                        flag = false;
                    }
                } catch (Exception e) {
                    String lixo = sc.nextLine();
                    System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
                }
            }
            contadorJogada--;
            vetor = new boolean[9];
        }
        char[][] tab = tabuleiro.transformaTabuleiro(tabuleiro.getTabuleiro());
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = true;
        }
        No raiz = new No(0, vetor, tab, joga, false, 0);
        System.out.println("Aguarde a Maquina...");
        raiz.preencheArvore(vetor, joga);
        while (!tabuleiro.acabou()) {
            tabuleiro.vezJogador();
            if (tabuleiro.getJogador() == 1) {
                try {
                    int jogada = sc.nextInt();
                    if (jogadasPossiveis.contains(jogada)) {
                        tabuleiro.jogar(jogada);
                        raiz = raiz.getFilhos().get(joga.procuraFilho(raiz, joga.jogadaEquivalente(tabuleiro.jogadaI(jogada), tabuleiro.jogadaJ(jogada))));
                        jogadasPossiveis.remove(posicao(jogadasPossiveis, jogada));
                    }
                    dificuldade++;
                    //raiz.mostraTabuleiro();
                    //raiz.mostraChance();
                } catch (Exception e) {
                    String lixo = sc.nextLine();
                    System.out.println(lixo + " Nao eh uma jogada Valida" + "\nInforme uma jogada Valida");
                }
            } else {
                if (dificuldade >= contadorJogada) {
                    if (!raiz.getFilhos().isEmpty()) {
                        raiz = raiz.getFilhos().get(joga.melhorJogada(raiz));
                    }
                    System.out.println("Jogada da Maquina Int: " + tabuleiro.jogadaEquivalente(joga.getI(raiz.getJogada()), joga.getJ(raiz.getJogada())));
                    jogadasPossiveis.remove(posicao(jogadasPossiveis, tabuleiro.jogadaEquivalente(joga.getI(raiz.getJogada()), joga.getJ(raiz.getJogada()))));
                    tabuleiro.addJogada(joga.getI(raiz.getJogada()), joga.getJ(raiz.getJogada()));
                } else {
                    int jogada = rand.nextInt(0, jogadasPossiveis.size());
                    System.out.println("Jogada da Maquina Aleatoria: " + jogadasPossiveis.get(jogada));
                    tabuleiro.jogar(jogadasPossiveis.get(jogada));
                    if (!raiz.getFilhos().isEmpty()) {
                        raiz = raiz.getFilhos().get(joga.procuraFilho(raiz, joga.jogadaEquivalente(tabuleiro.jogadaI(jogadasPossiveis.get(jogada)), tabuleiro.jogadaJ(jogadasPossiveis.get(jogada)))));
                    }
                    jogadasPossiveis.remove(posicao(jogadasPossiveis, jogadasPossiveis.get(jogada)));
                }
                dificuldade++;
            }
        }
        System.out.println("\n\n\n\n");
        tabuleiro.mostrarTabuleiro();
        switch (tabuleiro.vencedor()) {
            case 1 ->
                System.out.println("Maquina Venceu");
            case 2 ->
                System.out.println("Jogador Venceu!");
            default ->
                System.out.println("Empatou!");
        }
    }

    static int posicao(ArrayList<Integer> jogadasPossiveis, int jogada) {
        for (int i = 0; i < jogadasPossiveis.size(); i++) {
            if (jogadasPossiveis.get(i) == jogada) {
                return i;
            }
        }
        return -1;
    }
}

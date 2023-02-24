package tabuleiro;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pedro.piva
 */
public class Tabuleiro {

    private final ArrayList<String> jogadas = new ArrayList(); //jogadas possiveis
    private final char[][] tabuleiro; //tabuleiro
    private final ArrayList<Quadrado> quadrados; //quadrados dentro do tabuleiro
    private final int jogadores[];
    private int jogador;

    public Tabuleiro() {
        jogadores = new int[2];
        jogadores[0] = 0;
        jogadores[1] = 0;
        jogador = 0;
        tabuleiro = new char[5][5]; //inicializando tabuleiro
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                tabuleiro[i][j] = '*'; //preenchendo os pontos
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] != '*') {
                    tabuleiro[i][j] = ' ';//preenchendo o resto com espaco
                }
            }
        }
        tabuleiro[1][1] = 'x';//colocando os x onde seria o centro do quadrado
        tabuleiro[1][3] = 'x';
        tabuleiro[3][1] = 'x';
        tabuleiro[3][3] = 'x';
        quadrados = new ArrayList();
        quadrados.add(new Quadrado(new Arestas(0, 0)));
        quadrados.add(new Quadrado(new Arestas(0, 2)));
        quadrados.add(new Quadrado(new Arestas(2, 0)));
        quadrados.add(new Quadrado(new Arestas(2, 2)));
        quadrados.get(0).addLinha(0, 1);
        quadrados.get(0).addLinha(1, 0);
        quadrados.get(0).addLinha(1, 2);
        quadrados.get(0).addLinha(2, 1);
        quadrados.get(1).addLinha(1, 2);
        quadrados.get(1).addLinha(0, 3);
        quadrados.get(1).addLinha(2, 3);
        quadrados.get(1).addLinha(1, 4);
        quadrados.get(2).addLinha(2, 1);
        quadrados.get(2).addLinha(3, 0);
        quadrados.get(2).addLinha(3, 2);
        quadrados.get(2).addLinha(4, 1);
        quadrados.get(3).addLinha(2, 3);
        quadrados.get(3).addLinha(3, 2);
        quadrados.get(3).addLinha(3, 4);
        quadrados.get(3).addLinha(4, 3);
    }

    public boolean verificaJogada(int i, int j) {
        if (i < 5 && i >= 0 && j < 5 && j >= 0) {
            return tabuleiro[i][j] == ' ';
        } else {
            return false;
        }
    }

    public void setJogador(int jogador) {
        this.jogador = jogador - 1;
    }

    public void vezJogador() {
        System.out.println("\n\nVez do Jogador " + (jogador + 1));
        mostrarTabuleiro();
        System.out.print("Sua jogada: ");
    }

    public void addJogada(int i, int j) {
        if (verificaJogada(i, j)) {//se a jogada for valida
            if (i % 2 == 0) {//horizontal ou vertical
                tabuleiro[i][j] = '-';//marca o tabuleiro
            } else {//vertical
                tabuleiro[i][j] = '|';//marca o tabuleiro
            }
            boolean jogarDeNovo = false; //saber se vai jogar de novo
            for (int index = 0; index < quadrados.size(); index++) {
                if (quadrados.get(index).verificaLinha(i, j)) {//verifica se o quadrado tem a linha
                    if (quadrados.get(index).fechouQuadrado()) {//verifica se o quadrado foi fechado
                        jogarDeNovo = true;//jogador joga de novo
                    }
                }
            }
            if (!jogarDeNovo) {//alterna o jogador
                if (jogador == 0) {
                    jogador = 1;
                    int[] jogada = stringToInt(getJogada());
                    vezJogador();
                    addJogada(jogada[0], jogada[1]);
                } else {
                    jogador = 0;
                }
            } else {
                for (int index = 0; index < quadrados.size(); index++) {//apaga os quadrados que foram fechados
                    if (quadrados.get(index).isFechado()) {
                        marcaX(quadrados.get(index), jogador);
                        jogadores[jogador]++;
                        quadrados.remove(index);
                        index = -1;
                    }
                }
            }
        } else {
            if (jogador == 1) {
                int[] jogada = stringToInt(getJogada());
                vezJogador();
                addJogada(jogada[0], jogada[1]);
            } else {
                System.out.println("\n\n\n\n\n\n\n");
                System.out.println("JOGADA INVALIDA");
            }
        }
    }

    public boolean acabou() {//verifica se o jogo acabou
        return quadrados.isEmpty();
    }

    public void marcaX(Quadrado quadrado, int jogador) { //funcao para trocar o X pelo numero do jogador
        if (jogador == 0) {
            tabuleiro[quadrado.getAresta().getPosX() + 1][quadrado.getAresta().getPosY() + 1] = '1';
        } else {
            tabuleiro[quadrado.getAresta().getPosX() + 1][quadrado.getAresta().getPosY() + 1] = '2';
        }
    }

    public int vencedor() {//Funcao para verificar o vencedor
        if (jogadores[0] > jogadores[1]) {
            return 1;//jogador 1 venceu
        } else if (jogadores[0] < jogadores[1]) {
            return 2;//jogador 2 venceu
        } else {
            return 0;//empatou
        }
    }

    public void mostrarTabuleiro() {//funcao para printar o tabuleiro
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] == ' ') {
                    System.out.print(i + "" + j + " ");
                } else {
                    System.out.print(tabuleiro[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void jogadasPossiveis() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] == ' ') {
                    addJogadas(intToString(i, j));
                }
            }
        }
    }

    public void addJogadas(String pos) {
        this.jogadas.add(pos);//adiciona as jogadas possiveis no array
    }

    public String intToString(int i, int j) {//transforma 2 posicoes INT em String
        String s = Integer.toString(i);
        s += Integer.toString(j);
        return s;
    }

    public int escolheJogada() {//escolhe a proxima jogada da maquina Aleatoriamente
        Random ran = new Random();
        jogadasPossiveis();
        System.out.println(jogadas);
        int pos = ran.nextInt(0, jogadas.size());
        return pos;
    }

    public int[] stringToInt(String s) {
        int saida[] = new int[2];
        saida[0] = Character.getNumericValue(s.charAt(0));
        saida[1] = Character.getNumericValue(s.charAt(1));
        zerarJogadas();
        return saida;
    }

    public String getJogada() { //pega uma jogada
        return jogadas.get(escolheJogada());
    }

    public void zerarJogadas() { //zera as jogadas possiveis
        jogadas.clear();
    }

}

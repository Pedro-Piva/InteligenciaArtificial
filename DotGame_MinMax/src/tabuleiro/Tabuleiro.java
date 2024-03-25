package tabuleiro;

import java.util.ArrayList;

/**
 *
 * @author pedro.piva
 */
public class Tabuleiro {

    private final ArrayList<String> jogadas = new ArrayList(); //mapa de jogadas
    private final String[][] tabuleiro; //tabuleiro
    private final ArrayList<Quadrado> quadrados; //quadrados dentro do tabuleiro
    private final int jogadores[];
    private int jogador;

    public Tabuleiro() {
        jogadores = new int[2];
        jogadores[0] = 0;
        jogadores[1] = 0;
        jogador = 0;
        tabuleiro = new String[5][5]; //inicializando tabuleiro
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                tabuleiro[i][j] = "*"; //preenchendo os pontos
            }
        }

        tabuleiro[1][1] = "x";//colocando os x onde seria o centro do quadrado
        tabuleiro[1][3] = "x";
        tabuleiro[3][1] = "x";
        tabuleiro[3][3] = "x";

        int cont = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] == null) {
                    tabuleiro[i][j] = Integer.toString(cont);//preenchendo as jogadas
                    jogadas.add(i + "" + j); // mapeando as jogadas
                    cont++;
                }
            }
        }
        quadrados = new ArrayList(); //iniciando a array

        quadrados.add(new Quadrado(new Arestas(0, 0))); //criando cada quadrado
        quadrados.add(new Quadrado(new Arestas(0, 2)));
        quadrados.add(new Quadrado(new Arestas(2, 0)));
        quadrados.add(new Quadrado(new Arestas(2, 2)));

        quadrados.get(0).addLinha(0, 1); //colocando as linhas de cada quadrado
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

    public boolean verificaJogada(int i, int j) { //verifica se a jogada eh valida
        try {
            Integer.valueOf(tabuleiro[i][j]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void vezJogador() { //Mostra a vez de quem que eh
        if (jogador == 1) {
            System.out.println("\n\nVez do Jogador ");
            mostrarTabuleiro();
            System.out.print("Sua jogada: ");
        } else {
            System.out.println("\n\nVez da Maquina ");
            mostrarTabuleiro();
        }
    }

    public void jogar(int jogada) { //funcao para chamar o add jogada
        addJogada(stringToInt(jogadas.get(jogada - 1))[0], stringToInt(jogadas.get(jogada - 1))[1]);
    }

    public void addJogada(int i, int j) {
        if (verificaJogada(i, j)) {//se a jogada for valida
            if (i % 2 == 0) {//horizontal ou vertical
                tabuleiro[i][j] = "-";//marca o tabuleiro
            } else {//vertical
                tabuleiro[i][j] = "|";//marca o tabuleiro
            }
            boolean jogarDeNovo = false; //saber se vai jogar de novo
            for (int index = 0; index < quadrados.size(); index++) {//verifica cada quadrado
                if (quadrados.get(index).verificaLinha(i, j)) {//verifica se o quadrado tem a linha
                    if (quadrados.get(index).fechouQuadrado()) {//verifica se o quadrado foi fechado
                        jogarDeNovo = true;//jogador joga de novo
                    }
                }
            }
            if (!jogarDeNovo) {//alterna o jogador
                if (jogador == 0) {
                    jogador = 1;
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
            System.out.println("\n\n\n\n\n\n\n");
            System.out.println("JOGADA INVALIDA");
        }
    }

    public boolean acabou() {//verifica se o jogo acabou
        return quadrados.isEmpty();
    }

    public void marcaX(Quadrado quadrado, int jogador) { //funcao para trocar o X pelo numero do jogador
        if (jogador == 0) {
            tabuleiro[quadrado.getAresta().getPosX() + 1][quadrado.getAresta().getPosY() + 1] = "M";
        } else {
            tabuleiro[quadrado.getAresta().getPosX() + 1][quadrado.getAresta().getPosY() + 1] = "P";
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

    public ArrayList<String> getJogadas() {
        return jogadas;
    }

    public void mostrarTabuleiro() {//funcao para printar o tabuleiro
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String intToString(int i, int j) {//transforma 2 posicoes INT em String
        String s = Integer.toString(i);
        s += Integer.toString(j);
        return s;
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    public int jogadaI(int index) {
        return stringToInt(jogadas.get(index - 1))[0];
    }

    public int jogadaJ(int index) {
        return stringToInt(jogadas.get(index - 1))[1];
    }

    public int[] stringToInt(String s) {
        int saida[] = new int[2];
        saida[0] = Character.getNumericValue(s.charAt(0));
        saida[1] = Character.getNumericValue(s.charAt(1));
        return saida;
    }

    public int getJogador() {
        return jogador;
    }

    public int jogadaEquivalente(int i, int j) {
        int pos = 0;
        for (int k = 0; k < jogadas.size(); k++) {
            if (i == stringToInt(jogadas.get(k))[0] && j == stringToInt(jogadas.get(k))[1]) {
                pos = k;
            }
        }
        return ++pos;
    }
}

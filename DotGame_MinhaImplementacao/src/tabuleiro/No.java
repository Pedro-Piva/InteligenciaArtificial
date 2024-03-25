package tabuleiro;

import java.util.ArrayList;

/**
 *
 * @author pedro.piva
 */
public final class No {

    private int minMax;
    private final char[][] tabuleiro;//tabuleiro para verificacao e armazenar as jogadas
    private final ArrayList<No> filhos = new ArrayList();//filhos desse No
    private final boolean vez; //vez, se for da maquina true, se for do jogador false
    private final int jogada; //jogada realizada

    public No(int numero, boolean[] n, char[][] tabuleiro, Jogada jogada, boolean vez, int c) {//construtor passando o numero da jogada, as jogadas ainda possiveis, o tabuleiro do pai, a jogada e a vez de quem 
        this.tabuleiro = new char[5][5];//instancia o tabuleiro
        this.jogada = numero;//jogada realizada
        for (int i = 0; i < 5; i++) { //copia o tabuleiro pai para o filho
            System.arraycopy(tabuleiro[i], 0, this.tabuleiro[i], 0, 5);
        }
        if (c != 0) {//se nao for o raiz
            preencheTabuleiro(jogada.getI(numero), jogada.getJ(numero));//adiciona a jogada ao tabuleiro
            if (marcaPonto(jogada.fechouQuadrado(this.tabuleiro, jogada.qualQuadrado(numero)), jogada, vez)) {//marca caso o quadrado foi fechado e alterna ou nao a vez
                vez = !vez; //inverte a vez para manter
            }
        }
        this.vez = vez;//seta a vez
    }

    public void preencheArvore(boolean[] n, Jogada jogada) {//chamada para criar os filhos, passa as jogadas possiveis, a jogada e a vez
        int count = 0;
        for (int i = 0; i < n.length; i++) {//pelo tamanho das possibilidades, ou seja 10
            if (n[i]) {//se for possivel
                n[i] = false;//nao eh mais possivel
                filhos.add(new No(i, n, tabuleiro, jogada, !vez, 1));//cria um novo filho passando os parametros depois voltando a chamada recursiva
                filhos.get(count).preencheArvore(n, jogada);//chama recursivamente a funcao
                count++;//numero de filhos
                n[i] = true;//volta a ser possivel
            }
        }//2,7,3,8,11
        //jogadas vitoria 9/10/12 jogador
        if (count == 0) {//se nao tiver filhos
            int resultado = vencedor(tabuleiro, jogada); //procura quem venceu
            switch (resultado) {
                case 0 ->
                    this.minMax = 1;
                case 1 ->
                    this.minMax = 1;
                case -1 ->
                    this.minMax = -1;
            }
        } else { //se tiver filhos
            for (No filho : filhos) {
                this.minMax += filho.minMax;
            }
        }
    }

    public boolean marcaPonto(ArrayList<Integer> quadrados, Jogada jogada, boolean vez) { //marca o ponto no tabuleiro
        if (!quadrados.isEmpty()) {//verifica se alguem fechou algum quadrado
            if (vez) { //vez da maquina
                this.tabuleiro[jogada.centroI(quadrados.get(0))][jogada.centroJ(quadrados.get(0))] = 'M'; //marca no tabuleiro M
            } else { //vez do jogador
                this.tabuleiro[jogada.centroI(quadrados.get(0))][jogada.centroJ(quadrados.get(0))] = 'P'; //marca no tabuleiro P
            }
            if (quadrados.size() > 1) { //se fechou mais de 1 quadrado
                if (vez) { //vez da maquina
                    this.tabuleiro[jogada.centroI(quadrados.get(1))][jogada.centroJ(quadrados.get(1))] = 'M';//marca no tabuleiro M
                } else { //vez do jogador
                    this.tabuleiro[jogada.centroI(quadrados.get(1))][jogada.centroJ(quadrados.get(1))] = 'P';//marca no tabuleiro P
                }
            }
            return true; //retorna true, para manter a vez
        } else {
            return false; //retorna falso para inverter a vez
        }
    }

    public int vencedor(char[][] tabuleiro, Jogada jogada) {
        int p = 0;
        int m = 0;
        if (tabuleiro[jogada.centroI(0)][jogada.centroJ(0)] == 'P') {
            p++;
        } else {
            m++;
        }
        if (tabuleiro[jogada.centroI(1)][jogada.centroJ(1)] == 'P') {
            p++;
        } else {
            m++;
        }
        if (tabuleiro[jogada.centroI(2)][jogada.centroJ(2)] == 'P') {
            p++;
        } else {
            m++;
        }
        if (tabuleiro[jogada.centroI(3)][jogada.centroJ(3)] == 'P') {
            p++;
        } else {
            m++;
        }
        if (p > m) {
            return -1;
        } else if (m > p) {
            return 1;
        } else {
            return 0;
        }
    }

    public void preencheTabuleiro(int i, int j) { //marca no tabuleiro a jogada
        if (i % 2 == 0) { //condicao para marcas -
            tabuleiro[i][j] = '-'; //marca a jogada
        } else { //condicao para marcas |
            tabuleiro[i][j] = '|'; //marca a jogada
        }
    }

    public No getFilho() { //pega o primeiro filho da array
        return filhos.get(0); //retorna o primeiro filho da array
    }

    public ArrayList<No> getFilhos() { //retorna a array de filhos
        return filhos; //retorna a array de filhos
    }

    public int getMinMax() {
        return minMax;
    }

    public void mostraTabuleiro() { //printa o tabuleiro
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getJogada() {
        return jogada;
    }
}

package tabuleiro;

import java.util.ArrayList;

/**
 *
 * @author pedro.piva
 */
public final class No {

    private int minMax;//MinMax para a escolha das jogadas
    private final char[][] tabuleiro;//tabuleiro para verificacao e armazenar as jogadas
    private final ArrayList<No> filhos = new ArrayList();//filhos desse No
    private final boolean vez; //vez, se for da maquina true, se for do jogador false
    private final int jogada; //jogada realizada

    public No(int numero, boolean[] n, char[][] tabuleiro, Jogada jogada, boolean vez, int c) {//construtor passando o numero da jogada, as jogadas ainda possiveis, o tabuleiro do pai, a jogada e a vez de quem 
        this.tabuleiro = new char[5][5];//instancia o tabuleiro
        this.jogada = numero;//jogada realizada
        this.minMax = 0;//instancia o MinMax
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
        }
        if (count == 0) {//se nao tiver filhos
            minMax = vencedor(tabuleiro, jogada); //procura quem venceu
        } else { //se tiver filhos
            minMax();
        }
    }

    public void minMax() {
        int comparador;
        if (vez) { //vez for da maquina
            comparador = Integer.MIN_VALUE; //comparador
            for (No filho : filhos) { //cada filho
                if (comparador < filho.getMinMax()) { //se o minMax do filho for maior
                    comparador = filho.getMinMax(); //seta o maior como o valor encontrado
                }
            }
        } else { //vez do jogador
            comparador = Integer.MAX_VALUE; //comparador
            for (No filho : filhos) { //cada filho
                if (comparador > filho.getMinMax()) { //se o minMax do filho for menor
                    comparador = filho.getMinMax(); //seta o menor como o valor encontrado
                }
            }
        }
        this.minMax = comparador;
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

    public int vencedor(char[][] tabuleiro, Jogada jogada) { //verifica quem venceu
        int p = 0; //pontos player
        int m = 0; //pontos IA
        if (tabuleiro[jogada.centroI(0)][jogada.centroJ(0)] == 'P') { //posicao[1][3] se for P
            p++; //conta 1 ponto para player
        } else { //posicao[1][3] se for M
            m++; //conta 1 ponto para IA
        }
        if (tabuleiro[jogada.centroI(1)][jogada.centroJ(1)] == 'P') { //posicao[3][1] se for P
            p++; //conta 1 ponto para player
        } else { //posicao[3][1] se for M
            m++; //conta 1 ponto para IA
        }
        if (tabuleiro[jogada.centroI(2)][jogada.centroJ(2)] == 'P') { //posicao[3][3] se for P
            p++; //conta 1 ponto para player
        } else { //posicao[1][1] se for M
            m++; //conta 1 ponto para IA
        }
        if (tabuleiro[jogada.centroI(3)][jogada.centroJ(3)] == 'P') { //posicao[3][3] se for P
            p++; //conta 1 ponto para player
        } else { //posicao[1][1] se for M
            m++; //conta 1 ponto para IA
        }
        if (p > m) { //se Player tiver mais pontos
            return -1; //vitoria player
        } else if (m > p) { //se IA tiver mais pontos
            return 1; //vitoria IA
        } else { //se os 2 tiverem a mesma quantidade de pontos
            return 0; //empate
        }
    }

    public void preencheTabuleiro(int i, int j) { //marca no tabuleiro a jogada
        if (i % 2 == 0) { //condicao para marcas -
            tabuleiro[i][j] = '-'; //marca a jogada
        } else { //condicao para marcas |
            tabuleiro[i][j] = '|'; //marca a jogada
        }
    }

    public ArrayList<No> getFilhos() { //retorna a array de filhos
        return filhos; //retorna a array de filhos
    }

    public void mostraTabuleiro() { //printa o tabuleiro
        System.out.println(); //Pula Linha
        for (int i = 0; i < 5; i++) { //para cada linha
            for (int j = 0; j < 5; j++) { //para cada coluna
                System.out.print(tabuleiro[i][j] + " "); //mostra o tabuleiro naquela posicao
            }
            System.out.println(); //Pula Linha
        }
        System.out.println(); //Pula linha
    }

    public int getMinMax() { //pega o valor do MinMax
        return minMax; //retorna o valor
    }

    public int getJogada() { //pega o valor da jogada
        return jogada; //retorna o valor
    }
}

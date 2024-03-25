package tabuleiro;

import java.util.ArrayList;

/**
 *
 * @author pedro.piva
 */
public class Quadrado {

    private final Arestas aresta;
    private final ArrayList<Linha> vertices; //vertices do quadrado
    private boolean fechado; //se ele esta fechado ou nao

    public Quadrado(Arestas aresta) {
        this.aresta = aresta;
        this.vertices = new ArrayList(); //inicializa os vertices
        this.fechado = false; //define que ele nao esta fechado
    }
    public Arestas getAresta(){
        return aresta;
    }

    public void addLinha(int posx, int posy) {//adiciona a linha nos vertices
        vertices.add(new Linha(posx, posy));
    }

    public boolean fechouQuadrado() {//verifica se o quadrado acabou de ser fechado
        if (vertices.isEmpty()) {
            if (fechado) {
                return false;
            } else {
                fechado = true;
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean isFechado() { //retorna se esta fechado ou nao
        return fechado;
    }

    public boolean verificaLinha(int i, int j) {
        boolean saida = false;
        for (int index = 0; index < vertices.size(); index++) {
            if(vertices.get(index).getX() == i && vertices.get(index).getY() == j){
                saida = true;
                removeVerificada(index);
            }
        }
        return saida;
    }

    public Linha getLinha(int index){
        return vertices.get(index);
    }
    public void removeVerificada(int pos) {
        vertices.remove(pos);
    }
}
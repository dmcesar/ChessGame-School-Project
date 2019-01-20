package pt.ulusofona.lp2.crazyChess;

public class JogadaValida implements Comparable<JogadaValida>{

    int x;
    int y;
    String nrPontos;

    public JogadaValida(){}

    public JogadaValida(int x, int y, String nrPontos){

        this.x = x;
        this.y = y;
        this.nrPontos = nrPontos;
    }

    @Override
    public int compareTo(JogadaValida jogadaValida){

        /*Caso o número de pontos das jogadas sejam iguais retorna 0. */
        if(jogadaValida.nrPontos.equals(this.nrPontos)){

            return 0;
        }

        /*Como as jogadas não têm o mesmo valor relativo, verifica se alguma dos jogadas tém número de pontos infinito.
        Se a jogada "this" tiver número infinito de pontos retorna 1, pois é maior que a outra jogada. */
        if(this.nrPontos.equals("(infinito)")){

            return 1;
        }
        /*Caso contrário, verifica se a outra jogada tem número de pontos infinitos. Se sim, retorna -1. */
        if(jogadaValida.nrPontos.equals("(infinito)")){

            return -1;
        }

        /*Caso contrário compara o valor numérico de cada jogada. */
        if(Integer.parseInt(this.nrPontos) >  Integer.parseInt(jogadaValida.nrPontos)){

            return 1;

        } else {

            return -1;
        }
    }

    public String toString(){return this.x + ", " + this.y + ", " + this.nrPontos;}
}

package pt.ulusofona.lp2.crazyChess;

public class JogadaValida implements Comparable<JogadaValida>{

    private int x;
    private int y;
    private int nrPontos;

    JogadaValida(){}

    JogadaValida(int x, int y, int nrPontos){

        this.x = x;
        this.y = y;
        this.nrPontos = nrPontos;
    }

    @Override
    public int compareTo(JogadaValida jogadaValida){

        /*Caso o número de pontos das jogadas sejam iguais retorna 0. */
        if(jogadaValida.nrPontos == this.nrPontos){

            return 0;
        }

        /*Caso contrário compara o valor numérico de cada jogada. */
        if(this.nrPontos >  jogadaValida.nrPontos){

            return 1;

        } else {

            return -1;
        }
    }

    public String toString(){return this.x + ", " + this.y + ", " + this.nrPontos;}
}

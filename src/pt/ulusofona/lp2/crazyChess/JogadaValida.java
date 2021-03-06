package pt.ulusofona.lp2.crazyChess;

public class JogadaValida implements Comparable<JogadaValida>{

    private int x;
    private int y;
    private String nrPontos;

    JogadaValida(int x, int y, String nrPontos){

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

        if(this.nrPontos.equals("(infinito)")){

            return -1;
        }

        if(jogadaValida.nrPontos.equals("(infinito)")){

            return 1;
        }

        /*Caso contrário compara o valor numérico de cada jogada. */
        if(Integer.parseInt(this.nrPontos) >  Integer.parseInt(jogadaValida.nrPontos)){

            return -1;

        } else {

            return 1;
        }
    }

    public String toString(){return this.x + ", " + this.y + ", " + this.nrPontos;}
}

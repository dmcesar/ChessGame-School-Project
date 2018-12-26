package pt.ulusofona.lp2.crazyChess;

public class TorreH extends CrazyPiece {

    TorreH(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "torre_h_black.png";

        }else { //ID equipa branca

            return "torre_h_white.png";
        }
    }

    @Override
    public String getType() {
        return "TorreH";
    }


    @Override
    public String getRelativeValue() {
        return "3";
    }

    @Override
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        //Caso seja indicado que a peça se mova na vertical
        if (yO != yD) {
            return false;
        }

        //Se for indicado que a peça se mova para a direita
        if (xO < xD){
            int x=xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD){
                x++;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(x == xD){
                    break;
                }
                if(Simulador.tabuleiro[yO][x] != null) {
                    return false;
                }
                //System.out.println(x);
            }

        } else {
            //Se for indicado que a peça se mova para a direita
            int x=xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD){
                x--;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(x == xD){
                    break;
                }
                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[yO][x] != null){
                    return false;
                }
                //System.out.println(x);
            }
        }
        return true;
    }

}



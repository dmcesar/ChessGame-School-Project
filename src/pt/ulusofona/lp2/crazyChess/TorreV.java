package pt.ulusofona.lp2.crazyChess;

public class TorreV extends CrazyPiece {
    TorreV(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "torre_v_black.png";

        }else { //ID equipa branca

            return "torre_v_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "TorreV";
    }


    @Override
    public String getValorRelativo() {
        return "3";
    }

    @Override
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        //Caso seja indicado que a peça se mova na horizontal
        if (xO != xD) {
            return false;
        }

        //Se for indicado que a peça se mova para baixo
        if (yO < yD){
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y++;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(y == yD){
                    break;
                }
                if(Simulador.tabuleiro[y][xO] != null) {
                    return false;
                }
            }

        } else {
            //Se for indicado que a peça se mova para cima
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y--;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(y == yD){
                    break;
                }
                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[y][xO] != null){
                    return false;
                }
            }
        }
        return true;
    }

}

package pt.ulusofona.lp2.crazyChess;

public class PadreDaVila extends CrazyPiece {

    PadreDaVila(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "padre_vila_black.png";

        }else { //ID equipa branca

            return "padre_vila_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Padre da Vila";
    }


    @Override
    public String getValorRelativo() {
        return "3";
    }
}

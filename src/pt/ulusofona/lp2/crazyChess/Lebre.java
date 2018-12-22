package pt.ulusofona.lp2.crazyChess;

public class Lebre extends CrazyPiece {

    Lebre(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "lebre_black.png";

        }else { //ID equipa branca

            return "lebre_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Lebre";
    }


    @Override
    public String getValorRelativo() {
        return "2";
    }

    @Override
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        return false;
    }
}

package pt.ulusofona.lp2.crazyChess;

public class Rei extends CrazyPiece {

    Rei(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "crazy_emoji_black.png";

        }else { //ID equipa branca

            return "crazy_emoji_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Rei";
    }


    @Override
    public String getValorRelativo() {
        return "(infinito)";
    }
}

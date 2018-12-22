package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {

    Joker(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "joker_black.png";

        }else { //ID equipa branca

            return "joker_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "Joker";
    }


    @Override
    public String getValorRelativo() {
        return "4";
    }

    @Override
    public boolean validaMovimento(int xO, int yO, int xD, int yD) {
        return false;
    }
}

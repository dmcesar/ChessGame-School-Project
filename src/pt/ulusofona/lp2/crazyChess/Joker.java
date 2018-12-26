package pt.ulusofona.lp2.crazyChess;

public class Joker extends CrazyPiece {

    Joker(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "joker_black.png";

        }else { //ID equipa branca

            return "joker_white.png";
        }
    }

    @Override
    public String getType() {
        return "Joker";
    }


    @Override
    public String getRelativeValue() {
        return "4";
    }
}

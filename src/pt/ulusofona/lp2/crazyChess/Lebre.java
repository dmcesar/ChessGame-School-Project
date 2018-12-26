package pt.ulusofona.lp2.crazyChess;

public class Lebre extends CrazyPiece {

    Lebre(int idPiece, int idType, int idTeam, String nickname){ super(idPiece, idType, idTeam, nickname); }

    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "lebre_black.png";

        }else { //ID equipa branca

            return "lebre_white.png";
        }
    }

    @Override
    public String getType() {
        return "Lebre";
    }


    @Override
    public String getRelativeValue() {
        return "2";
    }
}

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
}

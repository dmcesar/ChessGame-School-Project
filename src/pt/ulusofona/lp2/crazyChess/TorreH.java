package pt.ulusofona.lp2.crazyChess;

public class TorreH extends CrazyPiece {

    TorreH(int idPiece, int idType, int idTeam, String nickname){
        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }


    @Override
    public String getImagePNG() {

        if(this.getIdEquipa() == 10) { //ID equipa preta

            return "torre_h_black.png";

        }else { //ID equipa branca

            return "torre_h_white.png";
        }
    }

    @Override
    public String getDesignacao() {
        return "TorreH";
    }


    @Override
    public String getValorRelativo() {
        return "3";
    }
}

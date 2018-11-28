package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {

    int idPiece;
    int idType;
    int idTeam;
    String nickname;

    CrazyPiece(){}

    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }

    public int getId(){return this.idPiece;}

    public int getIdEquipa(){return this.idTeam;}

    public String getImagePNG(){

        if(this.getIdEquipa() == 0) { //ID equipa preta

            return "crazy_emoji_black.png";

        }else { //ID equipa branca

            return "crazy_emoji_white.png";
        }
    }

    public Equipa getTeam(){

        if(this.idTeam == 0){

            return Simulador.blackTeam;

        } else {

            return Simulador.whiteTeam;
        }
    }

    public String toString(){

        String output = this.idPiece + " | " + this.idType + " | " + this.idTeam + " | " + this.nickname + " @ (n/a)";

        if(this.getTeam().inGameCrazyPieces.contains(this)){

            for(int x = 0; x < Simulador.tabuleiro.length; x++){

                for(int y = 0; y < Simulador.tabuleiro.length; y++){

                    if(Simulador.tabuleiro[y][x] != null && Simulador.tabuleiro[y][x].idPiece == this.idPiece){

                        output =  this.idPiece + " | " + this.idType + " | " + this.idTeam + " | " + this.nickname + " @ " + "(" + x + ", " + y + ")";
                    }
                }
            }
        }

        return output;
    }
}
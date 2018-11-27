package pt.ulusofona.lp2.crazyChess;

public class CrazyPiece {

    int idPeca;
    int idTipo;
    int idEquipa;
    String alcunha;

    CrazyPiece(){}

    CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha){

        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
    }

    public int getId(){return this.idPeca;}

    public int getIdEquipa(){return this.idEquipa;}

    public String getImagePNG(){

        if(this.getIdEquipa() == 0) { //ID equipa preta

            return "crazy_emoji_black.png";

        }else { //ID equipa branca

            return "crazy_emoji_white.png";
        }
    }

    public Equipa getTeam(){

        if(this.idEquipa == 0){

            return Simulador.blackTeam;

        } else {

            return Simulador.whiteTeam;
        }
    }

    public String toString(){

        String output = this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ (n/a)";

        if(this.getTeam().inGameCrazyPiecesIds.contains(this.idPeca)){

            for(int x = 0; x < Simulador.boardSize; x++){

                for(int y = 0; y < Simulador.boardSize; y++){

                    if(Simulador.tabuleiro[x][y] != null && Simulador.tabuleiro[x][y].idPeca == this.idPeca){

                        output =  this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha + " @ " + "(" + x + ", " + y + ")";
                    }
                }
            }
        }

        return output;
    }
}
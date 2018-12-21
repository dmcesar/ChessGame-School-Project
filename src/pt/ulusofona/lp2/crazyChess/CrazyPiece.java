package pt.ulusofona.lp2.crazyChess;

public abstract class CrazyPiece {

    int idPiece;
    int idType;
    int idTeam;
    String nickname;

    CrazyPiece(){}

/*
    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
    }

*/

    public int getId(){return this.idPiece;}

    public int getIdType(){
        return this.idType;
    }

    public int getIdEquipa(){return this.idTeam;}

    public String getNickname(){
        return this.nickname;
    }

    abstract public String getDesignacao();

    abstract public String getValorRelativo();

    abstract public  String getImagePNG();

    public Equipa getTeam(){

        Simulador simulador = new Simulador();

        if(this.idTeam == 10){

            return simulador.blackTeam;

        } else {

            return simulador.whiteTeam;
        }
    }

    public String toString(){

        Simulador simulador = new Simulador();

        String output = this.idPiece + " | " + this.idType + " | " + this.idTeam + " | " + this.nickname + " @ (n/a)";

        if(this.getTeam().inGameCrazyPieces.contains(this)){

            for(int x = 0; x < simulador.tabuleiro.length; x++){

                for(int y = 0; y < simulador.tabuleiro.length; y++){

                    if(simulador.tabuleiro[y][x] != null && simulador.tabuleiro[y][x].idPiece == this.idPiece){

                        output =  this.idPiece + " | " + getDesignacao() + " | " + getValorRelativo() + " | " + this.idType + " | " + this.idTeam + " | " + this.nickname + " @ " + "(" + x + ", " + y + ")";
                    }
                }
            }
        }

        return output;
    }
}

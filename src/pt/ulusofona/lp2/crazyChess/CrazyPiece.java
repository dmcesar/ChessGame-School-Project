package pt.ulusofona.lp2.crazyChess;

import java.util.HashMap;

public class CrazyPiece {

    int idPeca;
    int idTipo;
    int idEquipa;
    String alcunha;
    boolean isInGame;
    int x;
    int y;

     CrazyPiece(){}

     CrazyPiece(int idPeca, int idTipo, int idEquipa, String alcunha){
        this.idPeca = idPeca;
        this.idTipo = idTipo;
        this.idEquipa = idEquipa;
        this.alcunha = alcunha;
    }

    public void setX(int x){
         this.x = x;
    }

    public void  setY(int y){
        this.y = y;
    }

    public int getId(){return this.idPeca;}

    public int getIdEquipa(){
        return this.idEquipa;
    }

    public String getImagePNG(){
        short idEquipaPreta = 0;
        if(this.idEquipa == idEquipaPreta) {
            return "crazy_emoji_black.png";
        }else {
            return "crazy_emoji_white.png";
        }
    }

    public String toString(){
        return this.idPeca + " | " + this.idTipo + " | " + this.idEquipa + " | " + this.alcunha;
    }
}
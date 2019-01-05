package pt.ulusofona.lp2.crazyChess;

import java.util.ArrayList;
import java.util.List;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

abstract public class CrazyPiece {

    protected int idPiece;
    protected int idType;
    protected int idTeam;
    protected String nickname;
    protected String previousCoords;

    CrazyPiece(){}

    CrazyPiece(int idPiece, int idType, int idTeam, String nickname){

        this.idPiece = idPiece;
        this.idType = idType;
        this.idTeam = idTeam;
        this.nickname = nickname;
        this.previousCoords = "";
    }

    public int getId(){return this.idPiece;}

    public int getIdType(){return this.idType;}

    public int getIdEquipa(){return this.idTeam;}

    public String getNickname(){return this.nickname;}

    public Equipa getTeam(){

        if(this.idTeam == 10){

            return blackTeam;

        } else {

            return whiteTeam;
        }
    }

    public String toString(){

        String output = this.idPiece + " | " + this.getType() + " | " + this.getRelativeValue() + " | " + this.idTeam + " | " + this.nickname + " @ (n/a)";

        if(this.getTeam().inGameCrazyPieces.contains(this)){

            for(int x = 0; x < tabuleiro.length; x++){

                for(int y = 0; y < tabuleiro.length; y++){

                    if(tabuleiro[y][x] != null && tabuleiro[y][x].idPiece == this.idPiece){

                        output =  this.idPiece + " | " + this.getType() + " | " + this.getRelativeValue() + " | " + this.idTeam + " | " + this.nickname + " @ " + "(" + x + ", " + y + ")";
                    }
                }
            }
        }

        return output;
    }

    abstract public String getImagePNG();

    abstract public String getType();

    abstract public String getRelativeValue();

    abstract public boolean checkValidMovement(int xO, int yO, int xD, int yD);

    abstract public ArrayList<String> getValidPlays(int xO, int yO);

    public boolean checkPieceBlockingMove(int xO, int yO, int xD, int yD){

        //Caso a peça se tente mover na horizontal (direita)
        if (xO < xD && yO == yD) {
            int x = xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD) {
                x++;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if (x == xD) {
                    break;
                }
                if (Simulador.tabuleiro[yO][x] != null) {
                    return false;
                }
                //System.out.println(x);
            }
        }

        //Caso a peça se tente mover na horizontal (esquerda)
        else if(xO > xD && yO == yD) {
            int x=xO;
            //Enquanto a peça nao chegar ao seu destino
            while (x != xD){
                x--;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(x == xD){
                    break;
                }
                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[yO][x] != null){
                    return false;
                }
            }
        }

        //Movimento vertical (para baixo)
        else if (yO < yD && xO == xD){
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y++;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(y == yD){
                    break;
                }
                if(Simulador.tabuleiro[y][xO] != null) {
                    return false;
                }
            }
        }

        //Movimento vertical (para cima)
        else if (yO > yD && xO == xD){
            //Se for indicado que a peça se mova para cima
            int y=yO;
            //Enquanto a peça nao chegar ao seu destino
            while (y != yD){
                y--;
                //Caso a peça chegue ao seu destino interrompe-se o ciclo
                if(y == yD){
                    break;
                }
                //Vê se existe alguma peça no seu caminho
                if(Simulador.tabuleiro[y][xO] != null){
                    return false;
                }
            }
        }

        else if(xO != xD && yO != yD) {
            //Movimento diagonal para baixo direita
            if (xO < xD && yO < yD){
                int x = xO;
                int y = yO;
                while (x != xD && y != yD){
                    x++;
                    y++;

                    if (x == xD && y == yD){
                        break;
                    }

                    if (Simulador.tabuleiro[y][x] != null){
                        return false;
                    }
                }
            }

            //Movimento diagonal para cima esquerda
            else if (xO > xD && yO > yD){
                int x = xO;
                int y = yO;
                while (x != xD && y != yD){
                    x--;
                    y--;

                    if (x == xD && y == yD){
                        break;
                    }

                    if (Simulador.tabuleiro[y][x] != null){
                        return false;
                    }
                }
            }

            else if (xO > xD && yO < yD){
                int x = xO;
                int y = yO;
                while (x != xD && y != yD){
                    x--;
                    y++;

                    if (x == xD && y == yD){
                        break;
                    }

                    if (Simulador.tabuleiro[y][x] != null){
                        return false;
                    }
                }
            }

            else if (xO < xD && yO > yD){
                int x = xO;
                int y = yO;
                while (x != xD && y != yD){
                    x++;
                    y--;

                    if (x == xD && y == yD){
                        break;
                    }

                    if (Simulador.tabuleiro[y][x] != null){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
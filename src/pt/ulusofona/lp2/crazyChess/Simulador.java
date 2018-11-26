package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Simulador{

    static CrazyPiece[][] tabuleiro;
    static int boardSize;

    static HashMap<Integer, CrazyPiece> crazyPieces;
    HashMap<Integer, CrazyPiece> pecasEmJogo;
    static int numberOfPieces;

    static int cntMoves = 0; //Conta o número de jogadas efetuadas ao longo do jogo. Usada também para verificar qual a equipa a jogar.
    int jogadasInvalidasPretas = 0;
    int jogadasInvalidasBrancas = 0;
    int jogadasValidasPretas = 0;
    int jogadasValidasBrancas = 0;
    int nrCapturasPretas = 0;
    int nrCapturasBrancas = 0;
    int jogadasSemCaptura = 0;

    String resultado;

    public boolean iniciaJogo(File ficheiroInicial){

        try {

            //Secçao 1
            Scanner fileReader = new Scanner(ficheiroInicial);

            String line = fileReader.nextLine();

            boardSize = Integer.parseInt(line);

            tabuleiro = new CrazyPiece[boardSize][boardSize];

            //Secçao 2
             line = fileReader.nextLine();

            numberOfPieces = Integer.parseInt(line);

            crazyPieces = new HashMap<>();
            pecasEmJogo = new HashMap<>();


            //Secçao 3
            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                CrazyPiece peca = new CrazyPiece(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]), lineData[3]);

                crazyPieces.put(peca.getId(), peca);
                pecasEmJogo.put(peca.getId(), peca);
            }


            //Secçao 4
            for (int cntline = 0; cntline < boardSize; cntline++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                for (int cntPos = 0; cntPos < lineData.length; cntPos++) {

                    tabuleiro[cntPos][cntline] = (Integer.parseInt(lineData[cntPos]) != 0) ? crazyPieces.get(Integer.parseInt                           (lineData[cntPos])) : null;
                }
            }

        } catch (FileNotFoundException e) {

            return false;
        }

        return true;
    }

    public int getTamanhoTabuleiro(){return boardSize;}

    public boolean processaJogada(int xO, int yO, int xD, int yD){
        short idEquipaPreta = 0;

        if((0 <= xO && xO < boardSize) && (0 <= yO && yO < boardSize)){ //Valida posição inicial

            if(tabuleiro[xO][yO] != null){ //Verifica se existe uma peça na posição inicial

                if((0 <= xD && xD < boardSize) && (0 <= yD && yD < boardSize)){ //Valida posição final

                    if(sqrt(Math.pow((xD - xO), 2) + Math.pow((yD - yO), 2)) == 1) { //Verifica se a distância a ser percorrida é válida
                        if (tabuleiro[xO][yO].getIdEquipa() != tabuleiro[xD][yD].getIdEquipa()) {

                            //Caso a jogada seja válida
                            //Incrementa o numero de jogadas válidas de cada equipa para utilizar nos resultados
                            if (tabuleiro[xO][yO].getIdEquipa() == idEquipaPreta) {
                                jogadasValidasPretas++;
                            } else {
                                jogadasValidasBrancas++;
                            }

                            //Caso a peça vá para uma parte do tabuleiro já ocupada
                            if (tabuleiro[xD][yD] != null) {
                                //Verifica se as peças são de equipas diferentes

                                if (tabuleiro[xO][yO].getIdEquipa() != tabuleiro[xD][yD].getIdEquipa()) {
                                    //Remoção da peça eliminada
                                /*
                                tabuleiro[xO][yO] = tabuleiro[xD][yD];
                                tabuleiro[xO][yO] = null;
                                */
                                    pecasEmJogo.remove(tabuleiro[xD][yD].getId());

                                    if (tabuleiro[xO][yO].getIdEquipa() == idEquipaPreta) {
                                        nrCapturasPretas++;
                                    } else {
                                        nrCapturasBrancas++;
                                    }

                                }

                            }
                            tabuleiro[xD][yD] = tabuleiro[xO][yO];
                            tabuleiro[xO][yO] = null;

                            cntMoves++;
                            return true;
                        }
                    }
                }
            }
        }

        //Caso a jogada seja inválida
        //Incrementa o numero de jogadas inválidas de cada equipa para utilizar nos resultados
        if(tabuleiro[xO][yO].getIdEquipa() == idEquipaPreta){
            jogadasInvalidasPretas++;
        } else {
            jogadasInvalidasBrancas++;
        }
        return false;
    }

    public List<CrazyPiece> getPecasMalucas(){

        List<CrazyPiece> crazyPiecesList = new ArrayList<>();

        for(int idPiece : crazyPieces.keySet()){

            crazyPiecesList.add(crazyPieces.get(idPiece));
        }

        return crazyPiecesList;
    }


    public boolean jogoTerminado(){
        short idEquipaPreta = 0;
        short cntPecasPretas = 0;
        short cntPecasBrancas = 0;
        int nrCapturas = nrCapturasBrancas + nrCapturasPretas;

        for ( int idPiece : pecasEmJogo.keySet() ){
            if(pecasEmJogo.get(idPiece).getIdEquipa() == idEquipaPreta ){
                cntPecasPretas++;
            } else {
                cntPecasBrancas++;
            }
        }

        if ( cntPecasBrancas == 1 && cntPecasPretas == 1){
            resultado = "EMPATE";
            return true;
        }

        if ( cntPecasBrancas == 0 && cntPecasPretas > 0 ){
            resultado = "VENCERAM AS PRETAS";
            return true;
        }


        if ( cntPecasBrancas > 0 && cntPecasPretas == 0 ) {
            resultado = "VENCERAM AS BRANCAS";
            return true;
        }



        if(nrCapturas > 0 && jogadasSemCaptura >= 10){
            resultado = "EMPATE";
            return true;
        }

        return false;
    }



    public List<String> getAutores(){

        ArrayList<String> authors = new ArrayList<>();

        authors.add("Diogo César : 21804304");
        authors.add("Rafael Horta : 21705375");

        return authors;
    }

    public List<String> getResultados(){
        ArrayList<String> resultados = new ArrayList<>();

        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultados: " + resultado);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add("" + nrCapturasPretas);
        resultados.add("" + jogadasValidasPretas);
        resultados.add("" + jogadasInvalidasPretas);

        resultados.add("Equida das brancas");
        resultados.add("" + nrCapturasBrancas);
        resultados.add("" + jogadasValidasBrancas);
        resultados.add("" + jogadasInvalidasBrancas);

        return resultados;
    }

    public int getIDPeca(int x, int y){

        if(tabuleiro[x][y] != null){

            return tabuleiro[x][y].idPeca;

        }else{

            return 0;
        }
    }

    public int getIDEquipaAJogar(){
        int idEquipaPreta = 0;
        int idEquipaBranca = 1;

        if(cntMoves % 2 == 0) {
            return idEquipaPreta;
        } else {
            return idEquipaBranca;
        }
    }
}
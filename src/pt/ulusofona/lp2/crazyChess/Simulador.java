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
    static int numberOfPieces;

    static Equipa blackTeam = new Equipa(0, 0, 0);
    static Equipa whiteTeam = new Equipa(1, 0, 0);

    static int cntPlays = 0; //Conta o número de jogadas efetuadas ao longo do jogo. Usada também para verificar qual a equipa a jogar.
    static int cntPlaysNoCaptures = 0;

    static String result = "";

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


            //Secçao 3
            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                CrazyPiece crazyPiece = new CrazyPiece(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]), lineData[3]);

                //Adiciona o id da peça á sua equipa
                if(crazyPiece.idEquipa == whiteTeam.id){

                    whiteTeam.crazyPiecesIds.add(crazyPiece.idPeca);
                    whiteTeam.inGameCrazyPiecesIds.add(crazyPiece.idPeca);

                }else{

                    whiteTeam.crazyPiecesIds.add(crazyPiece.idPeca);
                    blackTeam.inGameCrazyPiecesIds.add(crazyPiece.idPeca);
                }

                //Adiciona a peça ao conjunto de peças do jogo
                crazyPieces.put(crazyPiece.idPeca, crazyPiece);
            }


            //Secçao 4
            for (int cntline = 0; cntline < boardSize; cntline++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                for (int cntPos = 0; cntPos < lineData.length; cntPos++) {

                    tabuleiro[cntline][cntPos] = (Integer.parseInt(lineData[cntPos]) != 0) ? crazyPieces.get(Integer.parseInt(lineData[cntPos])) : null;
                }
            }

        } catch (FileNotFoundException e) {

            return false;
        }

        return true;
    }

    public int getTamanhoTabuleiro(){return boardSize;}

    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Valida posição inicial
        if ((0 <= xO && xO < boardSize) && (0 <= yO && yO < boardSize)) {

            //Verifica se existe uma peça na posição inicial
            if (tabuleiro[xO][yO] != null) {

                CrazyPiece crazyPiece = tabuleiro[xO][yO];

                //Verifica se é a vez da equipa da peça em questão jogar
                if ((tabuleiro[xO][yO].getIdEquipa() == blackTeam.getId() && cntPlays % 2 == 0) || (tabuleiro[xO][yO].getIdEquipa() == whiteTeam.getId() && cntPlays % 2 != 0)) {

                    //Valida posição final
                    if ((0 <= xD && xD < boardSize) && (0 <= yD && yD < boardSize)) {

                        //Verifica se a distância a ser percorrida é válida
                        if (sqrt(Math.pow((xD - xO), 2) + Math.pow((yD - yO), 2)) == 1) {

                            //Verifica se existe uma peça na posição final
                            if (tabuleiro[xD][yD] != null) {

                                //Caso as peças sejam de equipas diferentes ocorre uma captura
                                if (tabuleiro[xD][yD].getIdEquipa() != tabuleiro[xO][yO].getIdEquipa()) {

                                    Equipa team = tabuleiro[xD][yD].getTeam();

                                    team.inGameCrazyPiecesIds.remove(tabuleiro[xD][yD].idPeca);

                                    tabuleiro[xD][yD] = crazyPiece;

                                    crazyPiece.getTeam().cntValidPlays++;

                                    cntPlaysNoCaptures = 0;

                                    return true;

                                 //Caso contrário a jogada é inválida
                                } else {

                                    crazyPiece.getTeam().cntInvalidPlays++;

                                    return false;
                                }

                                //Caso contrário não existe uma captura e a mudança de posição é direta.
                            } else {

                                tabuleiro[xD][yD] = crazyPiece;

                                crazyPiece.getTeam().cntValidPlays++;

                                cntPlaysNoCaptures++;

                                return true;
                            }
                        }
                    }
                }
            }
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

        //Vitória das brancas
        if(blackTeam.inGameCrazyPiecesIds.size() == 0){

            result = "VENCERAM AS BRANCAS";

            return true;
        }

        //Vitória das Pretas
        if(whiteTeam.inGameCrazyPiecesIds.size() == 0){

            result = "VENCERAM AS PRETAS";

            return true;
        }

        //Empate por número igual de peças
        if(blackTeam.inGameCrazyPiecesIds.size() == 1 && whiteTeam.inGameCrazyPiecesIds.size() == 1){

            result = "EMPATE";

            return true;
        }

        //Empate por falta de capturas;
        if (cntPlaysNoCaptures == 10){

            result = "EMPATE";

            return true;
        }

        return false;
    }

    public List<String> getAutores(){

        ArrayList<String> authors = new ArrayList<>();

        authors.add("Diogo César : 21804304");
        authors.add("Rafael Horta : <numero_aluno>");

        return authors;
    }

    public List<String> getResultados(){

        ArrayList<String> resultados = new ArrayList<>();

        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: " + result);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(Integer.toString(whiteTeam.crazyPiecesIds.size() - whiteTeam.inGameCrazyPiecesIds.size()));
        resultados.add(Integer.toString(blackTeam.cntValidPlays));
        resultados.add(Integer.toString(blackTeam.cntInvalidPlays));

        resultados.add("Equipa das Brancas");
        resultados.add(Integer.toString(blackTeam.crazyPiecesIds.size() - blackTeam.inGameCrazyPiecesIds.size()));
        resultados.add(Integer.toString(whiteTeam.cntValidPlays));
        resultados.add(Integer.toString(whiteTeam.cntInvalidPlays));

        return resultados;
    }

    public int getIDPeca(int x, int y){

        //Verifica se as coordenadas passadas são válidas
        if ((0 <= x && x < boardSize) && (0 <= y && y < boardSize)) {

            //Verifica se existe uma peça na posição passada
            if (tabuleiro[x][y] != null) {

                return tabuleiro[x][y].idPeca;

            }
        }
        return 0;
    }

    public int getIDEquipaAJogar(){

        if(cntPlays % 2 == 0){

            return blackTeam.id;

        } else {

            return whiteTeam.id;
        }
    }
}
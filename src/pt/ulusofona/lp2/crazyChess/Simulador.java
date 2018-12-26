package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Simulador{

    //Informações sobre o tabuleiro
    static CrazyPiece[][] tabuleiro;


    //Contém todas as peças em jogo
    private static ArrayList<CrazyPiece> crazyPieces;

    //Tipos complexos que contêm a informação respetivamente a cada uma das equipas
    static Equipa blackTeam;
    static Equipa whiteTeam;

    private static int idEquipaAJogar;


    //Contém o número de jogadas sem capturas
    private static int cntPlaysNoCaptures;


    //Contém o resultado final do jogo
    private String result;


    public boolean iniciaJogo(File ficheiroInicial){

        try {

            //Inicializa equipas e variáveis do jogo.
            blackTeam = new Equipa(10);
            whiteTeam = new Equipa(20);

            idEquipaAJogar = blackTeam.getId();

            cntPlaysNoCaptures = 0;

            result = "";


            //Secçao 1
            Scanner fileReader = new Scanner(ficheiroInicial);

            String line = fileReader.nextLine();

            tabuleiro = new CrazyPiece[Integer.parseInt(line)][Integer.parseInt(line)];


            //Secçao 2
            line = fileReader.nextLine();

            int numberOfPieces = Integer.parseInt(line);

            crazyPieces = new ArrayList<>();


            //Secçao 3
            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                //Cria a nova peça a ser adicionada
                CrazyPiece crazyPiece = getPeca(lineData);

                //Adiciona a peça ao conjunto de peças do jogo
                crazyPieces.add(crazyPiece);
            }


            //Secçao 4
            for (int y = 0; y < tabuleiro.length; y++) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                for (int x = 0; x < lineData.length; x++) {

                    //Devolve o valor de id contido nessa posição da matriz
                    int positionID = Integer.parseInt(lineData[x]);

                    //Se esse valor for diferente de 0 significa que se encontra lá uma peça
                    if (positionID != 0) {

                        //Itera todas as peças do jogo
                        for (CrazyPiece crazyPiece : crazyPieces) {

                            //Á procura de uma cujo id corresponda áquele lido do ficheiro
                            if (crazyPiece.getId() == positionID) {

                                //Insere a peça no conjunto de peças em jogo da equipa
                                crazyPiece.getTeam().inGameCrazyPieces.add(crazyPiece);
                                crazyPiece.getTeam().crazyPieces.add(crazyPiece);

                                //Insere a nova peça no tabuleiro
                                tabuleiro[y][x] = crazyPiece;
                            }
                        }
                    }
                }
            }

            //Secçao 5
            if (fileReader.hasNextLine()) {

                line = fileReader.nextLine();
                String[] lineData = line.split(":");

                idEquipaAJogar = Integer.parseInt(lineData[0]);

                blackTeam.cntValidPlays = Integer.parseInt(lineData[1]);
                blackTeam.cntCaptures = Integer.parseInt(lineData[2]);
                blackTeam.cntInvalidPlays = Integer.parseInt(lineData[3]);

                whiteTeam.cntValidPlays = Integer.parseInt(lineData[4]);
                whiteTeam.cntCaptures = Integer.parseInt(lineData[5]);
                whiteTeam.cntInvalidPlays = Integer.parseInt(lineData[6]);
            }

        } catch (FileNotFoundException e) {

            return false;
        }

        return true;
    }

    public int getTamanhoTabuleiro(){return tabuleiro.length;}

    public static boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Verifica se a posição inicial é diferente da posição final
        if (xO != xD && yO != yD) {

            //Valida posição inicial
            if ((0 <= xO && xO < tabuleiro.length) && (0 <= yO && yO < tabuleiro.length)) {

                //Verifica se existe uma peça na posição inicial
                if (tabuleiro[yO][xO] != null) {

                    //Retorna a peça que se pretende mover
                    CrazyPiece crazyPiece = tabuleiro[yO][xO];

                    //Verifica se é a vez da equipa da peça em questão jogar
                    if ((tabuleiro[yO][xO].getIdEquipa() == getIDEquipaAJogar())) {

                        //Valida posição final
                        if ((0 <= xD && xD < tabuleiro.length) && (0 <= yD && yD < tabuleiro.length)) {
                            //Verifica se a peça pode se movmentar

                            if (crazyPiece.checkValidMovement(xO, yO, xD, yD)) {

                                //Verifica se existe uma peça na posição final
                                if (tabuleiro[yD][xD] != null) {

                                    //Caso as peças sejam de equipas diferentes ocorre uma captura
                                    if (tabuleiro[yD][xD].getIdEquipa() != tabuleiro[yO][xO].getIdEquipa()) {

                                        //Remove a peça da equipa oposta da lista de peças em jogo dessa equipa
                                        tabuleiro[yD][xD].getTeam().inGameCrazyPieces.remove(tabuleiro[yD][xD]);

                                        //Mexe a peça
                                        tabuleiro[yD][xD] = crazyPiece;

                                        //Limpa a posição anterior
                                        tabuleiro[yO][xO] = null;

                                        //É incrementado o contador de jogadas válidas da equipa
                                        crazyPiece.getTeam().cntValidPlays++;

                                        //Como foi efetuada uma captura, o número de jogadas sem capturas foi reposto
                                        cntPlaysNoCaptures = 0;

                                        crazyPiece.getTeam().cntCaptures++;

                                        //Troca o id da equipa atual a jogar
                                        setIdEquipaAJogar();

                                        for (Joker joker : crazyPiece.getTeam().jokers) {

                                            joker.switchJokerType();
                                        }

                                        //Jogada realizada com sucesso
                                        return true;
                                    }
                                    //Caso contrário a jogada é inválida
                                    else {

                                        //É incrementado o contador de jogadas inválidas da equipa
                                        crazyPiece.getTeam().cntInvalidPlays++;

                                        //Jogada falhada
                                        return false;
                                    }
                                }
                                //Caso contrário não existe uma captura e a mudança de posição é direta.
                                else {

                                    tabuleiro[yD][xD] = crazyPiece;

                                    tabuleiro[yO][xO] = null;

                                    crazyPiece.getTeam().cntValidPlays++;

                                    //Se já tiver ocorrido uma captura préviamente e se for efuetuada uma jogada sem captura (número de capturas de uma equipa = diferença entre número de peças da outra e o seu número de peças em jogo)
                                    if (blackTeam.cntCaptures != 0 && whiteTeam.cntCaptures != 0) {

                                        //O contador de jogadas sem captura é incrementado
                                        cntPlaysNoCaptures++;
                                    }

                                    //Troca o id da equipa atual a jogar
                                    setIdEquipaAJogar();

                                    for (Joker joker : crazyPiece.getTeam().jokers) {

                                        joker.switchJokerType();
                                    }

                                    //Jogada realizada com sucesso
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        //Como a jogada falhou, incrementa o número de jogadas inválidas da equipa em questão
        if (getIDEquipaAJogar() == blackTeam.getId()) {

            blackTeam.cntInvalidPlays++;

        } else {

            whiteTeam.cntInvalidPlays++;
        }

        //Jogada falhada, falhou algum dos parametros acima logo não foram efetuadas alterações ao tabuleiro
        return false;
    }

    public List<CrazyPiece> getPecasMalucas(){ return crazyPieces;}

    public boolean jogoTerminado(){

        //Vitória das brancas por falta de peças pretas em jogo
        if(blackTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS BRANCAS";

            return true;
        }

        //Vitória das Pretas por falta de peças brancas em jogo
        if(whiteTeam.inGameCrazyPieces.size() == 0){

            result = "VENCERAM AS PRETAS";

            return true;
        }

        //Empate por número igual de peças em jogo (1 peça por equipa é considerado empate)
        if(blackTeam.inGameCrazyPieces.size() == 1 && whiteTeam.inGameCrazyPieces.size() == 1){

            result = "EMPATE";

            return true;
        }

        //Empate por falta de capturas
        if (cntPlaysNoCaptures == 10){

            result = "EMPATE";

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
        resultados.add("Resultado: " + result);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(Integer.toString(blackTeam.cntCaptures));
        resultados.add(Integer.toString(blackTeam.cntValidPlays));
        resultados.add(Integer.toString(blackTeam.cntInvalidPlays));

        resultados.add("Equipa das Brancas");
        resultados.add(Integer.toString(whiteTeam.cntCaptures));
        resultados.add(Integer.toString(whiteTeam.cntValidPlays));
        resultados.add(Integer.toString(whiteTeam.cntInvalidPlays));

        return resultados;
    }

    public int getIDPeca(int x, int y){

        //Verifica se as coordenadas passadas são válidas
        if ((0 <= x && x < tabuleiro.length) && (0 <= y && y < tabuleiro.length)) {

            //Verifica se existe uma peça na posição passada
            if (tabuleiro[y][x] != null) {

                return tabuleiro[y][x].getId();

            }
        }
        return 0;
    }

    public static int getIDEquipaAJogar(){ return idEquipaAJogar; }

    public static void setIdEquipaAJogar(){

        if(getIDEquipaAJogar() == blackTeam.getId()){

            idEquipaAJogar = whiteTeam.getId();

        } else {

            idEquipaAJogar = blackTeam.getId();
        }
    }

    public static CrazyPiece getPeca(String[] lineData){

        int idPiece = Integer.parseInt(lineData[0]);
        int idType = Integer.parseInt(lineData[1]);
        int idTeam = Integer.parseInt(lineData[2]);
        String nickname = lineData[3];

        switch (idType){

            case 0: return new Rei(idPiece, idType, idTeam, nickname);

            case 1: return new Rainha(idPiece, idType, idTeam, nickname);

            case 2: return new PoneiMagico(idPiece, idType, idTeam, nickname);

            case 3: return new PadreDaVila(idPiece, idType, idTeam, nickname);

            case 4: return new TorreH(idPiece, idType, idTeam, nickname);

            case 5: return new TorreV(idPiece, idType, idTeam, nickname);

            case 6: return new Lebre(idPiece, idType, idTeam, nickname);

            default: return new Joker(idPiece, idType, idTeam, nickname);
        }
    }
}
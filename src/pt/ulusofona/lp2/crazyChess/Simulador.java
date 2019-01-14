package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.abs;

public class Simulador {

    //Informações sobre o tabuleiro
    static CrazyPiece[][] tabuleiro;


    //Contém todas as peças em jogo
    private static ArrayList<CrazyPiece> crazyPieces;

    private static ArrayList<CrazyPiece> lastPlayOutcome;

    //Tipos complexos que contêm a informação respetivamente a cada uma das equipas
    static Equipa blackTeam;
    static Equipa whiteTeam;

    private static int idEquipaAJogar;


    //Contém o número de jogadas sem capturas
    private static int cntPlaysNoCaptures;


    //Contém o resultado final do jogo
    private String result;

    //TODO: Tratar exception "InvalidSimuladorInputException" - caso alguma linha das secções 1 a 4 tenha dados a mas ou a menos - lançar a exception
    //TODO: ALTERAÇÃO DA SECCÃO 5: Passa a receber a variavel cntPlaysNoCaptures para validar o empate por exaustão á leitura
    //TODO: A partir de agora cada peça guarda o seu numero de capturas, o seu numero total de pontos, nr jogadas validas e invalidas
    public void iniciaJogo(File ficheiroInicial) throws InvalidSimuladorInputException{

        int cntFileLines = 0;

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
            cntFileLines++;

            tabuleiro = new CrazyPiece[Integer.parseInt(line)][Integer.parseInt(line)];


            //Secçao 2

            line = fileReader.nextLine();
            cntFileLines++;

            int numberOfPieces = Integer.parseInt(line);

            crazyPieces = new ArrayList<>();


            //Secçao 3

            for (int cntLine = 0; cntLine < numberOfPieces; cntLine++) {

                line = fileReader.nextLine();
                cntFileLines++;
                String[] lineData = line.split(":");

                if(lineData.length > 4){

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MAIS (Esperava: 4 ; Obtive: " + lineData.length + ")");
                }

                if(lineData.length < 4){

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MENOS (Esperava: 4 ; Obtive: " + lineData.length + ")");
                }


                //Cria a nova peça a ser adicionada
                CrazyPiece crazyPiece = getPeca(lineData);

                //Adiciona a peça ao conjunto de peças do jogo
                crazyPieces.add(crazyPiece);
            }


            //Secçao 4

            for (int y = 0; y < tabuleiro.length; y++) {

                line = fileReader.nextLine();
                cntFileLines++;
                String[] lineData = line.split(":");

                if (lineData.length > tabuleiro.length) {

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MAIS (Esperava: " + tabuleiro.length + " ; Obtive: " + lineData.length + ")");
                }

                if (lineData.length < tabuleiro.length) {

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MENOS (Esperava: " + tabuleiro.length + " ; Obtive: " + lineData.length + ")");
                }

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

                                //Se a peça for um joker troca o seu tipo
                                if (crazyPiece.getIdType() == 7) {

                                    //Adiciona o joker á lista de jokers da sua equipa
                                    crazyPiece.getTeam().jokers.add((Joker) crazyPiece);
                                }

                                //Insere a nova peça no tabuleiro
                                tabuleiro[y][x] = crazyPiece;

                                crazyPiece.presentCoords = new PieceCoords(y, x);
                            }
                        }
                    }
                }
            }

            //TODO: Ler com estatisticas da peça guardadas
            //Secçao 5

            if (fileReader.hasNextLine()) {

                line = fileReader.nextLine();
                cntFileLines++;
                String[] lineData = line.split(":");

                if(lineData.length > 8){

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MAIS (Esperava: 8 ; Obtive: " + lineData.length + ")");
                }

                if(lineData.length < 8){

                    throw new InvalidSimuladorInputException(cntFileLines, "DADOS A MENOS (Esperava: 8 ; Obtive: " + lineData.length + ")");
                }

                idEquipaAJogar = Integer.parseInt(lineData[0]);

                blackTeam.cntValidPlays = Integer.parseInt(lineData[1]);
                blackTeam.cntCaptures = Integer.parseInt(lineData[2]);
                blackTeam.cntInvalidPlays = Integer.parseInt(lineData[3]);

                whiteTeam.cntValidPlays = Integer.parseInt(lineData[4]);
                whiteTeam.cntCaptures = Integer.parseInt(lineData[5]);
                whiteTeam.cntInvalidPlays = Integer.parseInt(lineData[6]);

                //BUG FIX - Empate por exaustão á leitura.
                cntPlaysNoCaptures = Integer.parseInt(lineData[7]);
            }

            //TODO: Meter algo dentro dos catches
        } catch (FileNotFoundException notFoundException) {

            //O visualizador trata disto?
        }

        catch (InvalidSimuladorInputException invalidInputException){

            //O visualizador trata disto também?
        }

        jogoTerminado();

    }

    public int getTamanhoTabuleiro() {
        return tabuleiro.length;
    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        //Troca as máscaras dos jokers de ambas as equipas
        for (Joker joker : blackTeam.jokers) {

            joker.switchJokerType();
        }

        for (Joker joker : whiteTeam.jokers) {

            joker.switchJokerType();
        }

        //Verifica se a posição inicial é diferente da posição final
        if (xO != xD || yO != yD) {

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

                                        lastPlayOutcome = new ArrayList<>();

                                        //Guarda a peça antes de ser movida
                                        crazyPiece.previousCoords = crazyPiece.presentCoords;
                                        lastPlayOutcome.add(crazyPiece);

                                        //Guarda a peça que foi caputurada
                                        tabuleiro[yD][xD].previousCoords = tabuleiro[yD][xD].presentCoords;
                                        tabuleiro[yD][xD].presentCoords = new PieceCoords(-1, -1);
                                        lastPlayOutcome.add(tabuleiro[yD][xD]);

                                        //São atualizadas as estatisticas da equuipa da peça
                                        crazyPiece.getTeam().cntCaptures++;
                                        crazyPiece.getTeam().cntValidPlays++;

                                        //São atualizadas as estatisticas da peça
                                        crazyPiece.statistics.cntValidPlays++;
                                        crazyPiece.statistics.cntCaptures++;

                                        //Atualiza o número de pontos da peça de acordo com o valor relativo da peça que ela capturou
                                        if (tabuleiro[yD][xD].getRelativeValue().equals("(infinito)")) {

                                            crazyPiece.statistics.cntPoints += 1000;

                                        } else {

                                            crazyPiece.statistics.cntPoints = crazyPiece.statistics.cntPoints + Integer.parseInt(tabuleiro[yD][xD].getRelativeValue());
                                        }

                                        //Remove a peça da equipa oposta da lista de peças em jogo dessa equipa
                                        tabuleiro[yD][xD].getTeam().inGameCrazyPieces.remove(tabuleiro[yD][xD]);

                                        //Mexe a peça
                                        tabuleiro[yD][xD] = crazyPiece;
                                        crazyPiece.presentCoords = new PieceCoords(yD, xD);

                                        //Limpa a posição anterior
                                        tabuleiro[yO][xO] = null;

                                        //Como foi efetuada uma captura, o número de jogadas sem capturas foi reposto
                                        cntPlaysNoCaptures = 0;

                                        //Troca o id da equipa atual a jogar
                                        setIdEquipaAJogar();

                                        //Jogada realizada com sucesso
                                        return true;
                                    }
                                    //Caso contrário a jogada é inválida
                                    else {

                                        //É incrementado o contador de jogadas inválidas da equipa
                                        crazyPiece.getTeam().cntInvalidPlays++;

                                        crazyPiece.statistics.cntInvalidPlays++;

                                        //Jogada falhada
                                        return false;
                                    }
                                }
                                //Caso contrário não existe uma captura e a mudança de posição é direta.
                                else {

                                    lastPlayOutcome = new ArrayList<>();

                                    //Guarda a peça antes de ser movida
                                    crazyPiece.previousCoords = crazyPiece.presentCoords;
                                    lastPlayOutcome.add(crazyPiece);

                                    //São atualizadas as estatisticas da peça
                                    crazyPiece.statistics.cntValidPlays++;

                                    //Move a peça
                                    tabuleiro[yD][xD] = crazyPiece;
                                    crazyPiece.presentCoords = new PieceCoords(yD, xD);

                                    //Apaga a posição anterior
                                    tabuleiro[yO][xO] = null;

                                    //Incrementa o número de jogadas válidas da equipa
                                    crazyPiece.getTeam().cntValidPlays++;

                                    //Se já tiver ocorrido uma captura préviamente e se for efuetuada uma jogada sem captura (número de capturas de uma equipa = diferença entre número de peças da outra e o seu número de peças em jogo)
                                    if (crazyPiece.getTeam().cntCaptures != 0) {

                                        //O contador de jogadas sem captura é incrementado
                                        cntPlaysNoCaptures++;
                                    }

                                    setIdEquipaAJogar();

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

    public List<CrazyPiece> getPecasMalucas() { return crazyPieces; }

    public boolean jogoTerminado() {

        int nrReisBlack = 0;
        int nrReisWhite = 0;

        for (CrazyPiece piece : blackTeam.inGameCrazyPieces){
            if (piece.getIdType() == 0){
                nrReisBlack++;
            }
        }


        for (CrazyPiece piece : whiteTeam.inGameCrazyPieces){
            if (piece.getIdType() == 0){
                nrReisWhite++;
            }
        }
        //Vitória das brancas por falta de reis da equipa preta em jogo
        if (nrReisBlack == 0) {

            result = "VENCERAM AS BRANCAS";

            return true;
        }

        //Vitória das Pretas por falta de reis da equipa branca em jogo
        if (nrReisWhite == 0) {

            result = "VENCERAM AS PRETAS";

            return true;
        }

        //Empate por número igual de reis em jogo (1 peça por equipa é considerado empate)
        if ((nrReisBlack == 1 && blackTeam.inGameCrazyPieces.size() == 1) && (nrReisWhite == 1 && whiteTeam.inGameCrazyPieces.size() == 1)) {
            result = "EMPATE";

            return true;
        }

        //Empate por falta de capturas
        if (cntPlaysNoCaptures == 10) {

            result = "EMPATE";

            return true;
        }

        return false;
    }

    public List<String> getAutores() {

        ArrayList<String> authors = new ArrayList<>();

        authors.add("Diogo César : 21804304");
        authors.add("Rafael Horta : 21705375");

        return authors;
    }

    public List<String> getResultados() {

        ArrayList<String> resultados = new ArrayList<>();

        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: " + result);
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(" Capturas: " + Integer.toString(blackTeam.cntCaptures));
        resultados.add(" Jogadas válidas: " + Integer.toString(blackTeam.cntValidPlays));
        resultados.add(" Tentativas inválidas: " + Integer.toString(blackTeam.cntInvalidPlays));

        resultados.add("Equipa das Brancas");
        resultados.add(" Capturas: " + Integer.toString(whiteTeam.cntCaptures));
        resultados.add(" Jogadas válidas: " + Integer.toString(whiteTeam.cntValidPlays));
        resultados.add(" Tentativas inválidas: " + Integer.toString(whiteTeam.cntInvalidPlays));

        return resultados;
    }

    public int getIDPeca(int x, int y) {

        //Verifica se as coordenadas passadas são válidas
        if ((0 <= x && x < tabuleiro.length) && (0 <= y && y < tabuleiro.length)) {

            //Verifica se existe uma peça na posição passada
            if (tabuleiro[y][x] != null) {

                return tabuleiro[y][x].getId();
            }
        }
        return 0;
    }

    public int getIDEquipaAJogar() {
        return idEquipaAJogar;
    }

    public void setIdEquipaAJogar() {

        if (getIDEquipaAJogar() == blackTeam.getId()) {

            idEquipaAJogar = whiteTeam.getId();

        } else {

            idEquipaAJogar = blackTeam.getId();
        }
    }

    public boolean gravarJogo(File ficheiroDestino) {

        try {
            FileWriter writer = new FileWriter(ficheiroDestino);

            //Escrita da primeira linha do ficheiro, que é o tamanho do tabuleiro
            writer.write(getTamanhoTabuleiro() + "\n");

            //Escrita do número de peças existentes no jogo
            writer.write((blackTeam.crazyPieces.size() + whiteTeam.crazyPieces.size()) + "\n");

            //Percorre-se a lista onde estão todas as CrazyPieces
            for (CrazyPiece piece : crazyPieces) {

                //Escrita do id da peça no ficheiro
                writer.write(piece.getId() + ":");

                //Escrita do tipo da peça no ficheiro
                writer.write(piece.getIdType() + ":");

                //Escrita do id da equipa da peça no ficheiro
                writer.write(piece.getIdEquipa() + ":");

                //Escrita da alcunha da peça no ficheiro
                writer.write(piece.getNickname() + "\n");
            }

            //Percorre-se o tabuleiro
            for (int y = 0; y < tabuleiro.length; y++) {

                for (int x = 0; x < tabuleiro.length; x++) {

                    //Verifica-se se numa determinada posição do tabuleiro existe uma peça
                    if (tabuleiro[y][x] != null) {

                        //Esta condição é necessária para o escritor não escrever um ":" a mais em cada linha
                        if (x == tabuleiro.length - 1) {

                            writer.write(tabuleiro[y][x].getId());

                        } else {

                            writer.write(tabuleiro[y][x].getId() + ":");
                        }

                    } else {

                        //Se não houver nenhuma peça na posição atual do tabuleiro escreve-se 0, em vez do id da peça
                        //Esta condição é necessária para o escritor não escrever um ":" a mais em cada linha
                        if (x == tabuleiro.length - 1) {

                            writer.write("0");

                        } else {

                            writer.write("0:");
                        }
                    }
                }
                writer.write("\n");
            }

            //Escrita do id da equipa a jogar
            writer.write(getIDEquipaAJogar() + ":");

            //Escrita do nº de jogadas válidas da equipa preta
            writer.write(blackTeam.getCntValidPlays() + ":");

            //Escrita do nr capturas da equipa preta
            writer.write(blackTeam.getCntCaptures() + ":");

            //Escrita do nº de jogadas inválidas da equipa preta
            writer.write(blackTeam.getCntInvalidPlays() + ":");

            //Escrita do nº de jogadas válidas da equipa branca
            writer.write(whiteTeam.getCntValidPlays() + ":");

            //Escrita do nr capturas da equipa branca
            writer.write(whiteTeam.getCntCaptures() + ":");

            //Escrita do nº de jogadas inválidas da equipa preta
            writer.write(whiteTeam.getCntInvalidPlays() + "");

            //Fecho do escritor
            writer.close();

            return true;

        } catch (IOException e) {

            return false;
        }
    }

    public void anularJogadaAnterior() {

        for (CrazyPiece crazyPiece : lastPlayOutcome) {

            boolean pieceInGame = checkPieceInGame(crazyPiece);

            //Se for a peça que se moveu
            if (pieceInGame) {

                //Insere-a na posição antiga
                tabuleiro[crazyPiece.previousCoords.y][crazyPiece.previousCoords.x] = crazyPiece;

                crazyPiece.presentCoords = crazyPiece.previousCoords;

                //Decrementa o número de jogadas válidas e de capturas da equipa
                crazyPiece.getTeam().cntValidPlays--;
                crazyPiece.statistics.cntValidPlays--;

                //Ocorreu uma captura
                if(lastPlayOutcome.size() > 1){

                    crazyPiece.getTeam().cntCaptures--;
                    crazyPiece.statistics.cntCaptures--;

                    for(CrazyPiece captured : lastPlayOutcome){

                        if(!checkPieceInGame(captured)){

                            if(captured.getRelativeValue().equals("(infinito)")){

                                crazyPiece.statistics.cntPoints -= 1000;

                            } else {

                                crazyPiece.statistics.cntPoints -= captured.statistics.cntPoints;
                            }
                        }
                    }
                }
            }

            //Se for a peça capturada
            else {

                //Volta a inserir a peça capturada no jogo
                tabuleiro[crazyPiece.previousCoords.y][crazyPiece.previousCoords.x] = crazyPiece;

                crazyPiece.presentCoords = crazyPiece.previousCoords;

                //Volta a adicionar a peça á lista de peças em jogo da equipa
                crazyPiece.getTeam().inGameCrazyPieces.add(crazyPiece);
            }
        }

        //Troca os jokers das equipas para a sua máscara anterior
        for (Joker joker : blackTeam.jokers) {

            joker.switchJokerType();
        }

        for (Joker joker : whiteTeam.jokers) {

            joker.switchJokerType();
        }

        //Troca o id da equipa a jogar (repete a jogada anterior)
        setIdEquipaAJogar();

        //Repõe a lista de alterações da última jogada
        lastPlayOutcome = new ArrayList<>();
    }

    public CrazyPiece getPeca(String[] lineData) {

        //Conforme a linha passada como parâmetro, devolve a peça do tipo pretendido
        int idPiece = Integer.parseInt(lineData[0]);
        int idType = Integer.parseInt(lineData[1]);
        int idTeam = Integer.parseInt(lineData[2]);
        String nickname = lineData[3];

        switch (idType) {

            case 0:
                return new Rei(idPiece, idType, idTeam, nickname);

            case 1:
                return new Rainha(idPiece, idType, idTeam, nickname);

            case 2:
                return new PoneiMagico(idPiece, idType, idTeam, nickname);

            case 3:
                return new PadreDaVila(idPiece, idType, idTeam, nickname);

            case 4:
                return new TorreH(idPiece, idType, idTeam, nickname);

            case 5:
                return new TorreV(idPiece, idType, idTeam, nickname);

            case 6:
                return new Lebre(idPiece, idType, idTeam, nickname);

            default:
                return new Joker(idPiece, idType, idTeam, nickname);
        }
    }

    public boolean possibilidadesJogadasValidas (int xO, int yO, int xD, int yD) {

        //Verifica se a jogada passada é válida (efetua as mesmas verificações que a função "processaJogada()", apenas sem as alterações ás peças e ás estatisticas das equipas

        //Retorna a peça que se pretende mover
        CrazyPiece crazyPiece = tabuleiro[yO][xO];

        //Verifica se a posição final é válida
        if ((0 <= xD && xD < tabuleiro.length) && (0 <= yD && yD < tabuleiro.length)) {

            //Verifica se a peça pode se movmentar
            if (crazyPiece.checkValidMovement(xO, yO, xD, yD)) {

                //Devolve TRUE se a peça se mover ou se capturar uma peça da equipa oposta
                return tabuleiro[yD][xD] == null || (tabuleiro[yD][xD] != null && crazyPiece.getIdEquipa() != tabuleiro[yD][xD].getIdEquipa());
            }
        }

        return false;
    }

    public List<Comparable> obterSugestoesJogada(int xO, int yO){

        //Atualiza as máscaras dos jokers de ambas as equipas para o caso de não estarem atualizadas com o turno atual
        for (Joker joker : blackTeam.jokers) {
            joker.switchJokerType();
        }

        for (Joker joker : whiteTeam.jokers) {
            joker.switchJokerType();
        }

        ArrayList<Comparable> jogadasValidas = new ArrayList<>();

        CrazyPiece crazyPiece;

        //Verifica se a posição inicial é válida
        if ((0 <= xO && xO < tabuleiro.length) && (0 <= yO && yO < tabuleiro.length)) {

            return jogadasValidas;
        }

        //Verifica se existe uma peça na posição pretendida
        if(tabuleiro[yO][xO] == null){

            return jogadasValidas;

        } else {

            crazyPiece = tabuleiro[yO][xO];
        }

        //Verifica se é a vez da equipa dessa peça jogar
        if(crazyPiece.getTeam().getId() != getIDEquipaAJogar()){

            return jogadasValidas;
        }

        //Itera todas as jogadas que essa peça está programada para fazer
        for(String jogada : crazyPiece.getValidPlays(xO, yO)){

            int xD = Integer.parseInt(jogada.split(", ")[0]);

            int yD = Integer.parseInt(jogada.split(", ")[1]);

            //Verifica se essas jogadas são válidas para a situação em questão
            if(possibilidadesJogadasValidas(xO, yO, xD, yD)){

                jogadasValidas.add(jogada);
            }
        }

        //Devolve apenas as jogadas dessa peça que são válidas para a situação em questão
        return jogadasValidas;
    }

    public boolean checkPieceInGame(CrazyPiece crazyPiece){

        return crazyPiece.presentCoords.x != -1 && crazyPiece.presentCoords.y != -1;

    }

    public Map<String, List<String>> getEstatisticas(){

        Map<String, List<String>> estatisticas = new HashMap<>();

        return estatisticas;
    }
}
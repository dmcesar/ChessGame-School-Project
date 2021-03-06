package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

import static pt.ulusofona.lp2.crazyChess.Simulador.*;

public class TestSimulador {
    @Test
    public void testIniciaJogo01(){
        File ficheiro = new File("test-files/FicheiroDadosAMais.txt");
        Simulador simulador = new Simulador();
        String resultadoReal = "";

        try {
            try {
                simulador.iniciaJogo(ficheiro);
            } catch (InvalidSimulatorInputException e) {
                resultadoReal = e.getDescricaoProblema();
            }
        } catch (IOException e){}

        //Como o ficheiro não existe, o resultado esperado é false
        assertEquals("DADOS A MAIS (Esperava: 4 ; Obtive: 5)", resultadoReal);
    }

    @Test
    public void testIniciaJogo02(){
        File ficheiro = new File("test-files/FicheiroDadosAMais.txt");
        Simulador simulador = new Simulador();
        int resultadoReal = 0;

        try {


            try {
                simulador.iniciaJogo(ficheiro);
            } catch (InvalidSimulatorInputException e) {
                resultadoReal = e.getLinhaErro();
            }
        } catch (IOException e){}

        //Como o ficheiro não existe, o resultado esperado é false
        assertEquals(12, resultadoReal);
    }

    @Test
    public void testIniciaJogo03(){
        File ficheiro = new File("test-files/FicheiroDadosAMenos.txt");
        Simulador simulador = new Simulador();
        String resultadoReal = "";

        try {
            try {
                simulador.iniciaJogo(ficheiro);
            } catch (InvalidSimulatorInputException e) {
                resultadoReal = e.getDescricaoProblema();
            }
        } catch (IOException e){}

        //Como o ficheiro não existe, o resultado esperado é false
        assertEquals("DADOS A MENOS (Esperava: 4 ; Obtive: 3)", resultadoReal);

    }

    @Test
    public void testIniciaJogo04(){
        File ficheiro = new File("test-files/FicheiroDadosAMenos.txt");
        Simulador simulador = new Simulador();
        int resultadoReal = 0;

        try {


            try {
                simulador.iniciaJogo(ficheiro);
            } catch (InvalidSimulatorInputException e) {
                resultadoReal = e.getLinhaErro();
            }
        } catch (IOException e){}

        //Como o ficheiro não existe, o resultado esperado é false
        assertEquals(3, resultadoReal);
    }


    @Test
    public void testJaVemVitoria(){
        File ficheiro = new File("test-files/FicheiroComVitoria.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.jogoTerminado();
        //Deve retornar true pois há condições de paragem do jogo
        assertEquals(true, resultadoReal);

    }


    @Test
    public void testJaVemVitoria02(){
        File ficheiro = new File("test-files/FicheiroComVitoria.txt");
        Simulador simulador = new Simulador();

        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        simulador.jogoTerminado();

        List<String> resultadoReal = simulador.getResultados();

        List<String> resultados = new ArrayList<>();
        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: VENCERAM AS PRETAS");
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(" Capturas: 0");
        resultados.add(" Jogadas válidas: 0");
        resultados.add(" Tentativas inválidas: 0");

        resultados.add("Equipa das Brancas");
        resultados.add(" Capturas: 0");
        resultados.add(" Jogadas válidas: 0");
        resultados.add(" Tentativas inválidas: 0");

        assertEquals(resultados, resultadoReal);

    }


    @Test
    public void testJaVemEmpate(){
        File ficheiro = new File("test-files/FicheiroComEmpate.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.jogoTerminado();
        //Deve retornar true pois há condições de paragem do jogo
        assertEquals(true, resultadoReal);

    }


    @Test
    public void testJaVemEmpate02(){
        File ficheiro = new File("test-files/FicheiroComEmpate.txt");
        Simulador simulador = new Simulador();

        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        simulador.jogoTerminado();

        List<String> resultadoReal = simulador.getResultados();

        List<String> resultados = new ArrayList<>();
        resultados.add("JOGO DE CRAZY CHESS");
        resultados.add("Resultado: EMPATE");
        resultados.add("---");

        resultados.add("Equipa das Pretas");
        resultados.add(" Capturas: 0");
        resultados.add(" Jogadas válidas: 0");
        resultados.add(" Tentativas inválidas: 0");

        resultados.add("Equipa das Brancas");
        resultados.add(" Capturas: 0");
        resultados.add(" Jogadas válidas: 0");
        resultados.add(" Tentativas inválidas: 0");

        assertEquals(resultados, resultadoReal);

    }

    @Test
    public void testJogadaInvalida01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(0, 0, 1, 1);
        //Deve retornar false pois não existe nenhuma peça naquela posição
        assertEquals(false, resultadoReal);
    }


    @Test
    public void testJogadaInvalida02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(1, 0, 1, 0);
        //Deve retornar false pois as coordenadas de origem e destino são iguais
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testJogadaInvalida03(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(1, 0, 1, -1);
        //Deve retornar false pois as coordenadas de destino são fora do tabuleiro
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testJogadaInvalida04(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(1, 0, 0, 1);
        //Deve retornar false pois nas coordenadas de destino está lá uma peça da mesma equipa
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testJogadaInvalida05(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(0, 5, 0, 4);
        //Deve retornar false pois não é esta equipa a jogar
        assertEquals(false, resultadoReal);
    }

    //Fim dos testes das peças da equipa preta no ficheiro "input2"

    @Test
    public void testProcessaJogadaRei01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(1, 0, 3, 2);
        //Deve retornar false pois não é compatível com o seu movimento
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaRei02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(1, 0, 2, 1);
        //Deve retornar true pois é compatível com o seu movimento
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testProcessaJogadaReiCaptura01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(6, 1, 5, 2);
        //Deve retornar true pois a captura é de uma peça da equipa contrária
        assertEquals(true, resultadoReal);

    }

    @Test
    public void testSugereJogadaRei(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        List<Comparable> resultados = simulador.obterSugestoesJogada(1, 0);
        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }

        List<Comparable> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("2, 0, 0");
        resultadoEsperado.add("0, 0, 0");
        resultadoEsperado.add("1, 1, 0");
        resultadoEsperado.add("2, 1, 0");

        assertEquals(resultadoEsperado, resultadosReais);

    }


    @Test
    public void testProcessaJogadaRainha01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}


        boolean resultadoReal = simulador.processaJogada(4, 1, 7, 4);
        //Deve retornar false pois há bloqueio no caminho da rainha
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaRainha02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}


        boolean resultadoReal = simulador.processaJogada(4, 1, 4, 6);
        //Deve retornar true pois o movimento é compatível com o da rainha
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testProcessaJogadaRainha03(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}


        boolean resultadoReal = simulador.processaJogada(4, 1, 4, 7);
        //Deve retornar false pois a distância é maior que 5
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testSugereJogadasRainha(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}


        List<Comparable> resultados = simulador.obterSugestoesJogada(4,1);
        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }

        List<String> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("5, 1, 0");
        resultadoEsperado.add("3, 1, 0");
        resultadoEsperado.add("4, 2, 0");
        resultadoEsperado.add("4, 0, 0");
        resultadoEsperado.add("5, 2, 0");
        resultadoEsperado.add("5, 0, 0");
        resultadoEsperado.add("3, 2, 0");
        resultadoEsperado.add("3, 0, 0");
        resultadoEsperado.add("2, 1, 0");
        resultadoEsperado.add("4, 3, 0");
        resultadoEsperado.add("6, 3, 3");
        resultadoEsperado.add("2, 3, 0");
        resultadoEsperado.add("1, 1, 0");
        resultadoEsperado.add("4, 4, 0");
        resultadoEsperado.add("1, 4, 0");
        resultadoEsperado.add("4, 5, 0");
        resultadoEsperado.add("4, 6, 0");

        assertEquals(resultadoEsperado, resultadosReais);
    }


    @Test
    public void testJogadaPonei01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(0, 1, 2, 3);
        //Deve retornar true
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testJogadaPonei02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(0, 1, 3, 2);
        //Deve retornar false pois o movimento faz com que o ponei mova 3 casa na horizontal
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testSugestoesJogadasPonei(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        List<Comparable> resultados = simulador.obterSugestoesJogada(0,1);

        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }

        List<String> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("2, 3, 0");

        assertEquals(resultadoEsperado, resultadosReais);

    }

    @Test
    public void testProcessaJogadaLebre01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(3, 4, 4, 5);
        //Deve retornar true pois apenas anda uma casa na diagonal
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testProcessaJogadaLebre02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        boolean resultadoReal = simulador.processaJogada(3, 4, 5, 6);
        //Deve retornar false pois anda 2 casas na diagonal
        assertEquals(false, resultadoReal);
    }


    @Test
    public void testSugereJogadasLebre(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        List<Comparable> resultados = simulador.obterSugestoesJogada(3,4);
        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }
        List<String> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("4, 5, 0");
        resultadoEsperado.add("4, 3, 0");
        resultadoEsperado.add("2, 5, 0");
        resultadoEsperado.add("2, 3, 0");

        assertEquals(resultadoEsperado, resultadosReais);
    }

    //Fim dos testes das peças da equipa preta no ficheiro "input2"

    //Início dos testes das peças da equipa branca no ficheiro "input2"


    @Test
    public void testProcessaJogadaPadre01() {
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 3, 5, 2);

        //Deve retornar false pois o bispo fica a uma 1 casa de distância da rainha
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaPadre02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 3, 3, 0);

        //Deve retornar false pois há bloqueio no seu caminho
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaPadre03(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 3, 7, 4);

        //Deve retornar true
        assertEquals(true, resultadoReal);
    }


    @Test
    public void testSugereJogadasPadre(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 2, 3);

        List<Comparable> resultados = simulador.obterSugestoesJogada(6,3);
        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }

        List<String> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("7, 4, 0");
        resultadoEsperado.add("7, 2, 0");
        resultadoEsperado.add("5, 4, 0");
        resultadoEsperado.add("4, 5, 0");
        resultadoEsperado.add("4, 1, 8");
        resultadoEsperado.add("3, 6, 0");

        assertEquals(resultadoEsperado, resultadosReais);
    }


    @Test
    public void testProcessaJogadaTorreV01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 6, 7, 6);

        //Deve retornar false pois a torreV não se pode mover na horizontal
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaTorreV02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 6, 6, 4);

        //Deve retornar true
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testProcessaJogadaTorreV03(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        simulador.setIdEquipaAJogar();

        boolean resultadoReal = simulador.processaJogada(6, 6, 6, 2);

        //Deve retornar true, apesar de existir uma captura
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaTorreV04(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(6, 6, 6, 0);

        //Deve retornar false, pois existe bloqueio no seu caminho
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testSugereJogadasTorreV(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        List<Comparable> resultados = simulador.obterSugestoesJogada(6, 6);

        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }

        List<String> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("6, 7, 0");
        resultadoEsperado.add("6, 5, 0");
        resultadoEsperado.add("6, 4, 0");

        assertEquals(resultadoEsperado, resultadosReais);
    }

    @Test
    public void testProcessaJogadaTorreH01(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(4, 7, 4, 6);

        //Deve retornar false, pois esta peça não se pode mover na vertical
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaTorreH02(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(4, 7, 5, 6);

        //Deve retornar false, pois esta peça não se pode mover na diagonal
        assertEquals(false, resultadoReal);
    }

    @Test
    public void testProcessaJogadaTorreH03(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        boolean resultadoReal = simulador.processaJogada(4, 7, 7, 7);

        //Deve retornar true
        assertEquals(true, resultadoReal);
    }

    @Test
    public void testSugereJogadasTorreH(){
        File ficheiro = new File("test-files/input2.txt");
        Simulador simulador = new Simulador();
        try {
            simulador.iniciaJogo(ficheiro);
        } catch (IOException e){}

        //De forma a que haja uma jogada da equipa preta, para de seguida ser a equipa branca a jogar
        simulador.processaJogada(3, 4, 4, 5);

        List<Comparable> resultados = simulador.obterSugestoesJogada(4, 7);

        List<String> resultadosReais = new ArrayList<>();

        for (Comparable resultado: resultados){
            resultadosReais.add(resultado.toString());
        }


        List<String> resultadoEsperado = new ArrayList<>();

        resultadoEsperado.add("5, 7, 0");
        resultadoEsperado.add("3, 7, 0");
        resultadoEsperado.add("6, 7, 0");
        resultadoEsperado.add("2, 7, 0");
        resultadoEsperado.add("7, 7, 0");
        resultadoEsperado.add("1, 7, 0");
        resultadoEsperado.add("0, 7, 0");

        assertEquals(resultadoEsperado, resultadosReais);
    }

}

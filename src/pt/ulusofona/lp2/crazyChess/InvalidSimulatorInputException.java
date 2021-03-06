package pt.ulusofona.lp2.crazyChess;

import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {

    private int linhaErro;
    private String descricao;

    public InvalidSimulatorInputException(){}

    public InvalidSimulatorInputException(int linhaErro, String descricao){

        this.linhaErro = linhaErro;
        this.descricao = descricao;
    }

    public void setLinhaErro(int linhaErro){

        this.linhaErro = linhaErro;
    }

    public void setDescricao(String descricao){

        this.descricao = descricao;
    }

    public int getLinhaErro(){return this.linhaErro;}

    public String getDescricaoProblema(){return this.descricao;}
}
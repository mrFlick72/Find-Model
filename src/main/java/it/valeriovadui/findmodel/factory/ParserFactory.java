package it.valeriovadui.findmodel.factory;

import it.valeriovadui.findmodel.service.ComplexParser;
import it.valeriovadui.findmodel.service.Parser;
import it.valeriovadui.findmodel.service.SimpleParser;

/**
 * @author mrFlick72
 */
public class ParserFactory {

    private ParserFactory(){

    }

    public static Parser getSimParserParser(String formula){
        return new SimpleParser(formula);
    }

    public static Parser getCompelxParser(String formula){
        return new ComplexParser(formula);
    }
}

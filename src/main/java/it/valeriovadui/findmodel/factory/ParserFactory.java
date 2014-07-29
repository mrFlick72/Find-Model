package it.valeriovadui.findmodel.factory;

import it.valeriovadui.findmodel.service.ComplexParser;
import it.valeriovadui.findmodel.service.Parser;
import it.valeriovadui.findmodel.service.SimpleParser;

/**
 * Created by Valerio on 29/07/2014.
 */
public class ParserFactory {
    public static Parser getSimParserParser(String formula){return new SimpleParser(formula);}
    public static Parser getCompelxParser(String formula){return new ComplexParser(formula);}
}

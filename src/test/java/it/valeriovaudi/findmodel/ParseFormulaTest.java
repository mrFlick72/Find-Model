package it.valeriovaudi.findmodel;

import it.valeriovadui.findmodel.control.OperatorControl;
import it.valeriovadui.findmodel.factory.ParserFactory;
import it.valeriovadui.findmodel.model.Formula;
import it.valeriovadui.findmodel.service.Parser;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Valerio on 30/07/2014.
 */
public class ParseFormulaTest {


    @Test
    public void parseFormulaTest(){
        String formulaString = "( a => b )";

        Parser parser = ParserFactory.getCompelxParser(formulaString);

        Formula formula = parser.parse();
        String[] allVariable = formula.getAllVariable();
        List<Map<String, Boolean>> hashMaps = OperatorControl.checkModel(formula, allVariable);
        System.out.println(hashMaps);

    }
}

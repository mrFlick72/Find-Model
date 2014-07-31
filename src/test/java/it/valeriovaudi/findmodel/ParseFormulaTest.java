package it.valeriovaudi.findmodel;

import it.valeriovadui.findmodel.control.OperatorControl;
import it.valeriovadui.findmodel.factory.ParserFactory;
import it.valeriovadui.findmodel.model.ElementaryFormula;
import it.valeriovadui.findmodel.model.Expression;
import it.valeriovadui.findmodel.model.Formula;
import it.valeriovadui.findmodel.service.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Created by Valerio on 30/07/2014.
 */
public class ParseFormulaTest extends AbstractGenericTestConfiguration{

    @MockitoAnnotations.Mock
    Parser parser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /*
    * Test that the evaluation of formula: ( a => b ) is correct
    * For this i'm mock the parser interface and test only the evaluation operation without test the parsing work
    * */
    @Test
    public void interpretationFormulaTest(){
        when(parser.parse()).thenReturn(getFormulaTest());

        Formula formula = parser.parse();
        String[] allVariable = formula.getAllVariable();
        List<Map<String, Boolean>> hashMaps = OperatorControl.checkModel(formula, allVariable);
        logger.info(hashMaps);
    }


    @Test
    public void parseFormulaTest() throws Exception {
        Parser formulaParser = ParserFactory.getSimParserParser("( a => b )");

        Formula parse = formulaParser.parse();
        Assert.assertEquals(parse,getFormulaTest());
    }

    /* helper method that return a Formula object that is a object representation of ( a => b ) formula */
    private Formula getFormulaTest(){
        Formula resultFormulaFirstElement = new ElementaryFormula("a",false);
        Formula resultFormulaSecondElement = new ElementaryFormula("b",false);

        Formula resultFormulaExpression = new Expression(resultFormulaFirstElement,resultFormulaSecondElement,Expression.IMPLICATION,false);

        return resultFormulaExpression;
    }
}

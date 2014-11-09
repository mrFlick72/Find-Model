package it.valeriovadui.findmodel.service;

import it.valeriovadui.findmodel.model.Formula;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mrFlick72
 */
public abstract class AbstractParser implements Parser{
    protected static Logger logger = Logger.getLogger(AbstractParser.class);
    protected static final boolean DEBUG = true;
    public static final int Q0 = 0, Q1 = 1, Q2 = 2, Q3 = 3 , Q4 = 4 , Q5 = 5 , Q6 = 6 , Q7 = 7;
    protected int currentState;
    protected final LinkedList<Integer> pila;
    protected final LinkedList<Formula> queue;

    public static final int Z0 = 0;
    public static final int F = 1;
    public static final int F1 = 1;
    public static final int F2 = 2;
    public static final int F11 = 11;
    public static final int F22 = 22;
    public static final int E = 3;
    public static final int OP = 4;
    public static final int OP1 = 5;

    protected final String formula;


    public AbstractParser(String formula){
        currentState = Q0;

        pila = new LinkedList<>();
        queue = new LinkedList<>();

        this.formula = formula;

        pila.add(Z0);
    }

    public int getCurrenState(){
        return currentState;
    }

    protected void setCurrentState(int currentState){
        this.currentState = currentState;
    }
    @Override
    public abstract Formula parse();

    protected static void stampaPila(List l){

        Iterator<Integer> iteratore = l.iterator();
        int aux;
        logger.info("Current value of the stack");

        while(iteratore.hasNext()){
            aux = iteratore.next();
            if(aux == Z0){
                logger.info("Z0");
            } else if(aux == F1){
                logger.info("F1");
            } else if(aux == F2){
                logger.info("F2");
            } else if(aux == F11){
                logger.info("F11");
            } else if(aux == F22){
                logger.info("F22");
            } else if(aux == E){
                logger.info("E");
            } else if(aux == OP){
                logger.info("OP");
            } else if(aux == OP1){
                logger.info("OP1");
            }
        }

        logger.info("---------------------");
    }
}

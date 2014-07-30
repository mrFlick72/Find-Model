package it.valeriovadui.findmodel.service;

import it.valeriovadui.findmodel.model.ElementaryFormula;
import it.valeriovadui.findmodel.model.Expression;
import it.valeriovadui.findmodel.model.Formula;

import java.util.StringTokenizer;

/**
 * @author mrFlick72
 */
public final class SimpleParser extends AbstractParser {
    public SimpleParser(String formula) {
        super(formula);
    }

    @Override
    // implementation of a simple stack automaton
    public Formula parse(){
        int count  = 0;
        Formula result = null;
        Formula  currentFormula;
        Expression auxExp;

        boolean isError = false;
        int index = 0;

        if(formula != null && !formula.equals("")){

            StringTokenizer stringToken = new StringTokenizer(formula);
            String aux;

            boolean negation = false;


            while(stringToken.hasMoreTokens() && !isError){

                aux = stringToken.nextToken();
                logger.info(aux);

                if(aux.contains("!"))
                    negation =  true;

                switch(currentState){

                    case Q0:
                        count++;
                        logger.info("Step Number: " + count);
                        logger.info("First Step");

                        if(aux.contains("(") && pila.get(0) == Z0){

                            pila.add(0,E);
                            pila.add(0,F);
                            pila.add(0,OP);
                            pila.add(0,F);

                            result = new Expression(negation);

                            currentFormula = result;
                            queue.add(currentFormula);

                            // change state
                            currentState = Q1;
                        }
                        else if(!aux.contains("(") && formula.length() == 1){

                            result = new ElementaryFormula(aux.replace("!", ""), negation);

                            currentState = Q6;

                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }

                        if(debug)
                            StampaPila(pila);

                        break;

                    case Q1:
                        count++;
                        logger.info("Step Number: " + count);

                        logger.info("mi trovo nello stato 1 del'automa");

                        if(aux.contains("(") && pila.get(0) == F){

                            // mi trovo nello stato 1 del'automa
                            // I put data on the stack
                            pila.removeFirst();
                            pila.add(0,E);
                            pila.add(0,F);
                            pila.add(0,OP);
                            pila.add(0,F);

                            // setto l'espressione correntemente puntata e la metto in coda

                            currentFormula = new Expression(negation);

                            // I put in the queue currentFormula
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);

                        }
                        else if(!aux.contains("(") && pila.get(0) == F){

                            // mi trovo nello stato 2 del'automa
                            // I put data on the stack
                            pila.removeFirst();

                            currentFormula = new ElementaryFormula(aux.replace("!", ""),negation);

                            // I put in the queue currentFormula
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);


                            currentState = Q2;
                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }
                        break;

                    case Q2:
                         /* STATO IN CUI SETTO L'OPERATORE */
                        count++;
                        logger.info("Step Number: " + count);

                        logger.info("mi trovo nello stato 2 del'automa");

                        if((aux.contains(Expression.OR_STRING) ||
                                aux.contains(Expression.AND_STRING) ||
                                aux.contains(Expression.IMPLICATION_STRING))
                                && pila.get(0) == OP){
                            // mi trovo nello stato 3 del'automa
                            // I put data on the stack

                            pila.removeFirst();

                            index = queue.size() -2;

                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            // setto l'operatore
                            auxExp.setOperatorType(Expression.getOperatorType(aux));

                            // reinserisco l'espressione nel suo posto

                            currentFormula = auxExp;

                            queue.set(index, currentFormula);


                            if(debug)
                                StampaPila(pila);

                            currentState = Q3;

                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }


                        break;

                    case Q3:
                         /* TROVO UNA VARIABILE E L'INSERISCO IN CODA */
                        count++;
                        logger.info("Step Number: " + count);

                        logger.info("mi trovo nello stato 3 del'automa");

                        if(!aux.contains("(") && pila.get(0) == F){
                            // mi trovo nello stato 4 del'automa
                            // I put data on the stack


                            pila.removeFirst();

                            currentFormula = new ElementaryFormula(aux.replace("!", ""),negation);

                            // currentFormula la metto in coda
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);

                            currentState = Q4;


                        }
                        else if(aux.contains("(") && pila.get(0) == F){
                            // mi trovo nello stato 1 del'automa
                            // I put data on the stack

                            pila.removeFirst();

                            pila.add(0, E);
                            pila.add(0, F);
                            pila.add(0, OP);
                            pila.add(0, F);

                            currentFormula = new Expression(negation);

                            // currentFormula la metto in coda
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);

                            currentState = Q1;


                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }
                        break;

                    case Q4:
                        count++;
                        logger.info("Step Number: "+ count);

                        logger.info("mi trovo nello stato 4 del'automa");

                        if(aux.contains(")") && pila.get(0) == E){
                            // mi trovo nello stato 5 del'automa
                            // I put data on the stack
                            logger.info("transizione dello stato 4 del'automa per andare allo stato 5");

                            pila.removeFirst(); // rimuovo il primo elemento a fiorante

                            // mi trovo il root
                            index = queue.size() -3;

                            // ottengo l0espressione root
                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            // setto le formule
                            auxExp.setFormulaOne(queue.get(index +1));
                            auxExp.setFormulaTwo(queue.get(index +2));

                            // elimino le formule settate elementari assegnate
                            queue.removeLast();
                            queue.removeLast();

                            if(debug)
                                StampaPila(pila);

                            currentState = Q5;

                        }
                        else if(aux.contains(")") && pila.get(0) == E && pila.get(1) == Z0){
                        /* IMPORTANTE questo stato porta ad uno stato finale */
                            pila.removeFirst();

                            if(debug)
                                StampaPila(pila);

                            currentState = Q6;

                            // mi trovo il root
                            index = queue.size() -3;

                            // ottengo l0espressione root
                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            logger.info("Step Number: " + queue.size());
                            // setto le formule

                            auxExp.setFormulaOne(queue.get(index +1));
                            auxExp.setFormulaTwo(queue.get(index +2));

                            // elimino le formule settate elementari assegnate

                            queue.removeLast();
                            queue.removeLast();

                            result = queue.getFirst();

                            if(currentState == Q6){
                                logger.info("Step Number: "+ count);
                                logger.info("Mi trovo nello stato finale dell'automa");
                                logger.info("Riconoscimento Completato");
                            }

                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }
                        break;

                    case Q5:
                        count++;
                        logger.info("Step Number: "+ count);

                        logger.info("mi trovo nello stato 5 del'automa");

                        if((aux.contains(Expression.OR_STRING) ||
                                aux.contains(Expression.AND_STRING) ||
                                aux.contains(Expression.IMPLICATION_STRING))
                                && pila.get(0) == OP){
                            // mi trovo nello stato 5 del'automa
                            // I put data on the stack

                            logger.info("mi trovo nello stato 3 del'automa");

                            pila.removeFirst(); // rimuovo il primo elemento a fiorante

                            index = queue.size() -2;

                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            // setto l'operatore
                            auxExp.setOperatorType(Expression.getOperatorType(aux));

                            // reinserisco l'espressione nel suo posto
                            currentFormula = auxExp;

                            queue.set(index, currentFormula);

                            if(debug)
                                StampaPila(pila);

                            currentState = Q3;

                        } else if(aux.contains(")") && pila.get(0) == E){


                            pila.removeFirst();

                            index = queue.size() -3;

                            // ottengo l0espressione root
                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            // setto le formule
                            auxExp.setFormulaOne(queue.get(index +1));
                            auxExp.setFormulaTwo(queue.get(index +2));

                            // elimino le formule settate elementari assegnate

                            queue.removeLast();
                            queue.removeLast();

                            if(debug)
                                StampaPila(pila);

                            currentState = Q5;

                        }else if(aux.contains(")") && pila.get(0) == E && pila.get(1) == Z0){

                            pila.removeFirst();

                            index = queue.size() -3;

                            // ottengo l0espressione root
                            currentFormula = queue.get(index);

                            auxExp = (Expression) currentFormula;

                            // setto le formule
                            auxExp.setFormulaOne(queue.get(index +1));
                            auxExp.setFormulaTwo(queue.get(index +2));

                            // elimino le formule settate elementari assegnate

                            queue.removeLast();
                            queue.removeLast();

                            if(debug)
                                StampaPila(pila);

                            currentState = Q6;

                            if(currentState == Q6){
                                logger.info("Step Number: " + count);
                                logger.info("Mi trovo nello stato finale dell'automa");
                                logger.info("Riconoscimento Completato");
                            }
                        }
                        else{
                            isError = true;
                            currentState = Q7;
                        }
                        break;

                    case Q6:
                        count++;
                        logger.info("Step Number: " + count);
                        logger.info("Mi trovo nello stato finale dell'automa");
                        logger.info("Riconoscimento Completato");
                        break;
                }

                negation =  false;
            }

        }
        return result;
    }
}

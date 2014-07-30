package it.valeriovadui.findmodel.service;

import it.valeriovadui.findmodel.model.ElementaryFormula;
import it.valeriovadui.findmodel.model.Expression;
import it.valeriovadui.findmodel.model.Formula;

import java.util.StringTokenizer;

/**
 * @author mrFlick72
 */
public final class ComplexParser extends AbstractParser {
    public ComplexParser(String formula) {
        super(formula);
    }

    public Formula parse(){
        int count  = 0;
        Formula result = null;
        Formula  currentFormula = null;
        Expression auxExp = null;
        ElementaryFormula auxEl = null;

        boolean init = false;
        int index = 0;

        if(formula != null && !formula.equals("")){

            StringTokenizer stringToken = new StringTokenizer(formula);
            String aux;

            boolean negation = false;


            while(stringToken.hasMoreTokens()){

                aux = stringToken.nextToken();
                logger.info(aux);

                if(aux.contains("!"))
                    negation =  true;

                switch(currentState){

                    case Q0:
                        count++;

                        logger.info("Passo numero " + count);
                        logger.info("Stato iniziale");

                        if(aux.contains("(") && pila.get(0) == Z0){

                            pila.add(0,E);
                            pila.add(0,F2);
                            pila.add(0,OP);
                            pila.add(0,F1);

                            result = new Expression(negation);

                            currentFormula = result;
                            queue.add(currentFormula);



                            // mi muovo nello stato corretto
                            currentState = Q1;
                        }
                        else if(!aux.contains("(")){

                            result = new ElementaryFormula(aux.replace("!", ""), negation);

                            currentState = Q6;
                        }

                        if(debug)
                            StampaPila(pila);

                        break;

                    case Q1:
                        count++;
                        logger.info("Passo numero " + count);

                        logger.info("mi trovo nello stato 1 del'automa");

                        if(aux.contains("(") && pila.get(0) == F1){

                            // mi trovo nello stato 1 del'automa
                            // aggiungo i dati in pila
                            pila.set(0, F11);
                            pila.add(0,E);
                            pila.add(0,F2);
                            pila.add(0,OP);
                            pila.add(0,F1);

                            // setto l'espressione correntemente puntata e la metto in coda

                            currentFormula = new Expression(negation);

                            // currentFormula la metto in coda
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);

                        }
                        else if(aux.contains("(") && pila.get(0) == F2){

                            // mi trovo nello stato 1 del'automa
                            // aggiungo i dati in pila
                            pila.set(0, F22);
                            pila.add(0,E);
                            pila.add(0,F2);
                            pila.add(0,OP);
                            pila.add(0,F1);

                            // setto l'espressione correntemente puntata e la metto in coda

                            currentFormula = new Expression(negation);

                            // currentFormula lametto in coda
                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);

                        }
                        else if(!aux.contains("(") && pila.get(0) == F1){

                            // mi trovo nello stato 2 del'automa
                            // aggiungo i dati in pila
                            pila.set(0, F11);

                            currentFormula = new ElementaryFormula(aux.replace("!", ""),negation);

                            // currentFormula lametto in coda

                            queue.add(currentFormula);

                            if(debug)
                                StampaPila(pila);


                            currentState = Q2;
                        }
                        break;

                    case Q2:
                         /* STATO IN CUI SETTO L'OPERATORE */
                        count++;
                        logger.info("Passo numero " + count);

                        logger.info("mi trovo nello stato 2 del'automa");

                        if((aux.contains(Expression.OR_STRING) ||
                                aux.contains(Expression.AND_STRING) ||
                                aux.contains(Expression.IMPLICATION_STRING))){

                            // controllo la pila

                            if(pila.get(0) == F11 && pila.get(1) == OP){
                                // mi trovo nello stato 3 del'automa
                                // aggiungo i dati in pila

                                pila.removeFirst();
                                pila.set(0, OP1);

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

                            }
                        }

                        currentState = Q3;
                        break;

                    case Q3:
                         /* TROVO UNA VARIABILE E L'INSERISCO IN CODA */
                        count++;
                        logger.info("Passo numero " + count);

                        logger.info("mi trovo nello stato 3 del'automa");

                        if(!aux.contains("(")){

                            // controllo la pila

                            if(pila.get(0) == OP1 && pila.get(1) == F2){
                                // mi trovo nello stato 4 del'automa
                                // aggiungo i dati in pila


                                pila.removeFirst();
                                pila.set(0, F22);

                                currentFormula = new ElementaryFormula(aux.replace("!", ""),negation);

                                // currentFormula la metto in coda
                                queue.add(currentFormula);

                                if(debug)
                                    StampaPila(pila);

                                currentState = Q4;

                            }
                        }
                        else if(aux.contains("(")){
                         /* TROVO UN ALTRA PARENTESI E CREO UN ALTRA SOTTO ESPRESSIONE */
                            // controllo la pila

                            if(pila.get(0) == OP1 && pila.get(1) == F2){
                                // mi trovo nello stato 1 del'automa
                                // aggiungo i dati in pila

                                pila.removeFirst(); // rimuovo il primo elemento a fiorante
                                pila.removeFirst(); // rimuovo il secondo elemento a fiorante

                                pila.add(0, F22);
                                pila.add(0, E);
                                pila.add(0, F2);
                                pila.add(0, OP);
                                pila.add(0, F1);

                                currentFormula = new Expression(negation);

                                // currentFormula la metto in coda
                                queue.add(currentFormula);

                                if(debug)
                                    StampaPila(pila);

                                currentState = Q1;

                            }
                        }
                        break;

                    case Q4:
                        count++;
                        logger.info("Passo numero " + count);

                        logger.info("mi trovo nello stato 4 del'automa");

                        if(aux.contains(")")){


                            // controllo la pila

                            if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == F11){
                                // mi trovo nello stato 5 del'automa
                                // aggiungo i dati in pila
                                logger.info("transizione dello stato 4 del'automa per andare allo stato 5");

                                pila.removeFirst(); // rimuovo il primo elemento a fiorante
                                pila.removeFirst(); // rimuovo il secondo elemento a fiorante
                                pila.removeFirst(); // rimuovo il terzo elemento a fiorante

                                pila.add(0, F11);

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
                            else if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == F22){
                                // mi trovo nello stato 5 del'automa
                                // aggiungo i dati in pila

                                logger.info("transizione dello stato 4 del'automa per andare allo stato 5");


                                pila.removeFirst(); // rimuovo il primo elemento a fiorante
                                pila.removeFirst(); // rimuovo il secondo elemento a fiorante
                                pila.removeFirst(); // rimuovo il terzo elemento a fiorante

                                pila.add(0, F22);

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
                            else if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == Z0){
                        /* IMPORTANTE questo stato porta ad uno stato finale */
                                pila.removeFirst();
                                pila.removeFirst();

                                if(debug)
                                    StampaPila(pila);

                                currentState = Q6;

                                // mi trovo il root
                                index = queue.size() -3;

                                // ottengo l0espressione root
                                currentFormula = queue.get(index);

                                auxExp = (Expression) currentFormula;

                                logger.info("Passo numero " + queue.size());
                                // setto le formule

                                auxExp.setFormulaOne(queue.get(index +1));
                                auxExp.setFormulaTwo(queue.get(index +2));

                                // elimino le formule settate elementari assegnate

                                queue.removeLast();
                                queue.removeLast();

                                result = queue.getFirst();

                                if(currentState == Q6){
                                    logger.info("Passo numero " + count);
                                    logger.info("Mi trovo nello stato finale dell'automa");
                                    logger.info("Riconoscimento Completato");
                                }

                            }

                        }
                        break;

                    case Q5:
                        count++;
                        logger.info("Passo numero " + count);

                        logger.info("mi trovo nello stato 5 del'automa");

                        if((aux.contains(Expression.OR_STRING) ||
                                aux.contains(Expression.AND_STRING) ||
                                aux.contains(Expression.IMPLICATION_STRING))){

                            // controllo la pila

                            if(pila.get(0) == F11 && pila.get(1) == OP){
                                // mi trovo nello stato 5 del'automa
                                // aggiungo i dati in pila

                                logger.info("mi trovo nello stato 3 del'automa");

                                pila.removeFirst(); // rimuovo il primo elemento a fiorante
                                pila.removeFirst(); // rimuovo il secondo elemento a fiorante

                                pila.add(0, OP1);

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
                        } else if(aux.contains(")")){

                            if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == F2){

                                pila.removeFirst();
                                pila.removeFirst();
                                pila.removeFirst();

                                pila.add(F22);

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

                            }else if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == F11){

                                pila.removeFirst();
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

                            }else if(pila.get(0) == F11 && pila.get(1) == E && pila.get(2) == F11){

                                pila.removeFirst();
                                pila.removeFirst();

                                if(debug)
                                    StampaPila(pila);

                                currentState = Q5;

                            }else if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == F22){

                                pila.removeFirst();
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

                            } else if(pila.get(0) == F11 && pila.get(1) == E && pila.get(2) == F22){

                                pila.removeFirst();
                                pila.removeFirst();
                                pila.removeFirst();
                                pila.add(F22);

                                if(debug)
                                    StampaPila(pila);

                                currentState = Q5;
                            }
                            else if(pila.get(0) == F22 && pila.get(1) == E && pila.get(2) == Z0){

                                pila.removeFirst();
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
                                    logger.info("Passo numero " + count);
                                    logger.info("Mi trovo nello stato finale dell'automa");
                                    logger.info("Riconoscimento Completato");
                                }
                            }
                        }
                        break;

                    case Q6:
                        count++;
                        logger.info("Passo numero " + count);
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

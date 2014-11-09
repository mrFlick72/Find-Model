/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.control;

import it.valeriovadui.findmodel.model.ElementaryFormula;
import it.valeriovadui.findmodel.model.Expression;
import it.valeriovadui.findmodel.model.Formula;

import java.util.*;

/**
 *
 * @author mrFlick72
 */

public class OperatorControl {
    private OperatorControl(){}
    
    public static List<Map<String,Boolean>> createAssignation(String[] variables){
        Map<String,Boolean>  aux;
        List<Map<String,Boolean> > result = new ArrayList<Map<String,Boolean> >();
        
        int i;
        int j;
        
        boolean[][] assegnition = createAssignationToArray(variables);
        
        // compilo le interpretazioni
        for(i = 0; i < assegnition.length ; i++){
            aux = new HashMap<>();
               // assegno tutti i valori possibili alle variabili
            for(j = 0; j < assegnition[0].length ; j++){
                 // se non edvo cambiare il valoere metto il deafault e quindi true
                 aux.put(variables[j], assegnition[i][j]);
            }
             result.add(aux);
        }
        return result;
    }
    
    public static boolean[][] createAssignationToArray(String[] variables){
        
        int i;
        int j;
        int row = (int) Math.pow(2, variables.length);
  
        // indica quanti true e quant false nella colonna ciu devono essere
        int next = 1;
        int trueAndFalse = 0;
        // mi dice quando ho finito di mettere i true della colonna
        boolean changeValue= false;
        
        boolean[][] assegnition = new boolean[row][variables.length];
        
        // per tutte le possibili assegnazioni
        for(j = 0; j < assegnition[0].length ; j++){

               // assegno tutti i valori possibili alle variabili
             for(i = 0; i < assegnition.length ; i++){
                 // se non edvo cambiare il valoere metto il deafault e quindi true
                 if(!changeValue){
                     assegnition[i][j] = true;
                     
                     trueAndFalse++;
                     if(trueAndFalse == next){
                         changeValue =  true;
                     }
                 } else{
                     // altrimenti metto i false
                     assegnition[i][j] = false;
                     
                     trueAndFalse++;
                     if(trueAndFalse == 2*next){
                         changeValue =  false;
                         trueAndFalse = 0;
                     }
                 }
             }
             next *=2;
        }
    
        return assegnition;
    }
    
    public static boolean execute(Formula f, Map<String,Boolean>  interpretation){
        boolean result = false;

        if(f!= null){
            if(f instanceof Expression){
                
                Expression e = (Expression) f;
                
                int operation = e.getOperatorType();
                
            switch(operation){
              case Expression.OR:
                  
                  if(e.isNegation()){
                      result = !(execute(e.getFormulaOne(),interpretation) || execute(e.getFormulaTwo(),interpretation));
                  } else{
                      result = execute(e.getFormulaOne(),interpretation) || execute(e.getFormulaTwo(),interpretation);
                  }
                  
                break;
              case Expression.AND:
                   if(e.isNegation()){
                      result = !(execute(e.getFormulaOne(),interpretation) && execute(e.getFormulaTwo(),interpretation));
                  } else{
                      result = execute(e.getFormulaOne(),interpretation) && execute(e.getFormulaTwo(),interpretation);
                  }

                break;
              case Expression.IMPLICATION:
                  if(e.isNegation()){
                      result = !(!execute(e.getFormulaOne(),interpretation) ||execute(e.getFormulaTwo(),interpretation));
                  } else{
                      result = !execute(e.getFormulaOne(),interpretation) ||execute(e.getFormulaTwo(),interpretation);
                  }
                break;
           }
                
            }
            else{
                ElementaryFormula ef = (ElementaryFormula) f;
                
                if(ef.isNegation())
                   result = !interpretation.get(ef.getVariable());
                else
                  result = interpretation.get(ef.getVariable());  
            }

        }
        
        return  result;
    }

    public static List<Map<String,Boolean>> checkModel(Formula f, String[] variables){
        List<Map<String,Boolean>>  result = new ArrayList<>();

        List<Map<String,Boolean> > interpretations = createAssignation(variables);
        Map<String,Boolean>  interpretation;
        Iterator<Map<String,Boolean> > iterator = interpretations.iterator();
        
        while(iterator.hasNext()){
            interpretation = iterator.next();
            
            if(execute(f, interpretation)){
                result.add(interpretation);

            }
        }
        return result;
    }

    public static String printCheckModel(Formula f, String[] variables){
    
    List<Map<String,Boolean>>  aux = checkModel(f, variables);
    String result = "";
    
        Map<String,Boolean>  interpretation;
        Iterator<Map<String,Boolean> > iterator = aux.iterator();
       
        while(iterator.hasNext()){
            interpretation = iterator.next();
            result += interpretation.toString() + "\n";
        }
    return result;
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mrFlick72
 */
public class ModelTable extends JTable{
    private List<Map<String,Boolean>> interpretations;
    private String[] variables;

    public ModelTable(List<Map<String,Boolean>> interpretations,String[] variables){
        super();
        
        this.interpretations= interpretations;
        this.variables = variables;
        setModel(new DefaultTableModel(InitTable(), variables));
    }
    
    private Object[][] InitTable(){
        Object[][] result = new Object[interpretations.size()][variables.length];
        int i = 0;

        Iterator<Map<String,Boolean>> iteratore = interpretations.iterator();
        Map<String,Boolean> auxMap;
        String auxVariable;
        
        // per ogni interpretazione
        while(iteratore.hasNext()){
            auxMap = iteratore.next();
            
            // for all the variables to find its Boolean value and insert it in the table
            for (int j = 0 ; j < variables.length ; j++){
                if(auxMap.get(variables[j])){
                    auxVariable = "TRUE";
                } else{
                    auxVariable = "FALSE";
                }
                result[i][j] = auxVariable;
            }
            i++;
        }
        return result;
    }
}

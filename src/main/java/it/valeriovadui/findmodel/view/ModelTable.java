/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valerio
 */
public class ModelTable extends JTable{
    
    private List<HashMap<String,Boolean>> interpretations;
    private String[] variables;
    public ModelTable(){
          super();
        
    }
    public ModelTable(List<HashMap<String,Boolean>> interpretations,String[] variables){
        super();
        
        this.interpretations= interpretations;
        this.variables = variables;
        setModel(new DefaultTableModel(InitTable(), variables));
    }
    
    private Object[][] InitTable(){
        Object[][] result = new Object[interpretations.size()][variables.length];
        int i = 0;
        // mi occupo del resto della tabella
        Iterator<HashMap<String,Boolean>> iteratore = interpretations.iterator();
        Map<String,Boolean> auxMap;
        String auxVariable;
        
        // per ogni interpretazione
        while(iteratore.hasNext()){
            auxMap = iteratore.next();
            
            // per ogni variabile trovo il suop valore booleano e lo inserisco in tabella
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
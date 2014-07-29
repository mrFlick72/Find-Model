/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valerio
 */
public abstract class Formula {
    
    private boolean netgation;

    public Formula(boolean netgation) {
        this.netgation = netgation;
    }

    public boolean isNetgation() {
        return netgation;
    }

    public void setNetgation(boolean netgation) {
        this.netgation = netgation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formula other = (Formula) obj;
        if (this.netgation != other.netgation) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.netgation ? 1 : 0);
        return hash;
    }
    public String[] getAllVariable(){
        String[] result = null;
        List<String> pull = new ArrayList<String>();
        
        findVariable(this,pull);

        result = pull.toArray(new String[pull.size()]);
        return result;
    }
    private List<String> findVariable(Formula f, List<String> pull){
        
        if(f instanceof ElementaryFormula){
            ElementaryFormula ef = (ElementaryFormula) f;
            if(!pull.contains(ef.getVariable()))
                 pull.add(ef.getVariable());
            
        }
        else{
            Expression e = (Expression) f;
            
            findVariable(e.getFormulaOne(),pull);
            findVariable(e.getFormulaTwo(), pull);

        }
        return pull;
    }
    
    @Override
    public String toString(){
        return this.toString();
    }
}

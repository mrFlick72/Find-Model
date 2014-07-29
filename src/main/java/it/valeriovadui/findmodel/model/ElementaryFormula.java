/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.model;

/**
 *
 * @author Valerio
 */
public class ElementaryFormula extends Formula{
    private String   variable;

    
    // costruttori
    public ElementaryFormula(boolean negation) {
        super(negation);
    }

    public ElementaryFormula(String variable,boolean negation) {
        super(negation);
        this.variable = variable;
    }
    

    // getter
    public boolean isNegation() {
        return isNetgation();
    }

    public String getVariable() {
        return variable;
    }
    
    // setter 

    public void setVariable(String variable) {
        this.variable = variable;
    }
    
    public void setNegation(boolean negation) {
        setNetgation(negation);
    }

    // equals - has code   

    @Override
    public boolean equals(Object obj) {
        
         super.equals(obj);
         
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElementaryFormula other = (ElementaryFormula) obj;
        if (this.variable != other.variable && (this.variable == null || !this.variable.equals(other.variable))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 29 *super.hashCode();
        hash = 29 * hash + (this.variable != null ? this.variable.hashCode() : 0);
        return hash;
    }
    
    @Override
     public String toString(){
        if(super.isNetgation())
            return "!" + variable;
        
        return variable;
    }
}

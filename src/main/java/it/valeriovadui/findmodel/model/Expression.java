/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.valeriovadui.findmodel.model;

/**
 *
 * @author mrFlick72
 */
public class Expression extends Formula{
    /* an expretion can be:
     * a Formula, 
     * an negation of a Formula,
     * an Formula operator Formula */
    
    public static final int OR = 0;
    public static final int AND = 1;
    public static final int IMPLICATION = 2;

    public static final String OR_STRING = "\u02C5";
    public static final String AND_STRING = "\u02C4";
    public static final String IMPLICATION_STRING = "=>";
    
    private Formula formulaOne;
    private Formula formulaTwo;
    
    
    private int operatorType;
    
    // costruttori
    public Expression(boolean negation) {
        super(negation);
        formulaOne =  null;
        formulaTwo =  null;
    }

    
    public Expression(Formula formulaOne, Formula formulaTwo, int operatorType,boolean negation) {
        super(negation);
        
        this.formulaOne = formulaOne;
        this.formulaTwo = formulaTwo;
        this.operatorType = operatorType;
    }
    
    // getter
    public Formula getFormulaOne() {
        return formulaOne;
    }

    public Formula getFormulaTwo() {
        return formulaTwo;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public static int getOperatorType(String operator) {
        
        int result = -1;
        
        if(operator.equals(AND_STRING))
            result = AND;
        else if(operator.equals(OR_STRING))
            result = OR;
        else if(operator.equals(IMPLICATION_STRING))
            result = IMPLICATION;
            
        return result;
        
    }
        
    public boolean isUnary(){
        return formulaTwo == null;
    }
     public boolean isNegation(){
         return isNetgation();
     }
    // setter
    public void setFormulaOne(Formula formulaOne) {
        this.formulaOne = formulaOne;
    }

    public void setFormulaTwo(Formula formulaTwo) {
        this.formulaTwo = formulaTwo;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }
    
    public void setUnary(){
        formulaTwo = null;
    }
    
    public void setNegation(boolean negation){
        setNetgation(negation);
    }
    
    // equal - has code
    @Override
    public boolean equals(Object obj) {
        
        super.equals(obj);
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Expression other = (Expression) obj;
  
        if (this.formulaOne != other.formulaOne && (this.formulaOne == null || !this.formulaOne.equals(other.formulaOne))) {
            return false;
        }
        if (this.formulaTwo != other.formulaTwo && (this.formulaTwo == null || !this.formulaTwo.equals(other.formulaTwo))) {
            return false;
        }
        if (this.operatorType != other.operatorType) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        
        int hash = 5;
        hash = 67 *super.hashCode();
        hash = 67 * hash + (this.formulaOne != null ? this.formulaOne.hashCode() : 0);
        hash = 67 * hash + (this.formulaTwo != null ? this.formulaTwo.hashCode() : 0);
        hash = 67 * hash + this.operatorType;
        return hash;
    }
    
   @Override
     public String toString(){
       String result = "";
       
            if(isNetgation())
                result += "!"; 
                   
            result +=  "( " + formulaOne.toString();
            switch(operatorType){
                case OR:
                    result += " " + OR_STRING + " ";
                break;
                    
                case AND:
                    result += " " + AND_STRING + " ";
                break;
                                       
                 case IMPLICATION:
                    result += " " + IMPLICATION_STRING + " ";
                break;
            }
            result += formulaTwo.toString() + " )" ;
        
        return result;
    }
}

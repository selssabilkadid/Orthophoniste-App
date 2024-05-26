package Classes;

import java.io.Serializable;

public class CompteRendu implements Serializable {
    private static final long serialVersionUID = 1L;
    private int scoretotal;
    private String conclusion;
    private Test test;
    public CompteRendu(int scoretotal, String conclusion, Test test){
           
    }
    public int getScoretotal(){
        return scoretotal;
    }
    public String getConclusion(){
        return conclusion;
    }
    public Test getTest(){
        return test;
    }
    public void setScoretotal(int scoretotal){
        this.scoretotal = scoretotal;
    }
    public void setConclusion(String conclusion){
        this.conclusion = conclusion;
    }
    public void setTest(Test test){
        this.test = test;
    }

}

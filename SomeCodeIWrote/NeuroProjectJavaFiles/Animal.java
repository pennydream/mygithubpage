package NeuroProjectJavaFiles;

public class Animal {
    public Trial[] rawSet;//this contains an array of Trial objects
    public int trialIterator=0,totalTrialNumber=0;
    public float GG=0,GNG=0,NGG=0,NGNG=0; //Number of different combination types in entire set
//For example: GNG is a No Go trial with a Go trial preceding it
    public float GR=0,GE=0,GO=0,NR=0,NE=0,NO=0;//Number of trial outcomes in entire set
    public float GC=0,GF=0,NC=0,NF=0;
    
    public float pGR=0,pGE=0,pGO=0,pNR=0,pNE=0,pNO=0;//ratio of number of trial outcome to total number of trials
    public float pGC=0,pGF=0,pNC=0,pNF=0;
    public double[] rxnTime;
            
            
    public Animal(){
	//max number of trials is 50, but it mostly ranges from 46 to 48
        rawSet= new Trial[50];
        rxnTime = new double[50];
    }
    
//This adds a trial to the animal. It is used in the Set class 
    public void addTrial(Trial A){
        if(trialIterator<50){
            rawSet[trialIterator]=A;
            trialIterator++;
        }else System.out.println("ERROR: SESSION FULL");
        totalTrialNumber=trialIterator-1;
    }
    
    public Trial getTrialAt(int i){
        return rawSet[i];
    }
    
//this sets the data on the previous trial in the Trial objects in this set, as mentioned in the Trial class 
    public void setPreviousData(){
        rawSet[0].GNGPrev=2;rawSet[0].OutPrev=' ';//no previous trial on first trial
        for(int i=1;i<trialIterator;i++){
            rawSet[i].GNGPrev=rawSet[i-1].GNG;
            rawSet[i].OutPrev=rawSet[i-1].Out;
        }
    }
   
//This prints information on this specific Animal 
    public void printSessionData(){
        int GO=0,NOGO=0;
        int R=0,E=0,O=0,F=0,C=0;
        for(int i=0;i<trialIterator;i++){
            if(rawSet[i].GNG==1){
                GO++;
                if(rawSet[i].Out=='r')R++;
                if(rawSet[i].Out=='e')E++;
                if(rawSet[i].Out=='o')O++;
            }
            if(rawSet[i].GNG==0){
                NOGO++;
                if(rawSet[i].Out=='c')C++;
                if(rawSet[i].Out=='f')F++;
            }
        }
        
        System.out.println("Of "+GO+" Go Trial(s):");
        System.out.println("Reward: "+R);
        System.out.println("Error: "+E);
        System.out.println("Omission: "+O);
        System.out.println("Of "+NOGO+" No-Go Trial(s):");
        System.out.println("Correct Rejections: "+C);
        System.out.println("False Alarms: "+F);
    }
    
//This sets data on preceding trial type in the Animal object
    public void setSessionPrevData(){
        for(int i=0;i<trialIterator;i++){
            //Subset of all GO trials
            if(rawSet[i].GNG==1){
                //subset of all GO-GO trials
                if(rawSet[i].GNGPrev==1){
                    GG++;    
                    if(rawSet[i].Out=='r')GR++;
                    if(rawSet[i].Out=='e')GE++;
                    if(rawSet[i].Out=='o')GO++;
                }
                //subset of all GO-NOGO trials
                if(rawSet[i].GNGPrev==0){
                    GNG++;    
                    if(rawSet[i].Out=='r')NR++;
                    if(rawSet[i].Out=='e')NE++;
                    if(rawSet[i].Out=='o')NO++;
                }
            }
            //subset of all NOGO trials
            if(rawSet[i].GNG==0){
                //subset of all GO-NOGO trials
                if(rawSet[i].GNGPrev==1){
                    NGG++;    
                    if(rawSet[i].Out=='c')GC++;
                    if(rawSet[i].Out=='f')GF++;
                }
                //subset of all NOGO-NOGO trials
                if(rawSet[i].GNGPrev==0){
                    NGNG++;    
                    if(rawSet[i].Out=='c')NC++;
                    if(rawSet[i].Out=='f')NF++;
                }
            }
        }
    }
    
//Prints info about Animal class broken into four groups based on trial type
    public void printSessionPrevStr(){
        System.out.println("Of "+GG+" Go-Go Trial(s):");
        System.out.println("Reward: "+GR);
        System.out.println("Error: "+GE);
        System.out.println("Omission: "+GO);
        System.out.println("Of "+GNG+" NoGo-Go Trial(s):");
        System.out.println("Reward: "+NR);
        System.out.println("Error: "+NE);
        System.out.println("Omission: "+NO);
        System.out.println("Of "+NGG+" Go-NoGo Trial(s):");
        System.out.println("Correct Rejections: "+GC);
        System.out.println("False Alarms: "+GF);
        System.out.println("Of "+NGNG+" NoGo-NoGo Trial(s):");
        System.out.println("Correct Rejections: "+NC);
        System.out.println("False Alarms: "+NF);
        
        System.out.println("TOTAL: "+totalTrialNumber);
    }
    
    public void printPrevData(){
        for(int i=1;i<trialIterator;i++){
            rawSet[i].printPrevData();
        }
    }
    
//This sets the ratio of trial outcome to number of trial type
    public void setProb(){
        pGR=GR/GG;
        pGE=GE/GG;
        pGO=GO/GG;
        pNR=NR/NGG;
        pNE=NE/NGG;
        pNO=NO/NGG;
        
        pGF=GF/GNG;
        pGC=GC/GNG;
        pNF=NF/NGNG;
        pNC=NC/NGNG;
        
    }
   
//This prints ratios of trial outcome to number of trial type 
    public void printProbability(){
        
        System.out.println("Probabilities: ");
        System.out.println("    P(R|G): "+pGR);
        System.out.println("    P(E|G): "+pGE);
        System.out.println("    P(O|G): "+pGO);
        System.out.println("    P(F|G): "+pGF);
        System.out.println("    P(C|G): "+pGC);
        System.out.println("    P(R|NG): "+pNR);
        System.out.println("    P(E|NG): "+pNE);
        System.out.println("    P(O|NG): "+pNO);
        System.out.println("    P(F|NG): "+pNF);
        System.out.println("    P(C|NG): "+pNC);
        //This tests to make sure math is correct:
        float shouldBeOne=((pGR+pGE+pGO)/GG+(pNR+pNE+pNO)/NGG+(pGC+pGF)/GNG+(pNC+pNF)/NGNG)/4;
        System.out.println("SHOULD BE ONE = "+shouldBeOne);
	//it is usually off because of algebra done using floats in java, 
	//which is not exact but appropriated when taking into account significant figures
    }
    
//This sets the array of Rxn Times
    public void setRxnTime(){
        for(int i=0;i<totalTrialNumber;i++){
            rxnTime[i]=rawSet[i].reactionTime;
        }
    }
    
//This sets all data by using previously coded methods
    public void setALL(){
        this.setPreviousData();
        this.setSessionPrevData();
        this.setProb();
        this.setRxnTime();
    }
}

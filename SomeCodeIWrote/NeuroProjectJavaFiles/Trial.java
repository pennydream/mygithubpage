package NeuroProjectJavaFiles;

public class Trial {
    public double[] rawData = new double[13];//contains cells from excel file
    public int GNG; //1 is GO trial, 0 is NO-GO trail
    public char Out; //r for reward, e for error, o for omission, f for false alarm, c for correct rejection
    public double reactionTime=0;
    public int GNGPrev; //1 if GO, 0 if NO-GO of PREVIOUS trial, 2 if no previous trial
    public char OutPrev; //output of PREVIOUS trial
    
    
    
    public Trial(double[] rD){
            rawData=rD;
            GNG=getGNG();
            Out=getOutput();
            reactionTime=getRXNTime();
            //Used for testing:
            //printRawData();
            //printStrData();
    }
    
    public void printRawData(){
        for(int i=0;i<13;i++){
            System.out.print(rawData[i]+" ");
        }System.out.println();
    }
    
    public double getDataAt(int i){
        return rawData[i];
    }
    
    
    //get/set if Go or No Go trial
    public int getGNG(){
        if(rawData[4]==1||rawData[5]==1||rawData[6]==1)
            return 1;
        else return 0;
    }
   
//return type of mouse's response to Go or No Go trial 
    public char getOutput(){
        if(GNG==1){
            if(rawData[4]==1)return 'r';
            if(rawData[5]==1)return 'e';
            if(rawData[6]==1)return 'o';
        }else {
            if(rawData[9]==1)return 'f';
            if(rawData[10]==1)return 'c';
        }
        return ' ';
    }
    
    
    //print out information for laymen to understand
    public void printStrData(){
        if(GNG==1){
            System.out.print("GO Trial: ");
            if(Out=='r')System.out.print("Reward");
            if(Out=='e')System.out.print("Error");
            if(Out=='o')System.out.print("Omission");
        }
        if(GNG==0){
            System.out.print("NO GO Trial: ");
            if(Out=='f')System.out.print("False Alarm");
            if(Out=='c')System.out.print("Correct Rejection");
        }
        if(reactionTime!=0){
            System.out.println("");
            System.out.print("  Reaction Time: "+reactionTime);
        }
        System.out.println("");
    }

//get/set reaction time of mouse to grial from excel file
    public double getRXNTime(){
        if(rawData[7]!=0){
            return (rawData[7]/100);
        }
        if(rawData[8]!=0){
            return rawData[8]/100;
        }else return 0;
    }
    
//GNG and OutPrev is set in Animal file, and this method is used from there
    public void printPrevData(){
        if(GNGPrev==1){
            System.out.print("GO Trial: ");
            if(OutPrev=='r')System.out.print("Reward");
            if(OutPrev=='e')System.out.print("Error");
            if(OutPrev=='o')System.out.print("Omission");
        }
        if(GNGPrev==0){
            System.out.print("NO GO Trial: ");
            if(OutPrev=='f')System.out.print("False Alarm");
            if(OutPrev=='c')System.out.print("Correct Rejection");
        }
        System.out.println("");
    }
}



package NeuroProjectJavaFiles;

//I use an Apache library to access the excel files, which is in .jar file inside file
//This file must be included when compiling and running this program
//Look at README for details
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Set {
    
    //name of files containing a set of Go-No Go trials and corrisponding names of animals
    public String[] fileNames;
    public Animal[] set;
    public double[][] rxnTime;
    
    //Number of animals in set
    public int size;
    
    //iterators for adding files and animals to set
    public int itAn;
    public int itFile;
    
    //Number of Trials in set
    public int trialNumber=0;
    
    //Trial Type Amounts
    public double GG=0,GNG=0,NGG=0,NGNG=0;
            
    //Trial Outcome Amounts
    public double GR=0,GE=0,GO=0,NR=0,NE=0,NO=0,GC=0,GF=0,NC=0,NF=0;
    
    //ratio of Trial outcome to trial type
    public double pGR=0,pGE=0,pGO=0,pNR=0,pNE=0,pNO=0,pGC=0,pGF=0,pNC=0,pNF=0;
    
    
    //set size when instant created
    public Set(int i){
        size = i;
        set = new Animal[size];
        fileNames = new String[size];
        itAn=0;
        itFile=0;
        rxnTime = new double[size][];
    }
    
    //add animal to set up to set size
    public void addAnimal(Animal T){
        if(itAn>=size){
            System.out.println("ERROR: SET FULL");
            return;
        }
        set[itAn]=T;
        itAn++;
    }
    
    //add file names to set up to size
    public void addFile(String T){
        if(itFile>size){
            System.out.println("ERROR: SET FULL");
            return;
        }
        fileNames[itFile]=T;
        itFile++;
    }
    
    
    //set animals using file names *****add ALL file names in set before getting animals********
    public void getSet() throws FileNotFoundException, IOException{
        for(int i=0;i<size;i++){
            File testFile = new File(fileNames[i]);
            FileInputStream fis=new FileInputStream(testFile);
            HSSFWorkbook mwb=new HSSFWorkbook (fis);
            HSSFSheet mySheet = mwb.getSheetAt(0);
            Iterator<Row> rowIterator=mySheet.iterator();
            for(int k=0;k<3;k++) rowIterator.next();
            Animal current=new Animal();
            Trial tempT;
            double[] temp = new double[15];
            int rawIt=0,rawTrialIt=0,CellIt=0;
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    CellIt++;
                    switch(cell.getCellType()){
                        case Cell.CELL_TYPE_STRING:
                            //System.out.println(cell.getStringCellValue()+", ");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.println(cell.getNumericCellValue()+", ");
                            temp[rawIt]=cell.getNumericCellValue();
                            rawIt++;
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //System.out.println(cell.getBooleanCellValue()+", ");
                            break;
                        default:

                        }
                }
                tempT=new Trial(temp);
                current.addTrial(tempT);
                rawTrialIt++;
                rawIt=0;
            }
            current.setALL();
            //first.printSessionData();
            //first.printPrevData();
            //current.printSessionPrevStr();
            this.addAnimal(current);
        }
    }
   
//Sets number of trials in Set 
    public void setTrialNumber(){
        int temp=0;
        for(int i=0;i<size;i++){
            temp+=set[i].totalTrialNumber;
        }
        trialNumber=temp;
    }
    
//sets number of each trial type in Set
    public void setTrialType(){
        int tGG=0,tGNG=0,tNGG=0,tNGNG=0;
        for(int i=0;i<size;i++){
            tGG+=set[i].GG;
            tGNG+=set[i].GNG;
            tNGNG+=set[i].NGNG;
            tNGG+=set[i].NGG;
        }
        GG=tGG;
        GNG=tGNG;
        NGNG=tNGNG;
        NGG=tNGG;
    }
    
//prints info about entire set
    public void printSet(){
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
        
        System.out.println("TOTAL NUMBER OF TRIALS: "+trialNumber);
    }
    
//sets type of preceding trial
    public void setPrev(){
        float tGR=0,tGE=0,tGO=0,tNR=0,tNE=0,tNO=0;
        float tGC=0,tGF=0,tNC=0,tNF=0;
        for(int i=0;i<size;i++){        
            tGR+=set[i].GR;
            tGE+=set[i].GE;
            tGO+=set[i].GO;
            tNR+=set[i].NR;
            tNE+=set[i].NE;
            tNO+=set[i].NO;
            tGF+=set[i].GF;
            tGC+=set[i].GC;
            tNF+=set[i].NF;
            tNC+=set[i].NC;
        }
        
        GR=tGR;
        GE=tGE;
        GO=tGO;
        NR=tNR;
        NE=tNE;
        NO=tNO;
        GF=tGF;
        GC=tGC;
        NF=tNF;
        NC=tNC;
    }
    
//Sets ratio of trial outcome to total number of trials
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
    
//print out ratio of trial outcome to total number of trial type in baysian probability format
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
        
        
        //all possibilities come from 4 trial types with 
        double shouldBeOne=((pGR+pGE+pGO)+(pNR+pNE+pNO)+(pGC+pGF)+(pNC+pNF))/4;
        System.out.println("SHOULD BE ONE = "+shouldBeOne);
    }
    
//sets reaction time for each Animal in each Set
    public void setRXNTime(){
        for(int i =0;i<size;i++){
            rxnTime[i]= set[i].rxnTime;
        }
    }
    
//get reaction time for a specific Trial in a Animal object
    public double getRXNTimeSpef(int x,int y){
        return rxnTime[x][y];
    }
    
    public void setALL() throws IOException{
        this.getSet();
        this.setTrialNumber();
        this.setTrialType();
        this.setPrev();
        this.setProb();
        this.setRXNTime();
    }
    
//makes a single array containing information about all ratios, used in Main class
    public double[] getProb(){
        double[] allProb = new double[10];
        
        allProb[0]=pGR;
        allProb[1]=pGE;
        allProb[2]=pGO;
        allProb[3]=pGF;
        allProb[4]=pGC;
        allProb[5]=pNR;
        allProb[6]=pNE;
        allProb[7]=pNO;
        allProb[8]=pNF;
        allProb[9]=pNC;

        return allProb;
    }
}

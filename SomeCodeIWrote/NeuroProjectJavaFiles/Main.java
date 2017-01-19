package NeuroProjectJavaFiles;

import java.lang.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Iterator;

public class Main {
    public String[] fileNames;//contains names of excel files with data
    public double[] oldP, nwP;//contains ratios of all trial types to their own number of trial types, using method in Set class
    public Main() throws IOException, FileNotFoundException{
	//make Sets and set size
        Set old = new Set(7);
        Set nw = new Set(9);
        
	//add files to each set for each animal
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG10.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG120.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG124.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG126.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG140526.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG140617.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG140629.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG140802.xls");
        nw.addFile("NeuroProjectJavaFiles/NEW/GNG141009.xls");

        old.addFile("NeuroProjectJavaFiles/OLD/GNG140718.xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG140719.xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG140723.xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG140726.xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG140726(2).xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG17.xls");
        old.addFile("NeuroProjectJavaFiles/OLD/GNG25.xls");
        
	//set all info about each Set, as shown below
        old.setALL();
        nw.setALL();

//print ratios of trial outcomes to total trial types for both groups, aged and young
        old.printProbability();
        nw.printProbability();
        
        double[] dif=new double[10];
        nwP=nw.getProb();
        oldP=old.getProb();
        
//Print out info on differences in ratio, used to test program	
//        System.out.println();
//        System.out.println("DIFFERENCE (new minus old): ");
//        for(int i=0;i<10;i++){
//            dif[i]=abs(nwP[i]-oldP[i]);
//        }
/*        
        System.out.println("    P(R|G): "+dif[0]);
        System.out.println("    P(E|G): "+dif[1]);
        System.out.println("    P(O|G): "+dif[2]);
        System.out.println("    P(F|G): "+dif[3]);
        System.out.println("    P(C|G): "+dif[4]);
        System.out.println("    P(R|NG): "+dif[5]);
        System.out.println("    P(E|NG): "+dif[6]);
        System.out.println("    P(O|NG): "+dif[7]);
        System.out.println("    P(F|NG): "+dif[8]);
        System.out.println("    P(C|NG): "+dif[9]);
*/        
       
//This next large section creates the data and writes the .csv files which contain the reaction time for each animal in both Old and Young animals
        double tempo;//temporary value
        int currento;//current value
        int prevo;//previous value
	int[] GGi,GNGi,NGNGi,NGGi;
//iterators for each trial type
	GGi=new int[old.size];
	GNGi=new int[old.size];
	NGNGi=new int[old.size];
	NGGi=new int[old.size];
	double[][] rxnGNGo,rxnNGNGo, rxnGGo, rxnNGGo;//arrays containing reaction times of all animals in each set
	rxnNGNGo=new double[old.size][48];
	rxnGNGo=new double[old.size][48];
	rxnGGo=new double[old.size][48];
	rxnNGGo=new double[old.size][48];
	int[] totRGNGo, totRNGNGo, totRGGo, totRNGGo;//arrays containing total number of trials of all animals in each set
	totRGNGo=new int[old.size];
	totRNGNGo=new int[old.size];
	totRGGo=new int[old.size];
	totRNGGo=new int[old.size];
	double[] meanRGNGo, meanRNGNGo, meanRGGo, meanRNGGo;//mean of reaction time for each Animal  
        meanRGNGo=new double[old.size];
	meanRNGNGo=new double[old.size];
	meanRGGo=new double[old.size];
	meanRNGGo=new double[old.size];
	//iterate through each animal
	for(int i=0;i<old.size;i++){
		//initialize all variables specific to each animal to zero
            	GNGi[i]=0;
                NGGi[i]=0;
		GGi[i]=0;
                NGNGi[i]=0;
		//iterate through each trial
	    for(int j=1;j<old.set[i].totalTrialNumber;j++){
		tempo = old.getRXNTimeSpef(i,j);
                currento = old.set[i].rawSet[j].GNG;
                prevo = old.set[i].rawSet[j].GNGPrev;
		//only enter if this trial has a reaction time
                if(tempo!=0){
			//GO-GO trial type
                    if(prevo==1&&currento==1){
			totRGGo[i]++;
			meanRGGo[i]+=tempo;
			rxnGGo[i][GGi[i]]=tempo;
			GGi[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: GO-GO"+" Reaction Time: "+tempo);
		}
			//Go-NoGo trial type
              	    if(prevo==1&&currento==0){
			totRGNGo[i]++;
			meanRGNGo[i]+=tempo;
			rxnGNGo[i][GNGi[i]]=tempo;
			GNGi[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: GO-NOGO"+" Reaction Time: "+tempo);
		}
			//No-go No-Go trial type
                    if(prevo==0&&currento==0){
			totRNGNGo[i]++;
			meanRNGNGo[i]+=tempo;
			rxnNGNGo[i][NGNGi[i]]=tempo;
			NGNGi[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: NOGO-NOGO"+" Reaction Time: "+tempo);
		}
			//No-Go -- Go trial type
                    if(prevo==0&&currento==1){
			totRNGGo[i]++;
			meanRNGGo[i]+=tempo;
			rxnNGGo[i][NGGi[i]]=tempo;
			NGGi[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: NOGO-GO"+" Reaction Time: "+tempo);
		}
                }		
            }
        }

	for(int i=0;i<old.size;i++){	
		System.out.println("Number of Trials with rxn times: "+totRGGo[i]+" "+totRGNGo[i]+" "+totRNGGo[i]+" "+totRNGNGo[i]);
		System.out.println("Number of Trials with rxn times: "+meanRGGo[i]+" "+meanRGNGo[i]+" "+meanRNGGo[i]+" "+meanRNGNGo[i]);
		if(totRGGo[i]!=0)meanRGGo[i]=meanRGGo[i]/totRGGo[i];
		if(totRGNGo[i]!=0)meanRGNGo[i]=meanRGNGo[i]/totRGNGo[i];
		if(totRNGNGo[i]!=0)meanRNGNGo[i]=meanRNGNGo[i]/totRNGNGo[i];
		if(totRNGGo[i]!=0)meanRNGGo[i]=meanRNGGo[i]/totRNGGo[i];
	}
	
	float histGNGo=0, histNGGo=0, histNGNGo=0, histGGo=0;
	
	for(int i=0;i<old.size;i++){
		histGGo+=meanRGGo[i];
		histGNGo+=meanRGNGo[i];
		histNGGo+=meanRNGGo[i];
		histNGNGo+=meanRNGNGo[i];
	}
	
	histGGo/=old.size;
	histGNGo/=old.size;
	histNGGo/=old.size;
	histNGNGo/=old.size;

	System.out.println();
	System.out.println("Old: \n");
	String OutOld = new String("");
	OutOld+= "ID, Previous Trial, Trial, Age, Reaction Time\n";
	System.out.print("ID, GG, GNG, NGG, NGNG\n");
	for(int i=0;i<old.size;i++){
		OutOld+=i+", G, G, Old, "+meanRGGo[i]+"\n";
		System.out.print(i+", G, G, Old, "+meanRGGo[i]+"\n");
	}for(int i=0;i<old.size;i++){
		OutOld+=i+", G, NG, Old, "+meanRGNGo[i]+"\n";
		System.out.print(i+", G, NG, Old, "+meanRGNGo[i]+"\n");
	}for(int i=0;i<old.size;i++){
		OutOld+=i+", NG, G, Old, "+meanRNGGo[i]+"\n";
		System.out.print(i+", NG, G, Old, "+meanRNGGo[i]+"\n");
	}for(int i=0;i<old.size;i++){
		OutOld+=i+", NG, NG, Old, "+meanRNGNGo[i]+"\n";
		System.out.print(i+", NG, NG, Old, "+meanRNGNGo[i]+"\n");
	}

	System.out.println();

	System.out.println("Animal Mu: "+histGGo+" "+histGNGo+" "+histNGGo+" "+histNGNGo);

	System.out.println("Order: GG GNG NGG NGNG");



	double tempy;
        int currenty;
        int prevy;
	int[] GGiy,GNGiy,NGNGiy,NGGiy;
	GGiy=new int[nw.size];
	GNGiy=new int[nw.size];
	NGNGiy=new int[nw.size];
	NGGiy=new int[nw.size];
	double[][] rxnGNGy,rxnNGNGy, rxnGGy, rxnNGGy;
	rxnNGNGy=new double[nw.size][48];
	rxnGNGy=new double[nw.size][48];
	rxnGGy=new double[nw.size][48];
	rxnNGGy=new double[nw.size][48];
	int[] totRGNGy, totRNGNGy, totRGGy, totRNGGy;
	totRGNGy=new int[nw.size];
	totRNGNGy=new int[nw.size];
	totRGGy=new int[nw.size];
	totRNGGy=new int[nw.size];
	double[] meanRGNGy, meanRNGNGy, meanRGGy, meanRNGGy;
        meanRGNGy=new double[nw.size];
	meanRNGNGy=new double[nw.size];
	meanRGGy=new double[nw.size];
	meanRNGGy=new double[nw.size];
	for(int i=0;i<nw.size;i++){
                GNGiy[i]=0;
                NGGiy[i]=0;
		GGiy[i]=0;	
                NGNGiy[i]=0;
            for(int j=0;j<nw.set[i].totalTrialNumber;j++){
		tempy = nw.getRXNTimeSpef(i,j);
                currenty = nw.set[i].rawSet[j].GNG;
                prevy = nw.set[i].rawSet[j].GNGPrev;
                if(tempy!=0){
                    if(prevy==1&&currenty==1)if(tempy!=0){
			totRGGy[i]++;
			meanRGGy[i]+=tempy;
			rxnGGy[i][GGiy[i]]=tempy;
			GGiy[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: GO-GO"+" Reaction Time: "+tempo);
		}
              	    if(prevy==1&&currenty==0){
			totRGNGy[i]++;
			meanRGNGy[i]+=tempy;
			rxnGNGy[i][GNGiy[i]]=tempy;
			GNGiy[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: GO-NOGO"+" Reaction Time: "+tempo);
		}
                    if(prevy==0&&currenty==0){
			totRNGNGy[i]++;
			meanRNGNGy[i]+=tempy;
			rxnNGNGy[i][NGNGiy[i]]=tempy;
			NGNGiy[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: NOGO-NOGO"+" Reaction Time: "+tempo);
		}
                    if(prevy==0&&currenty==1){
			totRNGGy[i]++;
			meanRNGGy[i]+=tempy;
			rxnNGGy[i][NGGiy[i]]=tempy;
			NGGiy[i]++;
//			System.out.println("Animal: "+(i+1)+" Trial: "+j+" "+ "Type: NOGO-GO"+" Reaction Time: "+tempo);
		}
                }
            }
        }

	for(int i=0;i<nw.size;i++){	
		if(totRGGy[i]!=0)meanRGGy[i]=meanRGGy[i]/totRGGy[i];
		if(totRGNGy[i]!=0)meanRGNGy[i]=meanRGNGy[i]/totRGNGy[i];
		if(totRNGNGy[i]!=0)meanRNGNGy[i]=meanRNGNGy[i]/totRNGNGy[i];
		if(totRNGGy[i]!=0)meanRNGGy[i]=meanRNGGy[i]/totRNGGy[i];
	}

	float histGNGy=0, histNGGy=0, histNGNGy=0, histGGy=0;
	
	for(int i=0;i<nw.size;i++){
		histGGy+=meanRGGy[i];
		histGNGy+=meanRGNGy[i];
		histNGGy+=meanRNGGy[i];
		histNGNGy+=meanRNGNGy[i];
	}
	
	histGGy/=nw.size;
	histGNGy/=nw.size;
	histNGGy/=nw.size;
	histNGNGy/=nw.size;

	System.out.println();
	System.out.println("Young:\n");
	for(int i=0;i<nw.size;i++){
		OutOld+=(i+old.size)+", G, G, Young, "+meanRGGy[i]+"\n";
		System.out.print(i+", G, G, Young, "+meanRGGy[i]+"\n");
	}for(int i=0;i<nw.size;i++){
		OutOld+=(i+old.size)+", G, NG, Young, "+meanRGNGy[i]+"\n";
		System.out.print(i+", G, NG, Young, "+meanRGNGy[i]+"\n");
	}for(int i=0;i<nw.size;i++){
		OutOld+=(i+old.size)+", NG, G, Young, "+meanRNGGy[i]+"\n";
		System.out.print(i+", NG, G, Young, "+meanRNGGy[i]+"\n");
	}for(int i=0;i<nw.size;i++){
		OutOld+=(i+old.size)+", NG, NG, Young, "+meanRNGNGy[i]+"\n";
		System.out.print(i+", NG, NG, Young, "+meanRNGNGy[i]+"\n");
	}
	writeToFile(OutOld, "RxnTimeOutput.csv");
	

	System.out.print("ID, GG, GNG, NGG, NGNG\n");
	for(int i=0;i<nw.size;i++){
		System.out.print(i+", "+meanRGGy[i]+", "+meanRGNGy[i]+", "+meanRNGGy[i]+", "+meanRNGNGy[i]+"\n");
	}
	System.out.println();	
	System.out.println("Animal Mu: "+histGGy+" "+histGNGy+" "+histNGGy+" "+histNGNGy);
	System.out.println("ORDER : GG, GNG, NGG, NGNG");
}

//get Old and New array of ratios
	public double[] getOldP(){
		return oldP;
	}

	public double[] getNwP(){
		return nwP;
	}


//write a string s to a file named string x
	public void writeToFile(String s, String x){
		try{
		File file = new File(x);
		if(!file.exists()) file.createNewFile();
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(s);
		bw.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

}

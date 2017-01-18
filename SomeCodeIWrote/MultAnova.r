####This file contains a skeleton for running MANOVAs with Tukey's HSD tests
##Created by Brendan Garrett
##email: brendan.garrett312@gmail.com with any questions


#First, create a table: 
##The first column is the subject code (use anything you want here)
##Next, add as many columns as necessary for variables: var1, var2, ect
##Put the independant variable in the last column
##Labels can be changed for readablility, but make sure 
	##to also change them in the code below the table (it will be explained)
tableName <- read.table(header=TRUE, text = ' 
	subject	var1	var2	var3	IndVar
	0	G	G	Aged	0.74
	0	G	NG	Aged	0.59
	0	NG	G	Aged	1.56
	0	NG	NG	Aged	0.0
	1	G	G	Aged	0.89
	1	G	NG	Aged	0.4
	1	NG	G	Aged	1.82
	1	NG	NG	Aged	0.0	
	2	G	G	Aged	0.41
	2	G	NG	Aged	1.3
	2	NG	G	Aged	0.45
	2	NG	NG	Aged	0.5
	3	G	G	Aged	1.94
	3	G	NG	Aged	1.4
	3	NG	G	Aged	1.9
	3	NG	NG	Aged	0.0
	4	G	G	Aged	0.58
	4	G	NG	Aged	0.46
	4	NG	G	Aged	0.29
	4	NG	NG	Aged	0.1	
	5	G	G	Aged	1.2
	5	G	NG	Aged	2.25
	5	NG	G	Aged	1.42
	5	NG	NG	Aged	0.1
	6	G	G	Aged	1.1
	6	G	NG	Aged	0.84
	6	NG	G	Aged	0.81
	6	NG	NG	Aged	0.0

	7	G	G	Young	0.1
	7	G	NG	Young	0.3
	7	NG	G	Young	1.5
	7	NG	NG	Young	0.2	
	8	G	G	Young	1.1
	8	G	NG	Young	1.0
	8	NG	G	Young	0.4
	8	NG	NG	Young	0.8
	9	G	G	Young	0.2
	9	G	NG	Young	0.1
	9	NG	G	Young	0.999
	9	NG	NG	Young	0.34	
	10	G	G	Young	0.45
	10	G	NG	Young	0.663
	10	NG	G	Young	0.56
	10	NG	NG	Young	0.0
	11	G	G	Young	0.92
	11	G	NG	Young	1.8
	11	NG	G	Young	0.33
	11	NG	NG	Young	0.0	
	12	G	G	Young	0.83
	12	G	NG	Young	1.7
	12	NG	G	Young	0.83
	12	NG	NG	Young	0.0
	13	G	G	Young	0.94
	13	G	NG	Young	0.76
	13	NG	G	Young	0.44
	13	NG	NG	Young	0.3
')

#This line makes the subject variable a factor instead continuous
##e.g. 22 is not greater than 21, they are just different subjects
##This allows for any code you want to be used in the subject column
	##b/c it is not taken into account when running the stats tests
tableName$subject <- factor(tableName$subject)

#This runs a multivariable analysis of variance (MANOVA)
##The format for the function aov() is this:
##aov(PutIndependantVariableNameHere ~ var1*var2*var3, data=nameOfTable)
##Again, you can put as many variables as you want, just add them like *var4*var5...
##Make sure to change the name of the variable to correspond with the name of the column from the table above
thisAov <- aov(IndVar ~ var1*var2*var3, data=tableName)

#This prints out a basic summary of the MANOVA (with p-values)
print('SUMMARY: ')
summary(thisAov)

#This prints out tables of means related to the variables
print('MEANS TABLE: ')
model.tables(thisAov, "means")

#This runs Tukey's Honest Significant Difference test on the MANOVA
print('TUKEYS HSD TEST STARTS HERE: ')
TukeyHSD(thisAov)

		##How run this program::
##First, you must have R install on your computer 
##You can either run this through RStudio or through R on command line

##Using R from Command Line: 
	##use shell to navigate to directory with this file in it
	##Run This: R -f MultANOVA.r 
	##The results will print to the screen
	##To save the output run this: R -f MultANOVA.r > Output.txt
	##The results will be saved in a text file called Output.txt

##From RStudio: 
	##Click on menu -> File -> New File -> R Script
	##Then, paste this code into the new source code area
	##Click "Source" button above the code area

##Enjoy your statistical investigations!

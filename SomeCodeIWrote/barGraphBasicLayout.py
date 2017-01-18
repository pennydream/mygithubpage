##Basic Bar Graph Layout using python and matplotlib
#By Brendan Garrett 

#INSTRUCTIONS IN COMMENTS

#must have numpy and matplotlib libraries for it to work:
import matplotlib.mlab as mlab
import matplotlib.pyplot as plt
import numpy as np

#INPUT DATA MANUALLY
#This array will be height of bars in graph
#In order of desired bar graph layout:
aData=[2,2.2,2.4,2.3,1.9]
bData=[1.3,1.6,1.4,1.8,1.2]
cData=[1.1,0.8,0.9,0.3,0.5]

#Things: aData, bData, cData values are going to be hieghts of bars in the graph... 
	#There can be as many arrays as you want... like dData, eData, ect
	#There can be as many values in the Data arrays 
		#as long as each Data array has the same number of numbers in it!
	#Each value of aData[n] will be graphed next to bData[n], cData[n] ect...

#OR: INPUT DATA FROM A TXT FILE (skip next 17 lines if you are doing it manual)

##IMPORT TEXT FILE.... if you don't feel like just typing stuff in
#text_fileA = open("fileWithDataA.txt", "r");
#text_fileB = open("fileWithDataB.txt", "r");
#text_fileC = ect...

##split up text file into array of numbers split by character '\n'
##'\n' represents a 'new line' character. It can be substituded by commas, spaces, or whatever else the numbers are separated by...
#aStringData = text_fileA.read().split('\n');
#bStringData = text_fileB.read().split('\n');
#cStringData = ect...

#Convert string of text to actual number variables
#aData=np.array(aStringData, dtype=float).astype(np.float)
#bData=np.array(bStringData, dtype=float).astype(np.float)
#cData=ect...

#BACK TO MANUAL INPUT: 
#n_groups must equal the number of values in each Data array
n_groups = 5

#input standard error of the mean here, or whatever value you want the error bars to show
sem_a = (0.1,0.3,0.14,0.32,0.1)
sem_b = (0.11,0.25,0.09,0.45,0.15)
sem_c = (0.19,0.15,0.14,0.31,0.12)

#technical stuff
fig, ax = plt.subplots()
index = np.arange(n_groups)
bar_width = 0.25
opacity = 0.4
error_config = {'ecolor': '0.3'}

#this next bit creates the bars (rectangles) for each Data array from earlier...
#add rectsN for each nData array... 
#copy rectsB for all following rectsN... but change some things
	#add '+ bar_width' to index value for each new rectsN
	#set label to groupN or whatever the variable is
	#set color to something different
	#change value of yerr to [[0,0,0,0...], sem_n] 
		#this set error bar values
		#have as many zeros in the array as numbers in sem_n

rectsA = plt.bar(index, aData, bar_width,
                 alpha=opacity,
                 color='b',
                 yerr=[[0,0,0,0,0],sem_a],
                 error_kw=error_config,
                 label='groupA')

rectsB = plt.bar(index + bar_width, bData, bar_width,
                 alpha=opacity,
                 color='r',
                 yerr=[[0,0,0,0,0],sem_b],
                 error_kw=error_config,
                 label='groupB')
rectsC = plt.bar(index + 2*bar_width, cData, bar_width,
                 alpha=opacity,
                 color='g',
                 yerr=[[0,0,0,0,0],sem_c],
                 error_kw=error_config,
                 label='groupC')

#LABELING SECTION
#label x axis, y axis, title
plt.xlabel('Variables')
plt.ylabel('Mean of Groups')
plt.title('Title of Graph')

#label names of variables (to go under bar graph(s))
plt.xticks(index + bar_width, ('Var 1','Var 2','Var 3','Var 4','Var 5'))
plt.legend()
plt.tight_layout()

#This makes the graph pop up on the screen: 
plt.show()
#It can be saved from this window 

#Comment it out if you want and...
#It can be saved automatically from this program by entering using this code:
#plt.savefig("nameOfGraph.png")

#Any questions? Email me: brendan.garrett312@gmail.com

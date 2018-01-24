#import numpy as N 
import random
import pdb
RIGHT_WORKER=0
LEFT_WORKER=1
	
MAX_COMP=3
ASSEMBLE_TIME=4
	
COMP_EMPTY=0
COMP_A=1
COMP_B=2
COMP_FINAL=3
dict = {}
	
conveyorBelt=[COMP_EMPTY,COMP_EMPTY,COMP_EMPTY];
	
workers=[[COMP_EMPTY,COMP_EMPTY,COMP_EMPTY],[COMP_EMPTY,COMP_EMPTY,COMP_EMPTY]];
fullComp=0;
unCollected=[0,0];
assemblingWorkers = [];
#a = N.workers([[COMP_EMPTY,COMP_EMPTY,COMP_EMPTY],[COMP_EMPTY,COMP_EMPTY,COMP_EMPTY]])
print("Number of workers on both side ", len(workers[0]))

def displayWorker():
    for i in range(len(workers)):
        print('Worker',i,' : ')
        for j in range(len(workers[i])):
            print("worker", i ,"is having product ",workers[i][j])
            print("    ")
            
def pickCompFromBelt():
    for i in range(len(workers[0])):
         beltContent=conveyorBelt[i]
         if (beltContent !=COMP_EMPTY) and (beltContent < COMP_FINAL):
             n=random.randint(0,1)
             m = LEFT_WORKER if n==RIGHT_WORKER else RIGHT_WORKER;
             #pdb.set_trace()
             if workers[n][i]!=COMP_FINAL and (workers[n][i]==0 or workers[n][i]!=beltContent):
                 #pdb.set_trace()
                 workers[n][i]+=beltContent
                 #pdb.set_trace()
                 conveyorBelt[i]=COMP_EMPTY
             elif workers[m][i]!=COMP_FINAL and (workers[m][i] ==0 or workers[m][i] != beltContent):
                     workers[m][i]+=beltContent;
                     conveyorBelt[i]=COMP_EMPTY;
    displayWorker()

def displayBelt():
    print("Current Belt : ")
    for i in range (len(conveyorBelt)):
        print("comp on belt at index", i,"is",conveyorBelt[i], " ")
        print()


def putCompOnBelt(comp):
    print("New Comp received ", comp)
    beltLength=len(conveyorBelt);
    lastItemOnBelt=conveyorBelt[beltLength-1];
    if lastItemOnBelt !=COMP_EMPTY and  lastItemOnBelt < COMP_FINAL :
        unCollected[lastItemOnBelt-1]+1
        pdb.set_trace()
    elif lastItemOnBelt >= COMP_FINAL :
        fullComp+1
        for i in range(beltLength-1):
            conveyorBelt[i]=conveyorBelt[i-1]
            i-1
    conveyorBelt[0]=comp
    displayBelt()

def putBackFinalCompOnBelt(j):
    conveyorBelt[j]+= COMP_FINAL
    pdb.set_trace()
    displayBelt()

def updateAssemblers():
    for i in range (len(workers)):
        for j in range(len(workers[0])):
            if workers[i][j] == COMP_FINAL:
                key=i+j
                #pdb.set_trace()
                if key not in dict:
                    val=dict.get(key)
                    pdb.set_trace()
                    val-1
                    if(val!=0):
                        dict[key]=val
                    else:
                        putBackFinalCompOnBelt(j)
                        print ("Putt back final products on idx :", key)
                        del dict[key]
                        workers[i][j]=0
                else:
                    print ("Worker doing Assembling [Busy] are ",key)
                    dict[key]=ASSEMBLE_TIME
					

def main():
    #Run for 100 times 
    for i in range(1,100):
        r=random.randint(0,3)
        if r==COMP_EMPTY:
            putCompOnBelt(COMP_EMPTY)
            pickCompFromBelt()
        elif r==COMP_A:
            putCompOnBelt(COMP_A)
            pickCompFromBelt()
        elif r==COMP_B:
            putCompOnBelt(COMP_B)
            #pdb.set_trace()
            pickCompFromBelt()
        #updateAssemblers()
    print(" Total Assembled components is ", fullComp)
    for j in range(len(unCollected)):
        n=j+1
        print(" Un Collected components ",  n , " is " ,unCollected[j])
        
	      
main()


		

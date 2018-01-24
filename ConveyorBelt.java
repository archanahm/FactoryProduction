import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ConveyorBelt {

	static final int RIGHT_WORKER=0;
	static final int LEFT_WORKER=1;
	
	static final int MAX_COMP=3;
	static final int ASSEMBLE_TIME=4;
	
	static final int COMP_EMPTY=0;
	static final int COMP_A=1;
	static final int COMP_B=2;
	static final int COMP_FINAL=3;
	
	static int conveyorBelt[]={COMP_EMPTY,COMP_EMPTY,COMP_EMPTY};
	
	static int workers[][]={{COMP_EMPTY,COMP_EMPTY,COMP_EMPTY},{COMP_EMPTY,COMP_EMPTY,COMP_EMPTY}};
	static int fullComp=0;
	static int unCollected[]={0,0};
	
	static List<Integer> assemblingWorkers = new ArrayList();
	
	static Map<String, Integer> map = new HashMap();
	
	static Random random = new Random();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0;i<100;i++) {
			int r=getRandomNumber(MAX_COMP);
			switch(r){
			case COMP_EMPTY:
				//pickCompFromBelt();
				putCompOnBelt(COMP_EMPTY);
				pickCompFromBelt();
				break;
			case COMP_A:
				//pickCompFromBelt();
				putCompOnBelt(COMP_A);
				pickCompFromBelt();
				break;
			case COMP_B:
				//pickCompFromBelt();
				putCompOnBelt(COMP_B);
				pickCompFromBelt();
				break; 
				
				/**
				 * there may be chances of having finished product in belt
				 */
			}
			updateAssemblers();
			System.out.println("-------------------");
		}
		System.out.println(" Total Assembled components is "+fullComp);
		for(int j=0;j< unCollected.length;j++) {
			int n=j+1;
			System.out.println(" Un Collected components "+ n +" is "+unCollected[j]);
		}
	}
	
	private static void updateAssemblers() {
		
		for(int i=0;i<workers.length;i++){
			for(int j=0;j<workers[0].length;j++){
				if(workers[i][j] == COMP_FINAL){
					String key=i+"_"+j;
					if(map.containsKey(key)) {
						Integer val=map.get(key);
						val--;
						if(val!=0) {
							map.put(key, val);
						} else {
							putBackFinalCompOnBelt(j);
							System.out.println("Putt back final products on idx :"+key);
							map.remove(key);
							workers[i][j]=0;
						}
					} else {
						System.out.println("Worker doing Assembling [Busy] are "+key);
						map.put(key, ASSEMBLE_TIME);
					}
				}
			}
		}
		
	}

	private static void putBackFinalCompOnBelt(int j) {
		conveyorBelt[j]+= COMP_FINAL;
		displayBelt();
	}

	private static void putCompOnBelt(int comp) {
		System.out.println("New Comp received "+comp);
		int beltLength=conveyorBelt.length;
		int lastItemOnBelt=conveyorBelt[beltLength-1];
		if(lastItemOnBelt !=COMP_EMPTY && lastItemOnBelt < COMP_FINAL) {
			unCollected[lastItemOnBelt-1]++; //this should go to uncollected array only if its not picked by worked A and B both should be busy 
		} else if(lastItemOnBelt >= COMP_FINAL) {
			fullComp++;
		}
		for(int i=beltLength-1;i>0;i--){
			conveyorBelt[i]=conveyorBelt[i-1];
		}
		conveyorBelt[0]=comp;
		displayBelt();
	}
	
	private static void pickCompFromBelt() {
		for(int i=0;i<workers[0].length;i++){
			int beltContent=conveyorBelt[i];
			if(beltContent !=COMP_EMPTY && beltContent < COMP_FINAL){
				int n=getRandomNumber(2); // 2 workers on both side
				int m= n==RIGHT_WORKER?LEFT_WORKER:RIGHT_WORKER;
				
				// check if workers is not assembling
				if(workers[n][i]!=COMP_FINAL && (workers[n][i]==0 || workers[n][i]!=beltContent)){
					workers[n][i]+=beltContent;
					conveyorBelt[i]=COMP_EMPTY;
				} else if(workers[m][i]!=COMP_FINAL && (workers[m][i] ==0 || workers[m][i] != beltContent)) {
					workers[m][i]+=beltContent;
					conveyorBelt[i]=COMP_EMPTY;
				}
			}
		}
		displayWorker();
	}


	private static void displayBelt(){
		System.out.print("Current Belt : ");
		for(int i=0;i<conveyorBelt.length;i++){
			System.out.print(conveyorBelt[i]+ " ");
		}
		System.out.println();
	}
	private static void displayWorker(){
		for(int i=0;i<workers.length;i++){
			System.out.print("Worker "+i + " : ");
			for(int j=0;j<workers[i].length;j++){
				System.out.print(workers[i][j]+ " ");
			}
			System.out.print("    ");
		}
		System.out.println();
	}

	private static int getRandomNumber(int n) {
	return random.nextInt(n);
		
	}

}

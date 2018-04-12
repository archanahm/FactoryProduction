import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

import java.lang.*;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;

import junit.framework.Assert;

@RunWith(Parameterized.class)
public class CoveyorBeltCheckerTest {
   private Integer inputNumber;
   private Integer j;
   private Integer i;
   private Integer val;
   private Integer wlist;


   @Before
   public void initialize() {
      
   }
   
   @After
   public void tearDown() throws Exception {
       
   }
   

   // Each parameter should be placed as an argument here
   // Every time runner triggers, it will pass the arguments
   // from parameters we defined in methods
	
   public CoveyorBeltCheckerTest(Integer inputNumber, Integer j, Integer i,Integer val,Integer wlist) {
      this.inputNumber = inputNumber;
      this.j = j;
      this.i = i;
      this.val = val;
      this.wlist=wlist;
   }

   @Parameterized.Parameters
   public static Collection coveyorBeltNumber() {
      return Arrays.asList(new Object[][] {
         { 2, 0 ,0,0,1},
         { 3, 1 ,1,0,0},
         { 0, 1 ,2,0,1},
         { 3, 0 ,1,3,1},
         { 3, 1 ,2,0,1},
         { 0, 8 ,1,0,1}
      });
   }

   // This test will run 6 times 
 @Test
   public void testupdateAssemblers() {
	  System.out.println("============================================  SUIT RUN ============================================ ");
	  System.out.println(); 
	  System.out.println("Starting test-----------> " + new Object(){}.getClass().getEnclosingMethod().getName());
	   int k =0;
	   		ConveyorBelt.workers[k][j]= inputNumber;
	   		int beltlen = ConveyorBelt.workers[j].length;

		if(inputNumber==3){
		
			System.out.println("PRINTING i="+ i +"j=" +j );
			  String actual = ConveyorBelt.updateAssemblers();
			 String Expected = k+"_"+j;
			 System.out.println("Expected key where the belt is getting updated ============= "+ Expected);
			 System.out.println("actualkey updaetd in belt when comp reached to palce 3  ============= "+ actual);
			 Assert.assertEquals(actual, Expected);
			 
		
		}else{
			System.out.println("COVEYOR BELT IS NOT GETTING UPDATED");
			 ConveyorBelt.updateAssemblers();
		}
		System.out.println("Ending test ------------->" + new Object(){}.getClass().getEnclosingMethod().getName());
		 System.out.println();
   }
	@Test
	public void pickCompFromBeltTest(){
		 System.out.println();
		System.out.println("Starting test ------------> " + new Object(){}.getClass().getEnclosingMethod().getName());
		int[][] wrkValu=ConveyorBelt.displayWorker();
		if(inputNumber != 3){
			ConveyorBelt.conveyorBelt[0]= 2;
			// ConveyorBelt.workers[1][0] = 2;/
		//checking if the worker array is getting updated 
			ConveyorBelt.pickCompFromBelt();
				Assert.assertTrue(wrkValu.length == ConveyorBelt.displayWorker().length);
				if (wrkValu.length == ConveyorBelt.displayWorker().length){
				
					Assert.assertTrue(Arrays.equals(wrkValu[0],ConveyorBelt.displayWorker()[0]) || Arrays.equals(wrkValu[1],ConveyorBelt.displayWorker()[1]));
                    }
			
		
		}
		System.out.println("Ending test ------------> " + new Object(){}.getClass().getEnclosingMethod().getName());
		 System.out.println();
	}
   
   @Test
	public void testputCompOnBelt(){
	   System.out.println();
		System.out.println("Starting test --------> " + new Object(){}.getClass().getEnclosingMethod().getName());
		ConveyorBelt.conveyorBelt[j]=3;
		int fullCompvalue=ConveyorBelt.fullComp;
		
		
		if(i==2){
			System.out.println("uncollected array  value should get updated");
			 int[] uncollectedSize = ConveyorBelt.unCollected;
			ConveyorBelt.putCompOnBelt(i);
			Assert.assertTrue(uncollectedSize[1] == ConveyorBelt.unCollected[1]);
			Assert.assertFalse(uncollectedSize[0] != ConveyorBelt.unCollected[0] );
		
			
		}else if( val == 3) {
			System.out.println("Full Comp value should get updated");
			ConveyorBelt.putCompOnBelt(i);
			Assert.assertTrue((fullCompvalue)<ConveyorBelt.fullComp);
		}else{
		
			ConveyorBelt.putCompOnBelt(i);

		}
		
		System.out.println("Ending test ---------> " + new Object(){}.getClass().getEnclosingMethod().getName());
		 System.out.println();
		 System.out.println("============================================END  SUIT RUN ============================================ ");
	}

   /*@Test
   public void testMain(){
	   try {
		   
		   ConveyorBelt ConveyorBeltobj=Mockito.mock(ConveyorBelt.class);
		   final ConveyorBelt bloMock = Mockito.spy(new ConveyorBelt());
		   Mockito.when(bloMock.getRandomNumber(2)).thenReturn(1);
		   ConveyorBelt.MAX_COMP=1;
		   ConveyorBelt.main(new String[] {"a"});
	
		 } catch (Exception e) { }
   }*/


   

}
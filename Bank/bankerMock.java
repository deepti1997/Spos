import java.io.* ; 
import java.util.Scanner ;

public class bankerMock {

	//Class variables;
	int processNum , resourceNum;
	int alloc[][],need[][],max[][],avail[];

	//function for taking input (no.of processes,resources, alloc, max ans avail matrices
	public void takeInput()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of Processes :");
		processNum = sc.nextInt();

		System.out.println("Enter the number of Resources :");
		resourceNum = sc.nextInt();
		
		alloc = new int [processNum][resourceNum];
		System.out.println("Enter the Allocation Matrix");
		for(int i=0;i<processNum;i++)
			for(int j=0;j<resourceNum;j++)
				alloc[i][j] = sc.nextInt();

		max= new int [processNum][resourceNum];
		System.out.println("Enter the Max Matrix");
		for(int i=0;i<processNum;i++)
			for(int j=0;j<resourceNum;j++)
				max[i][j] = sc.nextInt();
		
		avail = new int [resourceNum];
		System.out.println("Enter the Available Matrix");
		for(int j=0;j<resourceNum;j++)
			avail[j] = sc.nextInt();
	}
	public void calcNeed()
	{
		//Calculation of need matrix ( Max - Allocation)
		need= new int [processNum][resourceNum];		
		for(int i=0;i<processNum;i++)
			for(int j=0;j<resourceNum;j++)
				need[i][j] = max[i][j] - alloc[i][j];
	}

	public void displayNeed()
	{
		System.out.println("\nThe need Matrix is as follows");
		for(int i=0;i<processNum;i++){
			for(int j=0;j<resourceNum;j++)
				System.out.print(need[i][j]+" ");
		System.out.println();
		}
	}


	private boolean check(int i)
	{
	       //checking if all resources for ith process can be allocated
	       for(int j=0;j<resourceNum;j++) 
		       if(avail[j]<need[i][j])
		          return false;
    	       return true;
	}
 
	public void checkIfSafe()
	{
		boolean processDone[] = new boolean[processNum];
		int flag = 0; //when flag becomes = num of processes, looping stops
		
		while(flag < processNum)
		{
			boolean allocated=false;
			for(int i =0;i<processNum;i++)
				if(!processDone[i] && check(i))
				{
					for(int k=0; k<resourceNum; k++)
						avail[k] += alloc[i][k];
					
					System.out.println("Allocated process : "+i);
					allocated = processDone[i] = true;
					flag++;
				}
			if(!allocated) break;
		}
		if(flag==processNum)  //if all processes are allocated
        		System.out.println("\nSafely allocated");
      		else
        		System.out.println("All proceess cant be allocated safely");
	}
	
	public static void main(String[]args) throws IOException
	{
		bankerMock obj = new bankerMock();
		obj.takeInput();
	    obj.calcNeed();
		obj.displayNeed();
		obj.checkIfSafe();
	}
}

import java.util.Scanner;

public class InitiateAlgorithm {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ans;
		int choice = 0;
		do {
			System.out.print("\n*********** ALGORITHM MENU ************");
			System.out.print("\n\t1. FIFO\n\t2. LRU\n\t3. Optimal\nCHOICE : ");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				FIFO fifo = new FIFO();
				fifo.accept();
				fifo.algorithm();
				fifo.displayAll();
				break;
			case 2:
				LRU lru = new LRU();
				lru.accept();
				lru.algorithm();
				lru.displayAll();
				break;
			case 3:
				Optimal optimal = new Optimal();
				optimal.accept();
				optimal.algorithm();
				optimal.displayAll();
				break;
			default:
				break;
			}
			System.out.print("\nDo you want to continue (y/n)?\nAnswer : ");
			ans = scan.next();
		} while (ans.charAt(0) == 'y' || ans.charAt(0) == 'Y');
	}

}

/*
input : 
2	3	2	1
5	2	4	5
3	2	5	2
*/
/*

*********** ALGORITHM MENU ************
	1. FIFO
	2. LRU
	3. Optimal
CHOICE : 1

Enter page frame size : 3
Enter reference string size : 12
Enter reference string : 
2	3	2	1
5	2	4	5
3	2	5	2

		***** FIFO *****


Page No.	Miss/Hit		Frame
 2		Miss			2	0	0
 3		Miss			2	3	0
 2		Hit			2	3	0
 1		Miss			2	3	1
 5		Miss			5	3	1
 2		Miss			5	2	1
 4		Miss			5	2	4
 5		Hit			5	2	4
 3		Miss			3	2	4
 2		Hit			3	2	4
 5		Miss			3	5	4
 2		Miss			3	5	2
--------------------------------------------

Refernce String is : 
 2 3 2 1 5 2 4 5 3 2 5 2
Page Faults : 9
Page Hits : 3
Page Hit Ratio : 0.25
--------------------------------------------

Do you want to continue (y/n)?
Answer : y

*********** ALGORITHM MENU ************
	1. FIFO
	2. LRU
	3. Optimal
CHOICE : 2

Enter page frame size : 3
Enter reference string size : 12
Enter reference string : 
2	3	2	1
5	2	4	5
3	2	5	2
			**** LRU ****


Page No.	Miss/Hit		Frame
 2		Miss			2	0	0
 3		Miss			2	3	0
 2		Hit			2	3	0
 1		Miss			2	3	1
 5		Miss			2	5	1
 2		Hit			2	5	1
 4		Miss			2	5	4
 5		Hit			2	5	4
 3		Miss			3	5	4
 2		Miss			3	5	2
 5		Hit			3	5	2
 2		Hit			3	5	2
--------------------------------------------

Refernce String is : 
 2 3 2 1 5 2 4 5 3 2 5 2
Page Faults : 7
Page Hits : 5
Page Hit Ratio : 0.41666666
--------------------------------------------

Do you want to continue (y/n)?
Answer : y

*********** ALGORITHM MENU ************
	1. FIFO
	2. LRU
	3. Optimal
CHOICE : 3

Enter page frame size : 3
Enter reference string size : 12
Enter reference string : 
2	3	2	1
5	2	4	5
3	2	5	2
			**** Optimal ****


Page No.	Miss/Hit		Frame
 2		Miss			2	0	0
 3		Miss			2	3	0
 2		Hit			2	3	0
 1		Miss			2	3	1
 5		Miss			2	3	5
 2		Hit			2	3	5
 4		Miss			2	3	4
 5		Miss			2	3	5
 3		Hit			2	3	5
 2		Hit			2	3	5
 5		Hit			2	3	5
 2		Hit			2	3	5
--------------------------------------------

Refernce String is : 
 2 3 2 1 5 2 4 5 3 2 5 2
Page Faults : 6
Page Hits : 6
Page Hit Ratio : 0.5
--------------------------------------------

Do you want to continue (y/n)?
Answer : n

*/
import java.awt.DisplayMode;
import java.util.*;

public class Pages {
	int frameSize;
	int refStringLength;
	int[] pageFrame;
	int[] refString;
	int[] timeFrame;
	int[] distanceFrame;
	int pageFaults;
	int pageHits;
	float hitRatio;
	Scanner scan = new Scanner(System.in);

	public Pages() {
		frameSize = 0;
		refStringLength = 0;
		pageFaults = 0;
		pageHits = 0;
		hitRatio = 0.0f;
	}

	public void accept() {
		System.out.print("\nEnter page frame size : ");
		frameSize = scan.nextInt();
		System.out.print("Enter reference string size : ");
		refStringLength = scan.nextInt();
		
		refString = new int[refStringLength];
		pageFrame = new int[frameSize];
		timeFrame = new int[frameSize];
		distanceFrame = new int[frameSize];

		System.out.print("Enter reference string : \n");
		for (int i = 0; i < refStringLength; i++)
			refString[i] = scan.nextInt();

	}

	public void displayRefString() {
		System.out.println("\nRefernce String is : ");
		for (int i = 0; i < refStringLength; i++)
			System.out.print(" " + refString[i]);
	}

	public void displayPageFrame() {
		for (int i = 0; i < frameSize; i++)
			System.out.print("\t" + pageFrame[i]);
		System.out.print("\n");
	}

	public void calculateHitRatio() {
		pageHits = (refStringLength - pageFaults);
		hitRatio = (float)pageHits / refStringLength;
	}

	public void displayAll() {
		calculateHitRatio();
		System.out.println("--------------------------------------------");
		displayRefString();
		System.out.println("\nPage Faults : " + pageFaults);
		System.out.println("Page Hits : " + pageHits);
		System.out.println("Page Hit Ratio : " + hitRatio);
		System.out.println("--------------------------------------------");

	}
}

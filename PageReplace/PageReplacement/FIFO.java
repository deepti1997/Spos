
public class FIFO extends Pages {
	public void algorithm() {
		int j = 0;
		System.out.println("\n\t\t***** FIFO *****");
		System.out.print("\n\nPage No.\tMiss/Hit\t\tFrame\n");
		for (int i = 0; i < refStringLength; i++) {
			// If frame empty and element not already in frame
			if (notInFrame(refString[i])) {
				pageFrame[j] = refString[i];
				j++;
				pageFaults++;
				if (frameSize == j)
					j = 0;
				System.out.print(" "+refString[i]+"\t\tMiss\t\t");
			}
			else
				System.out.print(" "+refString[i]+"\t\tHit\t\t");
			displayPageFrame();
		}
		calculateHitRatio();
	}

//returns true if not in frame
	public boolean notInFrame(int x) {
		for (int i = 0; i < frameSize; i++) {
			if (pageFrame[i] == x)
				return false;
		}
		return true;
	}
}

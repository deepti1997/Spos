
public class Optimal extends Pages {
	public void algorithm() {
		int j = 0;
		System.out.println("\t\t\t**** Optimal ****");
		System.out.print("\n\nPage No.\tMiss/Hit\t\tFrame\n");
		for (int i = 0; i < refStringLength; i++) {
			// If frame empty and element not already in frame
			if (notInFrame(refString[i]) == -1 && isFrameEmpty()) {
				pageFrame[j] = refString[i];
				j++;
				pageFaults++;
				System.out.print(" " + refString[i] + "\t\tMiss\t\t");
			}
			// once frame initialized
			else if (notInFrame(refString[i]) == -1) {
				j = indexOptimal();
				pageFrame[j] = refString[i];
				j++;
				pageFaults++;
				System.out.print(" " + refString[i] + "\t\tMiss\t\t");
			} else {
				System.out.print(" " + refString[i] + "\t\tHit\t\t");
			}
			displayPageFrame();
		}
		calculateHitRatio();
	}

	// returns -1 if not in frame or else returns index
	public int notInFrame(int x) {
		for (int i = 0; i < frameSize; i++) {
			if (pageFrame[i] == x)
				return i;
		}
		return -1;
	}

	// returns true if even one element of frame is empty
	public boolean isFrameEmpty() {
		for (int i = 0; i < frameSize; i++) {
			if (pageFrame[i] == 0)
				return true;
		}
		return false;
	}

	// returns the index value of maximum time value page in the frame
	public void updateDistanceFrame() {
		for (int i = 0; i < frameSize; i++) {
			for (int j = 0; j < refStringLength; j++) {
				if (refString[j] == pageFrame[i]) {
					distanceFrame[i] = j;
					break;
				}
			}
		}
	}
	//to return index of the page in page fame having
	//maximum distance
	public int indexOptimal() {
		updateDistanceFrame();
		int max = 0; // index of page with max distance value
		for (int i = 0; i < frameSize; i++) {
			if (distanceFrame[i]>distanceFrame[max])
					max = i;
		}
		return max;
	}
}

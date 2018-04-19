
public class LRU extends Pages {
	public void algorithm() {
		int j = 0;
		System.out.println("\t\t\t**** LRU ****");
		System.out.print("\n\nPage No.\tMiss/Hit\t\tFrame\n");
		for (int i = 0; i < refStringLength; i++) {
			// If frame empty and element not already in frame
			if (notInFrame(refString[i])==-1 && isFrameEmpty()) {
				pageFrame[j] = refString[i];
				j++;
				pageFaults++;
				System.out.print(" " + refString[i] + "\t\tMiss\t\t");
			} 
			//once frame initialized
			else if(notInFrame(refString[i])==-1) {
				j = indexLeastRecentlyUsed();
				pageFrame[j] = refString[i];
				j++;
				pageFaults++;
				System.out.print(" " + refString[i] + "\t\tMiss\t\t");
			}
			else {
				System.out.print(" " + refString[i] + "\t\tHit\t\t");
				timeFrame[notInFrame(refString[i])]=0;			//time frame is made zero as hit has occurred an this page becomes recently used
			}
			updateTimeFrame();
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
	//returns the index value of maximum time value page in the frame
	public int indexLeastRecentlyUsed() {
		int max = 0;	//index of page with max time value
		for(int i =0;i<frameSize;i++)
		{
			if(timeFrame[max]<timeFrame[i])
				max = i;
		}
		timeFrame[max]=0;	//as now new element comes on this place
		return max;
	}
	//increment all indices by one if frame slot is not zero or empty
	public void updateTimeFrame() {
		for(int i =0;i<frameSize;i++)
		{
			if(pageFrame[i]!=0)
				timeFrame[i]++;
		}	
	}
}

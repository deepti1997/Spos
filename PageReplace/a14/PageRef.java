class PageRef{
	int pid;
	int distance;
	
	public PageRef(int pid){
		this.pid=pid;
		this.distance=0;
	}

	public void setPid(int pid){this.pid=pid;}
	public void setDistance(int distance){this.distance=distance;}
	public int getPid(){return pid;}
	public int getDistance(){return distance;}
}

import java.util.*;

abstract class MMU{
	public static ArrayList<PageRef> pagelist =new ArrayList();
	public static int framecount;
	

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of page refs :");
		int pcount=scan.nextInt();	
		System.out.println();
		for(int i=0;i<pcount;i++){
			System.out.print("Enter PID :");
			PageRef p =new PageRef(scan.nextInt());
			pagelist.add(p);
			System.out.println();
		}
		
		System.out.print("Enter number of Frames :");
		framecount=scan.nextInt();	
		System.out.println();
		
		//LRU lru =new LRU();
		//lru.runme();
		OPT opt = new OPT();
		opt.runme();
	}
	abstract public void runme();
	public boolean in_list(ArrayList<PageRef> list,PageRef key){
		for(PageRef p:list){
			if(p.getPid()==key.getPid())
			return true;
		}
		return false;
	}
	
	public void show_list(ArrayList<PageRef> list){
			System.out.println("-----------------");
			for(PageRef p:list){
			System.out.println("|"+p.getPid()+"  -> "+p.getDistance());
			}
			System.out.println("-----------------");
	}
}

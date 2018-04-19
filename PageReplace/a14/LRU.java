import java.util.*;
class LRU extends MMU{
	public static ArrayList<PageRef> frames= new ArrayList(framecount);
	public static int hit=0;
	public static int miss=0;
	public void runme(){
			for(int i=0;i<pagelist.size();i++){
				PageRef cur = pagelist.get(i);
				System.out.println("At :"+cur.getPid());
				if(!in_list(frames,cur)&&frames.size()<framecount){
				System.out.println("MISS AVAIL");
				miss++;
				move();
				frames.add(cur);
				
				}
				else if(in_list(frames,cur)){
				System.out.println("HIT");
				hit++;
				move();
				reset(cur);
				}
				else if(!in_list(frames,cur)&&frames.size()==framecount){
				miss++;
				frames.sort(new Comparator<PageRef>(){
					public int compare(PageRef p1,PageRef p2){
						return p2.getDistance()-p1.getDistance();
					}
				});
				System.out.println("---------------sort-----------------");		
				show_list(frames);
				System.out.println("---------------sort-----------------");
				
				System.out.println("VICTIM "+frames.get(0).getPid());
				frames.remove(0);
				move();
				frames.add(cur);
				}
				show_list(frames);
			}
	}
	
	public static void reset(PageRef key){
		for(PageRef p:frames){
			if(p.getPid()==key.getPid()){
				p.setDistance(0);
			}
		}
	}
	public static void move(){
		for(PageRef p:frames){
				p.setDistance(p.getDistance()+1);
		}
	}	
}

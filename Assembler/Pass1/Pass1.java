
import java.io.*;
import java.util.*;

public class Pass1 {
	
	int flag=0,flag1=0,litflag=0,symflag=0,assignedTill=0;
	int n=0,n1=0,lc=0;
	int symcnt=1,symptr=0,litcnt=1,litptr=0,poolptr=1;
	String temp,temp1,temp2;
	
	String tokens[] = new String[1024];
	String tokens1[] = new String[1024];
	String sym[] = new String[1024];
	String lit[] = new String[1024];
	
	BufferedReader br=null,br1=null;
	BufferedWriter bw=null,bw1=null,bw2=null,bw3=null;
	
	FileReader fr=null,fr1=null;
	FileWriter fw=null,fw1=null,fw2=null,fw3=null;
	
	
	public void getInput() {
		
		try {
			
			fr = new FileReader("code.txt");
			br = new BufferedReader(fr);
			
			fr1 = new FileReader("mnemonics.txt");
			br1 = new BufferedReader(fr1);
			
			fw = new FileWriter("inter.txt");
			bw = new BufferedWriter(fw);
			
			fw1 = new FileWriter("literal.txt");
			bw1 = new BufferedWriter(fw1);
			
			fw2 = new FileWriter("pool.txt");
			bw2 = new BufferedWriter(fw2);
			
			fw3 = new FileWriter("symbol.txt");
			bw3 = new BufferedWriter(fw3);
			
			//Tokens for input code
			
			StringBuffer sb = new StringBuffer();
			String str;
			
			while((str=br.readLine())!=null) {
				
				sb.append(str);
				sb.append("\n");
			
			}
			
			br.close();
			System.out.println("The code is : ");
			System.out.println(sb.toString());
			
			StringTokenizer st = new StringTokenizer(sb.toString()," ,	\n");
			while(st.hasMoreTokens()) {
				
				tokens[n]=st.nextToken();
				n++;
			}
			
			System.out.println("The tokens for the code are:");
			
			for(int i=0;i<n;i++) {
				System.out.println(tokens[i]);
			}
			
			StringBuffer sb1 = new StringBuffer();
			String str1;
			
			while((str1=br1.readLine())!=null) {
				
				sb1.append(str1);
				sb1.append("\n");
			
			}
			
			br1.close();
			System.out.println("The mnemonic file is : ");
			System.out.println(sb1.toString());
			
			//Tokens for mnemonic file
			
			StringTokenizer st1 = new StringTokenizer(sb1.toString()," ,	\n");
			while(st1.hasMoreTokens()) {
				
				tokens1[n1]=st1.nextToken();
				n1++;
			}
			
			System.out.println("The tokens for the mnemonic are:");
			
			for(int i=0;i<n1;i++) {
				System.out.println(tokens1[i]);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void processing() {
		
		
		//START OF PROCESSING
		try {
			
			for(int i=0;i<n;i++) {
				
				//START
				if(tokens[i].equals("START")) {
					
					lc = Integer.parseInt(tokens[i+1]);
					temp = Integer.toString(lc);
					bw.write(temp);
					bw.flush();
				}
				
				//Symbol as label
				
				else if(tokens[i].contains(":")) {
					symflag=0;
					int index=0;
					
					temp1 = Integer.toString(symcnt);
					temp = Integer.toString(lc);
					
					for(int k=0;k<symptr;k++) {
						
						if(sym[k].equals(tokens[i-1])) {
							
							//System.out.println("token already present:"+sym[k]);
							index=k;
							symflag=1;
							break;
							
						}
					}
					
					if(symflag==0) {
						
						//System.out.println("Encountered symbol as label direct:"+tokens[i-1]);
						//System.out.println("setting sym with address:"+tokens[i-1]);
						sym[symptr] = temp1;
						symptr++;
						symcnt++;
						sym[symptr] = tokens[i-1];
						symptr++;
						sym[symptr] = temp;
						symptr++;
					
						
					}
					
					else if(symflag==1){
						
						//System.out.println("I am in for token "+tokens[i-1]+"only putting address for :"+sym[index]);
						
						sym[index+1] = temp;
	
						
					}
						
					
				}
				
				//LTORG
				else if(tokens[i].equals("LTORG")) {
					
					flag=0;
					bw.write("\n");
					
					if(tokens[i+1].startsWith("=")) {
						
						
						for(int j=0;j<litptr;j=j+3) {
							
							System.out.println("In loop");
							
							//IC
							temp = Integer.toString(lc);
							bw.write(temp+"\n");
							bw.flush();
							
							//LITTAB
							lit[j+2]=temp;
							lc++;
							
							assignedTill = j+2;
							
						}
						
				
					}
				
					
					temp = Integer.toString(lc);
					bw.write(temp);
					bw.flush();
					
				}
				
				
				//END
				else if(tokens[i].equals("END")) {
					
					bw.write("	(AD,02)");
					bw.write("\n");
					bw.flush();
					
					flag=0;
					
					if(tokens[i+1].startsWith("=")) {
						
						for(int/*IMP*/ j=assignedTill+1;j<litptr;j=j+3) {
							
							System.out.println("In end loop");
							
							//IC
							temp = Integer.toString(lc);
							bw.write(temp+"\n");
							bw.flush();
							
							//LITTAB
							lit[j+2]=temp;
							lc++;
							
						}
						
				
					}
					
					else if(tokens[i+1].equals(null)) {
						
						//do nothing
						//program ended
						
					}
					
				}

				
				//ORIGIN
				else if(tokens[i].equals("ORIGIN")) {
					
					//IC
					bw.write("\n");
					bw.write("\t"+"(AD,03)");
					bw.write("\n");
					bw.flush();
					
					//Computing value of new lc
	
					for(int /*IMP*/j=1;j<symptr;j=j+3) {
						
						if(tokens[i+1].equals(sym[j])) {
							
							int one = Integer.parseInt(sym[j+1]);
							int two=0;
							if(tokens[i+2].equals("+")) {
								two = Integer.parseInt(tokens[i+3]);
							}
							else {
								two=0;
							}
							int sum = one+two;
							
							lc=sum;
							temp = Integer.toString(lc);
							bw.write(temp);
							bw.flush();
							break;
							
						}
						
					}
					
					
				}
				
				//DS
				
				else if(tokens[i].equals("DS"))
				{
					int found=0;
					
					for(int k=0;k<symptr;k++) {
						found=0;
						if(sym[k].equals(tokens[i-1])) {
							
							temp=Integer.toString(lc);
							sym[k+1]=temp;
									
							//IC
							bw.write("	(DL,01)	");
							bw.write(tokens[i+1]);
							
							//Increment lc
							lc=lc+Integer.parseInt(tokens[i+1]);
							bw.flush();
							
							found=1;
							break;
						}
					}
					
					if(found==0) {
						
						temp1=Integer.toString(symcnt);
						temp=Integer.toString(lc);
						
						sym[symptr] = temp1;
						symptr++;
						symcnt++;
						sym[symptr] = tokens[i-1];
						symptr++;
						sym[symptr] = temp;
						symptr++;
						
						bw.write("	(DL,01)	");
						bw.write(tokens[i+1]);
						
						//Increment lc
						lc=lc+Integer.parseInt(tokens[i+1]);
						bw.flush();
						
					}
						
					bw.write("\n");
					bw.flush();
				}
				
				//EQU
				else if(tokens[i].equals("EQU"))
				{
					//IC
					bw.write("\n");
					bw.write("   (AD,04)");
					bw.flush();
					
					for(int s=1;s<symptr;s=s+3)
					{
						if(tokens[i+1].equals(sym[s]))
						{
							String result=sym[s+1];
							for(int v=1;v<symptr;v=v+3)
							{
								if(tokens[i-2].equals(sym[v]))
								{
									sym[v+1]=result;
									
								}
							}
						}
					}
					
					//IC
					temp=Integer.toString(lc);
					bw.write("\n");
					bw.write(temp);
					bw.flush();
					
				}
				
				//IS
				for(int j=0;j<n1;j=j+3) {
					
					if(tokens[i].equals(tokens1[j])) {
						
						if(tokens[i].equals("STOP")) {
							lc=lc+1;
						}
						else {
							lc=lc+3;
						}
						
					
						//IC
			
						bw.write("	(IS,");
						bw.write(tokens1[j+1]+")");
						if(!tokens[i+1].equals("ORIGIN"))
						bw.write(tokens[i+1]);
						bw.flush();
						
						//For operand2
						if(tokens[i+2].startsWith("=")) {
							
							//PUT LITERALS IN TABLE AND POOL
							
							//LITTAB
				
							temp1 = Integer.toString(litcnt);
							litflag=0;
							//Checking for same literal before LTORG
							for(int k=1;k<litptr;k=k+3) {
								
								if(tokens[i+2].equals(lit[k]) && /*literals in the same pool*/lit[k+1].equals("null")) {
									
									litflag=1;
									break;
									
								}
								
							}
							
							if(litflag==0) {
								
								//--------------------------------
								temp2 = Integer.toString(poolptr);
								poolptr++;
								//---------------------------------
								
								lit[litptr] = temp1;
								litptr++;
								litcnt++;
								lit[litptr] = tokens[i+2];
								litptr++;
								lit[litptr] = "null";
								litptr++;
								
								//IC
								bw.write("	(L,");
								bw.write(temp1+")");
								bw.flush();
								
								
							}
							
							//LITPOOL
							if(flag==0) {
								
								System.out.println("temp2"+temp2);
								
								//Start a new pool
								bw2.write("\n");
								bw2.write("#"+temp2);
								bw2.flush();
								//---------------
								flag=1;
								//---------------
								
							}
							
							
						}
						
						else
						{
							if(tokens[i+1].equals("ORIGIN"))
							{
								break;
							}
							
							//Symbol
							else
							{
								
								//Symbol table
								symflag=0;
								int index=0;
								
								temp1 = Integer.toString(symcnt);
								temp = Integer.toString(lc);
								
								for(int k=0;k<symptr;k++) {
									
									if(sym[k].equals(tokens[i+2])) {
										
										index=k;
										symflag=1;
										break;
										
									}
								}
								
								if(symflag==1) {
									
									
									//System.out.println("Encountered symbols as old:"+tokens[i+2]);
									//do nothing
									
								}
								
								else if(symflag==0) {
									
									//System.out.println("Encountered symbols as new,setting null:"+tokens[i+2]);
									sym[symptr] = temp1;
									symptr++;
									symcnt++;
									sym[symptr] = tokens[i+2];
									symptr++;
									sym[symptr] = "null";
									symptr++;
			
								}
								 				
								//IC
								bw.write(" "+tokens[i+2]);
								bw.flush();
								
							}
						}
						
						bw.write("\n");
						temp1=Integer.toString(lc);
						bw.write(temp1);
						bw.flush();
					}
					
				}
				
				
			}
			
			//END OF PROCESSING----------------
			
			System.out.println("Literals-------");
			for(int j=0;j<litptr;j++) {
				
				System.out.println(lit[j]);
				
			}
			
			System.out.println("Symbols---");
			for(int j=0;j<symptr;j++)
			{
				System.out.println(sym[j]);
			}
			
			//Writing symbols in file
			int counter=1;
			for(int j=0;j<symptr;j++)
			{
				bw3.write(sym[j]);
				bw3.flush();
				if(( counter % 3)==0)
				{
					bw3.write("\n");
					bw3.flush();

				}
				else
				{
					bw3.write("\t");
					bw3.flush();

				}
				counter++;
			}
			
			//Writing literals in file
			int cnt=1;
			for(int j=0;j<litptr;j++)
			{
				bw1.write(lit[j]);
				bw1.flush();
				if(( cnt % 3)==0)
				{
					bw1.write("\n");
					bw1.flush();

				}
				else
				{
					bw1.write("\t");
					bw1.flush();

				}
				cnt++;
			}
		
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
			try {
				bw.close();
				bw1.close();
				bw2.close();
				bw3.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String args[])
	{
		Pass1 obj = new Pass1();
		obj.getInput();
		obj.processing();
	}

}

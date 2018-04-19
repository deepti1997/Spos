import java.util.*;
import java.io.*;

public class Pass2 {
	
	int n=0,s=0,l=0,flag=0;
	
	String tokens[] = new String[1024];
	String sym[] = new String[1024];
	String lit[] = new String[1024];
	
	BufferedReader br=null,br1=null,br2=null;
	BufferedWriter bw=null;
	
	FileReader fr=null,fr1=null,fr2=null;
	FileWriter fw=null;
	
	public void getInput() {
		
		try {
			fr = new FileReader("inter.txt");
			br = new BufferedReader(fr);
			
			fr1 = new FileReader("symbol.txt");
			br1 = new BufferedReader(fr1);
			
			fr2 = new FileReader("literal.txt");
			br2 = new BufferedReader(fr2);
			
			fw = new FileWriter("target.txt");
			bw = new BufferedWriter(fw);
			
			//Tokens of IC
			StringBuffer sb = new StringBuffer();
			String str;
			
			while((str=br.readLine())!=null) {
				
				sb.append(str);
				sb.append("\n");
			
			}
			
			br.close();
			System.out.println("The IC is : ");
			System.out.println(sb.toString());
			
			StringTokenizer st = new StringTokenizer(sb.toString(),"	,() \n");
			while(st.hasMoreTokens()) {
				
				tokens[n]=st.nextToken();
				n++;
			}
			
			System.out.println("The tokens for the code are:");
			
			for(int i=0;i<n;i++) {
				System.out.println(tokens[i]);
			}
			
			//Tokens of symbol file
			StringBuffer sb1 = new StringBuffer();
			String str1;
			
			while((str1=br1.readLine())!=null) {
				
				sb1.append(str1);
				sb1.append("\n");
			
			}
			
			br1.close();
			System.out.println("The symbol file is : ");
			System.out.println(sb1.toString());
			
			StringTokenizer st1 = new StringTokenizer(sb1.toString(),"	\n");
			while(st1.hasMoreTokens()) {
				
				sym[s]=st1.nextToken();
				s++;
			}
			
			System.out.println("The tokens for symbols are:");
			
			for(int i=0;i<s;i++) {
				System.out.println(sym[i]);
			}
			
			//Tokens of literal file
			StringBuffer sb2 = new StringBuffer();
			String str2;
			
			while((str2=br2.readLine())!=null) {
				
				sb2.append(str2);
				sb2.append("\n");
			
			}
			
			br2.close();
			System.out.println("The literal file is : ");
			System.out.println(sb2.toString());
			
			StringTokenizer st2 = new StringTokenizer(sb2.toString(),"	\n");
			while(st2.hasMoreTokens()) {
				
				lit[l]=st2.nextToken();
				l++;
			}
			
			System.out.println("The tokens for literals are:");
			
			for(int i=0;i<l;i++) {
				System.out.println(lit[i]);
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
		
		try {
			
			for(int i=0;i<n;i++) {
				
				//IS INSTRUCTIONS
				
				if(tokens[i].equals("IS")) {
					
					bw.write(tokens[i-1]+"\t"+tokens[i+1]+"\t");
					bw.flush();
					
					//First operand
					
					if(tokens[i+2].equals("AREG")) {
						
						bw.write("1"+"\t");
						bw.flush();
						
					}
					
					else if(tokens[i+2].equals("BREG")) {
						
						bw.write("2"+"\t");
						bw.flush();
						
					}
					
					else if(tokens[i+2].equals("CREG")) {
						
						bw.write("3"+"\t");
						bw.flush();
						
					}
					
					else if(tokens[i+2].equals("DREG")) {
						
						bw.write("4"+"\t");
						bw.flush();
						
					}
					
					//Second operand is a literal
					
					if(tokens[i+3].equals("L")) {
						
						for(int a=0;a<l;a++) {
							
							if(lit[a].equals(tokens[i+4])) {
								
								bw.write(lit[a+2]+"\n");
								bw.flush();
								break;
							}
							
						}
						
					}
					
					//Second operand is a symbol
					
					else {
						
						for(int a=1;a<s;a=a+3) {
							
							if(sym[a].equals(tokens[i+3])) {
								
								bw.write(sym[a+1]+"\n");
								bw.flush();
								break;
								
							}
							
						}
						
					}
					
					
				}
				
				//AD INSTRUCTIONS
				
				if(tokens[i].equals("AD")) {
					
					//LTORG
					if(tokens[i+1].equals("05")) {
						bw.write("\n");
						bw.flush();
						
						while(tokens[i+3].startsWith("=")) {
							
							bw.write(tokens[i+2]);
							bw.write("\t"+tokens[i+3]);
							bw.write("\n");
							bw.flush();
							
							i++;
							i++;
							
						}
						
						
					}
					
					//END
					else if(tokens[i+1].equals("02")) {
						
						bw.write("\n");
						bw.flush();
						
						int cnt=1;
							
						if(i+3<n) {
							
							//If literals are present
							while(tokens[i+3].startsWith("=")) {
								bw.write("\n");
								
								if(cnt==1) {
									//Write this only once
									bw.write(tokens[i+2]);
								}
								

								bw.write("\t"+tokens[i+3]);
								bw.write("\n");
								bw.flush();
								
								flag=1;
								i++;
								if(i+3>=n) {
		
									break;
								}
								cnt++;
								
							}
						
							//if literals are not present
							if(flag==0) {
							
								bw.write(tokens[i+2]);
								bw.flush();
							
							}
							
						}
						
							
					}
					
					//ORIGIN
					else if(tokens[i+1].equals("03")) {
						
						bw.write("\n");
						bw.flush();
							
					}
						
					//EQU
					else if(tokens[i+1].equals("04")) {
						
						bw.write("\n");
						bw.flush();
							
					}
				}
				
				//Declarative INSTRUCTIONS
				
				if(tokens[i].equals("DL")) {
					
					bw.write(tokens[i-1]);
					bw.write("\n");
					bw.flush();
					
				}
				
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pass2 obj = new Pass2();
		obj.getInput();
		obj.processing();

	}

}

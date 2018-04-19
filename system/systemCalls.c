#include <stdio.h>
#include <unistd.h>

int glob = 6;
void caseFork(){
	int var;
	pid_t pid;
	var = 88;
	printf("Before Fork\n");
	if((pid = fork()) < 0){
		printf("Fork Error\n");
	}else if (pid == 0){
		glob++;
		var++;
	}else{
		sleep(2);
	}
	printf("pid = %d,glob = %d , var = %d\n",getpid(),glob,var);
	
}
void caseVFork(){
	printf("Before vFork - glob : %d\n",glob );
	int var;
	pid_t pid;
	var=88;
	if((pid = vfork()) < 0){
		printf("Fork Error\n");
	}else if(pid == 0){
		glob++;
		var++;
		exit(0);
	}
	printf("pid = %d,glob = %d , var = %d\n",getpid(),glob,var);
}
void caseWait1(){
	int retVal, retCode;
	pid_t pid;
	if((pid=fork())<0){
		printf("Child process %x \n",getpid() );
		exit(10);
	}
	retVal = wait(&retCode);
	printf("Wait retVal %x retCode c %x\n",retVal,retCode );
}
void caseWait2() {
	if (fork()== 0)
        printf("HC: hello from child\n");
    else
    {
        printf("HP: hello from parent\n");
        wait(NULL);
        printf("CT: child has terminated\n");
    }
 
    printf("Bye\n");
    return 0;
}
void caseExecvp(){
	//A null terminated array of character 
        //pointers
        char *args[]={"./EXEC",NULL};
        execvp(args[0],args);
     
        /*All statements are ignored after execvp() call as this whole 
        process(execDemo.c) is replaced by another process (EXEC.c)
        */
        printf("Ending-----");
}
int main()
{
	/* code */
	int n;
	printf("1 - Fork\n2 - vFork\n3 - Wait\n4 - Execvp");
	scanf("%d",&n);
	switch(n){
		case 1 : caseFork();
		break;
		case 2 : caseVFork();
		break;
		case 3 : caseWait1();
				 caseWait2();
		break;
		case 4 : caseExecvp();
		break;
	}
	return 0;
}
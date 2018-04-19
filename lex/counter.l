%{
#include<stdio.h>
int charcount=0,linecount=0,wordcount=0;

%}

%%
[^ \t\n]+ {ECHO;charcount+=yyleng;wordcount++;}
[\n] {ECHO;linecount++;charcount++;}
. {ECHO;charcount++;}

%%

main(){
yylex();
printf("\n\t\t******** WELCOME **********\n");
printf("\n\tCharacter Count : %d",charcount);
printf("\n\tWord Count : %d",wordcount);
printf("\n\tLine Count : %d",linecount);
printf("\n******************************\n");
}
int yywrap()
{
return 1;
}

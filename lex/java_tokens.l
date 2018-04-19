%{
#include<stdio.h>
int flag=0;

%}
quotes ["]
syso "System.out.println("
package "*"|";"
java " java"
%%
public|static|void|main|class|import {printf("\nKeyword : %s",yytext);}
{java}+[a-zA-Z.]+{package} {printf("\nPackage : %s",yytext);}
("class "+[A-za-z0-9]+)  {printf("\nClass : %s",yytext);}
"{"|"}"|"("|")" {printf("\n Brackets : %s",yytext);}
"String"|"int"|"float"|"char" {printf("\nDatatype : %s",yytext);}
"+"|"="|"/"|"*" {printf("\nOperator : %s",yytext);}
[0-9]+ {printf("\nConstant : %s",yytext);}
({syso}+{quotes}+[A-Za-z0-9 :]+{quotes}) {printf("\nStatement : %s",yytext);}
[A-za-z0-9]+ {printf("\nVariable : %s",yytext);}
; {printf("\nTerminator : %s",yytext);}
. {}
%%

main(){
printf("\n\t\t******** WELCOME **********\n");
yylex();
}
int yywrap()
{
return 1;
}

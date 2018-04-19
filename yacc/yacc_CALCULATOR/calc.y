%{
#include <stdio.h>
#include <math.h>
%}
%token NUM
%left '+''-'
%left '*''/'

%%

start:exp	{printf("%d\n",$$);}
exp:exp'+'exp {$$=$1+$3;}
	|exp'-'exp {$$=$1-$3;}
	|exp'*'exp {$$=$1*$3;}
	|exp'/'exp {
					if($3==0)
					{
						exit(0);
					}
					else
					{
						$$=$1/$3;
					}
				}
	|'('exp')'	{$$=$2;}
	|NUM		{$$=$1;}
	;
%%
yyerror()
{
	printf("ERROR\n");
}

yywrap(){}

main(void)
{
	printf("Enter expression:");
	if(yyparse()==0)
	{
		printf("SUCCESS");
	}
}

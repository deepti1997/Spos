%{
#include<stdio.h>
%}

%token DT COMMA SC NL EQU VAR

%%
start: DT varlist SC NL {printf("valid\n");exit(0);}
;
varlist: varlist COMMA varlist
    | varlist COMMA VAR
    | VAR EQU
    | VAR 
    ;

%%
void yyerror(const char *str){printf("error\n");}
int yywrap()
{
    return 0;
}
main(void)
{
    yyparse();
}



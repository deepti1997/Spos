%{
#include <stdio.h>
#include "y.tab.h"
%}

%%

int|char|float|double|String|boolean return DT;

[a-zA-Z0-9]+ return VAR;

"," return COMMA;

";" return SC;

=[0-9]+ return EQU;

"\n" return NL;

%%



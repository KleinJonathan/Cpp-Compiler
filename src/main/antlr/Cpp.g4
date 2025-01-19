grammar Cpp;

// Parser
start               : stmt* ;


stmt                : declfunc ';'                                                                  # decfunc
                    | deffunc                                                                       # dfunc
                    | callfunc ';'                                                                  # cfunc
                    | declnew ';'                                                                   # decl2
                    | assignnew ';'                                                                 # assign1
                    | assignold ';'                                                                 # assign2
                    | ifelseblock                                                                   # ifelseblock1
                    | whileconn openscope body closescope                                           # whileblock
                    | builtin '(' expr ')' ';'                                                      # builtinstmt
                    | classdef                                                                      # classsstm
                    | openscope (stmt | expr ';' )+ closescope                                           # block
                    | RETURN expr? ';'                                                              # return
                    ;
//body                : (expr | stmt)+;
body                : (stmt | expr ';')+;
////
////


//Scope
openscope           : OPENSCOPE;
closescope          : CLOSESCOPE;
////
////


// Variable
basetype            : TYPE
                    | ID
                    ;
declvar             : ID;
declarray           : ID '[' expr ']';
defrefvar           : const? basetype REF ID;
declrefvar          : const? basetype REF;
decl                : basetype declvar
                    | basetype declarray
                    ;

assignvar           : ID SET expr;
assignarrayelement  : ID '[' expr ']' SET expr                                           # assignarrayelem
                    | ID '[' expr ']' '++'                                               # incarray
                    | ID '[' expr ']' '--'                                               # decarray
                    ;
assignnewarray      : ID '[' arraysize? ']' SET '{' (expr)(',' expr)* '}';
arraysize           : expr;
// Klassenvariablen und Objekte
assignclassvar      : ID'.'ID SET expr
                    | ID '[' expr ']' '.' ID SET expr
                    ;
assignnewclass      : ID
                    | ID SET callfunc                 //Student s = Student();
                    | ID '(' callparamlist ')'              // Student s(5);
                    ;

declnew             : basetype (declarray | declvar)(',' (declarray | declvar))*
                    ;
assignnew           : const? basetype REF? (assignvar | assignnewarray | assignnewclass)
                        (',' (assignvar | assignnewarray | assignnewclass))*
                    ;
assignold           : (assignvar | assignarrayelement | assignclassvar | assignnewclass)
                        (',' (assignvar | assignarrayelement | assignclassvar | assignnewclass))*;
////
////


// Klassen und Konstruktoren
classdef            : 'class' ID vererbung? '{' 'public' ':' (constructor | destruct /*| overridefunc | abstractfunc*/ | stmt)* '}' ';'
                    ;
//Konstruktor hat keine Rückgabe == Unterscheidung zu Funktionen
constructor         : ID '(' defparamlist ')'  openscope body? closescope   ';'?
                    | ID '(' defparamlist ')' ':' (initlist (',' initlist)*)? openscope body? closescope ';'?
                    | ID '(' defparamlist ')' ';'
                    | ID '(' ')' SET 'default' ';'
                    ;
destruct            : '~' ID '(' ')' openscope body? closescope ';'?
                    | '~' ID '(' ')' ';'?
                    | VIRTUAL '~' ID '(' ')' ';'?
                    | VIRTUAL '~' ID '(' ')' openscope body? closescope ';'?
                    ;
initlist            : ( ID '(' expr ')')
                    | ( ID '(' expr ',' expr ')')
                    ;
vererbung           : ':' 'public'? ID
                    ;
////
////


// Function
abstractfunc        : const? '=' NUM ';'?; // Hier wirde NUM gewählt, da bei der direkten Eongabe von 0 gab es Probleme beim Lexer
declfunc            : VIRTUAL? (basetype REF?| VOID) ID '(' defparamlist ')' abstractfunc? ';'? ;
deffunc             : VIRTUAL? (basetype REF?| VOID) ID '(' defparamlist ')' (const? OVERRIDE)? openscope body closescope ';'?       # funcdef
                    ;
// Es kann nicht unterschieden werden zwischen einem Funktionsaufruf und einem Konstruktoraufruf, der ja auch eine Funktion ist
callfunc            : ID '(' callparamlist ')'
                    | ID'.'ID '(' callparamlist ')'
                    ;

defparamlist        : ((defparam)(',' defparam)* | );
defparam            : (assignnew | const? decl | defrefvar | declrefvar | );

callparamlist       : ((callparam)(',' callparam)* | );
callparam           : expr;

builtin             : 'print_bool'                                                              # printbool
                    | 'print_int'                                                               # printint
                    | 'print_char'                                                              # printchar
                    ;
////
////


// IF ELSE WHILE
ifelseblock         : ifconn ifblock elseblock?;
ifblock             : openscope body closescope;
elseblock           : ELSE (ifelseblock | openscope body closescope);
ifconn              : IF '(' (expr | decl | assignnew | assignold | assignclassvar) (com expr)* ')'
                    ;
whileconn           : WHILE '(' (expr | decl | assignnew | assignold | assignclassvar) (com expr)* ')'
                    ;
////
////


// Expression
expr                : expr '*' expr                                                             # mul
                    | ID '*=' expr                                                              # muleq
                    | expr '/' expr                                                             # div
                    | ID '/=' expr                                                              # diveq
                    | expr '+' expr                                                             # add
                    | ID '+=' expr                                                              # addeq
                    | ID '++'                                                                   # inc
                    | expr '-' expr                                                             # sub
                    | ID '-=' expr                                                              # subeq
                    | ID '--'                                                                   # dec
                    | expr com expr                                                             # compare
                    | callfunc                                                                  # call
                    | ID '[' expr ']' '.' ID                                                    # classarrayelem
                    | ID '[' expr ']'                                                           # arrayelem
                    | ID'.'ID                                                                   # classvar
                    | NUM                                                                       # num
                    | ID                                                                        # id
                    | CHAR                                                                      # char
                    | BOOL                                                                      # bool
                    ;
////
////


// Comperator
com                 : EQUAL
                    | NOTEQUAL
                    | LOWER
                    | LOWEREQUAL
                    | GREATER
                    | GREATEREQUAL
                    ;
////
////


// Const
const               : CONST;


// Lexer
OPENSCOPE   : '{';
CLOSESCOPE  : '}';
OVERRIDE    : 'override';
VIRTUAL     : 'virtual';
RETURN      : 'return';
IF          : 'if';
ELSE        : 'else';
WHILE       : 'while';
DO          : 'do';

EQUAL       : '==';
NOTEQUAL    : '!=';
LOWER       : '<';
LOWEREQUAL  : '<=';
GREATER     : '>';
GREATEREQUAL: '>=';

BOOL        : 'true'
            | 'false'
            ;
SET         : '=';
CONST       : 'const';
VOID        : 'void'
            ;
TYPE        : 'char'
            | 'int'
            | 'bool'
            ;
EXPLIZIT    : 'explicit';
REF         : '&';
NUM         : [0-9]+;
ID          : [a-zA-Z_][a-zA-Z0-9_]*;
CHAR        : '\'' (~[\n\r"])? '\''; // Hier habe ich no Greedy gewählt, damit zum Beipeiel "Person p1(20, 'A'); // age=20, name='A'" erkannt wird
COMMENTLINE : '//' ~[\n\r]* -> skip;
COMMENTBLOCK: '/*' .*? '*/' -> skip;
WS          : [ \t\n]+ -> skip ;
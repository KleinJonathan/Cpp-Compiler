grammar Cpp;

// Parser
start               : stmt* ;


stmt                : declfunc ';'                                                                  # decfunc
                    | deffunc                                                                       # dfunc
                    | callfunc ';'                                                                  # cfunc
                    | decl   ';'                                                                    # decl1
                    | declnew ';'                                                                   # decl2
                    | assignnew ';'                                                                 # assign1
                    | assignold ';'                                                                 # assign2
                    | ifblock (elseblock*)?                                                         # ifelseblock
                    | whileconn openscope body closescope                                           # whileblock
                    | builtin '(' expr (',' expr)* ')' ';'                                          # builtinstmt
                    | classdef                                                                      # classsstm
                    | openscope stmt+ closescope                                                    # block
                    | RETURN (expr | ) ';'                                                          # return
                    | expr ';'                                                                      # exprstmt
                    ;
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
// Klassenvariablen
assignclassvar      : ID'.'ID SET expr
                    | ID '[' expr ']' '.' ID SET expr
                    ;
assignnewclass      : REF ID                        // Student &s = Student();
                    | ID SET callfunc                 //Student s = Student();
                    | ID '(' callparamlist ')'              // Student s(5);
                    | ID SET callparamlist              // Student2 &s2 = s1;
                    ;

declnew             : basetype (declarray | declvar)(',' (declarray | declvar))*
                    ;
assignnew           : const? basetype REF? (assignvar | assignnewarray | assignnewclass | defrefvar) (',' (| assignvar | assignnewarray | assignnewclass | defrefvar))*       //# assignwithoutref
                    //| const? TYPE REF assignrefvar (',' REF assignrefvar)*                                # assignwithref
                    ;
assignold           : (assignvar | assignarrayelement | assignclassvar | assignnewclass) (',' (assignvar | assignarrayelement | assignclassvar | assignnewclass))*;
////
////


// Klassen
classdef            : (EXPLIZIT | ABSTRACT)?  'class' ID vererbung? '{' ('public' ':' (constructor | stmt)*)? ('private' ':' (constructor | stmt)*)? '}' ';'
                    | (EXPLIZIT | ABSTRACT)? 'class' ID vererbung? '{' (constructor | stmt)* '}' ';'
                    ;
//Konstruktor hat keine Rückgabe == Unterscheidung zu Funktionen
constructor         : ID '(' defparamlist ')'  openscope body? closescope   ';'?
                    | ID '(' defparamlist ')' ':' (initlist (',' initlist)*)? openscope body? closescope ';'?
                    | ID '(' defparamlist ')' ';'
                    | ID '(' ')' SET 'default' ';'
                    ;
initlist            : ( ID '(' expr ')')
                    | ( ID '(' expr ',' expr ')')
                    ;
vererbung           : ':' 'public' ID
                    ;
////
////


// Function
declfunc            : (basetype REF?| VOID) ID '(' defparamlist ')' ';'? ;
deffunc             : (basetype REF?| VOID) ID '(' defparamlist ')' openscope body closescope ';'?              # funcdef
                    | (basetype REF?| VOID)? ID'::'ID '(' defparamlist ')' openscope body closescope  ';'?      # classfunc
                    ;
// Es kann nicht unterschieden werden zwischen einem Funktionsaufruf und einem Konstruktoraufruf, der ja auch eine Funktion ist
callfunc            : ID '(' callparamlist ')'
                    | ID'.'ID '(' callparamlist ')'
                    | ID'::'ID '(' callparamlist ')'
                    ;

defparamlist        : ((defparam)(',' defparam)* | );
//defparam            : (decl | assignnew | const? declnew | assignnewclass | declrefvar | const? basetype '&' | );
defparam            : (decl | assignnew | const? declnew | defrefvar | declrefvar | );

callparamlist       : ((callparam)(',' callparam)* | );
callparam           : expr;

builtin             : 'print_bool'                                                              # printbool
                    | 'print_int'                                                               # printint
                    | 'print_char'                                                              # printchar
                    ;
////
////


// IF ELSE WHILE
ifblock             : ifconn openscope body closescope;
elseblock           : elseconn  openscope body closescope;
ifconn              : IF '(' (expr | decl | assignnew | assignold | assignclassvar) (com expr)* ')'
                    ;
whileconn           : WHILE '(' (expr | decl | assignnew | assignold | assignclassvar) (com expr)* ')'
                    ;
elseconn            : ELSE ifconn?;
body                : stmt+;
////
////


// Expression
expr                : expr com expr                                                             # compare
                    | expr '*' expr                                                             # mul
                    | expr '*=' expr                                                            # muleq
                    | expr '/' expr                                                             # div
                    | expr '/=' expr                                                            # diveq
                    | expr '+' expr                                                             # add
                    | expr '+=' expr                                                            # addeq
                    | expr '++'                                                                 # inc
                    | expr '-' expr                                                             # sub
                    | expr '-=' expr                                                            # subeq
                    | expr '--'                                                                 # dec
                    | callfunc                                                                  # call
                    | ID'.'ID                                                                   # classvar
                    | ID                                                                        # id
                    | NUM                                                                       # num
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
PUBLIC      : 'public';
PRIVATE     : 'private';
ABSTRACT    : 'abstract';
REF         : '&';
ID          : [a-zA-Z_][a-zA-Z0-9_]*;
NUM         : [0-9]+;
CHAR        : '\'' (~[\n\r"])* '\'';
COMMENT     : '//' ~[\n\r]* -> skip ;
WS          : [ \t\n]+ -> skip ;
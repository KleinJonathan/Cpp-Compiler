import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterpreterTest {
    private AST root;
    private Scope globalScope;

    public InterpreterTest() {
        this.globalScope = new Scope(null); // Global scope
    }

    public void interpret(AST root) {
        this.root = root;
        eval(root, globalScope);
    }

    private SymbolType determineType(Object value) {
        if (value instanceof Integer) {
            return new SymbolType("int");
        } else if (value instanceof String) {
            return new SymbolType("string");
        } else if (value instanceof Boolean) {
            return new SymbolType("bool");
        } else {
            throw new RuntimeException("Unsupported value type: " + value.getClass());
        }
    }

    private List<String> extractParamTypes(AST paramListAST) {
        List<String> paramTypes = new ArrayList<>();
        for (AST param : paramListAST.kinder) {
            paramTypes.add(param.value); // Assume `param.value` holds the parameter type
        }
        return paramTypes;
    }

    private Object eval(AST t, Scope currentScope) {
        if (t == null) return null;

        switch (t.asttype) {
            case START: // Entry point of the program
                for (AST c : t.kinder) {
                    eval(c, currentScope);
                }
                break;

            case SCOPE:
                Scope newScope = new Scope(currentScope);
                for (AST c : t.kinder) {
                    eval(c, newScope);
                }
                break;

            case NUM:
                return Integer.parseInt(t.value);

            case CHAR:
                return t.value;

            case BOOL:
                return Boolean.parseBoolean(t.value);

            case ADD:
                return (Integer) eval(t.kinder.get(0), currentScope) +
                        (Integer) eval(t.kinder.get(1), currentScope);

            case SUB:
                return (Integer) eval(t.kinder.get(0), currentScope) -
                        (Integer) eval(t.kinder.get(1), currentScope);

            case ASSIGN:
                String varName = t.kinder.get(0).value;
                Object value = eval(t.kinder.get(1), currentScope);

                Symbol varSymbol = currentScope.resolve(varName);

                if (varSymbol != null && varSymbol.isConst) {
                    throw new RuntimeException("Cannot reassign constant: " + varName);
                }

                if (varSymbol == null) {
                    SymbolType type = determineType(value);
                    varSymbol = new SymbolVariable(varName, type, false);
                    currentScope.bind(varSymbol);
                }

                if (varSymbol instanceof SymbolVariable) {
                    ((SymbolVariable) varSymbol).value = value;
                } else if (varSymbol instanceof SymbolRefVariable) {
                    ((SymbolRefVariable) varSymbol).referencSymbol = (Symbol) value;
                } else {
                    throw new RuntimeException("Unsupported symbol type for assignment: " + varSymbol.getClass());
                }

                return null;

            case ID:
                Symbol symbol = currentScope.resolve(t.value);
                if (symbol == null) {
                    throw new RuntimeException("Undefined variable: " + t.value);
                }

                if (symbol instanceof SymbolVariable) {
                    return ((SymbolVariable) symbol).value;
                } else {
                    throw new RuntimeException("Variable is not of type SymbolVariable: " + t.value);
                }

            case PRINT_INT:
            case PRINT_CHAR:
            case PRINT_BOOL:
                System.out.println(eval(t.kinder.get(0), currentScope));
                break;

            case IF_BLOCK:
                boolean condition = (Boolean) eval(t.kinder.get(0), currentScope);
                if (condition) {
                    eval(t.kinder.get(1), currentScope); // Execute 'then' block
                } else if (t.kinder.size() > 2) {
                    eval(t.kinder.get(2), currentScope); // Execute 'else' block
                }
                break;

            case WHILE_BLOCK:
                while ((Boolean) eval(t.kinder.get(0), currentScope)) {
                    eval(t.kinder.get(1), currentScope);
                }
                break;

            case FUNCTION_DEF:
                String funcName = t.kinder.get(0).value;
                String returnType = t.kinder.get(1).value;
                List<String> paramTypes = extractParamTypes(t.kinder.get(2));
                AST functionAST = t.kinder.get(3);

                SymbolFunction funcSymbol = new SymbolFunction(funcName, returnType, currentScope, functionAST, true, paramTypes);
                currentScope.bind(funcSymbol);
                break;

            case FUNCTION_CALL:
                String callName = t.value;
                Symbol funcSymbol2 = currentScope.resolve(callName);

                if (funcSymbol2 == null || !(funcSymbol2 instanceof SymbolFunction)) {
                    throw new RuntimeException("Undefined function: " + callName);
                }

                SymbolFunction func = (SymbolFunction) funcSymbol2;
                Scope funcScope = new Scope(globalScope);

                AST paramList = func.functionAST.kinder.get(1);
                AST argList = t.kinder.get(0);

                if (paramList.kinder.size() != argList.kinder.size()) {
                    throw new RuntimeException("Argument count mismatch for function: " + callName);
                }

                for (int i = 0; i < paramList.kinder.size(); i++) {
                    String paramName = paramList.kinder.get(i).value;
                    Object argValue = eval(argList.kinder.get(i), currentScope);

                    String paramType = paramList.kinder.get(i).value;
                    SymbolType symbolType = new SymbolType(paramType);

                    funcScope.bind(new Symbol(paramName, symbolType, funcScope, false));
                }

                return eval(func.functionAST.kinder.get(2), funcScope);

            case RETURN:
                return eval(t.kinder.get(0), currentScope);

            case CLASSDEF:
                String className = t.value;
                SymbolType classType = new SymbolType("CLASS");
                currentScope.bind(new Symbol(className, classType, currentScope, false));
                break;

            case CLASS:
                String instanceName = t.value;
                String typeName = t.kinder.get(0).value;

                Symbol classSymbol = currentScope.resolve(typeName);
                if (classSymbol == null || !(classSymbol instanceof Clazz)) {
                    throw new RuntimeException("Undefined class: " + typeName);
                }

                Clazz classDef = (Clazz) classSymbol;
                Scope classScope = new Scope(currentScope);

                for (Symbol memberSymbol : classDef.scope.scope.values()) {
                    if (memberSymbol instanceof SymbolVariable) {
                        Object memberValue = ((SymbolVariable) memberSymbol).value;
                    } else if (memberSymbol instanceof SymbolFunction) {
                        Object memberValue = ((SymbolFunction) memberSymbol).value;
                    }
                }

                currentScope.bind(new Symbol(instanceName, new SymbolType("CLASS"), classScope, false));
                break;

            default:
                System.out.println("Error....");
        }
        return null;
    }
}

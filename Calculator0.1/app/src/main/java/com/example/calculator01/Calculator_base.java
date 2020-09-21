package com.example.calculator01;

import java.util.Stack;


public class Calculator_base {
    /*存储数字栈*/
    private Stack<Float> numberStack = null;
    /*存储符号栈*/
    private Stack<Character> markStack = null;

    public float caculate(String numStr){
        numberStack = new Stack<Float>();
        markStack = new Stack<Character>();

        StringBuffer buf = new StringBuffer();
        for(int i = 0;i<numStr.length();i++){
            char ch = numStr.charAt(i);
            if(isNumber(ch)){
                buf.append(ch);
            }else{
                String bufStr = buf.toString();
                if(!bufStr.isEmpty()){
                    float num = Float.parseFloat(bufStr);
                    numberStack.push(num);
                    buf = new StringBuffer();
                }
                while (!compare_first(ch) && !markStack.empty()) {
                    float b = numberStack.pop();
                    float a = numberStack.pop();
                    switch((char) markStack.pop()){
                        case '+':
                            numberStack.push(a+b);
                            break;
                        case '-':
                            numberStack.push(a-b);
                            break;
                        case 'X':
                            numberStack.push(a*b);
                            break;
                        case '/':
                            numberStack.push(a/b);
                            break;
                        default:
                            break;
                    }
                }
                if(ch != '='){
                    markStack.push(new Character(ch));
                    if(ch == ')'){
                        markStack.pop();
                        markStack.pop();
                    }
                }
            }
        }
        return numberStack.pop();
    }

    private boolean isNumber(char ch) {
        if(ch >= '0' && ch <= '9' || ch == '.')
            return true;
        return false;
    }

    private boolean compare_first(char ch) {
        if(markStack.empty()){
            return true;
        }
        char top = markStack.peek();
        if(top == '('){
            return true;
        }
        switch (ch){
            case '(':
                return true;
            case 'X':{
                if(top == '+' || top == '-')
                    return true;
                else
                    return false;
            }
            case '/':{
                if(top == '+' || top == '-')
                    return true;
                else
                    return false;
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')':
                return false;
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }
}

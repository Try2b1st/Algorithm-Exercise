package test;

import java.util.Scanner;

public class x {
    public static void main(String[] args) {
        double a;
        System.out.println("输入a的值:");
        Scanner input = new Scanner(System.in);
        a=input.nextDouble();
        if (a == 0){
            System.out.println("输入的不是一元二次次方程");
            System.exit(1);
        }
        System.out.println("输入b的值:");
        Scanner input1 = new Scanner(System.in);
        double b=input1.nextDouble();
        System.out.println("输入c的值:");
        Scanner input2 = new Scanner(System.in);
        double c=input2.nextDouble();
        double d=b*b-4*a*c;		//根据b^2-4ac判断方程可解性
        if(d<0)
            System.out.println("方程无解");
        else if(d==0)
            System.out.println("方程有一个解:"+ -b/(2*a));
        else
            System.out.println("方程有两个解："+ (-b+Math.sqrt(d))/(2*a) +"和"+ (-b-Math.sqrt(d))/(2*a));//Math.sqrt()用来开平方
    }
}

namespace ConsoleApp;
using System;

public class Variable
{
    // 글로벌 기본값 0
    private int globalVar;
    private const int MAX = 1024;

    public void Method1()
    {
        // 로컬변수
        int localVar;

        // 아래 할당이 없으면 에러 발생
        localVar = 100;

        Console.WriteLine(globalVar);
        Console.WriteLine(localVar);
    }
}

class Program
{
    // 모든 프로그램에는 Main()이 있어야 함.
    static void Main(string[] args)
    {
        // 테스트
        var obj = new Variable();
        obj.Method1();
        ExString.Print();
        ExStringBuilder.Print();
        ExEnumPrint.Print();
        ExEnumPrint2.Print();
        ExIfFor.PrintIf();
        ExIfFor.PrintSwitch();
        ExIfFor.PrintFor();
        ExIfFor.PrintForEach();
        ExYield.YieldPrint();
        ExCollection.PrintArrayList();
        ExCollection.PrintArrayList();
        ExCollection.PrintQueue();
        ExCollection.PrintStack();
        ExCollection.PrintHashTable();
        ExEvent.PrintEvent();
    }
}
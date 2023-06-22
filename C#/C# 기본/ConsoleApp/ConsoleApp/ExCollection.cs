using System.Collections;

namespace ConsoleApp;

public class ExCollection
{
    public static void PrintArrayList()
    {
        var arrayList = new ArrayList();
        arrayList.Add(1);
        arrayList.Add("Hello");
        arrayList.Add(3.4);
        arrayList.Add(true);

        foreach (var variable in arrayList)
        {
            Console.WriteLine(variable);
        }

        arrayList.Remove("Hello");
        
        foreach (var variable in arrayList)
        {
            Console.WriteLine(variable);
        }
    }

    public static void PrintQueue()
    {
        var queue = new Queue();
        queue.Enqueue(1);
        queue.Enqueue(2);
        queue.Enqueue(3);

        while (queue.Count > 0)
        {
            Console.WriteLine(queue.Dequeue());
        }
    }

    public static void PrintStack()
    {
        var stack = new Stack();
        stack.Push(1);
        stack.Push(2);
        stack.Push(3);
        
        while (stack.Count > 0)
        {
            Console.WriteLine(stack.Pop());
        }
    }
    
    public static void PrintHashTable()
    {
        var hashtable = new Hashtable
        {
            ["apple"] = "사과",
            ["banana"] = "바나나",
            ["orange"] = "오렌지"
        };


        Console.WriteLine(hashtable["apple"]);
        Console.WriteLine(hashtable["banana"]);
        Console.WriteLine(hashtable["orange"]);
    }
}
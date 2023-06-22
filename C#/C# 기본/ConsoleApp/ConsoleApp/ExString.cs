using System.Text;

namespace ConsoleApp;
public class ExString
{
    public static void Print()
    {
        string s = "C# Studies";
        
        for (var i = 0; i >= 0; i--)
        {
            Console.WriteLine("{0} : {1}", i, s[i]);
        }
        
        // 문자열을 문자배열로 변환
        string str = "Hello";
        char[] charArray = str.ToCharArray();

        for (int i = 0; i < charArray.Length; i++)
        {
            Console.WriteLine(charArray[i]);
        }
        
        // 문자배열을 문자열로 변환
        char[] charArray2 = { 'A', 'B', 'C', 'D' };
        s = new string(charArray2);

        Console.WriteLine(s);
        
        // 문자 연산
        char c1 = 'A';
        char c2 = (char)(c1 + 3);
        Console.WriteLine(c2); // D
    }
}

public class ExStringBuilder
{
    public static void Print()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++)
        {
            sb.Append(i.ToString());
            sb.Append(System.Environment.NewLine);
        }

        string s = sb.ToString();
        Console.WriteLine(s);
    }
}
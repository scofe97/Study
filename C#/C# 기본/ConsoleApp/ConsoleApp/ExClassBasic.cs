namespace ConsoleApp;

public class ExClassBasic
{
    private string name;
    private int age;

    public event EventHandler NameChanged;

    public ExClassBasic()
    {
        name = string.Empty;
        age = -1;
    }

    public string Name
    {
        get => this.name;
        set
        {
            if (this.name == value) return;
            
            this.name = value;
            if (NameChanged != null)
            {
                NameChanged(this, EventArgs.Empty);
            }
        }
    }

    public int Age
    {
        get { return this.age;}
        set { this.age = value;}
    }

    public string GetCustomerDate()
    {
        string data = string.Format("Name: {0} (Age: {1}", this.name, this.age);
        return data;
    }
}
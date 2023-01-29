package 자바공부.스트림;

class Worker implements Comparable<Worker> {
    private String name;
    private int age;

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Worker o){
        return Integer.compare(this.age, o.age);
    }
}

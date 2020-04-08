import java.util.Arrays;

public class skill {
    public static void main(String[] args) {
        //复制数组
        int[] arr = {1,4,6,3,5};
        int[] newArr = Arrays.copyOf(arr,arr.length+1);  //复制长度为arr.length+1的数组，原数组不够后面补0
        printArr(newArr);
        newArr = Arrays.copyOfRange(arr,2,arr.length-1); //复制索引[from,to)的数组
        printArr(newArr);
    }
    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    /*
    * 利用lambda表达式 对多个属性进行排序，
    * */
    /*public static class Person{
        private int age ;
        private String name ;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void change(Person[] persons){
        Arrays.sort(persons,((t0, t1) -> {
            int cmp = Integer.compare(t0.getAge(),t1.getAge());
            if(cmp == 0){
                cmp = t1.getName().compareTo(t0.getName());
            }
            return cmp;
        }));

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].getAge() + " : " + persons[i].name);
        }
    }
    public static void main(String[] args) {
        Person person1 = new Person(10,"ac");
        Person person2 = new Person(10,"aa");
        Person person3 = new Person(10,"ab");
        Person person4 = new Person(5,"ba");
        Person person5 = new Person(5,"ca");
        Person person6 = new Person(8,"ba");
        Person person7 = new Person(8,"aa");
        Person[] persons = new Person[]{person1,person2,person3,person4,person5,person6,person7};
        change(persons);
    }*/
}

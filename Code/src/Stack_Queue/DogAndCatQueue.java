package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;

public class DogAndCatQueue {
    /*
    * 实现一种狗猫队列的结构，
    * 要求如下： 用户可以调用add方法将cat类或dog类的实例放入队列中；
    *           用户可以调用pollAll方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
    *           用户可以调用pollDog方法，将队列中dog类的实例按照进队列的先后顺序依次弹出；
    *           用户可以调用pollCat方法，将队列中cat类的实例按照进队列的先后顺序依次弹出；
    *           用户可以调用isEmpty方法，检查队列中是否还有dog或cat的实例；
    *           用户可以调用isDogEmpty方法，检查队列中是否有dog类的实例；
    *           用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
    *
    * 实现：用两个队列分别为cat和dog队列，除过记录实体外，还要记录是第几个进来的，
    * */
    public static class Pet{
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static class Dog extends Pet{
        public Dog() {
            super("dog");
        }
    }
    public static class Cat extends Pet{
        public Cat() {
            super("cat");
        }
    }
    public static class PetEnterQueue{
        private Pet pet;
        private long count;
        public PetEnterQueue(Pet pet,long count){
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }
        public String getEnterPetType(){
            return pet.getType();
        }
    }

    public static class DogCatQueue{
        private Queue<PetEnterQueue> dogQueue;
        private Queue<PetEnterQueue> catQueue;
        private long count;
        public DogCatQueue(){
            this.dogQueue = new LinkedList<PetEnterQueue>();
            this.catQueue = new LinkedList<PetEnterQueue>();
            this.count = 0;
        }
        public void add(Pet pet) {
            if(pet.getType().equals("dog")){
                this.dogQueue.add(new PetEnterQueue(pet,this.count++));
            }else if(pet.getType().equals("cat")){
                this.catQueue.add(new PetEnterQueue(pet,this.count++));
            }else {
                throw new RuntimeException("err,not dog or cat");
            }
        }
        public PetEnterQueue pollAll(){
            if (!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
                if (this.dogQueue.peek().getCount() < this.catQueue.peek().getCount()) {
                    return this.dogQueue.poll();
                } else {
                    return this.catQueue.poll();
                }
            } else if (!this.dogQueue.isEmpty()) {
                return this.dogQueue.poll();
            } else if (!this.catQueue.isEmpty()) {
                return this.catQueue.poll();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }

        public PetEnterQueue pollDog() {
            if (!this.isDogQueueEmpty()) {
                return this.dogQueue.poll();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQueue.poll().getPet();
            } else
                throw new RuntimeException("Cat queue is empty!");
        }

        public boolean isEmpty() {
            return this.dogQueue.isEmpty() && this.catQueue.isEmpty();
        }

        public boolean isDogQueueEmpty() {
            return this.dogQueue.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatQueue test = new DogCatQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        while (!test.isDogQueueEmpty()) {
            PetEnterQueue petEnterQueue= test.pollDog();
            System.out.println(petEnterQueue.getPet().getType()+":"+petEnterQueue.getCount());
        }
        test.add(dog1);
        test.add(dog2);
        test.add(dog3);
        test.add(cat1);
        test.add(cat2);
        test.add(cat3);
        while (!test.isEmpty()) {
            PetEnterQueue petEnterQueue= test.pollAll();
            System.out.println(petEnterQueue.getPet().getType()+":"+petEnterQueue.getCount());
        }
    }
}

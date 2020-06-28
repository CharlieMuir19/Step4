public class Main {

    public static void main(String[] args){
        Batch b = new Batch();
        Fruit fruit = new Fruit();
        InputHandler inputHandler = new InputHandler();
        Processor processor = new Processor(b, fruit, inputHandler);
        processor.addNewBatch();
    }
}

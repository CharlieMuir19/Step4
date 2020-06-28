import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BatchTest {
    Batch b = new Batch();
    Fruit f = new Fruit();
    InputHandler inpH = new InputHandler();
    JSONProcessor jP = new JSONProcessor(inpH);

    @Test
    public void BatchTestMinimum(){
        b.setDate("08062020");
        b.setFarmCode("000");
        f.setSTFruit();
        b.setFruitCode(f.returnFruitCode());
        b.setFruit(f.returnFruit());
        b.setBatchWeight(1);
        assertEquals("08062020", b.getDate());
        assertEquals("000", b.getFarmCode());
        assertEquals("Strawberry", b.getFruit());
        assertEquals("ST", b.getFruitCode());
        assertEquals(1, b.getBatchWeight());
        System.out.print("Date is " + b.getDate());
    }

    @Test
    public void BatchTestMaximum(){
        b.setDate("08062020");
        b.setFarmCode("999");
        f.setRAFruit();
        b.setFruitCode(f.returnFruitCode());
        b.setFruit(f.returnFruit());
        b.setBatchWeight(100);
        assertEquals("08062020", b.getDate());
        assertEquals("999", b.getFarmCode());
        assertEquals("Raspberry", b.getFruit());
        assertEquals("RA", b.getFruitCode());
        assertEquals(100, b.getBatchWeight());
    }

    @Test
    public void BatchTestBL(){
        b.setDate("08062020");
        b.setFarmCode("499");
        f.setBLFruit();
        b.setFruitCode(f.returnFruitCode());
        b.setFruit(f.returnFruit());
        b.setBatchWeight(50);
        assertEquals("08062020", b.getDate());
        assertEquals("499", b.getFarmCode());
        assertEquals("Blackberry", b.getFruit());
        assertEquals("BL", b.getFruitCode());
        assertEquals(50, b.getBatchWeight());
    }

    @Test
    public void BatchTestGO(){
        b.setDate("08062020");
        b.setFarmCode("50");
        f.setGOFruit();
        b.setFruitCode(f.returnFruitCode());
        b.setFruit(f.returnFruit());
        b.setBatchWeight(10);
        assertEquals("08062020", b.getDate());
        assertEquals("50", b.getFarmCode());
        assertEquals("Gooseberry", b.getFruit());
        assertEquals("GO", b.getFruitCode());
        assertEquals(10, b.getBatchWeight());
    }

    @Test
    public void BatchTestTooHigh(){
        b.setFarmCode("1000");
//        b.setBatchWeight(aa);
        assertEquals("\nCannot Process Incorrect Farm Code - TOO HIGH\nEnter Farm Number (000 to 999)", "1000", b.getFarmCode());
        assertEquals("Cannot Process Incorrect Farm Code - TOO HIGH\nEnter Farm Number (000 to 999)", "aa", b.getBatchWeight());
    }

    @Test
    public void BatchTestChar(){
        b.setFarmCode("aaa");
        b.setBatchWeight(101);
        assertEquals("NO VALID INPUT Enter Farm Number (000 to 999)", "aaa", b.getFarmCode());
        assertEquals("Cannot Process Incorrect Farm Code - TOO HIGH\nEnter Farm Number (000 to 999)", 101, b.getBatchWeight());
    }

    @Test
    public void timeTest(){
        b.setDate("08062020");
        assertEquals("08062020", b.getDate());
    }

    @Test
    public void gradeDetails(){
        b.setFile("16062020-ST-000");
        b.setGradeA(60);
        b.setGradeB(32);
        b.setGradeC(6);
        b.setRejected(2);

        int total = b.getGradeA() + b.getGradeB() + b.getGradeC() + b.getRejected();
        assertEquals(total, 100);


    }

}

package test;

import java.util.Random;

/**
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 20:37
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num = -random.nextInt(240);
            System.out.println(num);
        }
    }
}

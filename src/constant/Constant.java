package constant;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 16:59
 */
public class Constant {
    private static Properties prop = new Properties();

    /**
     * 私有构造，防止被new
     */
    private Constant() {
    }

    /**
     * 静态加载properties配置文件
     */
    static {
        try {
            prop.load(Constant.class.getClassLoader().getResourceAsStream("FlyBird.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key返回int类型的属性值
     *
     * @param key key
     * @return int类型的值
     */
    private static int getProperty(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

    /**
     * 窗口的宽度
     */
    public static final int WINDOW_WIDTH = getProperty("WINDOW_WIDTH");

    /**
     * 窗口的高度
     */
    public static final int WINDOW_HEIGHT = getProperty("WINDOW_HEIGHT");
    /**
     * 图片的前缀
     */
    public static final String IMG_PRE = prop.getProperty("IMG_PRE");
    /**
     * 背景图片（白天Or黑夜）
     */
    public static final String BG = prop.getProperty("BG");
    /**
     * 重力G
     */
    public static final int G = getProperty("G");
    /**
     * 初速度
     */
    public static final int V0 = getProperty("V0");
    /**
     * 柱子距离
     */
    public static final int DISTANCE = getProperty("DISTANCE");
}

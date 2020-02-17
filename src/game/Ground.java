package game;

import util.ImageUtil;

import java.awt.*;

/**
 * 地面类
 *
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 18:31
 */
public class Ground {
    /**
     * x坐标
     */
    int x;
    /**
     * y坐标
     */
    int y;
    /**
     * 地面图片
     */
    Image img;
    /**
     * 地面的宽度
     */
    int width;
    /**
     * 地面的高度
     */
    int height;

    /**
     * 地面构造器用来构建地面对象
     */
    public Ground() {
        // 先初始化地面图片
        img = ImageUtil.get("land");
        // 获取图片的高度
        height = img.getHeight(null);
        // 获取图片的宽度
        width = img.getWidth(null);
        // 初始化X
        x = 0;
        // 初始化y
        y = 512 - height;
    }

    /**
     * 地面移动的方法
     */
    public void move() {
        if (x <= -512) {
            x = 0;
        } else {
            x -= 1;
        }
    }
}

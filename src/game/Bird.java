package game;

import constant.Constant;
import util.ImageUtil;

import java.awt.*;

/**
 * 小鸟类
 *
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 21:12
 */
public class Bird {
    /**
     * 鸟的图片
     */
    Image img;
    /**
     * 鸟的x坐标
     */
    int x;
    /**
     * 鸟的y坐标
     */
    int y;
    /**
     * 鸟的宽度
     */
    int width;
    /**
     * 鸟的高度
     */
    int height;
    /**
     * 初速度
     */
    double v0;
    /**
     * 时间间隔（往上移动的时间）
     */
    double t;
    /**
     * 距离
     */
    double s;
    /**
     * 重力
     */
    double g;

    /**
     * 构造方法，初始化鸟对象
     */
    public Bird() {
        // 初始化鸟的图片
        img = ImageUtil.getArrayList("bird").get(0);
        // 初始化鸟的x坐标
        x = 70;
        // 初始化鸟的y坐标
        y = 200;
        width = img.getWidth(null) / 3 * 2;
        height = img.getHeight(null) / 3 * 2;
        // 初始化速度
        v0 = 5;
        // 时间
        t = 0.2;
        // 距离
        s = 0;
        // 重力
        g = Constant.G;
    }

    /**
     * 鸟的图片下标
     */
    int index = 0;

    /**
     * 鸟的翅膀动起来
     */

    public void changeImage() {
        img = ImageUtil.getArrayList("bird").get(index % ImageUtil.getArrayList("bird").size());
        index++;
    }

    /**
     * 小鸟的落体运动
     */
    public void moveDown() {
        // 计算小鸟上抛的距离
        s = v0 * t;
        // 得到小鸟上抛到最高点时的y坐标
        y = (int) (y - s);
        // 计算小鸟到达最高点时的速度
        double v2 = v0 - g * t;
        // 最高点的速度就是小鸟下落时的初速度
        v0 = v2;
    }

    /**
     * 小鸟向上移动
     */
    public void moveUp() {
        v0 = Constant.V0;
    }

    /**
     * 小鸟与顶部发生碰撞
     */
    public boolean hit() {
        if (0 >= y || 339 <= y) {
            // 撞到了
            return true;
        } else {
            // 没撞到
            return false;
        }
    }

    /**
     * 小鸟与柱子碰撞
     */
    public boolean hit(Column column) {
        if (this.x > column.x - this.width + 5 && this.x < column.x + column.width - 5) {
            if (y > column.height / 2 + column.y - column.gap / 2 - 8 && y < column.height / 2 + column.y + column.gap / 2 - this.height + 8) {
                // 通过了
                return false;
            } else {
                // 撞到了
                return true;
            }
        }
        return false;
    }
}

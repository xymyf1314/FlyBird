package game;

import util.ImageUtil;

import java.awt.*;

/**
 * 柱子类
 *
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 19:32
 */
public class Column {
    /**
     * 柱子的图片
     */
    Image img;
    /**
     * 柱子的X坐标
     */
    int x;
    /**
     * 柱子的Y坐标
     */
    int y;
    /**
     * 柱子的宽度
     */
    int width;
    /**
     * 柱子的高度
     */
    int height;
    /**
     * 柱子间的间距
     */
    int distance;
    /**
     * 中介者
     */
    GamePanel gamePanel;
    /**
     * 安全间隙
     */
    int gap;

    /**
     * 构造器初始化柱子对象
     */
    public Column(int x, int y, int distance, GamePanel gamePanel) {
        // 初始化柱子图片
        img = ImageUtil.get("column");
        // 获取图片的宽度
        width = img.getWidth(null) / 3;
        // 获取图片的高度
        height = img.getHeight(null) / 2;
        // 初始化柱子间距
        this.distance = distance;
        // 设置x坐标
        this.x = x;
        // 设置y坐标
        this.y = y;
        this.gamePanel = gamePanel;
        gap = 75;
    }

    /**
     * 柱子的移动方法
     */
    public void move() {
        if (x <= -this.width) {
            gamePanel.columns.remove(this);
        } else {
            x -= 1;
        }
    }
}

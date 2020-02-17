package game;

import constant.Constant;
import util.ImageUtil;

import javax.swing.*;
/**
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 16:14
 */

/**
 * 游戏窗体类
 *
 * @author fan
 */
public class GameFrame extends JFrame {
    /**
     * 构造器用来做初始化窗口操作
     */
    public GameFrame() {
        // 设置尺寸（宽度，高度）
        this.setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        // 设置窗体居中显示
        this.setLocationRelativeTo(null);
        // 设置窗口不可以变大缩小
        this.setResizable(false);
        // 设置窗口关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置标题
        this.setTitle("FlyBird——fan");
        // 设置logo图标
        this.setIconImage(ImageUtil.get("icon"));
    }

    /**
     * 程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个窗体对象
        GameFrame frame = new GameFrame();
        // 创建一个画板的对象
        GamePanel gamePanel = new GamePanel();
        // 将画板装入窗体内
        frame.add(gamePanel);
        // 显示窗体
        frame.setVisible(true);
    }
}

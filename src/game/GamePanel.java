package game;

import constant.Constant;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * 画板类
 *
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 17:52
 */
public class GamePanel extends JPanel {
    /**
     * 声明一个用于存放地面的变量
     */
    Ground ground;
    /**
     * 声明一个用于存放柱子的集合
     */
    ArrayList<Column> columns = new ArrayList<>();
    /**
     * 用于生成柱子的随机数
     */
    Random r = new Random();
    /**
     * 声明一个鸟对象
     */
    Bird bird;
    /**
     * 游戏准备状态标志
     */
    boolean start;
    /**
     * 游戏结束标志
     */
    boolean gameOver;
    /**
     * 分数
     */
    int score;

    /**
     * 构造器
     */
    public GamePanel() {
        // 设置背景色
        this.setBackground(Color.PINK);
        // 初始化地面对象
        ground = new Ground();
        //初始化柱子对象
        this.generateColumn();
        // 初始化鸟对象
        bird = new Bird();
        // 初始化游戏状态(false为准备，true为开始)
        this.start = false;
        // 初始化游戏结束状态(false为没有结束，true为结束了)
        this.gameOver = false;
        // 初始化分数
        this.score = 0;
        // 鼠标监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (false == start) {
                    // 改变游戏状态
                    start = true;
                    start();
                    // 初始化分数
                    score = 0;
                } else if (gameOver) {
                    // 游戏回到准备状态
                    //初始化柱子对象
                    columns.clear();
                    generateColumn();
                    // 初始化鸟对象
                    bird = new Bird();
                    // 初始化游戏状态(false为准备，true为开始)
                    start = false;
                    // 初始化游戏结束状态(false为没有结束，true为结束了)
                    gameOver = false;
                    repaint();
                } else {
                    // 小鸟上抛
                    bird.moveUp();
                }
            }
        });
    }

    /**
     * 游戏开始的方法
     */
    public void start() {
        // 启动游戏运行线程
        Mythread mythread = new Mythread();
        // 将线程所有执行的任务装入到线程对象中
        Thread t = new Thread(mythread);
        // 将线程纳入线程调度
        t.start();
    }

    /**
     * 用来向画板上绘制内容的方法
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制背景图片
        g.drawImage(ImageUtil.get(Constant.BG), 0, 0, null);
        // 绘制柱子的图片
        for (int i = 0; i < columns.size(); i++) {
            g.drawImage(columns.get(i).img, columns.get(i).x, columns.get(i).y, columns.get(i).width, columns.get(i).height, null);
        }
        //生成新的柱子
        this.generateColumn();
        // 绘制地面的图片
        g.drawImage(ground.img, ground.x, ground.y, null);
        // 绘制出鸟对象
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        // 绘制准备状态的图片
        if (!start) {
            g.drawImage(ImageUtil.get("start"), 0, 0, ImageUtil.get("start").getWidth(null) / 3 * 2, ImageUtil.get("start").getHeight(null) / 3 * 2, null);
        }
        // 绘制游戏结束图片
        if (gameOver) {
            g.drawImage(ImageUtil.get("gameOver"), Constant.WINDOW_WIDTH / 2 - ImageUtil.get("gameOver").getWidth(null) / 2, Constant.WINDOW_HEIGHT / 2 - ImageUtil.get("gameOver").getHeight(null), null);
        }
        // 绘制分数
        Color color = g.getColor();
        Font font = g.getFont();
        g.setColor(Color.PINK);
        g.setFont(new Font("宋体", 40, 24));
        g.drawString("分数：" + score, 25, 30);

    }

    /**
     * 生成柱子的方法
     */
    public void generateColumn() {
        // 生成新的柱子
        if (columns.size() == 0) {
            for (int i = 0; i < 10; i++) {
                columns.add(new Column(288 + 120 * i, -r.nextInt(240), Constant.DISTANCE, this));
            }
        }
    }

    class Mythread implements Runnable {
        // 把该线程需要干的活放在run方法中
        @Override
        public void run() {
            while (true) {
                // 让地面移动起来
                ground.move();
                // 柱子移动
                for (int i = 0; i < columns.size(); i++) {
                    columns.get(i).move();
                }
                // 小鸟飞
                bird.changeImage();
                // 小鸟做落体运动
                bird.moveDown();
                // 检测小鸟是否与边界发生碰撞
                boolean hit = bird.hit();
                // 若发生碰撞则游戏结束
                if (true == hit) {
                    gameOver = true;
                    // 窗体类的全部静止
                    return;
                }
                // 小鸟与柱子的碰撞检测
                for (int i = 0; i < columns.size(); i++) {
                    if (bird.hit(columns.get(i))) {
                        gameOver = true;
                        return;
                    }
                }
                // 计算分数
                for (int i = 0; i < columns.size(); i++) {
                    if (bird.x == columns.get(i).x + columns.get(i).width) {
                        score++;
                    }
                }
                try {
                    // 每移动一次休息一秒钟
                    Thread.sleep(20);
                    // 刷新
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

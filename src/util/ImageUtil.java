package util;

import constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片工具类
 *
 * @author Fan
 * @version 1.0
 * @date 2020/2/13 16:46
 */
public final class ImageUtil {
    /**
     * 项目中存放所有图片的键值对
     */
    private static Map<String, Image> imgs = new HashMap<>();
    /**
     * 存放多张图片的键值对集合
     */
    private static Map<String, ArrayList<Image>> imgs2 = new HashMap<>();

    /**
     * 静态加载
     */
    static {
        // icon 图标
        imgs.put("icon", ImageUtil.getImage("bird0"));
        // 背景图片（白天和晚上）
        imgs.put("bg_day", ImageUtil.getImage("bg_day"));
        imgs.put("bg_night", ImageUtil.getImage("bg_night"));
        // 地面图片
        imgs.put("land", ImageUtil.getImage("land"));
        // 柱子图片（朝上和朝下）
        imgs.put("column", ImageUtil.getImage("column"));
        imgs.put("pipe_up", ImageUtil.getImage("pipe_up"));
        imgs.put("pipe_down", ImageUtil.getImage("pipe_down"));
        // 准备状态的图片
        imgs.put("start", ImageUtil.getImage("start"));
        // 结束状态的图片
        imgs.put("gameOver", ImageUtil.getImage("text_game_over"));
        /**
         * 鸟儿飞的动图
         */
        imgs2.put("bird", new ArrayList<>());
        for (int i = 0; i < 6; i++) {
            imgs2.get("bird").add(ImageUtil.getImage("bird" + i));
        }
    }

    /**
     * 根据图片名加载图片的方法
     *
     * @param path 图片名称
     * @return Image对象
     */
    private static Image getImage(String path) {
        URL u = ImageUtil.class.getClassLoader().getResource(Constant.IMG_PRE + path + ".png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 从Map中取出图片对象
     *
     * @param key key
     * @return 图片对象
     */
    public static Image get(String key) {
        return imgs.get(key);
    }

    /**
     * 从Map中取出图片对象
     *
     * @param key key
     * @return 图片集合对象
     */
    public static ArrayList<Image> getArrayList(String key) {
        return imgs2.get(key);
    }
}

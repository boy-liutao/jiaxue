package com.xuema.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
    
    public static void main(String[] args) throws IOException {

    }
    
    public static boolean resize(String src, String tgt, int maxWidth, int maxHeight) throws IOException{
        File file = new File(src);// 读入文件
        Image img = ImageIO.read(file); 
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        if (imgW <= maxWidth)
            return false;
        if (imgH <= maxHeight)
            return false;
        resizeFix(img.getWidth(null), img.getHeight(null), img, tgt, maxWidth, maxHeight);
        return true;
    }
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public static void resizeFix(int width, int height, Image src, String tgt, int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(width, height, src, tgt, w);  
        } else {  
            resizeByHeight(width, height, src, tgt, h);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public static void resizeByWidth(int width, int height, Image src, String tgt, int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(src, tgt, w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public static void resizeByHeight(int width, int height, Image src, String tgt, int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(src, tgt, w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    @SuppressWarnings("restriction")
	public static void resize(Image img, String tgt, int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(tgt);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  
    
    
    /**  
     * @param args  
     * @throws IOException 
     * @throws ImageFormatException 
     */  
    public static void addWater(String src, String water, String target) throws ImageFormatException, IOException {   
        //1.jpg是你的 主图片的路径
        InputStream is = new FileInputStream(src);
        
        
        //通过JPEG图象流创建JPEG数据流解码器
        
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        
        
        //解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        //得到画笔对象
        Graphics2D  g = buffImg.createGraphics();
        
        //创建你要附加的图象。
        //2.jpg是你的小图片的路径
        ImageIcon imgIcon = new ImageIcon(water); 
        
        //得到Image对象。
        Image img = imgIcon.getImage();
        
        float alpha = 0.3f; // 透明度 
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
                alpha)); 
        
        //将小图片绘到大图片上。
        //5,300 .表示你的小图片在大图片上的位置。
        g.drawImage(img,0,0,null);
        
        
        
        //设置颜色。
        g.setColor(Color.BLACK);
        
        
        //最后一个参数用来设置字体的大小
        Font f = new Font("宋体",Font.BOLD,30);
        
        g.setFont(f);
        
        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
//        g.drawString("默哀555555。。。。。。。",10,30);
        
        g.dispose();
        
        
        
        OutputStream os = new FileOutputStream(target);
        
        //创键编码器，用于编码内存中的图象数据。
        
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        
        
        is.close();
        os.close();
        
  
    }   
    
    public static void addWater2(String srcImgPath, String iconPath, String targerPath, Integer degree) throws ImageFormatException, IOException {  
        addWater2(srcImgPath, iconPath, targerPath, degree, 0.2f);
    }    
    
    
    public static void addWater2(String srcImgPath, String iconPath, String targerPath, Integer degree, float alpha) throws ImageFormatException, IOException {  
        OutputStream os = null;     
        try {     
            Image srcImg = ImageIO.read(new File(srcImgPath));   
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),     
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
            // 得到画笔对象     
            // Graphics g= buffImg.getGraphics();     
            Graphics2D g = buffImg.createGraphics();     
    
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg     
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);     
    
            if (null != degree) {     
                // 设置水印旋转     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg     
                                .getHeight() / 2);     
            }     
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度    
            ImageIcon imgIcon = new ImageIcon(iconPath);     
            // 得到Image对象。     
            Image img = imgIcon.getImage();     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,     
                    alpha));     
            // 表示水印图片的位置     
            g.drawImage(img, 0, 0, null);     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));     
            g.dispose();     
            os = new FileOutputStream(targerPath);     
            // 生成图片     
            ImageIO.write(buffImg, "JPG", os);     
        } catch (Exception e) {     
            throw e;
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }    
}

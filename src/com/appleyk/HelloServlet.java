package com.appleyk;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * <p>response 输出图片【随机验证码】</p>
 * <p>Java 1.8 API 中文在线地址：https://blog.fondme.cn/apidoc/jdk-1.8-google/</p>
 * @Description
 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 11:46 2018-10-10
 */
public class HelloServlet extends HttpServlet {

    /**
     * 验证码【框】宽度  -- 小写快速切换到大写  【Ctrl+Shift+U】
     */
    private static  final  int WIDTH = 120;

    /**
     * 验证码【框】高度
     */
    private static  final  int HEIGHT = 30;

    /**
     * 起点【顶点】
     */
    private static  final  int VERTEX = 0;

    /**
     * 【随机】干扰线的个数
     */
    private static  final  int RANDOM_LINE_SIZE = 5;

    /**
     * 【随机】验证码【字符】的个数
     */
    private static  final  int RANDOM_CHARACTER_SIZE = 4;

    /**
     * 验证码【字符】的位置【X方向】
     */
    private static  final  int CHARACTER_POSITION_X  = 20;


    /**
     * 验证码【字符】的位置【Y方向】
     */
    private static  final  int CHARACTER_POSITION_Y  = 21;

    /**
     * 验证码【字符】旋转的最大角度
     */
    private static  final  int CHARACTER_LARGE_ANGLE_OF_ROTATION = 30;


    /**
     * 常用汉字UNICODE编码
     */
    private static  final  String COMMON_CHINESE_CHARACTER = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";


    /**
     * 随机对象
     */
    private Random random;


    /**
     * 初始化操作
     */
    @Override
    public void init(){

        random = new Random();
        System.out.println("Servlet初始化完成......");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 创建一个图片访问缓存器【类型：具有8位RGBA颜色成分的整数像素的图像。】
        BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT ,BufferedImage.TYPE_INT_RGB );

        // 拿到画刷【图片上下文对象】
        Graphics g = bufferedImage.getGraphics();

        // 1、设置背景色
        setBackGround(g);

        // 2、设置边框【描边框】
        drawBorder(g);

        // 3、画随机线【干扰线，4~5条即可，多了，反而真成了“干扰线”了】
        drawRandomLines(g);

        // 4、万事俱备，只欠验证码【绘制内容】
        drawRandomText((Graphics2D) g);

        // 5、设置响应消息的类型
        response.setContentType("image/jpeg");

        // 6、设置页面不缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 7、将缓存图片写进response输出流
        ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());

    }
    /**
     * 设置验证码【框】的背景填充色
     * @param g
     */
    private void setBackGround(Graphics g) {

        // 设置画刷的颜色 -- 白色
        g.setColor(Color.WHITE);
        // 填充矩形框
        g.fillRect(VERTEX, VERTEX, WIDTH, HEIGHT);
    }


    /**
     * 绘制验证码【框】的边框
     * @param g
     */
    private void drawBorder(Graphics g) {

        // 设置画刷的颜色 -- 蓝色
        g.setColor(Color.BLUE);
        // 注意描边，宽度和高度一定要 减 1，否则有一半的边画到外面去了，看不见效果
        g.drawRect(VERTEX,VERTEX ,WIDTH -1,HEIGHT-1 );
    }


    /**
     * 绘制干扰线【防止高级、恶意的程序demo识别验证码图片，从而造成轰炸式的僵尸注册或其他...】
     * @param g
     */
    private void drawRandomLines(Graphics g) {

        // 依然先设置画刷的颜色 -- 绿色
        g.setColor(Color.GREEN);

        // 拟定画五条随机线，for循环 + 随机数
        for (int i = 0; i < RANDOM_LINE_SIZE ; i++) {

            // 线 -- 由起点【x1,y1】 和 终点【x2,y2】连接而成

            // 起点的范围 -- x：0-WIDTH ， y：0-HEIGHT
            int startX = random.nextInt(WIDTH);
            int startY = random.nextInt(HEIGHT);

            // 终点的范围 -- x：0-WIDTH ， y：0-HEIGHT
            int endX = random.nextInt(WIDTH);
            int endY = random.nextInt(HEIGHT);

            // 绘制一条线
            g.drawLine(startX,startY ,endX ,endY );

        }
    }

    /**
     * 绘制内容
     * @param g
     */
    private void drawRandomText(Graphics2D g){

        // 依然上来先设置画刷的颜色 -- 醒目大红
        g.setColor(Color.RED);

        // 设置字体样式
        Font font = new Font("宋体",Font.BOLD|Font.ITALIC , 18);
        g.setFont(font);


        // 验证码采用常用的汉字，四个一组，因此需要循环四次得到4个随机汉字
        int x = 0;
        for (int i = 0; i < RANDOM_CHARACTER_SIZE; i++) {

            // 获取常用汉字字节码字符串的长度
            int len = COMMON_CHINESE_CHARACTER.length();

            // 随机取出一个字符的位置
            int randomIndex = random.nextInt(len);

            // 取出指定位置的char字符，并转成String
            String text = COMMON_CHINESE_CHARACTER.charAt(randomIndex)+"";

            // 绘制文本的时候，x方向每次++CHARACTER_POSITION_X，y方向保持不动
            x +=  CHARACTER_POSITION_X ;

            // 当前旋转多少度 -- -30 到 30 -- 对30进行求余
            int angle = random.nextInt()%CHARACTER_LARGE_ANGLE_OF_ROTATION;

            // 旋转画刷 -- 基于哪个位置进行旋转，并进行角度换算
            g.rotate(angle*Math.PI/180,x,CHARACTER_POSITION_Y);

            // 使用该图形上下文的当前字体和颜色绘制由指定字符串给出的文本。
            g.drawString(text, x,CHARACTER_POSITION_Y );

            // 记得将画刷再转回来，防止下一次再绘制文本的时候，文本转出去了
            g.rotate(-angle*Math.PI/180,x,CHARACTER_POSITION_Y);

        }
    }
}

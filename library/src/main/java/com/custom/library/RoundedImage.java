package com.custom.library;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.render.*;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.Rect;
import ohos.media.image.common.Size;

import java.io.FileDescriptor;
import java.io.InputStream;

public class RoundedImage extends Image {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00200, "Lib");

    private PixelMapHolder holder = null;
    private int minImageLength = 0;

    /**
     * 默认构造函数，用于使用默认属性集和样式创建图像实例。
     *
     * @param context 应用程序上下文
     */
    public RoundedImage(Context context) {
        super(context);
    }

    /**
     * 用于在解析XML文件后使用指定的属性集和默认样式创建图像实例的构造函数。
     *
     * @param context 应用程序上下文
     * @param attrSet 要使用的属性集
     */
    public RoundedImage(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    /**
     * 用于在解析XML文件后使用指定的属性集和指定的样式创建图像实例的构造函数。
     *
     * @param context 应用程序上下文
     * @param attrSet 要使用的属性集
     * @param styleName 要使用的样式名称
     */
    public RoundedImage(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }

    /**
     * 设置图片显示成圆形
     *
     * @param resId 资源ID
     */
    public void setPixelMapToCircleImage(int resId) {
        initVariable(getPixelMap(resId));
        createCircleImage();
    }

    /**
     * 设置图片显示成圆形
     *
     * @param fileDescriptor 图像源文件的描述符
     */
    public void setPixelMapToCircleImage(FileDescriptor fileDescriptor) {
        initVariable(getPixelMap(fileDescriptor));
        createCircleImage();
    }

    /**
     * 设置椭圆图片
     *
     * @param resId 资源ID
     */
    public void setPixelMapToOvalImage(int resId) {
        initVariable(getPixelMap(resId));
        createOvalImage();
    }

    /**
     * 设置椭圆图片
     *
     * @param fileDescriptor 图像源文件的描述符
     */
    public void setPixelMapToToOvalImage(FileDescriptor fileDescriptor) {
        initVariable(getPixelMap(fileDescriptor));
        createOvalImage();
    }

    /**
     * 图片显示圆角
     *
     * @param resId 资源ID
     * @param topLeft 左上圆角半径
     * @param topRigth 右上圆角半径
     * @param bottomRight 右下圆角半径
     * @param bottomLeft 左下圆角半径
     */
    public void setPixelMapToRoundedRect(int resId, int topLeft, int topRigth, int bottomRight, int bottomLeft) {
        setPixelMap(resId);
        setClipGravity(GRAVITY_CENTER);
        setCornerRadii(new float[]{topLeft, topLeft, topRigth, topRigth, bottomRight, bottomRight, bottomLeft, bottomLeft});
        setScaleMode(ScaleMode.CLIP_CENTER);
    }

    /**
     * 图片显示圆角
     *
     * @param fileDescriptor 图像源文件的描述符
     * @param topLeft 左上圆角半径
     * @param topRigth 右上圆角半径
     * @param bottomRight 右下圆角半径
     * @param bottomLeft 左下圆角半径
     */
    public void setPixelMapToRoundedRect(FileDescriptor fileDescriptor, int topLeft, int topRigth, int bottomRight, int bottomLeft) {
        setPixelMap(getPixelMap(fileDescriptor));
        setClipGravity(GRAVITY_CENTER);
        setCornerRadii(new float[]{topLeft, topLeft, topRigth, topRigth, bottomRight, bottomRight, bottomLeft, bottomLeft});
        setScaleMode(ScaleMode.CLIP_CENTER);
    }

    private void createCircleImage() {
        if (holder != null) {
            this.addDrawTask(new DrawTask() {
                @Override
                public void onDraw(Component component, Canvas canvas) {
                    float centerX = getWidth() / 2f;
                    float centerY = getHeight() / 2f;
                    float radius = Math.min(centerX, centerY);
                    Paint paint = new Paint();
                    Shader shader = new PixelMapShader(holder, Shader.TileMode.CLAMP_TILEMODE, Shader.TileMode.CLAMP_TILEMODE);
                    paint.setShader(shader, Paint.ShaderType.SWEEP_SHADER);
                    canvas.drawCircle(centerX, centerY, radius, paint);
                }
            });
        }
    }

    private void createOvalImage(){
        this.addDrawTask(new DrawTask() {
            @Override
            public void onDraw(Component component, Canvas canvas) {
                Paint paint = new Paint();
                Shader shader = new PixelMapShader(holder, Shader.TileMode.CLAMP_TILEMODE, Shader.TileMode.CLAMP_TILEMODE);
                paint.setShader(shader, Paint.ShaderType.SWEEP_SHADER);
                PixelMap pixelMap = holder.getPixelMap();
                int min = Math.min(pixelMap.getImageInfo().size.width, pixelMap.getImageInfo().size.height);
                int radiusX = Math.min(min, minImageLength);
                float halfRadiusX = radiusX / 2f;
                float quarterRadiusX = radiusX / 4f;
                float left = getWidth() / 2f - halfRadiusX;
                float right = getWidth() / 2f + halfRadiusX;
                float top = getHeight() / 2f - quarterRadiusX;
                float bottom = getHeight() / 2f + quarterRadiusX;
                RectFloat rect = new RectFloat(left, top, right, bottom);
                canvas.drawOval(rect, paint);
            }
        });
    }

    private void initVariable(PixelMap pixelMap) {
        if (pixelMap != null) {
            holder = new PixelMapHolder(pixelMap);
            minImageLength = Math.min(getWidth(), getHeight());
        }
    }

    private PixelMap getPixelMap(int resId) {
        try (InputStream inputStream = getResourceManager().getResource(resId)) {
            ImageSource imageSource = ImageSource.create(inputStream, null);
            ImageSource.DecodingOptions decodingOpts = new ImageSource.DecodingOptions();
            decodingOpts.desiredSize = new Size(getWidth(), getHeight());
            decodingOpts.desiredRegion = new Rect(0, 0, 0, 0);
            return imageSource.createPixelmap(decodingOpts);
        } catch (Exception e) {
            HiLog.error(LABEL, "exception");
            return null;
        }
    }

    private PixelMap getPixelMap(FileDescriptor fileDescriptor) {
        ImageSource.DecodingOptions decodingOpts = new ImageSource.DecodingOptions();
        decodingOpts.desiredSize = new Size(getWidth(), getHeight());
        decodingOpts.desiredRegion = new Rect(0, 0, 0, 0);
        ImageSource imageSource = ImageSource.create(fileDescriptor, null);
        return imageSource.createThumbnailPixelmap(decodingOpts, true);
    }
}

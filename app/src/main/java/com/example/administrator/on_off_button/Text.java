package com.example.administrator.on_off_button;

/**
 * Created by yangchun on 2016-12-8.
 */

public class Text {
    /**
     *根元素 vector 是用来定义这个矢量图的，该元素包含如下属性：
     android:name 定义该drawable的名字
     android:width 定义该 drawable 的内部(intrinsic)宽度,支持所有 Android 系统支持的尺寸，通常使用 dp
     android:height 定义该 drawable 的内部(intrinsic)高度,支持所有 Android 系统支持的尺寸，通常使用 dp
     android:viewportWidth 定义矢量图视图的宽度，视图就是矢量图 path 路径数据所绘制的虚拟画布
     android:viewportHeight 定义矢量图视图的高度，视图就是矢量图 path 路径数据所绘制的虚拟画布
     android:tint 定义该 drawable 的 tint 颜色。默认是没有 tint 颜色的
     android:tintMode 定义 tint 颜色的 Porter-Duff blending 模式，默认值为 src_in
     android:autoMirrored 设置当系统为 RTL (right-to-left) 布局的时候，是否自动镜像该图片。比如 阿拉伯语。
     android:alpha 该图片的透明度属性


     group 支持的属性如下：

     android:name 定义 group 的名字
     android:rotation 定义该 group 的路径旋转多少度
     android:pivotX 定义缩放和旋转该 group 时候的 X 参考点。该值相对于 vector 的 viewport 值来指定的。
     android:pivotY 定义缩放和旋转该 group 时候的 Y 参考点。该值相对于 vector 的 viewport 值来指定的。
     android:scaleX 定义 X 轴的缩放倍数
     android:scaleY 定义 Y 轴的缩放倍数
     android:translateX 定义移动 X 轴的位移。相对于 vector 的 viewport 值来指定的。
     android:translateY 定义移动 Y 轴的位移。相对于 vector 的 viewport 值来指定的。

     vector 还支持 clip-path 元素。定义当前绘制的剪切路径。注意，clip-path 只对当前的 group 和子 group 有效。

     android:name 定义 clip path 的名字
     android:pathData 和 android:pathData 的取值一样。

     path 元素一共包含如下属性：
     android:name 定义该 path 的名字，这样在其他地方可以通过名字来引用这个路径
     android:pathData 和 SVG 中 d 元素一样的路径信息。
     android:fillColor 定义填充路径的颜色，如果没有定义则不填充路径
     android:strokeColor 定义如何绘制路径边框，如果没有定义则不显示边框
     android:strokeWidth 定义路径边框的粗细尺寸
     android:strokeAlpha 定义路径边框的透明度
     android:fillAlpha 定义填充路径颜色的透明度
     android:trimPathStart 从路径起始位置截断路径的比率，取值范围从 0 到1
     android:trimPathEnd 从路径结束位置截断路径的比率，取值范围从 0 到1
     android:trimPathOffset 设置路径截取的范围 Shift trim region (allows showed region to include the start and end), in the range from 0 to 1.
     android:strokeLineCap 设置路径线帽的形状，取值为 butt, round, square.
     android:strokeLineJoin 设置路径交界处的连接方式，取值为 miter,round,bevel.
     android:strokeMiterLimit 设置斜角的上限，Sets the Miter limit for a stroked path. 注： 当strokeLineJoin设置为 “miter” 的时候， 绘制两条线段以锐角相交的时候，所得的斜面可能相当长。当斜面太长，就会变得不协调。strokeMiterLimit 属性为斜面的长度设置一个上限。这个属性表示斜面长度和线条长度的比值。默认是 10，意味着一个斜面的长度不应该超过线条宽度的 10 倍。如果斜面达到这个长度，它就变成斜角了。当 strokeLineJoin 为 “round” 或 “bevel” 的时候，这个属性无效。

     */
}

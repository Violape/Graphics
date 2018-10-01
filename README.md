# 计算机图形学课程作业
　　本项目用于存放计算机图形学实验代码。
## 实验1：直线段的扫描转换算法
### 方法1：数值微分法（DDA）
#### 原理
　　已知过端点
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{0}(x_{0},y_{0})" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{0}(x_{0},y_{0})" title="P(x_{0},y_{0})" /></a>
 和
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{1}(x_{1},y_{1})" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{1}(x_{1},y_{1})" title="P_{1}(x_{1},y_{1})" /></a>
 的直线段，斜率为
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;k=\frac{y_{1}-y_{0}}{x_{1}-x_{0}}" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;k=\frac{y_{1}-y_{0}}{x_{1}-x_{0}}" title="k=\frac{y_{1}-y_{0}}{x_{1}-x_{0}}" /></a>
 。为了尽可能多画有效点，我们要沿着增长较慢的方向划线。在
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;\left&space;|&space;k&space;\right&space;|\leq&space;1" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;\left&space;|&space;k&space;\right&space;|\leq&space;1" title="\left | k \right |\leq 1" /></a>
 时，画线的过程为：从左端点开始向右步进，步长为1，每次计算相应的y坐标，取像素点(x,round(y))，即(x,(int)(y+0.5))作为新点的坐标。将公式变形成为带有增量的形式：
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;y_{i&plus;1}=kx_{i&plus;1}&plus;b=y_{i}&plus;k\Delta&space;x" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;y_{i&plus;1}=kx_{i&plus;1}&plus;b=y_{i}&plus;k\Delta&space;x" title="y_{i+1}=kx_{i+1}+b=y_{i}+k\Delta x" /></a>
 。由于步长为1，可得
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;y_{i&plus;1}=y_{i}&plus;k" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;y_{i&plus;1}=y_{i}&plus;k" title="y_{i+1}=y_{i}+k" /></a>
  。由此可以简化计算，将计算减少为每次一个加法。在
  <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;\left&space;|&space;k&space;\right&space;|>&space;1" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;\left&space;|&space;k&space;\right&space;|>&space;1" title="\left | k \right |> 1" /></a>
  时，情况类似，但要沿着纵轴向上步进，实现所需功能。
#### -1 ≤ k ≤ 1时DDA算法的算法程序：
```C++
void DDALine(int x0, int y0, int x1, int y1, int color){
    int x;
    float dx, dy, y, k;
    k = dy/dx, y = y0;
    for(x = x0; x <= x1; x++){
        drawpixel(x, (int)(y + 0.5), color);
        y = y + k;
    }
}
```
### 方法2：中点划线法（Midpoint）
#### 原理
　　对经过端点
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{0}(x_{0},y_{0})" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{0}(x_{0},y_{0})" title="P(x_{0},y_{0})" /></a>
 和
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{1}(x_{1},y_{1})" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{1}(x_{1},y_{1})" title="P_{1}(x_{1},y_{1})" /></a>
 的直线段，采用二元方程
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;F\left&space;(x,y&space;\right&space;)=ax&plus;by&plus;c=0" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;F\left&space;(x,y&space;\right&space;)=ax&plus;by&plus;c=0" title="F\left (x,y \right )=ax+by+c=0" /></a>
 表示，其中
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;a=y_{0}-y_{1},b=x_{1}-x_{0},c=x_{0}y_{1}-x_{1}y_{0}" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;a=y_{0}-y_{1},b=x_{1}-x_{0},c=x_{0}y_{1}-x_{1}y_{0}" title="a=y_{0}-y_{1},b=x_{1}-x_{0},c=x_{0}y_{1}-x_{1}y_{0}" /></a>
 。通过这样一个判别式确定点与直线的关系，即点相对于直线而言，有：
 
 
<center>
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;\left\{\begin{matrix}&space;On:F\left&space;(&space;x,y&space;\right&space;)=0\\&space;Over:F\left&space;(&space;x,y&space;\right&space;)>0\\&space;Under:F\left&space;(&space;x,y&space;\right&space;)<0&space;\end{matrix}\right." target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;\left\{\begin{matrix}&space;On:F\left&space;(&space;x,y&space;\right&space;)=0\\&space;Over:F\left&space;(&space;x,y&space;\right&space;)>0\\&space;Under:F\left&space;(&space;x,y&space;\right&space;)<0&space;\end{matrix}\right." title="\left\{\begin{matrix} On:F\left ( x,y \right )=0\\ Over:F\left ( x,y \right )>0\\ Under:F\left ( x,y \right )<0 \end{matrix}\right." /></a>
</center>
 
 
 因此，判断临界点在直线上方还是下方，仅需要使用判别式即可。对于0<k<1的场合，绘制完点
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P\left&space;(&space;x_{p},y_{p}&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P\left&space;(&space;x_{p},y_{p}&space;\right&space;)" title="P\left ( x_{p},y_{p} \right )" /></a>
 后需要判定的点为
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{1}\left&space;(&space;x_{p}&plus;1,y_{p}&plus;0.5&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{1}\left&space;(&space;x_{p}&plus;1,y_{p}&plus;0.5&space;\right&space;)" title="P_{1}\left ( x_{p}+1,y_{p}+0.5 \right )" /></a>
 。此时，通过使用判别式
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;d=F\left&space;(&space;M&space;\right&space;)=F\left&space;(&space;x_{p}&plus;1,y_{p}&plus;0.5&space;\right&space;)=a\left&space;(&space;x_{p}&plus;1&space;\right&space;)&plus;b\left&space;(&space;y_{p}&plus;0.5&space;\right&space;)&plus;c" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;d=F\left&space;(&space;M&space;\right&space;)=F\left&space;(&space;x_{p}&plus;1,y_{p}&plus;0.5&space;\right&space;)=a\left&space;(&space;x_{p}&plus;1&space;\right&space;)&plus;b\left&space;(&space;y_{p}&plus;0.5&space;\right&space;)&plus;c" title="d=F\left ( M \right )=F\left ( x_{p}+1,y_{p}+0.5 \right )=a\left ( x_{p}+1 \right )+b\left ( y_{p}+0.5 \right )+c" /></a>
 的符号判定即可。一般d>0时取靠下的一个像素，否则取靠上的一个像素。为了简化运算，我们采用增量运算，提高运算效率。当取靠下像素
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{1}\left&space;(&space;x_{p}&plus;1,y_{p}&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{1}\left&space;(&space;x_{p}&plus;1,y_{p}&space;\right&space;)" title="P_{1}\left ( x_{p}+1,y_{p} \right )" /></a>
 时，下一个判定点为
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;\left&space;(&space;x_{p}&plus;2,y_{p}&plus;0.5&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;\left&space;(&space;x_{p}&plus;2,y_{p}&plus;0.5&space;\right&space;)" title="\left ( x_{p}+2,y_{p}+0.5 \right )" /></a>
 ，d的增量为a。当取靠上像素
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;P_{2}\left&space;(&space;x_{p}&plus;1,y_{p}&plus;1&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;P_{2}\left&space;(&space;x_{p}&plus;1,y_{p}&plus;1&space;\right&space;)" title="P_{2}\left ( x_{p}+1,y_{p}+1 \right )" /></a>
 时，下一个判定点为
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;\left&space;(&space;x_{p}&plus;2,y_{p}&plus;1.5&space;\right&space;)" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;\left&space;(&space;x_{p}&plus;2,y_{p}&plus;1.5&space;\right&space;)" title="\left ( x_{p}+2,y_{p}+1.5 \right )" /></a>
 ，d的增量为a+b。d的初始值则为a+0.5b。为了计算方便，在不改变符号的情况下，将运算扩大一倍，消除浮点运算。
#### 0 ≤ k ≤ 1时中点算法的算法程序：
```C++
void MidpointLine(int x0, int y0, int x1, int y1, int color) {
    int a, b, d1, d2, d, x, y;
    a = y0 - y1, b = x1 - x0; d = 2 * a + b;
    d1 = 2 * a, d2 = 2 * (a + b);
    x = x0, y = y0;
    drawpixel(x, y, color);
    while(x < x1){
        if(d > 0){
            {x++; y++; d += d2;}
        else
            {x++; d += d1;}
        drawpixel(x, y, color);
    }
}
```
#### 其他情况的讨论
　　注意到，斜率大小和斜率符号会决定下一个点的取法，因此需要分开讨论。对于<strong>k > 1</strong>的情况，由于应该从y轴由下往上分析，因此初始点为(x+0.5, y+1)，d > 0时取左，下一点为(x+0.5, y+2)；否则取右，下一点为(x+1.5, y+2)。对于<strong>-1 < k < 0</strong>的情况，初始点变成了(x+1, y-0.5)，d > 0时取上，下一点为(x+2, y-0.5)，否则取下，下一点为(x+2, y-1.5)。对于<strong>k < -1</strong>的情况，初始点为（x-0.5, y+1），d > 0时取左，下一点为(x-1.5, y+2)， 否则取右，下一点为(x-0.5, y+2)。由于取点不断变化，d, d1, d2的计算方法都会变化，因此需要分多钟情况编写代码。

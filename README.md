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
 。画线的过程为：从左端点开始向右步进，步长为1，每次计算相应的y坐标，取像素点(x,round(y))，即(x,(int)(y+0.5))作为新点的坐标。将公式变形成为带有增量的形式：
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;y_{i&plus;1}=kx_{i&plus;1}&plus;b=y_{i}&plus;k\Delta&space;x" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;y_{i&plus;1}=kx_{i&plus;1}&plus;b=y_{i}&plus;k\Delta&space;x" title="y_{i+1}=kx_{i+1}+b=y_{i}+k\Delta x" /></a>
 。由于步长为1，可得
 <a href="http://www.codecogs.com/eqnedit.php?latex=\inline&space;y_{i&plus;1}=y_{i}&plus;k" target="_blank"><img src="http://latex.codecogs.com/gif.latex?\inline&space;y_{i&plus;1}=y_{i}&plus;k" title="y_{i+1}=y_{i}+k" /></a>
  。由此可以简化计算，将计算减少为每次一个加法。
#### 0<k<1时DDA算法的算法程序：
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

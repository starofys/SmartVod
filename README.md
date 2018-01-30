SmartVod
=========
简介
---------
- 1.整体工程采用MVP设计框架
- 2.模块化编程思想 分离各个模块 降低个模块之间的耦合度
- 3.工程主要划分为：
  >App主模块
  >API模块
  >Media模块
  >ViewLayer模块
  >LogicLayer模块
- 4.界面适配方面采用了像素自适应拉伸方式进行
- 5.播放器方面兼容了Native Player和IJK Player
- 6.播放器在单独进程中开启，避免播放器的异常问题导致App的Crash

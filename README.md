SmartVod
=========
简介
---------
- 整体工程采用MVP设计框架
- 模块化编程思想 降低各模块之间的耦合度
- 工程主要划分为：<br>
  1.App主模块<br>
  2.API模块<br>
  3.Media模块<br>
  4.ViewLayer模块<br>
  5.LogicLayer模块<br>
- 界面适配方面采用了像素自适应拉伸方式进行(基准分辨率为1920*1080)
- 播放器方面兼容了Native Player和IJK Player
- 播放器在单独进程中开启，避免播放器的异常问题导致App的Crash

使用注意点
-----------
### 传入初始化配置信息
>>* 启动app时传入配置信息
>>>* 服务器地址---pro://ip:port<br>
public static final String HOST = "host";
>>>* 登陆的用户名称<br>
public static final String USERNAME = "userName";
>>>* 媒体大组ID<br>
public static final String GROUPID = "programGroupID";
### 接收端代码
```Java
Intent intent = getIntent();
 if (null != intent) {
  String host = intent.getStringExtra(ConfigX.HOST);
		String userName = intent.getStringExtra(ConfigX.USERNAME);
		int programProupID = intent.getIntExtra(ConfigX.GROUPID, HttpConst.DEFAULT_GROUP_ID);
		ConfigMgr.getInstance().initEpgUrl(host);
		ConfigMgr.getInstance().initUserName(userName);
		ConfigMgr.getInstance().initGroupID(programProupID);
		Log.d(ConfigX.HHZT_SMART_LOG, "host=" + host + ";userName=" + userName + ";programProupID=" + programProupID);
	}
```
### LICENSE
```Java
Copyright (c) 2016-present, RxJava Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

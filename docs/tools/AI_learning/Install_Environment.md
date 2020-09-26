# 人工智能实践-安装#

## Conda Install On Windows##

* conda是开源的包管理系统，可以让用户方便的安装所需的包。

* conda包括anaconda以及miniconda两中选择，anaconda提前安装好用于科学计算的包，具有图形界面，但十分臃肿，达到3GB存储空间。而miniconda则是精简版本，需要自行安装所需要的包。

* 需要注意的是，安装conda时不要选择将其加入环境变量，否则会使conda污染其他软件的安装过程，conda会默认将新工具安装到其准备的环境。由于我下载的miniconda没有将其加入环境变量，无法在cmd使用conda命令，所以需要通过Anaconda powershell prompt启动（可在开始菜单看见）

* 关于conda的更多命令行使用方式可以看[这里](https://www.jianshu.com/p/edaa744ea47d)

## VSCode, Python and conda##

* 由于我和老师教程用了不一样的编译器(pycharm和VSCode)和conda工具(anaconda和miniconda)，所以会遇到不一样的问题。

* 由于开发一个项目时常常要特定版本的库，因此，为项目建立不同的库是必要的。conda具有创建环境的功能，操作是在miniconda/envs下建立一个新的文件夹，环境需要的库就安装在该文件夹下。但是，VSCode有独立的创建虚拟环境的功能，所以在vscode中无法应用新环境的库。

* 这个问题在网络上有五花八门的解决方案，且大多数都是玄学解法，没有任何解释。在偶然中，发现一个博客建议去官网寻找答案，问题在官网的指点下（似乎）得到了解决。即通过conda带有的anaconda prompt启动命令行，切换至目标环境，并在该终端界面启动vscode。测试样例最终和演示代码得到相同结果，安装完成。






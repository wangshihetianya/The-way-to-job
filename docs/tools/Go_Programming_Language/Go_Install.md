# Go语言安装 #
**1.下载Go语言安装包**

[官方网站](https://golang.org/dl/) 或者 [中国站点](https://golang.google.cn/dl/)

根据自己的操作系统选择对应的版本,我的系统为Windows操作系统-Amd CPU

**2.Go语言的使用**

[官方新手教程链接](https://golang.org/doc/tutorial/getting-started)

安装后，通过命令行操作

```
    go version      #查看Go版本，确认安装成功
    go run [programname].go     #运行Go
    go help         #查看帮助
```
官方提供了Hello, world的代码
```
    package main        

    import "fmt"

    func main() {
        fmt.Println("Hello, World!")
    }
```
# Go的功能拓展 #

Go语言通过package的方式拓展功能，更多的包可以在 [这里](https://pkg.go.dev/) 找到需要的包

可在代码所在的文件夹通过代码建立 go.mod 文件，该文件列出了代码所用到的package的信息
```
    go mod init [.go file name]
```
之后运行文件，会自动从官方站下载包，但是该下载过程似乎被墙挡住了，可考虑架梯子或者去github寻找需要的包，关于github地址可在官网的imported by标签下看到
当然，也可以使用go get指令直接下载包
```
    go get url  #如github/xx/xx
```
该指令需要有GoPath路径存放包，并安装好git来传输
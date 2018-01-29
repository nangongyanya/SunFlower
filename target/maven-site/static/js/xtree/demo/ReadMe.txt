=========================================================================
欢迎使用 Extended XTree(eXTree)， 如有任何问题，可反馈 luohc@toone.com.cn

=========================================================================
【注意】
	示例中的动态树要部署在Web服务器下才能正常运行(静态树可以直接用浏览器打开)。 
	建议你直接把压缩包解压在tomcat的webapps目录下既可。
	如果访问页面时报找不到 “XXX” 的脚本错误，或者显示不出图片，根据你的
	实际情况修改示例页面的include的脚本路径和图片路径即可。

        EXTree提供了API帮助文档及实例代码，详情请见开发帮助文档help.html.


eXtree 2.1        2006.05.15
==========================================================================
eXtree2.1兼容以前版本，更新如下：
+ 树节点对象增加setColor(color) 和 getColor()方法，可以改变节点文本的颜色。节点默认是黑色(black)，
   由webFXTreeConfig.defaultColor属性定义。setColor方法对disabled的节点也有效。
   如：将节点设为红色 node.setColor("red"); 动态加载节点可以在xml文件中指定color属性。
+ 树节点对象可以增加自定义属性。这一特性主要是用于动态加载树，通过xml文件
    指定节点的自定义属性，注意自定义属性名不能和已有的关键字同名。如：
    <tree id="nodeId" text="nodeName"  myAttrName="myAttrValue"/>
    该节点加载后，可以通过 getNodeById("nodeId").myAttrName 获取值 "myAttrValue"。
    静态节点可以直接支持自定义属性：node.myAttrName = "myAttrValue";
+ 树节点对象增加move(parentNode, index) 方法，将节点移动为指定节点的子节点。
    如：nodeA.move(nodeB); 节点nodeA（及其子节点，如有的话）将移动成为节点nodeB的
    最后一个子节点。
    目前只能移动成为最后一个子节点，不能指定索引位置(index)，这是一个缺陷。
* 修改了节点的expand()方法，当调用节点的expand()方法时，自动展开其祖先节点。
    动态加载节点的expand()还有些小问题，要 webFXTreeConfig.userPersistence=false。
* 增强了onchange事件属性。这个属性指向一个JavaScript方法，当鼠标单击radio、checkbox时，
    会触发这个事件，执行指定的JS方法。
    动态加载树在xml文件中指定这个属性。<tree onchange="doMyFunction"/>

   xtree.html 和 xloadtree.html 有这次更新的示例代码。


eXtree 2.0        2005.12.01
==========================================================================
综合一年来各项目对extended xtree 的使用情况，因此进行了升级，目的是提供更多的
方法以便操作树。
此次升级在结构方面和以前版本也有较大不同，因此版本号定为2.0。
eXtree2.0兼容以前版本，更新如下：
+ 树的节点对象增加setId(id),  getId() 方法，方便将业务对象和树节点进行关联。
        对于动态树，可以在xml文件中指定id值。id属性不是必要的，可以不设置。
	为了兼容以前版本，不通过构造函数传入id值。
+ 增加全局方法getNodeById(id)， 通过id获取树节点对象。
+ 树的节点对象增加setText(txt) 方法，方便不刷新整棵树而改变树节点名称。
+ 动态树的配置文件中 <tree /> 元素增加type属性，指定这个节点的类型，
   可以取值normal, check, radio三者之一（注意：区分大小写，只能是小写），
   分别表示“一般类型”、“checkbox类型”、“radiobutton类型”。
   当不指定type属性时，节点的类型与它的父节点类型相同。

以上的方法在xtree.html中有示例代码。

详情请见开发帮助文档help.html.


eXtree 1.2        2005.02.15
==========================================================================
+ webFXTreeConfig 对象增加方法 setImagePath：设置EXTree图片路径；
+ webFXTreeConfig 对象增加属性 cascadeCheck：定义CheckBox树是否级联选择，即改变
     当前节点的Check状态时，是否影响其子节点和父节点的Check状态；
* 修改了对cookie的实现。以前版本若使用cookie（webFXTreeConfig.usePersistence=true）
     记下节点的open/close状态，会导致session 失效，eXtree1.2 解决了这个问题。
* 修改了控件树（CheckBox和Raido树）通过构造方法传入bChecked 参数时，会获取不到它的值的问题。

详情请见开发帮助文档help.html.


eXtree 1.1        2004.12.27
==========================================================================
+ 增加了一个js文件(map.js)，里面定义了Map对象（这个Map对象和java中的Map类似，
      map.js可以单独拿出来在其它的地方使用）；
+ CheckBox树增加方法 getCheckObjects(boolean)；
* CheckBox树修改了方法 getCheckTexts(boolean) 的参数; 
* 修改了控件树（CheckBox和Raido树）的构造方法参数顺序, bChecked被移到倒数第二的位置；
* 改进了算法，提高了控件树获取值的速度；
* 修改了动态控件树的一些小问题。

详情请见开发帮助文档help.html.


eXTree 1.0       2004.12.03
==========================================================================
      XTree相信很多人都有所了解或者使用过。它所用面向对象的方式使得
生成一棵树非常简单。我在我所参与的项目中都使用XTree在页面生成树。

最新版的XTree是1.17版，里面有静态(动态)的普通类型树和静态的CheckBox树。
我在此版本的基础上进行了部分修改和扩展，修改了XTree里面的CheckBox树
（XTree的静态CheckBox树的CheckBox 没有value值，无法满足使用的要求），
增加了动态装载子节点的CheckBox树和静态（动态）Radio树。还提供了很多有用的方法。

我把它命名为 Extended XTree 1.0（eXTree1.0）。目前项目中要使用树的地方eXTree几乎都能满足。
对于每种类型的树我都做了示例代码，并提供了开发帮助文档(API)。
eXTree1.0支持跨平台，可以在支持DOM1的浏览器运行，但我只在我机器（IE6）上测试过。

特点：
   1.  面向对象，简单易用
   2.  每个节点都可以有不同的图标
   3.  提供很多有用的方法，满足开发要求
   4.  树中的控件（Checkbox, Radio）可以设置成disabled状态
   5.  跨平台,可以在支持DOM1的浏览器运行


--------------------------------------------------------------------------------------------------
                    Toone  Corporation         Apollo          2004.12.03              
--------------------------------------------------------------------------------------------------


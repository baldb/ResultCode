# 工程简介
1.简单的整合了对JSON返回集的统一处理。
2.各个状态码所代表的具体信息。
3.有返回和没返回参数时调用的构造函数不同。


# 类及其对应的作用：
1.ResultCodeEnum枚举类型
            <1>：已经定义好不同情况下返回不同的状态码及其信息提示。
            <2>：其中两个成员属性 code；message；分别代表状态码和返回的信息提示
            <3>：private ResultCodeEnum(Integer code, String message){}带有参数的构造器。
            
2.Result<T>类的规划：
            <1>：包含三个成员属性：code 状态码；message 返回消息提示；data 返回的数据          
            <2>：build(T data)和build(T data,ResultCodeEnum resultCodeEnum)方法：
                 后者调用前者去实例化一个result并存入数据及从枚举类型ResultCodeEnum中获取对应的code和message。            
            <3>：操作成功的方法：ok(T data)和ok()：
                 区别在于前者有返回数据，后者没有数据即调用前者并传入一个null。            
            <4>：操作失败的方法：同成功的方法一致。            


1.	Hardware
a.	Register
基础类，用于构造Computer各个部件，用于实现各部件的基本显示，存取，转换等。某些方法需按寄存器的bit位数来进行重载。
2.	Computer
a.	CPU
CPU各个部件，PC，IR等等。由Register类声明构造。
b.	Cache
Register类型的一维数组（暂）。
c.	Memory
Register类型的一维数组。
3.	Operation
a.	Decode
Instruction的分解并送到CPU的相应部件。
b.	Compute
用于模拟硬件上的计算。
c.	Transfer
用于模拟硬件上的数据转移。
4.	Execution
a.	Command
LDR,STR等命令的分解与执行。
b.	Load/Words
从txt读入到memory
5.	Simulate
a.	Simulate
整个的模拟器运行主程序的流程。
6.	Interface
a.	Interface
界面

"""
ss = socket() #创建服务器套接字
ss.bind() #地址绑定
ss.listen() #监听连接
inf_loop: #服务器无限循环
    cs = ss.accept() #与客户端连接
    comm_loop:
        cs.recv()/send()
    cs.close()
ss.close()
"""

import os
from socket import *
from time import ctime

HOST = ''
PORT = 23333
BUFSIZ = 1024
ADDR = (HOST,PORT)

tcpSerSock = socket(AF_INEF,SOCK_STPREM)
tcpSerSock.bind(ADDR)
tcpSerSock.listen(5)

while True:
    print('waiting for connection...')
    tcpCliSock, addr = tcpSerSock.accept()
    print ('...connected from:', addr)
    while True:
        data = tcpCliSock.recv(BUFSIZ)
        print("recv:",data.decode("utf-8"))
        if not data:
            break
        filename = data.decode("utf-8")
        if os.path.exists(filename):
            filesize = str(os.path.getsize(filename))
            print("文件大小为：",filesize)
            tcpCliSock.send(filesize.encode())
            data = tcpCliSock.recv(BUFSIZ)
            print("开始发送")
            f = open(filename, "rb")
            for line in f:
                tcpCliSock.send(line)
        else:
            tcpCliSock.send("0001".encode())
    tcpCliSock.close()
tcpSerSock.close()
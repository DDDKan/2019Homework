from socket import *

HOST = 'localhost'
PORT = 23333
BUFSIZ = 1024
ADDR = (HOST, PORT)

tcpCliSock = socket(AF_INET, SOCK_STREAM)
tcpCliSock.connect(ADDR)

while True:
    message = input('> ')
    if not message:
        break
    tcpCliSock.send(bytes(message, 'utf-8'))
    data = tcpCliSock.recv(BUFSIZ)
    if not data:
        break
    if data.decode() == "0001":
        print("Source file %s not found"%message)
    else:
        tcpCliSock.send("File size received".encode())
        file_total_size = int(data.decode())
        received_size = 0
        f = open("new" + message  ,"wb")
        while received_size < file_total_size:
            data = tcpCliSock.recv(BUFSIZ)
            f.write(data)
            received_size += len(data)
            print("already received:",received_size)
        f.close()
        print("receive done",file_total_size," ",received_size)
tcpCliSock.close()
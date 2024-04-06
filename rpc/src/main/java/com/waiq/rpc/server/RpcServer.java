package com.waiq.rpc.server;

import com.waiq.rpc.registry.ServiceRegistry;
import com.waiq.rpc.server.WorkerThread;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

@Slf4j
public class RpcServer {
    private final static int CORE_POOL_SIZE=5;
    private final static int MAXIMUM_POOL_SIZE=50;
    private final static int KEEP_ALIVE_TIME=60;
    private final static int BLOCKING_QUEUE_CAPACITY=100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;
    public RpcServer(ServiceRegistry serviceRegistry){
        this.serviceRegistry=serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,workingQueue,threadFactory);
    }

    public void start(int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            log.info("服务器正在启动");
            Socket socket;
            while((socket=serverSocket.accept())!=null){
                log.info("客户端连接! IP为: "+socket.getInetAddress());
                threadPool.execute(new RequestHandlerThread(socket,requestHandler,serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e){
            log.error("连接时有错误发生:{}",e);
        }
    }

 }

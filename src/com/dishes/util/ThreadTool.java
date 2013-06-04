/**
 * 
 */
package com.dishes.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dishes.common.Constant;

/**
 * @author SenYang 唯一线程池
 */
public class ThreadTool {

	private static ThreadTool threadTool;
	private ExecutorService threadpool;


	private ThreadTool() {

		threadpool = Executors.newFixedThreadPool( Constant.UtilConstant.THREADCOUNT );
	}


	public static ThreadTool getInstance() {

		if( threadTool == null ) {
			threadTool = new ThreadTool();
		}

		return threadTool;

	}


	public synchronized void addTask( Runnable runnable ) {

		threadpool.execute( runnable );

	}


	public void cancelTask() {

		if( !threadpool.isTerminated() ) {
			threadpool.shutdownNow();
		}
	}
}

/**
 * 
 */
package com.dishes.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dishes.common.Constant;
import com.dishes.util.ImageLoader.ImageLoadTask;

/**
 * 唯一线程池
 * 
 * @author SenYang
 */
public class ThreadTool {

	private static ThreadTool threadTool;
	private static ArrayList<ImageLoadTask> imageLoadTasks;
	private ExecutorService threadpool;


	private ThreadTool() {

		threadpool = Executors.newFixedThreadPool( Constant.UtilConstant.THREADCOUNT );
		imageLoadTasks = new ArrayList<ImageLoader.ImageLoadTask>();
	}


	public synchronized static ThreadTool getInstance() {

		if( threadTool == null ) {
			threadTool = new ThreadTool();
		}

		return threadTool;

	}


	/**
	 * @return the imageLoadTasks
	 */
	public static ArrayList<ImageLoadTask> getImageLoadTasks() {

		return imageLoadTasks;
	}


	public synchronized void addTask( Runnable runnable ) {

		threadpool.execute( runnable );

	}


	public void endTask( ) {

		while( imageLoadTasks.size() != 0 ) {
			ImageLoadTask task = imageLoadTasks.remove( 0 );
			task.stopTask();
		}

	}


	public void cancelTask() {

		if( !threadpool.isTerminated() ) {
			threadpool.shutdownNow();
		}
	}
}

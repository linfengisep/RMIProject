1.Future
A Future represents the result of an asynchronous computation.
Methods are provided to check if the computation is complete, to wait for its completion,
and to retrieve the result of the computation. The result can only be retrieved using method get
when the computation has completed, blocking if necessary until it is ready.


2.ExecutorService
2.1,Executor
An Executor that provides methods to manage termination
and methods that can produce a Future for tracking progress of one or more asynchronous tasks.

2.2,ExecutorService
An ExecutorService can be shut down, which will cause it to reject new tasks.
Two different methods are provided for shutting down an ExecutorService.
shutdown() method will allow previously submitted tasks to execute before terminating,
shutdownNow() method prevents waiting tasks from starting and attempts to stop currently executing tasks.

submit() extends base method Executor.execute(java.lang.Runnable) by creating and returning a Future that can be used to cancel execution and/or wait for completion.

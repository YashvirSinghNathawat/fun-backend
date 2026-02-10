
# Virtual Threads
1. Java virtual threads are different from the original platform threads in that virtual threads
2. Lighter than thread less RAM
3. A platform thread can only execute one virtual thread at a time.
4. While the virtual thread is being executed by a platform thread - the virtual thread is said to be mounted to that thread
5. A virtual thread that executes some blocking network call (IO) will be unmounted from the platform thread while waiting for the response.
6. In the meantime the platform thread can execute another virtual thread.
7.  As long as a virtual thread is running code and is not blocked waiting for a network response - the platform thread will keep executing the same virtual thread.
8. File system call does not unmount the virtual thread.